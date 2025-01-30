package com.example.CRUD.Controller;

import com.example.CRUD.Model.Kolegij;
import com.example.CRUD.Model.Upis;
import com.example.CRUD.Repository.UpisRepository;
import com.example.CRUD.Repository.StudentRepository;
import com.example.CRUD.Repository.KolegijRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/upisi")
public class UpisController {

    @Autowired
    private UpisRepository upisRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private KolegijRepository kolegijRepository;

    @GetMapping
    public String sviUpisi(Model model) {
        model.addAttribute("upisi", upisRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("kolegiji", kolegijRepository.findAll());
        return "Upis";
    }

    @GetMapping("/{id}/edit")
    public String urediUpisForma(@PathVariable Long id, Model model) {
        Optional<Upis> upis = upisRepository.findById(id);
        if(upis.isEmpty()) {
            return "redirect:/upisi";
        }
        model.addAttribute("upis", upis.get());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("kolegiji", kolegijRepository.findAll());
        return "editUpis";
    }

    @PostMapping
    public String kreirajUpis(@ModelAttribute Upis upis) {
        upisRepository.save(upis);
        return "redirect:/upisi";
    }

    @PostMapping("/{id}")
    public String updateUpis(@PathVariable Long id, @RequestParam(required = false) Integer ocjena) {
        Upis upis = upisRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid upis ID"));

        upis.setOcjena(ocjena);

        upisRepository.save(upis);
        return "redirect:/upisi";
    }

    @DeleteMapping("/{id}/delete")
    public String obrisiUpis(@PathVariable Long id) {
        if (!upisRepository.existsById(id)) {
            return "redirect:/upisi?error=Upis+ne+postoji";
        }
        upisRepository.deleteById(id);
        return "redirect:/upisi";
    }

}