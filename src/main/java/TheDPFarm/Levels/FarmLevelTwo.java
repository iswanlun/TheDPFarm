package TheDPFarm.levels;

import java.util.ArrayList;
import TheDPFarm.util.Acre;

public class FarmLevelTwo extends FarmLevelOne {

    private double taxRateTwo = 1.164;

    public FarmLevelTwo(FarmLevelOne farm) {
        super(farm.getFarmId(), farm.getAllAcres());
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
