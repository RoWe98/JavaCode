<%--
  Created by IntelliJ IDEA.
  User: rex
  Date: 2019-07-04
  Time: 18:36
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
<form action = "BookServlet" method = "POST">
    <table font-size: 30px>
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
