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

    /*public static Liste<Coordonnee> genSuivante(Liste t) {

        Liste newListe = new Liste();
        int newMinLigne = minLigne(t) - 1;
        int newMaxLigne = maxLigne(t) + 1;
        int newMinColonne = minColonne(t) - 1;
        int newMaxColonne = maxColonne(t) + 1;
        for (int i = newMinLigne; i <= newMaxLigne; i++) {
            for (int j = newMinColonne; j <= newMaxColonne; j++) {
                Coordonnee c = new Coordonnee(i, j);
                Maillon<Coordonnee> m = new Maillon<Coordonnee>(c, null);
                if (t.contient(m)) {
                    if ((nbVoisins(m, t) == 2) || (nbVoisins(m, t) == 3)) {
                        newListe.ajouterMaillon(m);
                    }
                } else {
                    if (nbVoisins(m, t) == 3) {
                        newListe.ajouterMaillon(m);
                    }
                }
            }
        }
        return newListe;
    }

    public static int nbVoisins(Maillon<Coordonnee> tmplc, Liste t) {

        int l = tmplc.getInfo().getLigne();
        int c = tmplc.getInfo().getColonne();
        int nombreVoisins = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Maillon<Coordonnee> mc = new Maillon<Coordonnee>(new Coordonnee(l + i, c + j), null);
                if (t.contient(mc) && mc.getInfo().compareTo(tmplc.getInfo()) != 0) {
                    nombreVoisins++;
                }
            }
        }

        return nombreVoisins;
    }*/
}