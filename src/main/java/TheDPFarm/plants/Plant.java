package thedpfarm.plants;

import thedpfarm.plants.PlantState.State;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.DayAndNightObserver;

public interface Plant extends DayAndNightObserver {

    public State getState();

    public void setState(State state);
    
    public AssetType getType();

}
