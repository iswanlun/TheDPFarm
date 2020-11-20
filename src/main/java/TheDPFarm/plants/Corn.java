package TheDPFarm.plants;

import java.util.Random;

import TheDPFarm.plants.PlantState.state;
import TheDPFarm.util.Acre;
import TheDPFarm.util.HarvestEvent;
import TheDPFarm.util.HarvestListener;
import TheDPFarm.util.Acre.AssetType;

public class Corn implements Crops {

    
    private final Acre.AssetType type = AssetType.CORN;
    private final int harvestAge = 10;
    private final double costPerAcre = 125.0;
    private final double netPerAcre = 350.0;

    private int age;
    private int farmId;
    private PlantState currentState;
    private HarvestListener hListener;

    private Random randGenerator;

    public Corn(PlantState state, int farmId) {
        this.age = 0;
        this.currentState = state;
        this.farmId = farmId;
        randGenerator = new Random();
    }

    @Override
    public state getState() {
        return currentState.getState();
    }

    @Override
    public void setState(PlantState newState) {
        this.currentState = newState;
    }

    @Override
    public AssetType getType() {
        return type;
    }

    @Override
    public void notifyDay() {

        this.age++;

        if (age < 3) {
            currentState.advanceState();
        } else if (age == harvestAge) {
            currentState.advanceState();
            HarvestEvent e = new HarvestEvent(type, farmId);
            hListener.harvestEvent(e);
        } else if ((age - harvestAge) > 3) {
            currentState.advanceState();
        }
    }

    @Override
    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(20); // %5 chance of early death
        if (sickOdds == 2) {
            currentState.setState(state.SICK);
        } else if (currentState.getState().equals(state.SICK)) {
            currentState.advanceState();
        }
    }

    @Override
    public double getCostPerAcre() {
        return costPerAcre;
    }

    @Override
    public double getHarvestNetAmountPerAcre() {
        return netPerAcre;
    }

    @Override
    public int getDaysToHarvest() {
        return harvestAge - age;
    }

    @Override
    public void addHarvestListener(HarvestListener listener) {
        this.hListener = listener;
    }
}
