package JeuDeLaVie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Object[] o = Controleur.saisies(args);
        if (o != null) {
            if ((Integer) o[0] == 3) {

                File d = (File) o[3];

                //java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les
                //    jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier
                //    html.
                //(o[1], d);
            } else {
                if ((Integer) o[0] == 1) {
                    //java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu
                    CalculGen.simulation((int)o[1], (Liste<Coordonnee>) o[2]);
                }
                if ((Integer) o[0] == 2) {
                    //java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du
                    //    jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée
                    //    maximale de simulation pour déduire les résultats du calcul.
                    //(t[2], l);
                }
            }
        }
    }

    //Juliette

    /**
     * Retourne vrai si le string est un nombre, faux sinon
     *
     * @param s : chaine à verifier
     * @return : retourne un booleen
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

    //Juliette et Ayoub

    /**
     * Retourne vrai si le string correspond au nom d'un fichier, faux sinon
     *
     * @param fichier : fichier à verifier
     * @return : retourne un booleen
     */
    public static boolean existe(String fichier) {
        try {
            FileReader fr = new FileReader(fichier);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //NEW

    public static String statFich(String nomFich,int max){
        Liste<Coordonnee> l =new Liste<Coordonnee>();
        try {
            l = GestionFichier.LireFichier(nomFich);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        String forme=null;
        String etat="<Fieldset><Legend>Nom Du Fichier :" +nomFich +"</Legend><p>";
        int i = 0;
        Liste<Coordonnee> liste1 = l;
        Liste<Coordonnee> liste2 = l;
        Liste<Coordonnee> liste1Bis = l;
        while(forme==null && i<max){
            liste1 = CalculGen.simulation(1, liste1);
            liste1Bis = CalculGen.simulation(1, liste1);
            liste2 = CalculGen.simulation(2, liste2);
            forme = verificationForme(liste1,liste1Bis,liste2);
            i++;
        }
        if(i<max){
            etat+= "Nombre de generation : " +(i+1) +" Forme detectee : " +forme +"</p></Fieldset>" +"\n";
        }else {
            etat+= "Nombre de generation atteint : " +i +" Il n'y a pas de Forme detectee." +"</p></Fieldset>" +"\n";
        }
        return etat;
    }

    private static String traiterFich(int max, String fichiers){
        Scanner sc = new Scanner (fichiers);
        String stat="<!DOCTYPE html><html lang=\"fr\"><head><title>D.A.S.S.</title><meta charset=\"utf-8\"></head><body><div>";
        String nomFich="";
        sc.useDelimiter("\n");
        while(sc.hasNext()){
            nomFich=sc.next();
            stat+=statFich(nomFich,max);
        }
        stat+="</div></body></html>";
        return stat;
    }

    public static String verificationForme(Liste<Coordonnee> liste1,Liste<Coordonnee> liste1Bis, Liste<Coordonnee> liste2){
        String s=null;
        if(liste1.estVide()) {
            s="Forme mort.";
        } else {
            if (liste1.toString().equals(liste1Bis.toString())) {
                s="Forme stable";
            } else {
                if ( !liste2.estVide() && (liste1.getTete().getInfo().compareTo(liste2.getTete().getInfo()) == 0 && liste1.toString().equals(liste2.toString()))) {
                    s="Forme oscilateur.";
                } else {
                    if(!liste2.estVide() && (liste1.toString().equals(liste2.toString()) && liste1.getTete().getInfo().compareTo(liste2.getTete().getInfo()) != 0)){
                        s="Forme vaisseau.";
                    }
                }
            }
        }
        return s;
    }

    private static void eFGen (String contenu)throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fichierHTML.html")));
        out.println(contenu);
        out.close();
    }

    public static void test(int max, String fich) {

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
            Liste<Coordonnee> liste2 = k;
            Liste<Coordonnee> liste1Bis = k;

            public void actionPerformed(ActionEvent e) {
                i++;
                System.out.print(liste1.toString());
                System.out.println("Génération  = " + i +"\n");
                liste1 = CalculGen.simulation(1, liste1);
                liste1Bis = CalculGen.simulation(1, liste1);
                liste2 = CalculGen.simulation(2, liste2);
                if(verificationForme(liste1,liste1Bis,liste2) != null){
                    System.out.println(verificationForme(liste1,liste1Bis,liste2));
                }
                if (i==max) {
                    System.exit(0);
                }
            }

        };

        Timer t = new Timer(1000, al);

        t.start();

        try {
            System.in.read();
        }
        catch (IOException e){}

        t.stop();
    }
}