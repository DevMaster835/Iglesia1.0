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
import javafx.scene.input.KeyEvent;

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
        lblad.setVisible(false);
        
    }

    
    @FXML
    public void registrarBautismo(){
        
        int genero = 0;

        if (rdbM.isSelected() == true) {
            genero = 1;
        } else if (rdbF.isSelected() == true) {
            genero = 2;
        }
        
        if(txtlibro.getText().isEmpty()){
           lblad.setVisible(true);
           lblad.setText("El No. de libro no puede estar vacío");
        }else
        if(txtfolio.getText().isEmpty()){
            lblad.setVisible(true);
            lblad.setText("El folio no puede estar vacío");
        }else
        if(txtfecha.getValue()==null){
            lblad.setVisible(true);
            lblad.setText("La fecha no puede estar vacía");
        }else
        if(txtparroco.getText().isEmpty()){
            lblad.setVisible(true);
            lblad.setText("El parroco no puede estar vacío");
        }else
        if(cmbpresbitero.getValue()==null){
            lblad.setVisible(true);
            lblad.setText("El presbítero no puede estar vacío");
        }else
        if(genero==0){
            lblad.setVisible(true);
            lblad.setText("Indique el género del bautizado");
            
        }else
         if(txtprimernb.getText().isEmpty()){
             lblad.setVisible(true);
             lblad.setText("El primer nombre del bautizado no puede ir vacío");
        }else
           if(txtprimerab.getText().isEmpty()){
              lblad.setVisible(true);
              lblad.setText("El primer apellido del bautizado no puede ir vacío"); 
        }else
            if(txtprimernp.getText().isEmpty()){
               lblad.setVisible(true);
              lblad.setText("El primer nombre del padre no puede ir vacío");  
        }else
            if(txtprimerap.getText().isEmpty()){
                lblad.setVisible(true);
                lblad.setText("El primer apellido del padre no puede ir vacío");  
        }else
            if(txtprimernm.getText().isEmpty()){
               lblad.setVisible(true);
               lblad.setText("El primer nombre de la madre no puede ir vacío");   
        }else
            if(txtprimeram.getText().isEmpty()){
               lblad.setVisible(true);
               lblad.setText("El primer apellido de la madre no puede ir vacío");    
        }else
            if(txtprimerpad1.getText().isEmpty()){
               lblad.setVisible(true);
               lblad.setText("El primer nombre del primer padrino no puede ir vacío");      
        }else
            if(txtprimerapad1.getText().isEmpty()){
               lblad.setVisible(true);
               lblad.setText("El primer apellido del primer padrino no puede ir vacío");       
        }else
            if(txtprimerpad2.getText().isEmpty()){
               lblad.setVisible(true);
               lblad.setText("El primer nombre del segundo padrino no puede ir vacío");        
        }else
            if(txtprimerapad2.getText().isEmpty()){
                
            }
        
    }

    @FXML
    private void txtlibroKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            lblad.setVisible(true);
            lblad.setText("Sólo se permiten números");
        }else{
            lblad.setVisible(false);
        }
    }

    @FXML
    private void txtfolioKeyTyped(KeyEvent event) {
    }
    
}
