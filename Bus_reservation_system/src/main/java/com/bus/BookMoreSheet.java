package com.bus;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/moresheet")
public class BookMoreSheet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		String ticket_rate=req.getParameter( "ticket_rate");
		
		response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Centered Form</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body {");
        response.getWriter().println("    display: flex;");
        response.getWriter().println("    justify-content: center;");
        response.getWriter().println("    align-items: center;");
        response.getWriter().println("    height: 100vh;");
        response.getWriter().println("}");
        response.getWriter().println(".container {");
        response.getWriter().println("    width: 300px;");
        response.getWriter().println("    padding: 20px;");
        response.getWriter().println("    border: 1px solid #ccc;");
        response.getWriter().println("    border-radius: 5px;");
        response.getWriter().println("}");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div class=\"container\">");
        response.getWriter().println("<form action=\"total_sheet\" >");
        response.getWriter().println("ticketrate :<h5 style='color:green;'>" + ticket_rate + "/-</h5>");
        response.getWriter().println("<input type='hidden' name='ticket_rate' value='"+ticket_rate+"'>");
        response.getWriter().println("<label for=\"name\">Name:</label>");
        response.getWriter().println("<input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
        response.getWriter().println("<label for=\"age\">Age:</label>");
        response.getWriter().println("<input type=\"text\"  title=\"enter age in 2 digits\"  pattern=\"[0-9]{2}\" maxlength=\"2\"   name=\"age\" required><br><br>");
        response.getWriter().println("Male:" + "<input type=\"radio\" id=\"gander1\" name=\"gander\" value=\"male\" required><td> ");
        response.getWriter().println("famle:" + "<input type=\"radio\" id=\"gander1\" name=\"gander\" value=\"female\" required><td> ");
        response.getWriter().println("Other:" + "<input type=\"radio\" id=\"gander1\" name=\"gander\" value=\"other\" required><td> ");
        response.getWriter().println("<br><br>");
        response.getWriter().println("<label for=\"name\">do you more sheet book:</label>");
        response.getWriter().println("<input type=\"submit\"name=\"check\" value=\"addTicket1\"><br><br>");
        response.getWriter().println("<input  style='color:green;' type=\"submit\" name=\"check\" value='bookTicket1'>");
        response.getWriter().println("</form>");
        response.getWriter().println("</div>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
	}
}
