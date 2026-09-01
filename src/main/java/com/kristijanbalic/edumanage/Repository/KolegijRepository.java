package com.kristijanbalic.edumanage.Repository;

import com.kristijanbalic.edumanage.Model.Kolegij;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolegijRepository extends JpaRepository<Kolegij, Long> {
}
