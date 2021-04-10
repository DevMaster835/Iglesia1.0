/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.conexion;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.Bautismo;


/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaBuscarBautismosController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    ObservableList<Bautismo>listaBautismos;
    
    @FXML
    private TableColumn<?, ?> clBautismo;
    @FXML
    private TableColumn<?, ?> clfechaB;
    @FXML
    private TableColumn<?, ?> clBautizado;
    @FXML
    private TableColumn<?, ?> clfechaNac;
    @FXML
    private TableColumn<?, ?> clMadre;
    @FXML
    private TableColumn<?, ?> clPadre;
    @FXML
    private TableColumn<?, ?> clPadrino;
    @FXML
    private TableColumn<?, ?> clMadrina;
    @FXML
    private TableView<Bautismo> tablaBautismo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaBautismos=FXCollections.observableArrayList();
        Bautismo.llenarTabla(cone, listaBautismos);
        tablaBautismo.setItems(listaBautismos);
        
        clBautismo.setCellValueFactory(new PropertyValueFactory("noBautismo"));
        clfechaB.setCellValueFactory(new PropertyValueFactory("fechaB"));
        clBautizado.setCellValueFactory(new PropertyValueFactory("nombreB"));
        clfechaNac.setCellValueFactory(new PropertyValueFactory("fechaNac"));
        clMadre.setCellValueFactory(new PropertyValueFactory("madre"));
        clPadre.setCellValueFactory(new PropertyValueFactory("padre"));
        clPadrino.setCellValueFactory(new PropertyValueFactory("padrino"));
        clMadrina.setCellValueFactory(new PropertyValueFactory("madrina"));
        
        // TODO
    }    
    
}
