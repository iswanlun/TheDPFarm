package TheDPFarm.util;

import TheDPFarm.util.Acre.AssetType;
import TheDPFarm.util.Acre.UsageType;
import TheDPFarm.world.Bank;
import TheDPFarm.world.World;

public class AcreDirector {

    private AcreBuilder cropBuilder;
    private AcreBuilder livestockBuilder;

    public AcreDirector(HarvestListener h, CollectionListener c) {
        cropBuilder = new CropBuilder(h, c);
        livestockBuilder = new Livestockbuilder(h, c);
    }

    public void constructCropAcre(Acre acre, AssetType aType) {
        try {
            cropBuilder.buildAcre(acre, aType);
        } catch (FinanceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void constructLivestockAcre(Acre acre, AssetType aType) {
        try {
            livestockBuilder.buildAcre(acre, aType);
        } catch (FinanceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAcres(int acres, double price) {
        Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(price);
        for (int i = 0; i < price; i++) {
            World.getFarm().expandFarm(new Acre(UsageType.EMPTY));
        }
    }
}
