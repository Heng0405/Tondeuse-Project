package utils;

import exceptions.ParsingException;
import models.TailleDePelouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCommandFile {
    /**
     * Lecture du fichier de commande.
     */

    static final Logger LOGGER = LoggerFactory.getLogger(ReadCommandFile.class);
    private String path;


    private ReadCommandFile(String path){
        this.path = path;
    }


    /**
     *
     * @param path chemin du fichier.
     * @return un tableau qui contient des commandes dans le fichier.
     * @throws ParsingException
     */
    private String[] readCommand(String path) throws ParsingException {
        LOGGER.info("Lecture du fichier");
        FileReader fileReader = null;
        List<String> lines = new ArrayList<String>();
        try{
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new ParsingException("Fichier non trouvé");
        }
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine())!=null){
               lines.add(line);
            }

        }catch (IOException io){
            LOGGER.debug("Erreur en parsant le fichier: " +io.getMessage());
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return lines.toArray(new String[lines.size()]);
    }

    public static TailleDePelouse getCoinSuperieur(String path) throws ParsingException{
        LOGGER.info("Lecture de la première ligne du fichier");
        ReadCommandFile readCommandFile = new ReadCommandFile(path);
        String[] commandes = readCommandFile.readCommand(path);
        if(commandes.length ==0){
            throw new ParsingException("Fichier est vide");
        }
        TailleDePelouse pelouse = null;

        try {
            String firstLine = commandes[0];
            int xMax = Integer.getInteger(firstLine.split("")[0]);
            int yMax = Integer.getInteger(firstLine.split("")[1]);
            pelouse = new TailleDePelouse(xMax,yMax);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return pelouse;

    }































}
