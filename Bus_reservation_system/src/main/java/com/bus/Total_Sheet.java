package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/total_sheet")
public class Total_Sheet extends HttpServlet {

	int totalPay=0;
	int totalsheet = 1;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		res.setContentType("text/html");

		 
		String ticket_rat = req.getParameter("ticket_rate");
		String mobile = req.getParameter("mobile");
		String gmail = req.getParameter("email");
       
		 
		HttpSession session = req.getSession(false);
		
		if(mobile!=null&&gmail!=null) {
			session.setAttribute("mobile", mobile);
			session.setAttribute("mail", gmail);
		}
	 
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gander = req.getParameter("gander");

		
		 
		 String moreSheet = req.getParameter("check");
		 
		 if(moreSheet.equals("bookTicket")) {
			 totalPay=0;
			 totalsheet=1;
			 new Arraylist().remove();
		 }
		 else if(moreSheet.equals("addTicket")) {
			 totalPay=0;
			 totalsheet=2;
			 new Arraylist().remove();
			  		 }
		 new Arraylist(("Name: "+name), ("Gander: "+gander), ("Age: "+age));
		 
		if (Integer.parseInt(age) < 10) {
			totalPay += Integer.parseInt(ticket_rat) / 2;
		} else if (gander.equals("female")) {
			totalPay += Integer.parseInt(ticket_rat) / 2;
		} else {
			totalPay += Integer.parseInt(ticket_rat);
		}
		
		session.setAttribute("totalpay", totalPay);
		session.setAttribute("totalsheet", totalsheet);
		
		System.out.println("totalPay: "+totalPay);
		 
		System.out.println("totalsheet :"+totalsheet);

		if(moreSheet.equals("addTicket")) {
			req.getRequestDispatcher("moresheet").forward(req, res);
		}
		else if (moreSheet.equals("addTicket1")) {
			totalsheet++;
			req.getRequestDispatcher("moresheet").forward(req, res);
			 } 
		else {
			 session.setAttribute( "otp", "sendotp");
			 
			out.println("<h2 style='margin-left:30%; margin-top:5%; color:red;'>pay " + totalPay + "/-</h2>");
			req.getRequestDispatcher("payment.html").include(req, res);
		 

		}

	}

}
