

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


