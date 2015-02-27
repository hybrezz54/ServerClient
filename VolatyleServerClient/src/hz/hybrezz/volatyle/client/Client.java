package hz.hybrezz.volatyle.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

	private Socket socket = null;
	private PrintWriter outStream = null;
	
	public Client(Socket socket) {
		super("VolatyleClientThread");
		this.socket = socket;
	}
	
	public Client(String host, int port) {
		
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		
		try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			outStream = out;
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
