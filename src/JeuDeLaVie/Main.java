package JeuDeLaVie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        /*String s = GestionFichier.fichier("C:\\Users\\Anonymous\\Desktop\\Cours L2 Info\\S4\\Projet\\JeuDeLaVie\\out\\artifacts\\JeuDeLaVie_jar");
        String t = traiterFich(15, s);
        System.out.println(t);
        eFGen(t);*/

        test(10, "test.LIF");

        // JULIETTE
        /*if (args.length == 1 || args.length == 3) { //else
            if (args.length == 1) {
                if (args[0].equals("-name")) {
                    System.out.println("Azaroual Ayoub \n Douare Juliette \n Sag Özgür \n Sag Uzay");
                } else {
                    if (args[0].equals("-h")) {
                        System.out.println("• java -jar JeuDeLaVie.jar -name affiche vos noms et prénoms " +
                                "\n• java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu d’une durée d affichant les configurations du jeu avec le numéro de génération." +
                                "\n • java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul." +
                                "\n• java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html.)");
                    } else {
                        System.out.println("Veuillez saisir une commande valide. \n java -jar JeuDeLaVie.jar -h : rappelle la liste des options du programme ");
                    }
                }

            } else {
                if (args[0].equals("-s")) {
                    try {
                        if (Integer.parseInt(args[1]) > 0) {
                            if (existe(args[2])) {
                                try {
                                    Liste l = GestionFichier.LireFichier(args[2]);
                                    CalculGen.simulation(Integer.parseInt(args[1]), l);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Fichier inexistant.");
                            }
                        } else {
                            System.out.println("Veuillez saisir un entier strictement supérieur à 0.");
                        }
                    } catch (Exception e) {
                        System.out.println("Veuillez saisir un entier strictement supérieur à 0 en deuxième paramètre.");
                    }


                } else {

                    if (args[0].equals("-c")) { //else
                        if (estEntier(args[1])) {
                            if (existe(args[2])) {
                                // evolution(Integer.parseInt(args[1]),args[2]);
                            } else {
                                System.out.println("Fichier inexistant.");
                            }
                        } else {
                            System.out.println("Veuillez saisir une commande valide. \n java -jar JeuDeLaVie.jar -h : rappelle la liste des options du programme ");
                        }

                    } else {
                        if (args[0].equals("-w")) {
                            if (estEntier(args[1])) {
                                if (f.isDirectory()) {

                                } else {
                                    System.out.println("Dossier inexistant.");
                                }
                                int a = Integer.parseInt(args[2]);
                                String s = traiterFich(a, args[2]);
                                eFGen(s);
                            } else {
                                System.out.println("Veuillez saisir une commande valide. \n java -jar JeuDeLaVie.jar -h : rappelle la liste des options du programme ");
                            }
                        } else {
                            System.out.println("Veuillez saisir une commande valide. \n java -jar JeuDeLaVie.jar -h : rappelle la liste des options du programme ");
                        }
                    }
                }
            }
        }*/
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