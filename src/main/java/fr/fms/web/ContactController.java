package fr.fms.web;

import fr.fms.Entities.Contact;
import fr.fms.business.IntBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            else contacts = businessImpl.getArticlesPages(kw,page);

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


}
