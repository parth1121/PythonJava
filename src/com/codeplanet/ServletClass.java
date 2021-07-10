package com.codeplanet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletClass extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		//String val = req.getParameter("psw-repeat");
		String val1 = req.getParameter("email");
		String val2 = req.getParameter("psw");
		System.out.print(val1 + "  "+ val2);
		req.setAttribute("email", val1);
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application", "root", "password");
			//Statement st = con.createStatement();
			//String query1 = "Select * from user where email ='" + val1 +"'";
			PreparedStatement pst = con.prepareStatement("Select * from user where email =?");
			pst.setString(1, val1);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("password"));
//				
//				if(rs.getString("password").equals(val2)) {
//					
//				}
//				else {
//					your pwd is not correct
//				}
//			}
//			else {
//			  String query = "insert into user values('"+val1+"','"+val2+ "')";
//			  int x = st.executeUpdate(query);
//			   if(x== 1) {
//				   signedup successfully
//			   }
//			   else {
//				   some error occured
//			   }
//				   
		} 
	    CallableStatement cst = con.prepareCall("{call example(?, ?)}");
	    cst.setString(1, val1);
	    cst.setString(2, val2);
		int x = cst.executeUpdate();
        System.out.println(x);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("First.jsp");
		dispatcher.forward(req, res);
		}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String val = req.getParameter("psw-repeat");
		String val1 = req.getParameter("email");
		String val2 = req.getParameter("psw");
		System.out.print(val1 + "  "+ val2);
		PrintWriter out = res.getWriter();
		System.out.print(val1 + "  "+ val2); 

		out.print("<h1>HelloWelcome " +val + "ToCodePlanet post request</h1>");
		out.close();
	}	 
	}


