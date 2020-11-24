package thedpfarm.levels;

import java.util.ArrayList;
import java.util.Vector;

import thedpfarm.animals.AnimalState;
import thedpfarm.plants.PlantState.State;
import thedpfarm.util.Acre;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.Acre.UsageType;
import thedpfarm.util.SimulationDialog;
import thedpfarm.world.Bank;


public class FarmLevelOne implements Farm {

    private int id;
    private ArrayList<Acre> acres;
    private double taxRateOne = 1.123;
    protected int dogCoverage = 0;
    protected int groundCoverage = 0;

    /**
     * Constructor which creates a new farm of some size.
     * @param size The number of acres of the new farm.
     */
    public FarmLevelOne(int size) {
        acres = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            acres.add(new Acre(UsageType.EMPTY, AssetType.EMPTY));
        }
    }

    protected FarmLevelOne(Farm farm) {
        this.id = farm.getFarmId();
        this.acres = ((FarmLevelOne) farm).getAllAcres();
        this.dogCoverage = farm.getDogCoverage();
        this.groundCoverage = farm.getGroundCoverage();
    }

    protected ArrayList<Acre> getAllAcres() {
        return acres;
    }

    public int getFarmId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int size() {
        return acres.size();
    }
    
    public void expandFarm(Acre addition) {
        acres.add(addition);
    }

    /**
     * Removes the number of acres specified from the farm.
     */
    public void trimFarm(int removal) {
        Vector<Acre> toBeRemoved = new Vector<>();
        for (int i = 0; i < removal; i++) {
            acres.get(i).renewAcre();
            toBeRemoved.add(acres.get(i));
        }
        acres.removeAll(toBeRemoved);
    }

    public Acre getSpecificAcre(int index) {
        return acres.get(index);
    }

    public double getTaxRate() {
        return taxRateOne;
    }

    /**
     * Gets a vector of acres on which there is nothing growing / living.
     */
    public Vector<Acre> getEmptyAcres() {
        Vector<Acre> empties = new Vector<>();
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.EMPTY)) {
                empties.add(a);
            } 
        }
        return empties;
    }
    
    /**
     * Returns a vector of acres which are harvest ready. Acres can be
     * both Crop or Livestock acres, every thing ready is harvested.
     */
    public Vector<Acre> getHarvestAcres(AssetType type) {
        Vector<Acre> harvestAcres = new Vector<>();
        for (Acre a : acres) {
            if (a.getAssetType().equals(type)) {
                if (a.getUsageType().equals(UsageType.CROPS)
                    && a.getCrop().getState().equals(State.HARVESTREADY)) {
                    harvestAcres.add(a);             
                } else if (a.getUsageType().equals(UsageType.LIVESTOCK)
                    && a.getLivestock().getState().equals(AnimalState.State.HARVESTREADY)) {
                    harvestAcres.add(a);
                }
            }
        }
        return harvestAcres;
    }

    /**
     * Returns a vector of acres on which livestock have indicated that 
     * products can be collected from them.
     */
    public Vector<Acre> getCollectAcres(AssetType type) {
        Vector<Acre> collectAcres = new Vector<>();
        for (Acre a : acres) {
            if (a.getAssetType().equals(type)) {
                if (a.getUsageType().equals(UsageType.LIVESTOCK)
                    && a.getLivestock().getState().equals(AnimalState.State.COLLECTREADY)) {
                    collectAcres.add(a);
                }
            }
        }
        return collectAcres;
    }

    /**
     * Gets the number of crop acres on a farm.
     */
    public int numCropsAcres() {
        int counter = 0;
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Gets the number of livestock acres in a farm.
     */
    public int numLivestockAcres() {
        int counter = 0;
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.LIVESTOCK)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Audits the planted crops and generates a report for every acre through
     * SimulationDialog. Also removes and dead crops.
     */
    public void auditCrops(SimulationDialog dlg) {
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                dlg.cropAudit(acres.indexOf(a) + 1, a.getUsageType(), 
                    a.getAssetType(), a.getCrop().getState());
                if (a.getCrop().getState().equals(State.DEAD)) {
                    a.renewAcre();
                }
            }
        }
    }

    /**
     * Audits the livestock being raised and generates a report through
     * SimulationDialog. Also removes any dead or eaten livestock.
     */
    public void auditLivestock(SimulationDialog dlg) {
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.LIVESTOCK)) {
                dlg.livestockAudit(acres.indexOf(a) + 1, a.getUsageType(), 
                    a.getAssetType(), a.getLivestock().getState());
                if (a.getLivestock().getState().equals(AnimalState.State.DEAD)
                    || a.getLivestock().getState().equals(AnimalState.State.EATEN)) {
                    a.renewAcre();
                }
            }
        }
    }

    public int getLevel() {
        return 1;
    }

    /**
     * Calculates the cost for removing weeds from all planted acres.
     */
    public double weedCost() {
        double cost = 0;
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                if (a.getCrop().getState().equals(State.WEEDINFESTED)) {
                    cost += 300;
                }
            }
        }
        return cost * getTaxRate();
    }

    /**
     * When called, removes all crops which have the WEEDINFESTED status,
     * restoring them to HEALTHY and preventing them from dying.
     */
    public void removeWeeds() {
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                if (a.getCrop().getState().equals(State.WEEDINFESTED)) {
                    a.getCrop().setState(State.HEALTHY);
                }
            }
        }
    }

    public void purge() {
        trimFarm(size());
        Bank.removeAccount(Bank.findAccount(id));
    }

    public int getPredatorRisk() {
        return 50;
    }

    public int getWeedRisk() {
        return 50;
    }

    public boolean addDogs(int amount) {
        return false;
    }

    public boolean addGroundCover(int amount) {
        return false;
    }

    public int getDogCoverage() {
        return dogCoverage;
    }

    public int getGroundCoverage() {
        return groundCoverage;
    }

}

