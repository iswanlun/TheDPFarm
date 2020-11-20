package thedpfarm.world;

public class BankAccount {
    
    private int farmId;
    private double balance;
    private static final double defaultBalance = 300000.0;

    public BankAccount(int id) {
        this.farmId = id;
        this.balance = defaultBalance;
    }

    public int getFarmId() {
        return farmId;
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
