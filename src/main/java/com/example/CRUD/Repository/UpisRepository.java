package com.example.CRUD.Repository;
import com.example.CRUD.Model.Upis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UpisRepository extends JpaRepository<Upis, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Upis u WHERE u.student.id = :studentId")
    void deleteByStudentId(@Param("studentId") Long studentId);

    void deleteByKolegijId(Long id);
}

