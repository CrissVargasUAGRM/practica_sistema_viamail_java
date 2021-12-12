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
public class DDetalleProducto {
    private Conexion con;

    public DDetalleProducto() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<detalleproducto> listar(int id){
        ArrayList<detalleproducto> lista = new ArrayList<>();
        try {
            String query = (id == 0) ? "SELECT actividad_id, productos.nombre FROM public.producto_actividad INNER JOIN productos on productos.id = producto_actividad.producto_id ORDER BY actividad_id ASC, producto_id ASC" : "SELECT actividad_id, productos.nombre FROM public.producto_actividad INNER JOIN productos on productos.id = producto_actividad.producto_id where producto_actividad.actividad_id = "+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new detalleproducto(result.getInt(1), result.getString(2)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DDetalleProducto: "+e);
        }finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean addDetalleProducto(String productoNombre){
        int actividad_id = 0;
        int producto_id = 0;
        try {
            /*OBTENER EL ID DE LA CABECERA*/
            String consultaid = "SELECT max(actividads.id) FROM public.actividads";
            PreparedStatement cons = con.conectar().prepareStatement(consultaid);
            ResultSet resp = cons.executeQuery();
            while(resp.next()){
                actividad_id = resp.getInt(1);
            }
            
            /*OBTENER EL ID DEL PRODUCTO POR EL NOMBRE*/
            String productoid = "SELECT productos.id FROM public.productos where productos.nombre = ?";
            PreparedStatement prodcons = con.conectar().prepareStatement(productoid);
            prodcons.setString(1, productoNombre);
            ResultSet productoresp = prodcons.executeQuery();
            while(productoresp.next()){
                producto_id = productoresp.getInt(1);
            }
            
            /*insertar el detallle completo*/
            String query = "INSERT INTO public.producto_actividad(actividad_id, producto_id, created_at, updated_at)VALUES (?, ?, now(), now())";
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, actividad_id);
            pre.setInt(2, producto_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DDetalleProducto crear: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int actividad_id, int producto_id, int nuevo_producto_id){
        String query = "UPDATE public.producto_actividad SET producto_id=?, updated_at=now() WHERE actividad_id = ? and producto_id = ?";
        try {
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, nuevo_producto_id);
            pre.setInt(2, actividad_id);
            pre.setInt(3, producto_id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DDetalleProducto editar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int actividad_id, int producto_id){
        String query = "DELETE FROM public.producto_actividad WHERE actividad_id = ? and producto_id = ?";
        try {
            if(listar(actividad_id).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, actividad_id);
            pre.setInt(2, producto_id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DDetalleProducto eliminar: "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
}
