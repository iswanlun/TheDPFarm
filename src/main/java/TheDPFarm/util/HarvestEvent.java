package thedpfarm.util;

import thedpfarm.util.Acre.AssetType;

public class HarvestEvent {

    private int farmId; //the farm on which the thing is to be harvested
    private AssetType assetType; //The type / name of the thing to be harvested

    public HarvestEvent(AssetType type, int farmId) {
        this.farmId = farmId;
        this.assetType = type;
    }
    
    public int getFarmId() {
        return farmId;
    }

    public AssetType getAssetType() {
        return assetType;
    }
}
