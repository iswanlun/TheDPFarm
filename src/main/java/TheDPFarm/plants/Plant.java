
package thedpfarm.plants;

import thedpfarm.plants.PlantState.State;
import thedpfarm.util.DayAndNightObserver;
import thedpfarm.util.Acre.AssetType;

public interface Plant extends DayAndNightObserver {

    public State getState();
    public void setState(State state);
    public AssetType getType();

}
