## Springboot整合缓存

JSR-107、Spring缓存抽象、整合Redis

### JSR107


Java Caching定义了5个核心接口，分别是**CachingProvider**, **CacheManager**, **Cache**, **Entry**

和 **Expiry**。 

*  **CachingProvider**定义了创建、配置、获取、管理和控制多个**CacheManager**。一个应用可

以在运行期访问多个CachingProvider。 

*  **CacheManager**定义了创建、配置、获取、管理和控制多个唯一命名的**Cache**，这些Cache

存在于CacheManager的上下文中。一个CacheManager仅被一个CachingProvider所拥有。

*  **Cache**是一个类似Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个

CacheManager所拥有。

* Entry**是一个存储在Cache中的key-value对。

*  **Expiry** 每一个存储在Cache中的条目有一个定义的有效期。一旦超过这个时间，条目为过期

的状态。一旦过期，条目将不可访问、更新和删除。缓存有效期可以通过ExpiryPolicy设置。



Spring从3.1开始定义了`org.springframework.cache.Cache`和`org.springframework.cache.CacheManager`接口来统一不同的缓存技术；

并支持使用JCache（JSR-107）注解简化我们开发；

* Cache接口为缓存的组件规范定义，包含缓存的各种操作集合；

* Cache接口下Spring提供了各种xxxCache的实现；如RedisCache，EhCacheCache , ConcurrentMapCache等；

* 每次调用需要缓存功能的方法时，Spring会检查检查指定参数的指定的目标方法是否已经被调用过；如果有就直接从缓存中获取方法调用后的结果，如果没有就调用方法并缓存结果后返回给用户。下次调用直接从缓存中获取。

* 使用Spring缓存抽象时我们需要关注以下两点；

    

    1. 确定方法需要被缓存以及他们的缓存策略

    2. 从缓存中读取之前缓存存储的数据

### 几个重要概念&缓存注解



| **Cache**          | **缓存接口，定义缓存操作。实现有：****RedisCache****、****EhCacheCache****、**ConcurrentMapCache****等** |
| ------------------ | ------------------------------------------------------------ |
| **CacheManager**   | **缓存管理器，管理各种缓存（****Cache****）组件**            |
| **@Cacheable**     | **主要针对方法配置，能够根据方法的请求参数对其结果进行缓存** |
| **@CacheEvict**    | **清空缓存**                                                 |
| **@CachePut**      | **保证方法被调用，又希望结果被缓存。**                       |
| **@EnableCaching** | **开启基于注解的缓存**                                       |
| **keyGenerator**   | **缓存数据时****key****生成策略**                            |
| **serialize**      | **缓存数据时****value****序列化策略**                        |


![01](/resources/技术框架/Spring家族/01.png)



### 搭建环境

#### 数据库

```sql

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

创建实体类

```java

public class Department {
	
	private Integer id;
	private String departmentName;
	
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Integer id, String departmentName) {
		super();
		this.id = id;
		this.departmentName = departmentName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}
}

public class Employee {
	private Integer id;
	private String lastName;
	private String email;
	private Integer gender; //性别 1男  0女
	private Integer dId;
	public Employee() {
		super();
	}
	public Employee(Integer id, String lastName, String email, Integer gender, Integer dId) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.dId = dId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getdId() {
		return dId;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", dId="
				+ dId + "]";
	}
}
```



整合mybatis操作数据库

1）、配置数据源信息

配置文件application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_cache
spring.datasource.username=root
spring.datasource.password=123456
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# \u5F00\u542F\u9A7C\u5CF0\u547D\u540D\u5339\u914D\u89C4\u5219
mybatis.configuration.map-underscore-to-camel-case=true
logging.level.com.atguigu.cache.mapper=debug
debug=true
spring.redis.host=118.24.44.169
```

2）、使用注解版的mybatis



mapper文件

```java
@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);
    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);
    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmpById(Integer id);
    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);
    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
```

```java
@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}
```

### 缓存

步骤

1. 开启基于注解的缓存

    ​	

    ​	@EnableCaching

    

2. 标注缓存注解

    ​	

    ​		@Cacheable

     

    ​		@CacheEvict

    #### 1）、开启缓存

    ```java
    
    /**
     * 一、搭建基本环境
     * 1、导入数据库文件 创建出department和employee表
     * 2、创建javaBean封装数据
     * 3、整合MyBatis操作数据库
     * 		1.配置数据源信息
     * 		2.使用注解版的MyBatis；
     * 			1）、@MapperScan指定需要扫描的mapper接口所在的包
     * 二、快速体验缓存
     * 		步骤：
     * 			1、开启基于注解的缓存 @EnableCaching
     * 			2、标注缓存注解即可
     * 				@Cacheable
     * 				@CacheEvict
     * 				@CachePut
     * 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在	ConcurrentMap<Object, Object>中
    
     *
     */
    @MapperScan("com.atguigu.cache.mapper")
    @SpringBootApplication
    @EnableCaching
    public class Springboot01CacheApplication {
    	public static void main(String[] args) {
    		SpringApplication.run(Springboot01CacheApplication.class, args);
    	}
    }
    ```
    
    #### 2）、使用缓存
    
    ```java
    
      @Cacheable(value = {"emp"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
        public Employee getEmp(Integer id){
            System.out.println("查询"+id+"号员工");
            Employee emp = employeeMapper.getEmpById(id);
            return emp;
        }
    
    ```
    
##### 几个属性：

![02](/resources/技术框架/Spring家族/02.png)
    
    * cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；


​    
​    
​    * key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值  1-方法的返回值
​    
​    ```
编写SpEL； `#i d;参数id的值   #a0  #p0  #root.args[0]`
​    
getEmp[2]
​    ```

​     

    * keyGenerator：key的生成器；可以自己指定key的生成器的组件id


​    

    			key/keyGenerator：二选一使用;


​    
​    * cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
​      
​    * condition：指定符合条件的情况下才缓存；

condition = "#id>0"
    
condition = "#a0>1"：第一个参数的值》1的时候才进行缓存
    

    * unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断


​        
​    
​    ```
​        unless = "#result == null"
​        unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
​    ```
​    
​    sync：是否使用异步模式
​    
​    @param id
​    
        @return


将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中获取，不用调用方法；



CacheManager管理多个Cache组件的，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字；

 

原理：



1）、自动配置类；CacheAutoConfiguration

2）、缓存的配置类

```
org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
```



3)、 个配置类默认生效：SimpleCacheConfiguration；



4)、给容器中注册了一个CacheManager：ConcurrentMapCacheManager



5)、可以获取和创建ConcurrentMapCache类型的缓存组件；他的作用将数据保存在ConcurrentMap中；


运行流程：



@Cacheable：

1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；（CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。

2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；

key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；

SimpleKeyGenerator生成key的默认策略；

如果没有参数；key=new SimpleKey()；

如果有一个参数：key=参数的值

如果有多个参数：key=new SimpleKey(params)；

3、没有查到缓存就调用目标方法；

4、将目标方法返回的结果，放进缓存中



@Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，

如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；


核心：

1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件

2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator



开发中使用缓存中间件；redis、memcached、ehcache；

#### 整合redis作为缓存

Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。

1、安装redis：使用docker；

```
docker pull registry.docker-cn.com/library/redis
docker images
docker run -d -p 6379:6379 --name myredis registry.docker-cn.com/library/redis
docker ps

```



2、引入redis的starter

3、配置redis

4、测试缓存

原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据

1）、引入redis的starter，容器中保存的是 RedisCacheManager；

2）、RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件；RedisCache通过操作redis缓存数据的

3）、默认保存数据 k-v 都是Object；利用序列化保存；如何保存为json

1、引入了redis的starter，cacheManager变为 RedisCacheManager；

2、默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object, Object>

3、RedisTemplate<Object, Object> 是 默认使用jdk的序列化机制

4）、自定义CacheManager；

## Springboot整合消息

大多应用中，可通过消息服务中间件来提升系统异步通信、扩展解耦能力

消息服务中两个重要概念：

      *  消息代理（message broker）
      *  和目的地（destination）

当消息发送者发送消息以后，将由消息代理接管，消息代理保证消息传递到指定目的地。

消息队列主要有两种形式的目的地

队列（queue）：点对点消息通信（point-to-point）

主题（topic）：发布（publish）/订阅（subscribe）消息通信

**点对点式：**

消息发送者发送消息，消息代理将其放入一个队列中，消息接收者从队列中获取消息内容，消息读取后被移出队列
消息只有唯一的发送者和接受者，但并不是说只能有一个接收者

**发布订阅式：**

发送者（发布者）发送消息到主题，多个接收者（订阅者）监听（订阅）这个主题，那么就会在消息到达时同时收到消息

**JMS**（Java Message Service）JAVA消息服务：

基于JVM消息代理的规范。ActiveMQ、HornetMQ是JMS实现

**AMQP**（Advanced Message Queuing Protocol）

高级消息队列协议，也是一个消息代理的规范，兼容JMS

RabbitMQ是AMQP的实现

|              | JMS                                                          | AMQP                                                         |
| ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 定义         | Java api                                                     | 网络线级协议                                                 |
| 跨语言       | 否                                                           | 是                                                           |
| 跨平台       | 否                                                           | 是                                                           |
| Model        | 提供两种消息模型：（1）、Peer-2-Peer（2）、Pub/sub           | 提供了五种消息模型：（1）、direct exchange（2）、fanout exchange（3）、topic change（4）、headers exchange（5）、system exchange本质来讲，后四种和JMS的pub/sub模型没有太大差别，仅是在路由机制上做了更详细的划分； |
| 支持消息类型 | 多种消息类型：TextMessageMapMessageBytesMessageStreamMessageObjectMessageMessage （只有消息头和属性） | byte[]当实际应用时，有复杂的消息，可以将消息序列化后发送。   |
| 综合评价     | JMS 定义了JAVA API层面的标准；在java体系中，多个client均可以通过JMS进行交互，不需要应用修改代码，但是其对跨平台的支持较差； | AMQP定义了wire-level层的协议标准；天然具有跨平台、跨语言特性。 |





**Spring支持**

spring-jms提供了对JMS的支持

spring-rabbit提供了对AMQP的支持

需要ConnectionFactory的实现来连接消息代理

提供JmsTemplate、RabbitTemplate来发送消息

@JmsListener（JMS）、@RabbitListener（AMQP）注解在方法上监听消息代理发布的消息

@EnableJms、@EnableRabbit开启支持

**Spring Boot自动配置**

```
JmsAutoConfiguration

RabbitAutoConfiguration
```

### RabbitMQ

#### 简介

**RabbitMQ简介：**

RabbitMQ是一个由erlang开发的AMQP(Advanved Message Queue Protocol)的开源实现。

#### 核心概念

**Message**

消息，消息是不具名的，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括routing-key（路由键）、priority（相对于其他消息的优先权）、delivery-mode（指出该消息可能需要持久性存储）等。

**Publisher**

消息的生产者，也是一个向交换器发布消息的客户端应用程序。

**Exchange**

交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。
Exchange有4种类型：direct(默认)，fanout, topic, 和headers，不同类型的Exchange转发消息的策略有所区别

**Queue**

消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。

**Binding**

绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。
Exchange 和Queue的绑定可以是多对多的关系。

**Connection**

网络连接，比如一个TCP连接。

**Channel**

信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内的虚拟连接，AMQP 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。

**Consumer**

消息的消费者，表示一个从消息队列中取得消息的客户端应用程序。

**Virtual Host**

虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个 vhost 本质上就是一个 mini 版的 RabbitMQ 服务器，拥有自己的队列、交换器、绑定和权限机制。vhost 是 AMQP 概念的基础，必须在连接时指定，RabbitMQ 默认的 vhost 是 / 。

**Broker**

表示消息队列服务器实体

#### RabbitMQ运行机制

AMQP 中的消息路由

AMQP 中消息的路由过程和 Java 开发者熟悉的 JMS 存在一些差别，AMQP 中增加了 Exchange 和 Binding 的角色。生产者把消息发布到 Exchange 上，消息最终到达队列并被消费者接收，而 Binding 决定交换器的消息应该发送到那个队列。

##### Direct Exchange

消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配，如果一个队列绑定到交换机要求路由键为“dog”，则只转发 routing key 标记为“dog”的消息，不会转发“dog.puppy”，也不会转发“dog.guard”等等。它是完全匹配、单播的模式。

##### Fanout Exchange

每个发到 fanout 类型交换器的消息都会分到所有绑定的队列上去。fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上。很像子网广播，每台子网内的主机都获得了一份复制的消息。fanout 类型转发消息是最快的。

##### Topic Exchange

topic 交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开。它同样也会识别两个通配符：符号“#”和符号“*”。#匹配0个或多个单词，*匹配一个单词。

### 整合RabbitMQ

1. 引入 spring-boot-starter-amqp
2. application.yml配置
3. 测试RabbitMQ
    1. AmqpAdmin：管理组件
    2. RabbitTemplate：消息发送处理组件

#### 安装RabbitMQ

```shell
docker pull registry.docker.cn.com/library/rabbitmq:3-management

docker images

docker run -d -p 5672:5672 -p 15672:15672 --name mybabbitmq df22j34k23

```

登录

```
22.22.22.22:15672
输入用户名密码
guest/guest
```

#### 测试

1）、添加exchange

name:exchange.direct

type:direct

Durability:Durable

添加其余两个除了name不同其余属性都是相同的

```
exchange.fanout  type:fanout
exchange.topic
```

2）、添加消息队列(Queues)

mumu、mumu.news、mumu.emps、mumulx.news

3）、绑定

进入exchange

在Add binding from this exchange处添加

exchange.fanout、exchange.direct交换机都和着四个消息队列进行绑定（To queue与Routing key相同）

exchange.top绑定四个消息队列routingkey为mumu.#和#.news

4）、交换机发送消息

进入exchange.direct在publish message中

Routing key:mumu

Payload:mumu.exchange.direct.hellword

然后我们切换回Queues发现只有mumu接收了

#### 创建工程

场景选择器选择rabbitmq、web

配置

```properties
spring.rabbitmq.host=xx
spring.rabbitmq.username=superrd
spring.rabbitmq.password=guest
```

测试

##### 发送

```java
 @Autowired
    RabbitTemplate rabbitTemplate;
    //发送消息
    @Test
    void contextLoads() {
        /*
        * 1.单播(点对点)
        * */
        //message需要自己定义，定义消息体内容
        //rabbitTemplate.send(exchage,routerKey,message);
        //只需要传入要发送的对象，自动序列化发送给rabbitMQ
        //rabbitTemplate.convertAndSend(exchage,routerKey,message);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一条消息");
        map.put("data", Arrays.asList("hello", 123, true));
        //对象被默认序列化之后发送出去content_type:	application/x-java-serialized-object
        rabbitTemplate.convertAndSend("exchange.direct","mumu.news",map);
    }
```

接收

```java
//接收消息
@Test
public void receive() {
    Object o = rabbitTemplate.receiveAndConvert("mumu.news");
    System.out.println(o.getClass());
    System.out.println(o);
}
```

##### 定义发送类型为Json类型

先找到模板RabbitTemplate在找到`private MessageConverter messageConverter = new SimpleMessageConverter();`发现是一个简单类型转换器。我们要自定义转换器，新建配置类返回结果为MessageConverter类型。F4打开继承关系图AbstractMessageConverter(抽象类)-->AbstractJackson2MessageConverter--》Jackson2JsonMessageConverter

我们只要返回Jackson2JsonMessageConverter类型的转化器即可

```java
@Configuration
public class RabbitMQConfig {
    //定义类型为Json
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
```

之后我们在发送消息发现content_type: application/json

格式为Json 

##### 广播发送消息

```java
 /*
    * 广播发送消息
    * */
    @Test
    public void postmsg() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一条消息");
        //map.put("data", Arrays.asList("hello", 1213, true));
        rabbitTemplate.convertAndSend("exchange.fanout","",map);
    }
```



#### 监听

`@EnableRabbit`+`@RabbitListener(queues = "mumu.news")`监听消息队列中的内容

```java
@EnableRabbit//开启基于注解的RabbitMQ模式
@SpringBootApplication
public class SpringbootRabbitmqApplication {
```

```java
@Service
public class BookService {
    //当消息队列中有新内容进来时，就会触发该函数
    @RabbitListener(queues = "mumu.news")
    public void receive(Book book) {
        System.out.println("=========收到消息");
    }
}
```

#### 创建消息队列

AmqpAdmin：rabbitMQ的系统管理功能组件

AmqpAdmin：创建和删除Queue、Exchange，Binding

```java
@Autowired
AmqpAdmin amqpAdmin;
@Test
public void createExchange(){
    /*amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("====创建成功");*/
    /*        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        System.out.println("队列创建成功");*/
    //创建绑定
    /*   amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqpxx",null));
    System.out.println("绑定成功");*/
}
```

## SpringBoot与检索

我们的应用经常需要添加检索功能，开源的 ElasticSearch 是目前全文搜索引擎的首选。他可以快速的存储、搜索和分析海量数据。Spring Boot通过整合Spring Data ElasticSearch为我们提供了非常便捷的检索功能支持；

Elasticsearch是一个分布式搜索服务，提供Restful API，底层基于Lucene，采用多shard（分片）的方式保证数据安全，并且提供自动resharding的功能，github等大型的站点也是采用了ElasticSearch作为其搜索服务，



### 安装

```shall
docker serach elasticsearch 
docker pull registry.docker-cb.com/library/elasticserach
docker imaages
docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d -p 9200:9200 -p 9300:9300 --name ES01 s7dfy98asd8f
docker ps
```

#### 测试

```
22.22.22.22:9200
```

以 员工文档 的形式存储为例：一个文档代表一个员工数据。存储数据到 ElasticSearch 的行为叫做 索引 ，但在索引一个文档之前，需要确定将文档存储在哪里。

一个 ElasticSearch 集群可以 包含多个 索引 ，相应的每个索引可以包含多个 类型 。 这些不同的类型存储着多个 文档 ，每个文档又有 多个 属性 。

类似关系：

```
索引-数据库
类型-表
文档-表中的记录
属性-列
```



### 使用postman发送请求

#### 发送数据

```
PUT请求    http://22.22.22.22:9200/megacorp/employee/1
```

Body -->raw-->JSON

```
{
	"aa":"aa",
	"aa":"bb",
	"cc":"cc",
	"dd":["d1","d2"]
}
```

#### 获取数据

```
GET请求    http://22.22.22.22:9200/megacorp/employee/1
```

#### 删除数据

```
DELETE请求
```



将HTTP命令由PUT 改为GET可以用来检索文档，同样的，可以使用DELETE命令来删除文档，以及使用HEAD指令来检查文档是否存在。如果想更新已存在的文档，只需再次PUT

#### 查询所有信息

```
GET请求    http://22.22.22.22:9200/megacorp/employee/_search
```

#### 搜索信息

```
GET请求    http://22.22.22.22:9200/megacorp/employee/_search?q=last_name:Smith
```

#### 查询表达式

```
POST请求    http://22.22.22.22:9200/megacorp/employee/_search
```

Body中添加JSON类型数据

```json
{
	"query":{
		"match":{
			"last_name":"Smith"
		}
	}
}
```

```json
{
	"query":{
		"bool":{
			"must":{
                "match":{
                    "last_name":"Smith"
                },
                "filter":{
                    "range":{
                        "age":{"gt":30}
                    }
                }
            }
		}
	}
}
```

全文检索

只要含有这些单词就会搜索出来

```json
{
	"query":{
		"match":{
			"about":"rock climing"
		}
	}
}
```

短语搜索

全部匹配

```json
{
	"query":{
		"match_phrase":{
			"about":"rock climing"
		}
	}
}
```

高亮搜索

```json
{
	"query":{
		"match_phrase":{
			"about":"rock climing"
		}
	},{
    	"highlight":{
    		"fields":{
    			"about":{}
			}
		}
	}
}
```

### 整合

引入spring-boot-starter-data-elasticsearch

安装Spring Data 对应版本的ElasticSearch

application.yml配置

Spring Boot自动配置的

​	ElasticsearchRepository、ElasticsearchTemplate、Jest

测试ElasticSearch

添加模块

web、

NO SQL ：	Elasticsearch

springBoot默认使用SpringData eleasticSearch模块进行操作

## SpringBoot与任务

异步任务、定时任务、邮件任务

### 异步任务

在Java应用中，绝大多数情况下都是通过同步的方式来实现交互处理的；但是在处理与第三方系统交互的时候，容易造成响应迟缓的情况，之前大部分都是使用多线程来完成此类任务，其实，在Spring 3.x之后，就已经内置了@Async来完美解决这个问题。

两个注解：

@EnableAysnc、@Aysnc

#### 开启异步注解

```java
@EnableAsync//开启异步注解
@SpringBootApplication
public class SpringbootTaskApplication {
```

```java
//告诉Spring这是一个异步方法
@Async
public void hello(){
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("处理数据中...");
}
```

### 定时任务

项目开发中经常需要执行一些定时任务，比如需要在每天凌晨时候，分析一次前一天的日志信息。Spring为我们提供了异步执行任务调度的方式，提供TaskExecutor 、TaskScheduler 接口。

两个注解：@EnableScheduling、@Scheduled

cron表达式：

| **字段** | **允许值**            | **允许的特殊字符** |
| -------- | --------------------- | ------------------ |
| 秒       | 0-59                  | , - * /            |
| 分       | 0-59                  | , - * /            |
| 小时     | 0-23                  | , - * /            |
| 日期     | 1-31                  | , - * ? / L W C    |
| 月份     | 1-12                  | , - * /            |
| 星期     | 0-7或SUN-SAT 0,7是SUN | , - * ? / L C #    |



| **特殊字符** | **代表含义**               |
| ------------ | -------------------------- |
| ,            | 枚举                       |
| -            | 区间                       |
| *            | 任意                       |
| /            | 步长                       |
| ?            | 日/星期冲突匹配            |
| L            | 最后                       |
| W            | 工作日                     |
| C            | 和calendar联系后计算过的值 |
| #            | 星期，4#2，第2个星期四     |



### 开启

```java
@EnableScheduling//开启基于注解的定时任务
@SpringBootApplication
public class SpringbootTaskApplication {
```



```java
/**
* second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
* 0 * * * * MON-FRI
*  【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
*  【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
*  【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
*  【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
*  【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
*/
// @Scheduled(cron = "0 * * * * MON-SAT")
//@Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
// @Scheduled(cron = "0-4 * * * * MON-SAT")
@Scheduled(cron = "0/4 * * * * MON-SAT")  //每4秒执行一次
public void hello(){
System.out.println("hello ... ");
}
```





### 邮件任务

邮件发送需要引入spring-boot-starter-mail

Spring Boot 自动配置MailSenderAutoConfiguration

定义MailProperties内容，配置在application.yml中

自动装配JavaMailSender

测试邮件发送

注意，配置的邮箱的用户名，密码时，并不是自己真正的邮箱的密码，而需要进入邮箱开通相关的服务，之后获取授权码



```java
@Autowired
    JavaMailSenderImpl mailSender;

    //简单邮件
    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("17512080612@163.com");
        message.setFrom("534096094@qq.com");

        mailSender.send(message);
    }

    @Test
    public void test02() throws  Exception{
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

        helper.setTo("17512080612@163.com");
        helper.setFrom("534096094@qq.com");

        //上传文件
        //helper.addAttachment("1.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\1.jpg"));
        //helper.addAttachment("2.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\2.jpg"));

        mailSender.send(mimeMessage);

    }
```

## SpringBoot与安全

Spring Security是针对Spring项目的安全框架，也是Spring Boot底层安全模块默认的技术选型。他可以实现强大的web安全控制。对于安全控制，我们仅需引入spring-boot-starter-security模块，进行少量的配置，即可实现强大的安全管理。几个类：

```
WebSecurityConfigurerAdapter：自定义Security策略
AuthenticationManagerBuilder：自定义认证策略
@EnableWebSecurity：开启WebSecurity模式
```



应用程序的两个主要区域是“认证”和“授权”（或者访问控制）。这两个主要区域是Spring Security 的两个目标。

“认证”（Authentication），是建立一个他声明的主体的过程（一个“主体”一般是指用户，设备或一些可以在你的应用程序中执行动作的其他系统）。

“授权”（Authorization）指确定一个主体是否允许在你的应用程序执行一个动作的过程。为了抵达需要授权的店，主体的身份已经有认证过程建立。

这个概念是通用的而不只在Spring Security中。



## SpringBoot与分布式

分步式、Dubbo/Zookeeper、Spring Boot/Cloud

在分布式系统中，国内常用zookeeper+dubbo组合，而Spring Boot推荐使用全栈的Spring，Spring Boot+Spring Cloud。

* 单一应用架构

    

    ​		当网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。此时，用于简化增删改查工作量的数据访问框架(ORM)是关键。

* 垂直应用架构

    ​	当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的Web框架(MVC)是关键。

* 分布式服务架构

    

    ​	当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。此时，用于提高业务复用及整合的分布式服务框架(RPC)是关键。

* 流动计算架构

    

    ​	当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于提高机器利用率的资源调度和治理中心(SOA)是关键。

    

### Dubbo/Zookeeper

ZooKeeper

ZooKeeper 是一个分布式的，开放源码的分布式应用程序协调服务。它是一个为分布式应用提供一致性服务的软件，提供的功能包括：配置维护、域名服务、分布式同步、组服务等。

Dubbo

Dubbo是Alibaba开源的分布式服务框架，它最大的特点是按照分层的方式来架构，使用这种方式可以使各个层之间解耦合（或者最大限度地松耦合）。从服务模型的角度来看，Dubbo采用的是一种非常简单的模型，要么是提供方提供服务，要么是消费方消费服务，所以基于这一点可以抽象出服务提供方（Provider）和服务消费方（Consumer）两个角色。

#### 安装zookeeper

```
docker pull registry.docker-cn.com/lobrary/zookeeper
docker images
docker run --name zk01 -p 2181:2181 --restart always -d a9s8df79
docker ps
```

#### 添加依赖

```xml
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.1.0</version>
</dependency>

<!--引入zookeeper的客户端工具-->
<!-- https://mvnrepository.com/artifact/com.github.sgroschupf/zkclient -->
<dependency>
    <groupId>com.github.sgroschupf</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.1</version>
</dependency>
```

配置

```properties
dubbo.application.name=provider-ticket

dubbo.registry.address=zookeeper://mycentos6:2182

dubbo.scan.base-packages=com.mumulx.ticket.service

```

```java
@EnableDubbo//开启springboot对dubbo的支持
@SpringBootApplication
public class SpringbootDockerServerApplication {
```

### Spring Boot/Cloud

Spring Cloud

Spring Cloud是一个分布式的整体解决方案。Spring Cloud 为开发者提供了在分布式系统（配置管理，服务发现，熔断，路由，微代理，控制总线，一次性token，全局琐，leader选举，分布式session，集群状态）中快速构建的工具，使用Spring Cloud的开发者可以快速的启动服务或构建应用、同时能够快速和云平台资源进行对接。

SpringCloud分布式开发五大常用组件

* 服务发现——Netflix Eureka
* 客服端负载均衡——Netflix Ribbon
* 断路器——Netflix Hystrix
* 服务网关——Netflix Zuul
* 分布式配置——Spring Cloud Config

Spring Cloud 入门

1、创建provider
2、创建consumer
3、引入Spring Cloud
4、引入Eureka注册中心
5、引入Ribbon进行客户端负载均衡

#### 整合

三个部分：注册中心center，提供方server，使用发client

**center**

配置

```yml
server:
  port: 8761
eureka:
  instance:
    hostname: eureka-center  # eureka实例的主机名
  client:
    register-with-eureka: false #不把自己注册到eureka上
    fetch-registry: false #不从eureka上来获取服务的注册信息
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

```java
@EnableEurekaServer
@SpringBootApplication
public class SpringbootCloudCenterApplication {
```

访问

```
localhost:8761
```

**提供方server**

```java
@Service
public class TicketService {
    public String getTicket(){
        System.out.println("8002");
        return "《厉害了，我的国》";
    }
}
@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;
    @GetMapping("/ticket")
    public String getTicket(){
        return ticketService.getTicket();
    }
}
```

配置

```yml
server:
  port: 8002
spring:
  application:
    name: provider-ticket
eureka:
  instance:
    prefer-ip-address: true # 注册服务的时候使用服务的ip地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

访问

```
localhost:8002/ticket
```

**使用方client**

```java
@RestController
public class UserController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/buy")
    public String buyTicket(String name){
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name+"购买了"+s;
    }
}
```

```yaml
spring:
  application:
    name: consumer-user
server:
  port: 8200

eureka:
  instance:
    prefer-ip-address: true # 注册服务的时候使用服务的ip地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

```

```java
@EnableDiscoveryClient //开启发现服务功能
@SpringBootApplication
public class SpringbootCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCloudClientApplication.class, args);
    }
    @LoadBalanced //使用负载均衡机制
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

```

测试

```
http://localhost:8200/buy?name="zs"
```



## springBoot与热部署

在开发中我们修改一个Java文件后想看到效果不得不重启应用，这导致大量时间花费，我们希望不重启应用的情况下，程序可以自动部署（热部署）。有以下四种情况，如何能实现热部署。

1）、模板引擎

在Spring Boot中开发情况下禁用模板引擎的cache页面模板改变ctrl+F9可以重新编译当前页面并生效

2）、Spring Loaded

Spring官方提供的热部署程序，实现修改类文件的热部署

下载Spring Loaded（[项目地址](https://github.com/spring-projects/spring-loaded)）

添加运行时参数；

```
-javaagent:C:/springloaded-1.2.5.RELEASE.jar –noverify
```


3）、JRebel

收费的一个热部署软件安装插件使用即可

4）、Spring Boot Devtools（推荐）
引入依赖

IDEA使用ctrl+F9或做一些小调整

​	Intellij IEDA和Eclipse不同，Eclipse设置了自动编译之后，修改类它会自动编译，而IDEA在非RUN或DEBUG情况下才会自动编译（前提是你已经设置了Auto-Compile）。

设置自动编译（settings-compiler-make project automatically）

ctrl+shift+alt+/（maintenance）

勾选compiler.automake.allow.when.app.running

### Spring Boot Devtools

#### 添加依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

## Spring Boot与监控管理

通过引入spring-boot-starter-actuator，可以使用Spring Boot为我们提供的准生产环境下的应用监控和管理功能。我们可以通过HTTP，JMX，SSH协议来进行操作，自动得到审计、健康及指标信息等

步骤：

引入spring-boot-starter-actuator

通过http方式访问监控端点

可进行shutdown（POST 提交，此端点默认关闭）

配置

```properties
management.endpoints.web.exposure.include=*
```

访问：这里要加上前缀/actuator

```
http://localhost:8080/actuator/beans
```

监控端点

| auditevents      | 获取当前应用暴露的审计事件信息                               |
| ---------------- | ------------------------------------------------------------ |
| beans            | 获取应用中所有的 Spring Beans 的完整关系列表                 |
| caches           | 获取公开可以用的缓存                                         |
| conditions       | 获取自动配置条件信息，记录哪些自动配置条件通过和没通过的原因 |
| configprops      | 获取所有配置属性，包括默认配置，显示一个所有 @ConfigurationProperties 的整理列版本 |
| env              | 获取所有环境变量                                             |
| flyway           | 获取已应用的所有Flyway数据库迁移信息，需要一个或多个 Flyway Bean |
| liquibase        | 获取已应用的所有Liquibase数据库迁移。需要一个或多个 Liquibase Bean |
| health           | 获取应用程序健康指标（运行状况信息）                         |
| httptrace        | 获取HTTP跟踪信息（默认情况下，最近100个HTTP请求-响应交换）。需要 HttpTraceRepository Bean |
| info             | 获取应用程序信息                                             |
| integrationgraph | 显示 Spring Integration 图。需要依赖 spring-integration-core |
| loggers          | 显示和修改应用程序中日志的配置                               |
| logfile          | 返回日志文件的内容（如果已设置logging.file.name或logging.file.path属性） |
| metrics          | 获取系统度量指标信息                                         |
| mappings         | 显示所有@RequestMapping路径的整理列表                        |
| scheduledtasks   | 显示应用程序中的计划任务                                     |
| sessions         | 允许从Spring Session支持的会话存储中检索和删除用户会话。需要使用Spring Session的基于Servlet的Web应用程序 |
| shutdown         | 关闭应用，要求endpoints.shutdown.enabled设置为true，默认为 false |
| threaddump       | 获取系统线程转储信息                                         |
| heapdump         | 返回hprof堆转储文件                                          |
| jolokia          | 通过HTTP公开JMX bean（当Jolokia在类路径上时，不适用于WebFlux）。需要依赖 jolokia-core |
| prometheus       | 以Prometheus服务器可以抓取的格式公开指标。需要依赖 micrometer-registry-prometheus |

### 远程关闭

```
# 远程关闭
management.endpoint.shutdown.enabled=true
```

发送请求（post请求）

```
http://localhost:8080/actuator/shutdown
```

### 定制端点信息

定制端点一般通过endpoints+端点名+属性名来设置。

修改端点id(springboot1.  )

```
??
```

开启远程应用关闭功能

```
management.endpoint.shutdown.enabled=true
```

关闭端点

```
management.endpoints.enabled-by-default=false
```

开启所需端点

```
management.endpoints.enabled-by-default=false
management.endpoint.beans.enabled=true
```

定制端点访问根路径

```
management.endpoints.web.base-path=/manage
```

定义端点号

```
management.server.port=8881
```

/health

可以查看其他应用的信息(如redis等)只需要加入对应的starter就行























































