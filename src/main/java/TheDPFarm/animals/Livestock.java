package TheDPFarm.animals;

import java.util.Random;

import TheDPFarm.animals.AnimalState.state;
import TheDPFarm.util.CollectionEvent;
import TheDPFarm.util.CollectionListener;
import TheDPFarm.util.HarvestEvent;
import TheDPFarm.util.HarvestListener;
import TheDPFarm.util.Acre.AssetType;

public abstract class Livestock implements Animal {

    protected AnimalState currentState;

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

    public state getState() {
        return currentState.getState();
    }

    public void setState(state newState) {
        currentState.setState(newState);
    }

    public void notifyDay() {
        this.age++;
        collectableToggle = (collectableToggle+1)%3;
        if(collectableToggle == 0 && !getState().equals(state.DEAD)) {
            CollectionEvent e = new CollectionEvent(type, farmId);
            cListener.collectionEvent(e);
        }
        if (age == harvestAge) {
            setState(state.HARVESTREADY);
            HarvestEvent e = new HarvestEvent(type, farmId);
            hListener.harvestEvent(e);
        } else if (age - harvestAge > 3) {
            setState(state.DEAD);
        }
    }

    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(20); // %5 chance of early death
        if (sickOdds == 2) {
            currentState.setState(state.SICK);
        } else if (getState().equals(state.SICK)) {
            setState(state.DEAD);
        }

        int predatorOdds = randGenerator.nextInt(20); // %5 chance of early death
        if (predatorOdds == 2) {
            currentState.setState(state.EATEN);
        }
    }

    protected void startRand() {
        randGenerator = new Random();
    }
}
