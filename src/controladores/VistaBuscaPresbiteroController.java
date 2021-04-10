/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import conexion.conexion;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modelos.Presbiteros;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaBuscaPresbiteroController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    ObservableList<Presbiteros> listaPresb;
    
    @FXML
    private JFXTextField txtparroco;

    @FXML
    private JFXDatePicker txtfecha;

    @FXML
    private JFXButton btnbuscar;

    @FXML
    private TableView<Presbiteros> tblPresbiteros;

    @FXML
    private TableColumn<?, ?> colnombre;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colParroco;

    @FXML
    private TableColumn<?, ?> colDuracion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaPresb=FXCollections.observableArrayList();
        Presbiteros.llenarTabla(cone, listaPresb);
        tblPresbiteros.setItems(listaPresb);
        
        colnombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colParroco.setCellValueFactory(new PropertyValueFactory("parroco"));
        colDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        // TODO
    } 
    
    @FXML
    private void buscarPresbitero(KeyEvent event){
        FilteredList<Presbiteros> filteredData = new FilteredList<>(listaPresb, p -> true);
        tblPresbiteros.setItems(filteredData);

        txtparroco.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(presbitero -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = presbitero.getNombre().toLowerCase();
                return lowerCaseFilter.contains(newValue.toLowerCase());

               /* if (presbitero.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (presbitero.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }*/

            });
        });

        SortedList<Presbiteros> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblPresbiteros.comparatorProperty());

        tblPresbiteros.setItems(sortedData); 
    }
    
   /* public void seleccionar(){
        tblPresbiteros.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Presbiteros>(){
            @Override
            public void changed(ObservableValue<? extends Presbiteros> arg0,
                Presbiteros valorAnteriror, Presbiteros valorS){
                
                if(valorS!=null){
                    
                }
        }
            )
    }
    }
    */
    /* @FXML
    void buscarPresbitero(KeyEvent event) {
        
    }*/
    
}
