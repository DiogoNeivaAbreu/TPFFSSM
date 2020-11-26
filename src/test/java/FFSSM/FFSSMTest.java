/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Asus
 */
public class FFSSMTest {

    Plongeur p;
    Embauche e;
    Moniteur m, pre;
    Club c;
    Licence li;
    Plongeur pl;

    @BeforeEach
    public void setUp() {
        m = new Moniteur("n°insee", "teur", "moni", "adresse", "tel", LocalDate.of(1989, 12, 17), 195);
        pre = new Moniteur("n°insee", "dent", "presi", "adresse", "tel", LocalDate.of(1989, 12, 17), 195);
        c = new Club(pre, "c", "n°tel");
        p = new Plongeur("123", "untel", "Pierre", "Maison", "0612345678", LocalDate.of(1992, 11, 10), 3);
        e = new Embauche(LocalDate.of(1999, 9, 1), m, c);
        pl = new Plongeur("num", "geur", "plon", "eau", "tel", LocalDate.of(2000, 01, 20), 2);
        li = new Licence(pl, "345", LocalDate.of(2020, 11, 25), 2, c);
    }

    /**
     * Test of employeurActuel and nouvelleEmbauche methods, of class Moniteur.
     */
    @Test
    public void testEmployeurActuelNouvelleEmbauche() {
        // Le moniteur m n'a pas d'mployeur pour l'instant
        assertEquals(Optional.empty(), m.employeurActuel());
        // On ajoute un employeur pour le moniteur m
        m.nouvelleEmbauche(c, LocalDate.of(1999, 9, 1));
        // Le moniteur m possède maintenant un employeur c
        assertEquals(Optional.ofNullable(c), m.employeurActuel());

    }

    /**
     * Test of estConforme method, of class Plongee.
     */
    @Test
    public void testEstConforme() {
        // On crée un site pour qu'une plongée ait lieu 
        Site s = new Site("plonge", "pro");
        Plongee plo = new Plongee(s, m, LocalDate.now(), 10, 1);
        // On ajoute un plongeur à la plongée
        plo.ajouteParticipant(pl);
        // On vérifie si la plongée est conforme
        assertEquals(false, plo.estConforme());
        // On ajoute une licence au plongeur 
        pl.ajouteLicence(pl, "345", LocalDate.of(2020, 11, 01), c);
        // La plongée est maintenant conforme
        assertEquals(true, plo.estConforme());

    }
    
    /**
    * Test of plongeeNonConforme method, of class Club.
    */
    @Test
    public void testPlongeeNonConforme() {
        // On crée un site pour qu'une plongée ait lieu
        Site s = new Site("plonge", "pro");
        Plongee plo = new Plongee(s, m, LocalDate.now(), 10, 1);
        // On ajoute un plongeur à la plongée sans licence
        plo.ajouteParticipant(pl);
        // On organise la plongée plo pour le club c
        c.organisePlongee(plo);
        // La plongee n'est pas conforme 
        assertEquals(1, c.plongeesNonConformes().size());
    }
        
        
    /**
     * Test of emplois method, of class Moniteur.
     * Test of terminer method, of class Embauche.
     */
    @Test
    public void testEmploisTerminer() throws Exception{
        // On ajoute une nouvelle embauche à la liste des emplois
        m.myEmplois.add(e);
        // On termine cette embauche
        e.terminer(LocalDate.now());
        // on vérifie qu'on ne peut pas terminer encore l'embauche
        try {
            // La ligne ci-dessous DOIT lever une exception
            e.terminer(LocalDate.now());
            // Si on arrive ici, c'est une erreur
            // On force l'échec du test
            fail();
        } catch (Exception ex) {
            // Si on arrive ici c'est normal, c'est ce qu'on veut
        }

        // On crée une nouvelle embauche 
        m.nouvelleEmbauche(c, LocalDate.of(1999, 9, 1));

        // On vérifie que le moniteur ne possède que 2 embauches
        assertEquals(2, m.emplois().size());
    }
    
    

}
