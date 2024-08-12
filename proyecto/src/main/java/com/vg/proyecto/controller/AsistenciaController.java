package com.vg.proyecto.controller;

import com.vg.proyecto.model.Asistencia;
import com.vg.proyecto.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping("/registrar")
    public ResponseEntity<Asistencia> registrarAsistencia(@RequestBody Map<String, String> payload) {
        String qrCode = payload.get("qrCode");
        Asistencia asistencia = asistenciaService.registrarAsistencia(qrCode);

        if (asistencia != null) {
            return ResponseEntity.ok(asistencia);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
