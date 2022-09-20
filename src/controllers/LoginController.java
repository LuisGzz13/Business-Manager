/**
 * 
 */
package controllers;

import java.io.BufferedReader;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;


import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.Initializable;


/**
 * @author Luis Gonzalez
 *
 */
public class LoginController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
	}


	@FXML
    private TextField Username;

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField Password;
    
    @FXML
    private Button CreateAccountButton;
    
   
  
    
   
   
    
    
    
   
	@FXML 
    void CreatingAccount(ActionEvent event) throws IOException {
    
    	if (Username.getText().equals("Luis")&& Password.getText().equals("123")) {
    		application.Main.mainStage = (Stage) CreateAccountButton.getScene().getWindow();
	    	application.Main.mainStage.setScene(application.Main.scene3);
    	}
    	
    	else {
    		
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Incorrect Admin Credentials");
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter Admin Credentials");
    		alert.showAndWait();
    		
    		Username.setText("");
    		Password.setText("");
    		
    	}
    }
    
    
    
    @FXML
    void LogInPress(ActionEvent event) throws IOException {
    	
    	if (Username.getText().equals("") || Password.getText().equals("")) {
    		
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Validate Fields");
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter all fields");
    		alert.showAndWait();
    	}
    	
    	else {
    	Path path = Paths.get("Files/Users.csv");
		try {
			BufferedReader br = Files.newBufferedReader(path,StandardCharsets.US_ASCII);
			String line = br.readLine();
			int validate = 0;
			while (line != null) {
				String [] logins = line.split(",");
				
				if (Username.getText().equals(logins[0])&&(Password.getText().equals(logins[1]))) {
		        	
					validate = 1;
		    		Scene();
		    	}
				
				line = br.readLine();
				
			}
			
			if (validate == 0) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Incorrect Credentials");
    		alert.setHeaderText(null);
    		alert.setContentText("Your username and/or password is incorrect");
    		alert.showAndWait();
			}
    		Username.setText("");
    		Password.setText("");
			
		} 
		catch (IOException e) {  
		}
    }
    }
    public void Scene() throws IOException {
    	
    	Stage secondStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ConduitGUI.fxml")); 
		Scene scene = new Scene(root);
		secondStage.setScene(scene);
		secondStage.show();
		Stage stage = (Stage) LoginButton.getScene().getWindow();
		stage.close();
		
		
    }
}
