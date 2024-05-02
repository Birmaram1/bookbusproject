package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/history")
public class ViewHistory extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		ServletContext context = req.getServletContext();
		Connection con = (Connection) context.getAttribute("con");
		req.getRequestDispatcher( "mainpage.html").include(req, res);
		
		HttpSession session =req.getSession(false);
		String user = (String) session.getAttribute("session_name");

		try {
		PreparedStatement ps = con.prepareStatement("select * from bookinghistory where user=?;");
		ps.setString(1, user);
		ResultSet rs = ps.executeQuery();

		 

		out.println("<html><head><title>Booking History</title></head><body>");
		out.print("<form action='login.html' >");
		out.println("<h2 style='text-align:center' >Booking History</h2>");
		out.println("<table border=\"1\" align=\"center\" width=\"60%\" hight=\"70%\">");
		out.println("<tr>");
		out.println("<th>Booking Id</th>");
		out.println("<th>Bus No.</th>");
		out.println("<th>Bus Name</th>");
		out.println("<th>From</th>");
		out.println("<th>To</th>");
		out.println("<th>Journey Date</th>");
		out.println("<th>Book Sheet</th>");
		out.println("<th>Mobile No.</th>");
		out.println("<th>Booking Time</th>");
		out.println("</tr>");

		 
		while (rs.next()) {
			
			   String bookingid = rs.getString("bookingid");
				String bus_no = rs.getString("bus_NO");
				String bus_name = rs.getString("bus_name");
				String from = rs.getString("fromm");
				String to = rs.getString("too");
				String jdate = rs.getString("journeydate");
				String sheet = rs.getString("totalsheet");
				String btime = rs.getString("bookingtime");
				String mobile = rs.getString("mobileno");
 
				out.println("<tr>");
				out.println("<td>" + bookingid + "</td>");
				out.println("<td>" + bus_no + "</td>");
				out.println("<td>" + bus_name + "</td>");
				out.println("<td>" + from + "</td>");
				out.println("<td>" + to + "</td>");
				out.println("<td>" + jdate + "</td>");
				out.println("<td>" + sheet + "</td>");
				out.println("<td>" + mobile + "</td>");
				out.println("<td>" + btime + "</td>");
			  
				out.println("</tr>");
				 
			}
		
		out.println("</table>");
		out.print("</form>");
		out.println("</body></html>");

		 

		  

	} catch (Exception e) {

		e.printStackTrace();
         System.out.println("exception error :"+e.getMessage());
         
		 
	}
	}
}
