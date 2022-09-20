/**
 * 
 */
package controllers;



import java.io.BufferedReader;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ResourceBundle;

import classes.profitLoss;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author luisgzz
 *
 */
public class ProfitLossController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		productColumn.setCellValueFactory(new PropertyValueFactory<profitLoss, String>("productName"));
		earningsColumn.setCellValueFactory(new PropertyValueFactory<profitLoss, String>("Earnings"));
		costsColumn.setCellValueFactory(new PropertyValueFactory<profitLoss, String>("Capital"));
		profitColumn.setCellValueFactory(new PropertyValueFactory<profitLoss, String>("profits"));
	
			
		
		Path productsPath = Paths.get("Files/Products.csv");
		try {
			BufferedReader br = Files.newBufferedReader(productsPath,StandardCharsets.US_ASCII);
			String line = br.readLine();
	
			
			while (line != null) {
				String productsName = null; // listo
				int Earnings = 0; // listo
				String Capital = "0"; // Listo
				int Profits = 0; 
				
				String [] characteristicsProducts = line.split(",");
				productsName = characteristicsProducts[0];
				Capital = characteristicsProducts[1];
				
					
				Path pathOrders = Paths.get("Files/Orders.csv");
				
					BufferedReader brOrder = Files.newBufferedReader(pathOrders,StandardCharsets.US_ASCII);
					String lineOrder = brOrder.readLine();
					
					while(lineOrder != null) {
						String [] characteristicsO = lineOrder.split(",");
						
						if (productsName.equals(characteristicsO[0])) {
							
							Earnings = Earnings + (Integer.parseInt(characteristicsO[3]) * Integer.parseInt(characteristicsO[4]));
							
						}
						lineOrder = brOrder.readLine();
					}
				
				Profits = Earnings - Integer.parseInt(Capital);
				
				profitLoss PL = new profitLoss(productsName, String.valueOf(Earnings), Capital , String.valueOf(Profits));
				ProfitLossTable.getItems().add(PL);  
				line = br.readLine();
			
			}
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			
		}
		
	}
	
	@FXML
	private TableView<profitLoss>ProfitLossTable;
	@FXML
	private TableColumn<profitLoss, String> productColumn;
	@FXML
	private TableColumn<profitLoss, String> earningsColumn;
	@FXML
	private TableColumn<profitLoss, String> costsColumn;
	@FXML
	private TableColumn<profitLoss, String> profitColumn;
	
	
	
	@FXML
	private Button backButton;
	
	@FXML
	public void goBack(ActionEvent event) {
		application.Main.mainStage = (Stage) backButton.getScene().getWindow();
		application.Main.mainStage.setScene(application.Main.scene2);
	}

}
