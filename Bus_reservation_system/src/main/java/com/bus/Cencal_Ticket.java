package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cencal")
public class Cencal_Ticket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("cencal ticket");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		ServletContext context = req.getServletContext();
		Connection con = (Connection) context.getAttribute("con");

		HttpSession session = req.getSession(false);
		String user = (String) session.getAttribute("session_name");
		session.setAttribute("otp", "sendcencalotp");

		req.getRequestDispatcher("mainpage.html").include(req, res);

		Date dd = new Date();
		try {

			PreparedStatement ps = con.prepareStatement("select * from bookinghistory where user=? ;");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Cencal Ticket</title>");
			out.println("<style>");
			out.println("table {");
			out.println("     margin-left: auto; ");
			out.println("     margin-right: auto; ");
			out.println("    width: 50%;");
			out.println("    border-collapse: collapse;");
			out.println("}");

			out.println("th, td {");
			out.println("    border: 1px solid #dddddd;");
			out.println("    text-align: left;");
			out.println("    padding: 8px;");
			out.println("}");

			out.println("th {");
			out.println("    background-color: #f2f2f2;");
			out.println("}");

			out.println("h2 {");
			out.println("    color: red;");
			out.println("   text-align: center;");
			out.println("}");

			out.println("button {");
			out.println("    background-color: red;");
			out.println("    color: black;");
			out.println("    padding: 10px 20px;");
			out.println("    text-align: center;");
			out.println("    text-decoration: none;");
			out.println("    display: inline-block;");
			out.println("    font-size: 16px;");
			out.println("    margin: 4px 2px;");
			out.println("    cursor: pointer;");
			out.println("    border-radius: 5px;");
			out.println("}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");

			out.print("<h2>Cencal Ticket</h2>");
			out.print("<br><br>");
			// Table header
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>Booking Id</th>");
			out.println("<th>Bus No.</th>");
			out.println("<th>Bus Name</th>");
			out.println("<th>From</th>");
			out.println("<th>To</th>");
			out.println("<th>Amount</th>");
			out.println("<th>Journey Date</th>");
			out.println("<th>Total Sheet</th>");
			out.println("<th>Mobile</th>");
			out.println("<th>Bording Time</th>");
			out.println("<th>Cencal Ticket</th>");
			out.println("</tr>");

			while (rs.next()) {

				Date d = rs.getDate("journeydate");

				if (d.after(dd)) {
					String bookingid = rs.getString("bookingid");
					String bus_no = rs.getString("bus_NO");
					String bus_name = rs.getString("bus_name");
					String from = rs.getString("fromm");
					String to = rs.getString("too");
					String amount = rs.getString("amount");
					String jdate = rs.getString("journeydate");
					String sheet = rs.getString("totalsheet");
					String mobile = rs.getString("mobileno");
					String BordingTime = rs.getString("bording_time");
					String gmail = rs.getString("gmail");

					out.println("<tr>");
					out.println("<td>" + bookingid + "</td>");
					out.println("<td>" + bus_no + "</td>");
					out.println("<td>" + bus_name + "</td>");
					out.println("<td>" + from + "</td>");
					out.println("<td>" + to + "</td>");
					out.println("<td>" + amount + "</td>");
					out.println("<td>" + jdate + "</td>");
					out.println("<td>" + sheet + "</td>");
					out.println("<td>" + mobile + "</td>");
					out.println("<td>" + BordingTime + "</td>");
					out.println("<td><form action ='cencalotp' method='post'>");
					out.println("<input type='hidden' name ='bookingid' value='" + bookingid + "'>");
					out.println("<input type='hidden' name ='gmail' value='" + gmail + "'>");
					out.println("<button type='submit' name='button' value='cencal' >cencal</button>");
					out.println("</form></td>");
					out.println("</tr>");

				}
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {

		}

	}
}
