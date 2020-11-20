package TheDPFarm.levels;

public class FarmLevelThree extends FarmLevelTwo {
    
    private double taxRateThree = 18.75;

    public FarmLevelThree(FarmLevelTwo farm) {
        super(farm.getFarmId(), farm.getAllAcres());
    }

    public double getTaxRate() {
        return taxRateThree;
    }
}
