package com.vg.proyecto.controller;

import com.vg.proyecto.model.Alumno;
import com.vg.proyecto.model.AlumnoDTO;
import com.vg.proyecto.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<AlumnoDTO> getAllAlumnos() {
        List<Alumno> alumnos = alumnoService.findAll();
        return alumnos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.findById(id);
        return alumno.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alumno createAlumno(@RequestBody Alumno alumno) {
        return alumnoService.save(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> alumno = alumnoService.findById(id);
        if (alumno.isPresent()) {
            Alumno alumnoToUpdate = alumno.get();
            alumnoToUpdate.setDni(alumnoDetails.getDni());
            alumnoToUpdate.setNombre(alumnoDetails.getNombre());
            alumnoToUpdate.setApellido(alumnoDetails.getApellido());
            alumnoToUpdate.setEmail(alumnoDetails.getEmail());
            alumnoToUpdate.setQrCode(alumnoDetails.getQrCode());
            alumnoToUpdate.setPadre(alumnoDetails.getPadre());
            return ResponseEntity.ok(alumnoService.save(alumnoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        if (alumnoService.findById(id).isPresent()) {
            alumnoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private AlumnoDTO convertToDTO(Alumno alumno) {
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setId(alumno.getId());
        alumnoDTO.setDni(alumno.getDni());
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellido(alumno.getApellido());
        alumnoDTO.setEmail(alumno.getEmail());
        alumnoDTO.setQrCode(alumno.getQrCode());
        alumnoDTO.setNombreCompletoPadre(alumno.getPadre().getNombre() + " " + alumno.getPadre().getApellido());
        return alumnoDTO;
    }

}
