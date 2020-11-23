package thedpfarm.world;

import java.util.ArrayList;
import thedpfarm.util.FarmManager;

public class Bank {
    
    private static ArrayList<BankAccount> _accounts = new ArrayList<>();

    public static void addAccount(int id, FarmManager farmManager) {
        _accounts.add(new BankAccount(id, farmManager));
    }

    public static void removeAccount(BankAccount oldAccount) {
        _accounts.remove(oldAccount);
    }

    public static BankAccount findAccount(int id) {
        for (BankAccount a : _accounts) {
            if (a.getFarmId() == id) {
                return a;
            }
        }
        return null;
    }
    
    public static double accountBalance(int id) {
        for (BankAccount a : _accounts) {
            if (a.getFarmId() == id) {
                return a.getBalance();
            }
        }
        return 0;
    }
}
