package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaBean.UserCl;

/**
 * 主界面
 */
public class Major extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Major() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
		// response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=null;
		String password=null;
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		//使用Session中存放的数据进行验证，用户登录成功，会在Session中添加username，password属性和值，判断用户是否为非正常访问
		HttpSession hs = request.getSession(true);
		username = (String) hs.getAttribute("username");
		password = (String) hs.getAttribute("password");
		if (new UserCl().checkUser(username, password)) {
			pw.println("<img src=imgs/welcome.png width=100 height=100>");
			pw.println("<br><hr>");
			pw.println("<center>");
			pw.println("<h1 style='color:blue'>主界面</h1>");
			pw.println("<a href=Wel>管理用户</a></br>");
			pw.println("<a href=Add>添加用户</a></br>");
			pw.println("<a href=LookFor>查找用户</a></br>");
			pw.println("<a href=Login>安全退出</a></br>");
			pw.println("</center>");
			pw.println("<br><hr>");
			pw.println("<img src=imgs/logo.png width=200 height=100>");
		}else{
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
