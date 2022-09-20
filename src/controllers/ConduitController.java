/**
 * 
 */
package controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author Luis Gonzalez
 *
 */
public class ConduitController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private Button backButton;
	@FXML
	private Button orderButton;
	@FXML
	private Button profitLossButton;
	@FXML
	private Button emailButton;
	@FXML
	private Button stockButton;

	public static Parent profitloss;
	public static Parent stock;
	public static Parent orders;
	
	public static Scene scene4;
	public static Scene scene5;
	public static Scene scene7;
	
	
	/*
	 * scene1 = Log-in Screen
	 * scene2 = Conduit Screen
	 * scene3 = Create account Screen
	 * scene4 = Shipments Screen
	 * scene5 = Profit and Loss Screen
	 * scene6 = Email Screen
	 * scene7 = Stock Screen
	 * scene8 = Add Product Screen
	 */
	
	@FXML
	public void backToLogIn(ActionEvent event) {
		application.Main.mainStage = (Stage) backButton.getScene().getWindow();
    	application.Main.mainStage.setScene(application.Main.scene1);
	}
	
	@FXML
	public void goToOrders(ActionEvent event) throws IOException {
		 orders = FXMLLoader.load(getClass().getResource("/application/OrderGUI.fxml"));
		 scene4 = new Scene(orders);
		application.Main.mainStage = (Stage) orderButton.getScene().getWindow();
    	application.Main.mainStage.setScene(scene4);
		
    	
	}
	
	@FXML
	public void goToProfitLoss(ActionEvent event) throws IOException {
		profitloss = FXMLLoader.load(getClass().getResource("/application/ProfitLossGUI.fxml"));
		scene5 = new Scene(profitloss);
		application.Main.mainStage = (Stage) profitLossButton.getScene().getWindow();
    	application.Main.mainStage.setScene(scene5);
		
	}
	
	@FXML
	public void goToEmail (ActionEvent event) {
		application.Main.mainStage = (Stage) emailButton.getScene().getWindow();
    	application.Main.mainStage.setScene(application.Main.scene6);
		
	}
	
	@FXML
	public void goToStock(ActionEvent event) throws IOException {
		
		stock = FXMLLoader.load(getClass().getResource("/application/StockGUI.fxml"));
		scene7 = new Scene(stock);
		application.Main.mainStage = (Stage) stockButton.getScene().getWindow();
    	application.Main.mainStage.setScene(scene7);
		
	}
	
	
	
	
}
