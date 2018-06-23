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


##### 使用Druid监控的时候需要创建以下的表信息  #####
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
  errorLastStackTrace varchar(256),
  errorLastTime bigint(20),
  updateCount bigint(20),
  updateCountMax bigint(20),
  fetchRowCount bigint(20),
  fetchRowCountMax bigint(20),
  inTxnCount bigint(20),
  lastSlowParameters varchar(256),
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
-- druid monitor 表结束



