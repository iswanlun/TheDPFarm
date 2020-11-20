package TheDPFarm.levels;

import java.util.Vector;
import TheDPFarm.util.Acre;

public interface Farm {

    public int getFarmId();

    public void setId(int id);

    public int size();

    public void expandFarm(Acre addition);

    public void trimFarm(int removal);

    public Acre getSpecificAcre(int index);

    public double getTaxRate();

    public Vector<Acre> getEmptyAcres();
}
