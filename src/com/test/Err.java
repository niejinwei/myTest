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
 * Servlet implementation class Err
 */
public class Err extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Err() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//使用Session中存放的数据进行验证，用户登录成功，会在Session中添加username，password属性和值，判断用户是否为非正常访问
		HttpSession hs = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		username = (String) hs.getAttribute("username");
		password = (String) hs.getAttribute("password");
		if (new UserCl().checkUser(username, password)) {
			pw.println("<h1>很遗憾，操作不成功！</h1>");
			pw.println("<a href=Major>返回主界面</a>");
			pw.println("<a href=Wel>继续删除</a>");
		} else {
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
