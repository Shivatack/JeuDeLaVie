package JeuDeLaVie;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListeTest {

    @Test
    public void getTete() {
        Maillon<Integer> teteli = new Maillon(5, null);
        Liste<Integer> li = new Liste<Integer>(teteli);
        Liste<Integer> lj = new Liste<Integer>();

        if (li.getTete() != teteli) {
            fail("La liste pointe vers le maillon contenant 5.");
        }
        Maillon<Integer> tetelj = null;
        if (lj.getTete() != tetelj) {
            fail("La liste pointe vers null.");
        }
    }

    @Test
    public void setTete() {
        Liste<Integer> lj = new Liste<Integer>();
        Maillon<Integer> m = new Maillon(4, null);

        lj.setTete(m);
        if (lj.getTete() != m) {
            fail("La list est vide.");
        }
    }

    @Test
    public void ajouterEnTete() {
        Maillon<Integer> n = new Maillon(5, null);
        Liste<Integer> li = new Liste<Integer>(n);
        Liste<Integer> lj = new Liste<Integer>(n);
        Maillon<Integer> m = new Maillon(4, null);
        li.setTete(m);

        lj.ajouterEnTete(m);
        if (lj.getTete() != li.getTete()) {
            fail("La liste pointe vers le maillon contenant 5.");
        }
        if (lj.getTete().getSuivant() != n) {
            fail("Le maillon suivant est le maillon 5.");
        }
    }

    @Test
    public void estVide() {
        Liste<Integer> li = new Liste<Integer>(new Maillon(5, null));
        Liste<Integer> lj = new Liste<Integer>();

        if (li.estVide() != false) {
            fail("La liste n'est pas vide.");
        }
        if (lj.estVide() != true) {
            fail("La liste est vide.");
        }
    }

    @Test
    public void ajouterMaillon() {
        Maillon<Integer> m1 = new Maillon(1, null);
        Maillon<Integer> m2 = new Maillon(2, null);
        Liste<Integer> li = new Liste<Integer>(m2);

        li.ajouterMaillon(m1);
        if (li.contient(m1) != true) {
            fail("La liste contient le maillon m1.");
        }
    }

    @Test
    public void contient() {
        Liste<Integer> li = new Liste<Integer>(new Maillon(5, null));
        Maillon<Integer> m = new Maillon(4, null);
        Maillon<Integer> n = new Maillon(5, null);

        if (li.contient(m) != false) {
            fail("La liste ne contient pas le maillon teste.");
        }
        if (li.contient(n) != true) {
            fail("La liste contient le maillon teste.");
        }
    }

    @Test
    public void supprimerMaillon() {
        Maillon<Integer> m = new Maillon(4, null);
        Maillon<Integer> n = new Maillon(5, null);
        Liste<Integer> li = new Liste<Integer>(n);


        li.supprimerMaillon(m);
        if (li.contient(m) != false) {
            fail("La liste contenait le maillon a supprimer.");
        }
        li.supprimerMaillon(n);
        if (li.contient(n) != false) {
            fail("La liste ne contenait pas le maillon a supprimer.");
        }
    }
}