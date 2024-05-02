package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/confirmcencal")
public class ConfirmCencel extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		ServletContext context = req.getServletContext();
		Connection con = (Connection) context.getAttribute("con");
		HttpSession session = req.getSession(false);

		String mailotp = String.valueOf(req.getParameter("mailotp"));
		String randotp = String.valueOf(req.getParameter("randotp"));

		String bookingid1 = req.getParameter("bookingid");

		if (mailotp.equals(randotp)) {
			session.removeAttribute("otp");
			try {
				PreparedStatement ps = con.prepareStatement("delete  from bookinghistory where bookingid=?;");
				ps.setString(1, bookingid1);
				int i = ps.executeUpdate();
				if (i > 0) {
					res.sendRedirect("mainpage.html");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			session.setAttribute("otp", "resendotp");
			out.print("<h3 style='margin-left:25%;'>enter correct otp: </h3>" + "<br>");
			req.getRequestDispatcher("cencalotp").include(req, res);
		}
	}
}
