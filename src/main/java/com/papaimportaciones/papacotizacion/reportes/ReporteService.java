package com.papaimportaciones.papacotizacion.reportes;

import com.papaimportaciones.papacotizacion.controlador.ReporteControlador;
import com.papaimportaciones.papacotizacion.entidades.Cabecera;
import com.papaimportaciones.papacotizacion.servicios.CotizacionReporteServicio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

    @Value("${dockerImage.images}")
    private String reporteDockeroW;

    private CotizacionReporteServicio cotizacionReporteServicio;


    public ReporteService(CotizacionReporteServicio cotizacionReporteServicio) {
        this.cotizacionReporteServicio = cotizacionReporteServicio;
    }

    public byte [] generarReporte(String reportName, String idcoti) throws JRException, IOException {


       //LLamar información
        Cabecera cabecera= cotizacionReporteServicio.getParentWithChildren(idcoti);

        Map<String, Object> parametros= new HashMap<>();
        parametros.put("nombreCliente", cabecera.getNombre());
        parametros.put("metrosLineales", cabecera.getDescripcion());
        parametros.put("fecha", new SimpleDateFormat("MM-dd-yyyy").format(new Date()));
        //si el parametro es verdadero se envía la ruta de docker si es falso se envía la ruta de windows.
        if(reporteDockeroW.equals("linux")){
            parametros.put("reporteDockeroW", "/app/grispapa.png");
        }

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(cabecera.getChildren());

        //Cargar el reporte
        InputStream reporteStream = this. getClass().getResourceAsStream("/reportes/"+reportName+".jasper");

        if (reporteStream == null) {
            throw new FileNotFoundException("No se encontró el archivo de reporte: " + reportName + ".jasper");
        }
        //llenado
        JasperPrint jasperPrint= JasperFillManager.fillReport(reporteStream, parametros, beanColDataSource);

        //Exportación en PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte [] agregarPaginaInformativa(byte[] presupuesto){

        //PDFMergerUtility merger = new PDFMergerUtility();
        // Wrap the byte array in a ByteArrayInputStream
        //ByteArrayInputStream bais = new ByteArrayInputStream(presupuesto);
        // Add the InputStream to the PDFMergerUtility
        //merger.addSource(bais);
       // merger.addSource(new File(pdf1Path)); hoja extra
        // You would typically add more sources and then merge the documents
        // merger.setDestinationFileName("merged.pdf");
        // merger.mergeDocuments(null);

        return null;
    }

    public String makecalculus(ReporteControlador.DtoPresu dtopresu)  {
        //Dividir la cantidad de metros totales para dos punto 2.5 por ejemplo 186/2.5
        // esa cantidad se redonde al superior en este caso da 75(74.4)
        //Llamar a tabla productos(cercas) se saca 75 de cerca definida se busca la cerca definida que manda la IA
        //LLamar a tabla productos(postes) se saca un poste mas, 76
        //Llamar a tabla productos(tapas) se saca igual que postes
        // Fijaciones, Pernos y tuercas * 5 del poste

        //calcular el cantidad de cada producto * el valor unitario
        // Un subtotal 1 solo materiales
        // una posible instalación valor
        // un subtotal (subtotal 1 + instalación)
        // el Iva
        // Total (subtotal+ IVA)

        //Crear la entidad cabecera
        // guardar subtotal1, instalación, subtotal, Total, teléfono cliente, nombre cliente
        //Crear las entidades detalles para persistir
        //calcular el cantidad de cada producto * el valor unitario y guardar en detalles

        //Persistir las dos entidades
        //retonar el codigo  de la cabecera
        return "";
    }

}
