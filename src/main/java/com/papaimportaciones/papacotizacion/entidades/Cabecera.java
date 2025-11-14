package com.papaimportaciones.papacotizacion.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cabecera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    private String descripcion;
    private String telefono;
    @NotNull
    private String codigo;
    @NotNull
    private Date fecha;
    @NotNull
    private Integer validezDias;

    @OneToMany(mappedBy = "id_maestro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Detalle> children = new ArrayList<>();

    public List<Detalle> getChildren() {
        return children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getValidezDias() {
        return validezDias;
    }

    public void setValidezDias(Integer validezDias) {
        this.validezDias = validezDias;
    }
}
