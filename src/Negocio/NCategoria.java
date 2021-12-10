/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Dato.DCategoria;
import Dato.Categoria;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class NCategoria {
    private DCategoria dcategoria;
    
     public NCategoria() {
        this.dcategoria = new DCategoria();
    }

    public String listar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            ArrayList<Categoria> lista = dcategoria.listar(Integer.valueOf(id));
            if (!lista.isEmpty()) {
                String res = "<h2> Lista de Rol </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Nombre</td>"
                        + "<td>Descripcion</td>"
                        + "</tr>\n";
                for (Categoria categoria : lista) {
                    res += categoria.toString();
                }
                res += "</table>";
                return res;
            } else {
                mensaje = "Categoria no encontrada";
            }
        }
        return mensaje;
    }

    public String crear(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 2";
        if (parametros.length == 2) {
            if (dcategoria.crear(parametros[0], parametros[1])) {
                return successMessage("Categoria Registrado exitosamente!!");
            }
            mensaje = "Error al insertar la Categoria";
        }
        return errorMessage(mensaje);
    }

    public String editar(String[] parametros) {
        String mensaje = "Error de parametros tiene : " + parametros.length + " deberia ser solo 3";
        if (parametros.length == 3 && esNumero(parametros[0])) {
            if (dcategoria.editar(Integer.valueOf(parametros[0]), parametros[1], parametros[2])) {
                return successMessage("Categoria editado exitosamente!!");
            }
            mensaje = "Error al editar la Categoria";
        }
        return errorMessage(mensaje);
    }

    public String eliminar(String id) {
        String mensaje = "Error de parametros tiene : " + id + " deberia ser numerico";
        if (esNumero(id)) {
            if (dcategoria.eliminar(Integer.valueOf(id))) {
                return successMessage("Categoria eliminada exitosamente!!");
            }
            mensaje = "Error al eliminar la Categoria";
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
