package TheDPFarm.levels;

public class FarmLevelTwo extends FarmLevelOne {

    private FarmLevelOne farm;

    public FarmLevelTwo(FarmLevelOne farm) {
        this.farm = farm;
    }
    
    protected FarmLevelTwo() {} //Needed for implicit constructor of level three


    
}
