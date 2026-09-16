package charles.tp_junit_comptes;

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
        solde += montant;
    }

    public void retirer(double montant) {
        solde -= montant;
    }

    public double calculerInterets(double taux) {
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
