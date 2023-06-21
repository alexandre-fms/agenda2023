package fr.fms.business;

import fr.fms.Entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IntBusiness {
    public Page<Contact> getContactsByCategoriePage(Long idCat, int page) throws Exception;

    public Page<Contact> getContactsPages(String kw, int page) throws Exception;

    Object getCatogries();

    void deleteContact(Long id);

    Contact getContactById(Long id);

    void saveContact(Contact contact);
}
