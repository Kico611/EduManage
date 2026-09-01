package com.kristijanbalic.edumanage.controller;

import com.kristijanbalic.edumanage.entity.Upis;
import com.kristijanbalic.edumanage.service.UpisService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/upisi")
public class UpisController {

    private final UpisService upisService;

    public UpisController(UpisService upisService) {
        this.upisService = upisService;
    }

    @GetMapping
    public String sviUpisi(Model model) {

        model.addAttribute("upisi", upisService.getAllUpisi());
        model.addAttribute("students", upisService.getAllStudents());
        model.addAttribute("kolegiji", upisService.getAllKolegiji());
        model.addAttribute("upis", new Upis());

        return "Upis";
    }

    @GetMapping("/{id}/edit")
    public String urediUpisForma(@PathVariable Long id, Model model) {

        model.addAttribute("upis", upisService.getUpisById(id));
        model.addAttribute("students", upisService.getAllStudents());
        model.addAttribute("kolegiji", upisService.getAllKolegiji());

        return "editUpis";
    }

    @PostMapping
    public String kreirajUpis(
            @Valid @ModelAttribute("upis") Upis upis,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("upisi", upisService.getAllUpisi());
            model.addAttribute("students", upisService.getAllStudents());
            model.addAttribute("kolegiji", upisService.getAllKolegiji());

            return "Upis";
        }

        upisService.createUpis(upis);

        return "redirect:/upisi";
    }

    @PostMapping("/{id}")
    public String updateUpis(
            @PathVariable Long id,
            @RequestParam(required = false) Integer ocjena) {

        upisService.updateOcjena(id, ocjena);

        return "redirect:/upisi";
    }

    @DeleteMapping("/{id}/delete")
    public String obrisiUpis(@PathVariable Long id) {

        upisService.deleteUpis(id);

        return "redirect:/upisi";
    }
}