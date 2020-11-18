package TheDPFarm.world;

import java.util.ArrayList;

import TheDPFarm.levels.Farm;

public class SimWorld {

    static class World {

        private ArrayList<Farm> farmList = new ArrayList<>();
        private Farm currentFarm;

        public void addFarm(Farm newFarm) {
            farmList.add(newFarm);
            newFarm.setId(farmList.indexOf(newFarm));
        }

        public void removeFarm(Farm oldFarm) {
            farmList.remove(oldFarm);
        }
        
        public Farm getFarm(int id) {
            for (Farm f : farmList) {
                if(f.getFarmId() == id) {
                    return f;
                }
            }
            return null;
        }

        public void setFarm(int id) {
            currentFarm = getFarm(id);
        }

        public Farm getFarm() {
            return currentFarm;
        }
    }
}