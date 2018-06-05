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
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		PrintWriter pw = response.getWriter();
		HttpSession hs = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		username = (String) hs.getAttribute("username");
		password = (String) hs.getAttribute("password");
		if (new UserCl().checkUser(username, password)) {
			UserCl uc = new UserCl();
			ArrayList al = uc.search(name);
			User user = (User) al.get(0);
			pw.println("<center>");
			pw.println("<form action=UpdateCl >");
			pw.println("<input type=text name=message value='个人信息'><br>");
			pw.println("<input type=text name=name value=" + user.getName() + "><br>");
			pw.println("<input type=text name=info value=" + user.getInfo() + "><br>");
			pw.println("<input type=text name=major value=" + user.getMajor() + "><br>");
			pw.println("<input type=submit name=update value=修改><br>");
			pw.println("</form>");
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
