package TheDPFarm.util;

import TheDPFarm.util.Acre.AssetType;
import TheDPFarm.world.Bank;
import TheDPFarm.world.World;
import TheDPFarm.animals.*;

public class Livestockbuilder extends AcreBuilder {

    private HarvestListener hListener;
    private CollectionListener cListener;
    private Livestock livestock;

    public Livestockbuilder(HarvestListener h, CollectionListener c) {
        this.hListener = h;
        this.cListener = c;
    }

    @Override
    protected void createAssets(AssetType aType) {
        livestock = getLivestockObject(aType);
    }

    @Override
    protected void chargeBank(AssetType aType) throws FinanceException {
        double price = livestock.getBatchPrice() * livestock.getBatchesPerAcre() * World.getFarm().getTaxRate();
        if(price < Bank.accountBalance(World.getFarm().getFarmId())) {
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
        livestock.addHarvestListener(hListener);
    }

    @Override
    protected void registerWithCollect() {
        livestock.addCollectionListener(cListener);
    }

    @Override
    protected void insertAssets(Acre acre) {
        acre.raiseLivestock(livestock);
    }  

    private Livestock getLivestockObject(AssetType type) {
        switch (type) {
            case CHICKEN: return new Chicken(AnimalState.State.HEALTHY, World.getFarm().getFarmId());
            case COW: return new Cow(AnimalState.State.HEALTHY, World.getFarm().getFarmId());
            case SHEEP: return new Sheep(AnimalState.State.HEALTHY, World.getFarm().getFarmId());
            case HOG: return new Hog(AnimalState.State.HEALTHY, World.getFarm().getFarmId());
            default:
                return null;
        }
    }
    
}
