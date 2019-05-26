package edu.xpu.test;

import edu.xpu.service.IUserService;
import edu.xpu.service.UserServiceImpl;

public class Lesson02 {

    public void test1(){
        // 用service
        IUserService userService = new UserServiceImpl();
        userService.add();
    }

}
