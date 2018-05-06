package JeuDeLaVie;

public class Coordonnee implements Comparable<Coordonnee> {

    // Attributs
    private int ligne;
    private int colonne;
    private int voisin;

    /**
     * Fonction qui retourne le nombre de voisins d'une cellule
     *
     * @return : Le nombre de voisins d'une cellule
     */
    public int getVoisin() {
        return voisin;
    }

    /**
     * Fonction qui permet de définir le nombre de voisins d'une cellule
     *
     * @param voisin : Le nombre de voisins d'une cellule
     */
    public void setVoisin(int voisin) {
        this.voisin = voisin;
    }

    // Constructeur

    /**
     * Constructeur des coordonnees
     *
     * @param ligne : ligne de la coordonnee
     * @param colonne : colonne de la coordonnee
     * @param voisin : voisin de la coordonnee
     */
    public Coordonnee(int ligne, int colonne, int voisin) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.voisin = voisin;
    }

    // Methodes

    /**
     * Fonction qui permet de récupérer la ligne d'une coordonnée
     *
     * @return : Le numero de ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Fonction qui permet d'attribuer une ligne à une coordonnée
     *
     * @param ligne : la ligne de la coordonnée
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * Fonction qui permet de récupérer la colonne d'une coordonnée
     *
     * @return : Le numero de colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Fonction qui permet d'attribuer une colonne à une coordonnée
     *
     * @param colonne : la colonne de la coordonnée
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
        return "[" + this.ligne +
                "," + this.colonne + "]" + "\nNombre de voisins : " + this.voisin;
    }
}