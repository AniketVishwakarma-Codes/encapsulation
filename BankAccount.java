public class BankAccount {

    private String accountHolder;
    private double balance;
    private int pin;
    private int failedAttempts;
    private boolean isLocked;

    public BankAccount(String accountHolder, double balance, int pin) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pin = pin;
        this.failedAttempts = 0;
        this.isLocked = false;
    }

    public void deposit(double amount) {
        if (isLocked) {
            System.out.println("Account is locked. Deposit is not allowed");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }

        balance += amount;
        System.out.println("Deposit successful. New Balance: " + balance);
    }

    public void withdraw(double amount, int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked. Withdrawal is not allowed.");
            return;
        }

        if (!validatePin(enteredPin)) {
            failedAttempts++;
            System.out.println("Wrong Pin.");

            if (failedAttempts == 3) {
                isLocked = true;
                System.out.println("account locked due to failed attempts");
            }
            return;
        }

        failedAttempts = 0;
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
        } else if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        }
    }

    private boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double checkBalance(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked");
            return -1;
        }

        if (!validatePin(enteredPin)) {
            System.out.println("Invalid pin");
            return -1;
        }

        return balance;
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Rachana", 1233450, 8787);
        acc.deposit(2333);
        acc.withdraw(234, 1222);
        acc.withdraw(2334, 8989);
        acc.withdraw(2345672, 8787);
        acc.withdraw(2333333, 8985);
        acc.deposit(2343);
        acc.withdraw(1000, 8787);
        acc.withdraw(2333, 1122);
        acc.withdraw(345455, 7676);
        System.out.println("balance: " + acc.checkBalance(8787));
        acc.withdraw(477677, 6756);
        System.out.println("balance: " + acc.checkBalance(8787));
    }
}
