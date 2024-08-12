package com.vg.proyecto.repository;

import com.vg.proyecto.model.Padre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PadreRepository extends JpaRepository<Padre, Long> {
}
