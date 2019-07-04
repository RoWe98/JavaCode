<%@ page import="com.rexrowe.pojo.UserVO" %>
<%@ page import="org.apache.catalina.User" %><%--
  Created by IntelliJ IDEA.
  User: rex
  Date: 2019-07-04
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
<%
  UserVO uvo = (UserVO)session.getAttribute("User");
  UserVO uinfo = (UserVO)session.getAttribute("userInfo");
  String userType = uinfo.getUsertype();
  String user_borrow_limit = uinfo.getBorrow_limit();
  String user_borrow_num=uinfo.getBorrow_num();
  String username = uvo.getUsername();
%>
<form action = "BookServlet" method = "POST">
    <table font-size: 30px>
        <tr>
            <td>欢迎使用图书管理系统:<%=username%></td>
        </tr>
        <tr>
            <td>你的身份信息为:<%=userType%>;你最多可以借<%=user_borrow_limit%>本书，你还可以借<%=Integer.parseInt(user_borrow_limit)-Integer.parseInt(user_borrow_num)%>本书</td>
        </tr>
        <tr>
            <td>新选择你的功能</td>
        </tr>
        <tr>
            <td>
                <input type="radio" value="search" name="function">查询
                <input type="radio" value="borrow" name="function">借阅
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="确定"></td>
        </tr>
    </table>

</form>
</body>
</html>
