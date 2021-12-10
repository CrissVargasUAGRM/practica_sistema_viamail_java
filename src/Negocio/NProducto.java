/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Dato.DProducto;
import Dato.Producto;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class NProducto {
  private DProducto dproducto;

    public NProducto() {
        this.dproducto = new DProducto();
    }
    
        public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Producto> lista = dproducto.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Producto </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>Cantidad</td>"
                        + "<td>Codigo</td>"
                        + "<td>Almacen_id</td>"
                        + "<td>Categoria_id</td>"
                        + "</tr>\n";
                for (Producto producto : lista) {
                    res += producto.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "El Producto no encontrado";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tien : " + parametros.length + " deberia ser solo 6";
        if (parametros.length == 6 && esNumero(parametros[5])) {
            if (dproducto.crear(parametros[0], Integer.valueOf(parametros[1]),Integer.valueOf(parametros[2]),Integer.valueOf(parametros[3]),Integer.valueOf(parametros[4]))) {
                return successMessage("Producto Registrado exitosamente!!");
            }
            mensaje = "Error al insertar el Producto";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 6";
        if (parametros.length == 6 && esNumero(parametros[0]) && esNumero(parametros[5])) {
            if (dproducto.editar(Integer.valueOf(parametros[0]),parametros[1], Integer.valueOf(parametros[2]),Integer.valueOf(parametros[3]),Integer.valueOf(parametros[4]),Integer.valueOf(parametros[5]))) {
                return successMessage("Producto editado exitosamente!!");
            }
            mensaje = "Error al editar el Producto";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (dproducto.eliminar(Integer.valueOf(id))) {
                return successMessage("Producto eliminado exitosamente!!");
            }
            mensaje = "Error al eliminar el Producto";
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
