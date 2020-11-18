package TheDPFarm.plants.states;

import TheDPFarm.plants.PlantState;

public class CropState extends PlantState {
    
    private final String type = "CROP";

    public String getStateType() {
        return type;
    }
}
