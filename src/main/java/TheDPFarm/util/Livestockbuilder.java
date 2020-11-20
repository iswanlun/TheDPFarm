package thedpfarm.util;

import thedpfarm.animals.AnimalState;
import thedpfarm.animals.Chicken;
import thedpfarm.animals.Cow;
import thedpfarm.animals.Hog;
import thedpfarm.animals.Livestock;
import thedpfarm.animals.Sheep;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

public class Livestockbuilder extends AcreBuilder {

    private SimulationDialog dlg;
    private Livestock livestock;

    public Livestockbuilder(SimulationDialog dlg) {
        this.dlg = dlg;
    }

    @Override
    protected void createAssets(AssetType assetType) {
        livestock = getLivestockObject(assetType);
    }

    @Override
    protected void chargeBank(AssetType assetType) throws FinanceException {
        double price = livestock.getBatchPrice() 
            * livestock.getBatchesPerAcre() * World.getFarm().getTaxRate();
        if (price < Bank.accountBalance(World.getFarm().getFarmId())) {
            Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(price);
        } else {
            throw new FinanceException("Ran out of funds purchasing new livestock.");
        }
    }

    @Override
    protected void registerWithDayNight() {
        World.TimeManager.addObserver(livestock);
    }

    @Override
    protected void registerWithHarvest() {
        HarvestListener h = new HarvestListener(dlg);
        livestock.addHarvestListener(h);
    }

    @Override
    protected void registerWithCollect() {
        CollectionListener c = new CollectionListener(dlg);
        livestock.addCollectionListener(c);
    }

    @Override
    protected void insertAssets(Acre acre) {
        acre.raiseLivestock(livestock);
    }  

    private Livestock getLivestockObject(AssetType type) {
        switch (type) {
            case CHICKEN: return new Chicken(AnimalState.State.HEALTHY, 
                World.getFarm().getFarmId());
            case COW: return new Cow(AnimalState.State.HEALTHY, 
                World.getFarm().getFarmId());
            case SHEEP: return new Sheep(AnimalState.State.HEALTHY, 
                World.getFarm().getFarmId());
            case HOG: return new Hog(AnimalState.State.HEALTHY, 
                World.getFarm().getFarmId());
            default:
                return null;
        }
    }
    
}
