package com.hanhui.stumanage;

import com.hanhui.stumanage.model.User;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Test {

    public static void main(String[] args) {

        ExpressionParser parser = new SpelExpressionParser();

        SpelExpression exp = (SpelExpression)parser.parseExpression("#user.getUserName()");

        StandardEvaluationContext ctx = new StandardEvaluationContext();
        User user = new User();
        user.setUserName("jackie");
        ctx.setVariable("user", user);

        String value = (String)exp.getValue(ctx);
        System.out.println(value);

        ctx.setVariable("userId", "123312312");
        SpelExpression exp1 = (SpelExpression)parser.parseExpression("#userId");
        String value1 = (String)exp1.getValue(ctx);
        System.out.println(value1);
    }
}
