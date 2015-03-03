package hz.hybrezz.volatyle.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	
	private ServerSocket mSocket;
	private Socket mClientSocket;
		
	public Server(ServerSocket socket) {
		super("VolatyleServer");
		mSocket = socket;
	}
	
	public Server(int port) {
		
		super("VolatyleClient");
		
		try {
			mSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {
		this.start();
	}
	
	@Override
	public void run() {
		
		super.run();
		
		try {
			
			mClientSocket = mSocket.accept();
			PrintWriter out = new PrintWriter(mClientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
			// BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			String strClient, strUser, strDisplay;
			ServerProtocol sp = new ServerProtocol();
			
			
			while ((strClient = in.readLine()) != null) {
				strDisplay = sp.processInput(strClient);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
