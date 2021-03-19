/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conexion.conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    PreparedStatement pps;
    conexion con = new conexion();
    Connection cone = (Connection) con.openConnection();
    
    @FXML
    private JFXComboBox<?> cmblugarnac;
    @FXML
    private JFXCheckBox cbxcomunion;
    @FXML
    private JFXCheckBox cbxconfirma;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblad.setVisible(false);
        cargarParroco();
        
    }
    
    public void cargarParroco(){
        try {
            String sql="SELECT parroco_presbitero.id_p, nombres_presbitero.id_nombre, nombres_presbitero.orden_nombre, nombre.nombre FROM parroco_presbitero, nombre, nombres_presbitero, apellido, apellidos_presbitero WHERE nombres_presbitero.id_nombre=nombre.id_nombre AND apellidos_presbitero.id_apellido=apellido.id_apellido AND parroco_presbitero.id_p=nombres_presbitero.id_p AND parroco_presbitero.id_p=apellidos_presbitero.id_p and parroco_presbitero.parroco=1 AND parroco_presbitero.parroco=1 \n" +
                    "\n" +
                    "UNION SELECT parroco_presbitero.id_p, apellidos_presbitero.id_apellido, apellidos_presbitero.orden_apellido, apellido.apellido FROM parroco_presbitero, nombre, nombres_presbitero, apellido, apellidos_presbitero WHERE nombres_presbitero.id_nombre=nombre.id_nombre AND apellidos_presbitero.id_apellido=apellido.id_apellido AND parroco_presbitero.id_p=nombres_presbitero.id_p AND parroco_presbitero.id_p=apellidos_presbitero.id_p and parroco_presbitero.parroco=1";
            PreparedStatement ps1=cone.prepareStatement(sql);
            ResultSet rs1= ps1.executeQuery();
            
            while(rs1.next()){
                String nombre= rs1.getString("orden_nombre");
//                String apellido= rs1.getString("apellido.apellido");
                txtparroco.setText(nombre /*+ " " + apellido*/);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaBautismoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    public void registrarBautismo(){
        
        
        int genero = 0;

        if (rdbM.isSelected() == true) {
            genero = 1;
        } else if (rdbF.isSelected() == true) {
            genero = 2;
        }
        
        int lugarnac = cmblugarnac.getSelectionModel().getSelectedIndex() + 1;
        int presb = cmbpresbitero.getSelectionModel().getSelectedIndex() + 1;
        int comu;
        if(cbxcomunion.isSelected()){
            comu=1;
        }else{
            comu=0;
        }
        
        int confir;
        if(cbxconfirma.isSelected()){
            confir=1;
        }else{
            confir=0;
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
       /* }else
        if(txtparroco.getText().isEmpty()){
            lblad.setVisible(true);
            lblad.setText("El parroco no puede estar vacío");
        }else
        if(cmbpresbitero.getValue()==null){
            lblad.setVisible(true);
            lblad.setText("El presbítero no puede estar vacío");*/
        }else
        if(genero==0){
            lblad.setVisible(true);
            lblad.setText("Indique el género del bautizado");
            
       /* }else
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
               lblad.setVisible(true);
               lblad.setText("El primer apellido del segundo padrino no puede ir vacío");*/
        }else{
             
            try {
                pps= cone.prepareStatement("INSERT INTO sacramento (no_libro, folio, id_parroco, fecha_bautismo, "
                        + "id_presbitero, lugar_nac, primera_comunion, confirma) VALUES(?,?,?,?,?,?,?,?)");
                pps.setString(1, txtlibro.getText());
                pps.setString(2, txtfolio.getText());
                pps.setString(3, /*txtparroco.getText()*/"1");
                pps.setString(4, txtfecha.getValue().toString());
                pps.setString(5, /*String.valueOf(presb)*/"1");
                pps.setString(6, /*String.valueOf(lugarnac)*/ "12");
                pps.setString(7, String.valueOf(comu));
                pps.setString(8, String.valueOf(confir));
                pps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(VistaBautismoController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
