package TheDPFarm.plants;

import TheDPFarm.util.Acre.AssetType;

public class Soybeans extends Crops {

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
