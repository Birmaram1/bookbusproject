package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchBus extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		RequestDispatcher rd = null;
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String date = req.getParameter("date");

		String start1 = req.getParameter("start1");
		String end1 = req.getParameter("end1");
		String date1 = req.getParameter("date1");

		if (start1 != null && end1 != null && date1 != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				// Parse string to LocalDate
				LocalDate date11 = LocalDate.parse(date1, formatter);
				DayOfWeek dayOfWeek = date11.getDayOfWeek();

				String week = dayOfWeek.toString();

				String day = "";
				switch (week) {

				case "SUNDAY":
					day = "0";
					break;
				case "MONDAY":
					day = "1";
					break;
				case "TUESDAY":
					day = "2";
					break;
				case "WEDNESDAY":
					day = "3";
					break;
				case "THURSDAY":
					day = "4";
					break;
				case "FRIDAY":
					day = "5";
					break;
				case "SATURDAY":
					day = "6";
					break;
				}

				ServletContext context = req.getServletContext();
				Connection con = (Connection) context.getAttribute("con");

				PreparedStatement ps = con.prepareStatement("select * from search_bus where fromm=? and too=?;");
				ps.setString(1, start1);
				ps.setString(2, end1);
				ResultSet rs = ps.executeQuery();

				System.out.println(dayOfWeek);

				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Bus Information</title>");
				out.println("<style>");
				out.println("table {");
				out.println("    width: 100%;");
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

				out.println("button {");
				out.println("    background-color: #4CAF50;");
				out.println("    color: white;");
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

				// Table header
				out.println("<table>");
				out.println("<tr>");
				out.println("<th>Bus No.</th>");
				out.println("<th>Bus Name</th>");
				out.println("<th>From</th>");
				out.println("<th>To</th>");
				out.println("<th>Bording</th>");
				out.println("<th>Droping</th>");
				out.println("<th>Via</th>");
				out.println("<th>Bus Type</th>");
				out.println("<th>BookBus</th>");
				out.println("</tr>");

				 
				while (rs.next()) {

					String days = String.valueOf(rs.getInt("days"));
					if (days.contains(day)) {
						 
						String bus_no = rs.getString("bus_NO");
						String bus_name = rs.getString("bus_name");
						String bus_via = rs.getString("via");
						String bus_type = rs.getString("bus_type");
						String start_point = rs.getString("fromm");
						String end_point = rs.getString("too");
						String bording = rs.getString("start_time");
						String droping = rs.getString("drop_time");
						int rupee = rs.getInt("ticket_rate");

						out.println("<tr>");
						out.println("<td>" + bus_no + "</td>");
						out.println("<td>" + bus_name + "</td>");
						out.println("<td>" + start_point + "</td>");
						out.println("<td>" + end_point + "</td>");
						out.println("<td>" + bording + "</td>");
						out.println("<td>" + droping + "</td>");
						out.println("<td>" + bus_via + "</td>");
						out.println("<td>" + bus_type + "</td>");
						out.println("<td>" + "<a href='booksheet?busno=" + bus_no + "&ticket_rate=" + rupee + "&from="
								+ start_point + "&to=" + end_point + "&busname=" + bus_name +"&date=" + date11 + "&bording=" + bording
								+ "'><button type=\"submit\">" + rupee + "</button></a>" + "</td>");
						out.println("</tr>");

						// System.out.println(start_point+" "+end_point+" "+rupee);
						//System.out.println(bus_name);
					}
				}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				
				
 

				out.print("<a style='padding-left:70%' href=\"mainpage.html\">back</a>");

			} catch (Exception e) {

				e.printStackTrace();

				out.print("exception occured :" + e.getMessage());
				rd = req.getRequestDispatcher("mainpage.html");
				rd.include(req, res);
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------
		else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				// Parse string to LocalDate
				LocalDate date11 = LocalDate.parse(date, formatter);
				DayOfWeek dayOfWeek = date11.getDayOfWeek();

				String week = dayOfWeek.toString();

				String day = "";
				switch (week) {

				case "SUNDAY":
					day = "0";
					break;
				case "MONDAY":
					day = "1";
					break;
				case "TUESDAY":
					day = "2";
					break;
				case "WEDNESDAY":
					day = "3";
					break;
				case "THURSDAY":
					day = "4";
					break;
				case "FRIDAY":
					day = "5";
					break;
				case "SATURDAY":
					day = "6";
					break;
				}

				ServletContext context = req.getServletContext();
				Connection con = (Connection) context.getAttribute("con");

				PreparedStatement ps = con.prepareStatement("select * from search_bus where fromm=? and too=?;");
				ps.setString(1, start);
				ps.setString(2, end);
				ResultSet rs = ps.executeQuery();

				System.out.println(dayOfWeek);

				out.println("<html><head><title>Bus Information</title></head><body>");
				out.println("<h2 style='text-align : center'>Bus Information</h2>");
				out.println("<table border=\"1\" align=\"center\" width=\"60%\" hight=\"70%\">");
				out.println("<tr>");
				out.println("<th>Bus No</th>");
				out.println("<th>Bus Name</th>");
				out.println("<th>From</th>");
				out.println("<th>To</th>");
				out.println("<th>Via</th>");
				out.println("<th>Rate</th>");
				out.println("<th>Bus Type</th>");
				out.println("<th>Start Time</th>");
				out.println("<th>Drop Time</th>");
				out.println("<th>book ticket</th>");

				out.println("</tr>");

				int count = 0;
				while (rs.next()) {

					String days = String.valueOf(rs.getInt("days"));
					if (days.contains(day)) {
						count++;
						String bus_no = rs.getString("bus_NO");
						String bus_name = rs.getString("bus_name");
						String bus_via = rs.getString("via");
						String bus_type = rs.getString("bus_type");
						String start_point = rs.getString("fromm");
						String end_point = rs.getString("too");
						String bording = rs.getString("start_time");
						String drop_time = rs.getString("drop_time");

						int rupee = rs.getInt("ticket_rate");

						out.println("</tr>");
						out.println("<td>" + bus_no + "</td>");
						out.println("<td>" + bus_name + "</td>");
						out.println("<td>" + start_point + "</td>");
						out.println("<td>" + end_point + "</td>");
						out.println("<td>" + bus_via + "</td>");
						out.println("<td>" + rupee + "</td>");
						out.println("<td>" + bus_type + "</td>");
						out.println("<td>" + bording + "</td>");
						out.println("<td>" + drop_time + "</td>");

						 
						out.println("<td>" + "<a href='loginForm?busno=" + bus_no + "&ticket_rate=" + rupee + "&from="
								+ start_point + "&to=" + end_point + "&busname=" + bus_name +"&date=" + date11 + "&bording=" + bording
								+ "'><button style='background:yellow;' type=\"submit\">" + rupee + "</button></a>" + "</td>");
						out.println("</tr>");
						// System.out.println(start_point+" "+end_point+" "+rupee);
					}
				}
				out.println("</table>");
				out.println("</body></html>");

				

				out.print("<a style='padding-left:70%' href=\"index.html\">back</a>");

			} catch (Exception e) {

				e.printStackTrace();

				out.print("exception occured :" + e.getMessage());
				rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);
			}
		}
	}
}
