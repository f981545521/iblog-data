JDK6，JDK7，JDK8：java.security.InvalidKeyException: Illegal key size or default parameters
这个是JDK8的

微信支付解密时，报错的解决方案。


转载CodingSir 最后发布于2018-07-16 09:40:55 阅读数 3816  收藏
我在测试采用  AES  去尝试超过128 位密钥加密的时候会抛出这个错误。发现Java中的  AES  256 算法确实会有这个问题“ Illegal key size or default parameters ”。

然后我尝试去修复，发现有对应的Version （  JDK  ）。在解决问题的同时，我把这几个包都提供了一下，让大家对应去下载。

Jar下载：
可以用下面的地址，或者用文章下的附件地址。

JDK6 解决Jar下载地址：https://pan.baidu.com/s/1eRYPGf4 密码: y9bd

JDK7 解决Jar下载地址： https://pan.baidu.com/s/1gfenrcn 密码: 5n4y

JDK8 解决Jar下载地址：https://pan.baidu.com/s/1cwErKE 密码: 2u6m

Jar替换步骤：
把里面的两个jar包：local_policy.jar  和 US_export_policy.jar  替换掉原来  Jdk  安装目录 $\Java\jre{6|7|8}\lib\security 下的两个jar 包接可以了。

比如我的Java JDK 替换全目录为：C:\Java\jdk1.8.0_66\jre\lib\security 那就把2个Jar Copy覆盖到当前目录即可。