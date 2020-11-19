package TheDPFarm.util;

import TheDPFarm.levels.Acre;
import TheDPFarm.levels.Acre.AssetType;

/**
 * This is used for planting crops, configures a acre of crops so that all that
 * is needed is a simple call to this classes concrete factory children.
 */
public abstract class AcreBuilder {

    public void buildAcre(Acre acre, AssetType aType) {
        chargeBank();
        createAssets();
        insertAssets();
        registerWithDayNight();
        registerWithHarvest();
    }

    public void createAcre(int acres, double price) {
        chargeBankAcre(acres, price);
        createAcres(acres);
    }

    protected abstract void chargeBank();
    protected abstract void createAssets();
    protected abstract void insertAssets();
    protected abstract void registerWithDayNight();
    protected abstract void registerWithHarvest();

    protected abstract void chargeBankAcre(int acres, double price);
    protected abstract void createAcres(int acres);

    /**
     * To construct an acre
     *  - take in the farm id, type of crop, amount of crop
     *    - Charge the bank for the amount
     *    - instanciate the crops
     *    - Put the crops on the land
     *    - add the right amount of weeds
     *    - register the crops with the day / night listener
     *    - register crops with the harvest listner
     */
    
}
