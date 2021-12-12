/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dato.DServicio;
import Dato.Servicio;
import java.util.ArrayList;

/**
 *
 * @author Christian Vargas
 */
public class NServicio {
    private DServicio dservicio;

    public NServicio() {
        this.dservicio = new DServicio();
    }
    
    public String listar(String id){
        String mensaje = "Error de parametros tiene: "+id+" deberia ser numerico";
        if(esNumero(id)){
            ArrayList<Servicio> lista = dservicio.listar(Integer.valueOf(id));
            if(!lista.isEmpty()){
                String res = "<h2> Lista de Servicios </h2>\n"
                        + "<table>\n"
                        + "<tr>"
                        + "<td>ID</td>"
                        + "<td>Direccion</td>"
                        + "<td>Codigo</td>"
                        + "<td>Tipo</td>"
                        + "<td>Estado</td>"
                        + "<td>Plan</td>"
                        + "</tr>\n";
                for (Servicio servicio : lista) {
                    res += servicio.toString();
                }
                res+="</table>";
                return res;
            }else{
                mensaje = "El servicio no fuen encontrado";
            }
        }
        return mensaje;
    }
    
    public String crear(String[] parametros){
        String mensaje = "Error de parametros tiene: "+parametros.length+" deberia ser 5";
        if(parametros.length == 5){
            if(this.dservicio.crear(parametros[0], parametros[1], parametros[2], parametros[3], parametros[4])){
                return successMessage("Servicio registrado con exito");
            }
            mensaje = "Error al insertar el servicio";
        }
        return errorMessage(mensaje);
    }
    
    public String editar(String[] parametros){
        String mensaje = "Error de parametros tiene: "+parametros.length+" deberia ser solo 6";
        if(parametros.length == 6 && esNumero(parametros[0])){
            if(dservicio.editar(Integer.valueOf(parametros[0]), parametros[1], parametros[2], parametros[3], parametros[4], parametros[5])){
                return successMessage("Servicio editado exitosamente");
            }
            mensaje = "Error al editar el servicio";
        }
        return errorMessage(mensaje);
    }
    
    public String eliminar(String id){
        String mensaje = "Error de parametros tiene: "+id+" deberia ser numerico";
        if(esNumero(id)){
            if(this.dservicio.eliminar(Integer.valueOf(id))){
                return successMessage("Servicio eliminado exitosamente!");
            }
            mensaje = "Error al eliminar el servicio";
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
