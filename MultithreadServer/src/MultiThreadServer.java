import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable {

	protected int port;
	protected ServerSocket ss = null;
	protected Thread runThread = null;
	protected boolean isStopped = false;
	private final static String newline = "\n";

	public MultiThreadServer(int port) {
		this.port = port;
	}

	public void run() {

		synchronized (this) {
			this.runThread = Thread.currentThread();
		}

		openSocket();
		Server.txtArea.append("Server started..." + newline);

		while (!isStopped) {
			Socket clientSocket = null;
			try {
				clientSocket = this.ss.accept();

			} catch (Exception e) {
				if (isStopped()) {
					//System.err.print("SERVER STOPPING... ");
					Server.txtArea.append("STOPPED!" + newline);
					return;
				}
				throw new RuntimeException("Client connection refused!");
			}

			new Thread(new WorkerThread(clientSocket, isStopped)).start();
		}

		System.err.print("SERVER STOPPING... ");
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.ss.close();
		} catch (Exception e) {
			throw new RuntimeException("Error stopping server!");
		}
	}

	private void openSocket() {
		try {
			this.ss = new ServerSocket(this.port);
		} catch (Exception e) {
			throw new RuntimeException("Cannot bind port " + this.port);
		}
	}

}