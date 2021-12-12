/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;
import java.util.Date;

/**
 *
 * @author Christian Vargas
 */
public class Actividad {
    private int id;
    private Date inicio;
    private Date fin;
    private String foto;
    private String estado;
    private int servicio_id;
    private String servicio_nombre;

    public Actividad() {
    }

    public Actividad(int id, Date inicio, Date fin, String foto, String estado, int servicio_id, String servicio_nombre) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.foto = foto;
        this.estado = estado;
        this.servicio_id = servicio_id;
        this.servicio_nombre = servicio_nombre;
    }

    public Actividad(Date inicio, Date fin, String foto, String estado, String servicio_nombre) {
        this.inicio = inicio;
        this.fin = fin;
        this.foto = foto;
        this.estado = estado;
        this.servicio_nombre = servicio_nombre;
    }
    
    

    @Override
    public String toString() {
        return "<tr>"
                + "<td>" + this.inicio + "</td>"
                + "<td>" + this.fin + "</td>"
                + "<td>" + this.foto + "</td>"
                + "<td>" + this.estado + "</td>"
                + "<td>" + this.servicio_nombre + "</td>"
                + "</tr>\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(int servicio_id) {
        this.servicio_id = servicio_id;
    }

    public String getServicio_nombre() {
        return servicio_nombre;
    }

    public void setServicio_nombre(String servicio_nombre) {
        this.servicio_nombre = servicio_nombre;
    }
    
    
}
