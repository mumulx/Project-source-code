+++

### 软件发展史

1. All In One

    所有代码在一个类/模块中编写容易造成代码混乱
2. MVC/三层架构

    将各个功能根据层次进行了划分，但是所有代码任然在同一台计算机中编写，并发能力有限

3. RPC

    使用RPC，可以让一个项目部署在不同的计算机中但是此种模式的ip+是端口号比较分散，有一定的维护难度

4. SOA：面向服务的架构

    客户端服务端注册中心（客户端、服务端、仲裁中心）


dubbo（阿里巴巴产品）

1. 运行提供方提供的服务
2. 发布服务
3. 订阅服务
4. 推送服务(notify)

    实施监听服务方是否发生改变
5. 调用

监听器(Monitor)

图形化界面，便于监听



## 开发dubbo程序

### 准备环境

#### linux中安装注册中心zookeeper

* linux中安装jdk

	
    下载jdk-8u171-linux-x64.rpm

	安装 
            
        rpm -ivh jdk-8u171-linux-x64.rpm
    会自动安装到usr目录中的java中，

		通过pwd命令，查看jdk安装路径：/usr/java/jdk1.8.0_171-amd64

	配置环境变量：

		vi /etc/profile，在文件最后追加：
		
    	export JAVA_HOME=/usr/java/jdk1.8.0_171-amd64
    	export CLASSPATH=$JAVA_HOME/lib:$CLASSPATH
    	export PATH=$JAVA_HOME/bin:$PATH

    刷新环境变量

        source /etc/profile
    
* 安装zookeeper

	下载zookeeper，[官网](http://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.4.14/)

	解压

        tar -zxvf apache-zookeeper-3.5.6-bin.tar.gz

	重命名zookeeper的配置文件：

        cd apache-zookeeper-3.5.6-bin/conf/
        mv zoo_sample.cfg zoo.cfg 

	在zoo.cfg中：可以发现 

      zookeeper的端口号是 clientPort=2181
    我们修改成		clientPort=2182
	

添加
	
```
	admin.serverPort=8889
```

  设置zookeeper存放数据的目录：

      dataDir=/app/apache-zookeeper-3.5.6-bin/data

  启动zookeeper：

      cd /app/apache-zookeeper-3.5.6-bin/
      bin/zkServer.sh start


        bin/zkServer.sh start   启动
        bin/zkServer.sh stop    关闭
        bin/zkServer.sh status  查看状态

 ### 新建服务端

sts新建maven工程

添加WEB-INF/web.xml文件

添加依赖pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.dubbo</groupId>
  <artifactId>students-server</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
    <!-- 统一版本号  -->
  <properties>
  	<spring.version>4.3.17.RELEASE</spring.version>
  </properties>
  
  <dependencies>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>${spring.version}</version>
	</dependency>

	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-beans</artifactId>
	    <version>${spring.version}</version>
	</dependency>
	
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>${spring.version}</version>
	</dependency>
	
	
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-aspects</artifactId>
	    <version>${spring.version}</version>
	</dependency>
	
	
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-jms</artifactId>
	    <version>${spring.version}</version>
	</dependency>
	
	  <dependency>
	    <groupId>commons-logging</groupId>
	    <artifactId>commons-logging</artifactId>
	    <version>1.1.1</version>
	</dependency>
	
	
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context-support</artifactId>
	    <version>${spring.version}</version>
	</dependency>
	
	<!-- dubbo组件 -->
	<dependency>
	    <groupId>com.alibaba</groupId>
	    <artifactId>dubbo</artifactId>
	    <version>2.5.10</version>
	</dependency>
	
	<!-- zookeeper -->
	<dependency>
	    <groupId>org.apache.zookeeper</groupId>
	    <artifactId>zookeeper</artifactId>
	    <version>3.4.12</version>
	</dependency>
	
	<!-- zookeeper客户端 -->
	<dependency>
	    <groupId>com.github.sgroschupf</groupId>
	    <artifactId>zkclient</artifactId>
	    <version>0.1</version>
	</dependency>
	
	
	<dependency>
	    <groupId>org.javassist</groupId>
	    <artifactId>javassist</artifactId>
	    <version>3.21.0-GA</version>
	</dependency>
	  
  </dependencies>
  
      
 <build>
  <plugins>
	    <plugin>
	        <groupId>org.apache.maven.plugins</groupId>
	        <artifactId>maven-compiler-plugin</artifactId>
	        <version>3.7.0</version>
	        <configuration>
	            <source>1.8</source>
	            <target>1.8</target>
	            <encoding>UTF8</encoding>
	        </configuration>
	    </plugin>
	 	<!-- 给maven项目 内置一个tomcat，之后 可以直接运行 -->
	    <plugin>
	    <groupId>org.apache.tomcat.maven</groupId>
	    <artifactId>tomcat7-maven-plugin</artifactId>
	    <configuration>
	        <port>8881</port>
	        <path>/</path>          
	     </configuration>
	</plugin> 
	</plugins>
	</build>
</project>
```

接口及实现类：（具体的服务）
```
public interface StudentServer {
	public String server(String name);//zs
}

@Service//阿里巴巴提供的@Service注解
public class StudentServerImpl  implements StudentServer{

	public String server(String name) {
		return "server:" +name;
	}

}
```

配置工作：

继承spring:web.xml
```
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
 <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  
  
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
</web-app>
```

配置spring: applicationContext.xml
```
	<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
		
		<!-- 
		<bean id="" class="org.students.server.impl.StudentServerImpl"></bean>
		 -->
	<!-- 配置dubbo的应用名称 -->
	
	<dubbo:application name="students-server" />
	<!-- 配置注册中心地址 -->
	<dubbo:registry protocol="zookeeper" address="zookeeper://192.168.2.128:2181"  />
	
	<!-- 配置dubbo扫描包  ：将@Service所在包 放入 dubbo扫描中，供后续 dubbo在rpc时使用-->
	<dubbo:annotation package="org.students.server.impl" />

	<!-- 将@Service所在包 放入springIOC容器中，供后续 依赖注入时使用 -->
	<context:component-scan base-package="org.student.service.impl"></context:component-scan>

</beans>

```

### 消费方代码：

* 引入依赖(jar)

    pom.xml (与服务方pom.xml一致，改端口号；并设置客户端自己的gav)

* 补齐web工程需要的 WEB-INF/web.xml

    ```
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
    
    <!-- 解决post乱码   -->
    <filter>
        <filter-name>CharacterEncodingfilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
            <init-param>
            <param-name>foreEncoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>CharacterEncodingfilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>  		
        </init-param>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>*.action</url-pattern> 
    </servlet-mapping>
    </web-app>
    ```

* 配置springmvc（通过springmvc 来访问 提供方）

```
	<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd
		http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
	<mvc:annotation-driven>
		<!-- 此配置的目的：将Controller中的内容 直接打印到 浏览器中 -->
		<mvc:message-converters register-defaults="false">
			<bean class="org.springframework.http.converter.StringHttpMessageConverter">
					<constructor-arg value="UTF-8"/>
				</bean>
		</mvc:message-converters>
	</mvc:annotation-driven>
	<!-- 配置dubbo的应用名称 -->
	<dubbo:application name="students-consumer"/>
	<!-- 配置注册中心地址 -->
	<dubbo:registry address="zookeeper://192.168.2.128:2181" />
	<!-- 配置dubbo扫描包 -->
	<dubbo:annotation  package="org.controller"/>
	<!-- 将控制器@Controller所在包 加入IOC容器 -->
	<context:component-scan base-package="org.controller"></context:component-scan>
		
</beans>
```

编写控制器代码：用于访问 服务方提供的服务代码

```
package org.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.students.server.StudentServer;

import com.alibaba.dubbo.config.annotation.Reference;
//@Controller
//@ResponseBody
@RestController
@RequestMapping("controller")
public class StudentController {
	@Reference
	private StudentServer stuServer ;
	@RequestMapping("rpcSerer")
	public String rpcSerer() {
		String result = stuServer.server("zs") ;
		
		return result ;//将结果显示在控制台
		
		
		
	}
}
```


运行

因为maven引入了内置的tomcat7组件所以项目启动可以

右键项目-->run as -->maven build ...

在Goals中输入：

```
romcat7:run
```

浏览器访问

```
http://localhost:8882/controller/rpcServer.action
```

## 安装管理控制台

[管理手册](http://dubbo.apache.org/en-us/docs/admin/install/provider-demo.html)

安装监听器（存在于dubbo-admin;而dubbo-admin又存在于incubator-dubbo-ops
之中）

因此要使用监听器，必须下载incubator-dubbo-ops，但是最新版的incubator-dubbo-ops是在spring boot中使用。

如果要使用 旧的web版，则需要回退版本。但是陈旧版中 ，当前时间该版本 不完整（没有提供完整的maven依赖），因此无法使用。

只能在 历史提交记录中 寻找一个可用的版本（Commits on May 18, 2018 ） 


将下载好的dubbo-admin源代码 进行打包war，为了后续的运行。

执行打包好的dubbo-admin的war包 ：在linux中的tomcat中运行 （将刚才的war放入  tomcat的webapps中即可0）

​		

在maven中引入一个 mvn中央仓库中不存在的Jar：

将jar自己安装到本地mvn仓库：

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=10.2.0.5.0 -Dpackaging=jar -Dfile=d:\ojdbc7.jar  



    <dependency>  
        <groupId>com.oracle</groupId>  
        <artifactId>ojdbc7</artifactId>  
        <version>10.2.0.5.0</version>  
    </dependency>  



service依赖：dao、pojo、父工程 

service启动、发布 

## Dubbo综合案例

zookeeper+dubbo+ssm+maven






















































































