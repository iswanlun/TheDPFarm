package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Mushrooms extends Crops {

    /**
     * Implementation of Mushroom, extended from the abstract class Plant.
     * @param state The state the plant begins in.
     * @param farmId The farm this plant is grown in.
     */
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
