/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.controller;

import galerie.dao.ArtisteRepository;
import galerie.dao.TableauRepository;
import galerie.entity.Tableau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tina Nantenaina
 */

@Controller
@RequestMapping(path="/tableau")
public class TableauController {
    @Autowired
    private TableauRepository dao;
    @Autowired
    private ArtisteRepository daoA;
    
    @GetMapping(path = "show")
    public String afficheLeTableau(Model model){
        model.addAttribute("tableaux", dao.findAll());
        return "afficheTableaux";
    }

    /**
     * Ajouter tableau par formulaire
     * @param tableau
     * @return nom de la vue "formulaireTableau"
     */
    @GetMapping(path = "add")
    public String afficheFormulaireAjoutTableau(@ModelAttribute("tableau")Tableau tableau, Model model){
        model.addAttribute("artistes", daoA.findAll());
        return "formulaireTableau";
    }
    
    /**
     * Enregistrer et afficher le titre de tableau ajouter
     * @param tableau
     * @param redirectInfo
     * @return une redirection vers l'affichage du tableau
     */
    @PostMapping(path ="save")
    public String ajouteTableauAfficheListe(Tableau tableau, RedirectAttributes redirectInfo ){
        String message;
        try{
            dao.save(tableau);
            message = "Le tableau '"+ tableau.getTitre()+" ' a été correctement enregistré";
        }catch (DataIntegrityViolationException e){
            message = "Erreur : le tableau '"+tableau.getTitre()+"' existe déja";   
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show";
    }
    
    @GetMapping(path = "delete")
    public String supprimerTableauAfficherListe(@RequestParam("id") Tableau tableau, RedirectAttributes redirectInfo){
        String message = "Le tableau '" + tableau.getTitre()+"' a été supprimé";
        try{
            dao.delete(tableau);
        }catch (DataIntegrityViolationException e){
            message = "Erreur : impossible de supprimer le tableau" + tableau.getTitre();
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show";
    }
    
    
}
