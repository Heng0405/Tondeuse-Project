package utils;

import actions.GoAvance;
import actions.GoGauche;
import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

public class GetPositionAndCommandsTest {


    @Test
    public void testgetTondeuses() throws OutOfBoundsException, ParsingException {
        String path = "src/test/resources/file.txt";
        List<Tondeuse> tondeuses = GetPositionAndCommands.getTondeuses(path);
        assertEquals(Tondeuse.Orientation.N, tondeuses.get(0).getOrientation());
        assertEquals(new Position(1,2), tondeuses.get(0).getPosition());
        assertEquals(Tondeuse.Orientation.E, tondeuses.get(1).getOrientation());
        assertEquals(new Position(3,3), tondeuses.get(1).getPosition());
    }


    @Test
    public void testgetListActions() throws ParsingException{
        String path = "src/test/resources/file.txt";
        List<String> listActions = GetPositionAndCommands.getListActions(path);
        assertEquals("GAGAGAGAA",listActions.get(0));
        assertEquals("AADAADADDA",listActions.get(1));
    }

    @Test
    public void testgetObjectActions() throws ParsingException{
        TailleDePelouse coinSuperieur = new TailleDePelouse(5,5);
        List<String> listAction = new ArrayList<String>();
        listAction.add("G");
        listAction.add("AA");
        ArrayList<Action>[] arrayActions = (ArrayList<Action>[]) GetPositionAndCommands.getObjectActions(listAction,coinSuperieur);
        assertSame(2,arrayActions.length);
        assertEquals(new GoGauche(coinSuperieur),arrayActions[0].get(0));
        assertEquals(new GoAvance(coinSuperieur),arrayActions[1].get(0));
        assertEquals(new GoAvance(coinSuperieur),arrayActions[1].get(1));

    }

}
