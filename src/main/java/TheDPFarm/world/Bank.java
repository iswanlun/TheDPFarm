package TheDPFarm.world;

import java.util.ArrayList;

public class Bank {
    
    static class WorldBank {

        private ArrayList<BankAccount> _accounts = new ArrayList<>();

        public void addAccount(BankAccount newAccount) {
            _accounts.add(newAccount);
        }

        public void removeAccount(BankAccount oldAccount) {
            _accounts.remove(oldAccount);
        }

        public BankAccount findAccount(int id) {
            for (BankAccount a : _accounts) {
                if(a.getFarmId() == id) {
                    return a;
                }
            }
            return null;
        }
    }
}
