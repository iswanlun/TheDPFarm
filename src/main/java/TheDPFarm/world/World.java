package TheDPFarm.world;

import java.util.ArrayList;

import TheDPFarm.levels.Farm;

public class World {

    private ArrayList<Farm> farmList = new ArrayList<>();

    public World() {
    }

    public void addFarm(Farm newFarm) {
        farmList.add(newFarm);
    }

    public void removeFarm(Farm oldFarm) {
        farmList.remove(oldFarm);
    }
}