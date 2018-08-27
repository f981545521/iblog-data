### Mybatis缓存机制

mybatis提供了缓存机制减轻数据库压力，提高数据库性能

mybatis的缓存分为两级：一级缓存、二级缓存

一级缓存是SqlSession级别的缓存，缓存的数据只在SqlSession内有效

二级缓存是mapper级别的缓存，同一个namespace公用这一个缓存，所以对SqlSession是共享的
#### 简述

mybatis默认开启一级缓存，支持在同一个会话（sqlsession）同一个statement执行两次，则第二次会默认会使用第一次创建的缓存对象。

#### 一级缓存
mybatis的一级缓存是SqlSession级别的缓存，在操作数据库的时候需要先创建SqlSession会话对象，在对象中有一个HashMap用于存储缓存数据，
此HashMap是当前会话对象私有的，别的SqlSession会话对象无法访问。
核心是`PerpetualCache`类

##### 具体流程：

1.第一次执行select完毕会将查到的数据写入SqlSession内的HashMap中缓存起来

2.第二次执行select会从缓存中查数据，如果select相同切传参数一样，那么就能从缓存中返回数据，不用去数据库了，从而提高了效率

##### 注意事项：

1.如果SqlSession执行了DML操作（insert、update、delete），并commit了，那么mybatis就会清空当前SqlSession缓存中的所有缓存数据，这样可以保证缓存中的存的数据永远和数据库中一致，避免出现脏读

2.当一个SqlSession结束后那么他里面的一级缓存也就不存在了，mybatis默认是开启一级缓存，不需要配置

3.mybatis的缓存是基于[namespace:sql语句:参数]来进行缓存的，意思就是，SqlSession的HashMap存储缓存数据时，是使用[namespace:sql:参数]作为key，查询返回的语句作为value保存的。例如：-1242243203:1146242777:winclpt.bean.userMapper.getUser:0:2147483647:select * from user where id=?:19

```
    2018-08-27 22:01:39.732 DEBUG 6344 --- [nio-8033-exec-1] o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@1af149f1] to manual commit
    2018-08-27 22:01:39.740  INFO 6344 --- [nio-8033-exec-1] c.a.i.service.impl.StudentServiceImpl    : 第一次查询
    2018-08-27 22:01:39.743 DEBUG 6344 --- [nio-8033-exec-1] org.mybatis.spring.SqlSessionUtils       : Creating a new SqlSession
    2018-08-27 22:01:39.754 DEBUG 6344 --- [nio-8033-exec-1] org.mybatis.spring.SqlSessionUtils       : Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@319fae52]
    Mon Aug 27 22:01:39 CST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
    2018-08-27 22:01:39.807 DEBUG 6344 --- [nio-8033-exec-1] o.m.s.t.SpringManagedTransaction         : JDBC Connection [com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@1af149f1] will be managed by Spring
    2018-08-27 22:01:39.816 DEBUG 6344 --- [nio-8033-exec-1] c.a.i.d.S.selectByPrimaryKey             : ==>  Preparing: SELECT id,name,age,birth FROM student WHERE id = ? 
    2018-08-27 22:01:39.838 DEBUG 6344 --- [nio-8033-exec-1] c.a.i.d.S.selectByPrimaryKey             : ==> Parameters: 2(String)
    2018-08-27 22:01:39.864 DEBUG 6344 --- [nio-8033-exec-1] c.a.i.d.S.selectByPrimaryKey             : <==      Total: 1
    耗时：104 ms - id:cn.acyou.iblogdata.dao.StudentMapper.selectByPrimaryKey
    <—————————————————————————SQL——————————————————————————>
    SELECT id,name,age,birth FROM student WHERE id = '2'
    <——————————————————————————————————————————————————————>
    2018-08-27 22:01:39.865 DEBUG 6344 --- [nio-8033-exec-1] org.mybatis.spring.SqlSessionUtils       : Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@319fae52]
    2018-08-27 22:01:39.865  INFO 6344 --- [nio-8033-exec-1] c.a.i.service.impl.StudentServiceImpl    : 第二次查询
    2018-08-27 22:01:39.865 DEBUG 6344 --- [nio-8033-exec-1] org.mybatis.spring.SqlSessionUtils       : Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@319fae52] from current transaction
    耗时：1 ms - id:cn.acyou.iblogdata.dao.StudentMapper.selectByPrimaryKey
    <—————————————————————————SQL——————————————————————————>
    SELECT id,name,age,birth FROM student WHERE id = '2'
    <——————————————————————————————————————————————————————>
    2018-08-27 22:01:39.866 DEBUG 6344 --- [nio-8033-exec-1] org.mybatis.spring.SqlSessionUtils       : Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@319fae52]

```

> 可以看到只有第一次查询时访问了数据库，第二次查询直接从缓存读取数据。

#### 二级缓存：

二级缓存是mapper级别的缓存，也就是同一个namespace的mapper.xml，当多个SqlSession使用同一个Mapper操作数据库的时候，得到的数据会缓存在同一个二级缓存区域

二级缓存默认是没有开启的。需要在setting全局参数中配置开启二级缓存

##### 如何使用
1. spring : mybatis-config.xml
```
    <settings>
            <setting name="cacheEnabled" value="true"/>默认是false：关闭二级缓存
    <settings>
```

2. springboot : application.properties（好像用不了）
```
    mybatis.configuration.cache-enabled=true
```

3. xxxMapper.xml
```
    <cache eviction="LRU" flushInterval="60000" size="512" readOnly="true"/>
```

> 当前mapper下所有语句开启二级缓存，这里配置了一个LRU缓存，并每隔60秒刷新，最大存储512个对象，而却返回的对象是只读的


 - LRU – 最近最少使用的:移除最长时间不被使用的对象。

 - FIFO – 先进先出:按对象进入缓存的顺序来移除它们。

 - SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。

 - WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。

4. 若想禁用当前select语句的二级缓存，添加useCache="false"修改如下：
```
    <select id="getCountByName" parameterType="java.util.Map" resultType="INTEGER" statementType="CALLABLE" useCache="false">
```

##### 具体流程：

1.当一个sqlseesion执行了一次select后，在关闭此session的时候，会将查询结果缓存到二级缓存

2.当另一个sqlsession执行select时，首先会在他自己的一级缓存中找，如果没找到，就回去二级缓存中找，找到了就返回，就不用去数据库了，从而减少了数据库压力提高了性能　

##### 注意事项：

1.如果SqlSession执行了DML操作（insert、update、delete），并commit了，那么mybatis就会清空当前mapper缓存中的所有缓存数据，这样可以保证缓存中的存的数据永远和数据库中一致，避免出现脏读

2.mybatis的缓存是基于[namespace:sql语句:参数]来进行缓存的，意思就是，SqlSession的HashMap存储缓存数据时，是使用[namespace:sql:参数]作为key，查询返回的语句作为value保存的。例如：-1242243203:1146242777:winclpt.bean.userMapper.getUser:0:2147483647:select * from user where id=?:19




