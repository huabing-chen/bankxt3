package com.chb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil2 {

	

		private static final String DRICER = "com.mysql.jdbc.Driver";
		private static final String URL="jdbc:mysql://localhost:3306/bankinfo";
		private static final String USER = "root";
		private static final String PWD = "root";
		
		private static Connection connection = null;
		private static PreparedStatement preps = null;
		/**
		 * 获取连接
		 */
		public static Connection getConnection() {
			try {
				Class.forName(DRICER);
				connection = DriverManager.getConnection(URL, USER, PWD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
		/**
		 * 写一个通用的  增加  删除  修改方法
		 */
		public static int insertOrDeleteOrUpdate(String sql,Object...objects) {
			int row = -1;
			
			
			try {
				connection=getConnection();
				preps = connection.prepareStatement(sql);
				//填充数据
				for(int i=0;i<objects.length;i++) {
					preps.setObject(i+1, objects[i]);
				}
				row = preps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
					try {
						if(preps!=null) {
						preps.close();
						}
						if(connection!=null) {
							connection.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			return row;
		}
}
