package thedpfarm.animals;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.util.Acre.AssetType;

public class Hog extends Livestock {

    public Hog(State state, int farmId) {
        batchSize = 10;
        batchPrice = 120;
        batchDensity = 30;
        harvestAge = 14;
        
        type = AssetType.HOG;
        age = 0;
        collectableToggle = 0;
        setState(state);
        startRand();
        setCollectPricePerBatch(48);
        setHarvestPricePerBatch(950);
        this.farmId = farmId;
    }
}
