package Presentacion;

import Dato.Usuario;
import Negocio.NActividad;
import Negocio.NAsignacionServicio;
import Negocio.NCliente;
import Negocio.NDetalleProducto;
import Negocio.NRol;
import Negocio.NServicio;
import Negocio.NTelefono;
import Negocio.NUsuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Oni
 */
public class Manejador {

    private int max = 0;
    private POP popmessage;
    private SMTP smtpmessage;

    private String correo_origen = "";

    private String comando = "";
    private String parametros = "";

    private int id;
    private String Nombre;
    private String Apellido;
    private int rol = 0;

    public Manejador() {
        popmessage = new POP();
        max = popmessage.getSize();
    }

    public void leer() {
        popmessage = new POP();
        if (popmessage.getSize() > max) {
            max++;
            boolean estado = analizarLineasSi(popmessage.getMessageArray(max));
            if (estado) {
                String personal = this.id + " - " + this.Nombre + " " + this.Apellido + " rol = " + this.rol;
                ejecutarMetodos(this.comando, this.parametros, this.rol, this.correo_origen, personal);
            } else {
                System.out.println("lo siento no se pudo mandar no se encontro el metodo.. \r\n");
            }
        }
        popmessage.cerrar();
    }

    private String getCorreoEmisor(String lineaUsuario) {
        //posiciones para usuario mail
        int posIni1 = lineaUsuario.indexOf("<");
        int posFin1 = lineaUsuario.indexOf(">");
        return lineaUsuario.substring(posIni1 + 1, posFin1);
    }

    private void enviarMensajeCorreoOrigen(String prt_mailFrom, String prt_asunto, String prt_mensaje) {
        smtpmessage = new SMTP();
        smtpmessage.sendMessage("grupo07sa@tecnoweb.org.bo", prt_mailFrom, "Consulta de : " + prt_asunto, prt_mensaje);
        smtpmessage.cerrar();

    }

    private boolean analizarLineasSi(List<String> messageArray) {

        for (String line : messageArray) {
//            System.out.println(line);
            if (line.contains("Return-Path:")) {
                //guardar correo emisor
                correo_origen = getCorreoEmisor(line);
                System.out.println(correo_origen);
                NUsuario nusuario = new NUsuario();
                ArrayList<Usuario> lista = nusuario.buscarCorreo(correo_origen);
                if (lista.isEmpty()) {
                    return false;
                }
                Usuario usuario = lista.get(0);
                this.id = usuario.getId();
                this.Nombre = usuario.getNombre();
                this.Apellido = usuario.getApellido();
                this.rol = usuario.getRol_id();
            }
            if (line.contains("Subject:")) {
                System.out.println(line);
                if (line.split(":")[1] == "" || line.split(":")[1] == " ") {
                    return false;
                }
                String subject = line.split(":")[1];
                if (subject.contains("[") && subject.contains("]")) {
                    subject = subject.substring(1);
                    System.out.println(subject);
                    this.comando = subject.split("\\[")[0];
                    if (comando == "HELP") {
                        return true;
                    }
                    if (validarComando(comando)) {
                        if (subject.split("\\[")[1].length() >= 2) {
                            this.parametros = subject.split("\\[")[1].split("\\]")[0];
                        } else {
                            this.parametros = "0";
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }
        }
        return false;
    }

    boolean validarComando(String comando) {
        String[] comandoGenerador = {
            "CLIENTE",
            "TELEFONO",
            "SERVICIO",
            "PLAN",
            "PROMOCION",
            "PRODUCTO",
            "ALMACEN",
            "CATEGORIA",
            "ACTIVIDADES",
            "VIATICO",
            "DETALLEPRODUCTO",
            "ROL",
            "USERS",
            "TEUSERS",
            "TECLIENTE",
            "ASIGNACIONSERVICIO"
        };
        String[] opciones = {
            "LIST",
            "REG",
            "EDI",
            "DEL"
        };
        for (String coman : comandoGenerador) {
            for (String opcion : opciones) {
//                System.out.println(opcion+""+coman);
//                System.out.println(comando);
                if (comando.equals(opcion + "" + coman)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void ejecutarMetodos(String comando, String parametros, int rol, String prt_mailFrom, String personal) {
        System.out.println("El comando:" + comando);
        System.out.println("Los parametros:" + parametros);
        System.out.println("la direccion origen es: " + prt_mailFrom);

        switch (rol) {
            case 1:
                permisosAdministrador(comando, parametros, rol, prt_mailFrom, personal);
                break;
            case 2:
                permisosTecnico(comando, parametros, rol, prt_mailFrom, personal);
                break;
            case 3:
                permisosSecretaria(comando, parametros, rol, prt_mailFrom, personal);
                break;
        }
    }

    private void permisosSecretaria(String comando, String parametros, int rol, String prt_mailFrom, String personal) {

    }

    private void permisosAdministrador(String comando, String parametros, int rol, String prt_mailFrom, String personal) {
        String resp = "";
        String[] arreglo;
        switch (comando) {
//---------- GESTIONAR ROL --------------------------------------------------
            case "LISTROL":
                if (!parametros.contains(",")) {
                    NRol listroles = new NRol();
                    resp = listroles.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGROL":
                if (parametros.contains(",")) {
                    NRol regroles = new NRol();
                    arreglo = parametros.split(",");
                    resp = regroles.crear(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDIROL":
                if (parametros.contains(",")) {
                    NRol ediroles = new NRol();
                    arreglo = parametros.split(",");
                    resp = ediroles.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELROL":
                if (!parametros.contains(",")) {
                    NRol delroles = new NRol();
                    resp = delroles.eliminar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
//---------- USERS --------------------------------------------------------
            case "LISTUSERS":
                if (!parametros.contains(",")) {
                    NUsuario listusuario = new NUsuario();
                    resp = listusuario.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGUSERS":
                if (parametros.contains(",")) {
                    NUsuario regusuario = new NUsuario();
                    arreglo = parametros.split(",");
                    resp = regusuario.crear(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDIUSERS":
                if (parametros.contains(",")) {
                    NUsuario ediusuario = new NUsuario();
                    arreglo = parametros.split(",");
                    resp = ediusuario.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELUSERS":
                if (!parametros.contains(",")) {
                    NUsuario delusuario = new NUsuario();
                    resp = delusuario.eliminar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;

//---------- CLIENTE ----------------------------------------------------
            case "LISTCLIENTE":
                if (!parametros.contains(",")) {
                    NCliente listcliente = new NCliente();
                    resp = listcliente.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGCLIENTE":
                if (parametros.contains(",")) {
                    NCliente regcliente = new NCliente();
                    arreglo = parametros.split(",");
                    resp = regcliente.crear(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDICLIENTE":
                if (parametros.contains(",")) {
                    NCliente edicliente = new NCliente();
                    arreglo = parametros.split(",");
                    resp = edicliente.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELCLIENTE":
                if (!parametros.contains(",")) {
                    NCliente delcliente = new NCliente();
                    resp = delcliente.eliminar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;

//------------- TELEFONO CLIENTE -----------------------------------------------
            case "LISTTECLIENTE":
                if (!parametros.contains(",")) {
                    NTelefono listtecliente = new NTelefono();
                    resp = listtecliente.listar(parametros, true);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGTECLIENTE":
                if (parametros.contains(",")) {
                    NTelefono regtecliente = new NTelefono();
                    arreglo = parametros.split(",");
                    resp = regtecliente.crear(arreglo, true);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDITELCIENTE":
                if (parametros.contains(",")) {
                    NTelefono editecliente = new NTelefono();
                    arreglo = parametros.split(",");
                    resp = editecliente.editar(arreglo, true);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELTECLIENTE":
                if (!parametros.contains(",")) {
                    NTelefono deltecliente = new NTelefono();
                    resp = deltecliente.eliminar(parametros, true);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
//------------- TELFONO USERS --------------------------------------------------
                case "LISTTEUSERS":
                if (!parametros.contains(",")) {
                    NTelefono listusuario = new NTelefono();
                    resp = listusuario.listar(parametros,false);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGTEUSERS":
                if (parametros.contains(",")) {
                    NTelefono regusuario = new NTelefono();
                    arreglo = parametros.split(",");
                    resp = regusuario.crear(arreglo,false);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDITEUSERS":
                if (parametros.contains(",")) {
                    NTelefono ediusuario = new NTelefono();
                    arreglo = parametros.split(",");
                    resp = ediusuario.editar(arreglo,false);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELTEUSERS":
                if (!parametros.contains(",")) {
                    NTelefono delusuario = new NTelefono();
                    resp = delusuario.eliminar(parametros,false);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
/*---------------------------------- ACTIVIDAD ------------------------------------------------------------------------------------*/
            case "LISTACTIVIDADES":
                if (!parametros.contains(",")) {
                    NActividad listactividad = new NActividad();
                    resp = listactividad.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGACTIVIDADES":
                if (parametros.contains(",")) {
                    NActividad regactividad = new NActividad();
                    arreglo = parametros.split(",");
                    resp = regactividad.crear(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDIACTIVIDADES":
                if (parametros.contains(",")) {
                    NActividad editactividad = new NActividad();
                    arreglo = parametros.split(",");
                    resp = editactividad.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELACTIVIDADES":
                if (!parametros.contains(",")) {
                    NActividad deltactividad = new NActividad();
                    resp = deltactividad.eliminar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
                
/* ----------------------------------DETALLES DE ACTIVIDAD PRODUCTO--------------------------------------------------------------------*/
            case "LISTDETALLEPRODUCTO":
                if (!parametros.contains(",")) {
                    NDetalleProducto listdetalle = new NDetalleProducto();
                    resp = listdetalle.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGDETALLEPRODUCTO":
                if (!parametros.contains(",")) {
                    NDetalleProducto regdetalleprod = new NDetalleProducto();
                    resp = regdetalleprod.agregaractual(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDIDETALLEPRODUCTO":
                if (parametros.contains(",")) {
                    NDetalleProducto editdetalle = new NDetalleProducto();
                    arreglo = parametros.split(",");
                    resp = editdetalle.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELDETALLEPRODUCTO":
                if (parametros.contains(",")) {
                    NDetalleProducto deldetalleprod = new NDetalleProducto();
                    arreglo = parametros.split(",");
                    resp = deldetalleprod.eliminar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
/*------------------------------------------ SERVICIOS ----------------------------------------------------------------------*/
            case "LISTSERVICIO":
                if (!parametros.contains(",")) {
                    NServicio listservicio = new NServicio();
                    resp = listservicio.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGSERVICIO":
                if (parametros.contains(",")) {
                    NServicio regservicio = new NServicio();
                    arreglo = parametros.split(",");
                    resp = regservicio.crear(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDISERVICIO":
                if (parametros.contains(",")) {
                    NServicio editservicio = new NServicio();
                    arreglo = parametros.split(",");
                    resp = editservicio.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELSERVICIO":
                if (!parametros.contains(",")) {
                    NServicio delservicio = new NServicio();
                    resp = delservicio.eliminar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
/*-------------------------------------------------------- DETALLE ASIGNACION USUARIO -------------------------------------------*/
            case "LISTASIGNACIONSERVICIO":
                if (!parametros.contains(",")) {
                    NAsignacionServicio listasigserv = new NAsignacionServicio();
                    resp = listasigserv.listar(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "REGASIGNACIONSERVICIO":
                if (!parametros.contains(",")) {
                    NAsignacionServicio regasigserv = new NAsignacionServicio();
                    resp = regasigserv.agregaractual(parametros);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "EDIASIGNACIONSERVICIO":
                if (parametros.contains(",")) {
                    NAsignacionServicio editasigserv = new NAsignacionServicio();
                    arreglo = parametros.split(",");
                    resp = editasigserv.editar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
            case "DELASIGNACIONSERVICIO":
                if (parametros.contains(",")) {
                    NAsignacionServicio delasigserv = new NAsignacionServicio();
                    arreglo = parametros.split(",");
                    resp = delasigserv.eliminar(arreglo);
                    System.out.println(resp);
                    enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp, personal));
                }
                break;
        }
    }

    private void permisosTecnico(String comando, String parametros, int rol, String prt_mailFrom, String personal) {

    }

    private String getMensajeRespuesta(String res, String personal) {
        String estilo = "<link rel='stylesheet' href='https://codepen.io/ingyas/pen/NENBOm.css'>";
        return "Content-Type:text/html;\r\n<html>" + estilo + res + "</br><p>" + personal + "</p></html>";
    }

}
