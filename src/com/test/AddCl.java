package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaBean.UserCl;

/**
 * AddCl
 */
public class AddCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//使用Add中表单提交的数据
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String info=request.getParameter("info");
		String major=request.getParameter("major");
		UserCl uc=new UserCl();
		//传入UserCl中的add方法。
		if(uc.add(name, password, info, major)){
			response.sendRedirect("Ok");
		}else{
			response.sendRedirect("Err");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
