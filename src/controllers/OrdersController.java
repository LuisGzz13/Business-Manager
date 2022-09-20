/**
 * 
 */
package controllers;





import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import classes.Orders;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
public class OrdersController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources)   { 
		// TODO Auto-generated method stub
		
		
		try {
			String csvSplit = (",");
			File file = new File("Files/Products.CSV");
			BufferedReader br = new BufferedReader(new FileReader(file)); // declare el buffered aqui
			String line;
			List<String> ProductsPrice = new ArrayList<String>();
			
			while((line = br.readLine()) != null) {
				String [] attributes = line.split(csvSplit);
				productChooser.getItems().add(attributes[0]);
				
				
				ProductsPrice.add(attributes[2]);
		
				productChooser.getSelectionModel().selectedIndexProperty().addListener(
						(ObservableValue<? extends Number> ov, Number old_val,
						Number new_val) -> {
							priceField.setText(ProductsPrice.get(new_val.intValue()));
							oldValue = ProductsPrice.get(new_val.intValue());
							
				});
				
				
			}
			
			
			br.close();
			
		}
		
		catch(FileNotFoundException e){
			new File("Files/Products.CSV");
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		productColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("productName"));
		customerColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("customerName"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("dateOfOrder"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("quantity"));
		
		
		Path path = Paths.get("Files/Orders.csv");
		try {
			BufferedReader br = Files.newBufferedReader(path,StandardCharsets.US_ASCII);
			String line = br.readLine();
			while (line != null) {
				String [] characteristics = line.split(",");
				Orders order = new Orders(characteristics[0],characteristics[1],characteristics[2],characteristics[3],characteristics[4]);
				OrdersTable.getItems().add(order);  
				line = br.readLine();
			}
		} catch (IOException e) {  
		}	
	}
	
	String oldValue = "";
	int x;
	@FXML
	private Button backButton;
	@FXML
	private Button addProductButton;
	@FXML
	private ChoiceBox<String> productChooser;
	@FXML
	private TextField clientNameField;
	@FXML
	private TextField dateOfSaleField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField quantityField;
	@FXML
	private Button addOrderButton;
	@FXML
	private CheckBox specialPrice;
	@FXML
	private Button deleteButton;
	@FXML
	private Button editButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button updateButton;
	
	@FXML
	private TableView<Orders> OrdersTable;
	@FXML
	private TableColumn<Orders, String> productColumn;
	@FXML
	private TableColumn<Orders, String> customerColumn;
	@FXML
	private TableColumn<Orders, String> dateColumn;
	@FXML
	private TableColumn<Orders, Integer> priceColumn;
	@FXML
	private TableColumn<Orders, Integer> quantityColumn;

	
	@FXML
	public void Updating(ActionEvent event) {
		
		
		if (x == 1) {
			if( clientNameField.getText().equals("") | dateOfSaleField.getText().equals("") | priceField.getText().equals("")) {
	    		
	
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("VALIDATE FIELDS");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Please enter all fields");
	    		alert.showAndWait();
	    		
	    	} 
		
			else {
				
			OrdersTable.getItems().removeAll(OrdersTable.getSelectionModel().getSelectedItem());
	    	
			Orders order = new Orders(productChooser.getSelectionModel().getSelectedItem(),clientNameField.getText(), dateOfSaleField.getText(),
					priceField.getText(), quantityField.getText() );
			OrdersTable.getItems().addAll(order);   
			
			productChooser.setValue(null);
			clientNameField.setText(null);
    		dateOfSaleField.setText(null);
    		priceField.setText(null);
    		quantityField.setText(null);
    		specialPrice.setSelected(false);
			
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
	public void SavingOrders(ActionEvent event) {
		FileWriter fileWriter = null;
    	try {
    		
    		fileWriter = new FileWriter("Files/Orders.csv");
   
    		
    		for (int i = 0; i < OrdersTable.getItems().size(); i++) {
				
    			fileWriter.append(OrdersTable.getItems().get(i).getProductName());
    			fileWriter.append(",");
    			fileWriter.append(OrdersTable.getItems().get(i).getCustomerName());
				fileWriter.append(",");
				fileWriter.append(OrdersTable.getItems().get(i).getDateOfOrder());
				fileWriter.append(",");
				fileWriter.append(OrdersTable.getItems().get(i).getPrice());
				fileWriter.append(",");
				fileWriter.append(OrdersTable.getItems().get(i).getQuantity());
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
	
	@FXML
	public void DeletingProduct(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete this Order?");
		Optional <ButtonType> action = alert.showAndWait();
		
			if(action.get() == ButtonType.OK) {
		    	OrdersTable.getItems().removeAll(OrdersTable.getSelectionModel().getSelectedItem());

			} else if (action.get()==ButtonType.CANCEL){
				
			}
		
	}
	@FXML
	public void EditingProduct(ActionEvent event) {
		
		productChooser.setValue(OrdersTable.getSelectionModel().getSelectedItem().getProductName());
		clientNameField.setText(OrdersTable.getSelectionModel().getSelectedItem().getCustomerName());
		dateOfSaleField.setText(OrdersTable.getSelectionModel().getSelectedItem().getDateOfOrder());
		priceField.setText(OrdersTable.getSelectionModel().getSelectedItem().getPrice());
		quantityField.setText(OrdersTable.getSelectionModel().getSelectedItem().getQuantity());
		
		x = 1;
		
	}
	
	
	@FXML
	public void GoBack(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText(null);
		alert.setContentText("All progress not saved will be lost");
		Optional <ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
			application.Main.mainStage = (Stage) backButton.getScene().getWindow();
			application.Main.mainStage.setScene(application.Main.scene2);
		}
		
		else if(action.get()==ButtonType.CANCEL) {
			
		}
		
	}
	
	@FXML
	public void GoToAddProduct(ActionEvent event) {
		
		application.Main.mainStage = (Stage) addProductButton.getScene().getWindow();
		application.Main.mainStage.setScene(application.Main.scene8);
	}
	
	@FXML
	public void AddingOrder(ActionEvent event) {
		int y = 0;
		if(x == 1) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("FINISH EDIT");
    		alert.setHeaderText(null);
    		alert.setContentText("Please finish editing the order, before adding a new one");
    		alert.showAndWait();
		}
		
		else{
			if(clientNameField.getText().equals("") | dateOfSaleField.getText().equals("") | priceField.getText().equals("") | quantityField.getText().equals("")  ) {
	    		

	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("VALIDATE FIELDS");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Please enter all fields");
	    		alert.showAndWait();
	    		
	    	} 
			
			else{
				
				try {
					int x = Integer.parseInt(quantityField.getText());
					
					if (x == 0) {
						
						quantityField.setText("1");
						Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setTitle("INFORMATION");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Your quantity has to be more than zero");
			    		alert.showAndWait();
						
				}
					
					else {
						
						y = y + 1;
					}
					System.out.println(y);
					
				}
				
				catch(NumberFormatException e) {
					e.printStackTrace();
					
				
						quantityField.setText("1");
						
						Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setTitle("INFORMATION");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Your quantity has to be a number");
			    		alert.showAndWait();
						
			    		
					
				}
				
				try {
					int x = Integer.parseInt(priceField.getText());
					
					if (x == 0) {
						
						quantityField.setText("1");
						Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setTitle("INFORMATION");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Your price has to be more than zero");
			    		alert.showAndWait();
			    		
						
				}
					
					else {
						if (y == 1) {
							
							y = 2;
						}
						
					}
					
					System.out.println(y);
				}
				
				catch(NumberFormatException e) {
					e.printStackTrace();
					
				
						priceField.setText("");
						Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setTitle("INFORMATION");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Your price has to be a number");
			    		alert.showAndWait();
						
			    	
					
				}
				// agregar check para que si sean numeros, el price y el quantity.

				if (y == 2) {
					
					Orders order = new Orders(productChooser.getSelectionModel().getSelectedItem(),clientNameField.getText(), dateOfSaleField.getText(), priceField.getText(), quantityField.getText());
					OrdersTable.getItems().addAll(order); 
					
					productChooser.setValue(null);
		    		clientNameField.setText(null);
		    		dateOfSaleField.setText(null);
		    		priceField.setText(null);
		    		quantityField.setText(null);
		    		specialPrice.setSelected(false);
		    		
		    		Alert alert = new Alert(AlertType.INFORMATION);
		    		alert.setTitle("INFORMATION");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Order has been added");
		    		alert.showAndWait();
		    		
		    		y = 0;
				}
				
	    		
				
	    		
	    	}
		}
		
		
	}
	

	
	@FXML
	public void ActivateSpecialPrice(ActionEvent event) throws IOException {
		

			
			if (specialPrice.isSelected()) {
				priceField.setEditable(true);
				priceField.setDisable(false);
				
				
			}
		
			else {
				priceField.setEditable(false);
				priceField.setDisable(true);
				priceField.setText(oldValue);
				
				
				
			}
			
			
			
		}
	

	

	
}

