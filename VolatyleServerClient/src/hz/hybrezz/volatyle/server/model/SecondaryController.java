package hz.hybrezz.volatyle.server.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SecondaryController {
	
	private String strHost;
	private String strPort;

    @FXML
    private TextField txtPort;

    @FXML
    private Button btnServer;

    @FXML
    private TextField txtHost;

    @FXML
    private TextField txtUser;

    @FXML
    private TextArea txtClient;

    @FXML
    void handleServerButtonAction(ActionEvent event) {
    	strHost = txtHost.getText();
    	strPort = txtPort.getText();
    	System.out.println(strHost + ":" + strPort);
    }
    
    @FXML
    void initialize() {
        assert txtPort != null : "fx:id=\"txtPort\" was not injected: check your FXML file 'ServerLayout.fxml'.";
        assert btnServer != null : "fx:id=\"btnServer\" was not injected: check your FXML file 'ServerLayout.fxml'.";
        assert txtHost != null : "fx:id=\"txtHost\" was not injected: check your FXML file 'ServerLayout.fxml'.";
        assert txtUser != null : "fx:id=\"txtUser\" was not injected: check your FXML file 'ServerLayout.fxml'.";
        assert txtClient != null : "fx:id=\"txtClient\" was not injected: check your FXML file 'ServerLayout.fxml'.";
    }
    
    public int getPort() {
    	return Integer.parseInt(strPort);
    }

}

