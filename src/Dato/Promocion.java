/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;
import java.sql.Date;
/**
 *
 * @author Usuario
 */
public class Promocion {
   private int id;
    private String nombre;
    private int descuento;
    private Date inicio;
    private Date fin;

    public Promocion(int id, String nombre, int descuento, Date inicio, Date fin) {
        this.id = id;
        this.nombre = nombre;
        this.descuento=descuento;
        this.inicio=inicio;
        this.fin=fin;
    }

    public Promocion() {
    }

    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.id + "</td>" +
                "<td>" + this.nombre + "</td>" + 
                "<td>" + this.descuento + "</td>" +
                "<td>" + this.inicio + "</td>"+
                "<td>" + this.fin + "</td>"+
                "</tr>\n";
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

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    public Date getInicio(){
        return inicio;
    }
    
    public void setInicio(Date inicio){
        this.inicio=inicio;
    }
    
        public Date getFin(){
        return fin;
    }
    
    public void setFin(Date fin){
        this.fin=fin;
    }
    
}
