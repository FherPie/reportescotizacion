package com.papaimportaciones.papacotizacion.reportes;

import com.papaimportaciones.papacotizacion.entidades.Cabecera;
import com.papaimportaciones.papacotizacion.servicios.CotizacionReporteServicio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

    private CotizacionReporteServicio cotizacionReporteServicio;


    public ReporteService(CotizacionReporteServicio cotizacionReporteServicio) {
        this.cotizacionReporteServicio = cotizacionReporteServicio;
    }

    public byte [] generarReporte(String reportName) throws JRException {

       //LLamar información
        Cabecera cabecera= cotizacionReporteServicio.getParentWithChildren("123");

        Map<String, Object> parametros= new HashMap<>();
        parametros.put("nombreCliente", cabecera.getNombre());
        parametros.put("metrosLineales", cabecera.getDescripcion());
        parametros.put("fecha", new SimpleDateFormat("MM-dd-yyyy").format(new Date()));

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(cabecera.getChildren());

        //Cargar el reporte
        InputStream reporteStream = this. getClass().getResourceAsStream("/reportes/"+reportName+".jasper");

        //llenado
        JasperPrint jasperPrint= JasperFillManager.fillReport(reporteStream, parametros, beanColDataSource);

        //Exportación en PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
