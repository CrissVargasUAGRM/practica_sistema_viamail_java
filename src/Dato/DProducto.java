/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;
import DB.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class DProducto {
      private Conexion con;

    public DProducto() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Producto> listar(int id){
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, nombre, cantidad, codigo, almacen_id, categoria_id FROM productos;" : "SELECT id, nombre, cantidad, codigo, almacen_id, categoria_id FROM productos WHERE productos.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Producto(result.getInt(1), result.getString(2), result.getInt(3),result.getInt(4),result.getInt(5),result.getInt(6)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DProducto : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, int cantidad, int codigo, int almacen_id, int categoria_id){
        String query = "insert into productos (nombre,cantidad,codigo,alamacen_id,categoria_id,created_at,updated_at) values(?,?,?,?,?,now(),now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setInt(2, cantidad);
            pre.setInt(3,codigo);
            pre.setInt(4, almacen_id);
            pre.setInt(5, categoria_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DProducto crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String nombre,  int cantidad, int codigo, int almacen_id, int categoria_id){
        String query = "update productos set nombre = ?, cantidad = ?, codigo=?, almacen_id=?, categoria_id=? where id = ? ";
        try {
            if (listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setInt(2, cantidad);
            pre.setInt(3, codigo);
            pre.setInt(4,almacen_id);
            pre.setInt(5, categoria_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DProducto editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from productos where id = ?";
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
            System.out.println("Error DProducto eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
  
}
