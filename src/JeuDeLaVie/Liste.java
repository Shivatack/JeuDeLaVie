package JeuDeLaVie;

import java.nio.file.Path;

/**
 *
 */
public class Liste<T> {

    // Attributs
    private Maillon<T> tete;

    // Constructeur

    /**
     * Initialise la liste a null
     */
    public Liste() {
        this.tete = null;
    }

    /**
     * Initialise la liste avec un maillon
     *
     * @param tete : maillon de tete
     */
    public Liste(Maillon<T> tete) {
        this.tete = tete;
    }

    // Methodes

    /**
     * Retourne le premier maillon de la liste
     *
     * @return : tete de la liste
     */
    public Maillon<T> getTete() {
        return tete;
    }

    /**
     * Affecte une nouvelle tete de liste a la liste avec le maillon passe en parametre
     *
     * @param tete : nouvelle tete de liste
     */
    public void setTete(Maillon<T> tete) {
        this.tete = tete;
    }

    // Ayoub
    /**
     * Ajoute un maillon en debut de liste
     *
     * @param m
     */
    public void ajouterEnTete(Maillon<T> m) {
        m.setSuivant(this.getTete());
        this.tete = m;
    }

    // Ozgur

    /**
     * Retourne vrai si la liste est vide faut sinon
     *
     * @return boolean
     */
    public boolean estVide(){
        return this.tete == null;
    }


    //Uzay

    /**
     * Retourne un entier correspondant a la taille de la liste
     *
     * @return : la taille de la liste
     */
    public int taille() {
        Maillon<T> tmp = this.tete;
        int taille = 0;
        while (tmp != null) {
            taille++;
            tmp = tmp.getSuivant();
        }
        return taille;
    }




    public boolean ajouterMaillon(Maillon<T> m) {
        if(m.getInfo() instanceof Coordonnee) {
            if(this.estVide()) {
                this.ajouterEnTete(m);
                return true;
            } else {
                Maillon<Coordonnee> tmp = (Maillon<Coordonnee>) this.getTete();
                Maillon<Coordonnee> mc = (Maillon<Coordonnee>) m;

                if (!this.contient(m) && mc.getInfo().compareTo(tmp.getInfo()) == -1) {
                    this.ajouterEnTete(m);
                    return true;
                } else {
                    if (tmp.getSuivant() != null) {
                        while ((tmp.getSuivant() != null) && mc.getInfo().compareTo(tmp.getSuivant().getInfo()) == 1) {
                            tmp = tmp.getSuivant();
                        }
                    }
                    if (!this.contient(m)) {
                        mc.setSuivant(tmp.getSuivant());
                        tmp.setSuivant(mc);
                        return true;
                    }
                }
            }
        } else {
            this.ajouterEnTete(m);
            return true;
        }
        return false;
    }

    public boolean contient(Maillon<T> m) {
        if(this.estVide()) return false;
            Maillon<T> tmp = this.getTete();
            while (tmp != null) {
                if (tmp.getInfo().equals(m.getInfo())) {
                    return true;
                }
                tmp = tmp.getSuivant();
            }
        return false;
    }

    //Ozgur
    /**
     * Retourne la liste sous forme de chaine de caracteres
     *
     * @return : Une chaine de caractere contenant la liste entiere
     */
    @Override
    public String toString() {
        String s = "";
        if (!this.estVide()) {
            Maillon<Coordonnee> tmp = (Maillon<Coordonnee>) this.getTete();

            //affichage des coordonnees
            /*while (tmp != null) {
                s += "(" + tmp.getInfo().getLigne() + ";" + tmp.getInfo().getColonne() + ")\n";
                tmp = tmp.getSuivant();
            }*/

            tmp = (Maillon<Coordonnee>) this.getTete();
            int minL = JeuDeLaVie.minLigne(this);
            int minC = JeuDeLaVie.minColonne(this);
            int maxL = JeuDeLaVie.maxLigne(this);
            int maxC = JeuDeLaVie.maxColonne(this);
            for (int l = minL; l <= maxL; l++){
                for (int c = minC; c <= maxC; c++){
                    if (tmp != null) {
                        if (tmp.getInfo().getLigne() == l && tmp.getInfo().getColonne() == c) {
                            s += "*";
                            tmp = tmp.getSuivant();
                        }else{
                            s += ".";
                        }
                    } else {
                        s += ".";
                    }
                }
                s += "\n";
            }
        }
        return s;
    }

    public void afficherListeLp(Liste lp) {
        Maillon<Path> m = lp.getTete();
        while (m.getSuivant() != null) {
            System.out.print(m.toString() + "\n");
            m = m.getSuivant();
        }
        System.out.print(m.toString() + "\n");
    }

    public void afficherListe(Liste<Coordonnee> l) {
        Maillon<Coordonnee> m = l.getTete();
        while (m.getSuivant() != null) {
            System.out.print(m.toString() + "\n");
            m = m.getSuivant();
        }
        System.out.print(m.toString() + "\n");
    }

    // Juliette

    /**
     * Supprime un maillon de la liste
     *
     * @param m : maillon a supprimer
     */
    public void supprimerMaillon(Maillon<T> m){
        if (tete.equals(m)) {
            tete = tete.getSuivant();
        } else {
            Maillon <T>tmp = tete;
            while (!(tmp.getSuivant() == null)) {
                if (tmp.getSuivant().equals(m)) {
                    tmp.setSuivant(tmp.getSuivant().getSuivant());
                    break;
                } else tmp = tmp.getSuivant();
            }
        }
    }
}