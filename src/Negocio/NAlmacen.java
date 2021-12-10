/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Dato.DAlmacen;
import Dato.Almacen;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class NAlmacen {
    private DAlmacen dalmacen;
   public NAlmacen(){
       this.dalmacen=new DAlmacen();
   } 
   
   public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Almacen> lista = dalmacen.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Almacen </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Direccion</td>"
                        + "</tr>\n";
                for (Almacen almacen : lista) {
                    res += almacen.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "El Almacen no encontrado";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 1";
        if (parametros.length == 1) {
            if (dalmacen.crear(parametros[0])) {
                return successMessage("Almacen Registrado exitosamente!!");
            }
            mensaje = "Error al insertar el Almacen";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 2";
        if (parametros.length == 2 && esNumero(parametros[0])) {
            if (dalmacen.editar(Integer.valueOf(parametros[0]), parametros[1])) {
                return successMessage("Almacen editado exitosamente!!");
            }
            mensaje = "Error al editar el Almacen";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (dalmacen.eliminar(Integer.valueOf(id))) {
                return successMessage("Almacen eliminado exitosamente!!");
            }
            mensaje = "Error al eliminar el Almacen";
        }
        return errorMessage(mensaje);
    }

    public boolean esNumero(String prt_parametros) {
        try {
            Integer.parseInt(prt_parametros);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage(String parametro) {
        return "<div><strong>ERROR</strong><p>" + parametro + "</p></div>";
    }

    public String successMessage(String parametro) {
        return "<div><strong>EXITO</strong><p>" + parametro + "</p></div>";
    }
}
