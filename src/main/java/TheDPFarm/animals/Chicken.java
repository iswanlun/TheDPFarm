package thedpfarm.animals;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.util.Acre.AssetType;

public class Chicken extends Livestock {

    /**
     * Implementation of a chicken which extends the abstract class Animal.
     * @param state Initial state for the bird.
     * @param farmId The farm id this animal belongs to.
     */
    public Chicken(State state, int farmId) {
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
        setHarvestPricePerBatch(200);
        this.farmId = farmId;
    }
}
