package com.bus;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Mailer {  
	public static void send(String to,String subject,String msg){  
		  
		final String user="birmaram6498@gmail.com";//change accordingly  
		final String pass="vwsvvqckmmxfaamd";  
		  
		//1st step) Get the session object    
		Properties pro = new Properties();  
		pro.put("mail.smtp.auth",true);
		pro.put("mail.smtp.starttls.enable", true);
		pro.put("mail.smtp.port",587 );
		pro.put("mail.smtp.host","smtp.gmail.com");
		  
		Session instance=Session.getInstance(pro, new Authenticator()
		{
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,pass);
		}});
		//2nd step)compose message 
		try {  
		 Message message = new MimeMessage(instance);  
		 message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));   // to= jisko gmail send karni h uski gmail aaegi 
		 message.setFrom(new InternetAddress(user));  
		 message.setSubject(subject);  
		 message.setText(msg);     
		 Transport.send(message);  
		 System.out.println("send successfully!!!!00");
		    
		 } catch (Exception e) {  
		    e.printStackTrace();  
		 }  
		      
		}  
      
 
}
