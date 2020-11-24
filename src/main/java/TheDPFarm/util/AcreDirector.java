package thedpfarm.util;

import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.Acre.UsageType;
import thedpfarm.world.World;

public class AcreDirector {

    private AcreBuilder cropBuilder;
    private AcreBuilder livestockBuilder;

    public AcreDirector(SimulationDialog dlg) {
        cropBuilder = new CropBuilder(dlg);
        livestockBuilder = new Livestockbuilder(dlg);
    }

    /**
     * Creates a crop acre.
     * @param acre Acre to construct.
     * @param assetType Asset type to insert.
     */
    public void constructCropAcre(Acre acre, AssetType assetType) {
        try {
            cropBuilder.buildAcre(acre, assetType);
        } catch (FinanceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a livestock acre.
     * @param acre Acre to construct.
     * @param assetType Asset type to insert.
     */
    public void constructLivestockAcre(Acre acre, AssetType assetType) {
        try {
            livestockBuilder.buildAcre(acre, assetType);
        } catch (FinanceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Created a series of new unused acres.
     * @param acres Amount of acres to be created.
     */
    public void createAcres(int acres) {
        for (int i = 0; i < acres; i++) {
            World.getFarm().expandFarm(new Acre(UsageType.EMPTY, AssetType.EMPTY));
        }
    }
}
