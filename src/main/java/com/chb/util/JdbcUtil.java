package com.chb.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.chb.entity.Account;

public class JdbcUtil {

	private static Properties properties = null;
	private static DataSource dataSource = null;
	private static Connection connection = null;
	
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	private static final String DRICER = "com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/bankinfo";
	private static final String USER = "root";
	private static final String PWD = "root";
	
//	private static Connection connection = null;
//	private static PreparedStatement preps = null;
	
	
	
	static {
        try {
            properties = new Properties();
            // 1.加载properties文件
            InputStream is = 
            		JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            // 2.加载输入流
            properties.load(is);

            // 3.获取数据源
            dataSource =  DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * 获取数据库连接
	 * @return 
	 */
	public static Connection getConnection() {
		try {
//			System.out.println("ss");
			connection = dataSource.getConnection();
//			System.out.println("ss");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 获取连接
	 */
//	public static Connection getConnection() {
//		try {
//			Class.forName(DRICER);
//			connection = DriverManager.getConnection(URL, USER, PWD);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return connection;
//	}
	public static int insertOrUpdateOrDelete1(String sql,Object...objects) {
		int row = -1;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			 connection = getConnection();
			 connection.setAutoCommit(false);
			 pstmt = connection.prepareStatement(sql);
			
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
			row = pstmt.executeUpdate();
			connection.commit();
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}finally {
			closeAll();
		}
		return row;
	}
	//账号的状态是否为  1，；state =1?
	
	public static int accountState(String accountNumber) {
		int row = -1;
		try {
			String sql = "select * from account where accountNumber = ?";
			Account dao = JdbcUtil.query(Account.class, sql, accountNumber);
			row = dao.getState();
			
		} catch (Exception e) {
			System.out.println();
		}
		 return row;
	}
	
//	public static int selectTable(String sql,Integer in)  {
//		Connection connection2 = getConnection();
//		PreparedStatement pstmt = null;
//		 boolean execute = false;
//		ResultSet rs = null;
//		int id = 0;
//		try {
//			pstmt = connection2.prepareStatement(sql);
//			pstmt.setObject(1, in);
//			
////			execute = pstmt.execute();//只要是查询语句就返回true；
//			 rs = pstmt.executeQuery();
//			 while(rs.next()) {
//				 id = rs.getString("roleId");
//			 }
//		} catch (SQLException e) {
//			e.printStackTrace();
////			System.out.println("账号或密码错误");
//		}finally {
//			closeAll();	
//		}
//		System.out.println(id);
//		return id;
//		
//	}
	
	
	
	public static int selectTable(String sql,String accountNumber,String password)  {
		Connection connection2 = getConnection();
		PreparedStatement pstmt = null;
		 boolean execute = false;
		ResultSet rs = null;
		int id = 0;
		try {
			pstmt = connection2.prepareStatement(sql);
			pstmt.setObject(1, accountNumber);
			pstmt.setObject(2, password);
//			execute = pstmt.execute();//只要是查询语句就返回true；
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 id = rs.getInt(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("账号或密码错误");
		}finally {
			closeAll();	
		}
//		System.out.println(id);
		return id;
		
	}
	public static int insertOrUpdateOrDelete9(String sql1,String sql2 ,String accountNumber1
			,String password1,String accountNumber2,Double balance) {
		int row1 = -1;// 受影响行数
		int row2 = -1;
		PreparedStatement pstmt1 = null;
		 PreparedStatement pstmt2 = null;
		 Connection connection2 = null;
		try {
			// 获取数据库连接
			 connection2 = getConnection();
			 // 变为手动提交事务
			 connection2.setAutoCommit(false);
			 // 通过prepareStatement加载  sql 这个时候的sql 里面带有？占位符
			 pstmt1  = connection2.prepareStatement(sql1);
			 pstmt1.setDouble(1, balance);
			 pstmt1.setString(2,accountNumber1);
			 pstmt1.setString(3, password1);
			 row1 =  pstmt1.executeUpdate();
//			String s = null;//验证回滚是否成功
//			s.charAt(8);
			 
			 pstmt2 = connection2.prepareStatement(sql2);
			 pstmt2.setDouble(1, balance);
			 pstmt2.setString(2, accountNumber2);
			 row2 = pstmt2.executeUpdate();
			 
			 //添加参数
//			 for (int i = 0; i < objects.length; i++) {
//				 // ？的下标从1 开始
//				 pstmt.setObject(i+1, objects[i]);
//			 }
			 // 执行sql 语句
			 //事务的提交
			 connection2.commit();
		} catch (Exception e) {
			try {
				connection2.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			closeAll();
			if(pstmt2!=null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return row1+row2;
	}
	
	
	/**
	 * 释放资源
	 */
	private static void closeAll() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}if(connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void insertOrUpdateOrDelete2(String[] sql,List<Object[]> list) {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			for(int i=0;i<sql.length;i++) {
				 // 通过prepareStatement加载  sql 这个时候的sql 里面带有？占位符
				pstmt = connection.prepareStatement(sql[i]);
				Object[] obs = list.get(i);
				String s = null;
				for(int j=0;j<obs.length;j++) {
					//?的下标从1开始
					pstmt.setObject(j+1,obs[j]);
					}
				int row = pstmt.executeUpdate();
				}
			connection.commit();
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}finally {
			closeAll();
		}
	}
	public static <T> T query(Class<T> cls,String sql,Object...objects) {
		T t = null;
		try {
			getConnection();
			pstmt = connection.prepareStatement(sql);
			//填充参数
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
			rs = pstmt.executeQuery();
			//根据列名找每一个列的值，然后set到对象每一个属性中
			ResultSetMetaData md= rs.getMetaData();
			int columnCount = md.getColumnCount();
			
			while(rs.next()) {//遍历每一行行记录
				t = cls.newInstance();
				//获得每一行对应列名
				for(int i=0;i<columnCount;i++) {
					//列名
					String columnLabel = md.getColumnLabel(i+1);
					Object value = rs.getObject(columnLabel);
					
					Field[] fs = cls.getDeclaredFields();
					for(int j=0;j<fs.length;j++) {
						Field f = fs[j];
						if(columnLabel.equalsIgnoreCase(f.getName())) {
							f.setAccessible(true);
							f.set(t, value);
						}
					}
//					System.out.println(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return t;
	}
	/*
	 * 查询多条记录
	 */
	public static <T> List<T> queryAll(Class<T> cls,String sql,Object...objects){
		List<T> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			//填充参数
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
			rs = pstmt.executeQuery();
			
			//根据列名找每一个列的值，然后set到对象每一个属性中
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			
			while(rs.next()) {//遍历每一行行记录
				T t = cls.newInstance();
				
				//获得每一行对应列名
				
				for(int i=0;i<columnCount;i++) {
					//列名
					String columnLabel = md.getColumnLabel(i+1);
					Object value = rs.getObject(columnLabel);
					
					Field[] fs = cls.getDeclaredFields();
					for(int j=0;j<fs.length;j++) {
						Field f = fs[i];
						if(columnLabel.equalsIgnoreCase(f.getName())) {
							f.setAccessible(true);
							f.set(t, value);
						}
					}
//					System.out.println(value);
				}
				list.add(t);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;
	}
	/**
	 * 查询行记录的行数
	 */
	public static Long getCount(String sql,Object...objects) {
		Long result = -1l;
		try {
			getConnection();
			pstmt = connection.prepareStatement(sql);
			//填充参数
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getLong(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return result;
	}
	
	public static Double getZhi(String sql,String accountNumber,String password)  {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Double balance = 0.0;
		try {
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, accountNumber);
			pstmt.setObject(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				balance= rs.getDouble("balance");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return balance;
	}
	private static void getObject(String string) {
		// TODO Auto-generated method stub
		
	}
	public static void insertOrUpdateOrDelete(String sql, String password2, String accountNumber, String password) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}















