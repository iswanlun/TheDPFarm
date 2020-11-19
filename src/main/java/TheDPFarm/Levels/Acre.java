package TheDPFarm.levels;

import java.util.ArrayList;

import TheDPFarm.plants.Crops;
import TheDPFarm.animals.Livestock;

/**
 * This holds farm assets for each acre and protects the integrety of the game
 * by enforcing density constraints for each asset
 */
public class Acre {

    public enum UsageType {
        EMPTY, CROPS, LIVESTOCK
    }
    public enum AssetType {
        EMPTY, CORN, MUSHROOMS, SOYBEANS, WHEAT, CHICKEN, COW, HOG, SHEEP
    }

    private Livestock livestock;
    private Crops crop;

    private UsageType utype = UsageType.EMPTY;
    private AssetType atype = AssetType.EMPTY;

    public Acre(UsageType type) {
        this.utype = type;
    }

    public UsageType getUsageType() {
        return utype;
    }

    public AssetType getAssetType() {
        return atype;
    }

    public boolean plantCrop(Crops crop) {
        if(utype.equals(UsageType.CROPS)) {
            this.crop = crop;
            return true;
        }
        return false;
    }

    public boolean raiseLivestock(Livestock livestock) {
        if(utype.equals(UsageType.LIVESTOCK)) {
            this.livestock = livestock;
            return true;
        }
        return false;
    }

    public void renewAcre() {
        livestock = null;
        crop = null;
        utype = UsageType.EMPTY;
    }

    public void reuseAcre(UsageType type) {
        renewAcre();
        this.utype = type;
    }

    public Crops getCrop() {
        return crop;
    }

    public Livestock getLivestock() {
        return livestock;
    }
}
