package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompteBancaireTest {
    private CompteBancaire compte;

    @BeforeEach
    void setup() {
        compte = new CompteBancaire("CB001", "Alice", 1000.0);
    }

    @Test
    void testInitialisation() {
        assertEquals("CB001", compte.getNumero());
        assertEquals("Alice", compte.getTitulaire());
        assertEquals(1000.0, compte.getSolde());
    }

    @Test
    void testSetTitulaireValide() {
        compte.setTitulaire("Alice M.");
        assertEquals("Alice M.", compte.getTitulaire());
    }

    @Test
    void testSetTitulaireInvalide() {
        compte.setTitulaire("");
        assertEquals("Alice", compte.getTitulaire()); // inchangé
    }

    @Test
    void testDepotValide() {
        assertTrue(compte.deposer(500));
        assertEquals(1500.0, compte.getSolde());
    }

    @Test
    void testDepotInvalide() {
        assertFalse(compte.deposer(-100));
        assertEquals(1000.0, compte.getSolde());
    }

    @Test
    void testRetraitValide() {
        assertTrue(compte.retirer(200));
        assertEquals(800.0, compte.getSolde());
    }

    @Test
    void testRetraitInvalideMontantNegatif() {
        assertFalse(compte.retirer(-50));
        assertEquals(1000.0, compte.getSolde());
    }

    @Test
    void testRetraitInvalideSoldeInsuffisant() {
        assertFalse(compte.retirer(2000));
        assertEquals(1000.0, compte.getSolde());
    }

    @Test
    void testTransfertValide() {
        CompteBancaire destinataire = new CompteBancaire("CB002", "Bob", 500.0);
        assertTrue(compte.transfererVers(destinataire, 300));
        assertEquals(700.0, compte.getSolde());
        assertEquals(800.0, destinataire.getSolde());
    }

    @Test
    void testTransfertEchoue() {
        CompteBancaire destinataire = new CompteBancaire("CB002", "Bob", 500.0);
        assertFalse(compte.transfererVers(destinataire, 2000));
        assertEquals(1000.0, compte.getSolde());
        assertEquals(500.0, destinataire.getSolde());
    }
}

