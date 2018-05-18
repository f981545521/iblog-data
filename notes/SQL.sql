-- 建表语句
DROP DATABASE IF EXISTS iblog_data;
CREATE DATABASE IF NOT EXISTS iblog_data DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;
USE iblog_data;
-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `age` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '刘备', '34');
INSERT INTO `student` VALUES ('2', '曹操', '45');
INSERT INTO `student` VALUES ('3', '孙权', '43');
INSERT INTO `student` VALUES ('4', '袁绍', '38');
INSERT INTO `student` VALUES ('5', '马腾', '58');
INSERT INTO `student` VALUES ('6', '关羽', '23');
INSERT INTO `student` VALUES ('7', '史蒂夫', '27');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `age` int(3) NOT NULL DEFAULT '0',
  `student_id` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='老师';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '吴菲菲', '34', '1');
INSERT INTO `teacher` VALUES ('2', '扣扣', '45', '1');
INSERT INTO `teacher` VALUES ('3', '范德萨', '43', '1');









