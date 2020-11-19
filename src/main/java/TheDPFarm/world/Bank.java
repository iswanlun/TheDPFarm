package TheDPFarm.world;

import java.util.ArrayList;

public class Bank {
    
    private static ArrayList<BankAccount> _accounts = new ArrayList<>();

    public static void addAccount(BankAccount newAccount) {
        _accounts.add(newAccount);
    }

    public static void removeAccount(BankAccount oldAccount) {
        _accounts.remove(oldAccount);
    }

    public static BankAccount findAccount(int id) {
        for (BankAccount a : _accounts) {
            if(a.getFarmId() == id) {
                return a;
            }
        }
        return null;
    }
}
