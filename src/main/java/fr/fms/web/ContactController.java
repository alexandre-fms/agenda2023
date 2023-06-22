package fr.fms.web;

import fr.fms.Entities.Contact;
import fr.fms.business.IntBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ContactController {
    @Autowired
    IntBusinessImpl businessImpl;

    /**
     * Méthode en GET correspondant à l'url .../index (page d'accueil)
     * @param model sert à ajouter des éléments partagés avec la vue
     * @param page correspond à la page active côté front, cela assure la pagination
     * @param kw est un mot dont on souhaite afficher tous les articles le contenant
     * @param idCat est l'identifiant de la catégorie dont on souhaite afficher tous les articles / par défaut vaut 0
     * @return la page articles.html
     */
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
                        @RequestParam(name="keyword" , defaultValue = "") String kw,
                        @RequestParam(name="idCat" , defaultValue = "0") Long idCat){
        Page<Contact> contacts = null;
        model.addAttribute("error", model.getAttribute("error"));
        try {
            if(idCat > 0)	{
                contacts = businessImpl.getContactsByCategoriePage(idCat,page);
                //if(contacts.isEmpty())
                    //model.addAttribute("error", ManageErrors.STR_ERROR);
            }
            else contacts = businessImpl.getContactsPages(kw,page);

            model.addAttribute("idCat",idCat);
            model.addAttribute("listContact",contacts.getContent());
            model.addAttribute("pages", new int[contacts.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("keyword",kw);
            model.addAttribute("categories",businessImpl.getCatogries());

//            String username;
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (principal instanceof UserDetails) {
//                username = ((UserDetails)principal).getUsername();
//            } else {
//                username = principal.toString();
//                if(username.contains("anonymous"))
//                    username = "";
//            }
//            model.addAttribute("username", " " +username);
        }
        catch(Exception e) {
            model.addAttribute("error",e.getMessage());
        }
        return "Contacts";
    }

    /**
     * Méthode en GET correspondant à l'url .../delete permettant de supprimer un article à partir de son id
     * @param id de l'article
     * @param page la page active
     * @param keyword le mot clé s'il existe
     * @param idCat l'identifiant de la catégorie
     * @param redirectAttrs sert à injecter un paramètre dans la redirection, delors ../index pourra le récupérer via le modelAttribute
     * @return une redirection vers ../index avec les éléments permettant de garder le contexte actuel
     */
    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword , Long idCat, RedirectAttributes redirectAttrs) {
        try {
            businessImpl.deleteContact(id);
        } catch (Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
        }
        return "redirect:/index?page="+page+"&keyword="+keyword + "&idCat=" + idCat;
    }

    /**
     * Méthode en GET correspondant à l'url .../edit permettant d'afficher un article en vue de sa mise à jour
     * @param id de l'article
     * @param model sert à ajouter des éléments partagés avec la vue
     * @return renvoie vers la page .../edit du contact à modifier
     */
    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        Contact contact;
        try {
            contact = businessImpl.getContactById(id);
            model.addAttribute("categories",businessImpl.getCatogries());
            model.addAttribute("contact", contact);
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
        }
        return "edit";
    }

    /**
     * Méthode en POST permettant l'enregistrement des modifications ou de la création de contacts
     * @param contact l'objet contact à enregistrer
     * @param bindingResult sert à la gestion des problèmes de saisie non conforme
     * @param model
     * @param redirectAttrs
     * @return une fois le contact sauvegardé sans erreur, redirection sur la page d'accueil (../index)
     */
    @PostMapping("/save")
    public String save(@Valid Contact contact, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("categories",businessImpl.getCatogries());
                return "contact";
            }
            businessImpl.saveContact(contact);
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
        }
        return "redirect:/index";
    }

    /**
     * Méthode en Get redirigeant vers l'url ../contact permettant de créer un nouveau contact.
     * @param model
     * @return renvoie la page contact
     */
    @GetMapping("/contact")
    public String contact(Model model){

        model.addAttribute("contact" , new Contact());
        try {
            model.addAttribute("categories",businessImpl.getCatogries());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());

        }

        return "contact";
    }

}
