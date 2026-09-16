
package charles.tp_junit_comptes;
import charles.tp_junit_comptes.exceptions.MontantInvalideException;
import charles.tp_junit_comptes.exceptions.SoldeInsuffisantException;

public class CompteBancaire {

    final String iban;
    String titulaire;
    double solde;
    double decouvertAutorise;

    public CompteBancaire(String iban, String titulaire, double solde, double decouvertAutorise) {
        this.iban = iban;
        this.titulaire = titulaire;
        this.solde = solde;
        this.decouvertAutorise = decouvertAutorise;
    }

    public void deposer(double montant) {
        if (montant <= 0) {
            throw new MontantInvalideException("montant invalide");
        }

        solde += montant;
    }

    public void retirer(double montant) {
        if (montant <= 0) {
            throw new MontantInvalideException("montant invalide");
        }

        if (solde - montant < -decouvertAutorise) {
            throw new SoldeInsuffisantException("solde invalide");
        }

        solde -= montant;
    }

    public double calculerInterets(double taux) {
        if (taux < 0) {
            throw new IllegalArgumentException();
        }

        if (solde > 0) {
            return solde * taux;
        }

        return 0;
    }


    public boolean estEnDecouvert() {
        return solde < 0;
    }

    public double getSolde() {
        return solde;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public String getIban() {
        return iban;
    }
}
