package JeuDeLaVie;

/**
 *
 */
public class Coordonnee implements Comparable<Coordonnee> {

    // Attributs
    private int ligne;
    private int colonne;

    // Constructeur

    /**
     * Constructeur des coordonnees
     *
     * @param : ligne
     * @param : colonne
     */
    public Coordonnee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Methodes

    /**
     * @return : Retourne le numero de ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * modification de la ligne
     *
     * @param : ligne
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * @return : Retourne le numero de colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * @return : Retourne le numero de colonne
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Implémentation de la méthode compareTo
     *
     * @param c : coordonnee a comparer
     * @return : 1 si supérieur, 0 si égal et -1 si inférieur
     */
    public int compareTo(Coordonnee c) {
            if (this.ligne < c.ligne) return -1;
            if (this.ligne > c.ligne) return 1;
            if (this.ligne == c.ligne) {
                if (this.colonne < c.colonne) return -1;
                if (this.colonne > c.colonne) return 1;
                if (this.colonne == c.colonne) return 0;
            }
        return -2;
    }

    /**
     * Redefinition de la methode equals
     *
     * @param o : objet a comparer
     * @return : retourne true si les objets sont egaux ou faux sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordonnee)) return false;
        Coordonnee that = (Coordonnee) o;
        return getLigne() == that.getLigne() &&
                getColonne() == that.getColonne();
    }

    /**
     * Retourne les coordonnees x et y sous forme de chaine de caracteres
     *
     * @return : Chaine de caractere contenant les coordonnees
     */
    @Override
    public String toString() {
        return "["+ this.ligne +
                "," + this.colonne + "]";
    }

    public Coordonnee distanceCoordonnee(Coordonnee c){
        Coordonnee vecteur = new Coordonnee(this.ligne+c.getLigne(),this.colonne+c.getColonne());
        return vecteur;
    }
}