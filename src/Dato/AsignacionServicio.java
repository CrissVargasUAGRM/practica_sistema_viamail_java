/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;

/**
 *
 * @author Christian Vargas
 */
public class AsignacionServicio {
    private int servicio_id;
    private int user_id;
    private String servicio_nombre;
    private String user_nombre;

    public AsignacionServicio() {
    }

    public AsignacionServicio(int servicio_id, int user_id, String servicio_nombre, String user_nombre) {
        this.servicio_id = servicio_id;
        this.user_id = user_id;
        this.servicio_nombre = servicio_nombre;
        this.user_nombre = user_nombre;
    }

    public AsignacionServicio(int servicio_id, int user_id) {
        this.servicio_id = servicio_id;
        this.user_id = user_id;
    }

    public AsignacionServicio(String servicio_nombre, String user_nombre) {
        this.servicio_nombre = servicio_nombre;
        this.user_nombre = user_nombre;
    }

    public AsignacionServicio(int servicio_id, String user_nombre) {
        this.servicio_id = servicio_id;
        this.user_nombre = user_nombre;
    }
    
    

    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.servicio_id + "</td>" +
                "<td>" + this.user_nombre + "</td>" + 
                "</tr>\n";
    }

    public int getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(int servicio_id) {
        this.servicio_id = servicio_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getServicio_nombre() {
        return servicio_nombre;
    }

    public void setServicio_nombre(String servicio_nombre) {
        this.servicio_nombre = servicio_nombre;
    }

    public String getUser_nombre() {
        return user_nombre;
    }

    public void setUser_nombre(String user_nombre) {
        this.user_nombre = user_nombre;
    }
    
    
}
