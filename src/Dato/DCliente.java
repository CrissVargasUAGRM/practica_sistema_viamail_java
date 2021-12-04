package Dato;

import DB.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Oni
 */
public class DCliente {
    private Conexion con;

    public DCliente() {
        this.con = Conexion.getInstancia();
    }
    
    public ArrayList<Cliente> listar(int id){
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            String query = (id == 0) ?"SELECT id, nombre, apellidos, ci, correo FROM clientes;" : "SELECT id, nombre, apellidos, ci, correo FROM clientes WHERE clientes.id="+id;
            PreparedStatement pre = con.conectar().prepareStatement(query);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                lista.add(new Cliente(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
            }
            pre.close();
        } catch (Exception e) {
            System.out.println("Error DConsultar : "+e);
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
}
