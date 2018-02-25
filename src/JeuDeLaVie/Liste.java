package JeuDeLaVie;

/**
 *
 */
public class Liste{

    // Attributs
    private Maillon tete;

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
    public Liste(Maillon tete) {
        this.tete = tete;
    }

    // Methodes

    /**
     * Retourne le premier maillon de la liste
     *
     * @return : tete de la liste
     */
    public Maillon getTete() {
        return tete;
    }

    /**
     * Affecte une nouvelle tete de liste a la liste avec le maillon passe en parametre
     *
     * @param tete : nouvelle tete de liste
     */
    public void setTete(Maillon tete) {
        this.tete = tete;
    }

    // Ayoub
    /**
     * Ajoute un maillon en debut de liste
     *
     * @param m
     */
    public void ajouterEnTete(Maillon m) {
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
        Maillon tmp = this.tete;
        int taille = 0;
        while (tmp != null) {
            taille++;
            tmp = tmp.getSuivant();
        }
        return taille;
    }

    //JULIETTE ET OZGUR LA BONNE

    public void ajouterMaillon(Maillon m) {
        if(m.getInfo() instanceof Coordonnee) {
            if(this.estVide()) {
                this.ajouterEnTete(m);
            } else {
                Maillon tmp =this.getTete();

                if (m.getInfo().compareTo(tmp.getInfo()) == -1) {
                    this.ajouterEnTete(m);
                } else {
                    if (tmp.getSuivant() != null) {
                        while ((tmp.getSuivant() != null) && m.getInfo().compareTo(tmp.getSuivant().getInfo()) == 1) {
                            tmp = tmp.getSuivant();
                        }
                    }
                    /*if (tmp.getSuivant() != null && mc.getInfo().compareTo(tmp.getSuivant().getInfo()) != 0) {
                        mc.setSuivant(tmp.getSuivant());
                        tmp.setSuivant(mc);
                    }*/
                    m.setSuivant(tmp.getSuivant());
                    tmp.setSuivant(m);
                }
            }
        } else {
            this.ajouterEnTete(m);
        }
        this.supprimerDoublons();
    }

    public void supprimerDoublons() {
        Maillon m = this.getTete();
        Maillon suiv = m.getSuivant();

        while (suiv != null) {
            while (suiv != null && m.getInfo().compareTo(suiv.getInfo()) == 0) {
                m.setSuivant(suiv.getSuivant());
                suiv = suiv.getSuivant();
            }
            m = m.getSuivant();
            if (suiv != null) {
                suiv = suiv.getSuivant();
            }
        }
    }

    //Uzay

    /**
     *
     * @return : la plus petite colonne
     */
    public int minColonne(){
        if(estVide()){
            return 0;
        }else{
            int c;
            Maillon m=getTete();
            c=(m.getInfo()).getColonne();
            m=m.getSuivant();
            while (m!=null){
                if((m.getInfo()).getColonne()<c){
                    c=(m.getInfo()).getColonne();
                }
                m=m.getSuivant();
            }
            return c;
        }
    }

    //Uzay

    /**
     *
     * @return : la plus petite ligne
     */
    public int minLigne(){
        if(estVide()){
            return 0;
        }else{
            return (getTete().getInfo()).getLigne();
        }
    }

    //Uzay

    /**
     *
     * @return : la plus grande colonne
     */
    public int maxColonne(){
        if(estVide()){
            return 0;
        }else{
            int c;
            Maillon m=getTete();
            c=(m.getInfo()).getColonne();
            m=m.getSuivant();
            while (m!=null){
                if((m.getInfo()).getColonne()>c){
                    c=(m.getInfo()).getColonne();
                }
                m=m.getSuivant();
            }
            return c;
        }
    }

    //Uzay

    /**
     *
     * @return : la plus grande ligne
     */
    public int maxLigne(){
        if (estVide()){
            return 0;
        }else{
            Maillon tmp = getTete();
            while(tmp.getSuivant()!=null){
                tmp=tmp.getSuivant();
            }
            return (tmp.getInfo()).getLigne();
        }
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
            Maillon tmp = this.getTete();
            tmp = this.getTete();
            int minL = this.minLigne();
            int minC = this.minColonne();
            int maxL = this.maxLigne();
            int maxC = this.maxColonne();
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

    public void afficherListe(Liste l) {
        Maillon m = l.getTete();
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
    public void supprimerMaillon(Maillon m){
        if (tete.equals(m)) {
            tete = tete.getSuivant();
        } else {
            Maillon tmp = tete;
            while (!(tmp.getSuivant() == null)) {
                if (tmp.getSuivant().equals(m)) {
                    tmp.setSuivant(tmp.getSuivant().getSuivant());
                    break;
                } else tmp = tmp.getSuivant();
            }
        }
    }

    public boolean contient(Maillon m) {
        Maillon tmp =this.getTete();
        while (tmp != null) {
            if (tmp.getInfo().compareTo(m.getInfo()) == 0) {
                return true;
            } else {
                if (tmp.getInfo().compareTo(m.getInfo()) == 1) {
                    return false;
                }
            }
            tmp = tmp.getSuivant();
        }
        return false;
    }

    public Liste genSuivante() {
        Liste newListe = new Liste();
        int newMinLigne=minLigne()-1;
        int newMaxLigne=maxLigne()+1;
        int newMinColonne=minColonne()-1;
        int newMaxColonne=maxColonne()+1;
        for(int i=newMinLigne; i<=newMaxLigne; i++){
            for(int j=newMinColonne; j<=newMaxColonne; j++){
                Coordonnee c=new Coordonnee(i,j);
                Maillon m=new Maillon(c,null);
                if(contient(m)){
                    if ((nbVoisins(m)==2) || (nbVoisins(m) ==3)){
                        newListe.ajouterMaillon(m);
                    }
                }else{
                    if (nbVoisins(m)==3){
                        newListe.ajouterMaillon(m);
                    }
                }
            }
        }
        return newListe;
    }

    public int nbVoisins(Maillon tmplc) {

        int l = tmplc.getInfo().getLigne();
        int c = tmplc.getInfo().getColonne();
        int nombreVoisins = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Maillon mc = new Maillon(new Coordonnee(l+i, c+j), null);
                if (this.contient(mc) && mc.getInfo().compareTo(tmplc.getInfo()) != 0) {
                    nombreVoisins++;
                }
            }
        }

        return nombreVoisins;
    }
}