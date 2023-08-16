package ponggu;

public class Transactions {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("Hong", "010-1234-5678");
        BankAccount account2 = new BankAccount("Hong", "010-8765-4321", 0);
        BankAccount account3 = new BankAccount("Kang", "010-1111-2222", 123456);

        System.out.println(account1.toString());
        System.out.println(account2.toString());
        System.out.println(account3.toString());

        account1.deposit(50000);
        account1.withdraw(10000);
    }
}

