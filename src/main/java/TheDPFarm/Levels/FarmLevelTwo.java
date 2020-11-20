package TheDPFarm.levels;

import java.util.ArrayList;
import TheDPFarm.util.Acre;

public class FarmLevelTwo extends FarmLevelOne {

    private double taxRateTwo = 16.4;

    public FarmLevelTwo(FarmLevelOne farm) {
        super(farm.getFarmId(), farm.getAllAcres());
    }
    
    protected FarmLevelTwo(int id, ArrayList<Acre> acres) {
        super(id, acres);
    } 

    public double getTaxRate() {
        return taxRateTwo;
    }
    
}
