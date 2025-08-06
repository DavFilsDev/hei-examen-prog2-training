package org.example;

public class CompteBancaire {
    private String numero;
    private String titulaire;
    private double solde;

    public CompteBancaire(String numero, String titulaire, double soldeInitial) {
        this.numero = numero;
        this.titulaire = (titulaire != null && !titulaire.isEmpty()) ? titulaire : "Inconnu";
        this.solde = Math.max(soldeInitial, 0.0);
    }

    public String getNumero() {
        return numero;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public double getSolde() {
        return solde;
    }

    public void setTitulaire(String nouveauNom) {
        if (nouveauNom != null && !nouveauNom.isEmpty()) {
            this.titulaire = nouveauNom;
        }
    }

    public boolean deposer(double montant) {
        if (montant > 0) {
            solde += montant;
            return true;
        }
        return false;
    }

    public boolean retirer(double montant) {
        if (montant > 0 && montant <= solde) {
            solde -= montant;
            return true;
        }
        return false;
    }

    public boolean transfererVers(CompteBancaire autreCompte, double montant) {
        if (autreCompte != null && this.retirer(montant)) {
            return autreCompte.deposer(montant);
        }
        return false;
    }

    public void afficherInfo() {
        System.out.println("Compte : " + numero);
        System.out.println("Titulaire : " + titulaire);
        System.out.println("Solde : " + solde + " €");
    }
}


