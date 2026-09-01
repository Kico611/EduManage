package com.kristijanbalic.edumanage.controller;

import com.kristijanbalic.edumanage.entity.Profesor;
import com.kristijanbalic.edumanage.service.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profesors")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public String getProfesori(Model model) {

        model.addAttribute("profesori", profesorService.getAllProfesori());
        model.addAttribute("kolegiji", profesorService.getAllKolegiji());

        return "Profesor";
    }

    @GetMapping("/new")
    public String showAddProfesorForm(Model model) {

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("kolegiji", profesorService.getAllKolegiji());

        return "Profesor";
    }

    @PostMapping
    public String saveProfesor(
            @ModelAttribute Profesor profesor,
            @RequestParam(name = "kolegiji", required = false)
            List<Long> kolegijiIds) {

        profesorService.saveProfesor(profesor, kolegijiIds);

        return "redirect:/profesors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        model.addAttribute(
                "profesor",
                profesorService.getProfesorById(id)
        );

        model.addAttribute(
                "kolegiji",
                profesorService.getAllKolegiji()
        );

        return "editProfesor";
    }

    @PostMapping("/{id}/edit")
    public String updateProfesor(
            @PathVariable Long id,
            @ModelAttribute Profesor profesor) {

        profesorService.updateProfesor(id, profesor);

        return "redirect:/profesors";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProfesor(@PathVariable Long id) {

        profesorService.deleteProfesor(id);

        return "redirect:/profesors";
    }
}