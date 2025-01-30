package com.example.CRUD.Controller;
import java.util.stream.Collectors;
import com.example.CRUD.Model.Kolegij;
import com.example.CRUD.Model.Profesor;
import com.example.CRUD.Repository.KolegijRepository;
import com.example.CRUD.Repository.ProfesorRepository;
import com.example.CRUD.Repository.UpisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class KolegijController {

    @Autowired
    private KolegijRepository kolegijRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private UpisRepository upisRepository;

    @GetMapping
    public String getKolegiji(Model model) {
        List<Kolegij> kolegiji = kolegijRepository.findAll();
        List<Profesor> profesori = profesorRepository.findAll();

        model.addAttribute("kolegiji", kolegiji);
        model.addAttribute("professors", profesori);
        return "Kolegij";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Kolegij course) {
        kolegijRepository.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String showEditCourseForm(@PathVariable Long id, Model model) {
        System.out.println("Accessing edit form for course with id: " + id);
        Kolegij course = kolegijRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<Profesor> allProfessors = profesorRepository.findAll();
        model.addAttribute("course", course);
        model.addAttribute("allProfessors", allProfessors);
        return "editKolegij";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute Kolegij course,
                               @RequestParam List<Long> profesori) {

        System.out.println("Updating course with id: " + id);
        Kolegij existingCourse = kolegijRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setNaziv(course.getNaziv());

        List<Profesor> selectedProfessors = profesorRepository.findAllById(profesori);
        existingCourse.setProfesori(selectedProfessors);

        kolegijRepository.save(existingCourse);
        return "redirect:/courses";
    }

    @Transactional
    @DeleteMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {

        Kolegij kolegij = kolegijRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        for (Profesor profesor : kolegij.getProfesori()) {
            profesor.getKolegiji().remove(kolegij);
            profesorRepository.save(profesor);
        }

        upisRepository.deleteByKolegijId(id);
        kolegijRepository.delete(kolegij);

        return "redirect:/courses";
    }

}
