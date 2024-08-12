package com.vg.proyecto.service;

import com.vg.proyecto.model.Padre;
import com.vg.proyecto.repository.PadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PadreService {

    @Autowired
    private PadreRepository padreRepository;

    public List<Padre> findAll() {
        return padreRepository.findAll();
    }

    public Optional<Padre> findById(Long id) {
        return padreRepository.findById(id);
    }

    public Padre save(Padre padre) {
        return padreRepository.save(padre);
    }

    public void deleteById(Long id) {
        padreRepository.deleteById(id);
    }

}
