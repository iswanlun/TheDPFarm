package thedpfarm.animals;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.util.Acre.AssetType;

public class Sheep extends Livestock {

    /**
     * Implementation of a sheep which extends the abstract class Animal.
     * @param state The inital state of the animal.
     * @param farmId The farm this animal belongs to.
     */
    public Sheep(State state, int farmId) {
        batchSize = 15;
        batchPrice = 150;
        batchDensity = 20;
        harvestAge = 14;
        
        type = AssetType.SHEEP;
        age = 0;
        collectableToggle = 0;
        setState(state);
        startRand();
        setCollectPricePerBatch(130);
        setHarvestPricePerBatch(750);
        this.farmId = farmId;
    }
}
