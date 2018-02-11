package JeuDeLaVie;

import java.io.IOException;

public class JeuDeLaVie {

    public static void main(String[] args) {

        // AYOUB
        Liste l = new Liste();
        String fichier = "pushgun2.lif";
        try {
            l = GestionFichier.LireFichier(fichier);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        if (!l.estVide()) {
            System.out.print(l.toString());
        }

        // JULIETTE
        if (args.length != 1 && args.length != 3) { //else
            System.out.println("Veuillez saisir une commande valide. \n java -jar JeuDeLaVie.jar -h : rappelle la liste des options du programme ");
        }
        if (args.length == 1) {
            if (args[1] == "-name") {
                System.out.println("Azaroual Ayoub \n Douare Juliette \n Sağ Özgür \n Sağ Uzay");
                if (args[1] == "-h") {
                    System.out.println("• java -jar JeuDeLaVie.jar -name affiche vos noms et prénoms " +
                            "\n• java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu d’une durée d affichant les configurations du jeu avec le numéro de génération." +
                            "\n • java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul." +
                            "\n• java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html.)");
                }
            }

        }
    }
}