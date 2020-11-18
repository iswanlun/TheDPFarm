package TheDPFarm.world;

public class BankAccount {
    
    private int FarmId;
    private double balance;

    public BankAccount(int id, double startingBalance) {
        this.FarmId = id;
        this.balance = startingBalance;
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

}
