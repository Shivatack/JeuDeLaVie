package JeuDeLaVie;

/**
 *
 */
public class Liste<T>{

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

    //JULIETTE ET OZGUR LA BONNE

    public void ajouterMaillon(Maillon<T> m) {
        if(m.getInfo() instanceof Coordonnee) {
            if(this.estVide()) {
                this.ajouterEnTete(m);
            } else {
                Maillon<Coordonnee> tmp = (Maillon<Coordonnee>) this.getTete();
                Maillon<Coordonnee> mc = (Maillon<Coordonnee>) m;

                if (mc.getInfo().compareTo(tmp.getInfo()) == -1) {
                    this.ajouterEnTete(m);
                } else {
                    if (tmp.getSuivant() != null) {
                        while ((tmp.getSuivant() != null) && mc.getInfo().compareTo(tmp.getSuivant().getInfo()) == 1) {
                            tmp = tmp.getSuivant();
                        }
                    }
                    /*if (tmp.getSuivant() != null && mc.getInfo().compareTo(tmp.getSuivant().getInfo()) != 0) {
                        mc.setSuivant(tmp.getSuivant());
                        tmp.setSuivant(mc);
                    }*/
                    mc.setSuivant(tmp.getSuivant());
                    tmp.setSuivant(mc);
                }
            }
        } else {
            this.ajouterEnTete(m);
        }
        this.supprimerDoublons();
    }

    public void supprimerDoublons() {
        Maillon<Coordonnee> m = (Maillon<Coordonnee>) this.getTete();
        Maillon<Coordonnee> suiv = m.getSuivant();

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
            Maillon<T> m=getTete();
            c=((Coordonnee)m.getInfo()).getColonne();
            m=m.getSuivant();
            while (m!=null){
                if(((Coordonnee)m.getInfo()).getColonne()<c){
                    c=((Coordonnee)m.getInfo()).getColonne();
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
            return ((Coordonnee)getTete().getInfo()).getLigne();
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
            Maillon<T> m=getTete();
            c=((Coordonnee)m.getInfo()).getColonne();
            m=m.getSuivant();
            while (m!=null){
                if(((Coordonnee)m.getInfo()).getColonne()>c){
                    c=((Coordonnee)m.getInfo()).getColonne();
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
            Maillon<T> tmp = getTete();
            while(tmp.getSuivant()!=null){
                tmp=tmp.getSuivant();
            }
            return ((Coordonnee)tmp.getInfo()).getLigne();
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
            Maillon<Coordonnee> tmp = (Maillon<Coordonnee>) this.getTete();

            //affichage des coordonnees
            /*while (tmp != null) {
                s += "(" + tmp.getInfo().getLigne() + ";" + tmp.getInfo().getColonne() + ")\n";
                tmp = tmp.getSuivant();
            }*/

            tmp = (Maillon<Coordonnee>) this.getTete();
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


    //Juliette

    /**
     * Verifie la configuration vaisseau du jeu
     *
     * @param l
     * @return True si la configuration du jeu est vaisseau, False sinon
     */
    public boolean estVaisseau(Liste<Coordonnee> l){
        if(this.taille()==l.taille()){
            Maillon<Coordonnee> tmp1=(Maillon<Coordonnee>)this.getTete();
            Maillon<Coordonnee> tmp2=l.getTete();
            Coordonnee vecteur = tmp1.getInfo().distanceCoordonnee(tmp2.getInfo()); //vecteur modele entre les coordonnees du premier maillon de chaque liste

            while (tmp1.getSuivant()!=null){
                tmp1=tmp1.getSuivant(); //coordonnee tmp1
                tmp2=tmp2.getSuivant();//coordonnee tmp2
                if(!(tmp1.getInfo().distanceCoordonnee(tmp2.getInfo()).equals(vecteur)))return false;

            }
            return  true;

        }else return false;

    }
    //Juliette
    /**
     * Verifie la configuration oscillateur du jeu
     *
     * @param l
     * @return True si la configuration du jeu est oscillateur, False sinon
     */
    public boolean estOscillateur(Liste<Coordonnee> l){
        if(this.taille()==l.taille()){
            Maillon tmp1=this.getTete();
            Maillon tmp2=l.getTete();

            while (tmp1!=null){
                if(tmp1.getInfo().equals(tmp2.getInfo())){
                    tmp1 = tmp1.getSuivant(); //coordonnee tmp1
                    tmp2 = tmp2.getSuivant();//coordonnee tmp2
                }else return false;
            }
            return  true;

        }else return false;

    }

    // Juliette
    /**
     * Teste la configuration du jeu
     *
     * @param l : Liste des configurations precedentes
     * @return configuration : 1 si configuration morte ; 2 si configuration stable ; 3 si configuration oscillateur ; 4 sı configuration vaisseau ; 5 si configuration indéterminee
     */
    public int configuration(Liste<Liste<Coordonnee>> l){ //la liste de liste doit etre completee avec ajouteEntete()
        if(this.estVide())return 1; //liste vide > pas de cellule vivante > configuration morte
        if(this.equals(l.getTete())) return 2; //liste identique a la precedente > plus d'evolution > configuration stable
        Maillon<Liste<Coordonnee>> tmp = l.getTete();

        while (tmp!=null){ //Recherche d'une liste identique a this > l'evolution sera la meme > configuration oscillateur
            if(this.estOscillateur(tmp.getInfo())){
                return 3;
            }else{
                tmp=tmp.getSuivant();
            }
        }
        while (tmp!=null){ //Recherche d'une liste identique a this > l'evolution sera la meme > configuration oscillateur
            if(this.estVaisseau(tmp.getInfo())){
                return 4;
            }else{
                tmp=tmp.getSuivant();
            }
        }
        return 5;
    }

    public boolean contient(Maillon<Coordonnee> m) {
        Maillon<Coordonnee> tmp = (Maillon<Coordonnee>) this.getTete();
        Maillon<Coordonnee> mc = (Maillon<Coordonnee>) m;
        while (tmp != null) {
            if (tmp.getInfo().compareTo(mc.getInfo()) == 0) {
                return true;
            } else {
                if (tmp.getInfo().compareTo(mc.getInfo()) == 1) {
                    return false;
                }
            }
            tmp = tmp.getSuivant();
        }
        return false;
    }

    public Liste<Coordonnee> genSuivante() {

        Liste<Coordonnee> nliste = new Liste(); //Nouvelle génération
        Liste<Coordonnee> lc = new Liste(); //Map

        int minColonne = this.minColonne()-1;
        int maxColonne = this.maxColonne()+1;
        int minLigne = this.minLigne()-1;
        int maxLigne = this.maxLigne()+1;

        for (int l = minLigne; l <= maxLigne; l++){
            for (int c = minColonne; c <= maxColonne; c++){
                lc.ajouterMaillon(new Maillon<Coordonnee>(new Coordonnee(l,c), null));
            }
        }

        Maillon<Coordonnee> tmplc = lc.getTete();

        while (tmplc != null) {
            int nombreVoisins = nombreVoisins(tmplc);

            if (this.contient(tmplc)) {
                if (nombreVoisins == 2 || nombreVoisins == 3) {
                    nliste.ajouterMaillon(new Maillon<Coordonnee>(new Coordonnee(tmplc.getInfo().getLigne(), tmplc.getInfo().getColonne()), null));
                }
            } else {
                if (nombreVoisins == 3) {
                    nliste.ajouterMaillon(new Maillon<Coordonnee>(new Coordonnee(tmplc.getInfo().getLigne(), tmplc.getInfo().getColonne()), null));
                }
            }

            tmplc = tmplc.getSuivant();
        }

        return nliste;
    }

    public int nombreVoisins(Maillon<Coordonnee> tmplc) {

        int l = tmplc.getInfo().getLigne();
        int c = tmplc.getInfo().getColonne();
        int nombreVoisins = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Maillon<Coordonnee> mc = new Maillon<Coordonnee>(new Coordonnee(l+i, c+j), null);
                if (this.contient(mc) && mc.getInfo().compareTo(tmplc.getInfo()) != 0) {
                    nombreVoisins++;
                }
            }
        }

        return nombreVoisins;
    }
}