package TheDPFarm.levels;

import java.util.ArrayList;
import java.util.Vector;

import TheDPFarm.util.Acre.UsageType;
import TheDPFarm.util.Acre.AssetType;
import TheDPFarm.animals.AnimalState;
import TheDPFarm.plants.PlantState.State;
import TheDPFarm.util.Acre;

public class FarmLevelOne implements Farm {

    private int id;
    private ArrayList<Acre> acres;
    private double taxRateOne = 12.3;

    public FarmLevelOne(int size) {
        acres = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            acres.add(new Acre(UsageType.EMPTY));
        }
    }

    protected FarmLevelOne(int id, ArrayList<Acre> acres) {
        this.id = id;
        this.acres = acres;
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

    public void trimFarm(int removal) {
        for (int i = 0; i < removal; i++) {
            acres.remove(acres.size() - 1);
        }
    }

    public Acre getSpecificAcre(int index) {
        return acres.get(index);
    }

    public double getTaxRate() {
        return taxRateOne;
    }

    public Vector<Acre> getEmptyAcres() {
        Vector<Acre> empties = new Vector<>();
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.EMPTY)) {
                empties.add(a);
            } 
        }
        return empties;
    }
    
    protected ArrayList<Acre> getAllAcres() {
        return acres;
    }

    public Vector<Acre> getHarvestAcres(AssetType type) {
        Vector<Acre> harvestAcres = new Vector<>();
        for (Acre a : acres) {
            if(a.getAssetType().equals(type)) {
                if (a.getUsageType().equals(UsageType.CROPS) &&
                    a.getCrop().getState().equals(State.HARVESTREADY)) {
                        harvestAcres.add(a);             
                } else if (a.getUsageType().equals(UsageType.LIVESTOCK) &&
                    a.getLivestock().getState().equals(AnimalState.State.HARVESTREADY)) {
                        harvestAcres.add(a);
                }
            }
        }
        return harvestAcres;
    }

    public Vector<Acre> getCollectAcres(AssetType type) {
        Vector<Acre> collectAcres = new Vector<>();
        for (Acre a : acres) {
            if(a.getAssetType().equals(type)) {
                if (a.getUsageType().equals(UsageType.LIVESTOCK) &&
                    a.getLivestock().getState().equals(AnimalState.State.COLLECTREADY)) {
                        collectAcres.add(a);
                }
            }
        }
        return collectAcres;
    }
}
