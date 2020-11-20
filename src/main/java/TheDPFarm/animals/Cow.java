package TheDPFarm.animals;

import TheDPFarm.animals.AnimalState.State;
import TheDPFarm.util.Acre.AssetType;

public class Cow extends Livestock {
    
    public Cow(State state, int farmId) {
        batchSize = 5;
        batchPrice = 250;
        batchDensity = 20;
        harvestAge = 14;
        
        type = AssetType.COW;
        age = 0;
        collectableToggle = 0;
        setState(state);
        startRand();
        setCollectPricePerBatch(150);
        setHarvestPricePerBatch(1000);
        this.farmId = farmId;
    }
}
