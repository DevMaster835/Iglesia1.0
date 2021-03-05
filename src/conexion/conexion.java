/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author José
 */
public class conexion {
    private static Connection con;
   // private Statement statement;

    public static Connection getCon() {
        return con;
    }
    
    public Connection openConnection(){
    
        if(con==null){
            String url="jdbc:mysql://localhost:3306/parroquia?autoReconnect=true&useSSL=false";
           // String db="devmaster";
            String driver= "com.mysql.jdbc.Driver";
            String user="root";
            String password="";
            
            try{
                Class.forName(driver);
                this.con= (Connection) DriverManager.getConnection(url, user, password);
                System.out.println("Conexión exitosa");
                
            }catch(ClassNotFoundException | SQLException sqle){
                System.out.println("Error en la conexión");
            }
        }
        return con;
}
    
}
