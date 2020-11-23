package thedpfarm.animals;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.util.Acre.AssetType;

public class Cow extends Livestock {
    
    /**
     * Implementation of a cow which extends the abstract class Animal.
     * @param state The inital state of the animal.
     * @param farmId The farm this animal belongs to.
     */
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
