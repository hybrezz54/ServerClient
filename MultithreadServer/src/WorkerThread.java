import java.io.BufferedReader;
import java.io.FileReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkerThread implements Runnable {

	protected Socket clientSocket;
	protected boolean isStopped;
	protected String remoteSocketAddress;

	public WorkerThread(Socket clientSocket, boolean isStopped) {
		this.clientSocket = clientSocket;
		this.remoteSocketAddress = clientSocket.getRemoteSocketAddress()
				.toString();
		this.isStopped = isStopped;
	}

	public void run() {
		try {

			Calendar cal = Calendar.getInstance();
			cal.getTime();
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");

			/*
			 * ObjectInputStream in = new ObjectInputStream(
			 * clientSocket.getInputStream());
			 * 
			 * ObjectOutputStream out = new ObjectOutputStream(
			 * clientSocket.getOutputStream());
			 */
			OutputStream out = clientSocket.getOutputStream();

			// if (clientSocket.getRemoteSocketAddress() != null) {

			out.write(("HTTP/1.1 200 OK\n\n" + "").getBytes());

			BufferedReader read = new BufferedReader(new FileReader(
					"index.html"));
			String line = null;
			while (((line = read.readLine()) != null)) {
				out.write((line).getBytes());
			}
			read.close();

			/*
			 * out.writeObject(clientSocket.getRemoteSocketAddress() +
			 * " connected at " + time.format(cal.getTime()));
			 */

			// System.out.println(clientSocket.getRemoteSocketAddress() +
			// " connected at " + time.format(cal.getTime()));
			Server.txtArea.append(clientSocket.getRemoteSocketAddress()
					+ " connected at " + time.format(cal.getTime()) + "\n");

			/*
			 * } else { out.writeObject(this.remoteSocketAddress +
			 * " disconnected at " + time.format(cal.getTime()));
			 * System.out.println(this.remoteSocketAddress + " disconnected at "
			 * + time.format(cal.getTime())); }
			 */

			out.close();
			// in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
