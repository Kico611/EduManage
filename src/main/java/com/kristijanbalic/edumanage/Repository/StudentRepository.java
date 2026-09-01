package com.kristijanbalic.edumanage.Repository;

import com.kristijanbalic.edumanage.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
