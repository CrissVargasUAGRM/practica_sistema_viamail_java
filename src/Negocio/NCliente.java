package Negocio;

import Dato.Cliente;
import Dato.DCliente;
import java.util.ArrayList;

/**
 *
 * @author Oni
 */
public class NCliente {

    private DCliente dcliente;

    public NCliente() {
        this.dcliente = new DCliente();
    }

    public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Cliente> lista = dcliente.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Cliente </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>Apellido</td>"
                        + "<td>CI</td>"
                        + "<td>correo</td>"
                        + "</tr>\n";
                for (Cliente cliente : lista) {
                    res += cliente.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "El Cliente no encontrado";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 4";
        if (parametros.length == 4 && esNumero(parametros[2])) {
            if (this.dcliente.crear(parametros[0], parametros[1], Integer.valueOf(parametros[2]), parametros[3])) {
                return successMessage("Cliente Registrado exitosamente!!");
            }
            mensaje = "Error al insertar el Cliente";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 5";
        if (parametros.length == 5 && esNumero(parametros[0]) && esNumero(parametros[3])) {
            if (this.dcliente.editar(Integer.valueOf(parametros[0]), parametros[1], parametros[2], Integer.valueOf(parametros[3]), parametros[4])) {
                return successMessage("Cliente editado exitosamente!!");
            }
            mensaje = "Error al editar el Cliente";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (this.dcliente.eliminar(Integer.valueOf(id))) {
                return successMessage("Cliente eliminado exitosamente!!");
            }
            mensaje = "Error al eliminar el Cliente";
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
