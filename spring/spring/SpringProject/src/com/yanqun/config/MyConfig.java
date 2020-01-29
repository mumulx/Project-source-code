package com.yanqun.config;

import com.yanqun.entity.*;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//配置类
@Configuration
// @Import({Apple.class,Banana.class})
// @Import({Orange.class,MyImportSelector.class})
// @Import({MyImportBeanDefinitionRegistrar.class})
//@ComponentScan(value="com.yanqun",excludeFilters = {  @ComponentScan.Filter(type= FilterType.ANNOTATION,classes ={Controller.class} )}  )
//@ComponentScan(value="com.yanqun",excludeFilters = {  @ComponentScan.Filter(type= FilterType.ANNOTATION,classes ={Controller.class} )}  )
//@ComponentScan(value="com.yanqun",excludeFilters = {  @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes ={StudentDao.class} )}  )
//@ComponentScan(value="com.yanqun",includeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,classes ={Controller.class})},useDefaultFilters = false)
//@ComponentScan(value="com.yanqun",includeFilters = {@ComponentScan.Filter(type= FilterType.CUSTOM,classes ={ MyFilter.class  })},useDefaultFilters = false)
@ComponentScan(value="com.yanqun")
public class MyConfig {
        /*
        <bean id="myaddress" class="com.yanqun.entity.Address">
              <property name="homeAddrss" value="xa"></property>
              <property name="schoolAddrss" value="bj"></property>
        </bean>
    */
//   @Bean(value="stu",initMethod = "myInit",destroyMethod = "myDestroy")  //id="stu" class="...Student"
   @Bean(value="stu")  //id="stu" class="...Student"
//   @Scope("singleton")
//   @Lazy
//   @Autowired
    public Student myStudent( Address address){
        Student student = new Student(10,"zs10",23);
//        Address address = new Address("xa02","bj02") ;
     System.out.println("=================address:"+address);
        student.setAddress(address);
        return student;
    }
    @Bean
    public Address address1(){
        Address address = new Address("xa02","bj02") ;
        return address ;
    }

//    @Bean
//    public Address address2(){
//        Address address = new Address("xa02","bj02") ;
//        return address ;
//    }

//    @Bean
//    @Conditional(OilCarCondition.class)
//    public Car oilCar()
//    {
//        return new OilCar() ;
//    }
//
//    @Bean
//    @Conditional(EnergyCarCondition.class)
//    public Car energyCar()
//    {
//        return new EnergyCar() ;
//    }

    @Bean
    public FactoryBean<Apple> myFactoryBean(){
       return new MyFactoryBean();//到底是什么？MyFactoryBean 、Apple ？
    }

    @Profile("myApple")
    @Bean("apple")
    public Fruit apple(){
       return new Apple() ;
    }
    @Profile("myBanana")
    @Bean("banana")
    public Fruit banana(){
        return new Banana() ;
    }

}
