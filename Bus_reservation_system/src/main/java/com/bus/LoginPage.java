package com.bus;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginForm")
public class LoginPage extends HttpServlet {

	String ticket_rat;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html"); 
        
		
		String busno = req.getParameter("busno");
		String busName = req.getParameter("busname");
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String start_time = req.getParameter("bording");
		  
		String date= req.getParameter("date");
		
		System.out.println("date : "+date);
		System.out.println("ticketRate: "+ticket_rat);
		
		if(busno!=null) {
			 ticket_rat = req.getParameter("ticket_rate");
		HttpSession session= req.getSession();
		
		session.setAttribute("date11", date);
		session.setAttribute("busno", busno);
		session.setAttribute("busname", busName);
		session.setAttribute("from", from);
		session.setAttribute("to", to);
		session.setAttribute("start_time", start_time);
		}
		System.out.println("welcome to login form");
		
        PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Login</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: gray;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    height: 70vh;");
        out.println("}");
        out.println(".container {");
        out.println("    background-color: #fff;");      
        out.println("    padding: 50px;");
        out.println("    border-radius: 5px;");
        out.println("    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);");
        out.println("    width: 40%;");
        out.println("    height: 40%;");
        out.println("}");
        out.println("h2 {");
        out.println("    text-align: center;");
        out.println("    margin-bottom: 20px;");
        out.println("}");
        out.println("input[type=\"text\"],");
        out.println("input[type=\"password\"],");
        out.println("input[type=\"submit\"] {");
        out.println("    width: 100%;");
        out.println("    padding: 10px;");
        out.println("    margin-bottom: 15px;");
        out.println("    border: 1px solid #ccc;");
        out.println("    border-radius: 5px;");
        out.println("    box-sizing: border-box;");
        out.println("}");
        out.println("input[type=\"submit\"] {");
        out.println("    background-color: #007bff;");
        out.println("    color: #fff;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("input[type=\"submit\"]:hover {");
        out.println("    background-color: #0056b3;");
        out.println("}");
        out.println("a {");
        out.println("   padding:20px;");
        out.println("   float:right");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h2>Login Form</h2>");
        out.println("<form action=\"log\">");
        out.println("<input type='hidden' name='ticketRate' value=" + ticket_rat + ">");
        
        out.println("<input type=\"text\" name=\"username\" placeholder=\"Username\" required>");
        out.println("<input type=\"password\" name=\"password\" placeholder=\"Password\" required>");
        out.println("<input type=\"submit\" value=\"Login\">");
        out.print("<a   href='register.html'>Register</a>");
       out.print("<a  href='index.html'>SearchBus</a>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    
	}
}
