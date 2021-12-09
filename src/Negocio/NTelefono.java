package Negocio;

import Dato.DTelefono;
import Dato.Telefono;
import java.util.ArrayList;

/**
 *
 * @author Oni
 */
public class NTelefono {

    private DTelefono dtelfono;

    public NTelefono() {
        this.dtelfono = new DTelefono();
    }

    public String listar(String id, boolean tipo) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Telefono> lista= this.dtelfono.listar(Integer.valueOf(id), tipo);
            if (!lista.isEmpty()) {
                String res = formatoTipo(tipo);
                for (Telefono telefono : lista) {
                    res += telefono.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "El Telefono no encontrado";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros,boolean tipo) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 2";
        if (parametros.length == 2 && esNumero(parametros[1])) {
            if (this.dtelfono.crear(parametros[0], Integer.valueOf(parametros[1]),tipo)) {
                return successMessage("Telefono Registrado exitosamente!!");
            }
            mensaje = "Error al insertar el Telefono";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros, boolean tipo) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 3";
        if (parametros.length == 3 && esNumero(parametros[0]) && esNumero(parametros[2])) {
            if (this.dtelfono.editar(Integer.valueOf(parametros[0]), parametros[1], Integer.valueOf(parametros[2]),tipo)) {
                return successMessage("Telefono editado exitosamente!!");
            }
            mensaje = "Error al editar el Telefono";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id, boolean tipo) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (this.dtelfono.eliminar(Integer.valueOf(id),tipo)) {
                return successMessage("Telefono eliminado exitosamente!!");
            }
            mensaje = "Error al eliminar el Telefono";
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

    private String formatoTipo(boolean tipo) {
        return tipo? "<h2> Lista de Telefono de Cliente </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>ID_Cliente</td>"
                        + "<td>Nombre</td>"
                        + "</tr>\n"
                :   "<h2> Lista de Telefono de Usuario </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>ID_User</td>"
                        + "<td>Nombre</td>"
                        + "</tr>\n";
    }

}
