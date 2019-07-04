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
<%
    String table_flag = (String)session.getAttribute("flag");
    BookBo bookBo = new BookBo();
    int bookNum = bookBo.getBookNum(table_flag);
%>
<form action = "BorrowServlet" method = "POST">
    <table font-size: 30px>
        <tr>
            <td>有<%=bookNum%>本书在书库里</td>
        </tr>
        <tr>
            <td>书本id</td>
            <td>书名</td>
            <td>作者<td>
            <td>出版社</td>
            <td>是否借阅</td>
        </tr>
        <tr>
        <%
            BookDAO bookDAO=new BookDAO();
            List<BookVO> list =bookDAO.show_BookInfo(table_flag);
            for(BookVO tl:list)
            {%>
            <td><%=tl.getId() %></td>
            <td><%=tl.getName() %></td>
            <td><%=tl.getAuthor() %></td>
            <td></td>
            <td><%=tl.getPublish() %></td>
            <%
                String id = tl.getId();
            %>
            <td><input type="radio" value=<%=id+","+table_flag%> name="isSelected">借阅选中</td>
        </tr>

        <%}
        %>
        <tr>
            <td><input type="submit" value="借阅"></td>
        </tr>
        <tr>
            <td><a href="showbook.jsp">返回</a></td>
        </tr>
    </table>

</form>
</body>
</html>
