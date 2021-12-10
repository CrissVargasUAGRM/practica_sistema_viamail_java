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
public class Almacen {
 private int id;
    private int cantidad;
    private String direccion;

    public Almacen(int id, String dirreccion) {
        this.id = id;
        this.direccion= direccion;
    }

    public Almacen() {
    }

    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.id + "</td>" + 
                "<td>" + this.direccion + "</td>" + 
                "</tr>\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirreccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }   
}
