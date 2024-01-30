package main.test;

import main.classes.Carte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    @Test
    void testCreationCarte(){
        Carte carte = new Carte(3,4);
        assertEquals(3, carte.getLargeur());
        assertEquals(4,carte.getHauteur());
    }

}