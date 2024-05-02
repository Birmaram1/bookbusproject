package com.bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.net.ssl.HttpsURLConnection;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteAccount extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		ServletContext context = req.getServletContext();
		Connection con = (Connection) context.getAttribute("con");

		HttpSession session = req.getSession(false);
		String user = (String) session.getAttribute("session_name");
		
		try {
			PreparedStatement ps = con.prepareStatement("delete from registrarion where user=?;");
			ps.setString(1, user);
			int i= ps.executeUpdate();
			if(i>0) {
				String deleteUser=user.concat( " delete");
				PreparedStatement pst = con.prepareStatement(" update bookinghistory set user=? where user=?;");
				pst.setString(1, deleteUser);
				pst.setString(2, user);
				
				int j=pst.executeUpdate();
				if(j>0) {
					System.out.println("data sucsessfully deleted");
					session.invalidate();
					res.sendRedirect("index.html");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
