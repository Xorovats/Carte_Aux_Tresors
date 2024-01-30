package main.test;

import main.classes.LecteurFichierEntree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecteurFichierEntreeTest {

    @Test
    void testLectureFichierEntree(){
        String cheminFichier = "../FileInput/input.txt";

        List<String> ligneAttendues = new ArrayList<>();
        ligneAttendues.add("C - 3 - 4");
        ligneAttendues.add("M - 1 - 0");
        ligneAttendues.add("M - 2 - 1");
        ligneAttendues.add("T - 0 - 3 - 2");
        ligneAttendues.add("T - 1 - 3 - 3");
        ligneAttendues.add("A - Lara - 1 - 1 - S - AADADAGGA");

        List<String> ligneLues = LecteurFichierEntree.lireFichier(cheminFichier);
        assertEquals(ligneAttendues,ligneLues);

    }

}