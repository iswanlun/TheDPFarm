package thedpfarm.util;

import thedpfarm.plants.Corn;
import thedpfarm.plants.Crops;
import thedpfarm.plants.Mushrooms;
import thedpfarm.plants.PlantState;
import thedpfarm.plants.PlantState.State;
import thedpfarm.plants.Soybeans;
import thedpfarm.plants.Wheat;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.world.Bank;
import thedpfarm.world.World;


public class CropBuilder extends AcreBuilder {

    private SimulationDialog dlg;
    private Crops crops;

    public CropBuilder(SimulationDialog dlg) {
        this.dlg = dlg;
    }

    @Override
    protected void createAssets(AssetType assetType) {
        crops = getCropsObject(assetType);
    }

    @Override
    protected void chargeBank(AssetType assetType) throws FinanceException {
        double price = crops.getCostPerAcre() * World.getFarm().getTaxRate();
        if (price < Bank.accountBalance(World.getFarm().getFarmId())) {
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
        HarvestListener h = new HarvestListener(dlg);
        crops.addHarvestListener(h);
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
            case CORN: return new Corn(new PlantState(State.SEED), 
                World.getFarm().getFarmId());
            case SOYBEANS: return new Soybeans(new PlantState(State.SEED), 
                World.getFarm().getFarmId());
            case WHEAT: return new Wheat(new PlantState(State.SEED), 
                World.getFarm().getFarmId());
            case MUSHROOMS: return new Mushrooms(new PlantState(State.SEED), 
                World.getFarm().getFarmId()); 
            default:
                return null;
        }
    }
}
