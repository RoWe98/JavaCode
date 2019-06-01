
package edu.xpu.test;

import edu.xpu.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Lesson01 {

    public static void test1(){


        // Spring 容器加载的三种方式
        // 第一种 ClassPathXmlApplicationContext ClassPath:指的就是classes路径 最常用的

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 第二种 文件系统路径获得配置文件[绝对路径获取]

        // ApplicationContext context = new FileSystemXmlApplicationContext("/Users/rex/Desktop/Code/Java/JavaCode/SpringLearn_No_Web/src/edu/xpu/beans.xml");

        // 第三种 使用BeanFactory 了解即可已经过时

        // String path = "/Users/rex/Desktop/Code/Java/JavaCode/SpringLearn_No_Web/src/edu/xpu/beans.xml";

        //BeanFactory factory = new XmlBeanFactory(new FileSystemResource(path));

        //IUserService user = (IUserService)factory.getBean("userService");
        IUserService user = (IUserService)context.getBean("userService");
        user.add();

    }

    public static void main(String[] args) {

        Lesson01.test1();
    }

}

