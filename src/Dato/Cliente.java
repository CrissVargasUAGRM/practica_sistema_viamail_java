package Dato;

/**
 *
 * @author Oni
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String ci;
    private String correo;

    public Cliente(int id, String nombre, String apellido, String ci, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.correo = correo;
    }

    public Cliente() {
    }
    
    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.id + "</td>" +
                "<td>" + this.nombre + "</td>" + 
                "<td>" + this.apellido + "</td>" + 
                "<td>" + this.ci + "</td>" + 
                "<td>" + this.correo + "</td>" + 
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
