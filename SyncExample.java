import java.util.Scanner;
class BankAccount {
    private int balance;
    BankAccount(int balance) {
        this.balance = balance;
    }
    public synchronized void withdraw(int amount, String user) {
       System.out.println(user + " trying to withdraw " + amount);
        if (balance >= amount) {
            System.out.println("Processing for " + user);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            balance -= amount;
            System.out.println(user + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance for " + user);
        }
    }
}
class UserThread extends Thread {
    BankAccount account;
    String user;
    int amount;
    UserThread(BankAccount acc, String user, int amount) {
        this.account = acc;
        this.user = user;
        this.amount = amount;
    }
    public void run() {
        account.withdraw(amount, user);
    }}
public class SyncExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        int balance = sc.nextInt();
        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();
        BankAccount account = new BankAccount(balance);
        UserThread t1 = new UserThread(account, "User1", amount);
        UserThread t2 = new UserThread(account, "User2", amount);
        t1.start();
        t2.start();
    }
}
