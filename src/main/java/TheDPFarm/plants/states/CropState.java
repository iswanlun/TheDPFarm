package TheDPFarm.plants.states;

import TheDPFarm.plants.PlantState;
/**
 * States allow for the plant factory to slip weeds into crop planting every so often.
 * Crops will return their normal type for most occasions, but when audited,
 * weeds can be discovered by looking at the state of crops rather than their type.
 */

public class CropState extends PlantState {
    
    private final String type = "CROP";

    public String getStateType() {
        return type;
    }
}
