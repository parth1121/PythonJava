package com.codeplanet;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forget")
public class ForgetPassword extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
	  String email = req.getParameter("email");
	  SendMail mail = new SendMail();
	  SecureRandom random = new SecureRandom();
	  int num = random.nextInt(100000);
	  String str = String.format("%05d", num);
	  mail.send(email, "Forget Passowrd Email", "Your Otp is "+str);
	  Connection con = null;
	  try {
	  Class.forName("com.mysql.jdbc.Driver");
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application", "root", "password");
		//Statement st = con.createStatement();
		//String query1 = "Select * from user where email ='" + val1 +"'";
		PreparedStatement pst = con.prepareStatement("update user set otp =? where email =?");
		pst.setString(1, str);
		pst.setString(2, email);
		pst.executeUpdate();
	  } catch(Exception ex) {
		  
	  }
	  req.setAttribute("email",email);
	  RequestDispatcher dispatcher = req.getRequestDispatcher("updatepassword.jsp");
	  dispatcher.forward(req, res);
	  
	}

}
