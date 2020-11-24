package thedpfarm.world;

import java.util.ArrayList;
import java.util.Random;

import thedpfarm.levels.Farm;
import thedpfarm.levels.FarmLevelThree;
import thedpfarm.levels.FarmLevelTwo;
import thedpfarm.util.DayNightManager;
import thedpfarm.util.TerminalStateListener;
import thedpfarm.util.TerminalStateManager;

public class World {

    private static ArrayList<Farm> farmList = new ArrayList<>();
    public final static DayNightManager TimeManager = new DayNightManager();
    private static TerminalStateManager terminalManager = new TerminalStateManager();
    private static Farm currentFarm;

    /**
     * Adds a new farm to the world.
     * @param newFarm Farm to be added.
     */
    public static void addFarm(Farm newFarm) {
        farmList.add(newFarm);
        Random r = new Random();
        int farmId = r.nextInt(899) + 100;
        newFarm.setId(farmId);
        currentFarm = newFarm;
    }

    /**
     * Removes farms from world.
     * @param oldFarm Farm to remove.
     */
    public static void removeFarm(Farm oldFarm) {
        farmList.remove(oldFarm);
        oldFarm = null;
    }

    /**
     * Return the specified farm.
     */
    public static Farm getFarm(int id) {
        for (Farm f : farmList) {
            if (f.getFarmId() == id) {
                return f;
            }
        }
        return null;
    }

    public static Farm getFarm() {
        return currentFarm;
    }

    public static void addTerminalStateListener(TerminalStateListener listener) {
        terminalManager.addListeners(listener);
    }

    public static void setFarm(int id) {
        currentFarm = getFarm(id);
    }

    public static int getNumFarms() {
        return farmList.size();
    }

    public static ArrayList<Farm> getFarmList() {
        return farmList;
    }

    /**
     * Inspects a farm for a defunct condition.
     */
    public static void inspectFarm() {
        if (Bank.accountBalance(currentFarm.getFarmId()) < 500) {
            terminalManager.notifyDefunct(currentFarm);
        }
    }

    /**
     * Evaluates a farm to determine if given the present level, it can upgrade.
     */
    public static void upgradeFarm() {
        switch (currentFarm.getLevel()) {
            case 1:
                if (Bank.accountBalance(currentFarm.getFarmId()) > 400000) {
                    currentFarm = new FarmLevelTwo(currentFarm);
                }
                break;
            case 2:
                if (Bank.accountBalance(currentFarm.getFarmId()) > 500000) {
                    currentFarm = new FarmLevelThree(currentFarm);
                }
                break;
            case 3:
                if (Bank.accountBalance(currentFarm.getFarmId()) > 650000) {
                    terminalManager.notifySuccess(currentFarm);
                }
                break;
            default:
                break;
        }
    }
}
