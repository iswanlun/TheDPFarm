package TheDPFarm.plants;

import java.util.Random;

import TheDPFarm.plants.PlantState.state;

public class Wheat implements Crops {
    private PlantState currentState;
    private final String type = "Wheat";
    private int age;
    private int harvestAge = 12;
    private double costPerAcre = 135.0;
    private double netPerAcre = 380.0;
    
    private Random randGenerator;

    public Wheat(PlantState state) {
        this.age = 0;
        this.currentState = state;
        randGenerator = new Random();
    }

    @Override
    public PlantState getState() {
        return currentState;
    }

    @Override
    public void setState(PlantState newState) {
        this.currentState = newState;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void notifyDay() {
        this.age++;
        if (age < 3) {
            currentState.advanceState();
        } else if(age == harvestAge) {
            currentState.advanceState();
        } else if ((age - harvestAge) > 3) {
            currentState.advanceState();
        }
    }

    @Override
    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(20); //%5 chance of early death
        if (sickOdds == 2) {
            currentState.setState(state.Sick);
        } else if (currentState.getState().equals(state.Sick)) {
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
}
