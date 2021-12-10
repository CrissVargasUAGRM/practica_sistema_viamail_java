package Dato;

import DB.Conexion;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Oni
 */
public class DTelefono {

    private Conexion con;

    public DTelefono() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Telefono> listar(int id, boolean tipo){
        ArrayList<Telefono> lista = new ArrayList<>();
        String query;
        try {
            if (tipo) {
                query = (id == 0) ?
                    "SELECT telefonoclientes.id, telefonoclientes.numero, telefonoclientes.cliente_id, clientes.nombre "
                    + "FROM telefonoclientes, clientes WHERE telefonoclientes.cliente_id = clientes.id " 
                    : "SELECT telefonoclientes.id, telefonoclientes.numero, telefonoclientes.cliente_id, clientes.nombre "
                    + "FROM telefonoclientes, clientes WHERE telefonoclientes.cliente_id = clientes.id and telefonoclientes.id = " + id;
            
            }else{
                query = (id == 0) ?
                    "SELECT telefonousers.id, telefonousers.numero, telefonousers.user_id, users.nombre \n" +
                    "FROM telefonousers, users \n" +
                    "WHERE telefonousers.user_id = users.id" 
                    : "SELECT telefonousers.id, telefonousers.numero, telefonousers.user_id, users.nombre \n" +
                    "FROM telefonousers, users \n" +
                    "WHERE telefonousers.user_id = users.id and telefonousers.id =" + id;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Telefono(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DTelefono : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public boolean crear(String numero, int id_users, boolean  tipo){
        String query;
        if (tipo) {
            query = "insert into telefonoclientes (numero,cliente_id,created_at,updated_at) values(?,?,now(),now())";
        }else{
            query = "insert into telefonousers (numero,user_id,created_at,updated_at) values(?,?,now(),now())";
        }
        try {
            if (!validarForanea(id_users,tipo)){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, numero);
            pre.setInt(2, id_users);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DTelefono crear : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean editar(int id, String numero, int id_users, boolean tipo){
        String query;
        if (tipo) {
            query = "UPDATE telefonoclientes SET numero = ?, cliente_id = ?, updated_at = now()  WHERE id = ? ";
        }else{
            query = "UPDATE telefonousers SET numero = ?, user_id = ?, updated_at = now()  WHERE id = ? ";
        }
        try {
            
            if (listar(id, tipo).isEmpty() && !validarForanea(id_users, tipo)){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setString(1, numero);
            pre.setInt(2, id_users);
            pre.setInt(3, id);
            int res = pre.executeUpdate();
            pre.close();
            return res == 1;
        } catch (Exception e) {
            System.out.println("Error DTelefono editar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }
    
    public boolean eliminar(int id, boolean tipo){
        String query;
        if (tipo) {
            query = "delete from telefonoclientes where id = ?";
        }else{
            query = "delete from telefonousers where id = ?";
        }
        try {
            if (listar(id,tipo).isEmpty()){
                return false;
            }
            PreparedStatement pre = con.conectar().prepareStatement(query);
            pre.setInt(1, id);
            pre.execute();
            pre.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error DTelefono eliminar : "+e);
        }finally{
            con.desconectar();
        }
        return false;
    }

    private boolean validarForanea(int id_users, boolean tipo) {
        if (tipo) {
            DCliente cliente = new DCliente();
            if (cliente.listar(id_users).isEmpty()){
                return false;
            }
        }else{
            DUsuario users = new DUsuario();
            if (users.listar(id_users).isEmpty()){
                return false;
            }
        }
        return true;
    }
    
}
