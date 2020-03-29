package utils;

import exceptions.ParsingException;
import models.TailleDePelouse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ReadCommandFileTest {


    @Test(expected = ParsingException.class)
    public void testEmptyFile() throws ParsingException{
        String path = "src/test/resources/EmptyFile.txt";
        ReadCommandFile.getCoinSuperieur(path);
    }

    @Test(expected = ParsingException.class)
    public void testMauvaisPath() throws ParsingException{
        String path = "testPath";
        ReadCommandFile.getCoinSuperieur(path);
    }

    @Test
    public void testgetCoinSuperieur() throws ParsingException{
        String path = "src/test/resources/file.txt";
        TailleDePelouse pelouse = ReadCommandFile.getCoinSuperieur(path);
        assertEquals(new TailleDePelouse(5,5),pelouse);
    }






}
