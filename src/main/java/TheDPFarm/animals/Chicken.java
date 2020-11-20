package TheDPFarm.animals;

import TheDPFarm.animals.AnimalState.state;
import TheDPFarm.util.Acre.AssetType;

public class Chicken extends Livestock {

    public Chicken(state state, int farmId) {
        batchSize = 50;
        batchPrice = 100;
        batchDensity = 60;
        harvestAge = 14;
        
        type = AssetType.CHICKEN;
        age = 0;
        collectableToggle = 0;
        setState(state);
        startRand();
        setCollectPricePerBatch(25);
        this.farmId = farmId;
    }
}
