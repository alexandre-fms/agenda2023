package fr.fms.business;

import fr.fms.Entities.Contact;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Page<Contact> getContactsPages(String kw, int page) {
        return contactRepository.findByNameContains(kw , PageRequest.of(page, 5));
    }

    @Override
    public Object getCatogries() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public Contact getContactById(Long id) {
        Optional<Contact> optional = contactRepository.findById(id);
        return optional.get();
        //return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

}
