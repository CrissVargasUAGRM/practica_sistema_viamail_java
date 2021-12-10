/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;

/**
 *
 * @author Usuario
 */
public class Plan {
 private int id;
    private String nombre;
    private String descripcion;
    private int tarifa;
    private int promocion_id;
    private String promocion_nombre;
         
    public Plan(int id, String nombre, String descripcion, int tarifa,int promocion_id, String promocion_nombre){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.tarifa=tarifa;
        this.promocion_id=promocion_id;
        this.promocion_nombre=promocion_nombre;
    }
    
    public Plan(){
    }
    
     @Override
    public String toString() {
        return "<tr>"
                + "<td>" + this.id + "</td>"
                + "<td>" + this.nombre + "</td>"
                + "<td>" + this.descripcion + "</td>"
                + "<td>" + this.tarifa + "</td>"
                + "<td>" + this.promocion_id + "</td>"
                + "<td>" + this.promocion_nombre + "</td>"
                + "</tr>\n";
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

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getPromocion_id() {
        return promocion_id;
    }

    public void setPromocion_id(int promocion_id ) {
        this.promocion_id = promocion_id;
    }

        public String getPromocion_nombre() {
        return promocion_nombre;
    }

    public void setPromocion_id(String promocion_nombre ) {
        this.promocion_nombre = promocion_nombre;
    }

}
