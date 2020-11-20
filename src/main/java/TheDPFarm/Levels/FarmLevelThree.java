package thedpfarm.levels;

public class FarmLevelThree extends FarmLevelTwo {
    
    private double taxRateThree = 1.1875;

    public FarmLevelThree(Farm farm) {
        super(farm.getFarmId(), ((FarmLevelTwo) farm).getAllAcres());
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
