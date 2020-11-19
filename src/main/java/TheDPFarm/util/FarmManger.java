package TheDPFarm.util;

import TheDPFarm.world.World;

public class FarmManger {

    private final int farmPrice = 100000;
    private final int acrePrice = 2000;
    private final int firstFarmSize = 50;

	public void sellAcres(String string) {
	}

	public void buyAcres(String string) {
	}

	public void addFence(String string) {
	}

	public void addDogs(String string) {
	}

	public void switchFarm(String string) {
	}

	public void newFarm() {
        if (World.getNumFarms() == 0) {
			//Sets up a default farm
		} else {
			//sets up a default farm and charges the player for it
		}
	}
	
	public void auditCrops() {
	}

	public void plant(String string, String string2) {
	}

	public void harvestCrops(String string) {
	}

	public void removeWeeds() {
	}

	public void auditLivestock() {
	}

	public void raise(String string, String string2) {
	}

	public void harvestLivestock(String string) {
	}

	public void collectProducts(String string) {
	}
    
}
