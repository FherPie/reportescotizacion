package com.papaimportaciones.papacotizacion.controlador;

import com.papaimportaciones.papacotizacion.reportes.ReporteService;
import jakarta.persistence.Entity;
import jakarta.websocket.server.PathParam;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReporteControlador {



    private static final Logger log = LoggerFactory.getLogger(ReporteControlador.class);
    private ReporteService reporteService;

    public ReporteControlador(ReporteService reporteService) {
        this.reporteService = reporteService;

    }

    @GetMapping("/reporte/{idcoti}")
    public ResponseEntity<byte[]> generateReporte(@PathVariable String idcoti){
        try {
            byte[] reporte= reporteService.generarReporte("cotizacion",idcoti);
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=cotizacion.pdf");

            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);

        }catch ( Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping("/makecalculus")
    public ResponseEntity<Integer> makecalculus(@RequestBody DtoPresu dtoPresu){
        try {
            String reporte= reporteService.makecalculus(dtoPresu);
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=cotizacion.pdf");

            return new ResponseEntity<>(1, headers, HttpStatus.OK);

        }catch ( Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class DtoPresu {
        double cantidadMetros = 0.0;
        double altura = 0.0;
        String tipoCerca = "";
        String nombrecliente = "";
        String telefonoCliente = "";
        Boolean instalacion= false;


        public DtoPresu(double cantidadMetros) {
            this.cantidadMetros = cantidadMetros;
        }

        public double getCantidadMetros() {
            return cantidadMetros;
        }

        public void setCantidadMetros(double cantidadMetros) {
            this.cantidadMetros = cantidadMetros;
        }
    }
}
