package charles.tp_junit_comptes;

import java.util.ArrayList;
import java.util.List;

import charles.tp_junit_comptes.exceptions.CompteDejaExistantException;
import charles.tp_junit_comptes.exceptions.CompteInconnuException;

public class GestionnaireComptes {

    private List<CompteBancaire> comptes = new ArrayList<>();

    public void ajouterCompte(CompteBancaire compte) {

        for (CompteBancaire c : comptes) {
            if (c.getIban().equals(compte.getIban())) {
                throw new CompteDejaExistantException("Compte déjà existant");
            }
        }

        comptes.add(compte);
    }

    public CompteBancaire rechercherCompte(String iban) {

        for (CompteBancaire compte : comptes) {
            if (compte.getIban().equals(iban)) {
                return compte;
            }
        }

        throw new CompteInconnuException("Compte inconnu");
    }

    public void virement(String ibanSource, String ibanDestination, double montant) {

        CompteBancaire source = rechercherCompte(ibanSource);
        CompteBancaire destination = rechercherCompte(ibanDestination);

        source.retirer(montant);
        destination.deposer(montant);
    }

    public double soldeTotal() {

        double total = 0;

        for (CompteBancaire compte : comptes) {
            total += compte.getSolde();
        }

        return total;
    }

    public List<CompteBancaire> listeComptesEnDecouvert() {

        List<CompteBancaire> resultats = new ArrayList<>();

        for (CompteBancaire compte : comptes) {
            if (compte.estEnDecouvert()) {
                resultats.add(compte);
            }
        }

        return resultats;
    }
}
