package edu.xpu.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class User implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("2.赋值属性"+username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("  赋值属性"+password);
        this.password = password;
    }

    public User() {
        System.out.println("1.实例化.....");
    }

    @Override
    public String toString() {
        return "User{" +
                "username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.设置bean的名字"+s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory)throws BeansException{
        //把对象放进工厂
        System.out.println("4.Bean工厂："+beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6.属性赋值完成.....");
    }

    public void myInit(){
        System.out.println("7.自定义初始化方法....");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("9.bean对象被销毁.....");
    }

    public void myDestroy(){
        System.out.println("10.自定义的销毁方法.....");
    }
}
