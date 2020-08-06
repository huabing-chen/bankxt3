package com.chb.service.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.management.relation.Role;

import com.chb.dao.AuthDao;
import com.chb.dao.RecodeDao;
import com.chb.dao.impl.AccountDaoImpl;
import com.chb.dao.impl.AuthDaoImpl;
import com.chb.dao.impl.RecodeDaoImpl;
import com.chb.dao.impl.RoleDaoImpl;
import com.chb.entity.Account;
import com.chb.entity.Menu;
import com.chb.entity.Recode;
import com.chb.entity.Roles;
import com.chb.service.AccountService;
import com.chb.util.CodeNumberUtil;
import com.chb.util.JdbcUtil;
import com.chb.util.JdbcUtil2;
import com.chb.util.MD5;
import com.chb.util.SendCode;

public class AccountServiceImpl implements AccountService{

	static Scanner input = new Scanner(System.in);
	
	//开户
	@Override
	public int addAccount() {
		//用户名 初始额度  身份证  手机号
		System.out.print("请输入用户名：");
		String userName = input.next();
		System.out.println("请选择权限（1、储户，2、银行职员，3、大堂经理）");
		int quanxian = input.nextInt();
		System.out.print("请输入初始额度：");
		int balance = input.nextInt();
		String idCard= new String();
		while(true) {
			System.out.print("请输入身份证：");
			 idCard = input.next();
			if(idCard.length()!=18) {
				System.out.println("身份证不正确，请从新输入！");
			}else {break;}
			
		}
		String telphone = new String();
		while(true) {
			System.out.print("请输入手机号：");
			telphone = input.next();
			if(telphone.length()!=11) {
				System.out.println("手机号不正确，请从新输入！");
			}else {break;};
		}
//		String send=new String();
//		try {
//			send = SendCode.send();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		while(true) {
//			
//			System.out.print("请输入验证码：");
//			String sendCode = input.next();
//			
//			if(sendCode.equals(send)) {
//				System.out.println("验证码正确！");
//				break;
//			}else {
//				System.out.println("验证码错误！");
//			}
//			
//		}//生成短信验证码，但免费次数已经用完了
		
		
		
		
		String mima = idCard.substring(idCard.length()-6, idCard.length());
//		System.out.println(mima);
		String accountNumber = CodeNumberUtil.getCodeNumber("68", "123");
		String password = null;
		Account a = null;
		int row = -1;
		try {
			password = MD5.md5(mima, "chb");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		a = new Account(userName,new BigDecimal(balance),idCard,telphone,password);
		//执行数据库操作
		JdbcUtil2.getConnection();
		String sql = "insert into account(userName,balance,idCard,telphone,password,accountNumber) values(?,?,?,?,?,?)";
		row = JdbcUtil2.insertOrDeleteOrUpdate(sql,userName,balance,idCard,telphone,password,accountNumber);
		if(row>0) {
			
			
			
			
			String sql2 = "select * from account where accountNumber = ? ";
			Account query = JdbcUtil.query(Account.class, sql2,accountNumber);
			
			Integer accountId = query.getAccountId();
			String sql3 ="insert into account_role(accountId,roleId) value(?,?)"; 
			int role = JdbcUtil.insertOrUpdateOrDelete1(sql3, accountId,quanxian);
				if(role>0) {
					System.out.println("权限设置成功！");
				}
			
		}
		return row;
	}

	@Override
	public int cunKuan() {
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		if(JdbcUtil.accountState(accountNumber)==1) {
		System.out.print("请输入密码：");
		String mima = input.next();
		String password = new String();
		ResultSet rs = null;
		 try {
			password = MD5.md5(mima, "chb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Connection connection = JdbcUtil2.getConnection();
			
			String sql3 = "select * from account where accountNumber = ? and password = ?";
			Account dao = JdbcUtil.query(Account.class, sql3, accountNumber,password);
			if(dao.getPassword().equals(password)) {
				System.out.println("密码为默认密码，请修改密码");
			}
			
			String sql = "select * from account where accountNumber = ? and password = ?";
		 int id = JdbcUtil.selectTable(sql, accountNumber, password);
		double balance2 = 0.0;
			if(id>0) {
				while(true) {//钱数是否为正数
					System.out.print("请输入要存入的钱数：");
					 balance2 = input.nextDouble();
					if(balance2<0) {
						System.out.println("错误，请输入大于零的数");
					}else {break;}
				
				}
				
				
				
				String sql2 = "update account set balance = (balance + ?) where accountNumber= ?";
				 row2 = JdbcUtil2.insertOrDeleteOrUpdate(sql2, balance2,accountNumber);
				 if(row2>0) {
					 Date d = new Date();
					 Recode r=new Recode(new BigDecimal(balance2),accountNumber,new java.sql.Date(d.getTime()), "存款");
					 RecodeDao rd= new RecodeDaoImpl();
					 int addDrecode = rd.addDrecode(r);
//					 System.out.println(addDrecode);
//					 System.out.println(r.toString());
					 if(addDrecode>0) {
						 System.out.println("记录保存成功！");
					 }
				 }
				 
				 return row2;
			}else {
				System.out.println("密码或账号错误！");
			}
		}else {
			System.out.println("账号不存在");
		}
		
		 
		return row2;
	}

//	@Override
//	public int quKuan() {
//		return 0;
//	}
	
	@Override
	public int quKuan() {
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		if(JdbcUtil.accountState(accountNumber)==1) {
			
			System.out.print("请输入密码：");
			String mima = input.next();
			String password = new String();
			ResultSet rs = null;
			try {
				password = MD5.md5(mima, "chb");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		Connection connection = JdbcUtil2.getConnection();
			
				
				String sql4 = "select * from account where accountNumber = ? and password = ?";
				Account dao = JdbcUtil.query(Account.class, sql4, accountNumber,password);
				if(dao.getPassword().equals(password)) {
					System.out.println("密码为默认密码，请修改密码");
				}
				
				
				String sql = "select * from account where accountNumber = ? and password = ?";
				int id = JdbcUtil.selectTable(sql, accountNumber, password);
				Double zhi = 0.0;
				double balance = 0.0;
				if(id>0) {
					
					
					while(true) {
						System.out.print("请输入要取出的钱数：");
						balance = input.nextDouble();
						if(balance<0) {
							System.out.println("错误，请输入大于零的数");
						}else {break;}
					}
					
					
					String sql3 = "select * from account where accountNumber = ? and password = ?";
					zhi = JdbcUtil.getZhi(sql3, accountNumber, password);
					
					if(zhi>=balance) {
						String sql2 = "update account set balance = (balance-?) where accountNumber= ?";
						row2 = JdbcUtil2.insertOrDeleteOrUpdate(sql2, balance,accountNumber);
						
						if(row2>0) {
							Date d = new Date();
							//BigDecimal withdrawalMoney,String moduleName, String accountNumber, Date recordTime
							Recode r=new Recode(new BigDecimal(balance), "取款",accountNumber,new java.sql.Date(d.getTime()));
							RecodeDao rd= new RecodeDaoImpl();
							int addDrecode = rd.addWrecode(r);
//							System.out.println(addDrecode);
//							System.out.println(r.toString());
							if(addDrecode>0) {
								System.out.println("记录保存成功！");
							}
						}
						
		
					return row2;
					
				}else {
					System.out.println("余额不足！余额为："+zhi);
					
				}
			
			
			
			
		}else {
			System.out.println("密码或账号错误！");
			}
		}else {
			System.out.println("账户不存在！");
		}
		
		return row2;
		
		
	}

	@Override
	public int chaXun() {
		
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		String mima = new String();
		while(true) {
			System.out.print("请输入密码：");
			mima = input.next();
			if(mima.length()!=6) {
				System.out.println("请输入密码");
			}else {
				break;
			}
		}
		String password = new String();
		ResultSet rs = null;
		 try {
			password = MD5.md5(mima, "chb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Connection connection = JdbcUtil2.getConnection();
		 
		 if(JdbcUtil.accountState(accountNumber)==1) {
			 	String sql = "select * from account where accountNumber = ? and password = ?";
	//		int insertOrUpdateOrDelete1 = JdbcUtil.insertOrUpdateOrDelete1(sql, accountNumber,password);
			Account dao = JdbcUtil.query(Account.class, sql, accountNumber,password);
			if(dao.getPassword().equals(password)) {
				
					System.out.println("密码为默认密码，请修改密码");
				
				
			}
			System.out.println("您的余额为："+dao.getBalance());
			
			 row2 = JdbcUtil.selectTable(sql, accountNumber, password);
		 }else {
			 System.out.println("账号不存在！");
		
		 }
	 
		return row2;
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public int zhuanZhang() {
		int row =-1;
		System.out.print("请输入转出的账号：");
		String accountNumber1 = input.next();
		if(JdbcUtil.accountState(accountNumber1)==1) {
			 System.out.print("请输入转出账号的密码：");
			 String mima1 = input.next();
			 System.out.print("请输入转入的账号：");
			 String accountNumber2 = input.next();
			 if(JdbcUtil.accountState(accountNumber2)==1) {
				 System.out.print("请输入要转入的钱：");
				 double balance = input.nextDouble();
				 
				
				 
				 String password1 = new String();
				 try {
					 password1 = MD5.md5(mima1, "chb");
				 } catch (Exception e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
				 
				 
				 
					String sql = "select * from account where accountNumber = ? and password = ?";
				  Account dao = JdbcUtil.query(Account.class, sql, accountNumber1,password1);
				  if(dao.getPassword().equals(password1)) {
					  System.out.println("密码为默认密码，请修改密码");
				  }
				  BigDecimal balance2 = dao.getBalance();
				  double balance3 = balance2.doubleValue();//账号余额，，balance 为要转出的钱
				  if(balance3 > balance) {
					  
					  //update account set balance = (balance-?) where accountNumber= ?
					  String sql1 = "update account set balance = (balance - ?) where accountNumber = ? and password = ?";
					  String sql2 = new String();
					  sql2 = "update account set balance = (balance + ?) where accountNumber = ?";
					  row = JdbcUtil.insertOrUpdateOrDelete9(sql1, sql2, accountNumber1, password1, accountNumber2, balance);
					  
					  if(row>0) {
							Date d = new Date();
							//String moduleName, BigDecimal transferMoney, String accountNumber, Date recordTime
							Recode r1=new Recode("转账",new BigDecimal(balance), accountNumber1,new java.sql.Date(d.getTime()));
							RecodeDao rd= new RecodeDaoImpl();
							int addDrecode = rd.addTrecode(r1);
							
							
							 Recode r2=new Recode(new BigDecimal(balance),accountNumber2,new java.sql.Date(d.getTime()), "存款");
//							 RecodeDao rd2= new RecodeDaoImpl();
							 int addDrecode2 = rd.addDrecode(r2);
							
							
//							System.out.println(addDrecode);
//							System.out.println(r2.toString());
							if(addDrecode>0 && addDrecode2>0) {
								System.out.println("记录保存成功！");
							}
						}
					  
					  
					  
				  }else {
					  System.out.println("余额不足，余额为："+balance3);
				  }
				  
				 
				 
			 }else {
				 System.out.println("转入账号不存在！");
			 }
			 
		 }else {
			 System.out.println("转出账号不存在！");
		 }
		return row;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int xiuGaiMiMa() {
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		String mima2 = new String();
		if(JdbcUtil.accountState(accountNumber)==1) {
			
			System.out.print("请输入密码：");
			String mima = input.next();
			
			
			while(true) {
				System.out.print("请输入新密码：");
				 mima2 = input.next();
				
				if(mima2.length()!=6) {
					System.out.println("请输入六位数字的密码");
				}else {
					break;
				}
			}
			System.out.print("请再次输入新密码：");
			String mima3 = input.next();
			String password = new String();
			String password2 = new String();
			ResultSet rs = null;
			try {
				password = MD5.md5(mima, "chb");
				password2 = MD5.md5(mima2, "chb");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		Connection connection = JdbcUtil2.getConnection();
			if(mima2.equals(mima3)) {
				String sql = "update account set password = ? where accountNumber = ? and password = ?";
				row = JdbcUtil.insertOrUpdateOrDelete1(sql,password2,accountNumber,password);
			}else {
				System.out.println("两次密码不匹配！");
			}
			
			
		}else {
			System.out.println("账号不存在！");
		}
		
		
		
		return row;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int xiaoHu() {
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		if(JdbcUtil.accountState(accountNumber)==1) {
			
			System.out.print("请输入密码：");
			String mima = input.next();
			String password = new String();
			try {
				password = MD5.md5(mima, "chb");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				String sql2 = "select * from account where accountNumber = ? and password = ?";
				Account dao = JdbcUtil.query(Account.class, sql2, accountNumber,password);
				System.out.println("您的余额为："+dao.getBalance());
			if(dao.getBalance().doubleValue()>0) {
				System.out.println("是否继续注销（y/n）");
				String c = input.next();
				if(c.equalsIgnoreCase("n")) {
					System.exit(0);
				}
			}
			
			String sql = "update account set state = 0 where accountNumber = ? and password =? ";
			row = JdbcUtil.insertOrUpdateOrDelete1(sql, accountNumber,password);
		}else {
			System.out.println("账号不存在！");
		}
		
		return row;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int tuiChu() {
		System.exit(0);
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMenu() {
		AccountDaoImpl adp = new AccountDaoImpl();
		AuthDao authDao= new AuthDaoImpl();
		System.out.println("欢迎来到XXX银行系统");
		System.out.print("请输入你的账号：");
		String accountNumber = input.next();
		
		String password = new String();
		if(JdbcUtil.accountState(accountNumber)==1) {
			
			System.out.print("请输入你的密码：");
			
			String mima = input.next();
			
			try {
				//把MD5加密去掉
//				password = mima;
				password = MD5.md5(mima, "chb");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "select * from account where accountNumber = ?";
			 Account dao = JdbcUtil.query(Account.class, sql, accountNumber);
			 String password2 = dao.getPassword();
			 if(password2.equals(password)) {
				 
				 List<Menu> menus =authDao.selectMenu(accountNumber, password);
//	     Menu menus =authDao.selectMenu(accountNumber, password);
//	     System.out.println("编号："+menus.getId()+":"+menus.getMenuName());
				 //	List<Menu> menus =authDao.selectMenu(userName, mima);
				 for(Menu m:menus) {
					 System.out.println("编号："+m.getId()+":"+m.getMenuName());
				 }
				 Integer id = dao.getAccountId();
				 
				 
				 
				 
				String sql2 = "select * from account_role where accountId = ?";	
				Roles role = JdbcUtil.query(Roles.class, sql2, id);
					Integer rolesId = role.getRoleId();
				 while(true) {
						if(rolesId==1) {
							System.out.println("欢迎您：账号为"+accountNumber+"的用户");
							System.out.println("请选择你要进行的操作：");
							int  a = input.nextInt();
							
							switch(a) {
							case 2:
								adp.zhuanZhang();//转账
							
								break;
							case 3:
								adp.updateAccount();//存钱
								
								break;
							case 4:
								adp.updateAccountj();//取钱
								
								break;
							case 7:
								adp.chaXun();//查询余额
								break;
							case 10:
								adp.tuiChu();//退出
								break;
							default:
								break;
							}
						}else if(rolesId==2) {
							System.out.println("欢迎您：账号为"+accountNumber+"的员工");
							System.out.println("请选择你要进行的操作：");
							int  a = input.nextInt();
							switch(a) {
							case 2:
								adp.zhuanZhang();//转账
							
								break;
							case 3:
								adp.updateAccount();//存钱
								
								break;
							case 4:
								adp.updateAccountj();//取钱
								
								break;
							case 5:
								adp.updatePwd();//修改密码
								break;
							case 6:
								adp.xiaoHu();//销户
								
								break;
							case 7:
								adp.chaXun();//查询余额
								break;
							case 8:
								adp.chaKanLiuShui();
								//查看流水
								break;
							case 9:
								adp.gengHuanShouJiHao();//更换手机号
								break;
							case 10:
								adp.tuiChu();//退出
								break;
							}
						}else if(rolesId==3) {
							System.out.println("欢迎您：大厅经理");
							System.out.println("请选择你要进行的操作：");
							int  a = input.nextInt();
							switch(a) {
							case 1:
								adp.addAccount();//开户
								break;
							case 10:
								adp.tuiChu();//退出
								break;
							default:
								break;
							}
						}else {
							System.out.println("无此选线");
						}
					}
				 
				 
				 
			 }else {
				 System.out.println("密码错误！");
			 }
			 
			
		}else {
			System.out.println("账户不存在！");
			showMenu();
		}
		
		
		
		
	}

	@Override
	public int getHuanShouJiHao() {
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		String mima2 = new String();
		if(JdbcUtil.accountState(accountNumber)==1) {
			
			System.out.print("请输入密码：");
			 mima2 = input.next();
			
			System.out.println("请输入新手机号");
			String telphone =input.next();
			
			
			
			
			
		
			String password2 = new String();
			ResultSet rs = null;
			try {
			
				password2 = MD5.md5(mima2, "chb");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		Connection connection = JdbcUtil2.getConnection();
			
				String sql = "update account set telphone = ? where accountNumber = ? and password = ?";
				row = JdbcUtil.insertOrUpdateOrDelete1(sql,telphone,accountNumber,password2);
				
		}else {
			System.out.println("账号不存在！");
		}
		
		return row;
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public int chaKanLiuShui() {
		
		int row =-1;
		int row2 = -2;
		System.out.print("请输入账号：");
		String accountNumber = input.next();
		String mima = new String();
		String password = new String();
		if(JdbcUtil.accountState(accountNumber)==1) {
			
			System.out.println("请输入密码：");
				mima = input.next();
				try {
					password = MD5.md5(mima, "chb");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String sql = "select * from Account where accountNumber = ?";
				Account account = JdbcUtil.query(Account.class, sql, accountNumber);
				String number = account.getPassword();
//				System.out.println(number);
				if(password.equals(number)) {
						 String sql2 ="select * from recode  \r\n" + 
								 	"where accountNumber = ? group by recordId  desc";
						List<Recode> queryAll = JdbcUtil.queryAll(Recode.class, sql2, accountNumber);
						System.out.println("操作\t\t转出\t\t取款\t\t存款\t\t账号\t\t\t日期");
						for(Recode r:queryAll) {
							System.out.println(r.getModuleName()+"\t\t"+r.getTransferMoney()+"\t\t"+
									r.getWithdrawalMoney()+"\t\t"+r.getDepositMoney()+"\t\t"+r.getAccountNumber()
									+"\t\t"+r.getRecordTime()
									);
						}
				}
				
			
			 
		
			
		}else {
			System.out.println("账号不存在！");
		}
			
			
		
			
		return 0;
	}

}
