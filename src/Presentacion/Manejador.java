package Presentacion;

import Dato.Usuario;
import Negocio.NRol;
import Negocio.NUsuario;
import java.util.ArrayList;
import java.util.List;

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
                ejecutarMetodos(this.comando, this.parametros, this.rol, this.correo_origen);
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
//                System.out.println(line);
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
            "USERS"
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

    private void ejecutarMetodos(String comando, String parametros, int rol, String prt_mailFrom) {
        System.out.println("El comando:" + this.comando);
        System.out.println("Los parametros:" + this.parametros);
        System.out.println("la direccion origen es: " + prt_mailFrom);
        String resp = "";
        String[] arreglo;
        
        switch (comando) {
            case "LISTROL":
                NRol listroles = new NRol();
                if (parametros.contains(",")) {
                    break;
                }
                resp = listroles.listar(parametros);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "REGROL":
                NRol regroles = new NRol();
                arreglo = parametros.split(",");
                resp = regroles.crear(arreglo);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "EDIROL":
                NRol ediroles = new NRol();
                arreglo = parametros.split(",");
                resp = ediroles.editar(arreglo);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "DELROL":
                NRol delroles = new NRol();
                if (parametros.contains(",")) {
                    break;
                }
                resp = delroles.eliminar(parametros);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "LISTUSERS":
                NUsuario listusuario = new NUsuario();
                if (parametros.contains(",")) {
                    break;
                }
                resp = listusuario.listar(parametros);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "REGUSERS":
                NUsuario regusuario = new NUsuario();
                arreglo = parametros.split(",");
                resp = regusuario.crear(arreglo);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "EDIUSERS":
                NUsuario ediusuario = new NUsuario();
                arreglo = parametros.split(",");
                resp = ediusuario.editar(arreglo);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
            case "DELUSERS":
                NUsuario delusuario = new NUsuario();
                if (parametros.contains(",")) {
                    break;
                }
                resp = delusuario.eliminar(parametros);
                System.out.println(resp);
                enviarMensajeCorreoOrigen(prt_mailFrom, comando + " + " + parametros, getMensajeRespuesta(resp));
                break;
        }
    }

    public String getMensajeRespuesta(String res) {

        String estilo = "<link rel='stylesheet' href='https://codepen.io/ingyas/pen/NENBOm.css'>";
        return "Content-Type:text/html;\r\n<html>" + estilo + res + "</html>";
    }

}
