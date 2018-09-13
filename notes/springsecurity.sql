DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;


-- 存储过程

-- 创建存储过程(查询得到男性或女性的数量, 如果传入的是0就女性否则是男性)
DELIMITER $
CREATE PROCEDURE iblog_data.ges_user_count(IN stu_age INT, OUT user_count INT)
  BEGIN
      SELECT COUNT(*) FROM iblog_data.student s WHERE s.age < stu_age INTO user_count;
  END
$

-- 调用存储过程
DELIMITER ;
SET @user_count = 0;
CALL iblog_data.ges_user_count(50, @user_count);
SELECT @user_count as total;