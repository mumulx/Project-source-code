package com.yanqun;

import java.sql.*;

public class MeteDataDemo {
    private  final static String  DRIVER="oracle.jdbc.OracleDriver";
    private  final static String  URL="jdbc:oracle:thin:@localhost:1521:orcl";
    private  final static String  USERNAME="scott";
    private  final static String  PASSWORD="tiger";



        public static void databaseMetaData(){
            try {
                Class.forName(DRIVER);
               Connection connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
               //数据库元信息
                DatabaseMetaData dbMetadata = connection.getMetaData() ;
               String dbName =  dbMetadata.getDatabaseProductName() ;
                System.out.println("数据库名"+dbName);
                String dbVersion = dbMetadata.getDatabaseProductVersion() ;//11.1.22
                System.out.println("数据库版本"+dbVersion);

                String driverName = dbMetadata.getDriverName();
                System.out.println(driverName);
                String url = dbMetadata.getURL();
                System.out.println(url);
                String userName = dbMetadata.getUserName();
                System.out.println(userName);

                System.out.println("-----------");
                ResultSet rs = dbMetadata.getPrimaryKeys(null, userName, "STUDENT");
                while(rs.next()){
                    Object tableName = rs.getObject(3);
                    Object columnName = rs.getObject(4);
                    Object pkName = rs.getObject(6);
                    System.out.println(tableName+"--"+columnName+"--"+pkName);
                }


            }catch(Exception e){
                e.printStackTrace();
            }
        }

    public static void parameterMetaData(){
        try {
            Class.forName(DRIVER);
            Connection connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);

            PreparedStatement pstmt = connection.prepareStatement("select * from student where id = ? and name = ?");
            //通過pstmt獲取參數元数据
            ParameterMetaData metaData = pstmt.getParameterMetaData();
            int count = metaData.getParameterCount() ;
            System.out.println("参数个数："+count);

            for(int i=1;i<=count ; i++){
                String typeName =  metaData.getParameterTypeName(i) ;
                System.out.println(typeName);

            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void resultSetMetaData(){
        try {
            Class.forName(DRIVER);
            Connection connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);

            PreparedStatement pstmt = connection.prepareStatement("select * from student ");
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            System.out.println("列的个数："+count);

            System.out.println("---");

            for(int i=1;i<=count ;i++){
                String columnName = metaData.getColumnName(i);

                String columnTypeName = metaData.getColumnTypeName(i);
                System.out.println( columnName+"\t"+columnTypeName);
            }




            while(rs.next()){
                for(int i=1;i<=count;i++){
                    System.out.print( rs.getObject(i)+"\t");
                }

                System.out.println();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

//            databaseMetaData();
//        parameterMetaData();
        resultSetMetaData();
    }
}
