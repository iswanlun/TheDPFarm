package TheDPFarm.util;

public class Livestockbuilder extends AcreBuilder {

    private HarvestListener hListener;
    private CollectionListener cListener;

    public Livestockbuilder(HarvestListener h, CollectionListener c) {
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
