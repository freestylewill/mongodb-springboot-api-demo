# mongoDB集群搭建

## 一、linux操作

```shell
#创建mongo服务镜像
docker run -d --name mongo-centos -p 27017:27017 --privileged=true centos:centos7 /usr/sbin/init

#进入docker
docker exec -it mongo-centos bash

#更换yum镜像源
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
docker cp /Users/xiangxz/Downloads/mysql-plugin/CentOS7-Base-163.repo  mongo-centos:/etc/yum.repos.d/CentOS7-Base-163.repo
yum clean all
yum makecache

#安装vim编辑器
yum install vim -y

#上传mongoDB安装包
docker cp /Users/xiangxz/Downloads/mongodb-linux-x86_64-rhel70-4.2.8.tgz  mongo-centos:/home/

#解压安装包
tar -xvf mongodb-linux-x86_64-rhel70-4.2.8.tgz

#创建数据库目录

#分片一
mkdir -p /data/mongo/shard/shard/shard1/shard1-37011
mkdir -p /data/mongo/shard/shard/shard1/shard1-37013
mkdir -p /data/mongo/shard/shard/shard1/shard1-37015
mkdir -p /data/mongo/shard/shard/shard1/shard1-37017

#分片二
mkdir -p /data/mongo/shard/shard/shard2/shard2-47011
mkdir -p /data/mongo/shard/shard/shard2/shard2-47013
mkdir -p /data/mongo/shard/shard/shard2/shard2-47015
mkdir -p /data/mongo/shard/shard/shard2/shard2-47017

#分片三
mkdir -p /data/mongo/shard/shard/shard3/shard3-57011
mkdir -p /data/mongo/shard/shard/shard3/shard3-57013
mkdir -p /data/mongo/shard/shard/shard3/shard3-57015
mkdir -p /data/mongo/shard/shard/shard3/shard3-57017

#分片四
mkdir -p /data/mongo/shard/shard/shard4/shard4-58011
mkdir -p /data/mongo/shard/shard/shard4/shard4-58013
mkdir -p /data/mongo/shard/shard/shard4/shard4-58015
mkdir -p /data/mongo/shard/shard/shard4/shard4-58017

#配置节点
mkdir -p /data/mongo/config-server/config-17011
mkdir -p /data/mongo/config-server/config-17013
mkdir -p /data/mongo/config-server/config-17015

#路由节点
mkdir -p /data/mongo/router-27017

#创建日志文件目录

#分片一
mkdir -p /logs/shard/shard1/

#分片二
mkdir -p /logs/shard/shard2/

#分片三
mkdir -p /logs/shard/shard3/

#分片四
mkdir -p /logs/shard/shard4/

#配置节点
mkdir -p /logs/config-server/

#路由节点
mkdir -p /logs/router/
```

## 二、mongo配置文件

```shell
#配置节点配置文件
#数据库文件位置 
dbpath=/data/mongo/shard/shard/shard1/shard1-37011

#日志文件位置 
logpath=/logs/shard/shard1/shard1-37011.log

#以追加方式写入日志 
logappend=true

#是否以守护进程方式运行 
fork=true

#绑定ip
bind_ip=0.0.0.0

#绑定端口号
port=37011

#表示是一个配置服务器 
#configsvr=true

#表示是一个分片服务器
shardsvr=true

#配置服务器副本集名称 
replSet=shard1
```

## 三、编写启动脚本

```shell
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/config-17011.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/config-17013.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/config-17015.conf

/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard1-37011.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard1-37013.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard1-37015.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard1-37017.conf

/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard2-47011.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard2-47013.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard2-47015.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard2-47017.conf

/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard3-57011.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard3-57013.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard3-57015.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard3-57017.conf

/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard4-58011.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard4-58013.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard4-58015.conf
/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongod -f /home/mongodb-linux-x86_64-rhel70-4.2.8/shard4-58017.conf

/home/mongodb-linux-x86_64-rhel70-4.2.8/bin/mongos -f /home/mongodb-linux-x86_64-rhel70-4.2.8/router-27017.conf
```

## 四、配置集群节点

```shell
#进入任意配置节点
./mongo --port 17011

#切换数据库
use admin

#申明变量
var cfg={
	"_id":"config-server",
	"members":[
	{"_id":1,"host":"172.17.0.2:17011"},
	{"_id":2,"host":"172.17.0.2:17013"},
	{"_id":3,"host":"172.17.0.2:17015"}
	]
};

#使用变量
rs.initiate(cfg);
```

## 五、配置分片节点

```shell
#进入任意配置节点
./mongo --port 37011

#切换数据库
use admin

#申明变量
var cfg={
	"_id":"shard1",
	"protocolVersion":1,
	"members":[
	{"_id":1,"host":"172.17.0.2:37011","priority":10},
	{"_id":2,"host":"172.17.0.2:37013","priority":0},
	{"_id":3,"host":"172.17.0.2:37015","priority":5},
	{"_id":4,"host":"172.17.0.2:37017","arbiterOnly":true},
	]
};


#使用变量
rs.initiate(cfg);
```

## 六、配置路由节点

```shell
#路由节点配置文件
logpath=/logs/router/router-27017.log

logappend=true

fork=true

bind_ip=0.0.0.0

port=27017

configdb=config-server/172.17.0.2:17011,172.17.0.2:17013,172.17.0.2:17015

#配置路由分片集群
./mongo --port 27017 
sh.status();
sh.addShard("shard1/172.17.0.2:37011,172.17.0.2:37013,172.17.0.2:37015,172.17.0.2:37017"); sh.addShard("shard2/172.17.0.2:47011,172.17.0.2:47013,172.17.0.2:47015,172.17.0.2:47017"); 
sh.addShard("shard3/172.17.0.2:57011,172.17.0.2:57013,172.17.0.2:57015,172.17.0.2:57017");
sh.addShard("shard4/172.17.0.2:58011,172.17.0.2:58013,172.17.0.2:58015,172.17.0.2:58017");
sh.status();

#为数据库开启分片功能
sh.enableSharding("test");
#为指定集合开启分片功能
sh.shardCollection("test.product",{"prdPtfId":"hashed"})
```

## 七、创建管理员

```shell
#进入路由节点 
./mongo --port 27017

#切换admin库
use admin

#创建管理员
#若不创建管理员，即使为其他库创建了用户，连接依然拥有超级权限
db.createUser(
{
	user:"root",
	pwd:"123456",
	roles:[
				{
					role:"root",
					db:"admin"
				}
				]
}
);

#为test库创建普通权限
db.createUser(
{
	user:"test01",
	pwd:"test01",
	roles:[
				{
					role:"readWrite",
					db:"test"
				}
				]
}
);
```

## 八、开启权限控制

```shell
#安装一键kill功能
yum install psmisc

#一键关闭
killall mongod

#生成秘钥文件
openssl rand -base64 756 > /data/testKeyFile.file

#授权
chmod 600 /data/testKeyFile.file

#在配置文件中开启身份认证
auth=true
keyFile=/data/testKeyFile.file

#在路由文件中配置
keyFile=/data/testKeyFile.file
```

## 视频地址
链接:https://pan.baidu.com/s/1aH90o80tQzZ2DG2C_CfOYw  密码:eqvh