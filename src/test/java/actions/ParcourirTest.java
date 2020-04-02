package actions;

import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.junit.Before;
import org.junit.Test;
import utils.GetPositionAndCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ParcourirTest {
    Parcourir parcourir;

    @Before
    public void setUp(){
        parcourir = new Parcourir();
    }

    @Test
    public void testpasserCommands() throws ParsingException, OutOfBoundsException {
        String path = "src/test/resources/fileTest.txt";
        String pathResult = "/home/heng/Bureau/result.txt";
        TailleDePelouse coinSuperieur = new TailleDePelouse(5,5);
        List<Tondeuse> tondeuses = GetPositionAndCommands.getTondeuses(path);
        List<String> listActions = GetPositionAndCommands.getListActions(path);
        ArrayList<Action>[] arrayActions = (ArrayList<Action>[]) GetPositionAndCommands.getObjectActions(listActions,coinSuperieur);
        Map<Tondeuse,ArrayList<Action>> map = GetPositionAndCommands.getTondeuseWithActions(tondeuses,arrayActions);
        List<Tondeuse> tondeusesPasse = parcourir.passerCommands(map);
        parcourir.printResult(tondeusesPasse,pathResult);


        assertSame(2,tondeusesPasse.size());

        assertEquals(Tondeuse.Orientation.W, tondeusesPasse.get(0).getOrientation());
        assertEquals(new Position(0,2), tondeusesPasse.get(0).getPosition());


        assertEquals(Tondeuse.Orientation.S,tondeusesPasse.get(1).getOrientation());
        assertEquals(new Position(5,3), tondeusesPasse.get(1).getPosition());



    }

}
