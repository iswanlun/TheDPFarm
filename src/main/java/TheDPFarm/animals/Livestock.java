package TheDPFarm.animals;

import java.util.Random;

import TheDPFarm.animals.AnimalState.State;
import TheDPFarm.util.CollectionEvent;
import TheDPFarm.util.CollectionListener;
import TheDPFarm.util.HarvestEvent;
import TheDPFarm.util.HarvestListener;
import TheDPFarm.util.Acre.AssetType;

public abstract class Livestock implements Animal {

    protected State currentState;

    protected int batchSize;
    protected int batchPrice;
    protected int batchDensity;
    protected int harvestAge;
    protected double collectPrice;
    protected double harvestPrice;
    protected int collectableToggle;

    protected AssetType type;
    protected int farmId;
    protected int age;

    protected HarvestListener hListener;
    protected CollectionListener cListener;
    protected Random randGenerator;

    public int getBatchSize() {
        return batchSize;
    }

    public int getBatchPrice() {
        return batchPrice;
    }

    public int getBatchesPerAcre() {
        return batchDensity;
    }

    public double getCollectPricePerBatch() {
        return collectPrice;
    }

    public void setCollectPricePerBatch(double collectPrice) {
        this.collectPrice = collectPrice;
    }

    public double getHarvestPricePerBatch() {
        return harvestPrice;
    }

    public void setHarvestPricePerBatch(double harvestPrice) {
        this.harvestPrice = harvestPrice;
    }

    public void addHarvestListener(HarvestListener listener) {
        this.hListener = listener;
    }

    public void addCollectionListener(CollectionListener listener) {
        this.cListener = listener;
    }

    public State getState() {
        return currentState;
    }

    public void setState(State newState) {
        currentState = newState;
    }

    public AssetType getType() {
        return type;
    }

    public void notifyDay() {
        this.age++;
        collectableToggle = (collectableToggle+1)%3;
        if(collectableToggle == 0 && !getState().equals(State.DEAD)) {
            CollectionEvent e = new CollectionEvent(type, farmId);
            cListener.collectionEvent(e);
        }
        if (age == harvestAge) {
            setState(State.HARVESTREADY);
            HarvestEvent e = new HarvestEvent(type, farmId);
            hListener.harvestEvent(e);
        } else if (age - harvestAge > 3) {
            setState(State.DEAD);
        }
    }

    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(50); // %5 chance of early death
        if (sickOdds == 2) {
            setState(State.SICK);
        } else if (getState().equals(State.SICK)) {
            setState(State.DEAD);
        }

        int predatorOdds = randGenerator.nextInt(50); // %5 chance of early death
        if (predatorOdds == 2) {
            setState(State.EATEN);
        }
    }

    protected void startRand() {
        randGenerator = new Random();
    }
}
