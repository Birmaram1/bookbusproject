package com.bus;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search&book")
public class Searchbus_bookTicket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		req.getRequestDispatcher("mainpage.html").include(req, res);

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Bus Reservation Form</title>");
		out.print(" <script src='https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js'></script>");
		out.println("<style>");
		out.println("form {");
		out.println("    margin-left: 30%;");
		out.println("    margin-top: 5%;");
		out.println("    padding: 20px;");
		out.println("    border: 1px solid #ccc;");
		out.println("    border-radius: 5px;");
		out.println("    width: 500px;");
		out.println("}");

		out.println("input[type=text], input[type=date] {");
		out.println("    width: 100%;");
		out.println("    padding: 10px;");
		out.println("    margin: 5px 0;");
		out.println("    display: inline-block;");
		out.println("    border: 1px solid #ccc;");
		out.println("    border-radius: 4px;");
		out.println("    box-sizing: border-box;");
		out.println("}");

		out.println("input[type=submit] {");
		out.println("    background-color: #4CAF50;");
		out.println("    color: white;");
		out.println("    padding: 14px 20px;");
		out.println("    margin: 8px 0;");
		out.println("    border: none;");
		out.println("    border-radius: 4px;");
		out.println("    cursor: pointer;");
		out.println("}");

		out.println("</style>");
		out.println("</head>");
		out.println("<body>");

		// Form
		out.println("<form action=\"search\" method=\"post\">");
		out.println("<label for='from'>From:</label>");
		out.println("<input type='text' id='from' name='start1' placeholder='Enter source location' required><br>");

		out.println("<label for='to'>To:</label>");
		out.println("<input type='text' id='to' name='end1' placeholder='Enter destination location' required><br>");

		out.println("<label for='date'>Date:</label>");
		out.println("<input type='date' id='inputdate' name='date1' required>");

		out.print(" <script type='text/javascript'>"
				+ "$(function(){ var dtToday = new Date(); var month = dtToday.getMonth() + 1;  var day = dtToday.getDate();  var year = dtToday.getFullYear();  if(month < 10)   month = '0' + month.toString();  if(day < 10) day = '0' + day.toString();  var maxDate = year + '-' + month + '-' + day;  $('#inputdate').attr('min', maxDate); });"
				+ " </script>");

		out.println("<br><br>");

		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
	}
}
