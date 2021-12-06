package Dato;

import DB.Conexion;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Oni
 */
public class DUsuario {
    private Conexion con;

    public DUsuario() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Usuario> listar(int id){
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT users.id, users.nombre, users.apellido, users.ci, users.correo, users.password, roles.id, roles.nombre	"
                    + "FROM users, roles WHERE users.rol_id = roles.id;" 
                    : "SELECT users.id, users.nombre, users.apellido, users.ci, users.correo, users.password, roles.id, roles.nombre "
                    + "FROM users, roles WHERE users.id = "+id+" and users.rol_id = roles.id";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Usuario(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6),result.getInt(7), result.getString(8) ));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DUsuario : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, String apellido, int ci, String correo, String password,int rol_id){
        String query = "insert into users (nombre,apellido,ci,correo,password,rol_id,created_at,updated_at) values(?,?,?,?,?,?,now(),now())";
        try {
            DRol rol = new DRol();
            if (rol.listar(rol_id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, apellido);
            pre.setInt(3, ci);
            pre.setString(4, correo);
            pre.setString(5, password);
            pre.setInt(6, rol_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DUsuario crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public ArrayList<Usuario> buscarCorreo(String correo){
        System.out.println(correo);
        ArrayList<Usuario> lista = new ArrayList<>();
        try{
            String query = "SELECT id, nombre, apellido, rol_id FROM users WHERE correo = ?";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, correo);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Usuario(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
            }
            pre.close();
        }catch(Exception e){
            System.out.println("Error DUsuario buscar : "+e);
        }finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean editar(int id, String nombre, String apellido, int ci, String correo, String password,int rol_id){
        String query = "UPDATE users SET nombre = ?, apellido = ?, ci = ?, correo = ?, password = ?, rol_id = ?, updated_at = now()  WHERE id = ? ";
        try {
            DRol rol = new DRol();
            if (listar(id).isEmpty() && rol.listar(rol_id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, apellido);
            pre.setInt(3, ci);
            pre.setString(4, correo);
            pre.setString(5, password);
            pre.setInt(6, rol_id);
            pre.setInt(7, id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DUsuario editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from users where id = ?";
        try {
            if (listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DUsuario eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
