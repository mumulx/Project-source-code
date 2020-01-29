package com.yanqun.test;

import com.yanqun.config.MyConfig;
import com.yanqun.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class TestStudent {
    public static void testXml()
    {
        //1.创建Spring的IOC容器对象
        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从IOC容器中获取Bean实例(id为"student"的Student对象)
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for(String name :beanDefinitionNames){
//            System.out.println(name);
//        }
//        Student stu1 =(Student)context.getBean("student");//setXxx()
//        Student stu2 =(Student)context.getBean("student");//setXxx()
//        System.out.println(stu1 ==stu2);
        ((ClassPathXmlApplicationContext) context).close();

    }

    public static void testAnnotation(){
        //注解方式
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext() ;
        ConfigurableEnvironment environment = (ConfigurableEnvironment)context.getEnvironment();
        environment.setActiveProfiles("myApple");

        //保存点
        context.register(MyConfig.class);
        context.refresh();

        Object apple = context.getBean("apple");
//        Object banana = context.getBean("banana");
        System.out.println("------------"+apple);
//        System.out.println("------------"+banana);

//        System.out.println(context+"9999999999999999999");
//        StudentDao studao = (StudentDao)context.getBean("studentDao") ;
//        System.out.println(studao);

        StudentService stuService = (StudentService)context.getBean("stuService") ;
        System.out.println(stuService);
////
        System.out.println("*******8*8*8******"+ stuService.getStudentDao());
//        Student stu =( Student)context.getBean("stu");
//        System.out.println(stu);

//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for(String name :beanDefinitionNames){
//            System.out.println(name);//name:id
//        }
//        Object obj = context.getBean("myFactoryBean");
//        System.out.println(obj);
//
//        Object obj2 = context.getBean("&myFactoryBean");
//        System.out.println(obj2);
//        Student stu1 = (Student)context.getBean(Student.class) ;
//        Student stu2 = (Student)context.getBean(Student.class) ;
//        System.out.println(stu1==stu2);
        //Student stu = new Student();
//        System.out.println(stu1);
//        MyIntToStringConverter converter=     (MyIntToStringConverter)context.getBean("myConverter") ;
//        converter.myConverter();;
        //创建一个事件 并且发布
//        context.publishEvent(new ApplicationEvent("My Event"  ) {
//         });
        MyEvent3 evn =  new MyEvent3("my Event3...");
        context.publishEvent(evn) ;


        ((AnnotationConfigApplicationContext) context).close();

    }
    public static void main(String[] args)
    {

//        testXml();
        testAnnotation();

    }
}
