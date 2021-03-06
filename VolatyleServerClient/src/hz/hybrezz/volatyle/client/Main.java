package hz.hybrezz.volatyle.client;

import hz.hybrezz.volatyle.client.model.SecondaryController;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private SecondaryController secondaryController;
	private Client mClient;
	
	public Main() {
		super();
	}
	
	public Main(String host, int port) {
		mClient = new Client(host, port, this);
	}

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Volatyle Client");
		
		initRootLayout();
		initSecondaryLayout();
		
	}

	private void initRootLayout() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void initSecondaryLayout() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ConnectLayout.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			secondaryController = (SecondaryController) loader.getController();
			pane.setPrefSize(rootLayout.getPrefWidth(), rootLayout.getPrefHeight());
			rootLayout.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void startClient(String host, int port) {
		new Main(host, port);
	}
	
	public static void clientAction(String text) {
		System.out.println(text);
	}
	
	public void log(String text) {
		secondaryController.getTxtServer().appendText(text);
	}

	public static void main(String[] args) {
		switch (args.length) {
		case 0:
			launch(args);
			break;
		case 1:
			new Client("localhost", Integer.parseInt(args[0]), null).init();
			break;
		case 2:
			new Client(args[0], Integer.parseInt(args[1]), null).init();
			break;
		default:
			System.out.println("Usage: java Client");
			System.out.println("Usage: java Client <port>");
			System.out.println("Usage: java Client <host> <port>");
			break;
		}
	}

}
