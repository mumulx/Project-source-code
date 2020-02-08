package org.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.student.entity.Student;

//通用的数据操作方法
public class DBUtil {
	private static final String URL  ="jdbc:oracle:thin:@127.0.0.1:1521:ORCL" ;
	private static final String USERNAME  ="scott" ;
	private static final String PASSWORD  ="tiger" ;
	public static PreparedStatement pstmt = null ;
	public static Connection connection = null ;
	public static ResultSet rs = null ; 
	
	
	
	
	//通用的：当前页的数据集合 ,因此当前的数据 是强烈依赖于实体类，例如 显示当前页的学生， List<Student>
	//因此需要将此方法 写入到dao层
	
//	public static List<Student> 
	
	//查询总数
	public static int getTotalCount(String sql ) { //select count(1) from student
		int count = -1 ;
		try {
			 pstmt = createPreParedStatement(sql, null );
			 rs = pstmt.executeQuery()  ;//88
			if(rs.next()) {
				count = rs.getInt(1) ;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, connection);
		}
		return count;
	}
	
	
	
	//通用的增删改
	public static boolean executeUpdate(String sql,Object[] params) {//{"zs",1}
		try {  
			 //Object[] obs = { name,age ,...,x} ; 
//			  String sql = "delete from xxx where Name = ? or id = ?  " ;
//			  pstmt.setInt(1,sno );
			  //setXxx()方法的个数 依赖于 ?的个数， 而?的个数 又和 数组params的个数一致
			  //setXxx()方法的个数 ->数组params的个数一致
			  pstmt = createPreParedStatement(sql,params);
			  int count = pstmt.executeUpdate() ;
			  if(count>0)
				  return true ;
			  else 
				  return false ;
			  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			  return false ;
		} catch (SQLException e) {
			e.printStackTrace();
			  return false ;
		}catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
		finally {
			closeAll(null,pstmt,connection);
		}
}
	//Statement
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection)
	{
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(connection!=null)connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}
		
	
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		 Class.forName("oracle.jdbc.OracleDriver") ;
		 return  DriverManager.getConnection( URL,USERNAME,PASSWORD ) ;
	}
	
	public static PreparedStatement createPreParedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		  pstmt = getConnection() .prepareStatement( sql) ;
		  if(params!=null ) {
			  for(int i=0;i<params.length;i++) {
				  pstmt.setObject(i+1, params[i]);
			  }
		  }
		  return pstmt;
	}
	
		//通用的查  :通用 表示  适合与 任何查询
		public static ResultSet executeQuery( String sql ,Object[] params) {//select xxx from xx where name=? or id=?
			Student student = null;
		
			List<Student> students = new ArrayList<>();
			try {
				
				//				  String sql = "select * from student" ;//select enmae ,job from xxxx where...id>3
				
				pstmt = createPreParedStatement(sql,params);
				 rs =  pstmt.executeQuery() ;
				  return rs ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null ; 
			} catch (SQLException e) {
				e.printStackTrace();
				return null ; 
			}catch (Exception e) {
				e.printStackTrace();
				return null ; 
			}
//			finally {
//					try {
//						if(rs!=null)rs.close();
//						if(pstmt!=null)pstmt.close();
//						if(connection!=null)connection.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					} 
//			}
		}
	
	
	
	
	
}
