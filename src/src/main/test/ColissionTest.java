package main.test;

import main.classes.Aventurier;
import main.classes.Carte;
import main.classes.Montagne;
import main.classes.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColissionTest {

    @Test
    void testCollisionAventurierMontagne(){
        Carte carte = new Carte(5,5);
        Montagne montagne = new Montagne(2,2);
        carte.setCase(montagne);

        Aventurier aventurier = new Aventurier("David", 1,2 , Orientation.EST , "A");
        assertFalse(aventurier.deplacementValide(montagne));
        assertFalse(aventurier.deplacementValide(carte.getCase(2,2)));
        assertTrue(aventurier.deplacementValide(carte.getCase(1,1)));

    }

}
