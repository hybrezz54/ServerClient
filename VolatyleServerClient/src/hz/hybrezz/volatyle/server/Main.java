package hz.hybrezz.volatyle.server;

import hz.hybrezz.volatyle.server.model.SecondaryController;

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

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Volatyle Server");
		
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
			loader.setLocation(Main.class.getResource("view/ServerLayout.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			secondaryController = (SecondaryController) loader.getController();
			pane.setPrefSize(rootLayout.getPrefWidth(), rootLayout.getPrefHeight());
			rootLayout.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void startServer() {
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
