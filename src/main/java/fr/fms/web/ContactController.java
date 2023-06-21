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
            //logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
        }




        return "Contacts";
    }


    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword , Long idCat, RedirectAttributes redirectAttrs) {
        try {
            businessImpl.deleteContact(id);
        } catch (Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            //logger.error("[ARTICLE CONTROLLER : DELETE] : {} " , e.getMessage());
        }
        return "redirect:/index?page="+page+"&keyword="+keyword + "&idCat=" + idCat;
    }


    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        Contact contact;
        try {
            contact = businessImpl.getContactById(id);
            model.addAttribute("categories",businessImpl.getCatogries());
            model.addAttribute("contact", contact);
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            //logger.error("[ARTICLE CONTROLLER : EDIT] : {} " , e.getMessage());
        }
        return "edit";
    }

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
            //logger.error("[ARTICLE CONTROLLER : SAVE ARTICLE] : {} " , e.getMessage());
        }
        return "redirect:/index";
    }


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
