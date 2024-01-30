package main.test;

import main.classes.Aventurier;
import main.classes.Carte;
import main.classes.Orientation;
import main.classes.Tresor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AventurierTest {

    @Test
    void testRamasserTresor(){
        Carte carte = new Carte(5,5);
        Tresor tresor = new Tresor(1,2, 2);
        Aventurier aventurier = new Aventurier("David", 1,1, Orientation.EST,"A");
        carte.setCase(tresor);
        aventurier.effectuerActionDeplacement(tresor);

        assertEquals(1,aventurier.getTresorsRecuperer());
        assertEquals(1,tresor.getNombreTresors());
    }

    @Test
    void testDeplacementAventurier(){
        Carte carte = new Carte(5,5);
        Aventurier aventurier = new Aventurier("David", 1,1, Orientation.EST,"AAA");

        int[] deplacement = aventurier.prochainDeplacement();
        aventurier.effectuerActionDeplacement(carte.getCase(deplacement[0],deplacement[1] ));

        assertEquals(2,aventurier.getX());
        assertEquals(1,aventurier.getY());

        deplacement = aventurier.prochainDeplacement();
        aventurier.effectuerActionDeplacement(carte.getCase(deplacement[0],deplacement[1] ));

        assertEquals(3,aventurier.getX());
        assertEquals(1,aventurier.getY());

    }

}