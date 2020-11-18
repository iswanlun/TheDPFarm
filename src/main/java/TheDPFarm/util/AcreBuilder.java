package TheDPFarm.util;

/**
 * This is used for planting crops, configures a acre of 
 * crops so that all that is needed is a simple call to this
 * classes concrete factory children.
 */
public abstract class AcreBuilder {

    public void getAcre() {
        chargeBank();
        createAssets();
        insertAssets();
        registerWithDayNight();
        registerWithHarvest();
    }

    public abstract void chargeBank();
    public abstract void createAssets();
    public abstract void insertAssets();
    public abstract void registerWithDayNight();
    public abstract void registerWithHarvest();

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
