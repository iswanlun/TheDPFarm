package TheDPFarm.util;

public class AcreDirector {

    private AcreBuilder cropBuilder;
    private AcreBuilder livestockBuilder;

    public AcreDirector() {
        cropBuilder = new CropBuilder();
        livestockBuilder = new Livestockbuilder();
    }

    public void constructCropAcre() {
        cropBuilder.getAcre();
    }

    public void constructLivestockAcre() {
        livestockBuilder.getAcre();
    }
    
}
