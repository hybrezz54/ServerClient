import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MultiThreadClient implements Runnable {

	protected int port;
	protected String host;
	protected Thread runThread = null;
	protected boolean isStopped;
	protected Socket socket;

	public MultiThreadClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {

		synchronized (this) {
			runThread = Thread.currentThread();
		}

		openClientSocket();

		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			oos.writeObject("");
			ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
			String strMessage = (String)ois.readObject();
			System.out.println("Host: " + strMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void stop() {
		this.isStopped = false;
		try {
			this.socket.close();
		} catch (Exception e) {
			throw new RuntimeException("Error stopping client!");
		}
	}

	private void openClientSocket() {
		try {
			this.socket = new Socket(host, port);
		} catch (Exception e) {
			throw new RuntimeException("Cannot bind client port " + this.port
					+ " to " + this.host);
		}
	}

}