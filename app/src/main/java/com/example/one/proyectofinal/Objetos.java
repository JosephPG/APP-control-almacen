package com.example.one.proyectofinal;

/**
 * Created by one on 14/12/15.
 */
public class Objetos {
    String id;
    String codpro;
    String nompro;
    String cantidad;
    String fecha;
    String extra;
    String identificador;

    public Objetos(String id, String codpro, String nompro, String cantidad, String fecha, String extra, String identificador) {
        this.id = id;
        this.codpro = codpro;
        this.nompro = nompro;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.extra = extra;
        this.identificador=identificador;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNompro() {
        return nompro;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
