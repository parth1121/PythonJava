package com.codeplanet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatepasswordusingotp")
public class UpdatePasswordOtp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		req=null;
	  String email = req.getParameter("email");
	  String otp = req.getParameter("otp");
	  String psw = req.getParameter("psw");
	  
	}

}
