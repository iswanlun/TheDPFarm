package TheDPFarm.levels;

public class FarmLevelThree extends FarmLevelTwo {
    
    private FarmLevelTwo farm;
    private double taxRateThree = 12.75;

    public FarmLevelThree(FarmLevelTwo farm) {
        this.farm = farm;
    }

    public double getTaxRate() {
        return taxRateThree;
    }
}
