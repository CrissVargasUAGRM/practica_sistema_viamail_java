/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dato.AsignacionServicio;
import Dato.DAsignacionServicio;
import java.util.ArrayList;

/**
 *
 * @author Christian Vargas
 */
public class NAsignacionServicio {
    private DAsignacionServicio dasignacionserv;

    public NAsignacionServicio() {
        this.dasignacionserv = new DAsignacionServicio();
    }
    
    public String listar(String id){
        String mensaje = "Error de parametros tiene: "+id+" deberia ser numerico";
        if(esNumero(id)){
            ArrayList<AsignacionServicio> lista = dasignacionserv.listar(Integer.valueOf(id));
            if(!lista.isEmpty()){
                String res = "<h2> Lista de detalle asignacion servicio </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID servicio</td>"
                        + "<td>Nombre tecnico</td>"
                        + "</tr>\n";
                for (AsignacionServicio asignacionServicio : lista) {
                    res += asignacionServicio.toString();
                }
                res += "</table>";
                return res;
            }else{
                mensaje = "Detalle no encontrado";
            }
        }
        return mensaje;
    }
    
    public String agregaractual(String nombre){
        String mensaje = "Error de parametros tiene "+nombre+" deberia ser un texto";
        if(!nombre.isEmpty()){
            if(this.dasignacionserv.addAsignacionServicio(nombre)){
                return successMessage("Detalle registrado exitosamente!");
            }
            mensaje = "Error al insertar el detalle";
        }
        return errorMessage(mensaje);
    }
    
    public String editar(String[] parametros){
        String mensaje = "Error de parametros tiene: "+parametros.length+"deberia ser solo 3";
        if(parametros.length == 3 && esNumero(parametros[0]) && esNumero(parametros[1]) && esNumero(parametros[2])){
            if(dasignacionserv.editar(Integer.valueOf(parametros[0]), Integer.valueOf(parametros[1]), Integer.valueOf(parametros[2]))){
                return successMessage("Detalle de asignacion y usuario actualizado!");
            }
            mensaje ="Error al editar el detalle de la asignacion y el usuario";
        }
        return errorMessage(mensaje);
    }
    
    public String eliminar(String[] parametros){
        String mensaje = "Error de parametros tiene: "+parametros.length+" deberia ser 2 parametros";
        if(parametros.length == 2 && esNumero(parametros[0]) && esNumero(parametros[1])){
            if(dasignacionserv.eliminar(Integer.valueOf(parametros[0]), Integer.valueOf(parametros[1]))){
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
