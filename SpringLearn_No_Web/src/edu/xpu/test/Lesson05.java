
package edu.xpu.test;

import edu.xpu.model.User;
import edu.xpu.service.IUserService;
import edu.xpu.service.UserServiceFactory1;
import edu.xpu.service.UserServiceFactory2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class Lesson05 {

    public static void test1() throws Exception{



        ApplicationContext context = new ClassPathXmlApplicationContext("beans5.xml");

        User user = (User)context.getBean("user");

        System.out.println(user);

        // 关闭容器
        context.getClass().getMethod("close").invoke(context);

    }

    public static void main(String[] args) {

        try{
            Lesson05.test1();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
