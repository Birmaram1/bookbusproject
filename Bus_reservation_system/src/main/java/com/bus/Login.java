package com.bus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 @WebServlet("/log")
public class Login extends HttpServlet {
	 
	 
	 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType( "text/html");
		PrintWriter out  = res.getWriter();
		RequestDispatcher rd =null;
		
		String user=req.getParameter( "username");
		String password=req.getParameter( "password");
		
		ServletContext context = req.getServletContext();
		Connection con = (Connection)context.getAttribute( "con");
	 
		try {
			PreparedStatement ps = con.prepareStatement( "select * from Registrarion where user=? and password=?");
			ps.setString(1, user);
			ps.setString(2, password);
			
             ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				System.out.println("login page");
				HttpSession  session = req.getSession();
			 session.setAttribute( "session_name",result.getString("user"));
			 
			 if(session.getAttribute("busno")!=null) {
				 rd=req.getRequestDispatcher("booksheet");
				 rd.forward(req, res);
			 }else {
				 
				 res.sendRedirect("mainpage.html");
				 
			 }
			 
				 
			}
			else  
			{  out.print( "<h3 style = 'padding-bottom: 20%;   color: red;'>please fill correct data</h3>");
				rd=req.getRequestDispatcher("loginForm");
				rd.include(req, res);
				
			}
			
		} catch (Exception e) {
			out.print( "sorry !! some error"+e.getMessage());
			rd=req.getRequestDispatcher("loginForm");
			rd.include(req, res);
			 
		}
	
	}

}
