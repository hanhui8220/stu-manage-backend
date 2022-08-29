package com.hanhui.stumanage.cache;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

@Component  //1.我是一个javaBean
@Aspect     //2.我是一个切面
@Slf4j
public class CacheAOP {

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    private static final ExpressionParser PARSER = new SpelExpressionParser();


    @Around("@annotation(com.hanhui.stumanage.cache.RedisCache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RedisCache redisCache = signature.getMethod().getAnnotation(RedisCache.class);
        String expression = redisCache.key();
        String key = String.valueOf(parseExpression(expression,joinPoint));
        String name = redisCache.name();
        key = name + "::" + key;
        log.info("redis key  : {} ",key);
        Object cachedValue = redisTemplate.opsForValue().get(key);
        if (cachedValue != null) {
            log.info("get cache from redis : {} ",cachedValue);
            return cachedValue;
        } else {
            Object realValue = joinPoint.proceed();
            log.info("get data from database : {} ",realValue);
            redisTemplate.opsForValue().set(key, realValue);
            return realValue;
        }
    }


    @Around("@annotation(com.hanhui.stumanage.cache.CacheEvict)")
    public Object cacheEvict(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CacheEvict redisCache = signature.getMethod().getAnnotation(CacheEvict.class);
        String expression = redisCache.key();
        String key = String.valueOf(parseExpression(expression,joinPoint));
        String name = redisCache.name();
        key = name + "::" + key;
        Object realValue = joinPoint.proceed();
        log.info("delete key from redis : {} ",key);
        redisTemplate.delete(key);
        return realValue;
    }




    private Object parseExpression(String expression,ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Method targetMethod = getTargetMethod(joinPoint);
        Parameter[] parameters = targetMethod.getParameters();
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for(int i = 0;i < parameters.length; i++){
            ctx.setVariable(parameters[i].getName(),args[i]);
        }

        SpelExpression exp  = (SpelExpression)PARSER.parseExpression(expression);
        Object value = exp.getValue(ctx);
        return value;
    }

    private Method getTargetMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method agentMethod = methodSignature.getMethod();
        return pjp.getTarget().getClass().getMethod(agentMethod.getName(),agentMethod.getParameterTypes());
    }
}
