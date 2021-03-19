/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
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
public class OrdenesPresbitero {
    int id_orden;
    String orden;

    public OrdenesPresbitero(int id_orden, String orden) {
        this.id_orden = id_orden;
        this.orden = orden;
    }

    public int getId_orden() {
        return id_orden;
    }

    public String getOrden() {
        return orden;
    }

    public static void llenarOrden(Connection cone, ObservableList <OrdenesPresbitero>lista){
        try {
            Statement statement=cone.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM orden_presbitero");
            while(rs.next()){
                lista.add(
                        new OrdenesPresbitero(
                                rs.getInt("id_orden"),
                                rs.getString("orden")
                        ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenesPresbitero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return orden;
    }
    
    
    
}
