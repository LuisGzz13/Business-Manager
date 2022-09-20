/**
 * 
 */
package controllers;

import java.io.BufferedReader;






import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

import classes.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author luisgzz
 *
 */
public class CreateAccountController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		UsernameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("Username"));
		PasswordColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("Password"));
		
		Path path = Paths.get("Files/Users.csv");
		try {
			BufferedReader br = Files.newBufferedReader(path,StandardCharsets.US_ASCII);
			String line = br.readLine();
			while (line != null) {
				String [] characteristics = line.split(",");
				Users user = new Users(characteristics[0], characteristics[1]);
				UsersTable.getItems().add(user);  
				line = br.readLine();
			}
		} catch (IOException e) {  
		}

	}
	
	
	
	String csvFile = "Files/Users.csv";
	String line = "";
	String csvSplit = ",";
	BufferedReader br = null;
	FileWriter writer;

	
	
	@FXML
	private Button SaveButton;
	@FXML
	private TextField UsernameField;
	@FXML
	private PasswordField PasswordField;
	@FXML
	private Button BackButton;
	@FXML
	private Button EditButton;
	@FXML
	private Button UpdateButton;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button SaveUpdateButton;

	int x = 0;
	
	@FXML
	private TableView<Users> UsersTable;
	    
	@FXML
	private TableColumn<Users, String> UsernameColumn;
	    
	@FXML
	private TableColumn<Users, String> PasswordColumn;
	
	
	@FXML
	public void BackToLogIn(ActionEvent event) throws IOException {
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText(null);
		alert.setContentText("All progress not saved will be lost");
		Optional <ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
			application.Main.mainStage = (Stage) BackButton.getScene().getWindow();
			application.Main.mainStage.setScene(application.Main.scene1);
		}
		
		else if(action.get()==ButtonType.CANCEL) {
			
		}
	}
	
	
	
	
	@FXML
	public void Saving (ActionEvent event) throws IOException {
	
		
		if( UsernameField.getText().equals("") | PasswordField.getText().equals("") ) {
    		

    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("VALIDATE FIELDS");
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter all fields");
    		alert.showAndWait();
    		
    	} 
		
		else{

    		Users user = new Users(UsernameField.getText(), PasswordField.getText());
    		UsersTable.getItems().add(user);  
    		

    		UsernameField.setText(null);
    		PasswordField.setText(null);
    		
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("INFORMATION");
    		alert.setHeaderText(null);
    		alert.setContentText("User has been added");
    		alert.showAndWait();
    	}
			
		} 
		
	
	
	public void Updating(ActionEvent event) {
		if (x == 1) {
			if( UsernameField.getText().equals("") | PasswordField.getText().equals("") ) {
	    		
	
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("VALIDATE FIELDS");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Please enter all fields");
	    		alert.showAndWait();
	    		
	    	} 
		
			else {
				
			UsersTable.getItems().removeAll(UsersTable.getSelectionModel().getSelectedItem());
	    	
	    	Users user = new Users(UsernameField.getText(), PasswordField.getText());
			UsersTable.getItems().add(user);  
			
			UsernameField.setText(null);
			PasswordField.setText(null);
			
			x = 0;
			
				}
		
		}
		
			else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("INFORMATION");
	    		alert.setHeaderText(null);
	    		alert.setContentText("There is nothing to edit, please choose something to edit.");
	    		alert.showAndWait();
				
			}
	}
	
	@FXML
	public void Editing(ActionEvent event) {
		
		UsernameField.setText(UsersTable.getSelectionModel().getSelectedItem().getUsername());
		PasswordField.setText(UsersTable.getSelectionModel().getSelectedItem().getPassword());
		
		x = 1;
	}
	
	@FXML
	public void Deleting(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete this user?");
		Optional <ButtonType> action = alert.showAndWait();
		
			if(action.get() == ButtonType.OK) {
		    	UsersTable.getItems().removeAll(UsersTable.getSelectionModel().getSelectedItem());

			} else if (action.get()==ButtonType.CANCEL){
				
			}
		
	}
	
	@FXML
	public void SavingAndUpdating(ActionEvent event) {
		
		FileWriter fileWriter = null;
    	try {
    		
    		fileWriter = new FileWriter("Files/Users.csv");
   
    		
    		for (int i = 0; i < UsersTable.getItems().size(); i++) {
				
    			fileWriter.append(UsersTable.getItems().get(i).getUsername());
				fileWriter.append(",");
				fileWriter.append(UsersTable.getItems().get(i).getPassword());
				fileWriter.append("\n");

			}
    	}
    	
    	catch (Exception ex) {
    		ex.printStackTrace();
    	} 
    	
    	
    	try {

	    	fileWriter.flush();
	    	fileWriter.close();
	    			
	    	int ValidateFields = 1;
	    	
	    	if(ValidateFields == 1) {
	    		Alert alert = new Alert(AlertType.INFORMATION);		
	    		alert.setTitle("INFORMATION");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Save and Update successful.");
	    		alert.showAndWait();
	    	}
    	
    	} 
    		
		catch (IOException e){
			e.printStackTrace();
		}	
    	
	}
	
	
	
	
	
	
	

	
}
