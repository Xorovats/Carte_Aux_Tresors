package main.classes;

import main.classes.Case;
import main.classes.Orientation;

public abstract class Personnage {
    private int x;
    private int y;
    private Orientation orientation;
    private String sequenceMouvements;
    //Compteur des actions dans la sequence de mouvement
    protected int compteurAction;
    private int totalActions;
    public Personnage(int x, int y, Orientation orientation, String sequenceMouvements) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.sequenceMouvements = sequenceMouvements;
        this.compteurAction = 0;
        this.totalActions = sequenceMouvements.length();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public boolean aTerminerLaSequence(){
        if (this.compteurAction == this.totalActions)
            return true;
        return false;
    }
    //Methode pour avancer
    protected void avancer(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Methode pour tourner a Gauche
    protected void tournerAGauche(){
        rotation(-1);
    }

    //Methode pour tourner a Drotie
    protected void tournerADroite(){
        rotation(1);
    }
/*
    Methode Rotation
    @Param sens: Sens de rotation -1 pour tourner a gauche, 1 pour tourner a droite
    En fonction du sens on prends l'orientation precedente ou suivante dans l'enum main.classes.Orientation qui est dans l'odre suivant : NORD, EST, SUD, OUEST
 */
    private void rotation(int sens){
        int nouvelleOrientation = (orientation.ordinal() + sens + Orientation.values().length) % Orientation.values().length;
        this.orientation = Orientation.values()[nouvelleOrientation];
    }

    public int[] prochainDeplacement(){
        int x = this.x ,y = this.y;
        char action = prochaineAction();
        if(!aTerminerLaSequence()) {
            if(action == 'A') {
                switch (this.orientation) {

                    case NORD:
                        y--;
                        break;
                    case SUD:
                        y++;
                        break;

                    case EST:
                        x++;
                        break;
                    case OUEST:
                        x--;
                        break;

                }
            }
        }
        return new int[] {x,y};
    }
    public char prochaineAction(){
        if (compteurAction <= this.totalActions) {
            char action = this.sequenceMouvements.charAt(compteurAction);
            return this.sequenceMouvements.charAt(compteurAction);
        }
        return '0';
    }
    // Effectuer les differentes actions de la sequences
    public void effectuerActionDeplacement(Case prochaineCase){
        char action = prochaineAction();
        switch(action) {
            case 'A':
                if (deplacementValide(prochaineCase)) {
                    int[] coordonnee = prochainDeplacement();
                    avancer(coordonnee[0],coordonnee[1]);
                }
                break;
            case 'G':
                tournerAGauche();
                break;
            case 'D':
                tournerADroite();
                break;
            default:
                System.out.println("Deplacement Invalide");
                break;
        }
    }

    public void finirTourPersonnage(){
        compteurAction++;
    }

    public abstract boolean deplacementValide(Case prochaineCase);

}
