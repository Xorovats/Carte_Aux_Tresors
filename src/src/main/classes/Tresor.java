package main.classes;

import main.classes.Case;

public class Tresor extends Case {
    private int nombreTresors;
    public Tresor(int x, int y, int nombreTresors) {
        super(x, y);
        this.nombreTresors = nombreTresors;
    }

    public int getNombreTresors() {
        return this.nombreTresors;
    }

    public void retirerUnTresor(){
        this.nombreTresors--;
    }

}
