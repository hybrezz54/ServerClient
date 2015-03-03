package hz.hybrezz.volatyle.client;

import hz.hybrezz.volatyle.client.model.SecondaryController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

	private Main main = null;
	private Socket mSocket = null;
	private PrintWriter outStream = null;
	
	public Client(Socket socket) {
		super("VolatyleClient");
		mSocket = socket;
	}
	
	public Client(String host, int port, Main main) {
		
		super("VolatyleClient");
		this.main = main;
		
		try {
			mSocket = new Socket(host, port);
			init();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			log("Could not connect to the server.");
			e.printStackTrace();
		}
		
	}
	
	public void init() {
		System.out.println("Client init() called");
		try(PrintWriter out = new PrintWriter(mSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		) {
			outStream = out;
			//this.start();
			//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String strServer, strUser;
			while ((strServer = in.readLine()) != null) {
				if (strServer.equals("/%/ DISCONNECT")) {
					outStream.println("Client Disconnected.");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void log(String text) {
		if (main != null)
			main.log(text);
		else
			System.out.println(text);
	}
	
	@Override
	public void run() {
		// keep alive
		while (isAlive() && !isInterrupted()) {
			try {
				outStream.println("/%/ " + System.currentTimeMillis());
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
