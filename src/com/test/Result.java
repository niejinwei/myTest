package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaBean.User;
import com.javaBean.UserCl;

/**
 * Servlet implementation class Result
 */
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Result() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		PrintWriter pw = response.getWriter();
		//使用Session中存放的数据进行验证，用户登录成功，会在Session中添加username，password属性和值，判断用户是否为非正常访问
		HttpSession hs = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		username = (String) hs.getAttribute("username");
		password = (String) hs.getAttribute("password");
		if (new UserCl().checkUser(username, password)) {
			ArrayList al = null;
			UserCl uc = new UserCl();
			al = uc.lookFor(name);
			pw.println("<center>");
			pw.println("<table border=1");
			pw.println("<tr><th>name</th><th>info</th><th>major</th></tr>");
			for (int i = 0; i < al.size(); i++) {
				User user = (User) al.get(i);// 将ArrayList中的数据转化为bean对象进行取出
				pw.println("<tr>");
				pw.println("<td>" + user.getName() + "</td>");
				pw.println("<td>" + user.getInfo() + "</td>");
				pw.println("<td>" + user.getMajor() + "</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<a href=Major>返回主菜单</a>");
			pw.println("<a href=LookFor>继续操作</a>");
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
