package JeuDeLaVie;

import java.io.File;
import java.io.IOException;

public class Controleur {
    /*
    • java -jar JeuDeLaVie.jar -name affiche vos noms et prénoms
    • java -jar JeuDeLaVie.jar -h rappelle la liste des options du programme
    1 java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu
    d’une durée d affichant les configurations du jeu avec le numéro de génération.
    2 java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du
    jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée
    maximale de simulation pour déduire les résultats du calcul.
    3 java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les
    jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier
    html.
    */

    /**
     * vérification de l'existence du fichier et conversion en liste
     * @param fichier
     * @return l : liste de coordonnee
     */
    public static Liste<Coordonnee> lecture(String fichier) {
        Liste<Coordonnee> l = null;
        try {
            l = GestionFichier.LireFichier(fichier);
        } catch (IOException e) {
            System.out.print("Le fichier donné est introuvable.");
            return l;
        }
        return l;
    }


    public static Integer convertion(String max) { //retourne false si le

        Integer n = -1;
        try {
            n = Integer.parseInt(max);
        } catch (Exception e) {
            System.out.println("Le deuxième paramètre correspond au nombre de generations a tester. Il doit etre strictement superieur a 0.");
            return n;
        }

        if (n <= 0) {
            System.out.println("La valeur correspondant au nombre de generations a tester doit etre strictement superieur a 0.");
            return n;
        }
        return n;
    }

    /**
     * Fonction qui verifie les parametres entres en ligne de commande
     *
     * @param args : tableau de String
     * @return : tableau de String
     */
    public static Object[] saisies(String[] args) {
        Liste<Coordonnee> l = null;
        Integer choix = 0;
        Integer max = -1;
        File f = null;
        //1 argument
        if (args.length == 1) {
            if (args[1] == "-name") {
                System.out.println("Azaroual Ayoub \n Douare Juliette \n Sağ Özgür \n Sağ Uzay");
                return null;
            }
            if (args[1] == "-h") {
                System.out.println("• java -jar Perso.jar -name affiche vos noms et prénoms " +
                        "\n• java -jar Perso.jar -s d fichier.lif exécute une simulation du jeu d’une durée d affichant les configurations du jeu avec le numéro de génération." +
                        "\n • java -jar Perso.jar -c max fichier.lif calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul." +
                        "\n• java -jar Perso.jar -w max dossier calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html.)");
                return null;
            }//3args
        } else {
            if (args.length == 3) {
                l = lecture(args[3]); //vérification de l'existence du fichier et conversion en liste
                max = convertion(args[2]);
                if (max > 0){
                    if (l!=null ) {
                        if (args[0].equals("s")) {
                            choix = 1; //simulation
                        } else {
                            if (args[0].equals("c")) {
                                choix = 2; //evolution
                            }
                        }
                    }
                    f = new File(args[2]);
                    if (args[0].equals("w")&& f.isDirectory()) { //revoir la fct
                        choix = 3; //evolutionDoss
                    } else {
                        System.out.println("Veuillez saisir une commande valide. \n java -jar Perso.jar -h : rappelle la liste des options du programme ");
                    }
                }
                //nb d'arguments incorrect
            } else {
                System.out.println("Veuillez saisir une commande valide. \n java -jar Perso.jar -h : rappelle la liste des options du programme.");
                return null;
            }
        }
        return new Object[] {choix, max, l, f};
    }
}
