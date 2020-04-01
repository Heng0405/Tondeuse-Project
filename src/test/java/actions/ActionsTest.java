package actions;

import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.junit.Test;
import utils.GetPositionAndCommands;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class ActionsTest {

    @Test
    public void testGoAvanceE() throws OutOfBoundsException {
        Position position = new Position(1,1);
        Tondeuse.Orientation orientation = Tondeuse.Orientation.E;
        Tondeuse tondeuse = new Tondeuse(position,orientation);
        TailleDePelouse coinSuperieur = new TailleDePelouse(5,5);
        GoAvance avance = new GoAvance(coinSuperieur);
        Tondeuse tondeusePass = avance.execute(tondeuse);
        assertSame(2,tondeusePass.getPosition().getX());
        assertSame(1,tondeusePass.getPosition().getY());
        assertSame(Tondeuse.Orientation.E,tondeusePass.getOrientation());
    }
    @Test
    public void testGoAvanceS() throws OutOfBoundsException {
        Position position = new Position(1,1);
        Tondeuse.Orientation orientation = Tondeuse.Orientation.S;
        Tondeuse tondeuse = new Tondeuse(position,orientation);
        TailleDePelouse coinSuperieur = new TailleDePelouse(5,5);
        GoAvance avance = new GoAvance(coinSuperieur);
        Tondeuse tondeusePass = avance.execute(tondeuse);
        assertSame(1,tondeusePass.getPosition().getX());
        assertSame(0,tondeusePass.getPosition().getY());
        assertSame(Tondeuse.Orientation.S,tondeusePass.getOrientation());
    }

    @Test
    public void testGoDroiteN() throws OutOfBoundsException, ParsingException {
        Position position = new Position(1,2);
        Tondeuse.Orientation orientation = Tondeuse.Orientation.N;
        Tondeuse tondeuse = new Tondeuse(position,orientation);
        TailleDePelouse coinSuperieur = new TailleDePelouse(5,5);
        GoDroite goDroite = new GoDroite(coinSuperieur);
        Tondeuse tondeusePass = goDroite.execute(tondeuse);
        assertSame(1,tondeusePass.getPosition().getX());
        assertSame(2,tondeusePass.getPosition().getY());
        assertSame(Tondeuse.Orientation.E,tondeusePass.getOrientation());
    }

    @Test
  public void testGoGaucheW() throws OutOfBoundsException, ParsingException {
        Position position = new Position(1,2);
        Tondeuse.Orientation orientation = Tondeuse.Orientation.W;
        Tondeuse tondeuse = new Tondeuse(position,orientation);
        TailleDePelouse coinSuperieur = new TailleDePelouse(5,5);
        GoGauche goGauche = new GoGauche(coinSuperieur);
        Tondeuse tondeusePass = goGauche.execute(tondeuse);
        assertSame(Tondeuse.Orientation.S,tondeusePass.getOrientation());
    }

}
