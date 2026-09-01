package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.entity.Profesor;
import com.kristijanbalic.edumanage.repository.KolegijRepository;
import com.kristijanbalic.edumanage.repository.ProfesorRepository;
import com.kristijanbalic.edumanage.repository.UpisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kristijanbalic.edumanage.exception.KolegijNotFoundException;

import java.util.List;

@Service
public class KolegijService {

    private final KolegijRepository kolegijRepository;
    private final ProfesorRepository profesorRepository;
    private final UpisRepository upisRepository;

    public KolegijService(KolegijRepository kolegijRepository,
                          ProfesorRepository profesorRepository,
                          UpisRepository upisRepository) {
        this.kolegijRepository = kolegijRepository;
        this.profesorRepository = profesorRepository;
        this.upisRepository = upisRepository;
    }

    public List<Kolegij> getAllKolegiji() {
        return kolegijRepository.findAll();
    }

    public Kolegij getKolegijById(Long id) {
        return kolegijRepository.findById(id)
                .orElseThrow(() -> new KolegijNotFoundException(id));
    }

    public List<Profesor> getAllProfesori() {
        return profesorRepository.findAll();
    }

    @Transactional
    public Kolegij createKolegij(Kolegij kolegij,
                                 List<Long> profesoriIds) {

        List<Profesor> profesori =
                profesorRepository.findAllById(profesoriIds);

        kolegij.setProfesori(profesori);

        return kolegijRepository.save(kolegij);
    }

    @Transactional
    public Kolegij updateKolegij(Long id,
                                 Kolegij kolegij,
                                 List<Long> profesoriIds) {

        Kolegij existingKolegij = getKolegijById(id);

        existingKolegij.setNaziv(kolegij.getNaziv());

        List<Profesor> selectedProfessors =
                profesorRepository.findAllById(profesoriIds);

        existingKolegij.setProfesori(selectedProfessors);

        return kolegijRepository.save(existingKolegij);
    }

    @Transactional
    public void deleteKolegij(Long id) {

        Kolegij kolegij = getKolegijById(id);

        for (Profesor profesor : kolegij.getProfesori()) {
            profesor.getKolegiji().remove(kolegij);
        }

        upisRepository.deleteByKolegijId(id);
        kolegijRepository.delete(kolegij);
    }
}