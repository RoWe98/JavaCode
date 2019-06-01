
package edu.xpu.test;

import edu.xpu.service.IUserService;
import edu.xpu.service.UserServiceFactory1;
import edu.xpu.service.UserServiceFactory2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class Lesson03 {

    public static void test1(){


        /*
        *
        * 装配Bean的三种方式，所谓的装配Bean就是在xml里面写一个bean标签
        *
        * */


        ApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");

        // new 对象

        //IUserService userService1 = (IUserService)context.getBean("userService1");
        //userService1.add();


        // 静态工厂
        //IUserService userService2 = UserServiceFactory1.createUserService();

        //IUserService userService2 = (IUserService)context.getBean("userService2");

        // 实例工厂
        //1.创建工厂
        //UserServiceFactory2 factory2 = new UserServiceFactory2();
        //IUserService userService3 = factory2.createUserService();

        IUserService userService3 = (IUserService)context.getBean("userService3");

        userService3.add();

    }

    public static void main(String[] args) {

        Lesson03.test1();

    }
}
