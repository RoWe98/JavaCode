package com.rexrowe.controller;

import com.rexrowe.bo.BookBo;
import com.rexrowe.dao.BookDAO;
import com.rexrowe.pojo.BookVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

@WebServlet(name = "BookServlet",urlPatterns = "/BookServlet")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        BookDAO bookDAO = new BookDAO();
        BookBo bookBo = new BookBo();
        String function = request.getParameter("function");
        if(function.equals("search")){
            response.sendRedirect("selectFlag.jsp");
        }else{
            response.sendRedirect("selectFlag.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
