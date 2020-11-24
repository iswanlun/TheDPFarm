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

    /**
     * Returns a specific account.
     * @param id The id of the account to return.
     * @return The account itself.
     */
    public static BankAccount findAccount(int id) {
        for (BankAccount a : _accounts) {
            if (a.getFarmId() == id) {
                return a;
            }
        }
        return null;
    }
    
    /**
     * Gets the current account balance.
     * @param id The account / farm number to use.
     * @return The accounts balance.
     */
    public static double accountBalance(int id) {
        for (BankAccount a : _accounts) {
            if (a.getFarmId() == id) {
                return a.getBalance();
            }
        }
        return 0;
    }
}
