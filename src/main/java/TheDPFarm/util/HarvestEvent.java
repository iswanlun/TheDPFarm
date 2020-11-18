package TheDPFarm.util;

public class HarvestEvent {

    private int farmId; //the farm on which the thing is to be harvested
    private String assetType; //The type / name of the thing to be harvested
    private int acreId; //The location of the thing to be harvested

    public HarvestEvent(int farmId, String assetType, int acreId) {

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
