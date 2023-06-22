package fr.fms.business;

import fr.fms.Entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IntBusiness {
    /**
     * Methode qui renvoie une liste de contact par catégorie
     * @param idCat l'identifiant de la catégorie
     * @param page le nombre de page correspondant
     * @return renvoie une lisrte de contact
     * @throws Exception
     */
    public Page<Contact> getContactsByCategoriePage(Long idCat, int page) throws Exception;

    /**
     * Méthode qui renvoie une liste de contact depuis un mot clé
     * @param kw le mot clé de la recherche
     * @param page le nombre de page correspondant
     * @return renvoie une liste de contact
     * @throws Exception
     */
    public Page<Contact> getContactsPages(String kw, int page) throws Exception;

    /**
     * Méthode qui renvoie toutes les catégories
     * @return renvoie toutes les catégories
     */
    Object getCatogries();

    /**
     *Méthode servant à supprimer un contact en base de données
     * @param id l'identifiant du contact à supprimer
     */
    void deleteContact(Long id);

    /**
     *Méthode permettant de sélectionner un contact via son identifiant
     * @param id l'identifiant du contact
     * @return renvoie un contact (objet)
     */
    Contact getContactById(Long id);

    /**
     *Méthode permettant de sauvegarder les informations modifiées ou créées sur un contact
     * @param contact l'objet contact à enregistrer
     */
    void saveContact(Contact contact);
}
