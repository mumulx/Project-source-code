package org.ycit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
//模型层用于处理登录（查询数据库）
	public static int login(Login login) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result =-1;//查询返回结果1/0
		int flag = -1;//登录成功与否-1系统异常   1 成功 0用户名密码错误
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:MyOracleDB","scott","135451");
			String sql="select count(*) from login where uname=? and upwd=?";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, login.getName());
			pstmt.setString(2, login.getPwd());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
				
			}
			if(result>0) {
				return 1;
				
			}else {
				return 0;//用户名或密码有误
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;//
		} catch (SQLException e) {
			// TODO: handle exception
	
			e.printStackTrace();
			return -1;//

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;//

		}finally{
			try {
				if(rs!=null) {
					rs.close();
				}	
				if(pstmt!=null) {
					pstmt.close();
				}	
				if(connection!=null) {
					connection.close();
				}	
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}

		
	}
	
	
}
