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
 * @author Christian Vargas
 */
public class DActividad {
    private Conexion con;

    public DActividad() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Actividad> listar(int id){
        ArrayList<Actividad> lista = new ArrayList<>();
        try {
            String query = (id == 0) ? "SELECT inicio, fin, foto, actividads.estado, servicios.codigo as servicio FROM public.actividads inner join servicios on servicios.id = actividads.servicio_id" : "SELECT inicio, fin, foto, actividads.estado, servicios.codigo as servicio FROM public.actividads inner join servicios on servicios.id = actividads.servicio_id where actividads.id ="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Actividad(result.getDate(1), result.getDate(2), result.getString(3), result.getString(4), result.getString(5)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DActividad: "+e);
        }finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean registrarActividad(Date inicio, Date fin, String foto, String estado, int servicio_id){
        String query = "INSERT INTO public.actividads(inicio, fin, foto, estado, servicio_id, created_at, updated_at)VALUES (?, ?, ?, ?, ?, now(), now())";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setDate(1, inicio);
            pre.setDate(2, fin);
            pre.setString(3, foto);
            pre.setString(4, estado);
            pre.setInt(5, servicio_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DActividad crear: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, Date inicio, Date fin, String foto, int servicio_id){
        String query = "UPDATE public.actividads SET inicio=?, fin=?, foto=?, servicio_id=?, updated_at=now() WHERE actividads.id = ?";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setDate(1, inicio);
            pre.setDate(2, fin);
            pre.setString(3, foto);
            pre.setInt(4, servicio_id);
            pre.setInt(5, id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DActividad editar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id){
        String query = "DELETE FROM public.actividads WHERE actividads.id = ?";
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
            System.out.println("Error DActividad eliminar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
}
