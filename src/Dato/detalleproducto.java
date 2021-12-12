/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;
import java.sql.Date;

/**
 *
 * @author Christian Vargas
 */
public class detalleproducto {
    private int actividad_id;
    private int procduto_id;
    private Date actividad_inicio;
    private String productoNombre;

    public detalleproducto() {
    }

    public detalleproducto(int actividad_id, int procduto_id, Date actividad_inicio, String productoNombre) {
        this.actividad_id = actividad_id;
        this.procduto_id = procduto_id;
        this.actividad_inicio = actividad_inicio;
        this.productoNombre = productoNombre;
    }

    public detalleproducto(Date actividad_inicio, String productoNombre) {
        this.actividad_inicio = actividad_inicio;
        this.productoNombre = productoNombre;
    }

    public detalleproducto(int actividad_id, String productoNombre) {
        this.actividad_id = actividad_id;
        this.productoNombre = productoNombre;
    }

    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.actividad_id + "</td>" +
                "<td>" + this.productoNombre + "</td>" +
                "</tr>\n";
    }
    
    

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    public int getProcduto_id() {
        return procduto_id;
    }

    public void setProcduto_id(int procduto_id) {
        this.procduto_id = procduto_id;
    }

    public Date getActividad_inicio() {
        return actividad_inicio;
    }

    public void setActividad_inicio(Date actividad_inicio) {
        this.actividad_inicio = actividad_inicio;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }
    
    
}
