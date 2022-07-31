
DROP TABLE IF EXISTS `stu_info`;

CREATE TABLE `stu_info` (
    `stu_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `stu_number` varchar(16) NOT NULL unique COMMENT '学生学号',
    `stu_name` varchar(12) DEFAULT NULL COMMENT '学生姓名',
    `stu_identity_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
    `stu_gender` varchar(12) DEFAULT NULL COMMENT '性别',
    `stu_nation` varchar(12) DEFAULT NULL COMMENT '民族',
    `stu_native_place` varchar(255) DEFAULT NULL COMMENT '籍贯',
    `stu_phone_number` varchar(11) DEFAULT NULL COMMENT '学生手机号码',
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


