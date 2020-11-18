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

    private Livestock livestock;
    private Crops crop;

    private UsageType type = UsageType.EMPTY;

    public Acre(UsageType type) {
        this.type = type;
    }

    public boolean plantCrop(Crops crop) {
        if(type.equals(UsageType.CROPS)) {
            this.crop = crop;
            return true;
        }
        return false;
    }

    public boolean raiseLivestock(Livestock livestock) {
        if(type.equals(UsageType.LIVESTOCK)) {
            this.livestock = livestock;
            return true;
        }
        return false;
    }

    public void renewAcre() {
        livestock = null;
        crop = null;
        type = UsageType.EMPTY;
    }

    public void reuseAcre(UsageType type) {
        renewAcre();
        this.type = type;
    }

    public Crops getCrop() {
        return crop;
    }

    public Livestock getLivestock() {
        return livestock;
    }
}
