/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.conexion;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author José
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/vistaMenu.fxml"));
            Pane ventana = (Pane) loader.load();    
            Scene scene= new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.DECORATED);           
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
       conexion con = new conexion();
       Connection cone = con.openConnection();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
