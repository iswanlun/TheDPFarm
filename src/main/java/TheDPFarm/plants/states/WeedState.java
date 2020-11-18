package TheDPFarm.plants.states;

import TheDPFarm.plants.PlantState;
import TheDPFarm.plants.Weeds;

public class WeedState extends PlantState {

    private final String type = "WEED";
    private Weeds thisWeed;

    public WeedState(Weeds thisWeed) {
        this.thisWeed = thisWeed;
    }

    public String getStateType() {
        return type;
    }

    public Weeds getWeedType() {
        return thisWeed;
    }
}
