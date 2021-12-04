package Dato;

/**
 *
 * @author Oni
 */
public class Rol {
    private int id;
    private String nombre;
    private String descripcion;

    public Rol(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Rol() {
    }

    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.id + "</td>" +
                "<td>" + this.nombre + "</td>" + 
                "<td>" + this.descripcion + "</td>" + 
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
