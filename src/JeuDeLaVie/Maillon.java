package JeuDeLaVie;

/**
 *
 */
public class Maillon<T> {

    // Attributs
    private T info;
    private Maillon suivant;

    // Constructeur

    /** Constructeur du maillon
     *
     * @param suivant : Maillon suivant
     * @param info : Coordonnee
     */
    public Maillon(T info, Maillon<T> suivant) {
        this.suivant = suivant;
        this.info = info;
    }

    /** Constructeur vide du maillon
     *
     */
    public Maillon() {
        this.suivant = null;
        this.info = null;
    }

    // Methodes

    /** Retourne le maillon suivant
     *
     * @return maillon.suivant : Champ suivant du maillon
     */
    public Maillon<T> getSuivant() {
        return suivant;
    }

    /** Affecte le maillon suivant au champ suivant
     *
     * @param suivant : Maillon suivant
     */
    public void setSuivant(Maillon suivant) {
        this.suivant = suivant;
    }

    /** Retourne l'objet du champ info
     *
     * @return objet du champ info
     */
    public T getInfo() {
        return info;
    }

    /** Affecte un objet au champ info
     *
     * @param info : Objet à affecter
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Retourne vrai si les maillons sont les meme, faux sinon
     *
     * @param o : maillon a comparer
     * @return : retourne un booleen
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Coordonnee)) return false;
        Maillon m = (Maillon)o;
        return (this.getInfo().equals(m.getInfo()));
    }

    /**
     * Retourne les coordonnees contenues dans le maillon sous forme de chaine de caracteres
     *
     * @return : Chaine de caractere contenant les coordonnees
     */
    @Override
    public String toString() {
        return "" + this.getInfo();
    }
}
