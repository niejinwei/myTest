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
 * Add
 */
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Add() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//使用Session中存放的数据进行验证，用户登录成功，会在Session中添加username，password属性和值，判断用户是否为非正常访问
		HttpSession hs = request.getSession(true);
		username = (String) hs.getAttribute("username");
		password = (String) hs.getAttribute("password");
		if (new UserCl().checkUser(username, password)) {
			pw.println("<center>");
			pw.println("<h1>添加用户</h1>");
			pw.println("<form action=AddCl>");
			pw.println("<input type=text name=message value='个人信息'><br>");
			pw.println("<input type=text name=name value=><br>");
			pw.println("<input type=password name=password value=><br>");
			pw.println("<input type=text name=info value=><br>");
			pw.println("<input type=text name=major value=><br>");
			pw.println("<input type=submit name=update value=添加><br>");
			pw.println("<a href=Major>返回主菜单</a>");
			pw.println("</form>");
			pw.println("</center>");
		} else {
			response.sendRedirect("Login");//验证不通过
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
