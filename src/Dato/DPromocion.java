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
import java.sql.Date;
/**
 *
 * @author Usuario
 */
public class DPromocion {
    private Conexion con;

    public DPromocion() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Promocion> listar(int id){
        ArrayList<Promocion> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, nombre, descuento, inicio, fin FROM promocions ;" : "SELECT id, nombre, descuento, inicion, fin FROM promocions WHERE promocions.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Promocion(result.getInt(1), result.getString(2), result.getInt(3),result.getDate(4),result.getDate(5)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DPromocion : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, int descuento, Date inicio, Date fin){
        String query = "insert into promocions (nombre,descuento,inicio,fin,created_at,updated_at) values(?,?,?,?,now(),now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setInt(2, descuento);
            pre.setDate(3, inicio);
            pre.setDate(4, fin);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DPromocion crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String nombre, int descuento, Date inicio, Date fin){
        String query = "update promocions set nombre = ?, descuento = ?, inicio=?, fin=? where id = ? ";
        try {
            if (listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setInt(2, descuento);
            pre.setDate(3, inicio);
            pre.setDate(4, fin);
            pre.setInt(3, id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DPromocion editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from promocions where id = ?";
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
            System.out.println("Error DPromocion eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }  
}
