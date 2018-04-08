package JeuDeLaVie;

public class JeuDeLaVie {

    //Uzay

    /**
     * @return : la plus petite colonne
     */
    public static int minColonne(Liste t) {
        if (t.estVide()) {
            return 0;
        } else {
            int c;
            Maillon<Coordonnee> m = t.getTete();
            c = ((Coordonnee) m.getInfo()).getColonne();
            m = m.getSuivant();
            while (m != null) {
                if (((Coordonnee) m.getInfo()).getColonne() < c) {
                    c = ((Coordonnee) m.getInfo()).getColonne();
                }
                m = m.getSuivant();
            }
            return c;
        }
    }

    //Uzay

    /**
     * @return : la plus petite ligne
     */
    public static int minLigne(Liste t) {
        if (t.estVide()) {
            return 0;
        } else {
            return ((Coordonnee) t.getTete().getInfo()).getLigne();
        }
    }

    //Uzay

    /**
     * @return : la plus grande colonne
     */
    public static int maxColonne(Liste t) {
        if (t.estVide()) {
            return 0;
        } else {
            int c;
            Maillon<Coordonnee> m = t.getTete();
            c = ((Coordonnee) m.getInfo()).getColonne();
            m = m.getSuivant();
            while (m != null) {
                if (((Coordonnee) m.getInfo()).getColonne() > c) {
                    c = ((Coordonnee) m.getInfo()).getColonne();
                }
                m = m.getSuivant();
            }
            return c;
        }
    }

    //Uzay

    /**
     * @return : la plus grande ligne
     */
    public static int maxLigne(Liste t) {
        if (t.estVide()) {
            return 0;
        } else {
            Maillon<Coordonnee> tmp = t.getTete();
            while (tmp.getSuivant() != null) {
                tmp = tmp.getSuivant();
            }
            return ((Coordonnee) tmp.getInfo()).getLigne();
        }
    }


}