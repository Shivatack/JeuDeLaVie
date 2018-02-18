package JeuDeLaVie;

import java.io.IOException;
import java.util.Scanner;

public class JeuDeLaVie {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // AYOUB
        Liste l = new Liste();
        String fichier = "test.lif";
        try {
            l = GestionFichier.LireFichier(fichier);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        if (!l.estVide()) {
            System.out.print(l.toString());
        }
        int choix = 1;

        while (choix == 1) {
            System.out.println("\nAvancer à la prochaine génération : 1\nStopper : 0");
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                if (choix == 1) {
                    System.out.println("\nPROCHAINE GENERATION !");
                    l = l.genSuivante();
                    if (!l.estVide()) {
                        System.out.print(l.toString());
                    } else {
                        System.out.println("\nGENERATION MORTE !");
                        break;
                    }
                } else {
                    if (choix == 0) {
                        System.out.println("\nSTOP !");
                    } else {
                        choix = 1;
                        System.out.println("\nJ'ai pas compris wallah...");
                    }
                }
            } else {
                sc.nextLine();
                System.err.println("\nSaisissez un entier !");
            }
        }

        // JULIETTE
        if (args.length != 1 && args.length != 3) { //else
            System.out.println("Veuillez saisir une commande valide. \n java -jar Perso.jar -h : rappelle la liste des options du programme ");
        }
        if (args.length == 1) {
            if (args[1] == "-name") {
                System.out.println("Azaroual Ayoub \n Douare Juliette \n Sağ Özgür \n Sağ Uzay");
                if (args[1] == "-h") {
                    System.out.println("• java -jar Perso.jar -name affiche vos noms et prénoms " +
                            "\n• java -jar Perso.jar -s d fichier.lif exécute une simulation du jeu d’une durée d affichant les configurations du jeu avec le numéro de génération." +
                            "\n • java -jar Perso.jar -c max fichier.lif calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul." +
                            "\n• java -jar Perso.jar -w max dossier calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html.)");
                }
            }

        }
    }
}