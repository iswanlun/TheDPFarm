package TheDPFarm.util;

import TheDPFarm.util.Acre.AssetType;
import TheDPFarm.plants.*;
import TheDPFarm.plants.PlantState.state;
import TheDPFarm.world.*;

public class CropBuilder extends AcreBuilder {

    private HarvestListener hListener;
    private CollectionListener cListener;
    private Crops crops;

    public CropBuilder(HarvestListener h, CollectionListener c) {
        this.hListener = h;
        this.cListener = c;
    }

    @Override
    protected void createAssets(AssetType aType) {
        crops = getCropsObject(aType);
    }

    @Override
    protected void chargeBank(AssetType aType) throws FinanceException {
        double price = crops.getCostPerAcre() * World.getFarm().getTaxRate();
        if(price < Bank.accountBalance(World.getFarm().getFarmId())) {
            Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(price);
        } else {
            throw new FinanceException("Ran out of funds planting new crops.");
        }
    }

    @Override
    protected void registerWithDayNight() {
        World.TimeManager.addObserver(crops);
    }

    @Override
    protected void registerWithHarvest() {
        crops.addHarvestListener(hListener);
    }

    @Override
    protected void registerWithCollect() {
        return;
    }

    @Override
    protected void insertAssets(Acre acre) {
        acre.plantCrop(crops);
    }  

    private Crops getCropsObject(AssetType type) {
        switch (type) {
            case CORN: return new Corn(new PlantState(state.SEED), World.getFarm().getFarmId());
            case SOYBEANS: return new Soybeans(new PlantState(state.SEED), World.getFarm().getFarmId());
            case WHEAT: return new Wheat(new PlantState(state.SEED), World.getFarm().getFarmId());
            case MUSHROOMS: return new Mushrooms(new PlantState(state.SEED), World.getFarm().getFarmId()); 
            default:
                return null;
        }
    }
}
