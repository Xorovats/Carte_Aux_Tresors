package main.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LecteurFichierEntree {

    public static List<String> lireFichier(String chemainFichier){

        List<String> lignes = new ArrayList<String>();
        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemainFichier))){
            String ligne;
            while((ligne = lecteur.readLine()) != null){
                if (!ligne.isEmpty() && ligne.charAt(0) != '#')
                    lignes.add(ligne);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return lignes;
    }

}
