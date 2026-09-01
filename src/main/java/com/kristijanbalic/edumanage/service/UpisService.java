package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.entity.Upis;
import com.kristijanbalic.edumanage.repository.KolegijRepository;
import com.kristijanbalic.edumanage.repository.StudentRepository;
import com.kristijanbalic.edumanage.repository.UpisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpisService {

    private final UpisRepository upisRepository;
    private final StudentRepository studentRepository;
    private final KolegijRepository kolegijRepository;

    public UpisService(UpisRepository upisRepository,
                       StudentRepository studentRepository,
                       KolegijRepository kolegijRepository) {
        this.upisRepository = upisRepository;
        this.studentRepository = studentRepository;
        this.kolegijRepository = kolegijRepository;
    }

    public List<Upis> getAllUpisi() {
        return upisRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Kolegij> getAllKolegiji() {
        return kolegijRepository.findAll();
    }

    public Upis getUpisById(Long id) {
        return upisRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid upis ID: " + id));
    }

    public Upis createUpis(Upis upis) {
        return upisRepository.save(upis);
    }

    public Upis updateOcjena(Long id, Integer ocjena) {

        Upis upis = getUpisById(id);

        upis.setOcjena(ocjena);

        return upisRepository.save(upis);
    }

    public void deleteUpis(Long id) {

        Upis upis = getUpisById(id);

        upisRepository.delete(upis);
    }
}