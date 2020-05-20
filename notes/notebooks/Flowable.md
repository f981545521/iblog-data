### Flowable 专栏

参考地址:https://blog.csdn.net/whatlookingfor/category_6454621.html


#### 七大service接口
- RepositoryService

Activiti 中每一个不同版本的业务流程的定义都需要使用一些定义文件，部署文件和支持数据 ( 例如 BPMN2.0 XML 文件，表单定义文件，流程定义图像文件等 )，这些文件都存储在 Activiti 内建的 Repository 中。Repository Service 提供了对 repository 的存取服务,流程仓库service，用于管理流程仓库，例如部署，删除，读取流程资源

- RunTimeService

在Activiti中，每个流程定义被启动一次之后，都会生成相应的流程对象实例。RunTimeService提供启动流程，查询流程实例，设置获取流程实例变量等功能。此外还提供对流程部署，流程定义和流程实例存取的服务。

- TaskService

在activiti业务流程定义的每一个执行节点被称为一个task，对流程中的数据存取，状态变更等操作都需要在task中完成。TaskService提供了对用户task和form的相关操作。提供了运行时任务的查询、领取、完成、删除以及变量设置等功能。

- IdentityService

Activiti内置了用户以及用户组的概念以及功能，必须使用用户或者用户组才能获取到相应的task。IdentityService提供了对用户和用户组的管理功能。

- HistoryService

主要用于获取正在运行或者已经运行结束的流程实例信息，与RunTimeService获取的流程信息不同，历史信息包含已经持久储存化的信息，并已经针对查询做出优化。

- FormService

Activiti中的流程和状态Task均可关联相关的业务数据，通过FormService可以存取启动和完成任务所需的表单数据并根据需要来渲染表单。

- ManagementService

ManagementService提供对流程引擎的管理和维护的功能，这些功能不在工作流驱动的应用程序中使用，主要运用activiti的日常维护。

### 相关表
Flowable 使用 Mybatis3 做持久化工作,可以在配置中设置流程引擎启动时创建表。
- Activiti 使用到的表都是 ACT_开头的。
- ACT_RE_*:流程定义存储。
- ACT_RU_*:流程执行记录,记录流程启动到结束的所有动作,流程结束后会清除相关记录。
- ACT_ID_*:用户记录,流程中使用到的用户和组。
- ACT_HI_*:流程执行的历史记录。

使用到的表:

- ACT_GE_BYTEARRAY:流程部署的数据。二进制数据表
- ACT_GE_PROPERTY:通用设置。 属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录
- ACT_HI_ACTINST:流程活动的实例，历史节点表
- ACT_HI_ATTACHMENT: 历史附件表
- ACT_HI_COMMENT:历史意见表
- ACT_HI_DETAIL: 历史详情表，提供历史变量的查询
- ACT_HI_PROCINST:历史流程实例。
- ACT_HI_TASKINST:历史任务实例。
- ACT_ID_GROUP:用户组。
- ACT_ID_INFO:用户扩展信息表
- ACT_ID_MEMBERSHIP: 用户组与用户对应信息表
- ACT_ID_USER:用户。
- ACT_RE_DEPLOYMENT:部署记录。
- ACT_RE_PROCDEF:流程定义数据表。
- ACT_RU_EXECUTION:流程执行记录。
- ACT_RU_IDENTITYLINK:运行时的流程人员表，主要储存任务节点与参与者的相关信息
- ACT_RU_JOB: 定时任务数据表
- ACT_RU_TASK:执行的任务节点记录。
- ACT_RU_VARIABLE:执行中的变量记录。


##### 工作流表删除顺序
1. 删除Flowable表
drop table act_evt_log; 
drop table act_hi_actinst; 
drop table act_hi_attachment; 
drop table act_hi_comment; 
drop table act_hi_detail; 
drop table act_hi_identitylink; 
drop table act_hi_procinst; 
drop table act_hi_taskinst; 
drop table act_hi_varinst; 
drop table act_id_info; 
drop table act_id_membership; 
drop table act_id_user; 
drop table act_re_model; 
drop table act_ru_event_subscr; 
drop table act_ru_identitylink; 
drop table ACT_RU_DEADLETTER_JOB; 
drop table ACT_RU_SUSPENDED_JOB; 
drop table ACT_RU_TIMER_JOB; 
drop table act_ru_job; 
drop table act_ru_task; 
drop table act_id_group; 
drop table act_ru_variable; 
drop table act_ru_execution; 
drop table ACT_GE_PROPERTY; 
drop table ACT_PROCDEF_INFO; 
drop table act_ge_bytearray; 
drop table act_re_deployment; 
drop table ACT_RE_PROCDEF;
drop table act_hi_entitylink;
drop table act_hi_tsk_log;
drop table act_id_bytearray;
drop table act_id_priv_mapping;
drop table act_id_property;
drop table act_id_token;
drop table act_ru_actinst;
drop table act_ru_entitylink;
drop table act_ru_history_job;
drop table act_id_priv;
# form相关的表
drop table if EXISTS act_fo_databasechangelog;
drop table if EXISTS act_fo_databasechangeloglock;
drop table if EXISTS act_fo_form_definition;
drop table if EXISTS act_fo_form_deployment;
drop table if EXISTS act_fo_form_instance;
drop table if EXISTS act_fo_form_resource;
-- DROP SEQUENCE  ACT_EVT_LOG_SEQ;
1. 清空Flowable表
set foreign_key_checks=0;
truncate table act_evt_log; 
truncate table act_hi_actinst; 
truncate table act_hi_attachment; 
truncate table act_hi_comment; 
truncate table act_hi_detail; 
truncate table act_hi_identitylink; 
truncate table act_hi_procinst; 
truncate table act_hi_taskinst; 
truncate table act_hi_varinst; 
truncate table act_id_info; 
truncate table act_id_membership; 
truncate table act_id_user; 
truncate table act_re_model; 
truncate table act_ru_event_subscr; 
truncate table act_ru_identitylink; 
truncate table ACT_RU_DEADLETTER_JOB; 
truncate table ACT_RU_SUSPENDED_JOB; 
truncate table ACT_RU_TIMER_JOB; 
truncate table act_ru_job; 
truncate table act_ru_task; 
truncate table act_id_group; 
truncate table act_ru_variable; 
truncate table act_ru_execution; 
-- 自带的truncate table ACT_GE_PROPERTY; 
truncate table ACT_PROCDEF_INFO; 
truncate table act_ge_bytearray; 
truncate table act_re_deployment; 
truncate table ACT_RE_PROCDEF;
truncate table act_hi_entitylink;
truncate table act_hi_tsk_log;
truncate table act_id_bytearray;
truncate table act_id_priv_mapping;
-- 自带的truncate table act_id_property;
truncate table act_id_token;
truncate table act_ru_actinst;
truncate table act_ru_entitylink;
truncate table act_ru_history_job;
truncate table act_id_priv;

### 表单Support
1. Maven中增加
        <!-- Form Engine Support -->
        <dependency>
            <groupId>org.flowable</groupId>
            <artifactId>flowable-form-spring-configurator</artifactId>
            <version>6.4.1</version>
        </dependency>
2. application.properties
```
    # 是否启用Form引擎。
    flowable.form.enabled=true
    # Form资源的路径。
    flowable.form.resource-location=classpath*:/forms/
    # 是否部署资源。默认为'true'。
    #flowable.form.deploy-resources=true
    # Form资源部署的名字。
    #flowable.form.deployment-name=SpringBootAutoDeployment
    # 需要扫描的资源后缀名。
    flowable.form.resource-suffixes=*.form
    # 启动时加载Form servlet。
    #flowable.form.servlet.load-on-startup=-1
    # Form servlet的名字。
    #flowable.form.servlet.name=Flowable Form Rest API
    # Form servlet的context path。
    #flowable.form.servlet.path=/form-api
```

#### 参考文档
Flowable 演示 Demo, 用来熟悉 Flowable 工作流：https://gitee.com/foolish93/flowable_demo

JSite 快速开发框架，内置Flowable工作流引擎 · 五大基础模块 · 前后端基础代码自动生成 · 权限精确控制：https://gitee.com/baseweb/JSite

https://gitee.com/quickd/quickd
