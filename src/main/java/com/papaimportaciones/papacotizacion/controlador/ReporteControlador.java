package com.papaimportaciones.papacotizacion.controlador;

import com.papaimportaciones.papacotizacion.reportes.ReporteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReporteControlador {

    private ReporteService reporteService;

    public ReporteControlador(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generateReporte(){

        try {
            byte[] reporte= reporteService.generarReporte("practica");
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=cotizacion.pdf");

            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);

        }catch ( Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
