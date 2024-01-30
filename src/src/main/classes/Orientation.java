package main.classes;

public enum Orientation {
    NORD, EST, SUD, OUEST;


    @Override
    public String toString() {
        switch (this) {

            case NORD:
                return "N";
            case SUD:
                return "S";
            case OUEST:
                return "O";
            case EST:
                return "E";
            default:
                return null;

        }
    }
}
