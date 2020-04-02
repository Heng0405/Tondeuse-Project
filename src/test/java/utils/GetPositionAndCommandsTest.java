package utils;

import actions.GoAvance;
import actions.GoGauche;
import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetPositionAndCommandsTest {
    String path;
    TailleDePelouse coinSuperieur;


    @Before
    public void setUp(){
        path = "src/test/resources/file.txt";
        coinSuperieur = new TailleDePelouse(5,5);
    }


    @Test
    public void testgetTondeuses() throws OutOfBoundsException, ParsingException {

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
        List<String> listAction = new ArrayList<String>();
        listAction.add("G");
        listAction.add("AA");
        ArrayList<Action>[] arrayActions = (ArrayList<Action>[]) GetPositionAndCommands.getObjectActions(listAction,coinSuperieur);
        assertSame(2,arrayActions.length);
        assertEquals(new GoGauche(coinSuperieur),arrayActions[0].get(0));
        assertEquals(new GoAvance(coinSuperieur),arrayActions[1].get(0));
        assertEquals(new GoAvance(coinSuperieur),arrayActions[1].get(1));

    }

    @Test
    public void testgetTondeuseWithActions() throws ParsingException, OutOfBoundsException {
        List<Tondeuse> tondeuses = GetPositionAndCommands.getTondeuses(path);
        List<String> listActions = GetPositionAndCommands.getListActions(path);
        ArrayList<Action>[] arrayActions = (ArrayList<Action>[]) GetPositionAndCommands.getObjectActions(listActions,coinSuperieur);
        Map<Tondeuse,ArrayList<Action>> map = GetPositionAndCommands.getTondeuseWithActions(tondeuses,arrayActions);
        List<Tondeuse> keys = new ArrayList<Tondeuse>();
        for(Tondeuse tondeuse : map.keySet()){
            keys.add(tondeuse);
        }
        assertSame(1,keys.get(0).getPosition().getX());
        assertSame(2,keys.get(0).getPosition().getY());
        assertSame(Tondeuse.Orientation.N,keys.get(0).getOrientation());

        assertSame(3,keys.get(1).getPosition().getX());
        assertSame(3,keys.get(1).getPosition().getY());
        assertSame(Tondeuse.Orientation.E,keys.get(1).getOrientation());


        List<ArrayList<Action>> actionlist = new ArrayList<ArrayList<Action>>();
        for(Map.Entry<Tondeuse, ArrayList<Action>> entry : map.entrySet()){
            actionlist.add(entry.getValue());
        }
        assertSame(2,actionlist.size());
        assertSame(9,actionlist.get(0).size());
        assertSame(10,actionlist.get(1).size());
        assertEquals(new GoGauche(coinSuperieur),actionlist.get(0).get(0));
        assertEquals(new GoAvance(coinSuperieur),actionlist.get(1).get(0));

    }

}
