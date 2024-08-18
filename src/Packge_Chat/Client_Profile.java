package Packge_Chat;

import java.math.BigInteger;

public class Client_Profile {

	protected BigInteger Id;
	protected String Name;
	protected String Permission;
	protected String Login;
	protected String Password;
	protected BigInteger Key;

	@SuppressWarnings("unused")
	private Boolean Compare_Key(BigInteger Value) {

		return Value.equals(Key);
	}

}
