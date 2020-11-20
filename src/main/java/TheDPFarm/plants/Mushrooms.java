package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Mushrooms extends Crops {

    public Mushrooms(PlantState state, int farmId) {
        type = AssetType.MUSHROOMS;
        harvestAge = 17;
        costPerAcre = 270.0;
        netPerAcre = 710.0;

        age = 0;
        currentState = state;
        this.farmId = farmId;
        startRandom();
    }

}
