-- 建表语句
DROP DATABASE IF EXISTS iblog_data;
CREATE DATABASE IF NOT EXISTS iblog_data DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;
USE iblog_data;
CREATE TABLE `t_boss` (
  `id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name`  varchar(20)  NOT NULL DEFAULT '' ,
  `age`  int(3) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='老板';

INSERT INTO `student` (`id`, `name`, `age`) VALUES ('1', '刘备', '34');
INSERT INTO `student` (`id`, `name`, `age`) VALUES ('2', '曹操', '45');
INSERT INTO `student` (`id`, `name`, `age`) VALUES ('3', '孙权', '43');
INSERT INTO `student` (`id`, `name`, `age`) VALUES ('4', '袁绍', '38');











