package thedpfarm.util;

import thedpfarm.animals.AnimalState;
import thedpfarm.plants.PlantState.State;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.Acre.UsageType;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

/**
 * This class contains the dialog methods for printing to the console.
 * All println's are contained here, with a few exceptions.
 */
public class SimulationDialog {

    public void help() {
        System.out.println("Refer to the documentation in Doc.md.");
    }

    public void welcome() {
        System.out.println("Welcome to TheDPFarm Simulator. Type 'help' for help.");
    }
    
    public void defaultMsg(String[] args) {
        System.out.print("The command string : ");
        for (String s : args) {
            System.out.print(" " + s);
        }
        System.out.println(" does not match any known parameter form.");
    }

    public void harvestMsg(String assetType, int farmId) {
        System.out.println(assetType + " on farm number " + farmId + " is ready for harvest.");
    }

    public void collectMsg(String assetType, int farmId) {
        System.out.println(assetType + " on farm number " + farmId + " can be collected.");
    }
    
    public void purchaseMade(double balance) {
        System.out.println("Purchase made, current balance : " + balance);
    }

    public void insufficientFunds(double price) {
        System.out.println("You have insufficient funds to make a purchase of : " + price);
    }

    public void saleMade(double balance) {
        System.out.println("Sale made, current balance : " + balance);
    }

    public void harvestSold(double balance) {
        System.out.println("Harvest sold, current balance : " + balance);
    }

    public void collectSold(double balance) {
        System.out.println("Collection sold, current balance : " + balance);
    }

    public void insufficientAmount(int amount) {
        System.out.println("You do not have enough to sell : " + amount);
    }

    public void insufficientLand(int amount) {
        System.out.println("You do not have acres to add : " + amount);
    }

    public void badParameter(String param) {
        System.out.println("The paramater : " + param + " is invalid.");
    }

    public void statusMessage() {
        System.out.println("----- Farm Status -----------------------------");
        System.out.println(" Farm Id :  " + (World.getFarm().getFarmId() + 1));
        System.out.println(" Farm level :  " + World.getFarm().getLevel());
        System.out.println(" Farm size in acres :  " + World.getFarm().size());
        if (World.TimeManager.getTime()) {
            System.out.println(" Time of day :  Day");
        } else {
            System.out.println(" Time of day :  Night");
        }
        System.out.println(" Number of empty acres :  " + World.getFarm().getEmptyAcres().size());
        System.out.println(" Number of acres with crops :  " + World.getFarm().numCropsAcres());
        System.out.println(" Number of acres with livestock :  " + World.getFarm().numLivestockAcres());
        System.out.println(" Account balance for this farm :  " + Bank.accountBalance(World.getFarm().getFarmId()));
        System.out.println("-----------------------------------------------");
        System.out.flush();
    }

    public void cropAudit(int num, UsageType uType, AssetType aType, State state) {
        System.out.printf("%20d %20s %20s %20s%n", num, uType.toString(), aType.toString(), state.toString());
        System.out.flush();
    }

    public void livestockAudit(int num, UsageType uType, AssetType aType, AnimalState.State state) {
        System.out.printf("%20d %20s %20s %20s%n", num, uType.toString(), aType.toString(), state.toString());
    }

    public void auditTable() {
        System.out.printf("%20s %20s %20s %20s%n", "#", "Usage Type", "Asset Type", "State");
        System.out.flush();
    }

    public void removeWeedsCost() {
        System.out.println(" Cost to remove all weeds :  " + World.getFarm().weedCost());
    }

    public void weedsRemoved() {
        System.out.println("Weeds removed.");
    }

    public void tooFewArguments() {
        System.out.println("Too few arguments provided.");
    }
    
    public void badArgumentType() {
        System.out.println("The arguments provided are malformed.");
    }

}
