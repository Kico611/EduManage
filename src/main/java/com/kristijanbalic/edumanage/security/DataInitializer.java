package com.kristijanbalic.edumanage.security;

import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.repository.StudentRepository;
import com.kristijanbalic.edumanage.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {

        // ADMIN
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }

        // PROFESSOR
        if (userRepository.findByUsername("professor").isEmpty()) {

            User professor = new User();
            professor.setUsername("professor");
            professor.setPassword(
                    passwordEncoder.encode("professor123")
            );
            professor.setRole(Role.PROFESSOR);

            userRepository.save(professor);
        }

        // STUDENT ACCOUNTS
        for (Student student : studentRepository.findAll()) {

            String username = student.getBrojIndeksa();

            User studentUser = userRepository
                    .findByUsername(username)
                    .orElseGet(() -> {

                        User newUser = new User();

                        newUser.setUsername(username);
                        newUser.setPassword(
                                passwordEncoder.encode("student123")
                        );
                        newUser.setRole(Role.STUDENT);

                        return newUser;
                    });

            studentUser.setStudent(student);
            studentUser.setRole(Role.STUDENT);

            userRepository.save(studentUser);
        }
    }
}