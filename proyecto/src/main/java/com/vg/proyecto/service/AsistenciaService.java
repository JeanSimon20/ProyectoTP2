package com.vg.proyecto.service;
import com.vg.proyecto.model.Alumno;
import com.vg.proyecto.model.Asistencia;
import com.vg.proyecto.repository.AlumnoRepository;
import com.vg.proyecto.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;


    @Autowired
    private EmailService emailService;

    public Asistencia registrarAsistencia(String qrCode) {
        Optional<Alumno> alumnoOpt = alumnoRepository.findAll()
                .stream()
                .filter(alumno -> alumno.getQrCode().equals(qrCode))
                .findFirst();

        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();

            Asistencia asistencia = new Asistencia();
            asistencia.setAlumno(alumno);
            asistencia.setFecha(LocalDateTime.now());
            asistencia.setEstado(true);

            Asistencia asistenciaGuardada = asistenciaRepository.save(asistencia);

            String subject = "Asistencia Registrada: " + alumno.getNombre() + " " + alumno.getApellido();
            String text = "Estimado " + alumno.getPadre().getNombre() + " " + alumno.getPadre().getApellido() + ",\n\n" +
                    "Le informamos que su hijo " + alumno.getNombre() + " " + alumno.getApellido() + " ha registrado su asistencia el " +
                    asistencia.getFecha() + ".\n\n" +
                    "Saludos,\nEl equipo de la escuela.";

            emailService.sendSimpleEmail(alumno.getPadre().getEmail(), subject, text);

            return asistenciaGuardada;
        } else {
            return null;
        }
    }

}
