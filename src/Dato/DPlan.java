/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;

import DB.Conexion;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Usuario
 */
public class DPlan {
    private Conexion con;

    public DPlan() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Plan> listar(int id){
        ArrayList<Plan> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT plans.id, plans.nombre, plans.descripcion, plans.tarifa, promocions.id, promocions.nombre	"
                    + "FROM plans, promocions WHERE plans.promocion_id = promocions.id;" 
                    : "SELECT plans.id, plans.nombre, plans.descripcion, plans.tarifa, promocions.id, promocions.nombre	"
                    + "FROM plans, promocions WHERE plans.id = "+id+" and plans.promocion_id = promocions.id";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Plan(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5), result.getString(6) ));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DPlan : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String nombre, String descripcion, int tarifa,int promocion_id){
        String query = "insert into plans (nombre,descripcion,tarifa,promocion_id,created_at,updated_at) values(?,?,?,?,now(),now())";
        try {
            DPromocion promocion = new DPromocion();
            if (promocion.listar(promocion_id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, descripcion);
            pre.setInt(3, tarifa);
            pre.setInt(4, promocion_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DPlan crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id,String nombre, String descripcion, int tarifa,int promocion_id){
        String query = "UPDATE plans SET nombre = ?, descripcion = ?, tarifa = ?, promocion_id = ?, updated_at = now()  WHERE id = ? ";
        try {
            DPromocion promocion = new DPromocion();
            if (listar(id).isEmpty() && promocion.listar(promocion_id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, nombre);
            pre.setString(2, descripcion);
            pre.setInt(3, tarifa);
            pre.setInt(4, promocion_id);
            pre.setInt(5, id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DPlan editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "delete from plans where id = ?";
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
            System.out.println("Error DPlans eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
