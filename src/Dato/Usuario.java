package Dato;

/**
 *
 * @author Oni
 */
public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private int ci;
    private String correo;
    private String password;
    private int rol_id;
    private String rol_nombre;

    public Usuario(int id, String nombre, String apellido, int ci, String correo, String password, int rol_id, String rol_nombre) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.correo = correo;
        this.password = password;
        this.rol_id = rol_id;
        this.rol_nombre = rol_nombre;
    }

    public Usuario(int id, String nombre, String apellido, int rol_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol_id = rol_id;
    }
    
    
    
    public Usuario() {
    }

    @Override
    public String toString() {
        return "<tr>"
                + "<td>" + this.id + "</td>"
                + "<td>" + this.nombre + "</td>"
                + "<td>" + this.apellido + "</td>"
                + "<td>" + this.ci + "</td>"
                + "<td>" + this.correo + "</td>"
                + "<td>" + this.password + "</td>"
                + "<td>" + this.rol_id + "</td>"
                + "<td>" + this.rol_nombre + "</td>"
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }
    
    
}
