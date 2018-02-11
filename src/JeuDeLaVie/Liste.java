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
                    mc.setSuivant(tmp.getSuivant());
                    tmp.setSuivant(mc);
                }
            }
        } else {
            this.ajouterEnTete(m);
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
        if(!this.estVide()){
            Maillon<Coordonnee> tmp = (Maillon<Coordonnee>) this.getTete();
            for (int l=minLigne();l<=maxLigne();l++){
                for (int c=minColonne();c<=maxColonne();c++){
                    Maillon<Coordonnee> m=new Maillon<Coordonnee>(new Coordonnee(l,c),null);
                    if(tmp.getSuivant() != null) {
                        if (m.getInfo().compareTo(tmp.getInfo()) == 0) {
                            s += '*';
                            tmp = tmp.getSuivant();
                        } else {
                            s += '.';
                        }
                    }
                }
                s+="\n";
            }
        }
        return s;
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
}