package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	
	public static Parent login;
	public static Parent conduit;
	public static Parent createAccount;
	public static Parent email;
	public static Parent addProduct;
	
	public static Scene scene1;
	public static Scene scene2;
	public static Scene scene3;
	
	public static Scene scene6;

	public static Scene scene8;
	
	public static Stage mainStage;
	
	
	
	public void start(Stage primaryStage) {
		try {
			
			 login = FXMLLoader.load(getClass().getResource("/application/LoginGUI.fxml")); // Login Screen
			 conduit = FXMLLoader.load(getClass().getResource("/application/ConduitGUI.fxml"));// decision screen
			 createAccount = FXMLLoader.load(getClass().getResource("/application/CreateAccountGUI.fxml"));
			
			 email = FXMLLoader.load(getClass().getResource("/application/EmailGUI.fxml"));
			 addProduct = FXMLLoader.load(getClass().getResource("/application/AddProductGUI.fxml"));
			 
			 
			 scene1 = new Scene(login); // Login Screen
			 scene2 = new Scene(conduit); // decision screen
			 scene3 = new Scene(createAccount);//create account
			 scene6 = new Scene(email);
			 scene8 = new Scene(addProduct);
			 
			 
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene1);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
