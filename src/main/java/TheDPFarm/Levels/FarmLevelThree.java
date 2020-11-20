package TheDPFarm.levels;

public class FarmLevelThree extends FarmLevelTwo {
    
    private double taxRateThree = 1.1875;

    public FarmLevelThree(FarmLevelTwo farm) {
        super(farm.getFarmId(), farm.getAllAcres());
    }

    @Override
    public double getTaxRate() {
        return taxRateThree;
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
