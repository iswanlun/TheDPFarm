package TheDPFarm.animals;

public class Chicken implements Livestock {

    private AnimalState currentState;
    private int batchSize = 50;
    private int batchPrice = 100;
    private int batchDensity = 60;

    private double batchHarvestPrice;
    private double batchSlaughterPrice;


    @Override
    public AnimalState getState() {
        return currentState;
    }

    @Override
    public void setState(AnimalState newState) {
        this.currentState = newState;
    }

    @Override
    public int getBatchSize() {
        return batchSize;
    }

    @Override
    public int getBatchPrice() {
        return batchPrice;
    }

    @Override
    public int getBatchesPerAcre() {
        return batchDensity;
    }

    @Override
    public double getHarvestPrivcePerBatch() {
        return batchHarvestPrice;
    }

    @Override
    public void setHarvestPrivcePerBatch(double harvestPrice) {
        this.batchHarvestPrice = harvestPrice;
    }

    @Override
    public double getSlaughterPricePerBatch() {
        return batchSlaughterPrice;
    }

    @Override
    public void setSlaughterPricePerBatch(double slaughterPrice) {
        this.batchSlaughterPrice = slaughterPrice;

    }

    @Override
    public void notifyDay() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyNight() {
        // TODO Auto-generated method stub

    }
}
