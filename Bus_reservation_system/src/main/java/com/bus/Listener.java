package com.bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
  
	Connection con=null;
    public void contextInitialized(ServletContextEvent sce)  { 
          
    	try {
    		
    		Class.forName( "com.mysql.cj.jdbc.Driver");
			  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus","root","root");

			  ServletContext context = sce.getServletContext();
			  context.setAttribute( "con",  con);
			  System.out.println("sddkjjnsdf");
		} catch (Exception e) {
			 System.out.println("listener Exception   "+e.getMessage());
		}
    }

	 
    public void contextDestroyed(ServletContextEvent sce)  { 
         try {
			con.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
    }
	
}
