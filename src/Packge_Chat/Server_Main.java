package Packge_Chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Server_Main {

	public Server_Main() throws Exception {

		System.out.println("________________________________________________________________________________");
		ServerSocket Main_server_socket = new ServerSocket(2020); // new port
		System.out.println("Port 2020 : Opened");

		All_ServerThread = new ArrayList<ServerThread>();

		Server_Chat = new ArrayList<String>();

		while (true) {

			System.out.println(Get_Chat());

			Socket socket = Main_server_socket.accept();
			System.out.println("Client " + socket.getInetAddress() + "Is Connected");

			ServerThread server_Thread = new ServerThread(socket, this);
			Thread thread = new Thread(server_Thread);
			thread.start();
			All_ServerThread.add(server_Thread);

		}

	}

	public void Broadcast(String Output, ServerThread Exeption) {

		if (All_ServerThread.size() != 0) {
			for (ServerThread test : All_ServerThread) {
				if (test.equals(Exeption)) {
					return;
				}
				test.Handle_Broadcast(Output);
			}
		}

	}

	public String Update_Main_Chat(String Output, ServerThread _Thread) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String Temp = "Message" + Output + "," + "Time" + dtf.format(now) + "," + "Id" + _Thread.Get_Id() + ","
				+ "|||||";

		Server_Chat.add(Temp);

		return Temp;
	}

	private int Client_Number = 1;

	public int Get_Client_Number() {

		return Client_Number++;
	}

	protected long Secrect_Key = 9223372036854775807L;
	protected BigInteger Server_Key = new BigInteger(
			"92233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807");

	public boolean authentify(BigInteger Key) {

		if (Server_Key.equals(Key))

			return true;

		else
			return false;

	}

	public int Server_Size = 100;
	public List<ServerThread> All_ServerThread;
	private List<String> Server_Chat;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			new Server_Main();

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}

	}

	public String Get_Chat() {
		String Temp = "";
		if (Server_Chat.size() != 0) {
			for (String tet : Server_Chat) {

				Temp += tet;
			}
		}

		System.out.println(Temp);
		return Temp;
	}
}
