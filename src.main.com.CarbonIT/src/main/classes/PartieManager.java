package main.classes;

import main.classes.*;

import java.util.*;

public class PartieManager {

    private List<Aventurier> aventuriers;
    private List<Tresor> tresors;
    private List<Montagne> montagnes;
    private Carte carte;
    private int[][] positionAventuriers;
/*    public main.classes.PartieManager(List<main.classes.Aventurier> aventuriers, List<main.classes.Tresor> tresors, List<main.classes.Montagne> montagnes, main.classes.Carte carte) {
        this.aventuriers = aventuriers;
        this.tresors = tresors;
        this.montagnes = montagnes;
        this.carte = carte;
    }
*/
    public PartieManager(){
        this.aventuriers = new ArrayList<>();
        this.montagnes = new ArrayList<>();
        this.tresors = new ArrayList<>();
        initialiserPartie();
        demarrerPartie();
    }
    public void demarrerPartie(){


        while(!testFinSequenceAventurier()){
            for (int i = 0; i < aventuriers.size(); i++){
                Aventurier aventurier = this.aventuriers.get(i);
                if(!aventurier.aTerminerLaSequence()) {

                    int[] prochaineDeplacement = aventurier.prochainDeplacement();

                    if(prochaineDeplacement[0] <= (carte.getLargeur() - 1) && prochaineDeplacement[1] <= (carte.getHauteur() - 1) ){
                        if((aventurier.getX() == prochaineDeplacement[0] && aventurier.getY() == prochaineDeplacement[1]) || !testerSiAventurierSurCase(prochaineDeplacement[0], prochaineDeplacement[1])) {
                            if (aventurier.deplacementValide(carte.getCase(prochaineDeplacement[0], prochaineDeplacement[1]))) {
                                positionAventuriers[aventurier.getX()][aventurier.getY()] = 0;
                                aventurier.effectuerActionDeplacement(carte.getCase(prochaineDeplacement[0], prochaineDeplacement[1]));
                                positionAventuriers[aventurier.getX()][aventurier.getY()] = 1;
                            }
                        }
                    }
                        aventurier.finirTourPersonnage();

                }
            }
        }
        terminerPartie();
    }


    private void initialiserPartie(){

        List<Aventurier> aventuriersList = new ArrayList<>();
        List<Tresor> tresorsList = new ArrayList<>();
        List<Montagne> montagnesList = new ArrayList<>();

        List<String> lignesFichierEntree = LecteurFichierEntree.lireFichier("FileInput/input.txt");
        for (int i = 0; i < lignesFichierEntree.size(); i++) {
            String ligne = lignesFichierEntree.get(i);
            if(ligne != null && !ligne.isEmpty()){
                ligne = ligne.trim();
                switch (ligne.charAt(0)){
                    case 'A': //AVENTURIER
                        String[] informationAventurier = ligne.split("-");

                        String nomAventurier = "", senquenceMouvement = "";
                        int axeHorizontal = -1, axeVertical = -1;
                        Orientation orientation = null;

                        if(informationAventurier.length >= 6){

                            if(!informationAventurier[1].isEmpty())
                                nomAventurier = informationAventurier[1].trim();
                            if (!informationAventurier[2].isEmpty())
                                axeHorizontal = Integer.parseInt(informationAventurier[2].trim());
                            if (!informationAventurier[3].isEmpty())
                                axeVertical = Integer.parseInt(informationAventurier[3].trim());
                            if (!informationAventurier[4].isEmpty()) {
                                char lettreOrientation = informationAventurier[4].trim().charAt(0);
                                switch (lettreOrientation){
                                    case 'N':
                                        orientation = Orientation.valueOf("NORD");
                                        break;
                                    case 'S':
                                        orientation = Orientation.valueOf("SUD");
                                        break;
                                    case 'E':
                                        orientation = Orientation.valueOf("EST");
                                        break;
                                    case 'O':
                                        orientation = Orientation.valueOf("OUEST");
                                        break;
                                }
                            }
                            if (!informationAventurier[5].isEmpty())
                                senquenceMouvement = informationAventurier[5].trim();

                            if ( !nomAventurier.isEmpty() || !senquenceMouvement.isEmpty() || axeHorizontal >= 0 || axeVertical >= 0 || orientation != null)
                                aventuriersList.add(new Aventurier(nomAventurier,axeHorizontal,axeVertical,orientation,senquenceMouvement));
                        }
                        else {
                            //CAS DERREUR
                        }
                        break;
                    case 'C': //CARTE
                        String[] informationCarte = ligne.split("-");

                        int largeurCarte = -1, hauteurCarte = -1;

                        if(informationCarte.length >= 3){

                            if(!informationCarte[1].isEmpty())
                                largeurCarte = Integer.parseInt(informationCarte[1].trim());
                            if(!informationCarte[2].isEmpty())
                                hauteurCarte = Integer.parseInt(informationCarte[2].trim());

                            if(largeurCarte >= 0 && hauteurCarte >= 0) {
                                this.carte = new Carte(largeurCarte, hauteurCarte);
                                this.positionAventuriers = new int[largeurCarte][hauteurCarte];
                            }
                        }
                        break;
                    case 'T': //TRESOR
                        String[] informationTresors = ligne.split("-");

                        int nombreTresors = -1;
                        axeHorizontal = -1;
                        axeVertical = -1;

                        if(informationTresors.length <= 4){

                            if(!informationTresors[1].isEmpty())
                                axeHorizontal = Integer.parseInt(informationTresors[1].trim());
                            if(!informationTresors[2].isEmpty())
                                axeVertical = Integer.parseInt(informationTresors[2].trim());
                            if(!informationTresors[3].isEmpty())
                                nombreTresors = Integer.parseInt(informationTresors[3].trim());

                            if(axeHorizontal >= 0 && axeVertical >= 0 && nombreTresors >= 0)
                                tresorsList.add( new Tresor(axeHorizontal, axeVertical, nombreTresors));
                        }
                        else { //CAS DERREUR
                        }
                        break;
                    case 'M': //MONTAGNE
                        String[] informationMontagne = ligne.split("-");

                        axeHorizontal = -1;
                        axeVertical = -1;

                        if(informationMontagne.length >= 3){

                            if(!informationMontagne[1].isEmpty())
                                axeHorizontal = Integer.parseInt(informationMontagne[1].trim());
                            if(!informationMontagne[2].isEmpty())
                                axeVertical = Integer.parseInt(informationMontagne[2].trim());

                            if(axeHorizontal >= 0 && axeVertical >= 0)
                                montagnesList.add(new Montagne(axeHorizontal, axeVertical));
                        }
                        else {
                            //CAS DERREUR
                        }
                        break;
                    default:
                        //Cas derreur
                        break;
                }
            }
        }
        //INITIALISATION DU TABLEAU AVENTURIER
        // SI = 1 ALORS aventurier SINON = 0
        if(this.positionAventuriers != null)
            for (int i = 0; i < positionAventuriers.length; i++)
                for (int j = 0; j < positionAventuriers[i].length; j++)
                    this.positionAventuriers[i][j] = 0;


        //AJOUT DES MONTAGNES
        for (int i = 0; i < montagnesList.size(); i++) {
            boolean conflitCase = false;
            for (int j = 0; j < montagnes.size(); j++) {
                if (montagnesList.get(i).getX() == montagnes.get(j).getX() && montagnesList.get(i).getY() == montagnes.get(j).getY())
                    conflitCase = true;

            }
            if (!conflitCase) {
                this.montagnes.add(montagnesList.get(i));
                this.carte.setCase(montagnesList.get(i));
            }

        }

        //AJOUT DES TRESORS
        for (int i = 0; i < tresorsList.size(); i++) {
            boolean conflitCase = false;
            for (int j = 0; j < this.tresors.size(); j++) {
                if(tresorsList.get(i).getX() == this.tresors.get(j).getX() && tresorsList.get(i).getY() == this.tresors.get(j).getY())
                    conflitCase = true;
            }
            if(!conflitCase) {
                this.tresors.add(tresorsList.get(i));
                this.carte.setCase(tresorsList.get(i));
            }
        }

        //AJOUT DES AVENTURIERS && POSITIONNEMENT DES AVENTURIER DANS LE TABLEAU
        for (int i = 0; i < aventuriersList.size(); i++) {
            boolean conflitCase = false;
            for (int j = 0; j < this.aventuriers.size(); j++) {
                if (aventuriersList.get(i).getX() == aventuriers.get(j).getX() && aventuriersList.get(i).getY() == aventuriers.get(j).getY())
                    conflitCase = true;
                for (Montagne montagne : this.montagnes)
                    if(aventuriersList.get(i).getX() == montagne.getX() && aventuriersList.get(i).getY() == montagne.getY())
                        conflitCase = true;
            }
            if(!conflitCase) {
                for(Tresor tresor : this.tresors)
                    if(tresor.getX() == aventuriersList.get(i).getX() && tresor.getY() == aventuriersList.get(i).getY())
                        aventuriersList.get(i).ramasserTresor(tresor);
                this.aventuriers.add(aventuriersList.get(i));
                this.positionAventuriers[aventuriersList.get(i).getX()][aventuriersList.get(i).getY()] = 1;
            }
        }

    }

    private void terminerPartie(){
        EcritureFichierSortie.ecrireFichierSortie("FileOutput/output.txt",carte,montagnes,aventuriers,this.tresors);
    }

    private boolean testFinSequenceAventurier(){
        if(this.aventuriers != null)
            for (int i = 0; i < aventuriers.size(); i++)
                if (!this.aventuriers.get(i).aTerminerLaSequence())
                    return false;
        return true;

    }

    private boolean testerSiAventurierSurCase(int x, int y){
        if(positionAventuriers[x][y] == 1)
            return true;
        return false;
    }


}
