package thedpfarm.world;

import thedpfarm.util.FarmManager;

public class BankAccount {
    
    private int farmId;
    private double balance;
    private static final double defaultBalance = 300000.0;


    public BankAccount(int id, FarmManager farmManager) {
        this.farmId = id;
        this.balance = defaultBalance;
    }

    public int getFarmId() {
        return farmId;
    }

    /**
     * Makes a deposit to this account.
     * @param amount Amount to deposit.
     * @return The new balance.
     */
    public double makeDeposit(double amount) {
        balance += amount;
        World.upgradeFarm();
        return balance;
    }

    /**
     * Makes a withdrawl from this account.
     */
    public double makeWithdrawl(double amount) {
        balance -= amount;
        World.inspectFarm();
        return balance;
    }

    public double getBalance() {
        return balance;
    }

}
