package com.rexrowe.dao;

import com.rexrowe.pojo.BookVO;
import com.rexrowe.pojo.UserVO;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class BookDAO {

    //获取书的信息
    public BookVO getbookInfo(String book_id,String table_flag){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookVO bookVO = new BookVO("","","","");
        try{
            conn = DbUtil.getConn();
            //select * from book_tech
            //select * from book_renwen
            String sql = "select * from book"+"_"+table_flag;
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                String book_Name = rs.getString(2);
                String book_Author = rs.getString(3);
                String book_Publish = rs.getString(4);
                bookVO = new BookVO(book_id,book_Name,book_Author,book_Publish);
                bookVO.setId(book_id);
                bookVO.setName(book_Name);
                bookVO.setAuthor(book_Author);
                bookVO.setPublish(book_Publish);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookVO;
    }


    //获取书的信息_借阅
    public BookVO getbookInfo_borrow(String book_id,String table_flag){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String book_id_sql = "'"+book_id+"'";
        System.out.println(book_id);
        BookVO bookVO = new BookVO("","","","");
        try{
            conn = DbUtil.getConn();
            //select * from book_tech where id = '1';
            String sql = "select * from book"+"_"+table_flag+" where id ="+book_id_sql+";";
            System.out.println(sql);
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                String book_Name = rs.getString(2);
                String book_Author = rs.getString(3);
                String book_Publish = rs.getString(4);
                bookVO = new BookVO(book_id,book_Name,book_Author,book_Publish);
                bookVO.setId(book_id);
                bookVO.setName(book_Name);
                bookVO.setAuthor(book_Author);
                bookVO.setPublish(book_Publish);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookVO;
    }

    //查询书本的数量
    public ArrayList<String> getbookNum(String table_flag){

        ArrayList<String> bookNumList = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();
            //select * from table_tech
            String sql = "select id from book"+"_"+table_flag;
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
               String book_id = rs.getString(1);
               bookNumList.add(book_id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookNumList;
    }

    //查询书本的信息
    public List show_BookInfo(String table_flag){

        List<BookVO> list =new ArrayList<BookVO>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();
            //select * from book_tech
            String sql = "select * from book"+"_"+table_flag;
            ps=conn.prepareStatement(sql);
            //ps.setString(1,id);
            rs=ps.executeQuery();
            while(rs.next()) {

                String book_id = rs.getString(1);
                String book_Name = rs.getString(2);
                String book_Author = rs.getString(3);
                String book_Publish = rs.getString(4);
                BookVO bookVO = new BookVO(book_id,book_Name,book_Author,book_Publish);
                list.add(bookVO);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
