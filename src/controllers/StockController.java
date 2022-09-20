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

import classes.Stock;
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
public class StockController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		productColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("productName"));
		ogStockColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("ogStock"));
		stockRemainingColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockRemaining"));
		
		
		Path productsPath = Paths.get("Files/Products.csv");
		try {
			BufferedReader br = Files.newBufferedReader(productsPath,StandardCharsets.US_ASCII);
			String line = br.readLine();
			
			
			
			while (line != null) {
				String productsName = null; // listo
				int ogStock = 0;
				int RemainingStock = 0;
				
				String [] characteristicsProducts = line.split(",");
				productsName = characteristicsProducts[0];
				ogStock = Integer.parseInt(characteristicsProducts[3]);
				RemainingStock = ogStock;
					
				Path pathOrders = Paths.get("Files/Orders.csv");
				
					BufferedReader brOrder = Files.newBufferedReader(pathOrders,StandardCharsets.US_ASCII);
					String lineOrder = brOrder.readLine();
					
					while(lineOrder != null) {
						String [] characteristicsO = lineOrder.split(",");
						
						if (productsName.equals(characteristicsO[0])) {
							
							RemainingStock = RemainingStock - Integer.parseInt(characteristicsO[4]);
							
						}
						lineOrder = brOrder.readLine();
					}
				
			
				
				Stock stock = new Stock(productsName, String.valueOf(ogStock), String.valueOf(RemainingStock));
				StockTable.getItems().add(stock);  
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
	private Button backButton;
	
	@FXML
	private TableView<Stock> StockTable;
	@FXML
	private TableColumn<Stock, String> productColumn;
	@FXML
	private TableColumn<Stock, String> ogStockColumn;
	@FXML
	private TableColumn<Stock, String> stockRemainingColumn;
	
	
	@FXML
	public void goBack(ActionEvent event) {
		
		application.Main.mainStage = (Stage) backButton.getScene().getWindow();
    	application.Main.mainStage.setScene(application.Main.scene2);
	}
}
