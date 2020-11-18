

package TheDPFarm.animals;

public interface Livestock extends Animal {

    public int getBatchSize();
    public int getBatchPrice();
    public int getBatchesPerAcre();

    public double getHarvestPrivcePerBatch();
    public void setHarvestPrivcePerBatch(double harvestPrice);
    public double getSlaughterPricePerBatch();
    public void setSlaughterPricePerBatch(double slaughterPrice);
}
