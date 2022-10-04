
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` varchar(32) NOT NULL COMMENT '用户账号',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_status` int(1) NOT NULL COMMENT '1:正常,0:暂停',
  `user_role`   varchar(10) NOT NULL COMMENT 'SYS:管理员； TEACH:教师；STU:学生',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `teach_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `teach_code` varchar(32) NOT NULL unique COMMENT '教师编号',
  `teach_name` varchar(50) NOT NULL COMMENT '教师名称',
  `teach_identity_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `teach_gender` varchar(12) DEFAULT NULL COMMENT '性别',
  `teach_phone_number` varchar(20) DEFAULT NULL COMMENT '教师手机号码',
  `teach_status` int(1) NOT NULL COMMENT '1:正常,0:暂停',
  PRIMARY KEY (`teach_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `stu_info`;

CREATE TABLE `stu_info` (
    `stu_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `stu_number` varchar(16) NOT NULL unique COMMENT '学生学号',
    `stu_name` varchar(12) DEFAULT NULL COMMENT '学生姓名',
    `stu_identity_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
    `stu_gender` varchar(12) DEFAULT NULL COMMENT '性别',
    `stu_nation` varchar(12) DEFAULT NULL COMMENT '民族',
    `stu_native_place` varchar(255) DEFAULT NULL COMMENT '籍贯',
    `stu_phone_number` varchar(20) DEFAULT NULL COMMENT '学生手机号码',
    `stu_political` varchar(20) DEFAULT NULL COMMENT '政治面貌',
    `stu_birth_date` date DEFAULT NULL COMMENT '出生日期',
    `stu_home_address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
    `stu_emergency_contact` varchar(20) DEFAULT NULL COMMENT '紧急联系人',
    `stu_emergency_contact_phone` varchar(20) DEFAULT NULL COMMENT '紧急联系人号码',
    `stu_grade` varchar(12) DEFAULT NULL COMMENT '学生年级',
    `stu_faculty` varchar(24) DEFAULT NULL COMMENT '学生院系',
    `stu_major` varchar(24) DEFAULT NULL COMMENT '学生专业',
    `stu_class` varchar(12) DEFAULT NULL COMMENT '学生班级',
    `stu_status` int(1) DEFAULT NULL COMMENT '学生状态',
    `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `course_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_number` varchar(16) NOT NULL unique COMMENT '课程编号',
  `course_name` varchar(32) NOT NULL  COMMENT '课程名称',
  `course_type` int(2) NOT NULL  COMMENT '选修/必修',
  `course_total` int(3)   COMMENT '开放人数',
  `course_remain` int(3)  COMMENT '剩余可选',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `major_info`;
CREATE TABLE `major_info` (
  `major_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `major_number` varchar(16) NOT NULL unique COMMENT '专业编号',
  `major_name` varchar(32) NOT NULL  COMMENT '专业名称',
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `course_x_stu`;
CREATE TABLE `course_x_stu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_number` varchar(16) NOT NULL  COMMENT '课程编号',
  `stu_number` varchar(16) NOT NULL  COMMENT '学生学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `major_x_stu`;
CREATE TABLE `major_x_stu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `major_number` varchar(16) NOT NULL  COMMENT '专业编号',
  `stu_number` varchar(16) NOT NULL  COMMENT '学生学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `score_x_stu`;
CREATE TABLE `score_x_stu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_number` varchar(16) NOT NULL  COMMENT '课程编号',
  `stu_number` varchar(16) NOT NULL  COMMENT '学生学号',
  `score` int(3)   NULL  COMMENT '课程分数',
  `term`  varchar(16)   NULL  COMMENT '学期',
  `year`  varchar(16)   NULL  COMMENT '学年',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_number` varchar(16) NOT NULL unique COMMENT '班级编号',
  `class_name` varchar(32) NOT NULL  COMMENT '班级名称',
  `class_manager` varchar(32) NOT NULL  COMMENT '辅导员/班主任编号',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `class_x_stu`;
CREATE TABLE `class_x_stu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_number` varchar(16) NOT NULL  COMMENT '班级编号',
  `stu_number` varchar(16) NOT NULL  COMMENT '学生学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
