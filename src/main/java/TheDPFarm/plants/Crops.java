package TheDPFarm.plants;

import java.util.Random;

import TheDPFarm.util.Acre;
import TheDPFarm.util.HarvestEvent;
import TheDPFarm.util.HarvestListener;
import TheDPFarm.util.Acre.AssetType;
import TheDPFarm.world.World;
import TheDPFarm.plants.PlantState.State;

public abstract class Crops implements Plant {

    protected Acre.AssetType type;

    protected int harvestAge;
    protected double costPerAcre;
    protected double netPerAcre;

    protected int age;
    protected int farmId;
    protected PlantState currentState;
    protected HarvestListener hListener;

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
            hListener.harvestEvent(e);
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
        int weedOdds = randGenerator.nextInt(40); // %5 chance of getting weeds
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
        this.hListener = listener;
    }

    public void purge() {

        World.TimeManager.acquire();
        World.TimeManager.removeObserver(this);
        World.TimeManager.release();
        
        this.hListener = null;
    }
}
