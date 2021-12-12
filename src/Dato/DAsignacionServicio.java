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
 * @author Christian Vargas
 */
public class DAsignacionServicio {
    private Conexion con;

    public DAsignacionServicio() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<AsignacionServicio> listar(int id){
        ArrayList<AsignacionServicio> lista = new ArrayList<>();
        try {
            String query = (id == 0) ? "SELECT servicio_id, users.nombre as tecnico FROM public.asignacion_servicio INNER JOIN users ON users.id = asignacion_servicio.user_id" : "SELECT servicio_id, users.nombre as tecnico FROM public.asignacion_servicio INNER JOIN users ON users.id = asignacion_servicio.user_id WHERE asignacion_servicio.servicio_id = "+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new AsignacionServicio(result.getInt(1), result.getString(2)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DAsignacionServicio: "+e);
        }finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean addAsignacionServicio(String nombre){
        int servicio_id = 0;
        int usuario_id = 0;
        try {
            /*OBTENER EL ID DE LA CABEZERA OSEA EL SERVICIO*/
            String serviciocons = "SELECT max(servicios.id) FROM public.servicios";
            PreparedStatement cons = con.conectar().prepareStatement(serviciocons);
            ResultSet servicioresp = cons.executeQuery();
            while(servicioresp.next()){
                servicio_id = servicioresp.getInt(1);
            }
            
            /*OBTENER EL ID DEL USUARIO ASIGNADO AL SERVICIO MEDIANTE EL NOMBRE*/
            String usercons = "SELECT users.id FROM public.users WHERE users.nombre = ?";
            PreparedStatement usersid = con.conectar().prepareStatement(usercons);
            usersid.setString(1, nombre);
            ResultSet userresp = usersid.executeQuery();
            while(userresp.next()){
                usuario_id = userresp.getInt(1);
            }
            
            /*INSERTAR EL DETALLE COMPLETO*/
            String query = "INSERT INTO public.asignacion_servicio(servicio_id, user_id, created_at, updated_at)VALUES (?, ?, now(), now())";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, servicio_id);
            pre.setInt(2, usuario_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DAsignacionServicio crear: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int servicio_id, int user_id, int nuevo_user){
        String query = "UPDATE public.asignacion_servicio SET user_id=?, updated_at=now() WHERE servicio_id = ? and user_id=?";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, nuevo_user);
            pre.setInt(2, servicio_id);
            pre.setInt(3, user_id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DAsignacionServicio editar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int servicio_id, int user_id){
        String query = "DELETE FROM public.asignacion_servicio WHERE asignacion_servicio.servicio_id = ? AND asignacion_servicio.user_id = ?";
        try {
            if(listar(servicio_id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, servicio_id);
            pre.setInt(2, user_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DAsignacionServicio eliminar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
