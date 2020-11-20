package thedpfarm.levels;

import java.util.Vector;

import thedpfarm.util.Acre;
import thedpfarm.util.SimulationDialog;
import thedpfarm.util.Acre.AssetType;

public interface Farm {

    public int getFarmId();

    public void setId(int id);

    public int size();

    public void expandFarm(Acre addition);

    public void trimFarm(int removal);

    public Acre getSpecificAcre(int index);

    public double getTaxRate();

    public Vector<Acre> getEmptyAcres();

    public Vector<Acre> getHarvestAcres(AssetType type);

    public Vector<Acre> getCollectAcres(AssetType type);

    public int numCropsAcres();

    public int numLivestockAcres();

    public void auditCrops(SimulationDialog dlg);

    public void auditLivestock(SimulationDialog dlg);

    public int getLevel();

    public double weedCost();

    public void removeWeeds();
}
