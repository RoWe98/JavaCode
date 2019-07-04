package com.rexrowe.dao;

import com.rexrowe.pojo.UserVO;
import org.apache.catalina.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserVO getAccess(String username){
        UserVO uservo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn=DbUtil.getConn(); // 连接数据库
            String sql = "select password from bookUser where id = ?;";// 根据用户名(id)在数据库中查找密码
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                uservo = new UserVO();
                uservo.setUsername(username);
                uservo.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeConn(conn,ps,rs);
        }
        return uservo; //将查找好的内容返回
    }

    public UserVO getusertype(String username_type){


        System.out.println(username_type);
        String ps_username = username_type;
        UserVO userVO_Type = null;
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            conn=DbUtil.getConn();
            String sql = "select usertype from bookUser where id = ?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,ps_username);
            rs=ps.executeQuery();
            while(rs.next()){
                userVO_Type = new UserVO();
                userVO_Type.setUsername(username_type);
                userVO_Type.setUsertype(rs.getString("userType"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeConn(conn,ps,rs);
        }

        return userVO_Type;

    }

    public UserVO get_borrow_limit(String username) {
        UserVO userVO_borrow_limit = null;
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try{
            conn=DbUtil.getConn();
            String sql = "select borrow_limit from bookUser where id = ?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                userVO_borrow_limit = new UserVO();
                userVO_borrow_limit.setUsername(username);
                userVO_borrow_limit.setBorrow_limit(rs.getString("borrow_limit"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeConn(conn,ps,rs);
        }

        return userVO_borrow_limit;
    }

    public UserVO get_borrow_num(String username) {
        UserVO userVO_borrow_num = null;
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn=DbUtil.getConn();
            String sql = "select borrow_num from bookUser where id = ?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                userVO_borrow_num = new UserVO();
                userVO_borrow_num.setUsername(username);
                userVO_borrow_num.setBorrow_num(rs.getString("borrow_num"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeConn(conn,ps,rs);
        }

        return userVO_borrow_num;
    }

    public UserVO get_borrow_book_id(String username) {
        UserVO userVO_borrow_book_id = null;
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn=DbUtil.getConn();
            String sql = "select borrow_book_id from bookUser where id = ?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                userVO_borrow_book_id = new UserVO();
                userVO_borrow_book_id.setUsername(username);
                userVO_borrow_book_id.setBorrow_book_id(rs.getString("borrow_book_id"));
                System.out.println(rs.getString("borrow_book_id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeConn(conn,ps,rs);
        }

        return userVO_borrow_book_id;
    }


    //借书
    public void BorrowBook(String username,String book_id,String table_flag){
        UserDAO userDAO = new UserDAO();
        //String userType = userDAO.getusertype(username).getUsertype();
        String userBorrow_num = userDAO.get_borrow_num(username).getBorrow_num();
        String userborrow_Limit = userDAO.get_borrow_limit(username).getBorrow_limit();

        int userborrow_num = Integer.parseInt(userBorrow_num);
        int userborrow_limit = Integer.parseInt(userborrow_Limit);
        System.out.println(userborrow_num);
        System.out.println(userborrow_limit);
        System.out.println(username);

        UserVO userVO_borrow_book_num = userDAO.get_borrow_num(username);
        UserVO userVO_borrow_book_id = userDAO.get_borrow_book_id(username);
        System.out.println(userVO_borrow_book_num.getBorrow_num());
        String borrow_num = userVO_borrow_book_num.getBorrow_num();
        String id_flag_new = "";
        if(borrow_num!="0") {
            System.out.println(userVO_borrow_book_id.getBorrow_book_id());
            String id_flag_pre[] = userVO_borrow_book_id.getBorrow_book_id().split(",");
            if (id_flag_pre.length > 0) {

                for (int i = 0; i < id_flag_pre.length; i++) {
                    id_flag_new = id_flag_new + id_flag_pre[i];
                }
                String id_flag = book_id + ":" + table_flag+","+id_flag_new;
            }
        }
        String id_flag = book_id + ":" + table_flag;
        int borrow_num_new = Integer.parseInt(borrow_num);
        if(userborrow_num>=0&&userborrow_num<userborrow_limit){
            Connection conn= null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                conn=DbUtil.getConn();
                String sql = "UPDATE `bookmanage`.`bookUser` SET `borrow_num`=?,`borrow_book_id`=? WHERE `id`=?;";
                ps=conn.prepareStatement(sql);
                ps.setString(1,String.valueOf(borrow_num_new+1));
                ps.setString(2,id_flag);
                ps.setString(3,username);
                ps.executeUpdate();
                System.out.println("插入成功");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DbUtil.closeConn(conn,ps,rs);
            }
        }
    }

}
