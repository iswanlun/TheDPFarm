package thedpfarm.util;

import thedpfarm.animals.Livestock;
import thedpfarm.animals.AnimalState.State;
import thedpfarm.plants.Crops;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

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

    private Livestock livestock = null;
    private Crops crop = null;

    private UsageType uType = UsageType.EMPTY;
    private AssetType aType = AssetType.EMPTY;

    public Acre(UsageType type) {
        this.uType = type;
    }

    public UsageType getUsageType() {
        return uType;
    }

    public AssetType getAssetType() {
        return aType;
    }

    public void plantCrop(Crops crop) {
        this.crop = crop;
        aType = crop.getType();
        this.uType = UsageType.CROPS;
    }

    public void raiseLivestock(Livestock livestock) {
        this.livestock = livestock;
        aType = livestock.getType();
        this.uType = UsageType.LIVESTOCK;
    }

    public void renewAcre() {
        if (livestock != null) {
            livestock.purge();
            livestock = null;
        } else if (crop != null) {
            crop.purge();
            crop = null;
        }
        uType = UsageType.EMPTY;
        aType = AssetType.EMPTY;
    }

    public Crops getCrop() {
        return crop;
    }

    public Livestock getLivestock() {
        return livestock;
    }

    public void harvest(SimulationDialog dlg) {
        double profit = 0;
        if(uType.equals(UsageType.CROPS)) {
            profit += crop.getHarvestNetAmountPerAcre();
        } else if (uType.equals(UsageType.LIVESTOCK)) {
            profit += livestock.getHarvestPricePerBatch() * livestock.getBatchesPerAcre();
        }
        double bal = Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(profit);
        renewAcre();
        dlg.harvestSold(bal);
    }

    public void collect(SimulationDialog dlg) {
        System.out.println("[DEBUG] Collect.");
        double profit = 0;
        if (uType.equals(UsageType.LIVESTOCK)) {
            profit += (livestock.getCollectPricePerBatch() * livestock.getBatchesPerAcre());
            livestock.setState(State.HEALTHY);
        }
        double bal = Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(profit);
        dlg.collectSold(bal);
    }
}
