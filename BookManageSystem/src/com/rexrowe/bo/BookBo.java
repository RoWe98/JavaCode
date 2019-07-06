package com.rexrowe.bo;

import com.rexrowe.dao.BookDAO;
import com.rexrowe.dao.DbUtil;
import com.rexrowe.dao.UserDAO;
import com.rexrowe.pojo.BookVO;
import com.rexrowe.pojo.UserVO;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BookBo {


    BookDAO bookDAO = new BookDAO();

    //获取书的数量
    public int getBookNum(String table_flag){
        ArrayList<String> booknumList = new ArrayList<String>();
        booknumList = bookDAO.getbookNum(table_flag);
        return booknumList.size();
    }



    //获取借书的书的编码
    public List get_Borrow_BookInfo(String username,String user_borrow_limit){


        String borrow_BookInfo = "";
        String borrow_table_id = "";

        UserDAO userDAO = new UserDAO();
        UserVO userVO_borrow_num = userDAO.get_borrow_num(username);
        String have_borrow_num = userVO_borrow_num.getBorrow_num();
        String borrow_num = have_borrow_num;
        List<String> list = new ArrayList<>();

        //通过最多可以借的书判断往哪张表插入数据
        if(user_borrow_limit.equals("10")) {
            borrow_table_id = "`bookUser_professor`";
            System.out.println(borrow_table_id);
        }else if(user_borrow_limit.equals("8")){
            borrow_table_id = "`bookUser_teacher`";
        }else if(user_borrow_limit.equals("6")){
            borrow_table_id = "`bookUser_helpteacher`";
        }else if(user_borrow_limit.equals("5")){
            borrow_table_id = "`bookUser_stu`";
        }

        String id = "'"+username+"'";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();

            String sql = "select * from "+borrow_table_id+" "+"where id = "+id+";";
            ps=conn.prepareStatement(sql);
            //ps.setString(1,borrow_table_id);
            rs=ps.executeQuery();
            System.out.println(borrow_num);
            if(borrow_table_id.equals("`bookUser_professor`")) {
                //List<String> list = new ArrayList<>();
                while (rs.next()) {
                    String user_id = rs.getString(1);
                    //borrow_BookInfo = borrow;
                    for(int i=2;i<=Integer.parseInt(borrow_num)+1;i++){
                        String book_info = rs.getString(i);
                        System.out.println("Add book_info");
                        list.add(book_info);
                    }
                    System.out.println(list);
                }
            }else if(borrow_table_id.equals("`bookUser_teacher`")){
                //List<String> list = new ArrayList<>();
                while (rs.next()) {
                    String user_id = rs.getString(1);
                    //borrow_BookInfo = borrow;
                    for(int i=2;i<=Integer.parseInt(borrow_num)+1;i++){
                        String book_info = rs.getString(i);
                        System.out.println("Add book_info");
                        list.add(book_info);
                    }
                    System.out.println(list);
                }
            }else if(borrow_table_id.equals("`bookUser_helpteacher`")){
                //List<String> list = new ArrayList<>();
                while (rs.next()) {
                    String user_id = rs.getString(1);
                    //borrow_BookInfo = borrow;
                    for(int i=2;i<=Integer.parseInt(borrow_num)+1;i++){
                        String book_info = rs.getString(i);
                        System.out.println("Add book_info");
                        list.add(book_info);
                    }
                    System.out.println(list);
                }
            }else if(borrow_table_id.equals("`bookUser_stu`")){
                //List<String> list = new ArrayList<>();
                while (rs.next()) {
                    String user_id = rs.getString(1);
                    //borrow_BookInfo = borrow;
                    for(int i=2;i<=Integer.parseInt(borrow_num)+1;i++){
                        String book_info = rs.getString(i);
                        System.out.println("Add book_info");
                        list.add(book_info);
                    }
                    System.out.println(list);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(borrow_BookInfo);
        return list;

    }

    //获取借的书并返回一个list
    public List show_Borrow_List(List list){

        List<BookVO> book_list = new ArrayList<>();

        int list_length = list.size();

        for(int i=0;i<list_length;i++){
            BookVO bookvo_info = new BookVO("","","","");
            BookDAO bookDAO = new BookDAO();
            String book_info = (String)list.get(i);
            String book_info_arr[] = book_info.split(":");
            String book_info_id = book_info_arr[0];
            String book_info_table = book_info_arr[1];
            bookvo_info = bookDAO.getbookInfo_borrow(book_info_id,book_info_table);
            book_list.add(bookvo_info);
        }

        return book_list;
    }
}
