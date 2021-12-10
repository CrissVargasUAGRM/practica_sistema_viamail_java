package Dato;

import DB.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oni
 */
public class DCliente {
    private Conexion con;

    public DCliente() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Cliente> listar(int id){
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, nombre, apellidos, ci, correo FROM clientes;" : "SELECT id, nombre, apellidos, ci, correo FROM clientes WHERE clientes.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Cliente(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
            }
            pre.close();
        } catch (SQLException e) {
            System.out.println("Error DCliente : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, String apellido, int ci, String correo){
        String query = "insert into clientes (nombre,apellidos,ci,correo,created_at,updated_at) values(?,?,?,?,now(),now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, apellido);
            pre.setInt(3, ci);
            pre.setString(4, correo);
            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error DCliente crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String nombre, String apellido, int ci, String correo){
        String query = "UPDATE clientes SET nombre = ?, apellidos = ?, ci = ?, correo = ?, updated_at = now()  WHERE id = ? ";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, apellido);
            pre.setInt(3, ci);
            pre.setString(4, correo);
            pre.setInt(5, id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (SQLException e) {
            System.out.println("Error DCliente editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from clientes where id = ?";
        try {
            if (listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, id);
            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error DCliente eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
}
