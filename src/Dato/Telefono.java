package Dato;

/**
 *
 * @author Oni
 */
public class Telefono {
    private int id;
    private String numero;
    private int id_users;
    private String nombre;

    public Telefono(int id, String numero, int id_users, String nombre) {
        this.id = id;
        this.numero = numero;
        this.id_users = id_users;
        this.nombre = nombre;
    }

    public Telefono() {
    }
    
    @Override
    public String toString() {
        return "<tr>" + 
                "<td>" + this.id + "</td>" +
                "<td>" + this.numero + "</td>" + 
                "<td>" + this.id_users + "</td>" + 
                "<td>" + this.nombre + "</td>" + 
                "</tr>\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
}
