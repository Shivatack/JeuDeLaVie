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
                String s = "";
                try {
                    s = GestionFichier.fichier((String) o[3]);
                    s = traiterFich((Integer) o[1], s, (Integer) o[4]);
                    eFGen(s);
                    System.out.println("\nFIN DE TRAITEMENT\n");
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                if ((Integer) o[0] == 1) {
                    test((int) o[1], (String) o[3], (int) o[4]);
                }
                if ((Integer) o[0] == 2) {
                    System.out.println(calculTQP((int) o[1], (String) o[3], (int) o[4]));
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
    private static String traiterFich(int max, String fichiers , int monde){
        Scanner sc = new Scanner (fichiers);
        String stat="<!DOCTYPE html><html lang=\"fr\"><head><title>D.A.S.S.</title><meta charset=\"utf-8\"></head><body><div>";
        String nomFich="";
        sc.useDelimiter("\n");
        System.out.println();
        while(sc.hasNext()){
            nomFich=sc.next();
            System.out.println("Calcul du fichier : " + nomFich);
            stat+="<Fieldset><Legend>Nom du fichier : " +nomFich +"</Legend><p>";
            stat+=calculTQP(max,nomFich,monde);
            stat+="</p></Fieldset>";
        }
        stat+="</div></body></html>";
        return stat;
    }

    public static String calculDep(Coordonnee c, Coordonnee k){
        int ligne=c.getLigne()-k.getLigne();
        int col=c.getColonne()-k.getColonne();
        return "("+ligne+";"+col+")";
    }

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

    public static String calculTQP(int max,String nomFich , int monde){
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
            if (!listeInit.estVide() && !listeTest.estVide()) { // JAI AJOUTE CA CEST BON ?????????!!!!!!!!!! A CAUSE DU MONDE FINI Y A UNE LISTE QUI EST VIDE
                dep = calculDep(listeInit.getTete().getInfo(), listeTest.getTete().getInfo());
            }
            etat += "Forme : " +forme +".<br />Taille de la queue : "+ queue +".<br />Periode : "+periode +".<br />Le vecteur de deplacement est : "+dep+".";
        } else {
            etat+="Aucun information n'est disponible sur le fichier avec le maximum " + max + ".";
        }
        return etat;
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

    public static void test(int max, String fich, int monde) {
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