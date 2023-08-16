import java.util.Random;

public class BankAccount {
	Random random = new Random();
	
	private String strName, strPhoneNumber;
	private int nAccountNumber;
	private int nBalance = 0;
	private boolean bDormantAccount = false;
	private final double RATE=0.02;
	
	// constructer
	public BankAccount() {
		nAccountNumber = random.nextInt(900000) + 100000;
	}
	public BankAccount(String name) {
		nAccountNumber = random.nextInt(900000) + 100000;
		strName = name;
	}
	
	public BankAccount(String name, String pnum) {
		nAccountNumber = random.nextInt(900000) + 100000;
		strName = name;
		strPhoneNumber = pnum;
	}
	
	public BankAccount(String name, String pnum, int balance) {
		nAccountNumber = random.nextInt(900000) + 100000;
		strName = name;
		strPhoneNumber = pnum;
		nBalance = balance;
	}
	
	public BankAccount(String name, String pnum, int balance, boolean bool) {
		nAccountNumber = random.nextInt(900000) + 100000;
		strName = name;
		strPhoneNumber = pnum;
		nBalance = balance;
		bDormantAccount = bool;
	}
	
	
	// get set
	public String getStrName() {return strName;}
	public void setStrName(String name) {strName=name;}
	
	public String getStrPhoneNumber() {return strPhoneNumber;}
	public void setStrPhoneNumber(String pnum) {strPhoneNumber = pnum;}
	
	public int getNAccountNumber(){return nAccountNumber;}
	
	public int getNBalance(){return nBalance;}
	public void setNBalance(int balance){nBalance = balance;}
	
	public boolean getBDormantAccount(){return bDormantAccount;}
	public void setBDormantAccount(boolean bool){bDormantAccount = bool;}
	
	
	// func
	public int deposit(int moneyIn) {
		if (bDormantAccount == true) return nBalance;
		nBalance += moneyIn;
		return nBalance;
	}
	
	public int withdraw(int moneyOut) {
		if (bDormantAccount == true) return nBalance;
		nBalance  = (nBalance>=moneyOut)?(nBalance-moneyOut):nBalance;
		return nBalance;
	}
	
	public int addInterest() {
		if (bDormantAccount == true) return nBalance;
		nBalance += nBalance*RATE;
		return nBalance;
	}
	
	public int combineAccount(BankAccount account) {
		if (bDormantAccount == true) return nBalance;
		if (strName.equals(account.strName))
			nBalance += account.nBalance;
		return nBalance;
	}
	
	// tostring
	public String toString() {
		if (bDormantAccount == true)
			return strName + ", " + nAccountNumber + ", " + nBalance + "원, " + "dormant account";
		return strName + ", " + nAccountNumber + ", " + nBalance + "원";
	}
	
}
