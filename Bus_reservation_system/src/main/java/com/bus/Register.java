package com.bus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

 @WebServlet("/reg")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType( "text/html");
		PrintWriter out  = res.getWriter();
		RequestDispatcher rd =null;
		
		ServletContext context = req.getServletContext();
		Connection con = (Connection)context.getAttribute( "con");
		
		String myname = req.getParameter( "username");
		String mypass = req.getParameter( "password");
		String myemail = req.getParameter( "email");
		String mymob = req.getParameter( "mobile");
		String myadd = req.getParameter( "address");
		
		
		try {
			PreparedStatement ps = con.prepareStatement( "insert into Registrarion (user, password, email, mobile, address) values(?,?,?,?,?)");
			 
			ps.setString(1, myname);
			ps.setString(2, mypass);
			ps.setString(3, myemail);
			ps.setString(4, mymob);
			ps.setString(5, myadd);
		int check=	ps.executeUpdate();
		
		if(check>0) { 
			 out.print("successfully register");
			res.sendRedirect("loginForm");
			System.out.println("successfully register");
			 // rd=req.getRequestDispatcher("loginForm");
		      // rd.include(req, res);
		      
		}
		else {
			out.print( "<h3 style= 'text-align:center'>not register</h3>");
			rd=req.getRequestDispatcher("register.html");
		       rd.include(req, res);
		}
		} 
		catch (Exception e) {
			out.print( "<h3 style='margin-bottom:auto;  text-align:center; color:red;'>exception occured :  "+e.getMessage()+"</h3>");
			rd=req.getRequestDispatcher("register.html");
		       rd.include(req, res);
		}
	}
}
