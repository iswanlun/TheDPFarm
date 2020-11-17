package TheDPFarm;

import java.util.ArrayList;

public class Farm {

    private int numFarmers;
    private String farmName;

    private ArrayList<Animal> livestock = new ArrayList<>();
    private ArrayList<Plant> crops = new ArrayList<>();
    
    public Farm(String farmName, int numFarmers) {
        this.farmName = farmName;
        this.numFarmers = numFarmers;
    }

    public void addFarmer() {
        numFarmers++;
    }

    public void removeFarmer() {
        numFarmers--;
    }

    
}
