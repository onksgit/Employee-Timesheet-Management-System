package com.tsms.web.validation;
import javax.mail.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tsms.web.dao.UserDao;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.AdminService;
import com.tsms.web.utils.BundleUtils;

public class MailUtils {
	
	
	public static void sendMail(String message, String sub, String to, String from) throws MessagingException {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(BundleUtils.message("company.private.mail"), BundleUtils.message("company.private.secret"));
			}
		});
		//session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(from);
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(sub);
		msg.setText(message);
		Transport.send(msg);
		System.out.println("Mail Send Success..");
	}
	
	
	public static void sendEmail(String to,String userName,String usernewPassword) throws IOException {
        String from = BundleUtils.message("company.private.mail"); 
        String password = BundleUtils.message("company.private.secret");
        String subject="Password Mail";
        
        String htmlContent = "\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Password Reset Successful</title>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"font-family: 'Arial', sans-serif; background-color: #f4f4f4; background-image: url('/KIPL-ART-WORK/WebContent/META-INF/resources/images/krish-new-logo.jpg'); background-size: cover;\">\n"
                + "\n"
                + "    <div style=\"max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; background-color: #fff;\">\n"
                + "        <img src='/resources/images/DRA-logo-new.png' alt='Company Logo' style='max-width: 100%; height: auto; margin-bottom: 20px;'>\n"
                + "\n"
                + "        <h2 style=\"color: #007BFF;\">Your password has been changed successfully.</h2>\n"
                + "\n"
                + "        <p>Hello [UserFirstName],</p>\n"
                + "        <p>Your password has been changed successfully. If you didn't make this change, please contact support.</p>\n"
                + "\n"
                + "        <h3 style=\"color: #007BFF;\">Changed Password Details:</h3>\n"
                + "        <p><strong>New Password:</strong> [usernewPassword]</p>\n"
                + "\n"
                + "        <h3 style=\"color: #007BFF;\">Thank you for choosing us!</h3>\n"
                + "        <p> If you have any questions, feel free to contact our support team.</p>\n"
                + "        <p style=\"font-size: 0.8em;\">This is an automated message. Please do not reply to this email.</p>\n"
	            + "        <img src='cid:companyLogo' alt='Company Logo' style='max-width: 100%; height: auto; margin-top: 20px;'>\n"
                + "\n"
                + "        <p style=\"margin-top: 20px; font-size: 0.8em; text-align: center;\">&copy; 2024 DRA Consultants Ltd. All rights reserved.</p>\n"
                + "\n"
                + "    </div>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";

        // Setup properties for the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("hr@dra.net.in"));
            message.setSubject(subject);
            htmlContent = htmlContent.replace("[UserFirstName]", userName);
            htmlContent = htmlContent.replace("[usernewPassword]", usernewPassword);
            
            MimeBodyPart htmlBodyPart = new MimeBodyPart();

            htmlBodyPart.setContent(htmlContent, "text/html");
            htmlBodyPart.setContent(htmlContent, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlBodyPart);
            MimeBodyPart imageBodyPart = new MimeBodyPart();
            imageBodyPart.attachFile(new File("/opt/TSMS/DRA-logo-new.png"));
            imageBodyPart.setContentID("<happyBirthdayImage>");
            imageBodyPart.setDisposition(MimeBodyPart.INLINE);
            multipart.addBodyPart(imageBodyPart);
            message.setContent(multipart);
            
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	
	public static void sendEmailNewEmployee(String to,String userName,String usernewPassword) throws IOException {
        String from = BundleUtils.message("company.private.mail"); 
        String password = BundleUtils.message("company.private.secret");
        String subject="New Employee Creation";
        
        String htmlContent = "\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>New Employee</title>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"font-family: 'Arial', sans-serif; background-color: #f4f4f4; background-image: url('/KIPL-ART-WORK/WebContent/META-INF/resources/images/krish-new-logo.jpg'); background-size: cover;\">\n"
                + "\n"
                + "    <div style=\"max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; background-color: #fff;\">\n"
                + "        <img src='cid:image' alt='Company Logo' style='max-width: 100%; height: auto; margin-bottom: 20px;'>\n"
                + "\n"
                + "        <h2 style=\"color: #007BFF;\">Welcome to DRA Consultant.</h2>\n"
                + "\n"
                + "        <p>Hello [UserFirstName],</p>\n"
                + "        <p>Your password has been created successfully.</p>\n"
                + "\n"
                + "        <h3 style=\"color: #007BFF;\"> Password Details:</h3>\n"
                + "        <p><strong>New Password:</strong> [usernewPassword]</p>\n"
                + "\n"
                + "        <h3 style=\"color: #007BFF;\">Thank you for choosing us!</h3>\n"
                + "        <p> If you have any questions, feel free to contact our support team.</p>\n"
                + "        <p style=\"font-size: 0.8em;\">This is an automated message. Please do not reply to this email.</p>\n"
	            + "        <img src='cid:companyLogo' alt='Company Logo' style='max-width: 100%; height: auto; margin-top: 20px;'>\n"

                + "\n"
                + "        <p style=\"margin-top: 20px; font-size: 0.8em; text-align: center;\">&copy; 2024 DRA Consultants Ltd. All rights reserved.</p>\n"
                + "\n"
                + "    </div>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("hr@dra.net.in"));
            message.setSubject(subject);
            
            htmlContent = htmlContent.replace("[UserFirstName]", userName);
            htmlContent = htmlContent.replace("[usernewPassword]", usernewPassword);
            
            

  	      Multipart multipart = new MimeMultipart();
  	     // Add Happy Birthday image
  	     MimeBodyPart happyBirthdayImagePart = new MimeBodyPart();
  	     happyBirthdayImagePart.attachFile(new File("/opt/TSMS/Employee-Joining.png"));
  	     happyBirthdayImagePart.setContentID("<image>");
  	     happyBirthdayImagePart.setDisposition(MimeBodyPart.INLINE);
  	     multipart.addBodyPart(happyBirthdayImagePart);

  	     // Add HTML content
  	     MimeBodyPart htmlBodyPart = new MimeBodyPart();
  	     htmlBodyPart.setContent(htmlContent, "text/html");
  	     multipart.addBodyPart(htmlBodyPart);

  	     // Add Company Logo image
  	     MimeBodyPart companyLogoImagePart = new MimeBodyPart();
  	     companyLogoImagePart.attachFile(new File("/opt/TSMS/DRA-logo-new.png"));
  	     companyLogoImagePart.setContentID("<companyLogo>");
  	     companyLogoImagePart.setDisposition(MimeBodyPart.INLINE);
  	     multipart.addBodyPart(companyLogoImagePart);
  	     message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	
	public static boolean sendEmailBirthday(String to, String userName, List<String> empMails) throws IOException {
		
	    String from = BundleUtils.message("company.private.mail");
	    String password = BundleUtils.message("company.private.secret");
	    String subject = "Happy Birthday";
	    boolean flag = false;
	    String htmlContent = "\n"
	            + "<!DOCTYPE html>\n"
	            + "<html lang=\"en\">\n"
	            + "\n"
	            + "<head>\n"
	            + "    <meta charset=\"UTF-8\">\n"
	            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
	            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
	            + "    <title>Happy Birthday</title>\n"
	            + "</head>\n"
	            + "\n"
	            + "<body style=\"font-family: 'Arial', sans-serif; background-color: #f4f4f4;\">\n"
	            + "\n"
	            + "    <div style=\"max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; background-color: #fff;\">\n"
	            + "        <img src='cid:happyBirthdayImage' alt='Happy Birthday' style='max-width: 100%; height: auto; margin-bottom: 20px;'>\n"
	            + "        <p>Happy Birthday, " + userName + ",</p>\n"
	            + "        <p><strong>Best wishes from the entire DRA Team.</strong></p>\n"
	            + "        <img src='cid:companyLogo' alt='Company Logo' style='max-width: 100%; height: auto; margin-top: 20px;'>\n"
	            + "        <p style=\"margin-top: 20px; font-size: 0.8em; text-align: center;\">&copy; 2024 DRA Consultants Ltd. All rights reserved.</p>\n"
	            + "    </div>\n"
	            + "\n"
	            + "</body>\n"
	            + "\n"
	            + "</html>";


	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    properties.put("mail.smtp.debug", "true");
	    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, password);
	        }
	    });

	    
	    try {
	    	
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        
//	        InternetAddress[] bccAddresses = new InternetAddress[empMails.size()];
//	        for (int i = 0; i < empMails.size(); i++) {
//	            bccAddresses[i] = new InternetAddress(empMails.get(i));
//	            System.out.println(i+":"+empMails.get(i));
//	        }
//	        message.setRecipients(Message.RecipientType.BCC, bccAddresses);
	        message.addRecipient(Message.RecipientType.CC, new InternetAddress("hr@dra.net.in"));
	        message.setSubject(subject);

	      Multipart multipart = new MimeMultipart();
	     // Add Happy Birthday image
	     MimeBodyPart happyBirthdayImagePart = new MimeBodyPart();
	     happyBirthdayImagePart.attachFile(new File("/opt/TSMS/Happy_Birthday.jpg"));
	     happyBirthdayImagePart.setContentID("<happyBirthdayImage>");
	     happyBirthdayImagePart.setDisposition(MimeBodyPart.INLINE);
	     multipart.addBodyPart(happyBirthdayImagePart);

	     // Add HTML content
	     MimeBodyPart htmlBodyPart = new MimeBodyPart();
	     htmlBodyPart.setContent(htmlContent, "text/html");
	     multipart.addBodyPart(htmlBodyPart);

	     // Add Company Logo image
	     MimeBodyPart companyLogoImagePart = new MimeBodyPart();
	     companyLogoImagePart.attachFile(new File("/opt/TSMS/DRA-logo-new.png"));
	     companyLogoImagePart.setContentID("<companyLogo>");
	     companyLogoImagePart.setDisposition(MimeBodyPart.INLINE);
	     multipart.addBodyPart(companyLogoImagePart);
	     message.setContent(multipart);
        Transport.send(message);
        flag = true;
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        flag = false;
	    }
	    return flag;
	}

	
	public static boolean sendEmailAnniversary(String to,String userName, List<String> empMails) throws IOException {
        String from = BundleUtils.message("company.private.mail"); 
        String password = BundleUtils.message("company.private.secret");
        String subject="Happy Work Anniversary";
        boolean flag = false;
        String htmlContent = "\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Happy Anniversary</title>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"font-family: 'Arial', sans-serif; background-color: #f4f4f4; background-image: url('/KIPL-ART-WORK/WebContent/META-INF/resources/images/krish-new-logo.jpg'); background-size: cover;\">\n"
                + "\n"
                + "    <div style=\"max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; background-color: #fff;\">\n"
                + "        <img src='cid:image' alt='Company Logo' style='max-width: 100%; height: auto; margin-bottom: 20px;'>\n"
                + "\n"
                + "        <p>Happy Anniversary, [UserFirstName],</p>\n"
                + "\n"
                + "        <p><strong>Best wishes from the entire DRA Team.</strong></p>\n"
	            + "        <img src='cid:companyLogo' alt='Company Logo' style='max-width: 100%; height: auto; margin-top: 20px;'>\n"
                + "\n"
                + "        <p style=\"margin-top: 20px; font-size: 0.8em; text-align: center;\">&copy; 2024 DRA Consultants Ltd. All rights reserved.</p>\n"
                + "\n"
                + "    </div>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
//            InternetAddress[] bccAddresses = new InternetAddress[empMails.size()];
//	        for (int i = 0; i < empMails.size(); i++) {
//	            bccAddresses[i] = new InternetAddress(empMails.get(i));
//	        }
//	        message.setRecipients(Message.RecipientType.BCC, bccAddresses);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("hr@dra.net.in"));
            message.setSubject(subject);
            
            htmlContent = htmlContent.replace("[UserFirstName]", userName);
            MimeBodyPart htmlBodyPart = new MimeBodyPart();

            htmlBodyPart.setContent(htmlContent, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlBodyPart);
            
            MimeBodyPart imageBodyPart = new MimeBodyPart();
            imageBodyPart.attachFile(new File("/opt/TSMS/HappyWorkAnniversary.jpg"));
            imageBodyPart.setContentID("<image>");
            imageBodyPart.setDisposition(MimeBodyPart.INLINE);
            multipart.addBodyPart(imageBodyPart);
            
            // Add Company Logo image
	   	     MimeBodyPart companyLogoImagePart = new MimeBodyPart();
	   	     companyLogoImagePart.attachFile(new File("/opt/TSMS/DRA-logo-new.png"));
	   	     companyLogoImagePart.setContentID("<companyLogo>");
	   	     companyLogoImagePart.setDisposition(MimeBodyPart.INLINE);
	   	     multipart.addBodyPart(companyLogoImagePart);
            message.setContent(multipart);
            Transport.send(message);
            flag = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

}
