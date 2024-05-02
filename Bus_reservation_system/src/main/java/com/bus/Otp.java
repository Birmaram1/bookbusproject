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
 

@WebServlet("/otp")
public class Otp extends HttpServlet {

	  
	  String msg="";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");  
        PrintWriter out = res.getWriter();  
        
        
         
        
           
        
        HttpSession session = req.getSession(false);
        String to=(String) session.getAttribute("mail");
         
        if(session.getAttribute( "otp").equals("sendotp")) {
        Random rand= new Random();
        int a= rand.nextInt(11, 99)*999;
           msg= String.valueOf(a);
        
        String subject= " book sheet otp" ;
        Mailer.send(to, subject, msg);  //otp sending to gmail 
        
        System.out.println(msg);
        }
         
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Enter OTP</title>");
        out.print("<style>");
        out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4;}");
        out.print(".container { width: 50%; margin: 0 auto; text-align: center;}");
        out.print(".form-container { background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);}");
        out.print(".form-container label { display: block; margin-bottom: 10px;}");
        out.print(".form-container input[type='text'] { width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 5px;}");
        out.print(".form-container input[type='submit'] { background-color: #4CAF50; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;}");
        out.print("</style>");
        out.print("</head>");
        out.print("<body>");
        out.print("<div class='container'>");
        out.print("<div class='form-container'>");
        out.print("<form action='ricipt' method='post'>"); 
        out.print("<input type='hidden' name='randotp' value=" + msg + ">");
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
}
