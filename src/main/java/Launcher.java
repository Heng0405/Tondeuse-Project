import actions.Parcourir;
import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.TailleDePelouse;
import models.Tondeuse;
import utils.GetPositionAndCommands;
import utils.ReadCommandFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Launcher {



    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("Le chemin vers le fichier doit etre donne en entree");
            System.exit(-1);
        }

        try {
            TailleDePelouse coinSuperieur = ReadCommandFile.getCoinSuperieur(args[0]);
            List<Tondeuse> tondeuses = GetPositionAndCommands.getTondeuses(args[0]);
            List<String> listActions = GetPositionAndCommands.getListActions(args[0]);
            ArrayList<Action>[] arrayActions = (ArrayList<Action>[]) GetPositionAndCommands.getObjectActions(listActions,coinSuperieur);
            Map<Tondeuse,ArrayList<Action>> map = GetPositionAndCommands.getTondeuseWithActions(tondeuses,arrayActions);
            Parcourir parcourir = new Parcourir();
            List<Tondeuse> tondeusesPasse = parcourir.passerCommands(map);
            parcourir.printResult(tondeusesPasse,args[1]);

        }catch (ParsingException e) {
            e.printStackTrace();
        } catch (OutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
