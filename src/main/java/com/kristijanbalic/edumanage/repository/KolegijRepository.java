package com.kristijanbalic.edumanage.repository;

import com.kristijanbalic.edumanage.entity.Kolegij;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolegijRepository extends JpaRepository<Kolegij, Long> {
}
