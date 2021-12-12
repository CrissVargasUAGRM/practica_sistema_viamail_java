/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;

import DB.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christian Vargas
 */
public class DServicio {
    private Conexion con;

    public DServicio() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Servicio> listar(int id){
        ArrayList<Servicio> lista = new ArrayList<>();
        try {
            String query = (id == 0) ? "SELECT servicios.id, direccion, codigo, tipo, estado, plans.nombre as plan FROM public.servicios INNER JOIN plans ON plans.id = servicios.plan_id" : "SELECT servicios.id, direccion, codigo, tipo, estado, plans.nombre as plan FROM public.servicios INNER JOIN plans ON plans.id = servicios.plan_id WHERE servicios.id = "+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Servicio(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6)));
                
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DServicio "+e);
        }finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String direccion, String codigo, String tipo, String estado, String nombre_plan){
        int id_plan = 0;
        try {
            /*obtener el id del plan por el nombre*/
            String planid = "SELECT plans.id FROM public.plans where plans.nombre = ?";
            PreparedStatement consulta = con.conectar().prepareStatement(planid);
            consulta.setString(1, nombre_plan);
            ResultSet resp = consulta.executeQuery();
            while(resp.next()){
                id_plan = resp.getInt(1);
            }
            
            /*insertamos en servicio*/
            String query = "INSERT INTO public.servicios(direccion, codigo, tipo, estado, plan_id, created_at, updated_at)VALUES (?, ?, ?, ?, ?, now(), now())";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, direccion);
            pre.setString(2, codigo);
            pre.setString(3, tipo);
            pre.setString(4, estado);
            pre.setInt(5, id_plan);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DServicio crear: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String direccion, String codigo, String tipo, String estado, String plan_nombre){
        /*obtener el id del plan en base al nombre*/
        int idplan = 0;
        try {
            String planid = "SELECT plans.id FROM public.plans where plans.nombre = ?";
            PreparedStatement consulta = con.conectar().prepareStatement(planid);
            consulta.setString(1, plan_nombre);
            ResultSet resp = consulta.executeQuery();
            while(resp.next()){
                idplan = resp.getInt(1);
            }
            
            /*actualizamos el servicios escogido*/
            String query = "UPDATE public.servicios SET direccion=?, codigo=?, tipo=?, estado=?, plan_id=? WHERE servicios.id = ?";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, direccion);
            pre.setString(2, codigo);
            pre.setString(3, tipo);
            pre.setString(4, estado);
            pre.setInt(5, idplan);
            pre.setInt(6, id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DServicio editar: "+e);
        }finally{
            con.desconectar();
        }
        return false; 
    }
    
    public boolean eliminar(int id){
        String query = "DELETE FROM public.servicios WHERE servicios.id = ?";
        try {
            if(listar(id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DServicio eliminar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
