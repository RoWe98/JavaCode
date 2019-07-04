package com.rexrowe.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.*;

public class DbUtil{

    private static String driver;
    private static String url;
    private static String userName;
    private static String password;

    static {
        try{

            Properties prop = new Properties();
            InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(in);
            driver=prop.getProperty("driver");
            url=prop.getProperty("url");
            userName=prop.getProperty("userName");
            password=prop.getProperty("password");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    //连接数据库
    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,userName,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    //释放资源
    public static void closeConn(Connection conn,Statement stmt,ResultSet rs) {
        try{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
