

DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_number` varchar(16) NOT NULL unique COMMENT '班级编号',
  `class_name` varchar(32) NOT NULL unique COMMENT '班级名称',
  `class_manager` varchar(32) NOT NULL unique COMMENT '辅导员/班主任编号',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `class_x_stu`;
CREATE TABLE `class_x_stu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_number` varchar(16) NOT NULL  COMMENT '班级编号',
  `stu_number` varchar(16) NOT NULL  COMMENT '学生学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
