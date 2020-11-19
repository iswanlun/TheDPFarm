package TheDPFarm.levels;

public class FarmLevelTwo extends FarmLevelOne {

    private FarmLevelOne farm;
    private double taxRateTwo = 16.4;

    public FarmLevelTwo(FarmLevelOne farm) {
        this.farm = farm;
    }
    
    protected FarmLevelTwo() {} //Needed for implicit constructor of level three

    public double getTaxRate() {
        return taxRateTwo;
    }
    
}
