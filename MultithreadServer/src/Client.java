import java.util.Scanner;

public class Client {
	
	private static int port;
	private static String host;
	
	public static void main(String args[]) {
		
		if (args.length != 2) {
			System.err.println("Usage: java Client <host> <port>");
			System.exit(1);
		}
		
		host = args[0];
		port = Integer.parseInt(args[1]);
		MultiThreadClient client = new MultiThreadClient(host, port);
		new Thread(client).start();
		
		try {
			while(!client.isStopped) {
				Scanner reader = new Scanner(System.in);
				System.out.print(">> ");
				if (reader.next().equals("exit")) {
					reader.close();
					client.stop();
					Thread.sleep(100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}