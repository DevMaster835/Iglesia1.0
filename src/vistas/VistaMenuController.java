/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irbautismo(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/vistaBautismo.fxml"));
            Pane ventana = (Pane) loader.load();    
            Scene scene= new Scene(ventana);
            Stage primaryStage= new Stage();
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.DECORATED);           
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void irpresbiteros(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/vistaRegistroPres.fxml"));
            Pane ventana = (Pane) loader.load();    
            Scene scene= new Scene(ventana);
            Stage primaryStage= new Stage();
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.DECORATED);           
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void irMatrimonio(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/vistaMatrimonio.fxml"));
            Pane ventana = (Pane) loader.load();    
            Scene scene= new Scene(ventana);
            Stage primaryStage= new Stage();
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.DECORATED);           
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
