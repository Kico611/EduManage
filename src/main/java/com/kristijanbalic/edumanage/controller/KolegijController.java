package com.kristijanbalic.edumanage.controller;

import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.service.KolegijService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        model.addAttribute("kolegiji",
                kolegijService.getAllKolegiji());

        model.addAttribute("professors",
                kolegijService.getAllProfesori());

        return "Kolegij";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Kolegij course) {

        kolegijService.createKolegij(course);

        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String showEditCourseForm(@PathVariable Long id,
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
            @ModelAttribute Kolegij course,
            @RequestParam List<Long> profesori) {

        kolegijService.updateKolegij(id, course, profesori);

        return "redirect:/courses";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {

        kolegijService.deleteKolegij(id);

        return "redirect:/courses";
    }
}