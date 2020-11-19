package TheDPFarm.util;

public class CropBuilder extends AcreBuilder {

    private HarvestListener hListener;
    private CollectionListener cListener;

    public CropBuilder(HarvestListener h, CollectionListener c) {
        this.hListener = h;
        this.cListener = c;
    }
    
    @Override
    protected void chargeBank() {

    }
    @Override
    protected void createAssets() {

    }
    @Override
    protected void insertAssets() {

    }
    @Override
    protected void registerWithDayNight() {

    }
    @Override
    protected void registerWithHarvest() {

    }

    @Override
    protected void chargeBankAcre(int acres, double price) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void createAcres(int acres) {
        // TODO Auto-generated method stub

    }
}
