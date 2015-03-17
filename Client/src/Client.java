import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyPairGenerator;


public class Client implements Runnable {
	
	private String hostName;
	private int port;
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;

	public Client(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
		
		try {
			socket = new Socket(hostName, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new PrintWriter(socket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public void run() {
		
	}
	
}
