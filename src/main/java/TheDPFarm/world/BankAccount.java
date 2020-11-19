package TheDPFarm.world;

public class BankAccount {
    
    private int FarmId;
    private double balance;
    private final double defaultBalance = 5000.0;

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
