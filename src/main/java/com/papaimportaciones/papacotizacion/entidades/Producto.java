package com.papaimportaciones.papacotizacion.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    private Double precioa;
    private Double preciob;
    private Double precioc;
    private Double cantidadmetro;
    private String codigo;
    private String tipo;

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

    public Double getPrecioa() {
        return precioa;
    }

    public void setPrecioa(Double precioa) {
        this.precioa = precioa;
    }

    public Double getPreciob() {
        return preciob;
    }

    public void setPreciob(Double preciob) {
        this.preciob = preciob;
    }

    public Double getPrecioc() {
        return precioc;
    }

    public void setPrecioc(Double precioc) {
        this.precioc = precioc;
    }

    public Double getCantidadmetro() {
        return cantidadmetro;
    }

    public void setCantidadmetro(Double cantidadmetro) {
        this.cantidadmetro = cantidadmetro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
