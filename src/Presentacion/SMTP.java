/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oni
 */
public class SMTP {
    private Socket skCliente;
    private String HOST = "mail.tecnoweb.org.bo";
    private int PORT = 25;
    private BufferedReader entrada;
    private DataOutputStream salida;
    
    public SMTP() {
        try {
            skCliente = new Socket(HOST, PORT);
            entrada = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            salida = new DataOutputStream(skCliente.getOutputStream());
        } catch (Exception e) {
            System.out.println("message....error  iniciar " + e);
        }
    }
    public void sendMessage(String fromTo,String rcptTo,String subject,String message){
        try {
            System.out.println(entrada.readLine());
            salida.writeBytes("EHLO mail.tecnoweb.org.bo\r\n");
            entrada.readLine();
            leerLineas(entrada);
            salida.writeBytes("MAIL FROM:"+fromTo+"\r\n");
            entrada.readLine();   
            salida.writeBytes("RCPT TO:"+rcptTo+"\r\n");
            entrada.readLine();
            salida.writeBytes("DATA\r\n");  
            entrada.readLine();
            String comando="Subject:"+subject+"\r\n"+message+"\n"+".\r\n";
            salida.writeBytes(comando);
            entrada.readLine();
            System.out.println("correo grupo:"+fromTo);
            System.out.println("correo origen de destino:"+rcptTo);
            System.out.println("mensaje de respuesta enviado");
        } catch (Exception e) {
            System.out.println("message....al querer escribir mensaje " + e);
        }
    }
    
    public List<String> leerLineas(BufferedReader in) {
        List<String> lines = new ArrayList<String>();
        try {
           while (true){
            String line = in.readLine();
            if (line == null){
            }
            if (line.charAt(3)==' '){
                lines.add(line);
                break;
            }           
            lines.add(line);
        }        
        } catch (Exception e) {
        }
        return lines;
    }

    public void cerrar() {
        try {
            salida.writeBytes("QUIT\r\n");
            entrada.readLine();
            entrada.close();
            salida.close();
            skCliente.close();
        } catch (Exception e) {
            System.out.println("error al cerrar " + e);
        }
    }
}
