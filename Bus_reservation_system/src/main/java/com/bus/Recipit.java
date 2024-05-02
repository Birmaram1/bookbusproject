package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ricipt")
public class Recipit extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		ServletContext context = req.getServletContext();
		Connection con = (Connection)context.getAttribute( "con");

		String mailotp = String.valueOf(req.getParameter("mailotp"));
		String randotp = String.valueOf(req.getParameter("randotp"));

		HttpSession session = req.getSession(false);

		String user = (String) session.getAttribute("session_name");
		String busno = (String) session.getAttribute("busno");
		String busname = (String) session.getAttribute("busname");
		String from = (String) session.getAttribute("from");
		String to = (String) session.getAttribute("to");
		String totalSheet = String.valueOf(session.getAttribute("totalsheet"));
		String totaolAmount = String.valueOf(session.getAttribute("totalpay"));
		String gmail = (String) session.getAttribute("mail");
		String mobile = (String) session.getAttribute("mobile");
		String startTime = (String) session.getAttribute("start_time");
		 

		String date = (String) (session.getAttribute("date11"));
		System.out.println("date :" + date);

		Arraylist list = new Arraylist(); // see Arraylist class (in com.bus package)
		String arr[] = list.show();
		
		//generate booking id
		 String bookingId="";
		Random rand= new Random();
        while(bookingId.length()!=10) {
	      long d= rand.nextInt(1,10);
	      bookingId+= String.valueOf(d);
	      }

		System.out.println("ricipt servlet 1");
		if (randotp.equals(mailotp)) {
			session.removeAttribute( "otp");
			out.print("<form style=border=\"1\" align=\"center\" font-size=\"10px\" width=\"60%\" hight=\"70%;\" action='pdf' method='post'> ");
			out.print("<h1>BookingId : "+bookingId+" </h1>");
			out.print("<td><label> BusNo :" + busno + " </label</td>");
			out.print("<td><label> BusName : " + busname + " </label</td>");
			out.print("<td><label> From : " + from + " </label</td>");
			out.print("<td><label> To :" + to + " </label</td>");
			out.print("<br><br>");
			out.print("<td><label> Journey :" + date + " </label</td>");
			out.print("<td><label> BordingTime :" + startTime + " </label</td>");
			out.print("<td><label> Sheet : " + totalSheet + " </label</td>");
			out.print("<td><label> Ammount :" + totaolAmount + " </label</td>");
			out.print("<br><br>");

			for (int i = 0; i < arr.length; i++) {
				 
				out.print("<td><td><td><label style=width=\"20%;\">" + arr[i] + "</label</td></td></td>");
				System.out.println(arr[i]);
				if (i == 2 ||i==5||i==8||i==11||i==15||i==17||i==20||i==23||i==26||i==29) {
					out.print("<br><br>");
				}
			}
			 
			out.print("</form>");
			out.print("<a style='margin-left:60% color:green;' href='mainpage.html'>home</a>");
			
			list.remove();   // booking ka data clear kr ega  [see Arraylist class (in com.bus package)]
			try {
				  
				Date datetime= new Date();
				Timestamp timestamp = new Timestamp(datetime.getTime());
				PreparedStatement ps = con.prepareStatement( "insert into bookinghistory values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				 
				ps.setString(1, bookingId);
				ps.setString(2, busno);
				ps.setString(3, busname);
				ps.setString(4, from);
				ps.setString(5, to);
				ps.setString(6, date);
				ps.setString(7, totalSheet);
				ps.setTimestamp(8, timestamp);
				ps.setString(9, mobile);
				ps.setString(10, user);
				ps.setString(11, totaolAmount);
				ps.setString(12, startTime);
				ps.setString(13, gmail);
				
				
			int check=	ps.executeUpdate();
			
			if(check>0) { 
				 System.out.println("data saved  in database");
			}
			else {
				 System.out.println("data not saved");
			}
			} 
			catch (Exception e) {
				 System.out.println("exception "+e.getMessage());
			}

		} else {
			out.print("<h3 style='margin-left:25%;'>enter correct otp: </h3>" + "<br>");
			req.getRequestDispatcher("otp").include(req, res);
			session.setAttribute( "otp","resendotp");
		}

	}
}
