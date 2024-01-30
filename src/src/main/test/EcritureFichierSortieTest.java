package main.test;

import main.classes.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EcritureFichierSortieTest {

    @Test
    void testEcrireFichierSortie() throws IOException {
        Carte carte = new Carte(3,4);
        List<Montagne> listMongtagne = new ArrayList<>();
        List<Tresor> listTresors = new ArrayList<>();
        List<Aventurier> listAventuriers = new ArrayList<>();

        listMongtagne.add(new Montagne(2,1));
        listMongtagne.add(new Montagne(1,0));

        listTresors.add(new Tresor(1,3,1));

        listAventuriers.add(new Aventurier("Lara",0,3, Orientation.SUD,"AADADAGGA"));

        EcritureFichierSortie.ecrireFichierSortie("../FileOutput/output.txt",carte,listMongtagne,listAventuriers,listTresors);

        List<String> lignes = Files.readAllLines(Path.of("../FileOutput/output.txt"));
        assertEquals("C - 3 - 4", lignes.get(0));
        assertTrue(lignes.contains("M - 1 - 0"));
        assertTrue(lignes.contains("M - 2 - 1"));
        assertTrue(lignes.contains("T - 1 - 3 - 1"));
        assertTrue(lignes.contains("A - Lara - 0 - 3 - S - 0"));



    }

}