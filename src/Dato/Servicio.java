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
public class Servicio {
    private int id;
    private String direccion;
    private String codigo;
    private String tipo;
    private String estado;
    private int plan_id;
    private String nombre_plan;

    public Servicio() {
    }

    public Servicio(int id, String direccion, String codigo, String tipo, String estado, String nombre_plan) {
        this.id = id;
        this.direccion = direccion;
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
        this.nombre_plan = nombre_plan;
    }

    public Servicio(int id, String direccion, String codigo, String tipo, String estado, int plan_id, String nombre_plan) {
        this.id = id;
        this.direccion = direccion;
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
        this.plan_id = plan_id;
        this.nombre_plan = nombre_plan;
    }

    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.id + "</td>" +
                "<td>" + this.direccion + "</td>" + 
                "<td>" + this.codigo + "</td>" + 
                "<td>" + this.tipo + "</td>" + 
                "<td>" + this.estado + "</td>" + 
                "<td>" + this.nombre_plan + "</td>" +
                "</tr>\n";
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }
    
    
}
