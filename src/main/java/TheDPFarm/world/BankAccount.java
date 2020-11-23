package thedpfarm.world;

import thedpfarm.util.DayAndNightObserver;
import thedpfarm.util.FarmManager;

public class BankAccount implements DayAndNightObserver {
    
    private int farmId;
    private double balance;
    private static final double defaultBalance = 300000.0;
    private int elapsedRiskDays;
    private FarmManager farmManager;

    public BankAccount(int id, FarmManager farmManager) {
        this.farmId = id;
        this.balance = defaultBalance;
        this.farmManager = farmManager;
        elapsedRiskDays = 0;
    }

    public int getFarmId() {
        return farmId;
    }

    public double makeDeposit(double amount) {
        balance += amount;
        World.upgradeFarm();
        elapsedRiskDays = 0;
        return balance;
    }

    public double makeWithdrawl(double amount) {
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public void notifyDay() {
        elapsedRiskDays++;
        if (elapsedRiskDays > 20) {
            farmManager.expellFarm(farmId);
        }
    }

    public void notifyNight() {}
}
