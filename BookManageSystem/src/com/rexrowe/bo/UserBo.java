package com.rexrowe.bo;

import com.rexrowe.dao.UserDAO;
import com.rexrowe.pojo.UserVO;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class UserBo {

    UserDAO userDAO = new UserDAO();

    //判断登录
    public boolean checkUser(String username,String password){

        boolean result =false;
        UserVO userVO = userDAO.getAccess(username);
        String password_access = userVO.getPassword();
        if(password.equals(password_access)) {
            result = true;
        }

        return result;
    }

}
