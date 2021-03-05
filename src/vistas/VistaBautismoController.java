/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaBautismoController implements Initializable {

    @FXML
    private JFXTextField txtlibro;
    @FXML
    private JFXTextField txtfolio;
    @FXML
    private JFXDatePicker txtfecha;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXTextField txtprimernb;
    @FXML
    private JFXTextField txtsegundonb;
    @FXML
    private JFXTextField txttercernb;
    @FXML
    private JFXTextField txtprimerab;
    @FXML
    private JFXTextField txtsegundoab;
    @FXML
    private JFXTextField txtprimernp;
    @FXML
    private JFXTextField txtsegundonp;
    @FXML
    private JFXTextField txttercernp;
    @FXML
    private JFXTextField txtprimerap;
    @FXML
    private JFXTextField txtsegundoap;
    @FXML
    private JFXTextField txtprimernm;
    @FXML
    private JFXTextField txtsegundonm;
    @FXML
    private JFXTextField txttercernm;
    @FXML
    private JFXTextField txtprimeram;
    @FXML
    private JFXTextField txtsegundoam;
    @FXML
    private JFXTextField txtprimerpad1;
    @FXML
    private JFXTextField txtsegundopad1;
    @FXML
    private JFXTextField txttercerpad1;
    @FXML
    private JFXTextField txtprimerapad1;
    @FXML
    private JFXTextField txtsegundoapad1;
    @FXML
    private JFXTextField txtprimerpad2;
    @FXML
    private JFXTextField txtsegundopad2;
    @FXML
    private JFXTextField txttercerpad2;
    @FXML
    private JFXTextField txtprimerapad2;
    @FXML
    private JFXTextField txtsegundoapad2;
    @FXML
    private JFXRadioButton rdbM;
    @FXML
    private JFXRadioButton rdbF;
    @FXML
    private JFXTextField txtparroco;
    @FXML
    private JFXComboBox<?> cmbpresbitero;
    @FXML
    private Label lblad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    
    public void registrarBautismo(){
        
        if(txtlibro.getText().isEmpty()){
            
           lblad.setText("El No. de libro no puede estar vacío");
        }
    }
    
}
