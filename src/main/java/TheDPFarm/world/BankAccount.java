package thedpfarm.world;

public class BankAccount {
    
    private int FarmId;
    private double balance;
    private static final double defaultBalance = 300000.0;

    public BankAccount(int id) {
        this.FarmId = id;
        this.balance = defaultBalance;
    }

    public int getFarmId() {
        return FarmId;
    }

    public double makeDeposit(double amount) {
        balance += amount;
        return balance;
    }

    public double makeWithdrawl(double amount) {
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }
}
