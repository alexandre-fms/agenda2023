package fr.fms.business;

import fr.fms.Entities.Contact;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class IntBusinessImpl implements IntBusiness{

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Contact> getContactsByCategoriePage(Long idCat, int page) {
        return contactRepository.findByCategoryId(idCat, PageRequest.of(page, 5));
    }

    @Override
    public Page<Contact> getArticlesPages(String kw, int page) {
        return contactRepository.findByFirstnameContains(kw , PageRequest.of(page, 5));
    }

    @Override
    public Object getCatogries() {
        return categoryRepository.findAll();
    }
}
