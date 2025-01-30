package com.example.CRUD.Repository;

import com.example.CRUD.Model.Kolegij;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolegijRepository extends JpaRepository<Kolegij, Long> {
}
