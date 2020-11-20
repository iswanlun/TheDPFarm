package thedpfarm.util;

import thedpfarm.util.Acre.AssetType;

/**
 * This is used for planting crops, configures a acre of crops so that all that
 * is needed is a simple call to this classes concrete factory children.
 */
public abstract class AcreBuilder {

    protected abstract void createAssets(AssetType aType);
    protected abstract void chargeBank(AssetType aType) throws FinanceException;
    protected abstract void registerWithDayNight();
    protected abstract void registerWithHarvest();
    protected abstract void registerWithCollect();
    protected abstract void insertAssets(Acre acre);

    public void buildAcre(Acre acre, AssetType aType) throws FinanceException {
        createAssets(aType);
        chargeBank(aType);
        registerWithDayNight();
        registerWithHarvest();
        registerWithCollect();
        insertAssets(acre);
    }    
}
