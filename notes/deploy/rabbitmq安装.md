### 安装

安装之前需要安装erlang
#### 下载地址
http://www.erlang.org/downloads

https://www.rabbitmq.com/download.html


在RabbitMQ官网的下载页面 https://www.rabbitmq.com/download.html 中，我们可以获取到针对各种不同操作系统的安装包和说明文档。这里，我们将对 windows 平台进行说明。

下面我们采用的Erlang和RabbitMQ Server版本说明：

- Erlang/OTP 19.3
- RabbitMQ Server 3.6.10
#### 开始

1. 安装Erland，通过官方下载页面http://www.erlang.org/downloads获取exe安装包，直接打开并完成安装。
2. 安装RabbitMQ，通过官方下载页面https://www.rabbitmq.com/download.html获取exe安装包。 下载完成后，直接运行安装程序。
3. RabbitMQ Server安装完成之后，会自动的注册为服务，并以默认配置启动起来。注意如果安装360等软件要放行。


### Rabbit管理

我们可以直接通过配置文件的访问进行管理，也可以通过Web的访问进行管理。下面我们将介绍如何通过Web进行管理。

在 RabbitMQ的安装目录的 sbin 下，执行 rabbitmq-plugins enable rabbitmq_management 命令，开启Web管理插件，这样我们就可以通过浏览器来进行管理了。
`PS D:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.7\sbin> .\rabbitmq-plugins.bat enable rabbitmq_management`

访问：http://localhost:15672/，并使用默认用户 guest 登录，密码也为 guest 。

添加用户`admin`，密码`admin123`，赋予权限

### 延时队列

1. 下载地址：
https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/tag/v3.8.0
2. 安装插件：
`PS D:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.7\sbin> .\rabbitmq-plugins.bat enable rabbitmq_delayed_message_exchange`
3. 最大延迟时间（毫秒）：
Integer.MAX_VALUE：2147483647
- 2147483.647	    秒(s)
- 35791.3941882	    分(min)
- 596.5232358	    时(h)
- 3.5507332	        周(week)
- 24.8551346	    天(d)
- 0.0680963	        年(yr)
- 2.1475e+15	    纳秒(ns)

> 参考： https://www.cnblogs.com/mfrank/p/11260355.html#autoid-0-4-0