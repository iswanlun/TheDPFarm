package TheDPFarm.plants;

public interface Crops extends Plant {

    public double getCostPerAcre(); //cost to grow
    public double getHarvestNetAmountPerAcre(); //amount earned
    public int getDaysToHarvest();
    
}
