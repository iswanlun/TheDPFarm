
package TheDPFarm.plants;

import TheDPFarm.plants.PlantState.state;
import TheDPFarm.util.DayAndNightObserver;
import TheDPFarm.util.Acre.AssetType;

public interface Plant extends DayAndNightObserver {

    public state getState();
    public void setState(PlantState newState);
    public AssetType getType();

}
