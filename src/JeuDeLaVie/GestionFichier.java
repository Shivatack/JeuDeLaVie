package JeuDeLaVie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GestionFichier {

    // Özgür et Ayoub

    /**
     * Cette fonction renvoi la liste contenant les cellules vivantes lues à partir du fichier passé en paramètre
     *
     * @param fichier : fichier lif
     * @return Liste de cellules vivantes
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Liste LireFichier(String fichier) throws FileNotFoundException, IOException {
        Liste maListe = new Liste();
        FileReader fr = new FileReader(fichier);
        BufferedReader br = new BufferedReader(fr);
        String bloc = "";
        String line = "";
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

    /**
     * Cette fonction ajoute à la liste des cellules lues à partir du bloc passé en paramètre lu à partir du fichier
     *
     * @param bloc : bloc de cellules
     * @param maListe : liste de cellules
     */
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
        while (li.hasNext()) {
            s = li.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    Coordonnee c = new Coordonnee((ligne + compteur), (colonne + i), 0);
                    Maillon<Coordonnee> m = new Maillon(c, null);
                    maListe.ajouterMaillon(m);
                }
            }
            compteur++;
        }
    }

    public static String fichier(String dossier) throws IOException {
        Liste<String> lp = new Liste();
        DirectoryStream<Path> d;
        d = Files.newDirectoryStream(Paths.get(dossier), path -> path.toString().endsWith(".LIF"));
        for (Path p : d) {
            lp.ajouterMaillon(new Maillon<String>(p.toString(), null));
        }
        Maillon<String> ms = lp.getTete();
        String s="";
        String[] ts=null;
        while (ms != null){
            ts=ms.getInfo().toString().split("\\\\");
            s+=ts[ts.length-1] +"\n";
            ms = ms.getSuivant();
        }
        return s;
    }
}
