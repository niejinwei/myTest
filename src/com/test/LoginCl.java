package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaBean.UserCl;

/**
 * 验证登录名，密码，实现跳转
 */
public class LoginCl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginCl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		String xh = null, xm = null;
		if (new UserCl().checkUser(username, password)) {
			response.sendRedirect("Major?username=" + username);
			hs.setAttribute("username", username);
			hs.setAttribute("password", password);
		} else {
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
