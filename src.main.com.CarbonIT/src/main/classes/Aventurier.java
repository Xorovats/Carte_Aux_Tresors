package main.classes;

public class Aventurier extends Personnage {

    private String nom;
    private int tresorsRecuperer;

    public Aventurier(String nom, int x, int y, Orientation orientation, String sequenceMouvements) {
        super(x,y,orientation,sequenceMouvements);
        this.nom = nom;
        this.tresorsRecuperer = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getTresorsRecuperer() {
        return tresorsRecuperer;
    }

    public void ramasserTresor(Case caseActuelle){
        if(((Tresor) caseActuelle).getNombreTresors() > 0){
            tresorsRecuperer++;
            ((Tresor) caseActuelle).retirerUnTresor();
        }
    }

    @Override
    public void effectuerActionDeplacement(Case prochaineCase) {
        char action = prochaineAction();
        switch(action) {
            case 'A':
                if (deplacementValide(prochaineCase)) {
                    int[] coordonnee = prochainDeplacement();
                    avancer(coordonnee[0],coordonnee[1]);
                    if(prochaineCase instanceof Tresor)
                        ramasserTresor(prochaineCase);
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

    @Override
    public boolean deplacementValide(Case prochaineCase) {
        if (prochaineCase instanceof Montagne){
            return false;
        }
        return true;
    }
}

