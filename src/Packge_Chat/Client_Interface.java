package Packge_Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client_Interface {

	public JFrame frame;
	private JTextField textField;
	private Client This_Client;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_Interface window = new Client_Interface(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_Interface(Client Temp_Client) {

		System.err.println("Client_Interface" + Temp_Client);

		This_Client = Temp_Client;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 944, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(34, 41, 650, 402);
		frame.getContentPane().add(textArea);

		textField = new JTextField();
		textField.setBounds(34, 459, 650, 53);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				This_Client.Client_Interface_Message(textField.getText() + "");
				textField.setText("");

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(694, 459, 95, 53);
		frame.getContentPane().add(btnNewButton);

		// Needs to be at the end
		frame.setVisible(true);

	}

	public void Add_Ligne(String Input) {

		textArea.append(String_Parse.Find_Value("Id", Input, null) + " : "
				+ String_Parse.Find_Value("Message", Input, null) + "\n\r");
	}

}
