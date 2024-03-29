package com.mumulx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * SpringBoot默认支持两种技术来和ES交互;
 * 1、Jest
 * 需要添加依赖
 * 2、SpringData ElasticSearch
 *		版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *  *		如果版本不适配：2.4.6
 *  *			1）、升级SpringBoot版本
 *  *			2）、安装对应版本的ES
 *  *
 *  * 		1）、Client 节点信息clusterNodes；clusterName
 *  * 		2）、ElasticsearchTemplate 操作es
 *  *		3）、编写一个 ElasticsearchRepository 的子接口来操作ES；
 *  *	两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *  *	1）、编写一个 ElasticsearchRepository
 *
 *
 *
 */
@SpringBootApplication
public class SpringbootElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElasticsearchApplication.class, args);
    }

}
