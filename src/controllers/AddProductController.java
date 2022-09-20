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


import classes.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author luisgzz
 *
 */
public class AddProductController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		NameColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("Name"));
		
		CapitalColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("totalCost"));
		PriceColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("Price"));
		StockColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("Stock"));
		
		Path path = Paths.get("Files/Products.csv");
		try {
			BufferedReader br = Files.newBufferedReader(path,StandardCharsets.US_ASCII);
			String line = br.readLine();
			while (line != null) {
				String [] characteristics = line.split(",");
				Products product = new Products(characteristics[0],characteristics[1],characteristics[2],characteristics[3]);
				ProductsTable.getItems().add(product);  
				line = br.readLine();
			}
		} catch (IOException e) {  
		}
	}
	

	int x;
	@FXML
	public TableView<Products> ProductsTable;
	@FXML
	private TableColumn<Products, String> NameColumn;
	@FXML
	private TableColumn<Products, String> CapitalColumn;
	@FXML
	private TableColumn<Products, String> PriceColumn;
	@FXML
	private TableColumn<Products, String> StockColumn;
	
	
	
	
	@FXML
	private Button addProductButton;
	@FXML
	private Button editButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button backButton;

	@FXML
	private TextField productNameField;
	@FXML
	private TextField investedCapitalField;
	@FXML
	private TextField basePriceField;
	@FXML
	private TextField totalStockField;

	
	@FXML
	public void BackToLogIn(ActionEvent event) throws IOException {
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText(null);
		alert.setContentText("All progress not saved will be lost");
		Optional <ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
			controllers.ConduitController.orders = FXMLLoader.load(getClass().getResource("/application/OrderGUI.fxml"));
			controllers.ConduitController.scene4 = new Scene(controllers.ConduitController.orders);
			application.Main.mainStage = (Stage) backButton.getScene().getWindow();
			application.Main.mainStage.setScene(controllers.ConduitController.scene4);
		}
		
		else if(action.get()==ButtonType.CANCEL) {
			
		}
	}
	
	@FXML
	public void SavingProduct(ActionEvent event) throws IOException {
		
			FileWriter fileWriter = null;
	    	try {
	    		
	    		fileWriter = new FileWriter("Files/Products.csv");
	   
	    		
	    		for (int i = 0; i < ProductsTable.getItems().size(); i++) {
					
	    			fileWriter.append(ProductsTable.getItems().get(i).getName());
					fileWriter.append(",");
					fileWriter.append(ProductsTable.getItems().get(i).getTotalCost() + "");
					fileWriter.append(",");
					fileWriter.append(ProductsTable.getItems().get(i).getPrice() + "");
					fileWriter.append(",");
					fileWriter.append(ProductsTable.getItems().get(i).getStock() + "");
					fileWriter.append("\n");

				}
	    		
	    		
	    		
	    	}
	    	
	    	catch (Exception ex) {
	    		ex.printStackTrace();
	    	} 
	    	
	    	
	    	try {

		    	fileWriter.flush();
		    	fileWriter.close();
		    			
		    
		    		Alert alert = new Alert(AlertType.INFORMATION);		
		    		alert.setTitle("INFORMATION");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Save successful.");
		    		alert.showAndWait();
		    	
	    	
	    	} 
	    		
			catch (IOException e){
				e.printStackTrace();
			}
	    	
	    	
	    	
			
		
		
		
	}
	
	@FXML
	public void AddingProduct(ActionEvent event) {
	if( x == 1) {
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("NOT A VALID COMMAND");
		alert.setHeaderText(null);
		alert.setContentText("You are editing a product, you cannot add a product until you finish editing");
		alert.showAndWait();
	}
	
	else {
		if( productNameField.getText().equals("") | investedCapitalField.getText().equals("") 
			| basePriceField.getText().equals("") | totalStockField.getText().equals("")) {
    		

    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("VALIDATE FIELDS");
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter all fields");
    		alert.showAndWait();
    		
    	} 
		
		else{

			Products product = new Products(productNameField.getText(), investedCapitalField.getText(),basePriceField.getText(), totalStockField.getText());
			ProductsTable.getItems().add(product);
    		
			
    		

    		productNameField.setText(null);
    		investedCapitalField.setText(null);
    		basePriceField.setText(null);
    		totalStockField.setText(null);
    		
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("INFORMATION");
    		alert.setHeaderText(null);
    		alert.setContentText("Product has been added");
    		alert.showAndWait();
    	}
	}
		
	}
	
	@FXML
	public void EditingProduct(ActionEvent event) {
		productNameField.setText(ProductsTable.getSelectionModel().getSelectedItem().getName());
		investedCapitalField.setText(ProductsTable.getSelectionModel().getSelectedItem().getTotalCost() + "");
		basePriceField.setText(ProductsTable.getSelectionModel().getSelectedItem().getPrice() + "");
		totalStockField.setText(ProductsTable.getSelectionModel().getSelectedItem().getStock() + "");

		x = 1;
		
	}
	
	@FXML
	public void DeletingProduct(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete this Product?");
		Optional <ButtonType> action = alert.showAndWait();
		
			if(action.get() == ButtonType.OK) {
		    	ProductsTable.getItems().removeAll(ProductsTable.getSelectionModel().getSelectedItem());

			}
			else if (action.get()==ButtonType.CANCEL){
				
			}
		
		
	}
	
	@FXML
	public void UpdatingProduct(ActionEvent event) {
		if (x == 1) {
			if( productNameField.getText().equals("") | investedCapitalField.getText().equals("") | basePriceField.getText().equals("") | totalStockField.getText().equals("")  ) {
	    		
	
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("VALIDATE FIELDS");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Please enter all fields");
	    		alert.showAndWait();
	    		
	    	} 
		
			else {
				
			ProductsTable.getItems().removeAll(ProductsTable.getSelectionModel().getSelectedItem());
	    	
			Products product = new Products(productNameField.getText(), investedCapitalField.getText(),basePriceField.getText(), totalStockField.getText());
			ProductsTable.getItems().add(product);
			
			productNameField.setText(null);
			investedCapitalField.setText(null);
			basePriceField.setText(null);
			totalStockField.setText(null);
			
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
	

}
