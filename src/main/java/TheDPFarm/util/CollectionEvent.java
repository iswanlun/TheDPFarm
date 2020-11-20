package TheDPFarm.util;

import TheDPFarm.util.Acre.AssetType;

public class CollectionEvent {

    private int farmId; //the farm on which the thing is to be collected
    private AssetType assetType; //The type / name of the thing to be collected

    public CollectionEvent(AssetType assetType, int farmId) {
        this.farmId = farmId;
        this.assetType = assetType;
    }
    
    public int getFarmId() {
        return farmId;
    }

    public AssetType getAssetType() {
        return assetType;
    }
}
