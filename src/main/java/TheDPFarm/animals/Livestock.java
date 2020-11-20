package thedpfarm.animals;

import java.util.Random;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.CollectionEvent;
import thedpfarm.util.CollectionListener;
import thedpfarm.util.HarvestEvent;
import thedpfarm.util.HarvestListener;
import thedpfarm.world.World;

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

    protected HarvestListener harvestListener;
    protected CollectionListener collectListener;
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
        this.harvestListener = listener;
    }

    public void addCollectionListener(CollectionListener listener) {
        this.collectListener = listener;
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
        collectableToggle = (collectableToggle + 1) % 3;
        if (collectableToggle == 0 && !getState().equals(State.DEAD) 
            && !getState().equals(State.EATEN)) {
            setState(State.COLLECTREADY);
            CollectionEvent e = new CollectionEvent(type, farmId);
            collectListener.collectionEvent(e);
        }
        if (age == harvestAge && !getState().equals(State.DEAD)) {
            setState(State.HARVESTREADY);
            HarvestEvent e = new HarvestEvent(type, farmId);
            harvestListener.harvestEvent(e);
        } else if (age - harvestAge > 3) {
            setState(State.DEAD);
        }
    }

    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(50);
        if (sickOdds == 2) {
            setState(State.SICK);
        } else if (getState().equals(State.SICK)) {
            setState(State.DEAD);
        }

        int predatorOdds = randGenerator.nextInt(50);
        if (predatorOdds == 2) {
            setState(State.EATEN);
        }
    }

    protected void startRand() {
        randGenerator = new Random();
    }

    public void purge() {

        World.TimeManager.acquire();
        World.TimeManager.removeObserver(this);
        World.TimeManager.release();

        this.harvestListener = null;
        this.collectListener = null;
    }
}
