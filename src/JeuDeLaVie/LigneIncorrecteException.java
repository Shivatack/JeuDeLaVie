package JeuDeLaVie;

public class LigneIncorrecteException extends Exception {

    /**
     * Fonction qui lève l'exception LigneIncorrecteException
     *
     * @param string : Erreur à afficher
     */
    public LigneIncorrecteException(String string) {
        super(string);
    }

    private static final long serialVersionUID = -1644322302405228699L;
}
