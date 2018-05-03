package JeuDeLaVie;

import java.io.*;
import java.util.Scanner;

public class Main {

    /**
     * Main du programme
     *
     * @param args : options entrées dans le terminal
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Object[] o = Controleur.saisies(args);
        if (o != null) {
            if ((Integer) o[0] == 3) {
                String s = "";
                try {
                    s = GestionFichier.fichier((String) o[3]);
                    s = JeuDeLaVie.traiterFich((Integer) o[1], s, (Integer) o[4]);
                    JeuDeLaVie.eFGen(s);
                    System.out.println("\nFIN DE TRAITEMENT\n");
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                if ((Integer) o[0] == 1) {
                    JeuDeLaVie.simulationRapide((int) o[1], (String) o[3], (int) o[4]);
                }
                if ((Integer) o[0] == 2) {
                    System.out.println(JeuDeLaVie.calculTQP((int) o[1], (String) o[3], (int) o[4]));
                }
            }
        }
    }
}