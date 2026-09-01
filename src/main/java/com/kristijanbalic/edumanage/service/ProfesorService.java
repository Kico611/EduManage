package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.entity.Profesor;
import com.kristijanbalic.edumanage.repository.KolegijRepository;
import com.kristijanbalic.edumanage.repository.ProfesorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final KolegijRepository kolegijRepository;

    public ProfesorService(ProfesorRepository profesorRepository,
                           KolegijRepository kolegijRepository) {
        this.profesorRepository = profesorRepository;
        this.kolegijRepository = kolegijRepository;
    }

    public List<Profesor> getAllProfesori() {
        List<Profesor> profesori = profesorRepository.findAll();

        for (Profesor profesor : profesori) {
            List<String> kolegijiImena = profesor.getKolegiji().stream()
                    .map(Kolegij::getNaziv)
                    .collect(Collectors.toList());

            profesor.setKolegijiImena(String.join(", ", kolegijiImena));
        }

        return profesori;
    }

    public Profesor getProfesorById(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid profesor ID: " + id));
    }

    public List<Kolegij> getAllKolegiji() {
        return kolegijRepository.findAll();
    }

    @Transactional
    public Profesor saveProfesor(Profesor profesor, List<Long> kolegijiIds) {

        List<Kolegij> kolegiji = kolegijiIds != null
                ? kolegijRepository.findAllById(kolegijiIds)
                : List.of();

        profesor.setKolegiji(kolegiji);

        for (Kolegij kolegij : kolegiji) {
            kolegij.getProfesori().add(profesor);
        }

        return profesorRepository.save(profesor);
    }

    public Profesor updateProfesor(Long id, Profesor profesor) {

        Profesor existingProfesor = getProfesorById(id);

        existingProfesor.setIme(profesor.getIme());
        existingProfesor.setPrezime(profesor.getPrezime());

        return profesorRepository.save(existingProfesor);
    }

    @Transactional
    public void deleteProfesor(Long id) {

        Profesor profesor = getProfesorById(id);

        for (Kolegij kolegij : profesor.getKolegiji()) {
            kolegij.getProfesori().remove(profesor);
        }

        profesorRepository.delete(profesor);
    }
}