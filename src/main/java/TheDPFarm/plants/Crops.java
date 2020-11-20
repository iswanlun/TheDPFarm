package TheDPFarm.plants;

import TheDPFarm.util.HarvestListener;

public interface Crops extends Plant {

    public double getCostPerAcre(); //cost to grow
    public double getHarvestNetAmountPerAcre(); //amount earned
    public int getDaysToHarvest();
    
    public void addHarvestListener(HarvestListener listener);
}
