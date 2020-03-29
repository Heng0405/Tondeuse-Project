package utils;

import models.Action;
import actions.GoAvance;
import actions.GoDroite;
import actions.GoGauche;
import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import models.Tondeuse.Orientation;

import java.util.ArrayList;
import java.util.List;

/**
 * Get la position de tondeuse et les commandes à partir du fichier.
 */
public class GetPositionAndCommands {


    /**
     * Get the position and orientations of tondeuses.
     *
     * @param path chemin du fichier.
     */
    public static List<Tondeuse> getTondeuses(String path) throws ParsingException, OutOfBoundsException {

        ReadCommandFile readCommandFile = new ReadCommandFile(path);
        String[] commandes = readCommandFile.readCommands(path);
        List<Tondeuse> tondeuses = new ArrayList<Tondeuse>();
        for (int i = 0; i < commandes.length; i++) {
            if (commandes[i].length() == 5 && Character.isDigit(commandes[i].charAt(0))) {
                try {

                    int x = Integer.valueOf(commandes[i].split(" ")[0]);
                    int y = Integer.valueOf(commandes[i].split(" ")[1]);
                    Position position = new Position(x, y);
                    Orientation orientation = getOrientation(commandes[i]);
                    Tondeuse tondeuse = new Tondeuse(position, orientation);
                    tondeuses.add(tondeuse);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (ParsingException e) {
                    throw new ParsingException("Parsing Error");
                }
            }
        }

        return tondeuses;
    }

    /**
     * @param line exemple [2 3 N]
     * @return
     */
    public static Orientation getOrientation(String line) throws ParsingException {


        Orientation orientation;
        String[] lineSplited = line.split(" ");
        String orientationString = lineSplited[2];


        if ("N".equals(orientationString)) {
            orientation = Orientation.N;
        } else if ("S".equals(orientationString)) {
            orientation = Orientation.S;
        } else if ("W".equals(orientationString)) {
            orientation = Orientation.W;
        } else if ("E".equals(orientationString)) {
            orientation = Orientation.E;
        } else {
            throw new ParsingException("Orientation non confirmée");
        }
        return orientation;

    }

    /**
     *
     * @param path chemin du fichier
     * @return une liste qui contient les lignes de commandes.
     * @throws ParsingException
     */
    public static List<String> getListActions(String path) throws ParsingException {
        ReadCommandFile readCommandFile = new ReadCommandFile(path);
        String[] commandes = readCommandFile.readCommands(path);
        List<String> listActions = new ArrayList<String>();
        for (int i = 0; i < commandes.length; i++) {
            if (commandes[i].matches("^[a-zA-Z]*$")) {
                listActions.add(commandes[i]);
            }
        }return listActions;
    }


    public static List<Action>[] getObjectActions(List<String> listAction, TailleDePelouse coinSuperieur) throws ParsingException {
        ArrayList<Action>[] arrayActions = (ArrayList<Action>[])new ArrayList[listAction.size()];
        Action avance = new GoAvance(coinSuperieur);
        Action goDroite = new GoDroite(coinSuperieur);
        Action goGauche = new GoGauche(coinSuperieur);
        for(int i=0;i<listAction.size();i++){
            arrayActions[i] = new ArrayList<Action>();
            for(int j=0;j<listAction.get(i).length();j++){
                char action = listAction.get(i).charAt(j);
                switch (action) {
                    case 'A':
                        arrayActions[i].add(avance);
                        break;
                    case 'G':
                        arrayActions[i].add(goGauche);
                        break;
                    case 'D':
                        arrayActions[i].add(goDroite);
                        break;
                    default:
                        throw new ParsingException("Action non connue");
                }

            }
        }
        return arrayActions;
    }

    /**
     * Get les commandes de la linge.
     *
     * @param line exemple AADAADADDA
     */
    public static List<Action> getActions(String line, TailleDePelouse coinSuperieur) throws ParsingException {

        List<Action> actions = new ArrayList<Action>();
        Action avance = new GoAvance(coinSuperieur);
        Action goDroite = new GoDroite(coinSuperieur);
        Action goGauche = new GoGauche(coinSuperieur);
        int length = line.length();
        for (int i = 0; i < length; i++) {
            char action = line.charAt(i);
            switch (action) {
                case 'A':
                    actions.add(avance);
                    break;
                case 'G':
                    actions.add(goGauche);
                    break;
                case 'D':
                    actions.add(goDroite);
                    break;
                default:
                    throw new ParsingException("Action non connue");
            }
        }
        return actions;
    }


}
