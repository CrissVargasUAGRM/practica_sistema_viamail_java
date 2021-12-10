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
public class DCategoria {
    private Conexion con;

    public DCategoria() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Categoria> listar(int id){
        ArrayList<Categoria> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, nombre, descripcion FROM categorias;" : "SELECT id, nombre, descripcion FROM categorias WHERE categorias.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Categoria(result.getInt(1), result.getString(2), result.getString(3)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DCategoria : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, String descripcion){
        String query = "insert into categorias (nombre,descripcion,created_at,updated_at) values(?,?,now(),now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, descripcion);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DCategoria crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String nombre, String descripcion){
        String query = "update categorias set nombre = ?, descripcion = ? where id = ? ";
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
            System.out.println("Error DCategoria editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from categorias where id = ?";
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
            System.out.println("Error DCategoria eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
