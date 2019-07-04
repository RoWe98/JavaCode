<%@ page import="com.rexrowe.bo.BookBo" %>
<%@ page import="com.rexrowe.pojo.BookVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.awt.print.Book" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rexrowe.dao.BookDAO" %><%--
  Created by IntelliJ IDEA.
  User: rex
  Date: 2019-07-04
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>图书管理系统</title>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <style>
         table{
            margin:auto;
            margin-top:50px;
            font-size:30px;
        }
    </style>
</head>
<body>

<form action = "SelectServlet" method = "POST">
    <table font-size: 30px>
        <tr>
            <td>选择分类<td>
        </tr>
        <tr>
            <td><input type="radio" value="tech" name="type">IT</td>
            <td><input type="radio" value="renwen" name="type">人文</td>
        </tr>
        <tr>
            <td><input type="submit" value="确定"></td>
        </tr>
    </table>

</form>
</body>
</html>
