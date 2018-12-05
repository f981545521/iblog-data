### 下载
$ wget http://download.redis.io/releases/redis-5.0.2.tar.gz
$ tar xzf redis-5.0.2.tar.gz
$ cd redis-5.0.2
$ make
$ src/redis-server
$ src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
### 集群安装
[root@acyou src]# ./redis-cli --cluster create 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006 --cluster-replicas 1
参考：https://www.cnblogs.com/zwcry/p/9174233.html
1.redis安装
　　安装教程：https://www.cnblogs.com/zwcry/p/9505949.html
2.安装Redis依赖（ruby）
　　1）安装依赖包
　　　　yum install -y gcc-c++ patch readline readline-devel zlib zlib-devel libyaml-devel libffi-devel openssl-devel make bzip2 autoconf automake libtool bison sqlite-devel iconv-devel
　　2）安装 rvm
　　　　curl -L get.rvm.io | bash -s stable
　　　　错误：
　　　　
　　　　他让你尝试 gpg2 --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3
　　　　执行：
　　　　gpg2 --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3
　　　　或
　　　　gpg2 --keyserver hkp://keys.gnupg.net --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3
　　3）再次安装rvm
　　　　curl -L get.rvm.io | bash -s stable 
　　　　
　　　　安装成功，提示请把用户添加到rvm用户组
　　　　usermod -G  rvm root
　　4）添加生效指令
　　　　echo "source /etc/profile.d/rvm.sh" >> ~/.bashrc && source /etc/profile.d/rvm.sh
　　5）安装ruby
　　　　rvm install ruby
　　6)安装ruby与redis接口
　　　　gem install redis
　　　　yum install -y rubygems
3.配置六个redis服务（官方建议3主3从） 
　　1）创建集群目录
　　　　mkdir /usr/local/redis-cluster/
　　　　cd  /usr/local/redis-cluster/
　　　　mkdir 7001
　　　　mkdir 7002
　　　　mkdir 7003
　　　　mkdir 7004
　　　　mkdir 7005
　　　　mkdir 7006
　　2）创建集群第一个redis.conf
　　　　cd  /usr/local/redis-cluster/
　　　　cp /usr/local/redis/redis.conf 7001
　　　　vim ./7001/redis.conf
　　　　#找到如下KEY，放开注释，修改值内容
　　　　bind 0.0.0.0#不限制访问ip和远程连接
　　　　protected-mode no#保护模式
　　　　port 7001 #修改端口号　
　　　　daemonize yes#后台运行线程
　　　　pidfile /var/run/redis_7001.pid #守护进程文件
　　　　dbfilename dump7001.rdb#缓存持久存储文件名称
　　　　dir /usr/local/redis-cluster/7001/#db文件存放路径
　　　　appendonly yes#开启日志实时持久
　　　　appendfilename "appendonly7001.aof"#日志名称
　　　　cluster-enabled yes#开启集群
　　　　cluster-config-file nodes_7001.conf#集群节点文件
　　　　cluster-node-timeout 15000#集群通信超过该时长视为挂掉，单位毫秒
　　3）拷贝7001/redis.conf到7002~7006
　　　　cd  /usr/local/redis-cluster/
　　　　cp ./7001/redis.conf ./7002/
　　　　cp ./7001/redis.conf ./7003/
　　　　cp ./7001/redis.conf ./7004/
　　　　cp ./7001/redis.conf ./7005/
　　　　cp ./7001/redis.conf ./7006/
　　4）修改7002~7006/redis.conf
　　　　#将7002~7006/redis.conf里的7001信息替换为7002~7006的正确信息
　　　　cd  /usr/local/redis-cluster/
　　　　vim ./7002/redis.conf
　　　　:%s/7001/7002/g#所有7001的字符串替换为7002
　　　　vim ./7003/redis.conf
　　　　:%s/7001/7003/g#所有7001的字符串替换为7003
　　　　vim ./7004/redis.conf
　　　　:%s/7001/7004/g#所有7001的字符串替换为7004
　　　　vim ./7005/redis.conf
　　　　:%s/7001/7005/g#所有7001的字符串替换为7005
　　　　vim ./7006/redis.conf
　　　　:%s/7001/7006/g#所有7001的字符串替换为7006
　　5）启动6个服务
　　　　cd  /usr/local/redis-cluster/
　　　　/usr/local/redis/src/redis-server ./7001/redis.conf
　　　　/usr/local/redis/src/redis-server ./7001/redis.conf
　　　　/usr/local/redis/src/redis-server ./7002/redis.conf
　　　　/usr/local/redis/src/redis-server ./7003/redis.conf
　　　　/usr/local/redis/src/redis-server ./7004/redis.conf
　　　　/usr/local/redis/src/redis-server ./7005/redis.conf
　　　　/usr/local/redis/src/redis-server ./7006/redis.conf
　　　　ps -ef|grep redis
　　　　
4.创建与测试
　　1）创建集群
　　　　/usr/local/redis/src/redis-trib.rb create --replicas 1 192.168.159.129:7001 192.168.159.129:7002 192.168.159.129:7003 192.168.159.129:7004 192.168.159.129:7005 192.168.159.129:7006　
　　　　如果出现
　　　　/usr/local/rvm/rubies/ruby-2.3.4/lib/ruby/site_ruby/2.3.0/rubygems/core_ext/kernel_require.rb:55:in `require': cannot load such file -- redis (LoadError)
　　　　from /usr/local/rvm/rubies/ruby-2.3.4/lib/ruby/site_ruby/2.3.0/rubygems/core_ext/kernel_require.rb:55:in `require'
　　　　from /usr/local/redis/src/redis-trib.rb:25:in `<main>'
　　　　
　　　　  1.确保redis安装编译成功，否则重装
　　　　  2.gem install redis
　　　　  然后再执行　　/usr/local/redis/src/redis-trib.rb create --replicas 1 192.168.159.129:7001 192.168.159.129:7002 192.168.159.129:7003 192.168.159.129:7004 192.168.159.129:7005 192.168.159.129:7006
　　　　成功如图：
　　　　
　　2）测试
　　　　/usr/local/redis/src/redis-cli -c -p 7001 --raw#-c表示连集群 --raw中文显示
　　　　set name '丁洁'
　　　　get name
　　　　
5.开机启动
　　mkdir /usr/local/redis-cluster/script
　　cd /usr/local/redis-cluster/script/
　　1）编写 start.sh
　　　　vim start.sh
复制代码
#!/bin/sh
/usr/local/redis/src/redis-server /usr/local/redis-cluster/7001/redis.conf
/usr/local/redis/src/redis-server /usr/local/redis-cluster/7002/redis.conf
/usr/local/redis/src/redis-server /usr/local/redis-cluster/7003/redis.conf
/usr/local/redis/src/redis-server /usr/local/redis-cluster/7004/redis.conf
/usr/local/redis/src/redis-server /usr/local/redis-cluster/7005/redis.conf
/usr/local/redis/src/redis-server /usr/local/redis-cluster/7006/redis.conf
复制代码
　　2）编写 stop.sh
　　　　vim stop.sh
复制代码
#!/bin/sh
/usr/local/redis/src/redis-cli -c -p 7001 shutdown
/usr/local/redis/src/redis-cli -c -p 7002 shutdown
/usr/local/redis/src/redis-cli -c -p 7003 shutdown
/usr/local/redis/src/redis-cli -c -p 7004 shutdown
/usr/local/redis/src/redis-cli -c -p 7005 shutdown
/usr/local/redis/src/redis-cli -c -p 7006 shutdown
复制代码
　　3）编写 restart.sh
　　　　vim restart.sh
#!/bin/sh
systemctl stop redis-cluster
systemctl start redis-cluster
　　4）改变权限
　　　　chmod 777 ./*
　　　　
　　5）编写自启服务
　　　　cd /usr/lib/systemd/system/
　　　　vim redis-cluster.service
　　　　添加内容如下
复制代码
[Unit]
Description=redis-cluster
After=network.target remote-fs.target nss-lookup.target
[Service]
Type=forking
ExecStart=/usr/local/redis-cluster/script/start.sh
ExecStop=/usr/local/redis-cluster/script/stop.sh
ExecReload=/usr/local/redis-cluster/script/restart.sh
[Install]
WantedBy=multi-user.target
复制代码
　　　　改变权限
　　　　　　chmod 777 redis-cluster.service
　　　　进程服务重加载
　　　　　　systemctl daemon-reload
　　　　开机启动集群
　　　　　　systemctl enable redis-cluster.service
　　　　启动集群
　　　　　　systemctl start redis-cluster.service
　　　　关闭集群
　　　　　　systemctl stop redis-cluster.service
　　　　重启集群
　　　　　　systemctl restart redis-cluster.service
127.0.0.1
