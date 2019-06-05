
package edu.xpu.test;

import edu.xpu.service.IUserService;
import edu.xpu.service.UserServiceFactory1;
import edu.xpu.service.UserServiceFactory2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class Lesson04 {

    public static void test1(){



        ApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");


        // 从容器获取两个Bean

        // singaton 单例 对象地址一样说明为同一个对象

        // prototype 多例 为不同的对象
        IUserService userService1 = (IUserService)context.getBean("userService");

        IUserService userService2  = (IUserService)context.getBean("userService");

        System.out.println(userService1);

        System.out.println(userService2);

    }

    public static void main(String[] args) {

        Lesson04.test1();

    }
}
