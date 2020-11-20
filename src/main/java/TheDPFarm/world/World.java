package TheDPFarm.world;

import java.util.ArrayList;

import TheDPFarm.levels.Farm;
import TheDPFarm.util.DayNightManager;

public class World {

    private static ArrayList<Farm> farmList = new ArrayList<>();
    public final static DayNightManager TimeManager = new DayNightManager();
    private static Farm currentFarm;

    public static void addFarm(Farm newFarm) {
        farmList.add(newFarm);
        newFarm.setId(farmList.indexOf(newFarm));
        currentFarm = newFarm;
    }

    public static void removeFarm(Farm oldFarm) {
        farmList.remove(oldFarm);
        for (Farm f : farmList) {
            f.setId(farmList.indexOf(f));
        }
    }
    
    public static Farm getFarm(int id) {
        for (Farm f : farmList) {
            if(f.getFarmId() == id) {
                return f;
            }
        }
        return null;
    }

    public static void setFarm(int id) {
        currentFarm = getFarm(id);
    }

    public static Farm getFarm() {
        return currentFarm;
    }

    public static int getNumFarms() {
        return farmList.size();
    }

    public static ArrayList<Farm> getFarmList() {
        return farmList;
    }
}
