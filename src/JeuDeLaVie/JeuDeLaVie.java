package JeuDeLaVie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class JeuDeLaVie {

    //Uzay

    /**
     * Renvoi la plus petite colonne contenue dans la liste passée en paramètre
     *
     * @param t : La liste dont il faut trouver la colonne minimum
     * @return la colonne minimum
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
     * Renvoi la plus petite ligne contenue dans la liste passée en paramètre
     *
     * @param t : La liste dont il faut trouver la ligne minimum
     * @return la ligne minimum
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
     * Renvoi la plus grande colonne contenue dans la liste passée en paramètre
     *
     * @param t : La liste dont il faut trouver la colonne maximum
     * @return la colonne maximum
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
     * Renvoi la plus grande ligne contenue dans la liste passee en paramètre
     *
     * @param t : La liste dont on doit trouver la ligne maximum
     * @return la ligne maximum
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

    //Juliette

    /**
     * Retourne vrai si le string est un nombre, faux sinon
     *
     * @param s : chaine à verifier
     * @return retourne un booleen
     */
    public static boolean estEntier(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Fonction qui creer un string contenant les resultats des simulations sous forme de html
     *
     * @param choix : choix de l'utilisateur -name, -h, -s, -c, -w
     * @param max : le nobre max de generation
     * @param fichiers : les noms des fichiers dans lesquels des generation initiales se trouvent
     * @param monde : le type de monde
     * @return : renvoie le code necessaire pour generer une page HTML
     */
    public static String traiterFich(int choix, int max, String fichiers , int monde){
        Scanner sc = new Scanner (fichiers);
        String css = "<style type=\"text/css\">header,body{margin: auto;width: 960px;height: 106px;}#logo{display: inline-block;vertical-align: middle;margin-left: 0.55%;height: 90px;width: 360px;}header{background-color: #EFEFEF;border: thick double #D84B1A;border-radius: 7px 7px;height: 125px;}#page{background-color: #EFEFEF;min-width: 960px;min-height: 434px;border: thick double #D84B1A;border-radius: 15px 15px;}#menu{display: inline-block;vertical-align: middle;list-style-type: none ;font-family: Impact;font-size: 16px;text-align: right;color: #FF640D;max-width: 550px;margin-left: 7%;margin-bottom: 3.5%;}form[class=\"accueil\"]{width: 900px;margin-top: 0.50%;margin-bottom: 2%;margin-left: 3%;}fieldset{border:2px solid #30AFFC;-moz-border-radius:8px;-webkit-border-radius:8px;border-radius:8px;margin-top: 1%;}legend{font-family: Impact;color: #1B6195;}footer{background-color: #EFEFEF;min-width: 960px;border: thick double #D84B1A;border-radius: 7px 7px;font-family: Impact;font-size: 16px;text-align: center;color: #FF640D;}.finpage{margin-top: 15px;}</style><header><img src=\"LOGO_JDV.png\" id=\"logo\" style=\"width: 389px; height: 144px;\"><p id=\"menu\">R&eacute;sultats de l'ex&eacute;cution du programme sur les fichiers .LIF suivants</p></header>";
        String stat="<!DOCTYPE html><html lang=\"fr\"><head><title>D.A.S.S.</title><meta charset=\"utf-8\"></head>" + css + "<body><div id=\"page\"><form class=\"accueil\">";
        String nomFich="";
        sc.useDelimiter("\n");
        System.out.println();
        while(sc.hasNext()){
            nomFich=sc.next();
            System.out.println("Calcul du fichier : " + nomFich);
            stat+="<fieldset><Legend>Nom du fichier : " +nomFich +"</Legend><p>";
            stat+=calculTQP(choix, max,nomFich,monde);
            stat+="</p></fieldset>";
        }
        stat+="</form></div></body><footer><p class=\"finpage\">R&eacute;alis&eacute; par Douare Juliette, Azaroual Ayoub, Uzay et &Ouml;zg&uuml;r Sa&#287;.</p></footer></html>";
        return stat;
    }

    /**
     * Fonction qui calcul le deplacement
     *
     * @param c : une coordonnee
     * @param k : une coordonnee
     * @return : le vecteur allant de c à k
     */
    public static String calculDep(Coordonnee c, Coordonnee k){
        int ligne=c.getLigne()-k.getLigne();
        int col=c.getColonne()-k.getColonne();
        return "("+ligne+";"+col+")";
    }

    /**
     * Fonction qui calcul le type d'evolution d'un jeu
     *
     * @param max : la generation limite
     * @param nomFich : nom de fichier dans lequel notre generation initiale se trouve
     * @param monde : le type de monde
     * @return les caracteristiques du fichier
     */
    public static Object[] typeEvolution(String nomFich, int max, int monde){
        Liste<Coordonnee> l = new Liste<Coordonnee>();
        try {
            l = GestionFichier.LireFichier(nomFich);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        String forme=null;
        int i = 0;
        Liste<Coordonnee> liste1 = l;
        Liste<Coordonnee> liste2 = l;
        Liste<Coordonnee> liste1Bis = l;
        while(forme==null && i<max){
            liste1 = CalculGen.simulation(1, liste1, monde);
            liste1Bis = CalculGen.simulation(1, liste1, monde);
            liste2 = CalculGen.simulation(2, liste2, monde);
            forme = verificationForme(liste1,liste1Bis,liste2);
            i++;
        }
        return new Object[]{forme,liste1,l};
    }

    /**
     * Fonction qui calcule a taille de la queue et de la periode
     *
     * @param choix : choix de l'utilisateur -name, -h, -s, -c, -w
     * @param max : la generation limite
     * @param nomFich : nom de fichier dans lequel notre generation initiale se trouve
     * @param monde : le type de monde
     * @return : les caracteristiques du fichier
     */
    public static String calculTQP(int choix, int max,String nomFich , int monde){
        Object[] o1=typeEvolution(nomFich,max,monde);
        String etat="";
        String forme=(String) o1[0];
        if(forme != null){
            Liste<Coordonnee> listeTest =(Liste<Coordonnee>) o1[1];
            Liste<Coordonnee> listeInit = (Liste<Coordonnee>) o1[2];
            Boolean arret = false;
            int periode = 1;
            Liste<Coordonnee> listeTest2 = CalculGen.simulation(1, listeTest,monde);
            while (!arret){
                if (listeTest.toString().equals(listeTest2.toString())) {
                    arret=true;
                } else {
                    periode++;
                    listeTest2 = CalculGen.simulation(1, listeTest2,monde);
                }
            }
            arret = false;
            listeTest = CalculGen.simulation(periode,listeInit,monde);
            int queue=0;
            while(!arret){
                if (listeTest.toString().equals(listeInit.toString())) {
                    arret=true;
                } else {
                    queue++;
                    listeTest = CalculGen.simulation(1, listeTest,monde);
                    listeInit = CalculGen.simulation(1, listeInit,monde);
                }
            }
            String dep = "";
            if (!listeInit.estVide() && !listeTest.estVide()) {
                dep = calculDep(listeInit.getTete().getInfo(), listeTest.getTete().getInfo());
            }
            if (choix == 2) {
                etat += "Forme : " + forme + ".\nTaille de la queue : " + queue + ".\nPeriode : " + periode + ".\nLe vecteur de deplacement est : " + dep + ".";
            } else {
                etat += "Forme : " + forme + ".<br />Taille de la queue : " + queue + ".<br />Periode : " + periode + ".<br />Le vecteur de deplacement est : " + dep + ".";
            }
        } else {
            etat+="Aucun information n'est disponible sur le fichier avec le maximum " + max + ".";
        }
        return etat;
    }

    /**
     * Fonction qui renvoie la forme du jeu en fonction des listes recues
     *
     * @param liste1 : liste de generation courtante
     * @param liste1Bis : liste avancé d'une generation par rapport a liste1
     * @param liste2 : liste qui avance 2 fois plus vite que liste1
     * @return : la forme detectée
     */
    public static String verificationForme(Liste<Coordonnee> liste1,Liste<Coordonnee> liste1Bis, Liste<Coordonnee> liste2){
        String s=null;
        if(liste1.estVide()) {
            s="Forme mort";
        } else {
            if (liste1.toString().equals(liste1Bis.toString())) {
                s="Forme stable";
            } else {
                if ( !liste2.estVide() && (liste1.getTete().getInfo().compareTo(liste2.getTete().getInfo()) == 0 && liste1.toString().equals(liste2.toString()))) {
                    s="Forme oscillateur";
                } else {
                    if(!liste2.estVide() && (liste1.toString().equals(liste2.toString()) && liste1.getTete().getInfo().compareTo(liste2.getTete().getInfo()) != 0)){
                        s="Forme vaisseau";
                    }
                }
            }
        }
        return s;
    }

    /**
     * Ajoute le contenu dans le fichier fichierHTML.html (le crée si necessaire=
     *
     * @param contenu : le code necessaire pour generer le page HTML
     * @throws IOException : Le fichier ne peut pas etre ecrit
     */
    public static void eFGen (String contenu)throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fichierHTML.html")));
        out.println(contenu);
        out.close();
    }

    /**
     * Fonction qui calcul et affiche les generations d'un jeu jusqu'a une generation maximum
     *
     * @param max : la valeur maximale à laquelle on doit simuler le jeu
     * @param fich : le fichier sur lequel on fait la simulation
     * @param monde : le monde sur lequel on est
     */
    public static void simulationRapide(int max, String fich, int monde) {
        Liste<Coordonnee> l =new Liste<Coordonnee>();
        try {
            l = GestionFichier.LireFichier(fich);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        final Liste<Coordonnee> k = l;
        ActionListener al = new ActionListener() {
            int i = 0;
            Liste<Coordonnee> liste1 = k;
            public void actionPerformed(ActionEvent e) {
                i++;
                System.out.print(liste1.toString());
                System.out.println("Génération  = " + i +"\n");
                liste1 = CalculGen.simulation(1, liste1, monde);
                if (i==max) {
                    System.exit(0);
                }
            }
        };
        Timer t = new Timer(2000, al);
        t.start();
        try {
            System.in.read();
        }
        catch (IOException e){}
        t.stop();
    }
}