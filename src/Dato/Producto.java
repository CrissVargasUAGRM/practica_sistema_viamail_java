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
public class Producto {
   private int id;
    private String nombre;
    private int cantidad;
    private int codigo;
    private int almacen_id;
    private int categoria_id;
         
    public Producto(int id, String nombre, int cantidad, int codigo,int almacen_id, int categoria_id){
        this.id=id;
        this.nombre=nombre;
        this.cantidad=cantidad;
        this.codigo=codigo;
        this.almacen_id=almacen_id;
        this.categoria_id=categoria_id;
    }
    
    public Producto(){
    }
    
     @Override
    public String toString() {
        return "<tr>"
                + "<td>" + this.id + "</td>"
                + "<td>" + this.nombre + "</td>"
                + "<td>" + this.cantidad + "</td>"
                + "<td>" + this.codigo + "</td>"
                + "<td>" + this.almacen_id + "</td>"
                + "<td>" + this.categoria_id + "</td>"
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(int almacen_id ) {
        this.almacen_id = almacen_id;
    }

        public int getCategoria() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id ) {
        this.categoria_id = categoria_id;
    } 
}
