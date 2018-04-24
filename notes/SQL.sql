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














