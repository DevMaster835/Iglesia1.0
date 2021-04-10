/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import conexion.conexion;
import controladores.main;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.OrdenesPresbitero;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaRegistroPresController implements Initializable {

    @FXML
    private JFXDatePicker txtfechaInicio;
    @FXML
    private JFXDatePicker txtfechaFin;
    @FXML
    private JFXCheckBox cbxparroco;
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
    private JFXComboBox<OrdenesPresbitero> cmborden;
    @FXML
    private Label lblad;

   
    PreparedStatement pps;
    ResultSet rs;
    
    ObservableList<OrdenesPresbitero> listaOrdenes;
    
    conexion con =  new conexion();
    Connection cone= con.openConnection();
    @FXML
    private JFXButton btnBuscar;
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblad.setVisible(false);
        
        //inicializar combobox ordenes del presbitero
        listaOrdenes=FXCollections.observableArrayList();
        
        //Se llena el combobox
        OrdenesPresbitero.llenarOrden(cone, listaOrdenes);
        
        //Enlazar la lista
        cmborden.setItems(listaOrdenes);
        
    }
    
    public void limpiar(){
        cmborden.setValue(null);
        txtprimernp.setText(" ");
        txtsegundonp.setText(" ");
        txttercernp.setText(" ");
        txtprimerap.setText(" ");
        txtsegundoap.setText(" ");
        txtfechaInicio.setValue(null);
        txtfechaFin.setValue(null);
    }

    @FXML
    public void registrarPresbitero(){
        int n1=1, n2=2, n3=3, ap1=1, ap2=2;
        
        
        if(txtprimernp.getText().isEmpty()){
            lblad.setVisible(true);
            lblad.setText("El primer nombre del presbítero no debe ir vacío");
        }else
           if(txtprimerap.getText().isEmpty()){
               lblad.setVisible(true);
               lblad.setText("El primer apellido del presbítero no debe ir vacío");
        }else
            if(cmborden.getValue()==null){
               lblad.setVisible(true);
               lblad.setText("La orden del presbítero no debe ir vacío");
        }else
            if(txtfechaInicio.getValue()==null){
                lblad.setVisible(true);
                lblad.setText("La fecha de inicio no debe ir vacío");
        /*}else
            if(txtfechaFin.getValue()==null){
                lblad.setVisible(true);
                lblad.setText("La fecha de fin no debe ir vacío");*/
        }else{
           
            try {
                int orden = cmborden.getSelectionModel().getSelectedIndex() + 1;
                int parroco;
                
                if(cbxparroco.isSelected()){
                    parroco=1;
                }else{
                    parroco=0;
                }
               /* pps=cone.prepareStatement("SELECT nombre FROM nombre WHERE nombre=?");
                pps.*/
                
               //GUARDA EN LA TABLA PARROCO_PRESBITERO
            /*    pps=cone.prepareStatement("INSERT INTO parroco_presbitero(id_orden,parroco,fecha_inicio) "
                        + " VALUES (?,?,?)");
                pps.setString(1, String.valueOf(orden));
                pps.setString(2, String.valueOf(parroco));
                pps.setString(3, txtfechaInicio.getValue().toString());
//              pps.setString(4, txtfechaFin.getValue().toString());
                pps.executeUpdate();
                
                pps=cone.prepareStatement("SELECT count(id_p) FROM parroco_presbitero");
                ResultSet rst= pps.executeQuery();
                int idp=0;
                while(rst.next()){
                     idp=rst.getInt("count(id_p)");
                    System.out.print(idp);
                }
                
                //GUARDA PRIMER NOMBRE
                if(txtsegundonp.getText().isEmpty() && txttercernp.getText().isEmpty()){
                   pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                   pps.setString(1, txtprimernp.getText());
                   pps.executeUpdate();
                }else
                
                //GUARDA SEGUNDO NOMBRE
                if(txttercernp.getText().isEmpty()){
                   pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                   pps.setString(1, txtprimernp.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                   pps.setString(1, txtsegundonp.getText());
                   pps.executeUpdate();
                }else{
                //GUARDA TERCER NOMBRE
                   pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                   pps.setString(1, txtprimernp.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                   pps.setString(1, txtsegundonp.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                   pps.setString(1, txttercernp.getText());
                   pps.executeUpdate();
                }
                
                //GUARDAR TABLA nombres_presbitero
                 if(txtsegundonp.getText().isEmpty() && txttercernp.getText().isEmpty()){
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimernp.getText());
                   ResultSet rs1=pps.executeQuery();              
                
                   if(rs1.next()){
                        int idn1=rs1.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                        pps.setString(1, String.valueOf(idp));
                        pps.setString(2, String.valueOf(n1));
                        pps.setString(3, String.valueOf(idn1));
                        pps.executeUpdate(); 
                   }
                 }
                 else if(txttercernp.getText().isEmpty()){
                     pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                     pps.setString(1, txtprimernp.getText());
                     ResultSet rs1=pps.executeQuery();              
                
                     while(rs1.next()){
                        int idn1=rs1.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                        pps.setString(1, String.valueOf(idp));
                        pps.setString(2, String.valueOf(n1));
                        pps.setString(3, String.valueOf(idn1));
                        pps.executeUpdate(); 
                     }
                   
                     pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                     pps.setString(1, txtsegundonp.getText());
                     ResultSet rs2=pps.executeQuery();
                     
                    while(rs2.next()){
                        int idn2=rs2.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                        pps.setString(1, String.valueOf(idp));
                        pps.setString(2, String.valueOf(n2));
                        pps.setString(3, String.valueOf(idn2));
                        pps.executeUpdate(); 
                   }
                 }else{
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                     pps.setString(1, txtprimernp.getText());
                     ResultSet rs1=pps.executeQuery();              
                
                     while(rs1.next()){
                        int idn1=rs1.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                        pps.setString(1, String.valueOf(idp));
                        pps.setString(2, String.valueOf(n1));
                        pps.setString(3, String.valueOf(idn1));
                        pps.executeUpdate(); 
                     }
                   
                     pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                     pps.setString(1, txtsegundonp.getText());
                     ResultSet rs2=pps.executeQuery();
                     
                    while(rs2.next()){
                        int idn2=rs2.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                        pps.setString(1, String.valueOf(idp));
                        pps.setString(2, String.valueOf(n2));
                        pps.setString(3, String.valueOf(idn2));
                        pps.executeUpdate(); 
                   }
                    
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txttercernp.getText());
                   ResultSet rs3=pps.executeQuery();
                   
                   while(rs3.next()){
                      int idn3=rs3.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(n3));
                      pps.setString(3, String.valueOf(idn3));
                      pps.executeUpdate();
                   }
                 }
                 
                //GUARDA EN TABLA apellido
                
                if(txtsegundoap.getText().isEmpty()){ //GUARDA PRIMER APELLIDO
                   pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                   pps.setString(1, txtprimerap.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                   pps.setString(1, txtprimerap.getText());
                   ResultSet rt1= pps.executeQuery();
                   
                   while(rt1.next()){
                      int idp1=rt1.getInt("id_apellido");
                      pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(ap1));
                      pps.setString(3, String.valueOf(idp1));
                      pps.executeUpdate();  
                   }
                }else{
                   //PRIMER APELLIDO
                   pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                   pps.setString(1, txtprimerap.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                   pps.setString(1, txtprimerap.getText());
                   ResultSet rt1= pps.executeQuery();
                   
                   while(rt1.next()){
                      int idp1=rt1.getInt("id_apellido");
                      pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(ap1));
                      pps.setString(3, String.valueOf(idp1));
                      pps.executeUpdate();  
                   }
                   //SEGUNDO APELLIDO
                   pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                   pps.setString(1, txtsegundoap.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                   pps.setString(1, txtsegundoap.getText());
                   ResultSet rt2= pps.executeQuery();
                   
                   while(rt2.next()){
                      int idp2=rt2.getInt("id_apellido");
                      pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(ap2));
                      pps.setString(3, String.valueOf(idp2));
                      pps.executeUpdate();  
                   }
                }
                */
            //GUARDA EN LA TABLA PARROCO_PRESBITERO
                pps=cone.prepareStatement("INSERT INTO parroco_presbitero(id_orden,parroco,fecha_inicio) "
                        + " VALUES (?,?,?)");
                pps.setString(1, String.valueOf(orden));
                pps.setString(2, String.valueOf(parroco));
                pps.setString(3, txtfechaInicio.getValue().toString());
//              pps.setString(4, txtfechaFin.getValue().toString());
                pps.executeUpdate();
                
                int idp=0;
                pps=cone.prepareStatement("SELECT count(id_p) FROM parroco_presbitero");
                ResultSet rst= pps.executeQuery();
                   
                    if(rst.next()){
                        idp=rst.getInt("count(id_p)");
                        System.out.print("Id pres: " + idp);
                    }
            //GUARDA LOS NOMBRES EN TABLA NOMBRES Y NOMBRES_PRESBITERO        
            if(txtsegundonp.getText().isEmpty() && txttercernp.getText().isEmpty()){
                   pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimernp.getText());
                   ResultSet r1=pps.executeQuery();
                   
                if(r1.next()){
                    int id= r1.getInt("id_nombre");
                    int idn1=r1.getInt("id_nombre");

                    pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(n1));
                    pps.setString(3, String.valueOf(idn1));
                    pps.executeUpdate();
                    System.out.print("\nid nombre: " + id);
                    
                }else{
                    pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                    pps.setString(1, txtprimernp.getText());
                    pps.executeUpdate();
                    
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtprimernp.getText());
                    ResultSet rs1=pps.executeQuery();
                    
                    while(rs1.next()){
                       int idn1=rs1.getInt("id_nombre");
                       pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                       pps.setString(1, String.valueOf(idp));
                       pps.setString(2, String.valueOf(n1));
                       pps.setString(3, String.valueOf(idn1));
                       pps.executeUpdate(); 
                    }  
                }
                
            //GUARDA DOS NOMBRES DEL PRESBITERO        
            }else if(txttercernp.getText().isEmpty()){
                pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimernp.getText());
                   ResultSet r1=pps.executeQuery();
                   
                if(r1.next()){
                    int id= r1.getInt("id_nombre");
                    int idn1=r1.getInt("id_nombre");

                    pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(n1));
                    pps.setString(3, String.valueOf(idn1));
                    pps.executeUpdate();
                    System.out.print("\nid nombre: " + id);
                    
                }else{
                    pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                    pps.setString(1, txtprimernp.getText());
                    pps.executeUpdate();
                    
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtprimernp.getText());
                    ResultSet rs1=pps.executeQuery();
                    
                    while(rs1.next()){
                       int idn1=rs1.getInt("id_nombre");
                       pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                       pps.setString(1, String.valueOf(idp));
                       pps.setString(2, String.valueOf(n1));
                       pps.setString(3, String.valueOf(idn1));
                       pps.executeUpdate(); 
                    }
                }
                
                pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                pps.setString(1, txtsegundonp.getText());
                ResultSet r2=pps.executeQuery();
                   
                if(r2.next()){
                    int id= r2.getInt("id_nombre");
                    int idn2=r2.getInt("id_nombre");

                    pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(n2));
                    pps.setString(3, String.valueOf(idn2));
                    pps.executeUpdate();
                    System.out.print("\nid nombre: " + id);
                    
                }else{
                    pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                    pps.setString(1, txtsegundonp.getText());
                    pps.executeUpdate();
                    
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtsegundonp.getText());
                    ResultSet rs2=pps.executeQuery();
                    
                    while(rs2.next()){
                       int idn2=rs2.getInt("id_nombre");
                       pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                       pps.setString(1, String.valueOf(idp));
                       pps.setString(2, String.valueOf(n2));
                       pps.setString(3, String.valueOf(idn2));
                       pps.executeUpdate(); 
                    }
                }
             //GUARDA TRES NOMBRES DEL PRESBITERO   
            }else{
                   pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimernp.getText());
                   ResultSet r1=pps.executeQuery();
                   
                if(r1.next()){
                    int id= r1.getInt("id_nombre");
                    int idn1=r1.getInt("id_nombre");

                    pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(n1));
                    pps.setString(3, String.valueOf(idn1));
                    pps.executeUpdate();
                    System.out.print("\nid nombre: " + id);
                    
                }else{
                    pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                    pps.setString(1, txtprimernp.getText());
                    pps.executeUpdate();
                    
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtprimernp.getText());
                    ResultSet rs1=pps.executeQuery();
                    
                    while(rs1.next()){
                       int idn1=rs1.getInt("id_nombre");
                       pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                       pps.setString(1, String.valueOf(idp));
                       pps.setString(2, String.valueOf(n1));
                       pps.setString(3, String.valueOf(idn1));
                       pps.executeUpdate(); 
                    }
                }
                
                pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtsegundonp.getText());
                   ResultSet r2=pps.executeQuery();
                   
                if(r2.next()){
                    int id= r2.getInt("id_nombre");
                    int idn2=r2.getInt("id_nombre");

                    pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(n2));
                    pps.setString(3, String.valueOf(idn2));
                    pps.executeUpdate();
                    System.out.print("\nid nombre: " + id);
                    
                }else{
                    pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                    pps.setString(1, txtsegundonp.getText());
                    pps.executeUpdate();
                    
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtsegundonp.getText());
                    ResultSet rs2=pps.executeQuery();
                    
                    while(rs2.next()){
                       int idn2=rs2.getInt("id_nombre");
                       pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                       pps.setString(1, String.valueOf(idp));
                       pps.setString(2, String.valueOf(n1));
                       pps.setString(3, String.valueOf(idn2));
                       pps.executeUpdate(); 
                    }
                }
                pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txttercernp.getText());
                   ResultSet r3=pps.executeQuery();
                   
                if(r3.next()){
                    int id= r3.getInt("id_nombre");
                    int idn3=r3.getInt("id_nombre");

                    pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(n3));
                    pps.setString(3, String.valueOf(idn3));
                    pps.executeUpdate();
                    System.out.print("\nid nombre: " + id);
                    
                }else{
                    pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                    pps.setString(1, txttercernp.getText());
                    pps.executeUpdate();
                    
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txttercernp.getText());
                    ResultSet rs3=pps.executeQuery();
                    
                    while(rs3.next()){
                       int idn3=rs3.getInt("id_nombre");
                       pps=cone.prepareStatement("INSERT into nombres_presbitero(id_p,orden_nombre,id_nombre) VALUES (?,?,?)");
                       pps.setString(1, String.valueOf(idp));
                       pps.setString(2, String.valueOf(n1));
                       pps.setString(3, String.valueOf(idn3));
                       pps.executeUpdate(); 
                    }
                }
            }
            
            //GUARDAR APELLIDOS DE LOS PRESBITEROS
            if(txtsegundoap.getText().isEmpty()){ //guarda el primer apellido
               pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
               pps.setString(1, txtprimerap.getText());
               ResultSet rt1= pps.executeQuery();
                   
                if(rt1.next()){
                    int idp1=rt1.getInt("id_apellido");
                    pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(ap1));
                    pps.setString(3, String.valueOf(idp1));
                    pps.executeUpdate();  
                }else{
                   pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                   pps.setString(1, txtprimerap.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                   pps.setString(1, txtprimerap.getText());
                   rt1= pps.executeQuery();
                   
                   while(rt1.next()){
                      int idp1=rt1.getInt("id_apellido");
                      pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(ap1));
                      pps.setString(3, String.valueOf(idp1));
                      pps.executeUpdate();  
                   }
                }
            //GUARDAR LOS DOS APELLIDOS DEL PRESBITERO
            }else{ //primer apellido
               pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
               pps.setString(1, txtprimerap.getText());
               ResultSet rt1= pps.executeQuery();
                   
                if(rt1.next()){
                    int idp1=rt1.getInt("id_apellido");
                    pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(ap1));
                    pps.setString(3, String.valueOf(idp1));
                    pps.executeUpdate();  
                }else{
                   pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                   pps.setString(1, txtprimerap.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                   pps.setString(1, txtprimerap.getText());
                   rt1= pps.executeQuery();
                   
                   while(rt1.next()){
                      int idp1=rt1.getInt("id_apellido");
                      pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(ap1));
                      pps.setString(3, String.valueOf(idp1));
                      pps.executeUpdate();  
                   }
                }
               //SEGUNDO APELLIDO
               pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
               pps.setString(1, txtsegundoap.getText());
               ResultSet rt2= pps.executeQuery();
                   
                if(rt2.next()){
                    int idp2=rt2.getInt("id_apellido");
                    pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                    pps.setString(1, String.valueOf(idp));
                    pps.setString(2, String.valueOf(ap2));
                    pps.setString(3, String.valueOf(idp2));
                    pps.executeUpdate();  
                }else{
                   pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                   pps.setString(1, txtsegundoap.getText());
                   pps.executeUpdate();
                   
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                   pps.setString(1, txtsegundoap.getText());
                   rt2= pps.executeQuery();
                   
                   while(rt2.next()){
                      int idp2=rt2.getInt("id_apellido");
                      pps=cone.prepareStatement("INSERT into apellidos_presbitero(id_p,orden_apellido,id_apellido) VALUES (?,?,?)");
                      pps.setString(1, String.valueOf(idp));
                      pps.setString(2, String.valueOf(ap2));
                      pps.setString(3, String.valueOf(idp2));
                      pps.executeUpdate();  
                   }
                }
            }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Exito");
                alert.setContentText("Se guardaron los datos del presbítero exitósamente");
                alert.showAndWait();     
           
            } catch (SQLException ex) {
                Logger.getLogger(VistaRegistroPresController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
            
    }
    
    public void actualizarPresbitero(){
        
    }

    @FXML
    private void txtprimernpKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && car > '\b'){
            event.consume();
            lblad.setVisible(true);
            lblad.setText("Sólo se permiten letras");
        }else{
            lblad.setVisible(false);
        }
    }

    @FXML
    private void txtsegundonpKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && car > '\b'){
            event.consume();
            lblad.setVisible(true);
            lblad.setText("Sólo se permiten letras");
        }else{
            lblad.setVisible(false);
        }
    }

    @FXML
    private void txttercernpKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && car > '\b'){
            event.consume();
            lblad.setVisible(true);
            lblad.setText("Sólo se permiten letras");
        }else{
            lblad.setVisible(false);
        }
    }

    @FXML
    private void txtprimerapKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && car > '\b'){
            event.consume();
            lblad.setVisible(true);
            lblad.setText("Sólo se permiten letras");
        }else{
            lblad.setVisible(false);
        }
    }

    @FXML
    private void txtsegundoapKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && car > '\b'){
            event.consume();
            lblad.setVisible(true);
            lblad.setText("Sólo se permiten letras");
        }else{
            lblad.setVisible(false);
        }
    }

    @FXML
    private void buscarPresbitero(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/vistaBuscaPresbitero.fxml"));
            Pane ventana = (Pane) loader.load();    
            Scene scene= new Scene(ventana);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);           
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
