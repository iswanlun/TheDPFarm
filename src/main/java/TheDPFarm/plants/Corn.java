package TheDPFarm.plants;

import java.util.Random;

public class Corn implements Crops {

    private PlantState currentState;
    private final String type = "Corn";
    private int age;
    private int harvestAge = 10;
    private double costPerAcre = 125.0;
    private double netPerAcre = 350.0;
    
    private Random randGenerator;

    public Corn() {
        this.age = 0;
        this.currentState = PlantState.Seed;
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

        if(age == harvestAge) {
            currentState = PlantState.ReadyForHarvest;
        } else if ((age - harvestAge) > 3) {
            currentState = PlantState.Dead;
        }

        if(currentState.equals(PlantState.Seed)) {
            currentState = PlantState.Seedling;
        } else if (currentState.equals(PlantState.Seedling)) {
            currentState = PlantState.Healthy;
        }
    }

    @Override
    public void notifyNight() {
        int sickOdds = randGenerator.nextInt(20); //%5 chance of early death
        if (sickOdds == 2) {
            currentState = PlantState.Sick;
        } else if (currentState.equals(PlantState.Sick)) {
            currentState = PlantState.Dead;
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
