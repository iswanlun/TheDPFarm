package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Wheat extends Crops {

    /**
     * Implementation of Wheat, extended from the abstract class Plant.
     * @param state The state the plant begins in.
     * @param farmId The farm this plant is grown in.
     */
    public Wheat(PlantState state, int farmId) {
        type = AssetType.WHEAT;
        harvestAge = 12;
        costPerAcre = 1135.0;
        netPerAcre = 3800.0;

        age = 0;
        currentState = state;
        this.farmId = farmId;
        startRandom();
    }
}
