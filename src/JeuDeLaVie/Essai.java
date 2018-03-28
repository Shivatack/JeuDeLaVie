package JeuDeLaVie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Essai {
    public static Liste<Path> essai (String [] args) throws IOException{
        return fichier("C:\\Users\\Anonymous\\Desktop\\Cours L2 Info\\S4\\Projet\\JeuDeLaVie"); // Informer ton chemin
    }

    private static Liste<Path> fichier(String chemin) throws IOException {
        Liste<Path> lp = new Liste();
        Files.newDirectoryStream(Paths.get(chemin),
                path -> path.toString().endsWith(".LIF")).forEach(k -> lp.ajouterMaillon(new Maillon(k, null)));
        return lp;
    }
}