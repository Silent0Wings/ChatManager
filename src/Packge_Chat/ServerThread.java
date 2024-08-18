package Packge_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ServerThread implements Runnable {

	private Socket socket;
	private Server_Main server_main;
	private String Client_Name;
	private String Build;
	private Client_Profile Current_Client_Profile;
	private List<String> Pending_Message;

	public ServerThread(Socket socket, Server_Main server_main) {
		this.server_main = server_main;
		this.socket = socket;

	}

	public void run() {

		try {

			Pending_Message = new ArrayList<String>();

			int Client_Number = server_main.Get_Client_Number();

			System.out.println("Client " + Client_Number + " Has Connected	");

			// Input Output Buffer
			BufferedReader In_Socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter Out_Socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			Authentify_Thread(In_Socket, Out_Socket);

			if (server_main.authentify(Current_Client_Profile.Key)) {

				// Accepted
				System.out.println("authentication Successful");

				System.out.println("server_main.Chat_Copy " + server_main.Get_Chat());

				// Send a copy of the chat
				Out_Socket.println(server_main.Get_Chat());

				Terminal_Loop(In_Socket, Out_Socket);

				Close_Client(socket);

				System.out.println("Client " + Client_Number + " Has DisConnected	");

			} else {
				System.out.println("authentication Failed");

				Close_Client(socket);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public Client_Profile Build_Profile(String Build) {

		Client_Profile Current_Client_Profile = new Client_Profile();
		Current_Client_Profile.Id = new BigInteger(String_Parse.Find_Value("Id", Build, false));
		Current_Client_Profile.Name = String_Parse.Find_Value("Name", Build, false);
		Current_Client_Profile.Permission = String_Parse.Find_Value("Permission", Build, false);
		Current_Client_Profile.Login = String_Parse.Find_Value("Login", Build, false);
		Current_Client_Profile.Password = String_Parse.Find_Value("Password", Build, false);
		Current_Client_Profile.Key = new BigInteger(String_Parse.Find_Value("Key", Build, false));

		return Current_Client_Profile;
	}

	public void Terminal_Loop(BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {

		String Message = "test";
		while (!Message.equalsIgnoreCase("||Exit||")) {

			Message = Handle_Thread(In_Socket.readLine(), In_Socket, Out_Socket);

			// Send_Message(Out_Socket);

		}

	}

	public void Authentify_Thread(BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {
		String str = In_Socket.readLine();// Taking Binary
		String str1 = In_Socket.readLine();// Taking Spliter

		// System.out.println(str);
		// System.out.println(str1);
		// Building the original String

		int[] new_int = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {

			new_int[i] = Integer.parseInt(str.charAt(i) + "");
		}
		// System.out.println("new_int: " + Arrays.toString(new_int));

		Build = String_Parse.UnConvert_Test(str1, new_int);

		Current_Client_Profile = Build_Profile(Build);
		Client_Name = Current_Client_Profile.Name;
		// System.out.println(Current_Client_Profile.Key);

	}

	public String Handle_Thread(String Input, BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {

		if (Input.toUpperCase().startsWith("||Exit||".toUpperCase(), 0)) {

			return "||Exit||";

		} else {

			if (String_Parse.Find_Value("Message", Input, false).length() == 0 || Input == "") {

				if (Pending_Message.size() != 0) {

					Out_Socket.println(Pending_Message.get(0) + ".");
					Pending_Message.remove(0);

				} else {

					Out_Socket.println("");

				}

			} else {

				// System.out.println(Input);
				String temp_str = String_Parse.Find_Value("Message", Input, false);

				server_main.Broadcast(temp_str, this);
				Out_Socket.println(server_main.Update_Main_Chat(temp_str, this));
				;
			}

			return "Message";

		}

	}

	public void Close_Client(Socket socket) throws IOException {
		// Close Socket
		socket.close();
		System.out.println("Socket is Closed");
		server_main.All_ServerThread.remove(this);

	}

	public void Handle_Broadcast(String Input) {
		// System.out.println("Pending Message");
		Pending_Message.add(Input);
		// System.out.println(Pending_Message.size());
	}

	public String Get_Id() {

		return Current_Client_Profile.Id + "";
	}
	/*
	 * public void Send_Message(PrintWriter Out_Socket) throws IOException {
	 * 
	 * //Send Pending Messages if (Pending_Message.size()!=0) {
	 * 
	 * System.out.println("Sending the Message : "+Pending_Message.get(0));
	 * Out_Socket.println(Pending_Message.get(0)); // Send Message
	 * Pending_Message.remove(0);
	 * 
	 * }else {
	 * 
	 * System.out.println("No Messages"); Out_Socket.println(""); // Send Message
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

}
