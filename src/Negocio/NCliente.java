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
    
    public String listar(int id) {
        ArrayList<Cliente> lista = dcliente.listar(id);
        if (!lista.isEmpty()) {
            String res = "<h2> Lista de Cliente </h2>"
                    + "<table>"
                    + "<tr>"
                    + "<td>ID</td>"
                    + "<td>Nombre</td>"
                    + "<td>Apellido</td>"
                    + "<td>CI</td>"
                    + "<td>correo</td>"
                    + "</tr>";
            for (Cliente cliente : lista) {
                res += cliente.toString();
            }
            res += "</table>";
            return res;
        } else {
            return "<h1>El patron no encontrado</h1>";
        }
    }
}
