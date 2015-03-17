import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Server extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2943286328043013210L;
	private final static String newline = "\n";
	public static JTextArea txtArea = new JTextArea();;
	protected JTextField txtField;
	protected static MultiThreadServer server;

	public Server() {
		super(new GridBagLayout());

		txtField = new JTextField();
		txtField.addActionListener(this);

		txtArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtArea);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;
		add(txtField, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
	}

	public static void main(String args[]) {

		int port = 54;

		if (args.length > 1) {
			// System.err.println("Usage: java Server <port>");
			System.exit(1);
		} else if (args.length != 0) {
			if (args[0].equals("help")) {
				// System.out.println("Usage: java Server <port>");
			} else {
				port = Integer.parseInt(args[0]);
			}
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});

		server = new MultiThreadServer(port);
		new Thread(server).start();

	}

	private static void createWindow() {
		// JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame wndFrame = new JFrame("Server Control Panel");
		wndFrame.setTitle("Server");
		wndFrame.setSize(500, 400);
		wndFrame.setLocationRelativeTo(null);

		wndFrame.add(new Server());
		wndFrame.setVisible(true);
		wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void stop() {
		try {
			txtField.setEditable(false);
			Thread.sleep(1);
			server.stop();
			txtArea.append("SERVER STOPPING... ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			txtArea.append(">> " + txtField.getText() + newline);
			if (txtField.getText().equals("stop")) {
				stop();
				
				Timer timer = new Timer(1500, new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(1);
					}
				});
				
				timer.start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}