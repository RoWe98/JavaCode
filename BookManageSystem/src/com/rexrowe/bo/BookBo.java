package com.rexrowe.bo;

import com.rexrowe.dao.BookDAO;
import com.rexrowe.dao.DbUtil;
import com.rexrowe.pojo.BookVO;

import javax.servlet.http.HttpSession;
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
    public String get_Borrow_BookInfo(String username){

        String borrow_BookInfo = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();
            String sql = "select borrow_book_id from bookUser where id=?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                String borrow = rs.getString(1);
                borrow_BookInfo = borrow;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return borrow_BookInfo;

    }

    //获取借的书
    public List show_Borrow_List(String borrow_BookInfo){

        List<BookVO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookDAO bookDAO = new BookDAO();
        BookVO bookVO = new BookVO("","","","");
        String bookinfo[] = borrow_BookInfo.split(",");

        for(int i=0;i<bookinfo.length;i++){

            String info_borrow[]  =bookinfo[i].split(":");
            if(i>0) {
                if(bookinfo[i]!=bookinfo[i-1]) {
                    for (int j = 0; j < info_borrow.length; j++) {
                        bookVO = bookDAO.getbookInfo(info_borrow[0], info_borrow[1]);
                        BookVO bookV_new = new BookVO(bookVO.getId(), bookVO.getName(), bookVO.getAuthor(), bookVO.getPublish());
                        list.add(bookV_new);
                    }
                }
            }
        }

        return list;
    }
}
