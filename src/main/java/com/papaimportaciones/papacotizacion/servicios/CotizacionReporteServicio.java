package com.papaimportaciones.papacotizacion.servicios;


import com.papaimportaciones.papacotizacion.entidades.Cabecera;
import com.papaimportaciones.papacotizacion.entidades.Detalle;
import com.papaimportaciones.papacotizacion.repositorios.CabeceraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotizacionReporteServicio {


    @Autowired
    private CabeceraRepositorio cabeceraRepositorio;

    public Cabecera getParentWithChildren(String codigo) {
        Cabecera parent = cabeceraRepositorio.findByCodigo(codigo);
        if (parent != null) {
            // Accessing children will trigger lazy loading if FetchType.LAZY is used
            List<Detalle> children = parent.getChildren();
            // ... further processing of children
        }
        return parent;
    }
}
