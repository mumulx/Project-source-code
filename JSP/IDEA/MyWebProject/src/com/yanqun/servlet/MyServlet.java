package com.yanqun.servlet;

import com.yanqun.util.DBCPDemo;
import com.yanqun.util.DButil;

import java.io.IOException;
import java.sql.SQLException;

@javax.servlet.annotation.WebServlet( "/MyServlet")
public class MyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("dopost...");
        System.out.println( DBCPDemo.getDataSourceWIthDBCP())  ;
        //三层
        String sql = "delete from student where id = ?" ;
        Object[] params = new Object[]{4};
        try {
            DButil.executeUpdate(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("doget1111");
        this.doPost(request,response);
    }
}
