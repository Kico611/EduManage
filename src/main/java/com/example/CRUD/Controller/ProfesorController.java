package com.example.CRUD.Controller;

import com.example.CRUD.Model.Kolegij;
import com.example.CRUD.Model.Profesor;
import com.example.CRUD.Repository.ProfesorRepository;
import com.example.CRUD.Repository.KolegijRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profesors")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private KolegijRepository kolegijRepository;

    @GetMapping
    public String getProfesori(Model model) {
        List<Profesor> profesori = profesorRepository.findAll();
        List<Kolegij> kolegiji = kolegijRepository.findAll();

        for (Profesor profesor : profesori) {
            List<String> kolegijiImena = profesor.getKolegiji().stream()
                    .map(Kolegij::getNaziv)
                    .collect(Collectors.toList());
            profesor.setKolegijiImena(String.join(", ", kolegijiImena));
        }

        model.addAttribute("profesori", profesori);
        model.addAttribute("kolegiji", kolegiji);
        return "Profesor";
    }

    @GetMapping("/new")
    public String showAddProfesorForm(Model model) {
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("kolegiji", kolegijRepository.findAll());
        return "Profesor";
    }

    @PostMapping
    public String saveProfesor(@ModelAttribute Profesor profesor,
                               @RequestParam(name = "kolegiji", required = false) List<Long> kolegijiIds) {
        List<Kolegij> kolegijiList = (kolegijiIds != null) ? kolegijRepository.findAllById(kolegijiIds) : List.of();

        for (Kolegij kolegij : kolegijiList) {
            kolegij.getProfesori().add(profesor);
        }

        profesor.setKolegiji(kolegijiList);
        profesorRepository.save(profesor);
        return "redirect:/profesors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profesor ID: " + id));
        model.addAttribute("profesor", profesor);
        model.addAttribute("kolegiji", kolegijRepository.findAll());
        return "editProfesor";
    }

    @PostMapping("/{id}/edit")
    public String updateProfesor(@PathVariable Long id, @ModelAttribute Profesor profesor) {
        Profesor existingProfesor = profesorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profesor ID: " + id));

        existingProfesor.setIme(profesor.getIme());
        existingProfesor.setPrezime(profesor.getPrezime());

        profesorRepository.save(existingProfesor);
        return "redirect:/profesors";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProfesor(@PathVariable Long id) {

        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor not found"));

        for (Kolegij kolegij : profesor.getKolegiji()) {
            kolegij.getProfesori().remove(profesor);
            kolegijRepository.save(kolegij);
        }

        profesorRepository.delete(profesor);

        return "redirect:/profesors";
    }

}
