/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dato.DDetalleProducto;
import Dato.detalleproducto;
import java.util.ArrayList;

/**
 *
 * @author Christian Vargas
 */
public class NDetalleProducto {
    private DDetalleProducto ddetalleproducto;

    public NDetalleProducto() {
        this.ddetalleproducto = new DDetalleProducto();
    }
    
    public String listar(String id){
        String mensaje = "Error de parametros tiene: "+id+"deberia ser numerico";
        if(esNumero(id)){
            ArrayList<detalleproducto> lista = ddetalleproducto.listar(Integer.valueOf(id));
            if(!lista.isEmpty()){
                String res = "<h2> Lista de detalle actividad producto </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID actividad</td>"
                        + "<td>Nombre producto</td>"
                        + "</tr>\n";
                for (detalleproducto object : lista) {
                    res += object.toString();
                }
                res += "</table>";
                return res;
            }else{
                mensaje = "Detalle no encontrados!";
            }
        }
        return mensaje;
    }
    
    public String agregaractual(String producto){
        String mensaje = "Error de parametros tiene "+producto+" deberia ser una cadena";
        if(!producto.isEmpty()){
            if(this.ddetalleproducto.addDetalleProducto(producto)){
                return successMessage("Detalle registrado exitosamente!");
            }
            mensaje = "Error al insertar el detalle";
        }
        return errorMessage(mensaje);
    }
    
    public String editar(String[] parametros){
        String mensaje = "Error de parametros tiene: "+parametros.length+" deberia ser solo 2";
        if(parametros.length == 3 && esNumero(parametros[0]) && esNumero(parametros[1]) && esNumero(parametros[2])){
            if(ddetalleproducto.editar(Integer.valueOf(parametros[0]), Integer.valueOf(parametros[1]), Integer.valueOf(parametros[2]))){
                return successMessage("Detalle de actividad y producto actualizado!");
            }
            mensaje = "Error al editar el detalle de la actividad y el producto";
        }
        return errorMessage(mensaje);
    }
    
    public String eliminar(String[] parametros){
        String mensaje = "Error de parametros tiene: "+parametros.length+" deberia ser 2 parametros";
        if(parametros.length == 2 && esNumero(parametros[0]) && esNumero(parametros[1])){
            if(ddetalleproducto.eliminar(Integer.valueOf(parametros[0]), Integer.valueOf(parametros[1]))){
                return successMessage("Eliminado con exito");
            }
            mensaje = "Error al eliminar";
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
