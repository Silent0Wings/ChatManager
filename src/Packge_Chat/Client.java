package Packge_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Client {

	private List<String> Server_Chat;

	private static Client_Interface This_Client_Interface;

	private List<String> Pending_Messages;

	@SuppressWarnings("unused")
	public Client() throws Exception {

		This_Client_Interface = new Client_Interface(this);

		Server_Chat = new ArrayList<String>();
		Pending_Messages = new ArrayList<String>();

		Socket socket = new Socket("127.0.0.1", 2020); // new port
		System.out.println("successful connection to server .");

		// Input Output Buffer
		BufferedReader In_Socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter Out_Socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		// Authentify
		Authentify_Client(In_Socket, Out_Socket);

		// Load Chat if ther is one
		Server_Chat = Construct_Chat(In_Socket.readLine() + "");

		System.out.println("Server_Chat" + Get_Chat());

		Client_Loop(In_Socket, Out_Socket);

		Close_Client(socket);

	}

	public void Client_Loop(BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {

		String Message = "test";
		while (!Message.equalsIgnoreCase("||Exit||")) {

			Message = Handle_Client(In_Socket, Out_Socket);
			Update_Chat(In_Socket, Out_Socket);

			System.out.println("Get_Chat() " + Get_Chat());
		}

	}

	public void Update_Chat(BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {
		String Temp_String = In_Socket.readLine();

		if (Temp_String == "") {

			return;

		} else if (String_Parse.Find_Value("Message", Temp_String, false).length() != 0) {

			Server_Chat.add(Temp_String);

			This_Client_Interface.Add_Ligne(Temp_String + "");

		}

	}

	public void Authentify_Client(BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {
		String Build = "Id9223372036854775806," + "Nameyahya," + "PermissionAdmin," + "LoginYahya123,"
				+ "PasswordTest123,"
				+ "Key92233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807";

		Object[] temp3 = String_Parse.Convert_Test(Build, false);

		System.out.println(String_Parse.Find_Value("Id", Build, false));
		int[] Binary_split_insctruction = (int[]) temp3[1];
		int[] binary_Split = String_Parse.Split_Binary(String.valueOf(temp3[0]), Binary_split_insctruction, false);

		// System.out.println(Arrays.toString(Binary_split_insctruction));
		// System.out.println(Arrays.toString(binary_Split));

		String str = Arrays.toString(Binary_split_insctruction).replaceAll("\\[|\\]|,|\\s", "");
		String str1 = Arrays.toString(binary_Split).replaceAll("\\[|\\]|,|\\s", "");

		// System.out.println(str);
		// System.out.println(str1);

		// Sending Binary
		Out_Socket.println(str);
		// Sending Spliter
		Out_Socket.println(str1);

	}

	public String Handle_Client(BufferedReader In_Socket, PrintWriter Out_Socket) throws IOException {

		System.out.println("Please Enter Text : ");
		Scanner Scan = new Scanner(System.in);
		String Message = Scan.nextLine() + ",";

		if (Message.toUpperCase().startsWith("||Exit||".toUpperCase())) {
			Out_Socket.println(Message);
			System.out.println("Exit from Server : " + In_Socket.readLine());

			return "Exit";

		} else {
			Out_Socket.println("Message" + Message);

			return "Message";
		}

	}

	public String Handle_Message(BufferedReader In_Socket) throws IOException {

		return In_Socket.readLine();
	}

	public void Close_Client(Socket socket) throws IOException {
		// Close Socket
		socket.close();
		System.out.println("Socket is Closed");

	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			new Client();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<String> Construct_Chat(String Input) {
		List<String> Server_Chat;

		Server_Chat = new ArrayList<String>();

		Server_Chat = String_Parse.Convert_String_To_List(Input, "|||||", false);

		System.out.println(Server_Chat);
		return Server_Chat;

		/*
		 * String temp[] = Input.split("|||||");
		 * 
		 * System.out.println(Arrays.toString(temp)); if (temp.length != 0) { for (int i
		 * = 0; i < temp.length; i++) { Server_Chat.add(temp[i]); } }
		 * 
		 * return Server_Chat;
		 * 
		 */
	}

	public String Get_Chat() {
		String Temp = "";
		if (Server_Chat.size() != 0) {
			for (String tet : Server_Chat) {

				Temp += tet;
			}
		}

		return Temp;
	}

	public void Client_Interface_Message(String Input) {
		Pending_Messages.add(Input);
	}

}
