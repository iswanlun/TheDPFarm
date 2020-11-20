package TheDPFarm.animals;

import TheDPFarm.animals.AnimalState.State;
import TheDPFarm.util.Acre.AssetType;

public class Sheep extends Livestock {

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
        this.farmId = farmId;
    }
}
