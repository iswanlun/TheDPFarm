package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Wheat extends Crops {

    public Wheat(PlantState state, int farmId) {
        type = AssetType.WHEAT;
        harvestAge = 12;
        costPerAcre = 135.0;
        netPerAcre = 380.0;

        age = 0;
        currentState = state;
        this.farmId = farmId;
        startRandom();
    }
}
