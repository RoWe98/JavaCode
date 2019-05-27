
package edu.xpu.test;

import edu.xpu.service.IUserService;
import edu.xpu.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lesson02 {

    public static void test1(){
        // 用service
        //IUserService userService = new UserServiceImpl();
        //userService.add();

        // 用userService 从spring的容器中获取对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        IUserService userService = (IUserService)context.getBean("userService");
        userService.add();
    }

    public static void main(String[] args) {
        Lesson02.test1();
    }

}

