

DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `course_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_number` varchar(16) NOT NULL unique COMMENT '课程编号',
  `course_name` varchar(32) NOT NULL  COMMENT '课程名称',
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
  `score` int(3) NOT NULL default 0 COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `major_x_stu`;
CREATE TABLE `major_x_stu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `major_number` varchar(16) NOT NULL  COMMENT '专业编号',
  `stu_number` varchar(16) NOT NULL  COMMENT '学生学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;