package com.rexrowe.controller;

import com.rexrowe.dao.UserDAO;
import com.rexrowe.pojo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.*;

@WebServlet(name = "BorrowServlet",urlPatterns = "/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value[] = request.getParameter("isSelected").split(",");
        String book_id = value[0];
        String table_flag = value[1];
        System.out.println(book_id+table_flag);
        HttpSession session = request.getSession();
        UserVO userInfo = (UserVO)session.getAttribute("userInfo");
        String user_id = userInfo.getUsername();
        System.out.println(user_id);


        String user_borrow_limit = userInfo.getBorrow_limit();
        String user_borrow_num = userInfo.getBorrow_num();

        UserDAO userDAO = new UserDAO();
        userDAO.BorrowBook(user_id,book_id,table_flag,user_borrow_limit,user_borrow_num);
        response.sendRedirect("borrowList.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
