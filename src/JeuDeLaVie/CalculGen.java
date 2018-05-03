package JeuDeLaVie;

public class CalculGen {

    /**
     * Fonction qui ajoute un maillon a 1 voisin (s'il n'est pas une cellule vivante) ou a 10 voisins (s'il est une cellule vivante) dans la liste de voisin.
     * Si le maillon est déjà contenu dans la liste, on incrémente son champ voisin de 1 ou de 10.
     *
     * @param v : liste de voisins contenant les maillons adjacents aux cellules vivantes contenues dans la liste initiale
     * @param m : Maillon a ajouter ou auquel ajouter un voisin dans la liste v
     * @param tmp : Maillon qui parcourt la liste initiale de cellules vivantes
     */
    public static void ajouterVoisin(Liste<Coordonnee> v, Maillon<Coordonnee> m, Maillon<Coordonnee> tmp) {
        Maillon<Coordonnee> p = v.getTete();//maillon de parcours
        if (tmp.getInfo().compareTo(m.getInfo())==0) {
            m.getInfo().setVoisin(10); // tous les maillons qui ont 10 ou plus dans leur champs voisin sont des maillons qui sont des cellules vivantes initialement
        } else {
            m.getInfo().setVoisin(1);
        }

        if (!v.ajouterMaillon(m)) {
             while (p != null) {
                  if (p.getInfo().equals(m.getInfo())) {
                      p.getInfo().setVoisin(p.getInfo().getVoisin() + m.getInfo().getVoisin());
                  }
                  p = p.getSuivant();
             }
        }
    }

    /**
     * Cette fonction supprime de la liste de voisin toutes les cellules qui meurent ou qui ne prendront pas vie. Elle renvoie donc la generation suivante de cellules vivantes.
     *
     * @param v : liste de voisins contenant les maillons adjacents aux cellules vivantes contenues dans la liste initiale
     * @return la liste de voisins contenant uniquement les cellules qui restent en vie ou qui prennent vie
     */
    public static Liste genSuivante(Liste<Coordonnee> v) { //13 : 3 voisins et initialement en vie, 12: 2 voisins et initialement en vie, 3 : 3 voisins -> doit naitre
        Maillon<Coordonnee> tmp = v.getTete();
        while (tmp != null) {
            if (tmp.getInfo().getVoisin() != 3 && tmp.getInfo().getVoisin() != 13 && tmp.getInfo().getVoisin() != 12) {
                v.supprimerMaillon(tmp);
            }
            tmp = tmp.getSuivant();
        }
        return v;

    }


    /**
     * Fonction qui prend une liste de cellules vivantes en paramètre et qui renvoie une liste de cases avec leur nombre de cellules vivantes adjacentes
     *
     * @param : liste l de cellules
     * @return : liste v de nombre de voisins
     */
    public static Liste listeVoisins(Liste<Coordonnee> l , int monde , int plin  , int pcol , int glin , int gcol) { // recoit la liste l qui contient les cellules vivantes de la génération actuelle
        Liste<Coordonnee> v = new Liste<Coordonnee>(); // Liste de voisins a retourner
        Maillon<Coordonnee> tmp = l.getTete(); // Maillon servant a parcourir la liste l
        while (tmp != null) {
            int ligne = tmp.getInfo().getLigne();
            int colonne = tmp.getInfo().getColonne();

            int[][] t =remplir_tabs(monde , ligne , colonne , plin , pcol , glin , gcol);

            for (int i : t[0]) {
                for (int j : t[1]) { //on va bien jusque là
                    Coordonnee c = new Coordonnee(i, j, 1);
                    Maillon<Coordonnee> m = new Maillon<Coordonnee>(c, null);
                    ajouterVoisin(v, m, tmp);
                }
            }
            tmp = tmp.getSuivant();
        }
        return v;
    }

    /**
     *
     * @param d
     * @param l
     * @param monde
     * @return
     */
    public static Liste<Coordonnee> simulation(int d, Liste<Coordonnee> l, int monde){
        int compteur = 0;
        Liste<Coordonnee> v;
        int plin=JeuDeLaVie.minLigne(l);
        int glin=JeuDeLaVie.maxLigne(l);
        int pcol=JeuDeLaVie.minColonne(l);
        int gcol=JeuDeLaVie.maxColonne(l);
        while (compteur != d){
            compteur=compteur+1;
            v = listeVoisins(l,monde,plin-10,pcol-10,glin+10,gcol+10);
            l = genSuivante(v);

        }
        return l;
    }

    /*
    1->infini
    2->fini
    3->circulaire
     */
    public static int[][] remplir_tabs(int monde , int ligne , int colonne , int plin  , int pcol , int glin , int gcol){
        int [] t_l;
        int [] t_c;
        if(monde==1){
            t_l = new int[3];
            t_c = new int[3];
            t_l[0]=ligne-1;
            t_l[1]=ligne;
            t_l[2]=ligne+1;
            t_c[0]=colonne-1;
            t_c[1]=colonne;
            t_c[2]=colonne+1;
        }else if(monde==2){
            if (ligne == plin) {
                t_l = new int[2];
                t_l[0] = ligne + 1;
                t_l[1] = ligne;
            } else if (ligne == glin) {
                t_l = new int[2];
                t_l[0] = ligne - 1;
                t_l[1] = ligne;
            } else {
                t_l = new int[3];
                t_l[0] = ligne - 1;
                t_l[1] = ligne;
                t_l[2] = ligne + 1;
            }
            if (colonne == pcol) {
                t_c = new int[2];
                t_c[0] = colonne + 1;
                t_c[1] = colonne;
            } else if (colonne == gcol) {
                t_c = new int[2];
                t_c[0] = colonne - 1;
                t_c[1] = colonne;
            } else {
                t_c = new int[3];
                t_c[0] = colonne - 1;
                t_c[1] = colonne;
                t_c[2] = colonne + 1;
            }
        }else{
            t_l = new int[3];
            t_c = new int[3];
            if (ligne == plin) {
                t_l[0]=ligne + 1;
                t_l[1]=ligne;
                t_l[2]=glin;
            } else if (ligne == glin) {
                t_l[0]=ligne - 1;
                t_l[1]=ligne;
                t_l[2]=plin;
            } else {
                t_l[0]=ligne - 1;
                t_l[1]=ligne;
                t_l[2]=ligne + 1;
            }
            if (colonne == pcol) {
                t_c[0]=colonne + 1;
                t_c[1]=colonne;
                t_c[2]=gcol;
            } else if (colonne == gcol) {
                t_c[0]=colonne - 1;
                t_c[1]=colonne;
                t_c[2]=pcol;
            } else {
                t_c[0]=colonne-1;
                t_c[1]=colonne;
                t_c[2]=colonne+1;
            }
        }
        return new int[][] {t_l, t_c};
    }

}