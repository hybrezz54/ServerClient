package hz.hybrezz.volatyle.client.model;

import hz.hybrezz.volatyle.client.Main;
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
    private Button btnConnect;

    @FXML
    private TextField txtHost;

    @FXML
    private TextField txtUser;

	@FXML
    private TextArea txtServer;

	@FXML
    void handleConnectButtonAction(ActionEvent event) {
    	strHost = txtHost.getText();
    	strPort = txtPort.getText();
    	Main.startClient(getHost(), getPort());
    	System.out.println(strHost + ":" + strPort);
    }
    
    @FXML
    void initialize() {
        assert txtPort != null : "fx:id=\"txtPort\" was not injected: check your FXML file 'ConnectLayout.fxml'.";
        assert btnConnect != null : "fx:id=\"btnConnect\" was not injected: check your FXML file 'ConnectLayout.fxml'.";
        assert txtHost != null : "fx:id=\"txtHost\" was not injected: check your FXML file 'ConnectLayout.fxml'.";
        assert txtUser != null : "fx:id=\"txtUser\" was not injected: check your FXML file 'ConnectLayout.fxml'.";
        assert txtServer != null : "fx:id=\"txtServer\" was not injected: check your FXML file 'ConnectLayout.fxml'.";
    }
    
    public String getHost() {
    	return strHost;
    }
    
    public int getPort() {
    	return Integer.parseInt(strPort);
    }
    
    public TextField getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(TextField txtUser) {
		this.txtUser = txtUser;
	}
	
	public TextArea getTxtServer() {
		return txtServer;
	}

	public void setTxtServer(TextArea txtServer) {
		this.txtServer = txtServer;
	}
	
	

}

