/**

 * 
 */
package controllers;

import java.net.URL;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 * @author luisgzz
 *
 */
public class EmailController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	
	
	@FXML
	private TextField ToEmail;
	@FXML
	private TextField Subject;
	@FXML
	private TextArea MessageTextArea;
	
	@FXML
	private Button backButton;
	@FXML
	private Button sendButton;
	
	@FXML
	public void Sending(ActionEvent event) {
	
		
		String toEmail = ToEmail.getText();
		String subject = Subject.getText();
		String message = MessageTextArea.getText();
		
		 Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        
	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication("MVRTESENDER@gmail.com", "MVRTEMAILSENDER1029!");
	                  }
	                }); 

	              try {

	                  Message mail = new MimeMessage(session);
	                  mail.setFrom(new InternetAddress("MVRTESENDER@gmail.com"));
	                  mail.setRecipients(Message.RecipientType.TO,
	                      InternetAddress.parse(toEmail));
	                  mail.setSubject(subject);
	                  StringBuilder stringBuilder = new StringBuilder();
	                  stringBuilder.append(message).append(System.lineSeparator());
	                  mail.setText(stringBuilder.toString());
	                  Transport.send(mail);
	              } catch (MessagingException e) {
	                  throw new RuntimeException(e);
	                  
	              }
	              
	              ToEmail.setText("");
	              Subject.setText("");
	              MessageTextArea.setText("");
	              
	              Alert alert = new Alert(AlertType.CONFIRMATION);
	              alert.setTitle("Succesful");
	              alert.setHeaderText(null);
	      		  alert.setContentText("Your mail has been sent!");
	      		  alert.showAndWait();
		
	}
	
	
	@FXML
	public void Backing(ActionEvent event) {
		application.Main.mainStage = (Stage) backButton.getScene().getWindow();
		application.Main.mainStage.setScene(application.Main.scene2);
		
	}
	
	
}
