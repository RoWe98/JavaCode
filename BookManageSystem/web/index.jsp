<%--
  Created by IntelliJ IDEA.
  User: rex
  Date: 2019-07-04
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <style>
      table{
        width:300px;
        height:300px;
        font-size:30px;
        margin:auto;
        margin-top:50px;
      }
    </style>
  </head>
  <body>
  <form action = "LoginServlet" method = "POST">

    <table border="1",font-size: 30px>
      <tr>
        <td>图书管理系统</td>
      </tr>
      <tr>
        <td>用户名:</td>
        <td><input type = "text" name = "user" id = "user"/></td>
      </tr>
      <tr>
        <td>密码:</td>
        <td><input type = "password" name = "pwd" id = "pwd"/></td>
      </tr>
      <tr>
        <td>登录:</td>
        <td><input type = "submit" value = "登录"/></td>
      </tr>
    </table>

  </form>
  </body>
</html>