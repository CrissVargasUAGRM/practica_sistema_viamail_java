package Negocio;

import Dato.DRol;
import Dato.Rol;
import java.util.ArrayList;

/**
 *
 * @author Oni
 */
public class NRol {

    private DRol drol;

    public NRol() {
        this.drol = new DRol();
    }

    public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Rol> lista = drol.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Rol </h2>"
                        + "<table>"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>Descripcion</td>"
                        + "</tr>";
                for (Rol rol : lista) {
                    res += rol.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "El Rol no encontrado";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tien : " + parametros.length + " deberia ser solo 2";
        if (parametros.length == 2) {
            if (drol.crear(parametros[0], parametros[1])) {
                return successMessage("Rol Registrado exitosamente!!");
            }
            mensaje = "Error al insertar el Rol";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 3";
        if (parametros.length == 3 && esNumero(parametros[0])) {
            if (drol.editar(Integer.valueOf(parametros[0]), parametros[1], parametros[2])) {
                return successMessage("Rol editado exitosamente!!");
            }
            mensaje = "Error al editar el Rol";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (drol.eliminar(Integer.valueOf(id))) {
                return successMessage("Rol eliminado exitosamente!!");
            }
            mensaje = "Error al eliminar el Rol";
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
