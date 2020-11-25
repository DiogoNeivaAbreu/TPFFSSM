/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;

    public List<Embauche> myEmplois = new ArrayList<>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, numeroDiplome);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est
     * terminée, ce moniteur n'a pas d'employeur.
     *
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
        // TODO: Implémenter cette méthode
        Club result;
        if (myEmplois.isEmpty() || myEmplois.get(myEmplois.size()-1).estTerminee()) {
            return Optional.empty();
        }
        result = myEmplois.get(myEmplois.size()-1).getEmployeur();
        return Optional.ofNullable(result);
    }

    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     *
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {
        // TODO: Implémenter cette méthode
        Embauche e = new Embauche(debutNouvelle, this, employeur);
        myEmplois.add(e);
    }

    public List<Embauche> emplois() {
        // TODO: Implémenter cette méthode
        return myEmplois;
    }

}
