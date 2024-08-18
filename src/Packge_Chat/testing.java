package Packge_Chat;

import java.util.List;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "Messagetest,Time2022/05/23 19:52:04,Id9223372036854775807,|||||MessageBefore,Time2022/05/23 19:52:06,Id9223372036854775807,|||||MessageEnd Exit,Time2022/05/23 19:52:31,Id9223372036854775807,|||||";

		List<String> Test = String_Parse.Convert_String_To_List(test, "|||||", false);

		System.out.println(Test);

		System.out.println(Test.size());

		System.out.println(Test.get(0));
		System.out.println(Test.get(1));
		System.out.println(Test.get(2));

	}

}
