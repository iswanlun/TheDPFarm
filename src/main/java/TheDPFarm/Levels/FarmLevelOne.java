package TheDPFarm.levels;

import java.util.ArrayList;

import TheDPFarm.levels.Farm;

public class FarmLevelOne implements Farm {

    private int id;
    private ArrayList<Acre> acres;

    public FarmLevelOne() {
        acres = new ArrayList<>();
    }

    public int getFarmId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int size() {
        return acres.size();
    }

}
