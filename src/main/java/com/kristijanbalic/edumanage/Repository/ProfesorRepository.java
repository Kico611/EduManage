package com.kristijanbalic.edumanage.Repository;

import com.kristijanbalic.edumanage.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
