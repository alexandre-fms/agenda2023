package fr.fms.business;

import fr.fms.Entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IntBusiness {
    public Page<Contact> getContactsByCategoriePage(Long idCat, int page) throws Exception;

    Page<Contact> getArticlesPages(String kw, int page);

    Object getCatogries();
}
