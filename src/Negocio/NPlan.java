/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dato.DPlan;
import Dato.Plan;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class NPlan {
    private DPlan dplan;

    public NPlan() {
        this.dplan = new DPlan();
    }

    public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Plan> lista = dplan.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Plan </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>Descripcion</td>"
                        + "<td>Tarifa</td>"
                        + "<td>promocion_id</td>"                  
                        + "</tr>\n";
                for (Plan plan : lista) {
                    res += plan.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "El Plan no encontrado";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tien : " + parametros.length + " deberia ser solo 4";
        if (parametros.length == 4 && esNumero(parametros[3])) {
            if (dplan.crear(parametros[0], parametros[1],Integer.valueOf(parametros[2]),Integer.valueOf(parametros[3]))) {
                return successMessage("Plan Registrado exitosamente!!");
            }
            mensaje = "Error al insertar el Plan";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 5";
        if (parametros.length == 4 && esNumero(parametros[0]) && esNumero(parametros[5])) {
            if (dplan.editar(Integer.valueOf(parametros[0]),parametros[1], parametros[2],Integer.valueOf(parametros[3]),Integer.valueOf(parametros[4]))) {
                return successMessage("Plan editado exitosamente!!");
            }
            mensaje = "Error al editar el Plan";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (dplan.eliminar(Integer.valueOf(id))) {
                return successMessage("Plan eliminado exitosamente!!");
            }
            mensaje = "Error al eliminar el Plan";
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
