package charles.tp_junit_comptes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import charles.tp_junit_comptes.exceptions.MontantInvalideException;
import charles.tp_junit_comptes.exceptions.SoldeInsuffisantException;


import org.junit.jupiter.api.Test;

public class CompteBancaireTest {

    @Test
    public void testDepot() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        compte.deposer(500);

        assertEquals(1500, compte.getSolde());
    }

    @Test
    public void testRetrait() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        compte.retirer(300);

        assertEquals(700, compte.getSolde());
    }

    @Test
    public void testCalculerInterets() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        double interets = compte.calculerInterets(0.05);

        assertEquals(50, interets);
        assertEquals(1000, compte.getSolde());
    }

    @Test
    public void testEstEnDecouvert() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", -100, 200);

        assertTrue(compte.estEnDecouvert());
    }
    @Test
    public void testInformationsCompte() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        assertEquals(1000, compte.getSolde());
        assertEquals("Charles", compte.getTitulaire());
        assertEquals("FR123", compte.getIban());
    }
    @Test
    public void testDepotMontantNul() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        assertThrows(MontantInvalideException.class, () -> {
            compte.deposer(0);
        });
    }

    @Test
    public void testDepotMontantNegatif() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        assertThrows(MontantInvalideException.class, () -> {
            compte.deposer(-100);
        });
    }

    @Test
    public void testRetraitMontantNul() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        assertThrows(MontantInvalideException.class, () -> {
            compte.retirer(0);
        });
    }

    @Test
    public void testRetraitMontantNegatif() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 1000, 200);

        assertThrows(MontantInvalideException.class, () -> {
            compte.retirer(-100);
        });
    }

    @Test
    public void testRetraitJusquAuDecouvertAutorise() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 100, 200);

        compte.retirer(300);

        assertEquals(-200, compte.getSolde());
    }

    @Test
    public void testRetraitDepasseDecouvertAutorise() {
        CompteBancaire compte = new CompteBancaire("FR123", "Charles", 100, 200);

        assertThrows(SoldeInsuffisantException.class, () -> {
            compte.retirer(301);
        });
    }
}
