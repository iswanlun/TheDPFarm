package thedpfarm.levels;

import java.util.ArrayList;
import java.util.Vector;

import thedpfarm.animals.AnimalState;
import thedpfarm.plants.PlantState.State;
import thedpfarm.util.Acre;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.Acre.UsageType;
import thedpfarm.util.SimulationDialog;

public class FarmLevelOne implements Farm {

    private int id;
    private ArrayList<Acre> acres;
    private double taxRateOne = 1.123;

    public FarmLevelOne(int size) {
        acres = new ArrayList<>();
        for (int i = 0; i < size; i++) {
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

    public Vector<Acre> getCollectAcres(AssetType type) {
        System.out.println("[DEBUG] Collect acres.");
        Vector<Acre> collectAcres = new Vector<>();
        for (Acre a : acres) {
            if (a.getAssetType().equals(type)) {
                if (a.getUsageType().equals(UsageType.LIVESTOCK)
                    && a.getLivestock().getState().equals(AnimalState.State.COLLECTREADY)) {
                    collectAcres.add(a);
                }
            }
        }
        System.out.println("[DEBUG] vector size : " + collectAcres.size());
        return collectAcres;
    }

    public int numCropsAcres() {
        int counter = 0;
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                counter++;
            }
        }
        return counter;
    }

    public int numLivestockAcres() {
        int counter = 0;
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.LIVESTOCK)) {
                counter++;
            }
        }
        return counter;
    }

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

    public double weedCost() {
        double cost = 0;
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                if (a.getCrop().getState().equals(State.WEEDINFESTED)) {
                    cost += 100;
                }
            }
        }
        return cost * getTaxRate();
    }

    public void removeWeeds() {
        for (Acre a : acres) {
            if (a.getUsageType().equals(UsageType.CROPS)) {
                if (a.getCrop().getState().equals(State.WEEDINFESTED)) {
                    a.getCrop().setState(State.HEALTHY);
                }
            }
        }
    }
}
