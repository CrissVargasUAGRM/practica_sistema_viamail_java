/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Dato.DPromocion;
import Dato.Promocion;
import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author Usuario
 */
public class NPromocion {
 private DPromocion dpromocion;
    
     public NPromocion() {
        this.dpromocion = new DPromocion();
    }

    public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Promocion> lista = dpromocion.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Rol </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>Descuento</td>"
                        + "<td>Inicio</td>"
                        + "<td>Fin</td>"
                        + "</tr>\n";
                for (Promocion promocion : lista) {
                    res += promocion.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "Promocion no encontrada";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 4";
        if (parametros.length == 4) {
            if (dpromocion.crear(parametros[0],Integer.valueOf(parametros[1]),Date.valueOf(parametros[2]),Date.valueOf(parametros[3]))) {
                return successMessage("Promocion Registrada exitosamente!!");
            }
            mensaje = "Error al insertar la Promocion";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 5";
        if (parametros.length == 5 && esNumero(parametros[0])) {
            if (dpromocion.editar(Integer.valueOf(parametros[0]), parametros[1], Integer.valueOf(parametros[2]),Date.valueOf(parametros[3]),Date.valueOf(parametros[4]))) {
                return successMessage("Promocion editada exitosamente!!");
            }
            mensaje = "Error al editar la Promocion";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (dpromocion.eliminar(Integer.valueOf(id))) {
                return successMessage("Promocion eliminada exitosamente!!");
            }
            mensaje = "Error al eliminar la Promocion";
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
