package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cencalotp")
public class CencalOtp extends HttpServlet {

	String msg = "";
	String replace = "";
	String bookingId = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		HttpSession session = req.getSession(false);

		if (session.getAttribute("otp").equals("sendcencalotp")) {

			bookingId = req.getParameter("bookingid");

			String subject = "booking cencal otp";
			String gmail = req.getParameter("gmail");

			String to = gmail;
			Random rand = new Random();

			while (msg.length() != 6) {
				long d = rand.nextInt(0, 10);
				msg += String.valueOf(d);
			}
			Mailer.send(to, subject, msg); // otp sending to gmail
			replace = gmail.replace(gmail.substring(2, gmail.length() - 4), "xxxxx");

			System.out.println(" ticket cencal otp: " + msg);

		}

		out.print("<html>");
		out.print("<head>");
		out.print("<title>Enter OTP</title>");
		out.print("<style>");
		out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4;}");
		out.print(".container { width: 50%; margin: 0 auto; text-align: center;}");
		out.print(
				".form-container { background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);}");
		out.print(".form-container label { display: block; margin-bottom: 10px;}");
		out.print(
				".form-container input[type='text'] { width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 5px;}");
		out.print(
				".form-container input[type='submit'] { background-color: #4CAF50; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;}");
		out.print("</style>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div class='container'>");
		out.print("<div class='form-container'>");
		out.print("<form action='confirmcencal' method='post'>");
		out.print("<input type='hidden' name='randotp' value=" + msg + ">");
		out.print("<input type='hidden' name='bookingid' value=" + bookingId + ">");
		out.println("<label>otp sent by this gmail:" + replace + "</label>");
		out.print("<label>Enter OTP:</label>");
		out.print("<input type='text' name='mailotp' required>");
		out.print("<input type='submit' value='OK'>");
		out.print("</form>");
		out.print("</div>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

	public void otp() {

	}
}
