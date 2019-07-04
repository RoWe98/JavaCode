package com.rexrowe.pojo;

public class UserVO {
    String username;
    String password;
    String name;
    String usertype;
    String borrow_limit;
    String borrow_num;
    String borrow_book_id;

    public String getBorrow_book_id() {
        return borrow_book_id;
    }

    public void setBorrow_book_id(String borrow_book_id) {
        this.borrow_book_id = borrow_book_id;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getBorrow_limit() {
        return borrow_limit;
    }

    public void setBorrow_limit(String borrow_limit) {
        this.borrow_limit = borrow_limit;
    }

    public String getBorrow_num() {
        return borrow_num;
    }

    public void setBorrow_num(String borrow_num) {
        this.borrow_num = borrow_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
