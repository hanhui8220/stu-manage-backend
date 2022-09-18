package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanhui.stumanage.constant.UserRole;
import com.hanhui.stumanage.constant.UserStatus;
import com.hanhui.stumanage.dao.*;
import com.hanhui.stumanage.entity.CourseStuentRelEntity;
import com.hanhui.stumanage.entity.ScoreStudentEntity;
import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.entity.UserEntity;
import com.hanhui.stumanage.mapper.ScoreStudentMapper;
import com.hanhui.stumanage.model.*;
import com.hanhui.stumanage.util.RandomNameUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TestDataService {

    @Resource
    private UserService userService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    @Resource
    private ClassService classService;

    @Resource
    private CourseService courseService;

    @Resource
    private MajorService majorService;

    @Resource
    private StudentDao studentDao;

    @Resource
    private CourseStudentDao courseStudentDao;

    @Resource
    private ScoreStudentDao scoreStudentDao;

    private static final String PWD = "123";

    @Transactional(rollbackFor=Exception.class)
    public Boolean initTestData(){

//        initUser();
//        initMajor();
//        initCourse();
//        initScore();

//        updateStuGrade();
        return true;
    }


    void updateStuGrade(){
        String [] gradeArr = new String[]{"2019","2020","2021","2022"};
        List<StudentEntity> studentEntities = studentDao.selectList(new QueryWrapper<StudentEntity>());
        for (StudentEntity entity : studentEntities) {
            int index = RandomNameUtil.getRandomNum(3);
            String grade = gradeArr[index] +"级";
            entity.setStuGrade(grade);
            studentDao.updateById(entity);
        }
    }

    void initScore(){

        List<CourseStuentRelEntity> list = courseStudentDao.selectList(new QueryWrapper<CourseStuentRelEntity>());

        for (CourseStuentRelEntity courseStuentRelEntity : list) {
            for(int i = 2022;i<= 2022; i++ ){
                int score = RandomNameUtil.getRandomNum(40, 60);
                ScoreStudentEntity entity = new ScoreStudentEntity();
                entity.setCourseNumber(courseStuentRelEntity.getCourseNumber());
                entity.setStuNumber(courseStuentRelEntity.getStuNumber());
                entity.setYear(i+"");
                entity.setTerm("第一学期");
                entity.setScore(score);
                scoreStudentDao.insert(entity);

                int score1 = RandomNameUtil.getRandomNum(40, 60);
                ScoreStudentEntity entity1 = new ScoreStudentEntity();
                entity1.setCourseNumber(courseStuentRelEntity.getCourseNumber());
                entity1.setStuNumber(courseStuentRelEntity.getStuNumber());
                entity1.setYear(i+"");
                entity1.setTerm("第二学期");
                entity1.setScore(score1);
                scoreStudentDao.insert(entity1);
            }
        }

    }


    void initCourse(){

        String course1 = "思想道德修养与法律基础,近现代史纲要,毛泽东思想与社会主义特色理论,马克思主义原理,高等数学,大学英语" +
                ",计算机基础,线性代数";

        String course2 = "中国语言文学,历史学,哲学,营养与健康,书法,中国书画,心理学,法国文化与艺术鉴赏,佛学文化中的生命探索与实践," +
                "初级法语,创业经济学,微处理器应用设计与实训,旅游地理,旅游资源开发与规划,房地产经纪概论,世界文明史,Python与人工智能," +
                "电影艺术欣赏";

        String[] coursrArr1 = course1.split(",");// 0 - 7 ; 8
        System.out.println("必修课------》"+coursrArr1.length);
        String[] coursrArr2 = course2.split(","); // 0-17 ; 18
        System.out.println("选修课------》"+coursrArr2.length);

        List<Course> needCourseList = new ArrayList<>();
        // 0 :  必修 ;  1: 选修
        for(int i = 0;i<coursrArr1.length;i++){
            Course course = new Course()
                    .setCourseName(coursrArr1[i])
                    .setCourseType(0)
                    .setCourseTotal(0)
                    .setCourseRemain(0)
                    .setCourseNumber("CO000"+(i+1));
            needCourseList.add(courseService.insert(course));
        }




        List<Course> flexCourseList = new ArrayList<>();

        for(int i = 0; i< coursrArr2.length;i++){
            Course course = new Course()
                    .setCourseName(coursrArr2[i])
                    .setCourseType(1)
                    .setCourseTotal(50)
                    .setCourseRemain(50)
                    .setCourseNumber("CL000"+(i+1));
            flexCourseList.add(courseService.insert(course));
        }

        // 必修课 ，每个人都必选
        List<StudentEntity> entities = studentDao.selectList(new QueryWrapper<StudentEntity>());
        for (StudentEntity entity : entities) {
            for (Course course : needCourseList) {
                CourseStuentRelEntity courseStuentRelEntity = new CourseStuentRelEntity();
                courseStuentRelEntity.setCourseNumber(course.getCourseNumber())
                        .setStuNumber(entity.getStuNumber());
                courseStudentDao.insert(courseStuentRelEntity);
            }

            // 选修课 每个人选修四门
            int flex = 1;
            while (true){
                if(flex == 4){
                    break;
                }
                int index = RandomNameUtil.getRandomNum(17);
                Course course = flexCourseList.get(index);
                CourseStuentRelEntity selectOne = courseStudentDao.selectOne(new QueryWrapper<CourseStuentRelEntity>()
                        .eq("course_number", course.getCourseNumber())
                        .eq("stu_number", entity.getStuNumber()));
                if(selectOne != null){
                    continue;
                }else{
                    CourseStuentRelEntity courseStuentRelEntity = new CourseStuentRelEntity();
                    courseStuentRelEntity.setCourseNumber(course.getCourseNumber())
                            .setStuNumber(entity.getStuNumber());
                    courseStudentDao.insert(courseStuentRelEntity);
                    flex++;
                }
            }

        }



    }


    void initMajor(){

        String major = "经济学,统计学,哲学,材料成型及控制工程,市场营销,会计学,生物工程,水利工程,工程造价,行政管理,人力资源管理," +
                "数学与应用数学,化学,物理学,应用化学,地理科学,生物科学,统计学,动物医学,市场营销,人力资源管理,财务管理,会计学,审计学,行政管理,物流管理,电子商务,旅游管理" +
                "机械设计制造及自动化,通信工程,自动化,信息工程,电子信息工程,测绘工程,化学工程与工艺,制药工程,交通运输,环境工程,环境科学,建筑学,城乡规划,电气工程及其自动化,土木工程";
        String[] majorArr = major.split(",");
        System.out.println("共多少个专业 ------> " + majorArr.length);

        List<Major> majorList = new ArrayList<>();
        for(int i = 0;i < majorArr.length;i++){
            Major major1  = new Major()
                    .setMajorName(majorArr[i]);
            if(i < 9){
                major1.setMajorNumber("M000"+(i+1));
            }else{
                major1.setMajorNumber("M00"+(i+1));
            }
            majorList.add(majorService.insert(major1));
        }

        // user
        List<StudentEntity> entities = studentDao.selectList(new QueryWrapper<StudentEntity>());
        for (StudentEntity entity : entities) {
            int majorIndex = RandomNameUtil.getRandomNum(42);
            Major major1 = majorList.get(majorIndex);
            entity.setStuMajor(major1.getMajorNumber());
            studentDao.updateById(entity);
        }
    }

    void initUser(){
        // 1.  admin
        User user = new User().setStatus(UserStatus.ACTIVE).setPassWord(PWD).setUserName("超级管理员")
                .setUserCode("admin").setUserRole(UserRole.SYS_ADMIN);
        userService.inserUser(user);

        // 2. tearcher
        // 32120219880721
        String []  classArr = new String[]{"一","二","三","四","五",
                "六","七","八","九","十"};

        List<ClassInfo>  classInfoList = new ArrayList<>();
        for(int i = 1;i <= 20;i++){
            Teacher teacher = new Teacher()
                    .setStatus(1);
            if(i < 10){
                teacher.setTeachCode("T000"+i);
                teacher.setTeachIdentityCard("3212021988072100" + i);
                teacher.setTeachPhoneNumber("13100000000"+i);
            }else{
                teacher.setTeachCode("T00"+i);
                teacher.setTeachIdentityCard("32120219880721" + i);
                teacher.setTeachPhoneNumber("1310000000"+i);
            }
            Integer gender = RandomNameUtil.getGender();
            teacher.setTeachGender(gender+"");
            String sex = gender == 1 ? "男":"女";
            teacher.setTeachName(RandomNameUtil.getFamilyName() + RandomNameUtil.getNameAndSex(sex)[0]);
            Teacher insert = teacherService.insert(teacher);

            // class
            if(i <= 10){
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassManager(insert.getTeachCode()).setClassName(classArr[i-1] + "班")
                        .setClassNumber("C000"+i);
                classInfoList.add(classService.insert(classInfo));
            }

        }

        // 3. student
        for(int i = 1;i <= 200;i++){
            Student student = new Student();
            student.setStatus(1);
            if(i < 10){
                student.setStuNumber("S0000"+i);
                student.setStuIdentityCard("3212021988072100" + i);
                student.setStuPhoneNumber("13100000000"+i);
            }else if (i < 100){
                student.setStuNumber("S000"+i);
                student.setStuIdentityCard("321202198807210" + i);
                student.setStuPhoneNumber("1310000000"+i);
            }else{
                student.setStuNumber("S00"+i);
                student.setStuIdentityCard("32120219880721" + i);
                student.setStuPhoneNumber("131000000"+i);
            }
            student.setRemarks("备注"+i).setStuBirthDate(new Date())
                    .setStuEmergencyContact("紧急联系人"+i).setStuEmergencyContactPhone("13111001111")
                    .setStuNation("汉").setStuNativePlace("江苏").setStuPolitical("团员")
                    .setStuHomeAddress("江苏省南京市").setStuGender("一年级").setStuFaculty("机电工程系")
            ;
            Integer gender = RandomNameUtil.getGender();
            student.setStuGender(gender+"");
            String sex = gender == 1 ? "男":"女";
            student.setStuName(RandomNameUtil.getFamilyName() + RandomNameUtil.getNameAndSex(sex)[0]);

            int classIndex = RandomNameUtil.getRandomNum(10);
            ClassInfo classInfo = classInfoList.get(classIndex);
            student.setStuClass(classInfo.getClassNumber());
            studentService.insert(student);
        }
    }


}
