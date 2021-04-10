/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author José
 */
public class Presbiteros {
    int id_p;
    String nombre;
    String apellido;
    OrdenesPresbitero orden;
    String parroco;
    String fechaInicio;
    String duracion;

    public Presbiteros(int id_p, String nombre, String apellido, OrdenesPresbitero orden, String parroco, String fechaInicio, String duracion) {
        this.id_p = id_p;
        this.nombre = nombre;
        this.apellido = apellido;
        this.orden = orden;
        this.parroco = parroco;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
    }

    public Presbiteros(String nombre, String parroco, String fechaInicio, String duracion) {
        this.nombre = nombre;
        this.parroco = parroco;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
    }

    public Presbiteros(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    

    public int getId_p() {
        return id_p;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public OrdenesPresbitero getOrden() {
        return orden;
    }

    public String getParroco() {
        return parroco;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getDuracion() {
        return duracion;
    }
    
    public static void llenarTabla(Connection cone, ObservableList <Presbiteros>lista ){
        try {
            String sq="";
            
            String sql="SELECT parroco_presbitero.id_p, nombres_presbitero.id_nombre, nombres_presbitero.orden_nombre, CONCAT(nombre.nombre, ' ', apellido.apellido), apellidos_presbitero.id_apellido, DATE_FORMAT(fecha_inicio, '%d-%m-%Y') AS fechaInicio  , if(parroco=1, 'Párroco', 'Presbítero'),  IF(duracion is null,'Actual', DATE_FORMAT(DATE_ADD(fecha_inicio, INTERVAL duracion DAY), '%d-%m-%Y')) as fechaFin FROM parroco_presbitero LEFT JOIN nombres_presbitero ON nombres_presbitero.id_p = parroco_presbitero.id_p LEFT JOIN nombre ON nombres_presbitero.id_nombre = nombre.id_nombre LEFT JOIN apellidos_presbitero ON apellidos_presbitero.id_p = parroco_presbitero.id_p LEFT JOIN apellido ON apellidos_presbitero.id_apellido = apellido.id_apellido  GROUP BY parroco_presbitero.id_p ORDER BY duracion='Actual'";
            Statement st= cone.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                lista.add(
                        new Presbiteros(
                              rs.getString("CONCAT(nombre.nombre, ' ', apellido.apellido)"),
                              rs.getString("if(parroco=1, 'Párroco', 'Presbítero')"),
                              rs.getString("fechaInicio"),
                              rs.getString("fechaFin")
                        )
                        );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Presbiteros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void llenarCombo(Connection cone, ObservableList <Presbiteros>lista){
        try {
            String sql="SELECT parroco_presbitero.id_p, nombres_presbitero.id_nombre, nombres_presbitero.orden_nombre, CONCAT(nombre.nombre, ' ', apellido.apellido) as nombre FROM parroco_presbitero LEFT JOIN nombres_presbitero ON nombres_presbitero.id_p = parroco_presbitero.id_p LEFT JOIN nombre ON nombres_presbitero.id_nombre = nombre.id_nombre LEFT JOIN apellidos_presbitero ON apellidos_presbitero.id_p = parroco_presbitero.id_p LEFT JOIN apellido ON apellidos_presbitero.id_apellido = apellido.id_apellido WHERE parroco_presbitero.duracion IS null GROUP BY parroco_presbitero.id_p";
            Statement st= cone.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                lista.add(
                    new Presbiteros(
                            rs.getString("nombre")
                    )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Presbiteros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
