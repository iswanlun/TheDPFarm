package thedpfarm.util;

import thedpfarm.util.Acre.AssetType;

/**
 * This is used for planting crops, configures a acre of crops so that all that
 * is needed is a simple call to this classes concrete factory children.
 */
public abstract class AcreBuilder {

    protected abstract void createAssets(AssetType assetType);

    protected abstract void chargeBank(AssetType assetType) throws FinanceException;

    protected abstract void registerWithDayNight();

    protected abstract void registerWithHarvest();

    protected abstract void registerWithCollect();
    
    protected abstract void insertAssets(Acre acre);

    /**
     * Methodology to create a new acre.
     */
    public void buildAcre(Acre acre, AssetType assetType) throws FinanceException {
        createAssets(assetType);
        chargeBank(assetType);
        registerWithDayNight();
        registerWithHarvest();
        registerWithCollect();
        insertAssets(acre);
    }    
}
