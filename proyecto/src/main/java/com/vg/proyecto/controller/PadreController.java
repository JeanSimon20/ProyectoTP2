package com.vg.proyecto.controller;

import com.vg.proyecto.model.Padre;
import com.vg.proyecto.service.PadreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/padres")
public class PadreController {

    @Autowired
    private PadreService padreService;

    @GetMapping
    public List<Padre> getAllPadres() {
        return padreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Padre> getPadreById(@PathVariable Long id) {
        Optional<Padre> padre = padreService.findById(id);
        return padre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Padre createPadre(@RequestBody Padre padre) {
        return padreService.save(padre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Padre> updatePadre(@PathVariable Long id, @RequestBody Padre padreDetails) {
        Optional<Padre> padre = padreService.findById(id);
        if (padre.isPresent()) {
            Padre padreToUpdate = padre.get();
            padreToUpdate.setNombre(padreDetails.getNombre());
            padreToUpdate.setApellido(padreDetails.getApellido());
            padreToUpdate.setEmail(padreDetails.getEmail());
            return ResponseEntity.ok(padreService.save(padreToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePadre(@PathVariable Long id) {
        if (padreService.findById(id).isPresent()) {
            padreService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
