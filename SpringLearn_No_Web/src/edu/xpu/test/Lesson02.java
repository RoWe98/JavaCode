
package edu.xpu.test;

import edu.xpu.service.IUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class Lesson02 {

    public static void test1(){

        /*
        *
        *   BeanFactory 采用延迟加载，第一次getBean时才会初始化Bean
        *   ApplicationContext 是对BeanFactory的拓展，提供的更多的功能
        *   国际化处理
        *   事件传递
        *   Bean自动装配
        *   各种不同应用层的Context实现
        *
        * */

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //String path = "/Users/rex/Desktop/Code/Java/JavaCode/SpringLearn_No_Web/src/beans.xml";

        //BeanFactory factory = new XmlBeanFactory(new FileSystemResource(path));

        IUserService user = (IUserService)context.getBean("userService");
        //IUserService user = (IUserService)factory.getBean("userService");
        user.add();

    }

    public static void main(String[] args) {

        Lesson02.test1();

    }
}
