package charles.tp_junit_comptes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import charles.tp_junit_comptes.exceptions.CompteDejaExistantException;
import charles.tp_junit_comptes.exceptions.CompteInconnuException;
import charles.tp_junit_comptes.exceptions.SoldeInsuffisantException;

class GestionnaireComptesTest {

    @Test
    void ajouter() {
        GestionnaireComptes g = new GestionnaireComptes();
        CompteBancaire c = new CompteBancaire("FR001", "Charles", 1000, 500);

        g.ajouterCompte(c);

        assertSame(c, g.rechercherCompte("FR001"));
    }

    @Test
    void doublon() {
        GestionnaireComptes g = new GestionnaireComptes();

        g.ajouterCompte(
            new CompteBancaire("FR001", "Charles", 1000, 500)
        );

        assertThrows(
            CompteDejaExistantException.class,
            () -> g.ajouterCompte(
                new CompteBancaire("FR001", "Paul", 500, 0)
            )
        );
    }

    @Test
    void rechercher() {
        GestionnaireComptes g = new GestionnaireComptes();
        CompteBancaire c = new CompteBancaire("FR001", "Charles", 1000, 500);

        g.ajouterCompte(c);

        assertSame(c, g.rechercherCompte("FR001"));
    }

    @Test
    void inconnu() {
        GestionnaireComptes g = new GestionnaireComptes();

        assertThrows(
            CompteInconnuException.class,
            () -> g.rechercherCompte("FR999")
        );
    }

    @Test
    void virement() {
        GestionnaireComptes g = new GestionnaireComptes();

        CompteBancaire source =
            new CompteBancaire("FR001", "Charles", 1000, 0);

        CompteBancaire destination =
            new CompteBancaire("FR002", "Boris", 500, 0);

        g.ajouterCompte(source);
        g.ajouterCompte(destination);

        g.virement("FR001", "FR002", 200);

        assertEquals(800, source.getSolde());
        assertEquals(700, destination.getSolde());
    }

    @Test
    void virementEchec() {
        GestionnaireComptes g = new GestionnaireComptes();

        CompteBancaire source =
            new CompteBancaire("FR001", "Charles", 100, 0);

        CompteBancaire destination =
            new CompteBancaire("FR002", "Boris", 500, 0);

        g.ajouterCompte(source);
        g.ajouterCompte(destination);

        assertThrows(
            SoldeInsuffisantException.class,
            () -> g.virement("FR001", "FR002", 200)
        );

        assertEquals(100, source.getSolde());
        assertEquals(500, destination.getSolde());
    }

    @Test
    void total() {
        GestionnaireComptes g = new GestionnaireComptes();

        g.ajouterCompte(
            new CompteBancaire("FR001", "Charles", 1000, 0)
        );

        g.ajouterCompte(
            new CompteBancaire("FR002", "Boris", 500, 0)
        );

        assertEquals(1500, g.soldeTotal());
    }

    @Test
    void decouvert() {
        GestionnaireComptes g = new GestionnaireComptes();

        CompteBancaire c1 =
            new CompteBancaire("FR001", "Charles", 1000, 500);

        CompteBancaire c2 =
            new CompteBancaire("FR002", "Boris", -100, 500);

        g.ajouterCompte(c1);
        g.ajouterCompte(c2);

        List<CompteBancaire> resultats =
            g.listeComptesEnDecouvert();

        assertEquals(1, resultats.size());
        assertTrue(resultats.contains(c2));
    }
}
