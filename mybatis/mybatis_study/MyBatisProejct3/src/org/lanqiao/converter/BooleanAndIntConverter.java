package org.lanqiao.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

//BaseTypeHandler<java类型>
public class BooleanAndIntConverter extends BaseTypeHandler<Boolean>{

	//java(boolean)-DB(number)
	/*
	 * ps:PreparedStatement对象
	 * i：PreparedStatement对象操作参数的位置
	 * parameter:java值
	 * jdbcType：jdbc操作的数据库类型
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
			if(parameter) {
				//1
				ps.setInt(i, 1); 
			}else {
//				0
				ps.setInt(i, 0); 
			}
	}

	//db(number)->java(boolean)
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int sexNum = rs.getInt(columnName) ;//rs.getInt("stuno") ;
//		if(sexNum == 1)
//		
//			return true;
//		else {
//			return false ;
//		}
		return sexNum == 1?true:false ;
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int sexNum = rs.getInt(columnIndex) ;//rs.getInt(1)
		return sexNum == 1?true:false ;
	}

	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int sexNum = cs.getInt(columnIndex) ;//rs.getInt(1)
		return sexNum == 1?true:false ;
	}


	

}
