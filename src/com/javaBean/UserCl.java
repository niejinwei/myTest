package com.javaBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**UserCl javaBean
 * 处理类，其他显示层的Servlet可以通过调用处理类中的方法直接得出结果，本类是业务逻辑代码
 */
public class UserCl {

	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";// 加载驱动
	String url = "jdbc:mysql://localhost:3306/system";// 数据库url
	String user = "root";// 用户名
	String password = "123456";// 密码
	PreparedStatement ps = null;// 用于执行sql
	ResultSet rs = null;// 结果集，用于存储sql语句执行后产生的数据
	// 获取连接

	public Connection getConnection() {
		try {
			Class.forName(driver);// 加载驱动
			con = DriverManager.getConnection(url, user, password);// 得到连接
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 验证用户登录
	public Boolean checkUser(String username, String password) {
		boolean b = false;
		String passwd = null;
		try {
			ps = this.getConnection().prepareStatement("select * from user where name=?and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}

	// 分页显示
	public ArrayList dividePage(int pageNow, int pageSize) {
		ArrayList al = new ArrayList();// ArrayList可以存放对象，所以把JavaBean，User对象中存放的数据可以通过取出ArrayList中的对象进行得到
		try {
			// limit ：取出除此第一个参数外的第二个参数数据
			ps = this.getConnection().prepareStatement("SELECT name,info,major FROM USER LIMIT ?,? ");
			ps.setInt(1, pageSize * (pageNow - 1));
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				User uc = new User();// 使用UserBean
				uc.setName(rs.getString(1));
				uc.setInfo(rs.getString(2));
				uc.setMajor(rs.getString(3));
				al.add(uc);// 添加到ArrayList中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return al;
	}

	// 执行更新
	public boolean update(String name, String info, String major) {
		boolean b = false;
		try {
			ps = this.getConnection().prepareStatement("update user set info=?,major=?where name=?");
			ps.setString(1, info);
			ps.setString(2, major);
			ps.setString(3, name);
			if (ps.executeUpdate() == 1) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}

	// Update查询
	public ArrayList search(String name) {
		ArrayList al = new ArrayList();
		try {
			ps = this.getConnection().prepareStatement("SELECT name,info,major FROM USER where name=? ");
			ps.setString(1, name);
			ps.executeQuery();
			rs = ps.executeQuery();
			while (rs.next()) {
				User uc = new User();
				uc.setName(rs.getString(1));
				uc.setInfo(rs.getString(2));
				uc.setMajor(rs.getString(3));
				al.add(uc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return al;
	}

	// 关闭资源
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 增加用户
	public boolean add(String name, String password, String info, String major) {
		boolean b = false;
		try {
			ps = this.getConnection().prepareStatement("insert into user values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, info);
			ps.setString(4, major);
			if (ps.executeUpdate() == 1) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	// 删除用户
	public boolean del(String name) {
		boolean b = false;
		try {
			ps = this.getConnection().prepareStatement("delete from user where name=?");
			ps.setString(1, name);
			if (ps.executeUpdate() == 1) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}

	// 查找用户（精确）
	public ArrayList lookFor(String name) {
		ArrayList al = new ArrayList();
		try {
			ps = this.getConnection().prepareStatement("select name,info,major from user where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				user.setInfo(rs.getString(2));
				user.setMajor(rs.getString(3));
				al.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
}
