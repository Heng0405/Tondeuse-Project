package actions;

import exceptions.OutOfBoundsException;
import models.Action;
import models.TailleDePelouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.Position;
import models.Tondeuse;

public class GoAvance extends Action {
    static final Logger LOGGER = LoggerFactory.getLogger(GoAvance.class);

    public GoAvance(TailleDePelouse coinSuperieur){
        super(coinSuperieur);

    }

    @Override
    public Tondeuse execute(Tondeuse tondeuse) throws OutOfBoundsException {
        LOGGER.info("Tondeuse prêt à avancer!");
        LOGGER.info("Position initiale : (" + tondeuse.getPosition().getX() + "," + tondeuse.getPosition().getY() + ")");
        LOGGER.info("Orientation initiale : {}", tondeuse.getOrientation());

        switch (tondeuse.getOrientation()) {
            case N:
                northAvance(tondeuse);
                break;
            case W:
                westAvance(tondeuse);
                break;
            case E:
                eastAvance(tondeuse);
                break;
            case S:
                southAvance(tondeuse);
                break;
            default:
                throw new OutOfBoundsException("Out of boundry");
        }

        LOGGER.info("Avancement terminé!");
        LOGGER.info("Position finale ({} {})", tondeuse.getPosition().getX(), tondeuse.getPosition().getY());
        LOGGER.info("Orientation finale {}", tondeuse.getOrientation());
        //return null;
        return tondeuse;
    }

    private Tondeuse southAvance(Tondeuse tondeuse) throws OutOfBoundsException {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int yInitale = position.getY();
        int xInitale = position.getX();
        if (yInitale > Action.minimumY) {
            LOGGER.info("Avancement autorisé");
            try {
                position.setY(yInitale - 1);
            } catch (OutOfBoundsException e) {
                LOGGER.error("Avancement non autorisé");
            }
        }
        return new Tondeuse(position,orientation);
    }

    private Tondeuse eastAvance(Tondeuse tondeuse) throws OutOfBoundsException {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int xInitale = position.getX();
        int yInitale = position.getY();
        if (xInitale < coinSuperieur.getMaxX()) {
            LOGGER.info("Avancement autorisé");
            try {
                position.setX(xInitale + 1);
            } catch (OutOfBoundsException e) {
                LOGGER.error("Avancement non autorisé");
            }
        }
        return new Tondeuse(position,orientation);
    }

    private Tondeuse westAvance(Tondeuse tondeuse) throws OutOfBoundsException {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int xInitale = position.getX();
        int yInitale = position.getY();
        if (xInitale > Action.minimumX) {
            LOGGER.info("Avancement autorisé");
            try {
                position.setX(xInitale - 1);
            } catch (OutOfBoundsException e) {
                LOGGER.error("Avancement non autorisé");
            }
        }
        return new Tondeuse(position,orientation);
    }

    private Tondeuse northAvance(Tondeuse tondeuse) throws OutOfBoundsException {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int yInitale = position.getY();
        int xInitale = position.getX();
        if (yInitale < coinSuperieur.getMaxY()) {
            LOGGER.info("Avancement autorisé");
            try {
                position.setY(yInitale + 1);
            } catch (OutOfBoundsException e) {
                LOGGER.error("Avancement non autorisé");
            }
        }
        return new Tondeuse(position,orientation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GoAvance avance =(GoAvance) obj;
        if(avance.coinSuperieur != ((GoAvance) obj).coinSuperieur){
            return false;
        }
        return true;

    }
}
