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
public class POP {
    private Socket skCliente;
    private String HOST = "mail.tecnoweb.org.bo";
    private int PORT=110;
    private BufferedReader entrada;
    private DataOutputStream salida;
    String user_local="grupo07sa";
    String pass_local="grup007grup007";
    
    
    public POP() {
        try {
            skCliente=new Socket(HOST,PORT);
            entrada=new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            salida=new DataOutputStream(skCliente.getOutputStream());
            entrada.readLine();
            salida.writeBytes("USER "+user_local+"\r\n");
            entrada.readLine();
            salida.writeBytes("PASS "+pass_local+"\r\n");
            entrada.readLine();
        } catch (Exception e) {
            System.out.println("error en la conexion... al iniciar POPMessage... "+ e);
        }
    }
    public String getMessage(int nro){
        String lines="";
        try {
            salida.writeBytes("RETR "+nro+"\r\n");
            lines=leerLineas(entrada).toString();
            System.out.println("mensaje leido");
        } catch (Exception e) {
            System.out.println(".. error"+e);
        }
        return lines;
    }
    public List<String> getMessageArray(int nro){
        List<String> lines=new ArrayList<String>();
        try {
            salida.writeBytes("RETR "+nro+"\r\n");
            lines=leerLineas(entrada);
            System.out.println("mensaje leido");
        } catch (Exception e) {
            System.out.println(".. error"+e);
        }
        return lines;
    }
     public int getSize(){
        int cant=0;
        try {
            salida.writeBytes("LIST\r\n");
            String texto=entrada.readLine();
            String[] palabras=texto.split(" ");
            cant=Integer.valueOf(palabras[1]);
            leerLineas(entrada);
        } catch (Exception e) {
            System.out.println("error en la conexion... size... "+e);
        }
        return cant;
    }
    public void vaciarMail(){
        try{
            int cantidad=getSize();
            for (int i = 1; i < cantidad; i++) {
                salida.writeBytes("DELE "+i+"\r\n");
                System.out.println("elimando "+i);
            }
        }catch(Exception e){
            System.out.println("error en la conexion... size... "+e);
        }
    }
    
    public void cerrar(){
        try {
            salida.writeBytes("QUIT\r\n");
            System.out.println("Se ha iniciado el servidor "+entrada.readLine());
            entrada.close();
            salida.close();
            skCliente.close();
        } catch (Exception e) {
            System.out.println("error en la conexion... cerrra."+e);
        }
    }
    private List<String> leerLineas(BufferedReader in) {
        List<String> lines=new ArrayList<String>();
        try{
        while (true){
            String line = in.readLine();
            if (line == null){
            }
            if (line.equals(".")){
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')){
                line = line.substring(1);
            }            
            lines.add(line);
        }   }catch(Exception e){
            System.out.println("ERROR .. "+e);
        }
        return lines;
    }
    public List<String> getList(){
        List<String> lines=new ArrayList<String>();
        try {
            salida.writeBytes("LIST\r\n");
            entrada.readLine();
            lines=leerLineas(entrada);
        } catch (Exception e) {
            System.out.println(".. error"+e);
        }
        return lines;
    } 
}
