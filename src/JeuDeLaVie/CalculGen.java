package JeuDeLaVie;

import com.sun.org.apache.xpath.internal.SourceTree;

public class CalculGen {

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

    public static Liste listeVoisins(Liste<Coordonnee> l , int monde , int plin  , int pcol , int glin , int gcol) {

        Liste<Coordonnee> v = new Liste<Coordonnee>(); // Liste de voisins a retourner
        Maillon<Coordonnee> tmp = l.getTete(); // Maillon servant a parcourir la liste l

        while (tmp != null) {
            int ligne = tmp.getInfo().getLigne();
            int colonne = tmp.getInfo().getColonne();

            int[][] t =remplir_tabs(monde , ligne , colonne , plin , pcol , glin , gcol);

            for (int i : t[0]) {
                for (int j : t[1]) {
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

    public static Liste<Coordonnee> simulation(int d, Liste<Coordonnee> l,int monde){
        int compteur = 0;
        Liste<Coordonnee> v;
        int plin=JeuDeLaVie.minLigne(l);
        System.out.println(plin);
        int glin=JeuDeLaVie.maxLigne(l);
        System.out.println(glin);
        int pcol=JeuDeLaVie.minColonne(l);
        System.out.println(pcol);
        int gcol=JeuDeLaVie.maxColonne(l);
        System.out.println(gcol);
        while (compteur != d){
            System.out.println(l.toString());
            System.out.println("\n Génération n° "+compteur);
            compteur++;
            v = listeVoisins(l,monde,plin,pcol,glin,gcol);
            System.out.println("###################");
            l = genSuivante(v, l);
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