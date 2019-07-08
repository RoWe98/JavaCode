package com.rexrowe.dao;

import com.rexrowe.pojo.UserVO;
import com.sun.deploy.net.HttpRequest;
import org.apache.catalina.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//数据库用户访问类
public class UserDAO {

    //判断登录
    public UserVO getAccess(String username){
        UserVO uservo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //通过你输入的用户名在数据库表bookUser中查询密码 并确认是否匹配
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


    //获取用户的身份 教授1 讲师2 助教3 学生0 userVO_Type.getuserType();
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

    //获取当前身份的用户可以借书的最高数量
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

    //获取当前身份用户已经借书的数量
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

    //获取已经借阅的书的id
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
        public void BorrowBook(String username,String book_id,String table_flag,String user_borrow_limit,String user_borrow_num){

            //获取已经借的书的数量
            UserDAO userDAO = new UserDAO();
            UserVO userVO_borrow_num = userDAO.get_borrow_num(username);
            String have_borrow_num = userVO_borrow_num.getBorrow_num();
            int have_borrow_num_int = Integer.parseInt(have_borrow_num);

            String borrow_table_id = "";

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

            if(have_borrow_num_int==0){
                //INSERT INTO `bookmanage`.`bookUser_professer` (`id`, `1`) VALUES ('001', '1:tech');
                String id = "'"+username+"'";
                String book_info = "'"+book_id+":"+table_flag+"'";
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try{

                    conn=DbUtil.getConn();
                    String sql = "INSERT INTO `bookmanage`."+borrow_table_id+" "+"(`id`,`1`) "+"VALUES "+"("+id+","+book_info+");";
                    System.out.println(sql);
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                    UserDAO.Brrow_num_Add(username);
                    System.out.println("DataBase add Success!");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    DbUtil.closeConn(conn,ps,rs);
                }

            }else{

                System.out.println("已经借了的书:"+have_borrow_num);
                //如果现在要借书那么当前可以借书的本数为：最多可以借的书-已经借的书
                int can_borrow_book_num = Integer.parseInt(user_borrow_limit)-have_borrow_num_int;

                //判断是否还可以借书
                if(can_borrow_book_num>0){

                    //判断当前借书的序号: 已经借书的数量+1
                    String borrow_book_no = String.valueOf(have_borrow_num_int+1);
                    System.out.println("当前可以借书的书的序号为:"+borrow_book_no);

                    //UPDATE `bookmanage`.`bookUser_professor` SET `2`='1:renwen' WHERE `id`='001';
                    String borrow_book_no_sql = "`"+borrow_book_no+"`";
                    String book_info = "'"+book_id+":"+table_flag+"'";
                    String id = "'"+username+"'";
                    Connection conn = null;
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    try{

                        conn = DbUtil.getConn();
                        String sql = "UPDATE `bookmanage`."+borrow_table_id+" SET "+borrow_book_no_sql+"="+book_info+" WHERE `id`="+id;
                        System.out.println(sql);
                        ps=conn.prepareStatement(sql);
                        ps.executeUpdate();
                        UserDAO.Brrow_num_Add(username);
                        System.out.println("DataBase add Success!");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        DbUtil.closeConn(conn,ps,rs);
                    }

                }

            }

        }

        //借书后借书字段+1
        public static void Brrow_num_Add(String username){

            UserDAO userDAO = new UserDAO();
            UserVO userVO_borrow_num = userDAO.get_borrow_num(username);
            String borrow_num = userVO_borrow_num.getBorrow_num();
            String new_borrow_num = String.valueOf(Integer.parseInt(borrow_num)+1);

            String new_borrow_num_sql = "'"+new_borrow_num+"'";
            String username_sql = "'"+username+"'";

            //UPDATE `bookmanage`.`bookUser` SET `borrow_num`='3' WHERE `id`='001';
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DbUtil.getConn();
                String sql = "UPDATE `bookmanage`.`bookUser` SET `borrow_num`="+new_borrow_num_sql+" WHERE `id`="+username_sql+";";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                System.out.println("插入成功");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DbUtil.closeConn(conn, ps, rs);
            }
        }

}
