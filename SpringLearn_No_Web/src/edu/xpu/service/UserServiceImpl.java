package edu.xpu.service;

public class UserServiceImpl implements IUserService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add() {
        System.out.println("添加用户..."+name);
    }

    public UserServiceImpl() {

        System.out.println("调用UserServiceImpl");

    }
}
