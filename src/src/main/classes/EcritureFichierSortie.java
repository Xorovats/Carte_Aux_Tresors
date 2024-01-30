package main.classes;

import main.classes.Aventurier;
import main.classes.Carte;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureFichierSortie {

    public static void ecrireFichierSortie(String cheminFichier, Carte carte, List<Montagne> montagnes, List<Aventurier> aventuriers, List<Tresor> tresors){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))){

            //CARTE
            writer.write("C - " + carte.getLargeur() + " - " + carte.getHauteur());
            writer.newLine();

            //MONTAGNE
            for (Montagne montagne: montagnes){
                writer.write("M - " + montagne.getX() + " - " + montagne.getY());
                writer.newLine();
            }

            //TRESORS
            for (Tresor tresor: tresors){
                if(tresor.getNombreTresors() > 0) {
                    writer.write("T - " + tresor.getX() + " - " + tresor.getY() + " - " + tresor.getNombreTresors());
                    writer.newLine();
                }
            }

            //AVENTURIER
            for (Aventurier aventurier : aventuriers){
                writer.write("A - " + aventurier.getNom() + " - " + aventurier.getX() + " - " + aventurier.getY() + " - " + aventurier.getOrientation().toString() + " - " + aventurier.getTresorsRecuperer() );
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
