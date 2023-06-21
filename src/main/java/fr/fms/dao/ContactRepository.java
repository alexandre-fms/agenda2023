package fr.fms.dao;

import fr.fms.Entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    public Page<Contact> findByCategoryId(Long idCat, Pageable pageable);

    public Page<Contact> findByFirstnameContains(String kw, Pageable pageable);
}
