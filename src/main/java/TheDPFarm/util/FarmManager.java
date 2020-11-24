package thedpfarm.util;

import java.util.Vector;
import thedpfarm.animals.AnimalState.State;
import thedpfarm.animals.Chicken;
import thedpfarm.animals.Cow;
import thedpfarm.animals.Hog;
import thedpfarm.animals.Sheep;
import thedpfarm.levels.Farm;
import thedpfarm.levels.FarmLevelOne;
import thedpfarm.plants.Corn;
import thedpfarm.plants.Mushrooms;
import thedpfarm.plants.Soybeans;
import thedpfarm.plants.Wheat;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.world.Bank;
import thedpfarm.world.World;


public class FarmManager implements TerminalStateListener {

    private static final int farmPrice = 100000;
    private static final int acrePrice = 5000;
    private static final int defaultFarmSize = 50;
    private static final int dogPricePerAcre = 2000;
    private static final int groundCoverPerAcre = 2500;

    private SimulationDialog dlg;
    private AcreDirector acreDir;


    /**
     * Constructor for the manager.
     * @param dlg Simulation dialog for alerts.
     */
    public FarmManager(SimulationDialog dlg) {
        this.dlg = dlg;
        this.acreDir = new AcreDirector(dlg);
        World.addTerminalStateListener(this);
    }

    /**
     * Sells off acres of a farm.
     * @param string Number of acres to sell.
     */
    public void sellAcres(String string) {
        int toSell = Integer.parseInt(string);
        if (toSell <= World.getFarm().size() - 5) {
            World.getFarm().trimFarm(toSell);
            double bal = Bank.findAccount(World.getFarm().getFarmId())
                .makeDeposit(toSell * acrePrice);
            dlg.saleMade(bal);
        } else {
            dlg.insufficientAmount(toSell);
        }
    }

    /**
     * Purchasing of more acres for a farm.
     * @param string Number of acres to purchase.
     */
    public void buyAcres(String string) {
        int numAcres = Integer.parseInt(string);
        double amount = numAcres * acrePrice * World.getFarm().getTaxRate();
        if (amount < Bank.accountBalance(World.getFarm().getFarmId())) {
            Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(amount);
            acreDir.createAcres(numAcres);
        } else {
            dlg.insufficientFunds(amount);
        }
    }

    /**
     * Adds ground cover to a farm.
     * @param string Number of acres.
     */
    public void addGroundCover(String string) {
        int groundCoverAcres = Integer.parseInt(string);
        boolean success = World.getFarm().addGroundCover(groundCoverAcres);
        if (success) {
            double balance = Bank.findAccount(World.getFarm().getFarmId())
                .makeWithdrawl(
                groundCoverAcres * groundCoverPerAcre * World.getFarm().getTaxRate());
            dlg.addedGroundCover(balance);
        } else {
            dlg.failedToAddGroundCover();
        }
    }

    /**
     * Adds dog coverage to a farm.
     * @param string Number of acres.
     */
    public void addDogs(String string) {
        int dogCoverAcres = Integer.parseInt(string);
        boolean success = World.getFarm().addDogs(dogCoverAcres);
        if (success) {
            double balance = Bank.findAccount(World.getFarm().getFarmId())
                .makeWithdrawl(dogCoverAcres * dogPricePerAcre * World.getFarm().getTaxRate());
            dlg.addedDogs(balance);
        } else {
            dlg.failedToAddDogs();
        }
    }

    /**
     * Allows for farms to be switched between.
     * @param string Alternate farm id.
     */
    public void switchFarm(String string) {
        World.setFarm(Integer.parseInt(string));
    }

    /**
     * Creates a new farm.
     */
    public void newFarm() {
        if (World.getNumFarms() == 0) {
            World.addFarm(new FarmLevelOne(defaultFarmSize));
            Bank.addAccount(World.getFarm().getFarmId(), this);
        } else {
            if (Bank.accountBalance(World.getFarm().getFarmId()) > farmPrice + 1000) {
                double newBalance = Bank.findAccount(World.getFarm().getFarmId())
                    .makeWithdrawl(farmPrice);
                World.addFarm(new FarmLevelOne(defaultFarmSize));
                Bank.addAccount(World.getFarm().getFarmId(), this);
                dlg.purchaseMade(newBalance);
            } else {
                dlg.insufficientFunds(farmPrice);
            }
        }
    }

    /**
     * Audits crops throug the farm class.
     */
    public void auditCrops() {
        dlg.auditTable();
        World.getFarm().auditCrops(dlg);
        dlg.removeWeedsCost();
    }

    /**
     * Audits livestock throught the farm class.
     */
    public void auditLivestock() {
        dlg.auditTable();
        World.getFarm().auditLivestock(dlg);
    }

    /**
     * Removes weeds from a farm.
     */
    public void removeWeeds() {
        Bank.findAccount(World.getFarm().getFarmId())
            .makeWithdrawl(World.getFarm().weedCost());
        World.getFarm().removeWeeds();
        dlg.weedsRemoved();
    }

    /**
     * Allows for planting of crops.
     * @param type Type of crop.
     * @param amount Number of acres.
     */
    public void plant(String type, String amount) {
        int amt = Integer.parseInt(amount);
        AssetType assetType = stringToEnumConverter(type);
        double price = amt * assetPriceCalc(assetType) * World.getFarm().getTaxRate();

        if (price > Bank.accountBalance(World.getFarm().getFarmId())) {
            dlg.insufficientFunds(price);
            return;
        }

        Vector<Acre> empties = World.getFarm().getEmptyAcres();
        int emptyAcres = empties.size();

        if (amt <= emptyAcres) {
            int counter = 0;
            for (Acre a : empties) {
                acreDir.constructCropAcre(a, stringToEnumConverter(type));
                counter++;
                if (counter == amt) {
                    break;
                }
            }
            dlg.purchaseMade(Bank.accountBalance(World.getFarm().getFarmId()));
        } else {
            dlg.insufficientLand(amt);
        }
    }

    /**
     * Allows for harvesting acres.
     * @param string AssetType acre to be harvested.
     */
    public void harvestAcres(String string) {
        AssetType type = stringToEnumConverter(string);
        for (Acre a : World.getFarm().getHarvestAcres(type)) {
            a.harvest(dlg);
        }
    }

    /**
     * Allows for livestock products to be collected.
     * @param string Type of livestock to collect from.
     */
    public void collectProducts(String string) {
        AssetType type = stringToEnumConverter(string);
        Vector<Acre> vec = World.getFarm().getCollectAcres(type);
        for (Acre a : vec) {
            a.collect(dlg);
        }
    }

    /**
     * Used to raise animals on a farm.
     * @param type Aniumal type.
     * @param amount Number of acres.
     * @throws IllegalArgumentException if illegal arguments passed in.
     */
    public void raise(String type, String amount) throws IllegalArgumentException {
        int amt = Integer.parseInt(amount);
        AssetType assetType = stringToEnumConverter(type);
        double price = amt * assetPriceCalc(assetType) * World.getFarm().getTaxRate();

        if (price > Bank.accountBalance(World.getFarm().getFarmId())) {
            dlg.insufficientFunds(price);
            return;
        }

        Vector<Acre> empties = World.getFarm().getEmptyAcres();
        int emptyAcres = empties.size();

        if (amt <= emptyAcres) {
            int counter = 0;
            for (Acre a : empties) {
                acreDir.constructLivestockAcre(a, stringToEnumConverter(type));
                counter++;
                if (counter == amt) {
                    break;
                }
            }
            dlg.purchaseMade(Bank.accountBalance(World.getFarm().getFarmId()));
        } else {
            dlg.insufficientLand(amt);
        }
    }

    /**
     * Defunct condition, farm id used.
     * @param farmId The farm id to be used.
     */
    public void defunct(int farmId) {
        Farm toRemove = World.getFarm(farmId);
        toRemove.purge();
        World.removeFarm(toRemove);
        dlg.expellFarm(farmId);
    }

    /**
     * If a defunct condition occurs, farm object.
     */
    public void defunct(Farm farm) {
        dlg.expellFarm(farm.getFarmId());
        farm.purge();
        World.removeFarm(farm);
    }

    /**
     * If a success condition occurs.
     * @param farmId Farm id to be used.
     */
    public void success(int farmId) {
        Farm toRemove = World.getFarm(farmId);
        toRemove.purge();
        World.removeFarm(toRemove);
        dlg.simulationCompleted(farmId);
    }

    /**
     * If a success condition occurs, farm object.
     */
    public void success(Farm farm) {
        dlg.simulationCompleted(farm.getFarmId());
        farm.purge();
        World.removeFarm(farm);
    }
    
    /**
     * Converts a string to an AssetType enum.
     * @param string The string to convert.
     * @return The AssetType.
     * @throws IllegalArgumentException If an illegal string is passed in.
     */
    private AssetType stringToEnumConverter(String string) throws IllegalArgumentException {
        switch (string) {
            case "corn": return AssetType.CORN;
            case "mushrooms": return AssetType.MUSHROOMS;
            case "soybeans": return AssetType.SOYBEANS;
            case "wheat": return AssetType.WHEAT;
            case "chicken": return AssetType.CHICKEN;
            case "cattle": return AssetType.COW;
            case "hog": return AssetType.HOG;
            case "sheep": return AssetType.SHEEP;
            default: 
                dlg.badParameter(string);
                return null;
        }
    }

    /**
     * Calculates the price of an asset.
     * @param type The asset type.
     * @return Its price.
     */
    private double assetPriceCalc(AssetType type) {
        switch (type) {
            case CORN: Corn c = new Corn(null, 0);
                       return c.getCostPerAcre();
            case MUSHROOMS: Mushrooms m = new Mushrooms(null, 0);
                        return m.getCostPerAcre();
            case SOYBEANS: Soybeans s = new Soybeans(null, 0);
                        return s.getCostPerAcre();
            case WHEAT: Wheat w = new Wheat(null, 0);
                        return w.getCostPerAcre();
            case CHICKEN: Chicken l = new Chicken(State.HEALTHY, 0);
                        return l.getBatchesPerAcre() * l.getBatchPrice();
            case COW: Cow t = new Cow(State.HEALTHY, 0);
                        return t.getBatchesPerAcre() * t.getBatchPrice();
            case HOG: Hog h = new Hog(State.HEALTHY, 0);
                        return h.getBatchesPerAcre() * h.getBatchPrice();
            case SHEEP: Sheep r = new Sheep(State.HEALTHY, 0);
                        return r.getBatchesPerAcre() * r.getBatchPrice();
            default: 
                return 0;
        }
    }
}
