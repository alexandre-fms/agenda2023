package fr.fms;

import fr.fms.Entities.Category;
import fr.fms.Entities.Contact;
import fr.fms.business.IntBusinessImpl;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Agenda2023Application implements CommandLineRunner {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    IntBusinessImpl businessImpl;

    public static void main(String[] args) {
        SpringApplication.run(Agenda2023Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataGenerator();
    }

    private void dataGenerator() {

        Category friend = categoryRepository.save(new Category(null, "Amis", null));
        Category job = categoryRepository.save(new Category(null, "Travail", null));
        Category family = categoryRepository.save(new Category(null, "Famille", null));
        Category spam = categoryRepository.save(new Category(null, "Spam", null));
        Category pc = categoryRepository.save(new Category(null, "Réserve", null));

        contactRepository.save(new Contact(null, "Martin", "rémi", "123 rue dfghjkloiuyt", "1478523698", "remi.azerty@gmail.com", job));
        contactRepository.save(new Contact(null, "Boucon", "Roger", "321 rue des iygbn,kuyg", "9874563214", "jesuispersonne@gmail.com", spam));
        contactRepository.save(new Contact(null, "Khalifa", "Mia", "inconnue", "maqué", "", pc));
        contactRepository.save(new Contact(null, "Phillips", "Lauren", "", "", "", pc));
        contactRepository.save(new Contact(null, "Artois", "Stella", "Belgique", "4563145632", "", friend));
        contactRepository.save(new Contact(null, "arenas", "alexandre", "chez lui", "7410852963", "alexandre.arenas@fms-ea.com", family));
        contactRepository.save(new Contact(null, "Roche", "Ludivine", "cmp de pau", "privé", "", friend));
        contactRepository.save(new Contact(null, "goo", "gle", "www.google.fr", "", "contact@google.com", friend));
        contactRepository.save(new Contact(null, "Kennedy", "jhon", "Whasington DC", "555-7458", "jfk@deadpresident.com", spam));
        contactRepository.save(new Contact(null, "Turner", "Tina", "heaven", "000-0004", "tina@turner.fr", job));
        contactRepository.save(new Contact(null, "Jackson", "michel", "somewhere in earth", "", "", spam));
        contactRepository.save(new Contact(null, "Sardou", "mickael", "187 code marseille", "0147852369", "sardou@chnateur.fr", friend));
        contactRepository.save(new Contact(null, "Mozart", "Wolfang Amadeus EX MACHINA", "Autriche", "connait pas", "ne sait pas ce que c'est", friend));
        contactRepository.save(new Contact(null, "Brassens", "Georges", "heaven", "03698741212", "brassensRox@gmail.com", job));
        contactRepository.save(new Contact(null, "MorningStar", "Lucifer", "Les enfers", "666-666", "Lucifer@hell.fr", job));
        contactRepository.save(new Contact(null, "Christ", "Jésus", "quelque part dans les nuages", "070707070707", "jesus@redempteur.com", job));
        contactRepository.save(new Contact(null, "voisine", "La", "à côté de chez moi", "", "", pc));
        contactRepository.save(new Contact(null, "la caissière", "du magasin", "auchan", "", "", pc));
//        contactRepository.save(new Contact(null, "", "", "", "", "", ));
//        contactRepository.save(new Contact(null, "", "", "", "", "", ));
//        contactRepository.save(new Contact(null, "", "", "", "", "", ));
//        contactRepository.save(new Contact(null, "", "", "", "", "", ));



    }
}
