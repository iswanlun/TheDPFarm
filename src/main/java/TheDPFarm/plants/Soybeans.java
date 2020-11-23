package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Soybeans extends Crops {

    /**
     * Implementation of Soybeans, extended from the abstract class Plant.
     * @param state The state the plant begins in.
     * @param farmId The farm this plant is grown in.
     */
    public Soybeans(PlantState state, int farmId) {
        type = AssetType.SOYBEANS;
        harvestAge = 15;
        costPerAcre = 180.0;
        netPerAcre = 375.0;

        age = 0;
        currentState = state;
        this.farmId = farmId;
        startRandom();
    }
}
