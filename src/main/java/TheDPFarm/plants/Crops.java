package thedpfarm.plants;

import java.util.Random;

import thedpfarm.plants.PlantState.State;
import thedpfarm.util.Acre;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.HarvestEvent;
import thedpfarm.util.HarvestListener;
import thedpfarm.world.World;

public abstract class Crops implements Plant {

    protected Acre.AssetType type;

    protected int harvestAge;
    protected double costPerAcre;
    protected double netPerAcre;

    protected int age;
    protected int farmId;
    protected PlantState currentState;
    protected HarvestListener harvestListener;

    protected Random randGenerator;
    
    public State getState() {
        return currentState.getState();
    }

    public void setState(State state) {
        this.currentState.setState(state);
    }

    public AssetType getType() {
        return type;
    }

    public void startRandom() {
        randGenerator = new Random();
    }

    public void notifyDay() {

        this.age++;

        if (age < 3) {
            currentState.advanceState();
        } else if (age == harvestAge && getState().equals(State.HEALTHY)) {
            currentState.advanceState();
            HarvestEvent e = new HarvestEvent(type, farmId);
            harvestListener.harvestEvent(e);
        } else if ((age - harvestAge) > 3) {
            currentState.advanceState();
        }
    }

    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(40); // %5 chance of early death
        if (sickOdds == 2) {
            setState(State.SICK);
        } else if (currentState.getState().equals(State.SICK)) {
            currentState.advanceState();
        }
        int weedOdds = randGenerator.nextInt(World.getFarm().getWeedRisk());
        if (weedOdds == 2) {
            setState(State.WEEDINFESTED);
        }
    }

    public double getCostPerAcre() {
        return costPerAcre;
    }

    public double getHarvestNetAmountPerAcre() {
        return netPerAcre;
    }

    public int getDaysToHarvest() {
        return harvestAge - age;
    }
    
    public void addHarvestListener(HarvestListener listener) {
        this.harvestListener = listener;
    }

    public void purge() {

        World.TimeManager.acquire();
        World.TimeManager.removeObserver(this);
        World.TimeManager.release();
        
        this.harvestListener = null;
    }
}
