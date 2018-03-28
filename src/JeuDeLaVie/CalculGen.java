package JeuDeLaVie;

public class CalculGen {

//Ayoub et Juliette

    /**
     * Fonction qui exécute une simulation du jeu d’une durée d affichant les configurations du jeu avec le numéro de génération.
     *
     * @param d : nombre de generations
     * @param l : liste fournie par la lecture du .lif
     */
    public static Liste<Coordonnee> simulation(int d, Liste<Coordonnee> l){
        int compteur = 0;
        //d++;
        Liste<Coordonnee> v;
        while (compteur != d){
            System.out.println(l.toString());
            System.out.println("\n Génération n° "+compteur);
            compteur++;
            v = listeVoisins(l);
            l = genSuivante(v, l);
        }
        return l;
    }

    // Juliette et Ayoub

    /**
     * Fonction qui prend une liste de cellules vivantes en paramètre et qui renvoie une liste de cases avec leur nombre de cellules vivantes adjacentes
     *
     * @param : liste l de cellules
     * @return : liste v de nombre de voisins
     */
    public static Liste listeVoisins(Liste<Coordonnee> l ) { // recoit la liste l qui contient les cellules vivantes de la génération actuelle

        Liste<Coordonnee> v = new Liste<Coordonnee>(); // Liste de voisins a retourner
        Maillon<Coordonnee> tmp = l.getTete(); // Maillon servant a parcourir la liste l

        while (tmp != null) {
            int ligne = tmp.getInfo().getLigne();
            int colonne = tmp.getInfo().getColonne();

            for (int i = ligne - 1; i <= ligne + 1; i++) {
                for (int j = colonne - 1; j <= colonne + 1; j++) {
                    Coordonnee c = new Coordonnee(i, j, 1);
                    Maillon<Coordonnee> m = new Maillon<Coordonnee>(c, null);
                    if (!tmp.getInfo().equals(m.getInfo())) { //bien
                        if (v.contient(m)) {
                            ajouterUnVoisin(v, m);
                        } else {
                            v.ajouterMaillon(m);
                        }
                    }
                }
            }
            tmp = tmp.getSuivant();
        }

        return v;
    }

    // Juliette et Ayoub

    /**
     * Fonction qui incrémente le champ voisin de m dans l
     *
     * @param : liste l
     * @param : Maillon m
     */
    public static void ajouterUnVoisin(Liste<Coordonnee> v, Maillon<Coordonnee> m) {
        Maillon<Coordonnee> tmp = v.getTete();
        while (tmp != null) {
            if (tmp.getInfo().equals(m.getInfo())) {
                tmp.getInfo().setVoisin(tmp.getInfo().getVoisin() + 1);
            }
            tmp = tmp.getSuivant();
        }
    }

    // Juliette et Ayoub

    /**
     * Fonction qui prend une liste de voisins en paramètre et qui supprime ceux qui ne prendront pas vie
     *
     * @param : liste l
     * @return : liste v de cellules vivantes
     */
    public static Liste genSuivante(Liste<Coordonnee> v, Liste<Coordonnee> l) {
        Maillon<Coordonnee> tmp = v.getTete();
        while (tmp != null) {
            if (tmp.getInfo().getVoisin() != 3) {
                if (tmp.getInfo().getVoisin() != 2) {
                    v.supprimerMaillon(tmp);
                } else {
                    if (!l.contient(tmp)) {
                        v.supprimerMaillon(tmp);
                    }
                }
            }
            tmp = tmp.getSuivant();
        }
        return v;
    }
}