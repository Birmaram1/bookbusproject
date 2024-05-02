package com.bus;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/account")
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        req.getRequestDispatcher("mainpage.html").include(req, res);

        ServletContext context = req.getServletContext();
        Connection con = (Connection) context.getAttribute("con");

        HttpSession session = req.getSession(false);
        String user = (String) session.getAttribute("session_name");

        try {
            PreparedStatement ps = con.prepareStatement("select *  from Registrarion where user=?;");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
            out.println("<style>");
            out.println(".abc{");
            out.println("    border: 1px solid #ccc;");
            out.println("    border-radius: 5px;");
            
            out.println("margin-left:30%;");
            out.println("margin-right:20%;");
            out.println("margin-top:5%;");
            out.println("text-align:center;");
            out.println("font-size:20px");
         
            
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='abc'>");
            while (rs.next()) {
                String u = rs.getString("user");
                String gmail = rs.getString("email");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                out.print("<h1>Welcome to : " + u + "</h1>");
                out.println("<hr>");
                out.println("<label >Gmail: " + gmail + "</label>");
                out.println("<hr>");
                out.println("<label>Mobile: " + mobile + "</label>");
                out.println("<hr>");
                out.println("<label>Address: " + address + "</label>");
                out.println("<hr>");
            }
            out.println("<a href='delete'>DeleteAccount</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            // Handle exceptions
        }
    }
}
