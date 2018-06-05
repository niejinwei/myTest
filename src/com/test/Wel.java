package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaBean.User;
import com.javaBean.UserCl;

/**
 * Wel
 */
public class Wel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResultSet rs = null;

	public Wel() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String spageNow = request.getParameter("pageNow");
		HttpSession hs = request.getSession(true);
		username = (String) hs.getAttribute("username");
		password = (String) hs.getAttribute("password");
		if (new UserCl().checkUser(username, password)) {
			int pageNow = 1;
			int pageSize = 3;
			int rowCount = 0;
			int pageCount = 5;
			ArrayList al = null;
			if (spageNow != null) {
				pageNow = Integer.parseInt(spageNow);
			}
			UserCl uc = new UserCl();
			al = uc.dividePage(pageNow, pageSize);
			pw.println("<center>");
			pw.println("<h1>管理用户</h1>");
			pw.println("<table border=1");
			pw.println("<tr><th>name</th><th>info</th><th>major</th><th>修改用户</th><th>删除用户</th></tr>");
			for (int i = 0; i < al.size(); i++) {
				User user = (User) al.get(i);// 将ArrayList中的数据转化为bean对象进行取出
				pw.println("<tr>");
				pw.println("<td>" + user.getName() + "</td>");
				pw.println("<td>" + user.getInfo() + "</td>");
				pw.println("<td>" + user.getMajor() + "</td>");
				pw.println("<td><a href=Update?name=" + user.getName() + ">修改用户</a></td>");
				pw.println("<td><a href=DelCl?name=" + user.getName() + ">删除用户</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			// 获得rowCount
			try {
				rs = uc.getConnection().prepareStatement("select count(*) from user").executeQuery();
				while (rs.next()) {
					rowCount = rs.getInt(1);
				}
				if (rowCount % pageSize == 0) {//计算分页数
					pageCount = rowCount / pageSize;
				} else {
					pageCount = rowCount / pageSize + 1;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 上一页
			if (pageNow != 1) {
				pw.println("<a href=Wel?pageNow=" + (pageNow - 1) + ">上一页</a>");
			}
			for (int i = 1; i <= pageCount; i++) {
				pw.println("<a href=Wel?pageNow=" + i + ">" + i + "</a>");
			}
			// 下一页
			if (pageNow < pageCount) {
				pw.println("<a href=Wel?pageNow=" + (pageNow + 1) + ">下一页</a>");
			}
			pw.println("<br><a href=Major>返回主菜单</a>");
			pw.println("</center>");
		} else {
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
