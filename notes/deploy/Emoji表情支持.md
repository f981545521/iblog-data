### MySQL支持
mysql 保存emoji时报，数据库报错：Caused by: java.sql.SQLException: Incorrect string value: '\xF0\x9F\x98\x82\xF0\x9F...' for column 'review' at row 1

我们可以看到错误提示中的字符0xF0 0x9F 0x98 0x84 ，这对应UTF-8编码格式中的4字节编码（UTF-8编码规范）。正常的汉字一般不会超过3个字节，为什么为出现4个字节呢？实际上是它对应的是智能手机输入法中的表情。那为什么会报错呢？因为mysql中的utf-8并不是真正意义上的utf-8，它只能存储1~3个字节长度的utf-8编码，如果想存储4个字节的必须用utf8mb4类型。不而要使用utf8mb4类型，首先要保证Mysql版本要不低于 MySQL 5.5.3。
#### 使用utf8mb4
1. 使用utf8mb4数据类型
[client]
default-character-set = utf8mb4
[mysql]
default-character-set = utf8mb4
[mysqld]
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci

2. 将数据库中对应的字段，改为utf8mb4_general_ci

　　# 对每一个数据库:
　　ALTER DATABASE 这里数据库名字 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
　　# 对每一个表:
　　ALTER TABLE 这里是表名字 CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
　　# 对每一个字段:
　　ALTER TABLE 这里是表名字 CHANGE 字段名字 重复字段名字 VARCHAR(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
　　# 上面一句或者使用modify来更改
　　ALTER TABLE 这里是表名字 modify 字段名字 VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '';
　　utf8mb4完全向后兼容utf8，无乱码或其他数据丢失的形式出现。理论上是可以放心修改… 还是修改数据库比较方便。

#### 数据库连接
修改项目中的连接数据库的url，将characterEncoding=utf-8去掉，此步骤一定要进行
SpringBoot可以增加：`spring.datasource.hikari.connection-init-sql=set names utf8mb4`

#### 查看三种MySQL字符集的方法

1. 查看MySQL数据库服务器和数据库MySQL字符集。
    mysql> show variables like '%char%';
2. 查看MySQL数据表（table）的MySQL字符集。
    mysql> show table status from sqlstudy_db like '%countries%';
3. 查看MySQL数据列（column）的MySQL字符集。
    mysql> show full columns from countries;

```
-- 修改数据库
ALTER DATABASE iblog_data CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- 修改表
ALTER TABLE student CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 修改字段
ALTER TABLE student CHANGE name VARCHAR(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 查看
SHOW VARIABLES WHERE Variable_name LIKE 'character\_set\_%' OR Variable_name LIKE 'collation%';
```

#### 注意
Navicat等数据库连接工具不支持显示emoji，都是显示的?。这时候可以返回到浏览器中测试






