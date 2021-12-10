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
public class DAlmacen {
  private Conexion con;
  public DAlmacen(){
      this.con = Conexion.getInstancia();
}
   public ArrayList<Almacen> listar(int id){
        ArrayList<Almacen> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, direccion FROM almacens;" : "SELECT id, direccion FROM almacens WHERE almacens.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Almacen(result.getInt(1),result.getString(2)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DAlmacen : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    public boolean crear( String direccion){
        String query = "insert into almacens (direccion,created_at,updated_at) values(?,now(),now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, direccion);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DAlmacens crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String direccion){
        String query = "update almacens set direccion = ? where id = ? ";
        try {
            if (listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, direccion);
            pre.setInt(2, id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DAlmacen editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from almacens where id = ?";
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
            System.out.println("Error DAlmacens eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
