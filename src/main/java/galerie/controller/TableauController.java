/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.controller;

import galerie.dao.TableauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tina Nantenaina
 */

@Controller
@RequestMapping(path="/tableau")
public class TableauController {
    @Autowired
    private TableauRepository dao;
    
    @GetMapping(path = "show")
    public String afficheLeTableau(Model model){
        model.addAttribute("tableaux", dao.findAll());
        return "afficheTableaux";
    }
}
