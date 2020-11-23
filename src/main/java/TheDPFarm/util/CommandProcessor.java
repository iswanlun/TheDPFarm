package thedpfarm.util;

import thedpfarm.world.World;

public class CommandProcessor {

    SimulationDialog dlg;
    FarmManager mngr;

    public CommandProcessor() {
        dlg = new SimulationDialog();
        mngr = new FarmManager(dlg);
        dlg.welcome();
    }

    public void process(String line) {
        line = line.toLowerCase();
        String[] tokens = line.split(" ");
        try {
            switch (tokens[0]) {
                case "f": farmProcessor(tokens);
                    break;
                case "c": cropProcessor(tokens);
                    break;
                case "l": livestockProcessor(tokens);
                    break; 
                case "help": dlg.help();
                    break;
                default:
                    dlg.defaultMsg(tokens);
            }
        } catch (NullPointerException n) {
            dlg.badArgumentType();
        }
    }

    /**
     * Processes farm commands.
     * @param args String array of arguments.
     */
    private void farmProcessor(String[] args) {
        try {
            switch (args[1]) {
                case "sell": mngr.sellAcres(args[2]);
                    break;
                case "buy": mngr.buyAcres(args[2]);
                    break;
                case "fence": mngr.addFence(args[2]);
                    break;
                case "dogs": mngr.addDogs(args[2]);
                    break;
                case "status": World.upgradeFarm();
                               dlg.statusMessage();
                    break;
                case "switch": mngr.switchFarm(args[2]);
                    break;
                case "new": mngr.newFarm();
                    break;
                default: dlg.defaultMsg(args);
            }
        } catch (IndexOutOfBoundsException i) {
            dlg.tooFewArguments();
        } catch (NumberFormatException n) {
            dlg.badArgumentType();
        }
    }

    /**
     * Processes crop commands.
     * @param args String array of arguments.
     */
    private void cropProcessor(String[] args) {
        try {
            switch (args[1]) {
                case "audit": mngr.auditCrops();
                    break;
                case "plant": mngr.plant(args[2], args[3]);
                    break;
                case "harvest": mngr.harvestAcres(args[2]);
                    break;
                case "remove": mngr.removeWeeds();
                    break;
                default: dlg.defaultMsg(args);
            }
        } catch (IndexOutOfBoundsException i) {
            dlg.tooFewArguments();
        } catch (NumberFormatException n) {
            dlg.badArgumentType();
        }
    }

    /**
     * Processes livestock commands.
     * @param args String array of arguments.
     */
    private void livestockProcessor(String[] args) {
        try {
            switch (args[1]) {
                case "audit": mngr.auditLivestock();
                    break;
                case "raise": mngr.raise(args[2], args[3]);
                    break;
                case "harvest": mngr.harvestAcres(args[2]);
                    break;
                case "collect": mngr.collectProducts(args[2]);
                    break;
                default: dlg.defaultMsg(args);
            }
        } catch (IndexOutOfBoundsException i) {
            dlg.tooFewArguments();
        } catch (NumberFormatException n) {
            dlg.badArgumentType();
        }
    }
}
