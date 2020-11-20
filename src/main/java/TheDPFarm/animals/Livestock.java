

package TheDPFarm.animals;

import TheDPFarm.util.CollectionListener;
import TheDPFarm.util.HarvestListener;

public interface Livestock extends Animal {

    public int getBatchSize();
    public int getBatchPrice();
    public int getBatchesPerAcre();

    public double getCollectPricePerBatch();
    public void setCollectPricePerBatch(double harvestPrice);

    public double getHarvestPricePerBatch();
    public void setHarvestPricePerBatch(double slaughterPrice);

    public void addHarvestListener(HarvestListener listener);
    public void addCollectionListener(CollectionListener listener);
}
