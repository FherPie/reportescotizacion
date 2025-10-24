package com.papaimportaciones.papacotizacion.repositorios;

import com.papaimportaciones.papacotizacion.entidades.Cabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabeceraRepositorio extends JpaRepository<Cabecera, Integer> {
    Cabecera findByCodigo(String codigo);

}
