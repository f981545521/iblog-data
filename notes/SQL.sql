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
  `birth` datetime not null default now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '刘备', '34', now());
INSERT INTO `student` VALUES ('2', '曹操', '45', now());
INSERT INTO `student` VALUES ('3', '孙权', '43', now());
INSERT INTO `student` VALUES ('4', '袁绍', '38', now());
INSERT INTO `student` VALUES ('5', '马腾', '58', now());
INSERT INTO `student` VALUES ('6', '关羽', '23', now());
INSERT INTO `student` VALUES ('7', '史蒂夫', '27', now());

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

CREATE TABLE `auditor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '元素名称',
  `description` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `deleted` int(1) NOT NULL DEFAULT '1' COMMENT '是否删除: 0-否 1-是，默认0-否',
  `version` int(1) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认当前时间',
  `last_update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='审计员';


-- ----------------------------
-- MySQL 中的序；列添加表tbl_sequence
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sequence`;
CREATE TABLE tbl_sequence (
                              seq_name VARCHAR(50) NOT NULL COMMENT '序列名称',
                              min_value bigint(20) NOT NULL COMMENT '最小值',
                              max_value bigint(20) NOT NULL COMMENT '最大值',
                              current_val bigint(20) NOT NULL COMMENT '当前值',
                              increment_val INT DEFAULT '1' NOT NULL COMMENT '增长步数',
                              remark VARCHAR(500) DEFAULT null COMMENT '备注',
                              PRIMARY KEY (seq_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sequence表';
-- ----------------------------
-- 添加表单据编号sequence
-- 插入数据前判断，防止数据重复插入
-- ----------------------------
insert into tbl_sequence (seq_name, min_value, max_value, current_val, increment_val,remark)
select 'depot_number_seq', 1, 999999999999999999, 1, 1,'单据编号sequence' from dual where not exists
    (select * from tbl_sequence where seq_name='depot_number_seq');
-- ----------------------------
-- 创建function _nextval() 用于获取当前序列号
-- ----------------------------
DROP FUNCTION IF EXISTS `_nextval`;
DELIMITER ;;
CREATE FUNCTION `_nextval`(name varchar(50)) RETURNS mediumtext CHARSET utf8
begin
    declare _cur bigint;
    declare _maxvalue bigint; -- 接收最大值
    declare _increment int; -- 接收增长步数
    set _increment = (select increment_val from tbl_sequence where seq_name = name);
    set _maxvalue = (select max_value from tbl_sequence where seq_name = name);
    set _cur = (select current_val from tbl_sequence where seq_name = name for update);
    update tbl_sequence -- 更新当前值
    set current_val = _cur + increment_val
    where seq_name = name ;
    if(_cur + _increment >= _maxvalue) then -- 判断是都达到最大值
        update tbl_sequence
        set current_val = minvalue
        where seq_name = name ;
    end if;
    return _cur;
end
;;
DELIMITER ;















####################### 使用Druid监控的时候需要创建以下的表信息  #######################
##### 参考：https://github.com/alibaba/druid/tree/53c3f98bb2be11eae603b71691317d31d45c2dcf/src/main/resources/support/monitor/mysql
-- druid monitor 表开始
create table druid_domain (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX (domain)
);

insert into druid_domain (domain) values ('default');

create table druid_app (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (domain, app)
);

insert into druid_app (domain, app) values ('default', 'default');

create table druid_cluster (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (domain, app, cluster)
);

insert into druid_cluster (domain, app, cluster) values ('default', 'default', 'default');

create table druid_inst (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  app varchar(45) NOT NULL,
  domain varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  host varchar(128) NOT NULL,
  ip varchar(32) NOT NULL,
  lastActiveTime datetime NOT NULL,
  lastPID bigint(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (domain, app, cluster, host)
);

CREATE TABLE druid_webapp (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  host varchar(128),
  pid int(10) NOT NULL,
  collectTime datetime NOT NULL,
  contextPath varchar(256),
  runningCount int(10),
  concurrentMax int(10),
  requestCount bigint(20),
  sessionCount bigint(20),
  jdbcFetchRowCount bigint(20),
  jdbcUpdateCount bigint(20),
  jdbcExecuteCount bigint(20),
  jdbcExecuteTimeNano bigint(20),
  jdbcCommitCount bigint(20),
  jdbcRollbackCount bigint(20),
  osMacOSXCount bigint(20),
  osWindowsCount bigint(20),
  osLinuxCount bigint(20),
  osSymbianCount bigint(20),
  osFreeBSDCount bigint(20),
  osOpenBSDCount bigint(20),
  osAndroidCount bigint(20),
  osWindows98Count bigint(20),
  osWindowsXPCount bigint(20),
  osWindows2000Count bigint(20),
  osWindowsVistaCount bigint(20),
  osWindows7Count bigint(20),
  osWindows8Count bigint(20),
  osAndroid15Count bigint(20),
  osAndroid16Count bigint(20),
  osAndroid20Count bigint(20),
  osAndroid21Count bigint(20),
  osAndroid22Count bigint(20),
  osAndroid23Count bigint(20),
  osAndroid30Count bigint(20),
  osAndroid31Count bigint(20),
  osAndroid32Count bigint(20),
  osAndroid40Count bigint(20),
  osAndroid41Count bigint(20),
  osAndroid42Count bigint(20),
  osAndroid43Count bigint(20),
  osLinuxUbuntuCount bigint(20),
  browserIECount bigint(20),
  browserFirefoxCount bigint(20),
  browserChromeCount bigint(20),
  browserSafariCount bigint(20),
  browserOperaCount bigint(20),
  browserIE5Count bigint(20),
  browserIE6Count bigint(20),
  browserIE7Count bigint(20),
  browserIE8Count bigint(20),
  browserIE9Count bigint(20),
  browserIE10Count bigint(20),
  browser360SECount bigint(20),
  deviceAndroidCount bigint(20),
  deviceIpadCount bigint(20),
  deviceIphoneCount bigint(20),
  deviceWindowsPhoneCount bigint(20),
  botCount bigint(20),
  botBaiduCount bigint(20),
  botYoudaoCount bigint(20),
  botGoogleCount bigint(20),
  botMsnCount bigint(20),
  botBingCount bigint(20),
  botSosoCount bigint(20),
  botSogouCount bigint(20),
  botYahooCount bigint(20),
  PRIMARY KEY (id)
);

CREATE INDEX druid_webapp_index ON druid_webapp (collectTime, domain, app);



CREATE TABLE druid_sql (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  host varchar(128),
  pid int(10) NOT NULL,
  collectTime datetime NOT NULL,
  sqlHash bigint(20),
  dataSource varchar(256),
  lastStartTime bigint(20),
  batchTotal bigint(20),
  batchToMax int(10),
  execSuccessCount bigint(20),
  execNanoTotal bigint(20),
  execNanoMax bigint(20),
  running int(10),
  concurrentMax int(10),
  rsHoldTime bigint(20),
  execRsHoldTime bigint(20),
  name varchar(256),
  file varchar(256),
  dbType varchar(256),
  execNanoMaxOccurTime bigint(20),
  errorCount bigint(20),
  errorLastMsg varchar(256),
  errorLastClass varchar(256),
  errorLastStackTrace varchar(6383),
  errorLastTime bigint(20),
  updateCount bigint(20),
  updateCountMax bigint(20),
  fetchRowCount bigint(20),
  fetchRowCountMax bigint(20),
  inTxnCount bigint(20),
  lastSlowParameters varchar(6383),
  clobOpenCount bigint(20),
  blobOpenCount bigint(20),
  readStringLength bigint(20),
  readBytesLength bigint(20),
  inputStreamOpenCount bigint(20),
  readerOpenCount bigint(20),
  h1 bigint(20),
  h10 bigint(20),
  h100 int(10),
  h1000 int(10),
  h10000 int(10),
  h100000 int(10),
  h1000000 int(10),
  hmore int(10),
  eh1 bigint(20),
  eh10 bigint(20),
  eh100 int(10),
  eh1000 int(10),
  eh10000 int(10),
  eh100000 int(10),
  eh1000000 int(10),
  ehmore int(10),
  f1 bigint(20),
  f10 bigint(20),
  f100 bigint(20),
  f1000 int(10),
  f10000 int(10),
  fmore int(10),
  u1 bigint(20),
  u10 bigint(20),
  u100 bigint(20),
  u1000 int(10),
  u10000 int(10),
  umore int(10),
  PRIMARY KEY (id)
);

CREATE INDEX druid_sql_index ON druid_sql (collectTime, domain, app);

CREATE TABLE druid_datasource (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  host varchar(128),
  pid int(10) NOT NULL,
  collectTime datetime NOT NULL,
  name varchar(256),
  dbType varchar(256),
  driverClassName varchar(256),
  activeCount int(10),
  activePeak int(10),
  activePeakTime bigint(20),
  poolingCount int(10),
  poolingPeak int(10),
  poolingPeakTime bigint(20),
  connectCount bigint(20),
  closeCount bigint(20),
  waitThreadCount bigint(20),
  notEmptyWaitCount bigint(20),
  notEmptyWaitNanos bigint(20),
  logicConnectErrorCount bigint(20),
  physicalConnectCount bigint(20),
  physicalCloseCount bigint(20),
  physicalConnectErrorCount bigint(20),
  executeCount bigint(20),
  errorCount bigint(20),
  commitCount bigint(20),
  rollbackCount bigint(20),
  pstmtCacheHitCount bigint(20),
  pstmtCacheMissCount bigint(20),
  startTransactionCount bigint(20),
  keepAliveCheckCount bigint(20),
  txn_0_1 bigint(20),
  txn_1_10 bigint(20),
  txn_10_100 bigint(20),
  txn_100_1000 bigint(20),
  txn_1000_10000 bigint(20),
  txn_10000_100000 bigint(20),
  txn_more bigint(20),
  clobOpenCount bigint(20),
  blobOpenCount bigint(20),
  sqlSkipCount bigint(20),
  PRIMARY KEY (id)
);

CREATE INDEX druid_datasource_index ON druid_datasource (collectTime, domain, app);

CREATE TABLE druid_weburi (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  host varchar(128),
  pid int(10) NOT NULL,
  collectTime datetime NOT NULL,
  uri varchar(256),
  runningCount int(10),
  concurrentMax int(10),
  requestCount bigint(20),
  requestTimeNano bigint(20),
  requestTimeNanoMax bigint(20),
  jdbcFetchRowCount bigint(20),
  jdbcFetchRowPeak bigint(20),
  jdbcUpdateCount bigint(20),
  jdbcUpdatePeak bigint(20),
  jdbcExecuteCount bigint(20),
  jdbcExecuteErrorCount bigint(20),
  jdbcExecutePeak bigint(20),
  jdbcExecuteTimeNano bigint(20),
  jdbcCommitCount bigint(20),
  jdbcRollbackCount bigint(20),
  jdbcPoolConnectionOpenCount bigint(20),
  jdbcPoolConnectionCloseCount bigint(20),
  jdbcResultSetOpenCount bigint(20),
  jdbcResultSetCloseCount bigint(20),
  errorCount bigint(20),
  lastAccessTime datetime,
  h1 bigint(20),
  h10 bigint(20),
  h100 bigint(20),
  h1000 bigint(20),
  h10000 int(10),
  h100000 int(10),
  h1000000 int(10),
  hmore int(10),
  requestTimeNanoMaxOccurTime datetime,
  PRIMARY KEY (id)
);

CREATE INDEX druid_weburi_index ON druid_weburi (collectTime, domain, app);

CREATE TABLE druid_const (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  type varchar(45) NOT NULL,
  hash bigint(20) NOT NULL,
  value text,
  PRIMARY KEY (id),
  UNIQUE (domain, app, type, hash)
)


CREATE TABLE druid_springmethod (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  domain varchar(45) NOT NULL,
  app varchar(45) NOT NULL,
  cluster varchar(45) NOT NULL,
  host varchar(128),
  pid int(10) NOT NULL,
  collectTime datetime NOT NULL,
  className varchar(256),
  signature varchar(256),
  runningCount int(10),
  concurrentMax int(10),
  executeCount bigint(20),
  executeErrorCount bigint(20),
  executeTimeNano bigint(20),
  jdbcFetchRowCount bigint(20),
  jdbcUpdateCount bigint(20),
  jdbcExecuteCount bigint(20),
  jdbcExecuteErrorCount bigint(20),
  jdbcExecuteTimeNano bigint(20),
  jdbcCommitCount bigint(20),
  jdbcRollbackCount bigint(20),
  jdbcPoolConnectionOpenCount bigint(20),
  jdbcPoolConnectionCloseCount bigint(20),
  jdbcResultSetOpenCount bigint(20),
  jdbcResultSetCloseCount bigint(20),
  lastErrorClass varchar(256),
  lastErrorMessage varchar(256),
  lastErrorStackTrace varchar(256),
  lastErrorTimeMillis bigint(20),
  h1 bigint(20),
  h10 bigint(20),
  h100 bigint(20),
  h1000 bigint(20),
  h10000 int(10),
  h100000 int(10),
  h1000000 int(10),
  hmore int(10),
  PRIMARY KEY (id)
);

CREATE INDEX druid_springmethod_index ON druid_springmethod (collectTime, domain, app);


CREATE TABLE druid_wall(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  domain varchar(45)  NOT NULL,
  app varchar(45)  NOT NULL,
  cluster varchar(45)  NOT NULL,
  host varchar(128),
  pid int(10)  NOT NULL,
  collectTime datetime NOT NULL,
  name varchar(256),
  checkCount bigint(20),
  hardCheckCount bigint(20),
  violationCount bigint(20),
  whiteListHitCount bigint(20),
  blackListHitCount bigint(20),
  syntaxErrorCount bigint(20),
  violationEffectRowCount bigint(20),
  PRIMARY KEY(id)
);
CREATE INDEX druid_wall_index ON druid_wall (collectTime, domain, app);

CREATE TABLE druid_wall_sql(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  domain varchar(45)  NOT NULL,
  app varchar(45)  NOT NULL,
  cluster varchar(45)  NOT NULL,
  host varchar(128), pid int(10)  NOT NULL,
  collectTime datetime NOT NULL,
  sqlHash bigint(20),
  sqlSHash bigint(20),
  sqlSampleHash bigint(20),
  executeCount bigint(20),
  fetchRowCount bigint(20),
  updateCount bigint(20),
  syntaxError int(1),
  violationMessage varchar(256),
  PRIMARY KEY(id)
);
CREATE INDEX druid_wall_sql_index ON druid_wall_sql (collectTime, domain, app);

CREATE TABLE druid_wall_table(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  domain varchar(45)  NOT NULL,
  app varchar(45)  NOT NULL,
  cluster varchar(45)  NOT NULL,
  host varchar(128),
  pid int(10)  NOT NULL,
  collectTime datetime NOT NULL,
  name varchar(256),
  selectCount bigint(20),
  selectIntoCount bigint(20),
  insertCount bigint(20),
  updateCount bigint(20),
  deleteCount bigint(20),
  truncateCount bigint(20),
  createCount bigint(20),
  alterCount bigint(20),
  dropCount bigint(20),
  replaceCount bigint(20),
  deleteDataCount bigint(20),
  updateDataCount bigint(20),
  insertDataCount bigint(20),
  fetchRowCount bigint(20),
  PRIMARY KEY(id)
);
CREATE INDEX druid_wall_table_index ON druid_wall_table (collectTime, domain, app);

CREATE TABLE druid_wall_function(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  domain varchar(45)  NOT NULL,
  app varchar(45)  NOT NULL,
  cluster varchar(45)  NOT NULL,
  host varchar(128),
  pid int(10)  NOT NULL,
  collectTime datetime NOT NULL,
  name varchar(256),
  invokeCount bigint(20),
  PRIMARY KEY(id)
);
CREATE INDEX druid_wall_function_index ON druid_wall_function (collectTime, domain, app);
####################### 使用Druid监控的时候需要创建以上的表信息  #######################



