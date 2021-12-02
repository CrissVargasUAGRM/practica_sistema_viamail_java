/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Oni
 */
public class Conexion {
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String DB = "db_grupo07sa";
    private final String USER = "grupo07sa";
    private final String PASSWORD  = "grup007grup007";
    private final String URL = "jdbc:postgresql://www.tecnoweb.org.bo/";
    
    private static Conexion instancia;
    private Connection con;
    
    private Conexion(){
        this.con = null;
    }
    
    public Connection conectar(){
        
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(this.URL+this.DB,this.USER,this.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error DB: "+e);
        }
        return this.con;
    }
    
    public void desconectar(){
        try {
            this.con.close();
//            System.out.println("desconectado :"+(this.con==null));
        } catch (SQLException e) {
            System.out.println("Error DB: "+e);
        }
    }
    
    
    public static Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }

//    public static void main(String[] args) {
//        Conexion con = getInstancia();
//        if (con.conectar()!= null) {
//            System.out.println("siu");
//        }
//        con.desconectar();
//    }
}