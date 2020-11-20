package thedpfarm.levels;

import java.util.ArrayList;

import thedpfarm.util.Acre;

public class FarmLevelTwo extends FarmLevelOne {

    private double taxRateTwo = 1.164;

    public FarmLevelTwo(Farm farm) {
        super(farm.getFarmId(), ((FarmLevelOne) farm).getAllAcres());
    }
    
    protected FarmLevelTwo(int id, ArrayList<Acre> acres) {
        super(id, acres);
    } 

    @Override
    public double getTaxRate() {
        return taxRateTwo;
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
