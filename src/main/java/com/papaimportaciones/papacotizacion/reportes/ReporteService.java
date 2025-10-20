package com.papaimportaciones.papacotizacion.reportes;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

@Service
public class ReporteService {

    public byte [] generarReporte(String reportName) throws JRException {
        //Cargar el reporte
        InputStream reporteStream = this. getClass().getResourceAsStream("/reportes/"+reportName+".jasper");

        Map<String, Object>  params= null;
        //llenado
        JasperPrint jasperPrint= JasperFillManager.fillReport(reporteStream, params, new JREmptyDataSource());

        //Exportación en PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
