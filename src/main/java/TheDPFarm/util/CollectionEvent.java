package TheDPFarm.util;

public class CollectionEvent {

    private int farmId; //the farm on which the thing is to be collected
    private String assetType; //The type / name of the thing to be collected
    private int acreId; //The location of the thing to be collected

    public CollectionEvent(int farmId, String assetType, int acreId) {
        this.farmId = farmId;
        this.assetType = assetType;
        this.acreId = acreId;
    }
    
    public int getFarmId() {
        return farmId;
    }

    public String getAssetType() {
        return assetType;
    }

    public int getAcreId() {
        return acreId;
    }
}
