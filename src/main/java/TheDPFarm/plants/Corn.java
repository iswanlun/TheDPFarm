package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Corn extends Crops {

    /**
     * Implementation of Corn, extended from the abstract class Plant.
     * @param state The state the plant begins in.
     * @param farmId The farm this plant is grown in.
     */
    public Corn(PlantState state, int farmId) {
        type = AssetType.CORN;
        harvestAge = 10;
        costPerAcre = 1125.0;
        netPerAcre = 3500.0;

        age = 0;
        currentState = state;
        this.farmId = farmId;
        startRandom();
    }
}
