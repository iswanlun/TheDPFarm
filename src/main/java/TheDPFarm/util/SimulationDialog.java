package TheDPFarm.util;

/**
 * This class contains the dialog methods for printing to the console.
 * All println's are contained here, with a few exceptions.
 */
public class SimulationDialog {

	public void getStatus() {
        // TODO get the status of the whole farm
	}

	public void help() {
        System.out.println("Refer to the documentation in Doc.md.");
	}

	public void welcome() {
        System.out.println("Welcome to TheDPFarm Simulator. Type 'help' for help.");
	}
    
    public void defaultMsg(String[] args) {
        System.out.print("The command string : ");
        for (String s : args) {
            System.out.print(" " + s);
        }
        System.out.println(" does not match any known parameter form.");
    }

	public void harvestMsg(String assetType, int acreId, int farmId) {
        System.out.println(assetType + " on acre " + acreId + " of farm number " + farmId + " is ready for harvest.");
    }

    public void collectMsg(String assetType, int acreId, int farmId) {
        System.out.println(assetType + " on acre " + acreId + " of farm number " + farmId + " can be collected.");
	}
}
