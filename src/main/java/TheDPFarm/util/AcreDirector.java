package TheDPFarm.util;

import TheDPFarm.levels.Acre;
import TheDPFarm.levels.Acre.AssetType;

public class AcreDirector {

    private AcreBuilder cropBuilder;
    private AcreBuilder livestockBuilder;

    public AcreDirector(HarvestListener h, CollectionListener c) {
        cropBuilder = new CropBuilder(h, c);
        livestockBuilder = new Livestockbuilder(h, c);
    }

    public void constructCropAcre(Acre acre, AssetType aType) {
        cropBuilder.buildAcre(acre, aType);
    }

    public void constructLivestockAcre(Acre acre, AssetType aType) {
        livestockBuilder.buildAcre(acre, aType);
    }

    public void createAcres(int acres, double price) {
        cropBuilder.createAcre(acres, price);
    }    
}
