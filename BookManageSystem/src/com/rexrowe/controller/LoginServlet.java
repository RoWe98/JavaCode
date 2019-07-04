package com.rexrowe.controller;

import com.rexrowe.bo.BookBo;
import com.rexrowe.bo.UserBo;
import com.rexrowe.dao.BookDAO;
import com.rexrowe.dao.UserDAO;
import com.rexrowe.pojo.UserVO;
import org.apache.catalina.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserDAO userDAO = new UserDAO();
        UserBo userBo = new UserBo();
        BookDAO bookDAO = new BookDAO();
        UserVO uservo = new UserVO();
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        HttpSession session = request.getSession();
        System.out.println(user);
        boolean validate = userBo.checkUser(user,pwd);
        if(validate)
        {
            uservo.setUsername(user);
            uservo.setPassword(pwd);
            UserVO userInfo = new UserVO();
            UserVO userType = userDAO.getusertype(user);
            //System.out.println(userType);
            UserVO userVO_borrow_limit = userDAO.get_borrow_limit(user);
            UserVO userVO_borrow_num = userDAO.get_borrow_num(user);
            String userType_flag = userType.getUsertype();
            String user_borrow_limit = userVO_borrow_limit.getBorrow_limit();
            String user_borrow_num = userVO_borrow_num.getBorrow_num();

            if(userType_flag.equals("1")){
                userType_flag = "教授";
            }else if(userType_flag.equals("2")){
                userType_flag = "讲师";
            }else if (!userType_flag.equals("3")) {
                if(userType_flag.equals("4")) {
                    userType_flag = "学生";
                }
            } else {
                userType_flag = "助教";
            }

            session.setAttribute("User",uservo);
            userInfo.setUsername(user);
            userInfo.setUsertype(userType_flag);
            userInfo.setBorrow_limit(user_borrow_limit);
            userInfo.setBorrow_num(user_borrow_num);
            session.setAttribute("userInfo",userInfo);
            response.sendRedirect("showbook.jsp");
        }
        else{
            out.println("Failed!");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
