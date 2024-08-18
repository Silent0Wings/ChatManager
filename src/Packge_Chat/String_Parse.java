package Packge_Chat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class String_Parse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Build_Login();

		String Build = "Id9223372036854775807," + "Nameyahya," + "PermissionAdmin," + "LoginYahya123,"
				+ "PasswordTest123,"
				+ "Key92233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807";

		BigInteger Id = new BigInteger(Find_Value("Id", Build, false));
		String Name = Find_Value("Name", Build, false);
		String Permission = Find_Value("Permission", Build, false);
		String Login = Find_Value("Login", Build, false);
		String Password = Find_Value("Password", Build, false);
		BigInteger Key = new BigInteger(Find_Value("Key", Build, false));

		System.out.println("Id : " + Id);
		System.out.println("Name : " + Name);
		System.out.println("Permission : " + Permission);
		System.out.println("Login : " + Login);
		System.out.println("Password : " + Password);
		System.out.println("Key : " + Key);

		Object[] temp3 = Convert_Test(Build, false);

		int[] Binary_split_insctruction = (int[]) temp3[1];
		int[] binary_Split = Split_Binary(String.valueOf(temp3[0]), Binary_split_insctruction, false);

		System.out.println("split_insctruction  : " + Arrays.toString(Binary_split_insctruction));
		System.out.println("Split_Binary        : " + Arrays.toString(binary_Split));

		System.out.println(temp3[0]);
		System.out.println(Arrays.toString((int[]) temp3[1]));

		System.out.println();
		System.out.println("Final               : " + UnConvert_Test(temp3[0].toString(), (int[]) temp3[1]));
		System.out.println(Build.equals(UnConvert_Test(temp3[0].toString(), (int[]) temp3[1])));

	}

	public static List<String> Convert_String_To_List(String Input, String Seperation, Boolean Debug) {

		boolean Stoper = true;

		List<String> Temp_List;

		Temp_List = new ArrayList<String>();
		if (Input.length() == 0) {
			return Temp_List;
		}

		int i = 0;
		String Temp_Core = "";
		String Temp_Seperator = "";

		if (Debug) {

			System.out.println("Lenght : " + Input.length());
		}

		while (Stoper) {

			if (Seperation.contains(Input.charAt(i) + "")) {
				Temp_Seperator += Input.charAt(i);
			}

			Temp_Core += Input.charAt(i);

			if (Temp_Seperator.equals(Seperation)) {

				if (Debug) {

					System.out.println("Temp_Seperator: " + Temp_Seperator);
					System.out.println("Temp_Core: " + Temp_Core);
					System.out.println(Input.substring((Input.length() - Seperation.length()),
							Input.length() - Seperation.length()));

					System.out.println("Start" + ((Temp_Core.length() - Seperation.length())));

					System.out.println("End" + (Temp_Core.length() - Seperation.length()));
				}

				Temp_List.add(Temp_Core.substring(0, Temp_Core.length() - Seperation.length()));
				Temp_Core = "";
				Temp_Seperator = "";

			}

			i++;

			if (i > 999999998 || i == Input.length()) {
				Stoper = false;

			}
		}
		return Temp_List;

	}

	public static String Find_Value(String Pointer, String Source, Boolean Debug) {

		if (Debug == null) {
			Debug = false;
		}

		int Start_Index = Source.indexOf(Pointer) + Pointer.length();

		if (Debug) {

			System.out.println("index start : " + Start_Index);
			System.out.println("Source length : " + Source.length());
		}

		String Holder = "";

		for (int i = Start_Index; i < Source.length(); i++) {

			if (Debug) {

				System.out.println("i : " + i);
			}

			if (Source.charAt(i) != ',') {

				Holder += Source.charAt(i);

			} else {
				break;
			}

		}

		if (Debug) {

			System.out.println("Holder : " + Holder);
		}

		return Holder;
	}

	public static String Find_Value(String Pointer, String Source, char Seperation, Boolean Debug) {

		int Start_Index = Source.indexOf(Pointer) + Pointer.length();

		if (Debug) {

			System.out.println("index start : " + Start_Index);
			System.out.println("Source length : " + Source.length());
		}

		String Holder = "";

		for (int i = Start_Index; i < Source.length(); i++) {

			if (Debug) {

				System.out.println("i : " + i);
			}

			if (Source.charAt(i) != Seperation) {

				Holder += Source.charAt(i);

			} else {
				break;
			}

		}

		if (Debug) {

			System.out.println("Holder : " + Holder);
		}

		return Holder;
	}

	public static Object[] String_To_Int(String Source, Boolean Debug) {

		Object[] holder = new Object[2];

		if (Debug) {

			System.out.println("Source length : " + Source.length());
		}

		// convert Char to int

		int[] Slicer = new int[Source.toString().length()];

		String Temp = "";
		BigInteger Output = new BigInteger("0");
		;
		for (int i = 0; i < Source.length(); i++) {

			Slicer[i] = ((int) (Source.charAt(i)) + "").length();
			Temp += (int) (Source.charAt(i));

			if (Debug) {

				System.out.println("i     : " + i);

				System.out.println("From  : " + Source.charAt(i) + " Value : " + (int) (Source.charAt(i)));

				System.out.println("Next  : " + Temp);

				System.out.println("Slice : " + ((int) (Source.charAt(i)) + "").length());

			}

		}

		Output = new BigInteger(Temp);
		if (Debug) {

			System.out.println(Arrays.toString(Slicer));

		}

		holder[0] = Output;
		holder[1] = Slicer;

		return holder;
	}

	public static String Int_To_String(String Source, Boolean Debug) {
		String Output = "";

		for (int i = 0; i < Source.length(); i++) {

			int test = (int) (Source.charAt(i));
			char b = (char) (test + '0');
			Output += b;

			if (Debug) {
				System.out.println("int : " + test + " Char : " + b);

			}

		}

		return Output;
	}

	public static String Int_To_String(int[] Source, Boolean Debug) {
		String Output = "";

		System.out.println("Source.length " + Source.length);

		for (int i = 0; i < Source.length; i++) {

			char b = (char) (Source[i]);
			Output += b;

			if (Debug) {
				System.out.println("i : " + i);
				System.out.println("int : " + Source[i] + " Char : " + b);

			}

		}

		return Output;
	}

	public static BigInteger Generate_BigInteger(int Size) {

		BigInteger Output = new BigInteger("0");
		;
		BigInteger increment = new BigInteger("1");
		for (int i = 0; i < Size; i++) {

			// int randomInt = new Random().ints(1, 0, 10).findFirst().getAsInt();

			Output = Output
					.add(BigInteger.valueOf((new Random().ints(1, 0, 10).findFirst().getAsInt())).multiply(increment));

			increment = increment.multiply(BigInteger.valueOf(10));

		}

		return Output;
	}

	public static Object[] Convert_To_Binary(BigInteger Source, int[] Int_Slicer, Boolean Debug) {

		Object[] holder = new Object[2];

		int[] Slicer = new int[Int_Slicer.length];

		String Output = "";

		if (Debug) {
			System.out.println("Source length : " + Source.toString().length());

		}

		String String_Source = Source.toString();

		int Increment = 0;
		for (int i = 0; i < Int_Slicer.length; i++) {

			String Case = "";
			for (int j = 0; j < Int_Slicer[i]; j++) {

				Case += String_Source.charAt(Increment + j);

			}

			if (Debug) {

				System.out.println(" Int : " + Case + " | Binary : "
						+ Integer.toBinaryString(Integer.parseInt(Case + "")).toString());

			}

			Increment += Int_Slicer[i];

			String temp = Integer.toBinaryString(Integer.parseInt(Case + "")).toString();

			Output += temp;
			Slicer[i] = temp.length();

		}

		if (Debug) {
			System.out.println(Output.length());
			System.out.println(Arrays.toString(Slicer));

		}

		holder[0] = Output;
		holder[1] = Slicer;

		return holder;
	}

	public static int[] Split_Binary(String Source, int[] Array, Boolean Debug) {
		int[] Output_Array = new int[Array.length];

		int Increment = 0;

		// Split the Binary
		for (int i = 0; i < Array.length; i++) {

			if (Debug) {
				System.out.println("Increment " + Increment);
				System.out.println("Array[i] " + Array[i]);
				System.out.println("Increment + Array[i] " + (Increment + Array[i]));
			}

			if (Array[i] != 0) {

				String Temp = "";

				for (int z = 0; z < Array[i]; z++) {

					Temp += Source.charAt(Increment + z) + "";

				}
				if (Debug) {
					System.out.println("Temp : " + Temp);
				}
				Output_Array[i] = Integer.parseInt(Temp);

				Increment += Array[i];

			}

		}

		if (Debug) {
			System.out.println(Arrays.toString(Output_Array));
		}

		// convert Binary to Int

		return Output_Array;
	}

	public static int[] Binary_To_Int(int[] Source, Boolean Debug) {

		int[] Output = new int[Source.length];
		// convert Binary to Int

		for (int i = 0; i < Source.length; i++) {

			int decimalnumber = Integer.parseInt(Source[i] + "", 2);

			if (Debug) {

				System.out.println("Char : " + Source[i] + "  | int : " + decimalnumber);

			}

			Output[i] = (int) decimalnumber;
		}

		if (Debug) {

			System.out.println(Arrays.toString(Output));

		}

		return Output;
	}

	public static Object[] Convert_Test(String Source, Boolean Debug) {

		Object[] holder = new Object[2];

		int[] Slicer = new int[Source.toString().length()];

		String Output = "";

		if (Debug) {
			System.out.println("Source length : " + Source.toString().length());

		}

		String String_Source = Source.toString();

		int Increment = 0;
		for (int i = 0; i < Source.length(); i++) {

			int temp_int = (int) (Source.charAt(i));
			String temp_Binary = Integer.toBinaryString(Integer.parseInt(temp_int + "")).toString();
			Slicer[i] = temp_Binary.length();
			Output += temp_Binary;

			if (Debug) {

				System.out.println("Char : " + Source.charAt(i) + " | Int : " + temp_int + " | Binary : " + temp_Binary
						+ " | Lenght : " + temp_Binary.length());

			}

		}

		if (Debug) {
			System.out.println(Output.length());
			System.out.println(Arrays.toString(Slicer));

		}

		holder[0] = Output;
		holder[1] = Slicer;

		return holder;
	}

	public static String UnConvert_Test(String Source, int[] Array) {

		return Int_To_String(Binary_To_Int(Split_Binary(Source, Array, false), false), false) + "";

	}

	public static String Build_Login() {

		/*
		 * 
		 * 
		 * int n=0; boolean flag;
		 * 
		 * do { try { Scanner sc = new Scanner(System.in);
		 * System.out.println("Enter integer value only  "); n=sc.nextInt(); flag=false;
		 * } catch(Exception e) { // accept integer only.
		 * System.out.println("Enter only integer value.."+e); flag=true; } }
		 * while(flag);
		 * 
		 * System.out.println("The Integer Value Entered is "+n);
		 * 
		 * }
		 * 
		 */

		System.out.println("");

		System.out.println("Please Enter Id");
		Scanner Scan_Id = new Scanner(System.in);
		Scan_Id.skip("[^0-9]*");
		Scan_Id.useDelimiter("[^0-9]+");
		BigInteger Id = Scan_Id.nextBigInteger();
		if (Id.toString().length() < 19) {
			return "--Id To Short";
		}

		System.out.println("Please Enter Name");
		Scanner Scan_Name = new Scanner(System.in);
		String Name = Scan_Name.nextLine();
		if (Name == "") {
			return "--No Name";
		}

		System.out.println("Please Enter Permission");
		Scanner Scan_Permission = new Scanner(System.in);
		String Permission = Scan_Permission.nextLine();

		System.out.println("Please Enter Login");
		Scanner Scan_Login = new Scanner(System.in);
		String Login = Scan_Login.nextLine();
		if (Login == "") {
			return "--No Login";
		}

		System.out.println("Please Enter Password");
		Scanner Scan_Password = new Scanner(System.in);
		String Password = Scan_Password.nextLine();
		if (Password.matches(".*[0-9].*") == false & Password.matches(".*[a-zA-Z]+.*")) {
			// Password= "Client_Temp";
			return "--Enter a password whit characters and numbers";
		}

		System.out.println("Please Enter Key");
		Scanner Scan_Key = new Scanner(System.in);
		Scan_Key.skip("[^0-9]*");
		Scan_Key.useDelimiter("[^0-9]+");
		BigInteger Key = Scan_Key.nextBigInteger();
		if (Id.toString().length() < 152) {
			return "--Key To Short152";
		}

		String Output = "Id" + Id + "," + "\n" + "Name" + Name + "," + "\n" + "Permission" + Permission + "," + "\n"
				+ "Login" + Login + "," + "\n" + "Password" + Password + "," + "\n" + "Key" + Key + ",";

		System.out.println(Output);
		System.out.println("");

		return Output;
	}

	public boolean Login_Build_Intact(String Input) {
		String Build = "Id9223372036854775807," + "Nameyahya," + "PermissionAdmin," + "LoginYahya123,"
				+ "PasswordTest123,"
				+ "Key92233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807922337203685477580792233720368547758079223372036854775807";

		Client_Profile Current_Client_Profile = Build_Client_Profile(Build);

		if ((Current_Client_Profile.Id.toString().length() > 19) && (Current_Client_Profile.Name.length() > 5)
				&& (Current_Client_Profile.Login.length() > 5) && (Current_Client_Profile.Password.length() > 5)
				&& (Current_Client_Profile.Key.toString().length() > 200)

		) {

			return true;

		} else {

			return false;

		}

	}

	public Client_Profile Build_Client_Profile(String Input) {
		Client_Profile Current_Client_Profile = new Client_Profile();
		Current_Client_Profile.Id = new BigInteger(Find_Value("Id", Input, false));
		Current_Client_Profile.Name = Find_Value("Name", Input, false);
		Current_Client_Profile.Permission = Find_Value("Permission", Input, false);
		Current_Client_Profile.Login = Find_Value("Login", Input, false);
		Current_Client_Profile.Password = Find_Value("Password", Input, false);
		Current_Client_Profile.Key = new BigInteger(Find_Value("Key", Input, false));

		return Current_Client_Profile;
	}

}
