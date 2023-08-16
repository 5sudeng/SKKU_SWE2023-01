package ponggu;
import java.util.Random;

class BankAccount {
    private String strName;
    private String strPhoneNumber;
    private int nAccountNumber;
    private int nBalance;
    private boolean bDormantAccount;
    private static final double RATE = 0.02;

    // 생성자들
    public BankAccount(String strName, String strPhoneNumber, int nBalance) {
        this.strName = strName;
        this.strPhoneNumber = strPhoneNumber;
        this.nAccountNumber = generateAccountNumber();
        this.nBalance = nBalance;
        this.bDormantAccount = false;
    }

    public BankAccount(String strName, String strPhoneNumber) {
        this(strName, strPhoneNumber, 0);
    }

    // Getter/Setter 메서드들
    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrPhoneNumber() {
        return strPhoneNumber;
    }

    public void setStrPhoneNumber(String strPhoneNumber) {
        this.strPhoneNumber = strPhoneNumber;
    }

    public int getnAccountNumber() {
        return nAccountNumber;
    }

    public int getnBalance() {
        return nBalance;
    }

    public boolean isbDormantAccount() {
        return bDormantAccount;
    }

    // 예금 메서드
    public void deposit(int amount) {
        if (!bDormantAccount) {
            nBalance += amount;
        }
    }

    // 인출 메서드
    public void withdraw(int amount) {
        if (!bDormantAccount && amount <= nBalance) {
            nBalance -= amount;
        }
    }

    // 이자 추가 메서드
    public void addInterest() {
        if (!bDormantAccount) {
            nBalance += (int) (nBalance * RATE);
        }
    }

    // 다른 계좌와 잔액 합산 메서드
    public void combineAccount(BankAccount other) {
        if (!bDormantAccount && !other.isbDormantAccount() && this.strName.equals(other.getStrName())) {
            this.nBalance += other.getnBalance();
            other.bDormantAccount = true;
        }
    }

    // 계좌 번호 랜덤 생성 메서드
    private int generateAccountNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    // 객체 출력을 위한 toString() 메서드
    @Override
    public String toString() {
        String status = bDormantAccount ? "dormant account" : "";
        return strName + ", " + nAccountNumber + ", " + nBalance + "원, " + status;
    }
}
