package JeuDeLaVie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GestionFichier {

    // Özgür et Ayoub
    public static Liste LireFichier(String fichier) throws FileNotFoundException, IOException {
        Liste maListe = new Liste();
        FileReader fr = new FileReader(fichier);
        BufferedReader br = new BufferedReader(fr);
        String bloc = "";
        String line = "";
        int aff = 1;
        boolean debut = true;
        while ((line = br.readLine()) != null) { // Parcours du fichier
            StringTokenizer st = new StringTokenizer(line);
            if (st.hasMoreTokens()) { // ligne ajoutee
                String token = st.nextToken();
                if (token.equals("#P") && debut == true) {
                    debut = false;
                    bloc = line + "\n";
                } else {
                    if (!token.equals("#P") && debut == false) {
                        bloc += line + "\n";
                    } else {
                        if (token.equals("#P") && debut == false) {
                            lireBlocs(bloc, maListe);
                            bloc = line + "\n";
                        }
                    }
                }
            }
        }
        lireBlocs(bloc, maListe);
        return maListe;
    }

    public static void lireBlocs(String bloc, Liste maListe) {
        int ligne=0;
        int colonne=0;
        Scanner li = new Scanner(bloc);
        li.useDelimiter("\n");
        String s = "";
        if (li.hasNext()) {
            StringTokenizer st = new StringTokenizer(li.nextLine());
            while (st.hasMoreTokens()) {
                st.nextToken();
                ligne = Integer.parseInt(st.nextToken());
                colonne = Integer.parseInt(st.nextToken());
            }
        }
        int compteur = 0;
        int calcul;
        while (li.hasNext()) {
            s = li.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    //calcul = ligne + compteur;
                    //System.out.print("\n***" + calcul + " ");
                    //calcul = colonne + i;
                    //System.out.print(calcul + "***\n");
                    Coordonnee c = new Coordonnee((ligne + compteur), (colonne + i));
                    Maillon<Coordonnee> m = new Maillon<Coordonnee>(c, null);
                    maListe.ajouterMaillon(m);
                }
            }
            compteur++;
        }
    }
}
