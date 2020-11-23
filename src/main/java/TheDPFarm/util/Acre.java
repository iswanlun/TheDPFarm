package thedpfarm.util;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.animals.Livestock;
import thedpfarm.plants.Crops;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

/**
 * This holds farm assets for each acre and protects the integrety of the game
 * by enforcing density constraints for each asset.
 */
public class Acre {

    public enum UsageType {
        EMPTY, CROPS, LIVESTOCK
    }

    public enum AssetType {
        EMPTY, CORN, MUSHROOMS, SOYBEANS, WHEAT, CHICKEN, COW, HOG, SHEEP
    }

    private Livestock livestock = null;
    private Crops crop = null;

    private UsageType usageType = UsageType.EMPTY;
    private AssetType assetType = AssetType.EMPTY;

    public Acre(UsageType usagetype, AssetType assettype) {
        this.usageType = usagetype;
        this.assetType = assettype;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void plantCrop(Crops crop) {
        this.crop = crop;
        assetType = crop.getType();
        this.usageType = UsageType.CROPS;
    }

    public void raiseLivestock(Livestock livestock) {
        this.livestock = livestock;
        assetType = livestock.getType();
        this.usageType = UsageType.LIVESTOCK;
    }

    public void renewAcre() {
        if (livestock != null) {
            livestock.purge();
            livestock = null;
        } else if (crop != null) {
            crop.purge();
            crop = null;
        }
        usageType = UsageType.EMPTY;
        assetType = AssetType.EMPTY;
    }

    public Crops getCrop() {
        return crop;
    }

    public Livestock getLivestock() {
        return livestock;
    }

    public void harvest(SimulationDialog dlg) {
        double profit = 0;
        if (usageType.equals(UsageType.CROPS)) {
            profit += crop.getHarvestNetAmountPerAcre();
        } else if (usageType.equals(UsageType.LIVESTOCK)) {
            profit += livestock.getHarvestPricePerBatch() * livestock.getBatchesPerAcre();
        }
        double bal = Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(profit);
        renewAcre();
        dlg.harvestSold(bal);
    }

    public void collect(SimulationDialog dlg) {
        double profit = 0;
        if (usageType.equals(UsageType.LIVESTOCK)) {
            profit += (livestock.getCollectPricePerBatch() * livestock.getBatchesPerAcre());
            livestock.setState(State.HEALTHY);
        }
        double bal = Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(profit);
        dlg.collectSold(bal);
    }
}
