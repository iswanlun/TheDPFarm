package thedpfarm.plants;

import thedpfarm.util.Acre.AssetType;

public class Corn extends Crops {

    public Corn(PlantState state, int farmId) {
        type = AssetType.CORN;
        harvestAge = 10;
        costPerAcre = 125.0;
        netPerAcre = 350.0;

        age = 0;
        currentState = state;
        this.farmId = farmId;
        startRandom();
    }
}
