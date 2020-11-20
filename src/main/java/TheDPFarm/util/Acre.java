package TheDPFarm.util;

import TheDPFarm.plants.Crops;
import TheDPFarm.world.Bank;
import TheDPFarm.world.World;
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

    public void plantCrop(Crops crop) {
        this.crop = crop;
        this.utype = UsageType.CROPS;
    }

    public void raiseLivestock(Livestock livestock) {
        this.livestock = livestock;
        this.utype = UsageType.LIVESTOCK;
    }

    public void renewAcre() {
        livestock = null;
        crop = null;
        utype = UsageType.EMPTY;
    }

    public Crops getCrop() {
        return crop;
    }

    public Livestock getLivestock() {
        return livestock;
    }

    public void harvest(SimulationDialog dlg) {
        double profit = 0;
        if(utype.equals(UsageType.CROPS)) {
            profit += crop.getHarvestNetAmountPerAcre();
        } else if (utype.equals(UsageType.LIVESTOCK)) {
            profit += livestock.getHarvestPricePerBatch() * livestock.getBatchesPerAcre();
        }
        double bal = Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(profit);
        renewAcre();
        dlg.harvestSold(bal);
    }
}
