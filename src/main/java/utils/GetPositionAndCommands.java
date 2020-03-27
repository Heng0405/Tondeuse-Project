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
     * Get the coin supérieur de la pelouse.
     *
     * @param lineSplited exemple [5 5]
     */
    public static Position getCoodinateOrSize(String[] lineSplited) throws OutOfBoundsException, ParsingException {

        if (lineSplited.length != 2) {
            throw new ParsingException("This is not the right line");
        }
        int xLimit = 0; //coin supérieur de la pelouse
        int yLimit = 0; //coin supérieur de la pelouse
        try {
            xLimit = Integer.parseInt(lineSplited[0]);
            yLimit = Integer.parseInt(lineSplited[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new Position(xLimit, yLimit);
    }

    /**
     * @param lineSplited exemple [2 3 N]
     * @return
     */
    public static Orientation getOrientation(String[] lineSplited) throws ParsingException {


        Orientation orientation;
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
     * Get the coordonnée et orientation de la tondeuse.
     * @param line exemple 2 3 N
     */
    public static Tondeuse getCoordinateOrientation(String line) throws ParsingException, OutOfBoundsException {

        String[] lineSplited = line.split(" ");
        if (lineSplited.length != 3) {
            throw new ParsingException("This is not the right line");
        }
        Orientation orientation = getOrientation(lineSplited);
        String[] coordinate = new String[]{lineSplited[0], lineSplited[1]};
        Position position = getCoodinateOrSize(coordinate);
        return new Tondeuse(position, orientation);
    }


    /**
     * Get les commandes de la linge.
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
