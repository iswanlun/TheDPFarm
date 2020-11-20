
package TheDPFarm.plants;

import TheDPFarm.plants.PlantState.State;
import TheDPFarm.util.DayAndNightObserver;
import TheDPFarm.util.Acre.AssetType;

public interface Plant extends DayAndNightObserver {

    public State getState();
    public void setState(State state);
    public AssetType getType();

}
