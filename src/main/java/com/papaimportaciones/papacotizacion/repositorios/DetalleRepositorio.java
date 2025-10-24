package com.papaimportaciones.papacotizacion.repositorios;


import com.papaimportaciones.papacotizacion.entidades.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepositorio  extends JpaRepository<Detalle, Integer> {

    //List<Detalle> findByid_maestro(Integer cabeceraId); // Example: find children by parent ID

}
