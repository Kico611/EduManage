package com.kristijanbalic.edumanage.controller;

import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.service.KolegijService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class KolegijController {

    private final KolegijService kolegijService;

    public KolegijController(KolegijService kolegijService) {
        this.kolegijService = kolegijService;
    }

    @GetMapping
    public String getKolegiji(Model model) {

        model.addAttribute("kolegiji", kolegijService.getAllKolegiji());
        model.addAttribute("professors", kolegijService.getAllProfesori());
        model.addAttribute("course", new Kolegij());

        return "Kolegij";
    }

    @PostMapping
    public String createCourse(
            @Valid @ModelAttribute("course") Kolegij course,
            BindingResult bindingResult,
            @RequestParam(name = "profesori", required = false)
            List<Long> profesori,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("kolegiji", kolegijService.getAllKolegiji());
            model.addAttribute("professors", kolegijService.getAllProfesori());

            return "Kolegij";
        }

        kolegijService.createKolegij(
                course,
                profesori != null ? profesori : List.of()
        );

        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String showEditCourseForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "course",
                kolegijService.getKolegijById(id)
        );

        model.addAttribute(
                "allProfessors",
                kolegijService.getAllProfesori()
        );

        return "editKolegij";
    }

    @PostMapping("/{id}")
    public String updateCourse(
            @PathVariable Long id,
            @Valid @ModelAttribute("course") Kolegij course,
            BindingResult bindingResult,
            @RequestParam(name = "profesori", required = false)
            List<Long> profesori,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(
                    "allProfessors",
                    kolegijService.getAllProfesori()
            );

            return "editKolegij";
        }

        kolegijService.updateKolegij(
                id,
                course,
                profesori != null ? profesori : List.of()
        );

        return "redirect:/courses";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {

        kolegijService.deleteKolegij(id);

        return "redirect:/courses";
    }
}