package Dato;

import DB.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Oni
 */
public class DRol {
    private Conexion con;

    public DRol() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Rol> listar(int id){
        ArrayList<Rol> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, nombre, descripcion FROM roles;" : "SELECT id, nombre, descripcion FROM roles WHERE roles.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Rol(result.getInt(1), result.getString(2), result.getString(3)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DRol : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, String descripcion){
        String query = "insert into roles (nombre,descripcion,created_at,updated_at) values(?,?,now(),now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, descripcion);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DRol crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String nombre, String descripcion){
        String query = "update roles set nombre = ?, descripcion = ?, updated_at = now() where id = ? ";
        try {
            if (listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, descripcion);
            pre.setInt(3, id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DRol editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from roles where id = ?";
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
            System.out.println("Error DRol eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
}
