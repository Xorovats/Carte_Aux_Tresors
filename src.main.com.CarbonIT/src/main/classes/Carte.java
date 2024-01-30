package main.classes;

import java.util.Arrays;

public class Carte {

    private Case[][] tableauCases;
    private int largeur;
    private int hauteur;
    public Carte(int largeur, int hauteur) {
        this.tableauCases = new Case[largeur][hauteur];
        this.largeur = largeur;
        this.hauteur = hauteur;
        initialiserCases();
    }

    public Case getCase(int x, int y){
        if (this.tableauCases.length > x && this.tableauCases[0].length > y){
            return this.tableauCases[x][y];
        }
        return null;
    }

    public void setCase(Case nouvelleCase){
        if(this.tableauCases.length > nouvelleCase.getX() && this.tableauCases[0].length > nouvelleCase.getY())
            this.tableauCases[nouvelleCase.getX()][nouvelleCase.getY()] = nouvelleCase;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    private void initialiserCases(){
        for (int i = 0; i < tableauCases.length; i++) {
            for (int j = 0; j < tableauCases[i].length; j++) {
                tableauCases[i][j] = new Plaine(i,j);
            }
        }
    }

}
