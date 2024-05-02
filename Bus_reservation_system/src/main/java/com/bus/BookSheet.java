package com.bus;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/booksheet")
public class BookSheet extends HttpServlet {

	String ticket_rat;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session= req.getSession(false);
		
        if(req.getParameter("ticketRate")!=null) {
        	
        	ticket_rat=req.getParameter("ticketRate");
        	
        	System.out.println(session.getAttribute("busno"));
        	System.out.println("tikcket rate: "+ticket_rat);
        	
    		 
        }else {
        	String busno = req.getParameter("busno");
    		String busName = req.getParameter("busname");
    		String from = req.getParameter("from");
    		String to = req.getParameter("to");
    		String start_time = req.getParameter("bording");
    		  ticket_rat = req.getParameter("ticket_rate");
    		String date= req.getParameter("date");
    		
    		System.out.println("date : "+date);
    		
    		
    		
    		session.setAttribute("date11", date);
    		session.setAttribute("busno", busno);
    		session.setAttribute("busname", busName);
    		session.setAttribute("from", from);
    		session.setAttribute("to", to);
    		session.setAttribute("start_time", start_time);
    		
    		System.out.println(busno);
    		 
        }
		

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Form Example</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
		out.println("<style>");
		out.println(".container{");
		out.println("    margin-left: 30%;");
		out.println("    font-size: 20px;");
		out.println("    margin-top: 5%;");
		out.println("    padding: 20px;");
		out.println("    border: 1px solid #ccc;");
		out.println("    border-radius: 5px;");
		out.println("    width: 400px;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");

		out.println("<div class=\"container\">");
		out.println("<h2>Enter Detials</h2>");
		out.println("<form action= 'total_sheet' >");
		out.print("TicketRate: " + "<h4 style='color:green;'>" + ticket_rat + "/-</h4>");
		out.println("<br>");
		out.print("<h6 style='color:red;'>if age is less than 10 or gander is female discount 50%</h6>");
		out.println("<br>");
		out.print("<input type='hidden' name='ticket_rate' value=" + ticket_rat + ">"); 
		out.println("<label for=\"name\">Name:</label>");
		out.println("<input type=\"text\" id=\"name\"  name=\"name\" required><br><br>");
		out.println("<label for=\"age\">Age:</label>");
		out.println("<input type=\"text\"  title=\"enter age in 2 digits\"  pattern=\"[0-9]{2}\" maxlength=\"2\"   name=\"age\" required><br><br>");
		out.println("<label for=\"mobile\">Mobile:</label>");
		out.println("<input type=\"tel\" name=\"mobile\" id=\"phoneNumber\" title=\"Please use a 10 digit telephone number with no dashes or dots\" pattern=\"[0-9]{10}\" maxlength=\"10\" <br><br>");
		out.println("<label for=\"email\">Email:</label>"); 
		out.println("<input type=\"email\" id=\"email\" name=\"email\" required><br><br>"); 
		out.println("Male:" + "<input type=\"radio\" id=\"gander1\" name=\"gander\" value=\"male\" required><td> ");
		out.println("famle:" + "<input type=\"radio\" id=\"gander1\" name=\"gander\" value=\"female\" required><td> ");
		out.println("Other:" + "<input type=\"radio\" id=\"gander1\" name=\"gander\" value=\"other\" required><td> ");
		out.println("<br><br>");
		out.println("<label for=\"name\">do you more sheet book:</label>");
		out.println("<input type=\"submit\"   name=\"check\" value=\"addTicket\"><br><br>");
		out.println("<input  style='color:green;' type=\"submit\" name=\"check\" value='bookTicket'>");
		out.println("</form>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");

		

	}

}
