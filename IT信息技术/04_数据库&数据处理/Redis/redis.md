# Redis数据库

key-value数据库，与Memcached类似，有String、list、set和hash类型值。支持push/pop、add/remove操作。

## 安装步骤

1. 下载tar包

2. 解压并进入解压文件夹

3.  make
    		若遇到错误则缺少gcc
        			yum install gcc
        			yum install gcc-g++

4. make install 放入/usr/local/bin中

5. 执行redis-server

6. 修改redis.conf文件
    		daemonize:默认情况下， redis 不是在后台运行的，如果需要在后台运行，把该项的值更改为 yes
        		pidfile：当 Redis 在后台运行的时候， Redis 默认会把 pid 文件放在/var/run/redis.pid，你可以配置到其他地址。当运行多个redis 服务时，需要指定不同的 pid 文件和端口
        		bind：指定 Redis 只接收来自于该 IP 地址的请求，如果不进行设置，那么将处理所有请求，在生产环境中最好设置该项
        		port：监听端口，默认为 6379
        		其他设置请参考：conf设置附录

7. 执行时输入redis-server xxxx/redis.conf

8. 执行redis-cli打开命令行界面

## 使用：
```
shutdown关闭redis服务
dbsize--------------查看当前key的数量
flushdb-------------清空当前库
flushall------------清空所有库
```
#### 键值命令：
```
keys * --------------------------查看当前库的所有键
exists <key> --------------------判断key是否存在
type <key> ----------------------查看键的类型
del <key> -----------------------删除键
move <key> ----------------------将当前数据库中的 key 转移到其它数据库中
expire <key> <second> -----------设置过期时间
persist <key> -------------------移除给定 key 的过期时间
ttl <key> -----------------------查看还有多少秒过期，-1永不过期，-2已过期
randomkey------------------------随机返回 key 空间的一个 key
rename key newkey----------------重命名 key
```
#### 服务器命令：
	ping-----------------------------测试连接是否存活
	echo value-----------------------在命令行打印一些内容
	select 0~15----------------------选择数据库
	quit-----------------------------退出连接
	info-----------------------------获取服务器的信息和统计。
	monitor--------------------------实时转储收到的请求。
	config get properties------------获取服务器配置信息。

###五大数据类型：

####string：

	一个最长512M，二进制安全
	get key--------------------得到key对应键值
	set key value--------------设置键值
	append key value-----------追加键值
	strlen key-----------------键值长度
	setnx key value------------在key不存在时设置key值，若有key则不设置，用以防覆盖问题，nx表示not exist
	incr key-------------------将key中存储的数字加一，若为空置为1
	decr key-------------------减一，空-> -1
	incrby/decrby key step-----将key中数值自定义步长增减
	meset k1 v1 k2 v2 ---------同时设置一个或多个value，成功返回ok表示所有的值都设置了，失败返回0表示没有任何值被设置。
	mget k1 k2 k3--------------同时获取一个或多个value
	msetnx k1 v1 k2 v2---------同时设置一个或多个value，在key不存在时设置key值，若有key则不设置，用以防覆盖问题
		成功返回 ok 表示所有的值都设置了，失败返回 0 表示没有任何值被设置， 但是不会覆盖已经存在的 key
	getrange key start end-----获取值的范围
	setrange key start value---用value覆盖key所存储的字符串值
	setex key time value-------设置键值同时设置过期时间
	getset key value-----------设置新值同时返回旧值

####list：双向链表

	lpush/rpush k1 v1 k2 v2 ...-------------------------从左边/右边插入一个或多个值
	lpop/rpop key---------------------------------------从左边/右边弹出一个值
	rpoplpush k1 k2-------------------------------------从k1列表右边弹出值插到k2左边
	lrange key start end--------------------------------按照索引下标获得元素（左-右）
	linsert key before value1 value---------------------在 key 对应 list 的特定位置之前或之后添加字符串元素
	lset key index value--------------------------------设置 list 中指定下标的元素值(下标从 0 开始)
	lindex key index------------------------------------按照索引取值，返回名称为 key 的 list 中 index 位置的元素
	lrem key num value----------------------------------删除key中num个值为value的值，num>0 时，按从头到尾的顺序删除，num<0 时，按从尾到头的顺序删除，num=0 时，删除全部
	ltrim key start end---------------------------------截取从start到end的key片段，保留指定 key 的值范围内的数据
	llen key--------------------------------------------返回 key 对应 list 的长度

####hashes：哈希值

添加、删除操作时间复杂度O(1)

存储对象时消耗的内存更小、更方便（zipmap）

大小可以在配置文件中指定：

```
hash-max-zipmap-entries 64 #配置字段最多 64 个
hash-max-zipmap-value 512 #配置 value 最大为 512 字节
hset key field value------------------设置 hash field 为指定值，如果 key 不存在，则先创建
hsetnx key field value----------------设置 hash field 为指定值，如果key不存在，则先创建。如果field已经存在，返回0，nx是not exist 的意思
hmset ...-----------------------------同时设置 hash 的多个 field
hget key field------------------------获取指定的 hash field
hmget key field1 field2...------------获取全部指定的 hash filed
hincrby key field num-----------------指定的 hash filed 加上给定值
hexits key field----------------------测试指定 field 是否存在
hlen key------------------------------返回指定 hash 的 field 数量
hdel key field------------------------返回指定 hash 的 field 数量
hkeys key-----------------------------返回 hash 的所有 field
hvals key-----------------------------返回 hash 的所有 value。
hgetall key---------------------------获取某个 hash 中全部的 filed 及 value。
```
####sets：集合类型

	添加、删除和查找的复杂度都是 O(1)
	sadd key value----------------------向名称为 key 的 set 中添加元素
	smembers key------------------------查看key中所有元素
	srem key value----------------------删除名称为 key 的 set 中的元素
	spop key value----------------------随机返回并删除名称为 key 的 set 中一个元素，弹栈
	sdiff key1 key2---------------------返回所有给定 key 与第一个 key 的差集
	sdiffstore newkey k1 k2-------------返回所有给定 key 与第一个 key 的差集，并将结果存为另一个 key
	sinter k1 k2------------------------返回所有给定 key 的交集
	sinterstore newkey k1 k2------------返回所有给定 key 的交集，并将结果存为另一个 key
	sunion k1 k2------------------------返回所有给定 key 的并集
	sunionstore newkey k1 k2------------返回所有给定 key 的并集，并将结果存为另一个 key
	smove k1 k2 value-------------------从第一个 key 对应的 set 中移除 member 并添加到第二个对应 set 中
	scard key---------------------------返回名称为 key 的 set 的元素个数
	sismember key value-----------------测试 member 是否是名称为 key 的 set 的元素
	srandmember key---------------------随机返回名称为 key 的 set 的一个元素，但是不删除元素

####sorted set：有序集合

	zadd key score value---------------------向名称为 key 的 zset 中添加元素，score用于排序。如果该元素已经存在，则根据score 更新该元素的顺序
	zrem key value---------------------------删除名称为 key 的 zset 中的元素
	zrange key start end withscores----------输出key中值，从start~end，顺便输出排序值
	zincrby key score value------------------如果在名称为 key 的 zset 中已经存在元素，则该元素的 score 增加 increment；否则向集合中添加该元素，其 score 的值为 increment
	zrank key value--------------------------返回名称为 key 的 zset 中 member 元素的排名(按 score 从小到大排序)即下标
	zrevrank key value-----------------------返回名称为 key 的 zset 中 member 元素的排名(按 score 从大到小排序)即下标
	zrevrange  key start end withscores------返回名称为 key 的 zset（按 score 从大到小排序）中的 index 从 start 到 end 的所有元素
	zrangebyscore key start end withscores---返回集合中 score 在给定区间的元素
	zcount key start end---------------------返回集合中 score 在给定区间的数量
	zcard key--------------------------------返回集合中元素个数
	zscore key value-------------------------返回给定元素对应的 score
	zremrangebyrank key start end------------删除集合中排名在给定区间的元素
	zremrangebyscore key start end-----------删除集合中 score 在给定区间的元素

##持久化：

#### 1.RDB(默认)：一定时间间隔检测key的变化然后持久化数据

	conf下save行：
	save 900 1 在1个键值改变后的900秒后持久化数据

####2.AOF：日志记录方式，记录每一条操作，每次操作后持久化数据

	conf下编辑：
		appendonly -> yes  	开启AOF
		appendfsync -> always  每一次操作后都进行持久化
					-> everysec 每一秒操作一次
					-> no  不进行持久化

##错误问题：
	1.MISCONF Redis is configured to save RDB snapshots, but it is currently not able to persist on disk. Commands that may modify the data set are disabled, because this instance is configured to report errors during writes if RDB snapshotting fails (stop-writes-on-bgsave-error option). Please check the Redis logs for details about the RDB error.

究其原因是因为强制把redis快照关闭了导致不能持久化的问题，在网上查了一些相关解决方案，通过stop-writes-on-bgsave-error值设置为no即可避免这种问题。
两种修改方法，一种是通过redis命令行修改，另一种是直接修改redis.conf配置文件：
	命令行修改方式示例：
		127.0.0.1:6379> config set stop-writes-on-bgsave-error no
	修改redis.conf文件：
		vi打开redis-server配置的redis.conf文件，然后使用快捷匹配模式：/stop-writes-on-bgsave-error定位到stop-writes-on-bgsave-error字符串所在位置，接着把后面的yes设置为no即可。


#conf设置附录
```
daemonize:
	默认情况下， redis 不是在后台运行的，如果需要在后台运行，把该项的值更改为 yes

pidfile：
	当 Redis 在后台运行的时候， Redis 默认会把 pid 文件放在/var/run/redis.pid，你可以配置到其他地址。当运行多个redis 服务时，需要指定不同的 pid 文件和端口

bind：
	指定 Redis 只接收来自于该 IP 地址的请求，如果不进行设置，那么将处理所有请求，在生产环境中最好设置该项

port：
	监听端口，默认为 6379

timeout：
	设置客户端连接时的超时时间，单位为秒。当客户端在这段时间内没有发出任何指令，那么关闭该连接

loglevel：
	log 等级分为 4 级， debug, verbose, notice, 和 warning。生产环境下一般开启 notice

logfile：
	配置 log 文件地址，默认使用标准输出，即打印在命令行终端的窗口上

databases：
	设置数据库的个数，可以使用 SELECT <dbid>命令来切换数据库。默认使用的数据库是 0

save：
	设置 Redis 进行数据库镜像的频率。
		if(在 60 秒之内有 10000 个 keys 发生变化时){
			进行镜像备份
		}else if(在 300 秒之内有 10 个 keys 发生了变化){
			进行镜像备份
		}else if(在 900 秒之内有 1 个 keys 发生了变化){
			进行镜像备份
		}

rdbcompression：
	在进行镜像备份时，是否进行压缩

dbfilename：
	镜像备份文件的文件名

dir：
	数据库镜像备份的文件放置的路径。这里的路径跟文件名要分开配置是因为 Redis 在进行备份时，先会将当前数据库的状态写入到一个临时文件中，等备份完成时，再把该临时文件替换为上面所指定的文件，而这里的临时文件和上面所配置的备份文件都会放在这个指定的路径当中

slaveof：
	设置该数据库为其他数据库的从数据库

masterauth：
	当主数据库连接需要密码验证时，在这里指定

requirepass：
	设置客户端连接后进行任何其他指定前需要使用的密码。警告：因为redis速度相当快，所以在一台比较好的服务器下，一个外部的用户可以在一秒钟进行 150K 次的密码尝试，这意味着你需要指定非常非常强大的密码来防止暴力破解。

maxclients：
	限制同时连接的客户数量。当连接数超过这个值时， redis 将不再接收其他连接请求，客户端尝试连接时将收到 error 信息。

maxmemory：
	设置 redis 能够使用的最大内存。当内存满了的时候，如果还接收到 set 命令， redis 将先尝试剔除设置过 expire 信息的 key，而不管该 key 的过期时间还没有到达。在删除时，将按照过期时间进行删除，最早将要被过期的 key 将最先被删除。如果带有 expire 信息的 key 都删光了，那么将返回错误。这样， redis 将不再接收写请求，只接收 get 请求。maxmemory 的设置比较适合于把 redis 当作于类似 memcached 的缓存来使用。

appendonly：
	默认情况下， redis 会在后台异步的把数据库镜像备份到磁盘，但是该备份是非常耗时的，而且备份也不能很频繁，如果发生诸如拉闸限电、拔插头等状况，那么将造成比较大范围的数据丢失。所以 redis 提供了另外一种更加高效的数据库备份及灾难恢复方式。开启 append only 模式之后，redis会把所接收到的每一次写操作请求都追加到appendonly.aof文件中，当redis重新启动时，会从该文件恢复出之前的状态。但是这样会造成 appendonly.aof 文件过大，所以 redis 还支持了 BGREWRITEAOF 指令，对appendonly.aof进行重新整理。所以我认为推荐生产环境下的做法为关闭镜像，开启appendonly.aof，同时可以选择在访问较少的时间每天对 appendonly.aof 进行重写一次。

appendfsync：
	设置对 appendonly.aof 文件进行同步的频率。 always 表示每次有写操作都进行同步，everysec 表示对写操作进行累积，每秒同步一次。这个需要根据实际业务场景进行配置

vm-enabled：
	是否开启虚拟内存支持。因为 redis 是一个内存数据库，而且当内存满的时候，无法接收新的写请求，所以在 redis 2.0 中，提供了虚拟内存的支持。但是需要注意的是， redis中，所有的 key 都会放在内存中，在内存不够时，只会把 value 值放入交换区。这样保证了虽然使用虚拟内存，但性能基本不受影响，同时，你需要注意的是你要把vm-max-memory 设置到足够来放下你的所有的 key

vm-swap-file：
	设置虚拟内存的交换文件路径

vm-max-memory：
	这里设置开启虚拟内存之后， redis 将使用的最大物理内存的大小。默认为 0， redis 将把他所有的能放到交换文件的都放到交换文件中，以尽量少的使用物理内存。在生产环境下，需要根据实际情况设置该值，最好不要使用默认的 0

vm-page-size：
	设置虚拟内存的页大小，如果你的 value 值比较大，比如说你要在 value 中放置博客、新闻之类的所有文章内容，就设大一点，如果要放置的都是很小的内容，那就设小一点。

vm-pages：
	设置交换文件的总的 page 数量，需要注意的是， page table 信息会放在物理内存中，每8 个 page 就会占据 RAM 中的 1 个 byte。总的虚拟内存大小 ＝ vm-page-size * vm-pages

vm-max-threads：
	设置 VM IO 同时使用的线程数量。因为在进行内存交换时，对数据有编码和解码的过程，所以尽管IO设备在硬件上本上不能支持很多的并发读写，但是还是如果你所保存的 vlaue 值比较大，将该值设大一些，还是能够提升性能的

glueoutputbuf：
	把小的输出缓存放在一起，以便能够在一个TCP packet中为客户端发送多个响应，具体原理和真实效果我不是很清楚。所以根据注释，你不是很确定的时候就设置成 yes
hash-max-zipmap-entries：
	在 redis 2.0 中引入了 hash 数据结构。当 hash 中包含超过指定元素个数并且最大的元素没有超过临界时， hash 将以一种特殊的编码方式（大大减少内存使用）来存储，这里可以设置这两个临界值

activerehashing：
	开启之后， redis 将在每 100 毫秒时使用 1 毫秒的 CPU 时间来对 redis 的 hash 表进行重新 hash，可以降低内存的使用。当你的使用场景中，有非常严格的实时性需要，不能够接受 Redis 时不时的对请求有 2 毫秒的延迟的话，把这项配置为 no。如果没有这么严格的实时性要求，可以设置为 yes，以便能够尽可能快的释放内存
	
```



# Jedis的使用

java客户端：
	1.下载jar包
		<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
		<dependency>
		    <groupId>redis.clients</groupId>
		    <artifactId>jedis</artifactId>
		    <version>3.1.0</version>
		</dependency>

	2. 获取连接
	   Jedis jedis=new Jedis("192.168.254.1",6379);
	   操作
	   jedis.set("username","zhangsan");
	   关闭连接
	   jedis.close();	

出现的问题：
	1.虚拟机启动redis：
		host改成本机IP地址，端口号自定或注释bind，将protectmode设置为no
		不修改会默认在localhost频道
	2.启动程序出错：
		防火墙问题：
			添加redis端口，重启防火请，ok