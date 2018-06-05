package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaBean.UserCl;
/**
 * UpdateCl
 */
public class UpdateCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=utf-8");
	String name=request.getParameter("name");
	String info=request.getParameter("info");
	String major=request.getParameter("major");
	UserCl uc=new UserCl();
	if(uc.update(name, info, major)){
		response.sendRedirect("Ok");
	}else{
		response.sendRedirect("Err");
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
