/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conexion.conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import controladores.main;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.Presbiteros;

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
    private JFXComboBox<Presbiteros> cmbpresbitero;
    @FXML
    private Label lblad;

    /**
     * Initializes the controller class.
     */
    PreparedStatement pps;
    conexion con = new conexion();
    Connection cone = (Connection) con.openConnection();
    ObservableList<Presbiteros>listapres;
    
    @FXML
    private JFXComboBox<?> cmblugarnac;
    @FXML
    private ToggleGroup generoB;
    @FXML
    private JFXDatePicker txtfecha_nac;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblad.setVisible(false);
        cargarParroco();
        
        listapres=FXCollections.observableArrayList(); 
        Presbiteros.llenarCombo(cone, listapres);
        cmbpresbitero.setItems(listapres);
        
        
    }
    
    public void cargarParroco(){
        try {
           /* String sql="SELECT parroco_presbitero.id_p, nombres_presbitero.id_nombre, nombres_presbitero.orden_nombre, nombre.nombre FROM parroco_presbitero, nombre, nombres_presbitero, apellido, apellidos_presbitero WHERE nombres_presbitero.id_nombre=nombre.id_nombre AND apellidos_presbitero.id_apellido=apellido.id_apellido AND parroco_presbitero.id_p=nombres_presbitero.id_p AND parroco_presbitero.id_p=apellidos_presbitero.id_p and parroco_presbitero.parroco=1 AND parroco_presbitero.parroco=1 \n" +
                    "\n" +
                    "UNION SELECT parroco_presbitero.id_p, apellidos_presbitero.id_apellido, apellidos_presbitero.orden_apellido, apellido.apellido FROM parroco_presbitero, nombre, nombres_presbitero, apellido, apellidos_presbitero WHERE nombres_presbitero.id_nombre=nombre.id_nombre AND apellidos_presbitero.id_apellido=apellido.id_apellido AND parroco_presbitero.id_p=nombres_presbitero.id_p AND parroco_presbitero.id_p=apellidos_presbitero.id_p and parroco_presbitero.parroco=1";
            */
            String sql="SELECT parroco_presbitero.id_p, nombres_presbitero.id_nombre, nombres_presbitero.orden_nombre, CONCAT(nombre.nombre, ' ', apellido.apellido) as nombre FROM parroco_presbitero LEFT JOIN nombres_presbitero ON nombres_presbitero.id_p = parroco_presbitero.id_p LEFT JOIN nombre ON nombres_presbitero.id_nombre = nombre.id_nombre LEFT JOIN apellidos_presbitero ON apellidos_presbitero.id_p = parroco_presbitero.id_p LEFT JOIN apellido ON apellidos_presbitero.id_apellido = apellido.id_apellido WHERE parroco_presbitero.parroco=1 AND parroco_presbitero.duracion IS null GROUP BY parroco_presbitero.id_p";
            PreparedStatement ps1=cone.prepareStatement(sql);
            ResultSet rs1= ps1.executeQuery();
            
            while(rs1.next()){
                String nombre= rs1.getString("nombre");
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
                        + "id_presbitero, fecha_nac, lugar_nac) VALUES(?,?,?,?,?,?,?)");
                pps.setString(1, txtlibro.getText());
                pps.setString(2, txtfolio.getText());
                pps.setString(3, /*txtparroco.getText()*/"1");
                pps.setString(4, txtfecha.getValue().toString());
                pps.setString(5, /*String.valueOf(presb)*/"2");
                pps.setString(6, txtfecha_nac.getValue().toString());
                pps.setString(7, /*String.valueOf(lugarnac)*/ "12");
                pps.executeUpdate();
                
                pps=cone.prepareStatement("SELECT count(no_bautismo) FROM sacramento");
                ResultSet rst=pps.executeQuery();
                int nob=0;
                while(rst.next()){
                    nob=rst.getInt("count(no_bautismo)");
                    System.out.print(nob);
                }
                //GUARDA LOS NOMBRES EN LA TABLA NOMBRES Y NOMBRES_BAUTIZADO
                if(txtsegundonb.getText().isEmpty() && txttercernb.getText().isEmpty()){
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtprimernb.getText());
                    ResultSet rs1=pps.executeQuery();
                    
                    if(rs1.next()){
                        int n1=rs1.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                        pps.setInt(1, nob);
                        pps.setString(2, "1");
                        pps.setInt(3,n1);
                        pps.executeUpdate();
                    }else{
                        pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                        pps.setString(1, txtprimernb.getText());
                        pps.executeUpdate();
                        
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernb.getText());
                        rs1=pps.executeQuery();
                        
                        while(rs1.next()){
                            int n1=rs1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "1");
                            pps.setInt(3,n1);
                            pps.executeUpdate();   
                        }               
                    }
                    //GUARDA LOS DOS NOMBRES DEL BAUTIZADO
                }else if(txttercernb.getText().isEmpty()){
                    // prrmer nombre
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtprimernb.getText());
                    ResultSet rs1=pps.executeQuery();
                    
                    if(rs1.next()){
                        int n1=rs1.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                        pps.setInt(1, nob);
                        pps.setString(2, "1");
                        pps.setInt(3,n1);
                        pps.executeUpdate();
                        
                    }else{
                        pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                        pps.setString(1, txtprimernb.getText());
                        pps.executeUpdate();
                    
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernb.getText());                       
                        ResultSet rs=pps.executeQuery();
                        
                        while(rs.next()){
                           int n1=rs.getInt("id_nombre");
                           pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "1");
                            pps.setInt(3,n1);
                            pps.executeUpdate();
                        }
                    }
                    //segundo numero
                    pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtsegundonb.getText());
                    ResultSet rs2=pps.executeQuery();
                    
                    if(rs2.next()){
                        int n2=rs2.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "2");
                            pps.setInt(3,n2);
                            pps.executeUpdate();         
                    }else{
                        pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                        pps.setString(1, txtsegundonb.getText());
                        pps.executeUpdate();
                        
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonb.getText());
                        ResultSet r2=pps.executeQuery();
                        
                        while(r2.next()){
                           int n2=r2.getInt("id_nombre");
                           pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "2");
                            pps.setInt(3,n2);
                            pps.executeUpdate();    
                        }
                    }
                    
                }else{
                  // prrmer nombre
                    pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtprimernb.getText());
                    ResultSet rs1=pps.executeQuery();
                    
                    if(rs1.next()){
                        int n1=rs1.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                        pps.setInt(1, nob);
                        pps.setString(2, "1");
                        pps.setInt(3,n1);
                        pps.executeUpdate();
                        
                    }else{
                        pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                        pps.setString(1, txtprimernb.getText());
                        pps.executeUpdate();
                    
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernb.getText());                       
                        ResultSet rs=pps.executeQuery();
                        
                        while(rs.next()){
                           int n1=rs.getInt("id_nombre");
                           pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "1");
                            pps.setInt(3,n1);
                            pps.executeUpdate();
                        }
                    }
                    //segundo nombre
                    pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txtsegundonb.getText());
                    ResultSet rs2=pps.executeQuery();
                    
                    if(rs2.next()){
                        int n2=rs2.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "2");
                            pps.setInt(3,n2);
                            pps.executeUpdate();         
                    }else{
                        pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                        pps.setString(1, txtsegundonb.getText());
                        pps.executeUpdate();
                        
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonb.getText());
                        ResultSet r2=pps.executeQuery();
                        
                        while(r2.next()){
                           int n2=r2.getInt("id_nombre");
                           pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "2");
                            pps.setInt(3,n2);
                            pps.executeUpdate();    
                        }
                    }
                    // tercernombre
                    pps=cone.prepareStatement("SELECT id_nombre, nombre FROM nombre WHERE nombre=?");
                    pps.setString(1, txttercernb.getText());
                    ResultSet rs3=pps.executeQuery();
                    
                    if(rs3.next()){
                        int n3=rs3.getInt("id_nombre");
                        pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "3");
                            pps.setInt(3,n3);
                            pps.executeUpdate();         
                    }else{
                        pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                        pps.setString(1, txttercernb.getText());
                        pps.executeUpdate();
                        
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercernb.getText());
                        ResultSet r3=pps.executeQuery();
                        
                        while(r3.next()){
                           int n3=r3.getInt("id_nombre");
                           pps=cone.prepareStatement("INSERT INTO nombres_bautizado(no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setString(2, "3");
                            pps.setInt(3,n3);
                            pps.executeUpdate();    
                        }
                    }
                }
                //GUARDA EN LA TABLA APELLIDO Y APELLIDOS_BAUTIZADO
                if(txtsegundoab.getText().isEmpty()){
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                    pps.setString(1, txtprimerab.getText());
                    ResultSet rt1= pps.executeQuery();
                    
                    if(rt1.next()){
                        int ap1=rt1.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_bautizado(no_bautismo,orden_apellido,id_apellido) VALUES(?,?,?)");
                        pps.setInt(1, nob);
                        pps.setInt(2, 1);
                        pps.setInt(3, ap1);
                        pps.executeUpdate();                      
                    }else{
                       pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                        pps.setString(1, txtprimerab.getText());
                        pps.executeUpdate(); 
                        
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerab.getText());
                        ResultSet rt= pps.executeQuery();
                        
                        while(rt.next()){
                          int ap1=rt.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT INTO apellidos_bautizado(no_bautismo,orden_apellido,id_apellido) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setInt(2, 1);
                            pps.setInt(3, ap1);
                            pps.executeUpdate();    
                        }
                    }
                    //GUARDA DOS APELLIDOS
                }else{ 
                   pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                    pps.setString(1, txtprimerab.getText());
                    ResultSet rt1= pps.executeQuery();
                    
                    if(rt1.next()){
                        int ap1=rt1.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_bautizado(no_bautismo,orden_apellido,id_apellido) VALUES(?,?,?)");
                        pps.setInt(1, nob);
                        pps.setInt(2, 1);
                        pps.setInt(3, ap1);
                        pps.executeUpdate();                      
                    }else{
                       pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                        pps.setString(1, txtprimerab.getText());
                        pps.executeUpdate(); 
                        
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerab.getText());
                        ResultSet rt= pps.executeQuery();
                        
                        while(rt.next()){
                          int ap1=rt.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT INTO apellidos_bautizado(no_bautismo,orden_apellido,id_apellido) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setInt(2, 1);
                            pps.setInt(3, ap1);
                            pps.executeUpdate();    
                        }
                    }
                    //se guarda el segundo apellido
                    pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                    pps.setString(1, txtsegundoab.getText());
                    ResultSet rt2= pps.executeQuery();
                    
                    if(rt2.next()){
                        int ap2=rt2.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_bautizado(no_bautismo,orden_apellido,id_apellido) VALUES(?,?,?)");
                        pps.setInt(1, nob);
                        pps.setInt(2, 2);
                        pps.setInt(3, ap2);
                        pps.executeUpdate();                      
                    }else{
                       pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                        pps.setString(1, txtsegundoab.getText());
                        pps.executeUpdate(); 
                        
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoab.getText());
                        ResultSet rt= pps.executeQuery();
                        
                        while(rt.next()){
                          int ap2=rt.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT INTO apellidos_bautizado(no_bautismo,orden_apellido,id_apellido) VALUES(?,?,?)");
                            pps.setInt(1, nob);
                            pps.setInt(2, 2);
                            pps.setInt(3, ap2);
                            pps.executeUpdate();    
                        }
                    }
                }
                
               /* int generop=0;
                if(rdbm1.isSelected()){
                    genero=0;
                }else{
                    genero=1;
                }*/
                
     //GUARDAR NOMMBRES DE MADRE/PADRE           
                if(txtprimernp.getText().isEmpty() && txtsegundonp.getText().isEmpty() && txttercernp.getText().isEmpty() && txtprimerap.getText().isEmpty() && txtsegundoap.getText().isEmpty()){
                   pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                        pps.setInt(1, 0);
                        pps.setInt(2, 1);
                        pps.executeUpdate();
                        
                    //verifica el id del padre/padrino   
                    pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                    ResultSet r0=pps.executeQuery();
                    int idpad=0;
                    while(r0.next()){
                        idpad=r0.getInt("count(id_pad)");
                        System.out.print("id padrino: " + idpad);
                    }
                    
                    if(txtsegundonm.getText().isEmpty() && txttercernm.getText().isEmpty()){
                        //primer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernm.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA DOS NOMBRES DE LA MADRE
                    }else if(txttercernm.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernm.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        
                        //SEGUNDO NOMBRE
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonm.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA TRES NOMBRES DE LA MADRE
                    }else{
                        //primer nombre
                       pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernm.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        
                        //SEGUNDO NOMBRE
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonm.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                        //TERCER NOMBRE
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercernm.getText());
                        ResultSet r3= pps.executeQuery();
                        
                        if(r3.next()){
                            int n3=r3.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 3);
                            pps.setInt(4, n3);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txttercernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txttercernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n3=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 3);
                                pps.setInt(4, n3);
                                pps.executeUpdate(); 
                            }
                        } 
                    }
                    //GUARDA APELLIDOS DE LA MADRE
                    if(txtsegundoam.getText().isEmpty()){
                        //primer apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimeram.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimeram.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimeram.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                    //GUARDA LOS DOS APELLIDOS                       
                    }else{
                       //primer apellido
                       pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimeram.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimeram.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimeram.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                        //segundo apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoam.getText());
                        ResultSet rt2= pps.executeQuery();
                        
                        if(rt2.next()){
                            int ape2=rt2.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, ape2);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtsegundoam.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimeram.getText());
                            ResultSet r2= pps.executeQuery();
                            
                            while(r2.next()){
                                int ape2=r2.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, ape2);
                                pps.executeUpdate();  
                            }
                        }
                    }
                //SE GUARDA LOS DATOS DEL PADRE
                }else if(txtprimernm.getText().isEmpty() && txtsegundonm.getText().isEmpty() && txttercernm.getText().isEmpty() && txtprimeram.getText().isEmpty() && txtsegundoam.getText().isEmpty()){
                    pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                       pps.setInt(1, 0);
                       pps.setInt(2, 0);
                       pps.executeUpdate();
                       
                    //verifica el id del padre/padrino   
                    pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                    ResultSet r0=pps.executeQuery();
                    int idpad=0;
                    while(r0.next()){
                        idpad=r0.getInt("count(id_pad)");
                        System.out.print("id padrino: " + idpad);
                    }
                    
                    if(txtsegundonp.getText().isEmpty() && txttercernp.getText().isEmpty()){
                        //primer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernp.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA DOS NOMBRES DEL PADRE DEL BAUTIZADO
                    }else if(txttercernp.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernp.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        //segundo nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonp.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA TRES NOMBRES DEL PADRE
                    }else{
                       //primer nombre
                       pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernp.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        //segundo nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonp.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                        
                        //tercer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercernp.getText());
                        ResultSet r3= pps.executeQuery();
                        
                        if(r3.next()){
                            int n3=r3.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 3);
                            pps.setInt(4, n3);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txttercernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txttercernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n3=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 3);
                                pps.setInt(4, n3);
                                pps.executeUpdate(); 
                            }
                        }                                                                                           
                    }
                    
                    //GUARDA LOS APELLIDOS DEL PADRE
                    if(txtsegundoap.getText().isEmpty()){
                        //primer apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerap.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerap.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerap.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                    //GUARDA LOS DOS APELLIDOS                       
                    }else{
                       //primer apellido
                       pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerap.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerap.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerap.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                        //segundo apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoap.getText());
                        ResultSet rt2= pps.executeQuery();
                        
                        if(rt2.next()){
                            int ape2=rt2.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, ape2);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtsegundoap.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtsegundoap.getText());
                            ResultSet r2= pps.executeQuery();
                            
                            while(r2.next()){
                                int ape2=r2.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, ape2);
                                pps.executeUpdate();  
                            }
                        }
                    }
            //GUARDA DATOS DE LA MADRE Y DEL PADRE
                }else{
                  //DATOS DE LA MADRE
                  pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                        pps.setInt(1, 0);
                        pps.setInt(2, 1);
                        pps.executeUpdate();
                        
                    //verifica el id del padre/padrino   
                    pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                    ResultSet r0=pps.executeQuery();
                    int idpad=0;
                    while(r0.next()){
                        idpad=r0.getInt("count(id_pad)");
                        System.out.print("id padrino: " + idpad);
                    }
                    
                    if(txtsegundonm.getText().isEmpty() && txttercernm.getText().isEmpty()){
                        //primer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernm.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA DOS NOMBRES DE LA MADRE
                    }else if(txttercernm.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernm.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        
                        //SEGUNDO NOMBRE
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonm.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                   //GUARDA TRES NOMBRES DE LA MADRE
                    }else{
                        //primer nombre
                       pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernm.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        
                        //SEGUNDO NOMBRE
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonm.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                        //TERCER NOMBRE
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercernm.getText());
                        ResultSet r3= pps.executeQuery();
                        
                        if(r3.next()){
                            int n3=r3.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 3);
                            pps.setInt(4, n3);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txttercernm.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txttercernm.getText());
                            ResultSet r =pps.executeQuery();
                            
                            while(r.next()){
                               int n3=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 3);
                                pps.setInt(4, n3);
                                pps.executeUpdate(); 
                            }
                        } 
                    }
                    //GUARDA APELLIDOS DE LA MADRE
                    if(txtsegundoam.getText().isEmpty()){
                        //primer apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimeram.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimeram.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimeram.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                    //GUARDA LOS DOS APELLIDOS                       
                    }else{
                       //primer apellido
                       pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimeram.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimeram.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimeram.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                        //segundo apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoam.getText());
                        ResultSet rt2= pps.executeQuery();
                        
                        if(rt2.next()){
                            int ape2=rt2.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, ape2);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtsegundoam.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtsegundoam.getText());
                            ResultSet r2= pps.executeQuery();
                            
                            while(r2.next()){
                                int ape2=r2.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, ape2);
                                pps.executeUpdate();  
                            }
                        }
                    }
                    //GUARDA LOS DATOS DEL PADRE
                     pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                       pps.setInt(1, 0);
                       pps.setInt(2, 0);
                       pps.executeUpdate();
                       
                    //verifica el id del padre/padrino   
                    pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                    r0=pps.executeQuery();
                    //int idpad=0;
                    while(r0.next()){
                        idpad=r0.getInt("count(id_pad)");
                        System.out.print("id padrino: " + idpad);
                    }
                    
                    if(txtsegundonp.getText().isEmpty() && txttercernp.getText().isEmpty()){
                        //primer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernp.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA DOS NOMBRES DEL PADRE DEL BAUTIZADO
                    }else if(txttercernp.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernp.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        //segundo nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonp.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                    //GUARDA TRES NOMBRES DEL PADRE
                    }else{
                       //primer nombre
                       pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimernp.getText());
                        ResultSet r1= pps.executeQuery();
                        
                        if(r1.next()){
                            int n1=r1.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, n1);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtprimernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtprimernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n1=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, n1);
                                pps.executeUpdate(); 
                            }
                        }
                        //segundo nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundonp.getText());
                        ResultSet r2= pps.executeQuery();
                        
                        if(r2.next()){
                            int n2=r2.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, n2);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txtsegundonp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txtsegundonp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n2=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, n2);
                                pps.executeUpdate(); 
                            }
                        }
                        
                        //tercer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercernp.getText());
                        ResultSet r3= pps.executeQuery();
                        
                        if(r3.next()){
                            int n3=r3.getInt("id_nombre");
                            pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 3);
                            pps.setInt(4, n3);
                            pps.executeUpdate();
                            
                        }else{
                           pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES (?)");
                            pps.setString(1, txttercernp.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                            pps.setString(1, txttercernp.getText());
                            ResultSet r =pps.executeQuery(); 
                            
                            while(r.next()){
                               int n3=r.getInt("id_nombre");
                                pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 3);
                                pps.setInt(4, n3);
                                pps.executeUpdate(); 
                            }
                        }                                                                                           
                    }
                    
                    //GUARDA LOS APELLIDOS DEL PADRE
                    if(txtsegundoap.getText().isEmpty()){
                        //primer apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerap.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerap.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerap.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                    //GUARDA LOS DOS APELLIDOS                       
                    }else{
                       //primer apellido
                       pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerap.getText());
                        ResultSet rt1= pps.executeQuery();
                        
                        if(rt1.next()){
                            int ape1=rt1.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 1);
                            pps.setInt(4, ape1);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerap.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerap.getText());
                            ResultSet r= pps.executeQuery();
                            
                            while(r.next()){
                                int ape1=r.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 1);
                                pps.setInt(4, ape1);
                                pps.executeUpdate();  
                            }
                        }
                        
                        //segundo apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoap.getText());
                        ResultSet rt2= pps.executeQuery();
                        
                        if(rt2.next()){
                            int ape2=rt2.getInt("id_apellido");
                            pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                            pps.setInt(1, idpad);
                            pps.setInt(2, nob);
                            pps.setInt(3, 2);
                            pps.setInt(4, ape2);
                            pps.executeUpdate();
                            
                        }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtsegundoap.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtsegundoap.getText());
                            ResultSet r2= pps.executeQuery();
                            
                            while(r2.next()){
                                int ape2=r2.getInt("id_apellido");
                                pps=cone.prepareStatement("INSERT into apellidos_padrepa(id_pad,no_bautismo,orden_apellido,id_apellido) VALUES (?,?,?,?)");
                                pps.setInt(1, idpad);
                                pps.setInt(2, nob);
                                pps.setInt(3, 2);
                                pps.setInt(4, ape2);
                                pps.executeUpdate();  
                            }
                        }
                    }                                      
                }
                
            //GUARDAR LOS NOMBRES DEL PADRINO/MADRINA
            //padrino
            if(txtprimerpad2.getText().isEmpty() && txtsegundopad2.getText().isEmpty() && txttercerpad2.getText().isEmpty() && txtprimerapad2.getText().isEmpty() && txtsegundoapad2.getText().isEmpty()){
               
                pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                        pps.setInt(1, 1);
                        pps.setInt(2, 0);
                        pps.executeUpdate();
                        
                    //verifica el id del padrino   
                    pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                    ResultSet r0=pps.executeQuery();
                    int idpadre=0;
                    while(r0.next()){
                        idpadre=r0.getInt("count(id_pad)");
                        System.out.print("id padre: " + idpadre);
                    }
                    //GUARDA UN NOMBRE DEL PADRINO
                    if(txtsegundopad1.getText().isEmpty() && txttercerpad1.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimerpad1.getText());
                        ResultSet rt=pps.executeQuery();
                        
                        if(rt.next()){
                          int idn1=rt.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, idn1);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtprimerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtprimerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn1=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idn1);
                             pps.executeUpdate();                        
                           }
                        }
                    //guarda dos nombres del padrino
                    
                    }else if(txttercerpad1.getText().isEmpty()){
                        //primer nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimerpad1.getText());
                        ResultSet rt=pps.executeQuery();
                        
                        if(rt.next()){
                          int idn1=rt.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, idn1);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtprimerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtprimerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn1=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idn1);
                             pps.executeUpdate();                        
                           }
                        }
                        //segundo nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundopad1.getText());
                        ResultSet rt2=pps.executeQuery();
                        
                        if(rt2.next()){
                          int idn2=rt2.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, idn2);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtsegundopad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtsegundopad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn2=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 2);
                             pps.setInt(4, idn2);
                             pps.executeUpdate();                        
                           }
                        }
                    //GUARDA TRES NOMBRES DEL PADRINO
                    }else{
                       //primer nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimerpad1.getText());
                        ResultSet rt=pps.executeQuery();
                        
                        if(rt.next()){
                          int idn1=rt.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, idn1);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtprimerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtprimerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn1=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idn1);
                             pps.executeUpdate();                        
                           }
                        }
                        //segundo nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundopad1.getText());
                        ResultSet rt2=pps.executeQuery();
                        
                        if(rt2.next()){
                          int idn2=rt2.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, idn2);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtsegundopad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtsegundopad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn2=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 2);
                             pps.setInt(4, idn2);
                             pps.executeUpdate();                        
                           }
                        }
                        
                        //tercer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercerpad1.getText());
                        ResultSet rt3=pps.executeQuery();
                        
                        if(rt3.next()){
                          int idn3=rt3.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 3);
                          pps.setInt(4, idn3);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txttercerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txttercerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn3=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 3);
                             pps.setInt(4, idn3);
                             pps.executeUpdate();                        
                           }
                        }
                    }
                    
                    //GUARDA LOS APELLIDOS DEL PADRINO
                    if(txtsegundoapad1.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerapad1.getText());
                        int idap;
                         ResultSet ra1= pps.executeQuery();
                         
                         if(ra1.next()){
                             idap=ra1.getInt("id_apellido");
                             pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idap);
                             pps.executeUpdate();               
                         }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerapad1.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerapad1.getText());
                            ra1= pps.executeQuery();
                            
                            while(ra1.next()){
                              idap=ra1.getInt("id_apellido");
                              pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                              pps.setInt(1, idpadre);
                              pps.setInt(2, nob);
                              pps.setInt(3, 1);
                              pps.setInt(4, idap);
                              pps.executeUpdate();  
                            }
                         }
                   //GUARDA DOS APELLIDOS DEL PADRINO                          
                    }else{
                      //primer apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerapad1.getText());
                        int idap;
                         ResultSet ra1= pps.executeQuery();
                         
                         if(ra1.next()){
                             idap=ra1.getInt("id_apellido");
                             pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idap);
                             pps.executeUpdate();               
                         }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerapad1.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerapad1.getText());
                            ra1= pps.executeQuery();
                            
                            while(ra1.next()){
                              idap=ra1.getInt("id_apellido");
                              pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                              pps.setInt(1, idpadre);
                              pps.setInt(2, nob);
                              pps.setInt(3, 1);
                              pps.setInt(4, idap);
                              pps.executeUpdate();  
                            }
                         }
                         
                      //segundo apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoapad1.getText());
                         int idap2;
                         ResultSet ra2= pps.executeQuery();
                         
                         if(ra2.next()){
                             idap2=ra2.getInt("id_apellido");
                             pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 2);
                             pps.setInt(4, idap2);
                             pps.executeUpdate();               
                         }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtsegundoapad1.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtsegundoapad1.getText());
                            ra2= pps.executeQuery();
                            
                            while(ra2.next()){
                              idap2=ra2.getInt("id_apellido");
                              pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                              pps.setInt(1, idpadre);
                              pps.setInt(2, nob);
                              pps.setInt(3, 2);
                              pps.setInt(4, idap2);
                              pps.executeUpdate();  
                            }
                         }
                         
                    }
            //GUARDA LOS DATOS DE LA MADRINA
            }else if(txtprimerpad1.getText().isEmpty() && txtsegundopad1.getText().isEmpty() && txttercerpad1.getText().isEmpty() && txtprimerapad1.getText().isEmpty() && txtsegundoapad1.getText().isEmpty()){
                pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                pps.setInt(1, 1);
                pps.setInt(2, 1);
                pps.executeUpdate();
                        
               //verifica el id de la madrina   
                pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                ResultSet rm=pps.executeQuery();
                int idmadrina=0;
                
                while(rm.next()){
                    idmadrina=rm.getInt("count(id_pad)");
                    System.out.print("\n id madrina: " + idmadrina);
                }
                //primer nombre
                if(txtsegundopad2.getText().isEmpty() && txttercerpad2.getText().isEmpty()){
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimerpad2.getText());
                   ResultSet r1= pps.executeQuery();
                   int n1;
                   
                    if(r1.next()){
                      n1=r1.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 1);
                      pps.setInt(4, n1);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtprimerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtprimerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n1=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, n1);
                          pps.executeUpdate();
                      }
                   }
               //GAUDAR DOS NOMBRES DE LA MADRINA
               }else if(txttercerpad2.getText().isEmpty()){
                   //primer nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimerpad2.getText());
                   ResultSet r1= pps.executeQuery();
                   int n1;
                   
                    if(r1.next()){
                      n1=r1.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 1);
                      pps.setInt(4, n1);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtprimerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtprimerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n1=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, n1);
                          pps.executeUpdate();
                      }
                   }
                   
                   //segundo nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtsegundopad2.getText());
                   ResultSet r2= pps.executeQuery();
                   int n2;
                   
                    if(r2.next()){
                      n2=r2.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 2);
                      pps.setInt(4, n2);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtsegundopad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtsegundopad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n2=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, n2);
                          pps.executeUpdate();
                      }
                   }
               //GUARDA TRES NOMBRES DE LA MADRINA
               }else{
                  //primer nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimerpad2.getText());
                   ResultSet r1= pps.executeQuery();
                   int n1;
                   
                    if(r1.next()){
                      n1=r1.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 1);
                      pps.setInt(4, n1);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtprimerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtprimerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n1=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, n1);
                          pps.executeUpdate();
                      }
                   }
                   
                   //segundo nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtsegundopad2.getText());
                   ResultSet r2= pps.executeQuery();
                   int n2;
                   
                    if(r2.next()){
                      n2=r2.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 2);
                      pps.setInt(4, n2);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtsegundopad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtsegundopad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n2=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, n2);
                          pps.executeUpdate();
                      }
                   }
                   
                   //tercer nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txttercerpad2.getText());
                   ResultSet r3= pps.executeQuery();
                   int n3;
                   
                    if(r3.next()){
                      n3=r3.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 3);
                      pps.setInt(4, n3);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txttercerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txttercerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n3=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 3);
                          pps.setInt(4, n3);
                          pps.executeUpdate();
                      }
                   }                                     
               }
                
              //GUARDA LOS APELLIDOS DE LA MADRINA
              
              if(txtsegundoapad2.getText().isEmpty()){
                 //primer apellido
                 pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                 pps.setString(1, txtprimerapad2.getText());
                 ResultSet rt1= pps.executeQuery(); 
                 int idap1;
                 
                 if(rt1.next()){
                     idap1=rt1.getInt("id_apellido");
                     pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                     pps.setInt(1, idmadrina);
                     pps.setInt(2, nob);
                     pps.setInt(3, 1);
                     pps.setInt(4, idap1);
                     pps.executeUpdate();
                 }else{
                     pps=cone.prepareStatement("INSER INTO apellido(apellido) WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     pps.executeUpdate();
                     
                     pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     ResultSet rs= pps.executeQuery();
                     
                     while(rs.next()){
                        idap1=rs.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                        pps.setInt(1, idmadrina);
                        pps.setInt(2, nob);
                        pps.setInt(3, 1);
                        pps.setInt(4, idap1);
                        pps.executeUpdate();
                     }
                 }
              //GUARDA DOS APELLIDOS DE LA MADRINA
              }else{
                 //primer apellido
                 pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                 pps.setString(1, txtprimerapad2.getText());
                 ResultSet rt1= pps.executeQuery(); 
                 int idap1;
                 
                 if(rt1.next()){
                     idap1=rt1.getInt("id_apellido");
                     pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                     pps.setInt(1, idmadrina);
                     pps.setInt(2, nob);
                     pps.setInt(3, 1);
                     pps.setInt(4, idap1);
                     pps.executeUpdate();
                 }else{
                     pps=cone.prepareStatement("INSER INTO apellido(apellido) WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     pps.executeUpdate();
                     
                     pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     ResultSet rs= pps.executeQuery();
                     
                     while(rs.next()){
                        idap1=rs.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                        pps.setInt(1, idmadrina);
                        pps.setInt(2, nob);
                        pps.setInt(3, 1);
                        pps.setInt(4, idap1);
                        pps.executeUpdate();
                     }
                 }
                 
                 //segundo apellido
                 pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                 pps.setString(1, txtsegundoapad2.getText());
                 ResultSet rt2= pps.executeQuery(); 
                 int idap2;
                 
                 if(rt2.next()){
                     idap2=rt2.getInt("id_apellido");
                     pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                     pps.setInt(1, idmadrina);
                     pps.setInt(2, nob);
                     pps.setInt(3, 2);
                     pps.setInt(4, idap2);
                     pps.executeUpdate();
                 }else{
                     pps=cone.prepareStatement("INSER INTO apellido(apellido) WHERE apellido=?");
                     pps.setString(1, txtsegundoapad2.getText());
                     pps.executeUpdate();
                     
                     pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                     pps.setString(1, txtsegundoapad2.getText());
                     ResultSet rs= pps.executeQuery();
                     
                     while(rs.next()){
                        idap2=rs.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                        pps.setInt(1, idmadrina);
                        pps.setInt(2, nob);
                        pps.setInt(3, 1);
                        pps.setInt(4, idap2);
                        pps.executeUpdate();
                     }
                 }
              }
            //GUARDA LOS DATOS DEL PADRINO Y MADRINA
            }else{
              //PADRINO
                pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                        pps.setInt(1, 1);
                        pps.setInt(2, 0);
                        pps.executeUpdate();
                        
                    //verifica el id del padrino   
                    pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                    ResultSet r0=pps.executeQuery();
                    int idpadre=0;
                    while(r0.next()){
                        idpadre=r0.getInt("count(id_pad)");
                        System.out.print("id padre: " + idpadre);
                    }
                    //GUARDA UN NOMBRE DEL PADRINO
                    if(txtsegundopad1.getText().isEmpty() && txttercerpad1.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimerpad1.getText());
                        ResultSet rt=pps.executeQuery();
                        
                        if(rt.next()){
                          int idn1=rt.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, idn1);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtprimerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtprimerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn1=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idn1);
                             pps.executeUpdate();                        
                           }
                        }
                    //guarda dos nombres del padrino
                    
                    }else if(txttercerpad1.getText().isEmpty()){
                        //primer nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimerpad1.getText());
                        ResultSet rt=pps.executeQuery();
                        
                        if(rt.next()){
                          int idn1=rt.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, idn1);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtprimerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtprimerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn1=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idn1);
                             pps.executeUpdate();                        
                           }
                        }
                        //segundo nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundopad1.getText());
                        ResultSet rt2=pps.executeQuery();
                        
                        if(rt2.next()){
                          int idn2=rt2.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, idn2);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtsegundopad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtsegundopad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn2=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 2);
                             pps.setInt(4, idn2);
                             pps.executeUpdate();                        
                           }
                        }
                    //GUARDA TRES NOMBRES DEL PADRINO
                    }else{
                       //primer nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtprimerpad1.getText());
                        ResultSet rt=pps.executeQuery();
                        
                        if(rt.next()){
                          int idn1=rt.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, idn1);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtprimerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtprimerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn1=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idn1);
                             pps.executeUpdate();                        
                           }
                        }
                        //segundo nombre
                         pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txtsegundopad1.getText());
                        ResultSet rt2=pps.executeQuery();
                        
                        if(rt2.next()){
                          int idn2=rt2.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, idn2);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txtsegundopad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txtsegundopad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn2=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 2);
                             pps.setInt(4, idn2);
                             pps.executeUpdate();                        
                           }
                        }
                        
                        //tercer nombre
                        pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                        pps.setString(1, txttercerpad1.getText());
                        ResultSet rt3=pps.executeQuery();
                        
                        if(rt3.next()){
                          int idn3=rt3.getInt("id_nombre");
                          
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                          pps.setInt(1, idpadre);
                          pps.setInt(2, nob);
                          pps.setInt(3, 3);
                          pps.setInt(4, idn3);
                          pps.executeUpdate();
    
                        }else{
                          pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                          pps.setString(1, txttercerpad1.getText());
                          pps.executeUpdate();
                          
                          pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                          pps.setString(1, txttercerpad1.getText());
                            ResultSet rs=pps.executeQuery();
                            
                           while(rs.next()){
                             int idn3=rs.getInt("id_nombre");
                             
                             pps=cone.prepareStatement("INSERT INTO nombres_padrepa(id_pad, no_bautismo, orden_nombre, id_nombre) VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 3);
                             pps.setInt(4, idn3);
                             pps.executeUpdate();                        
                           }
                        }
                    }
                    
                    //GUARDA LOS APELLIDOS DEL PADRINO
                    if(txtsegundoapad1.getText().isEmpty()){
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerapad1.getText());
                        int idap;
                         ResultSet ra1= pps.executeQuery();
                         
                         if(ra1.next()){
                             idap=ra1.getInt("id_apellido");
                             pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idap);
                             pps.executeUpdate();               
                         }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerapad1.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerapad1.getText());
                            ra1= pps.executeQuery();
                            
                            while(ra1.next()){
                              idap=ra1.getInt("id_apellido");
                              pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                              pps.setInt(1, idpadre);
                              pps.setInt(2, nob);
                              pps.setInt(3, 1);
                              pps.setInt(4, idap);
                              pps.executeUpdate();  
                            }
                         }
                   //GUARDA DOS APELLIDOS DEL PADRINO                          
                    }else{
                      //primer apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtprimerapad1.getText());
                        int idap;
                         ResultSet ra1= pps.executeQuery();
                         
                         if(ra1.next()){
                             idap=ra1.getInt("id_apellido");
                             pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 1);
                             pps.setInt(4, idap);
                             pps.executeUpdate();               
                         }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtprimerapad1.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtprimerapad1.getText());
                            ra1= pps.executeQuery();
                            
                            while(ra1.next()){
                              idap=ra1.getInt("id_apellido");
                              pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                              pps.setInt(1, idpadre);
                              pps.setInt(2, nob);
                              pps.setInt(3, 1);
                              pps.setInt(4, idap);
                              pps.executeUpdate();  
                            }
                         }
                         
                      //segundo apellido
                        pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                        pps.setString(1, txtsegundoapad1.getText());
                         int idap2;
                         ResultSet ra2= pps.executeQuery();
                         
                         if(ra2.next()){
                             idap2=ra2.getInt("id_apellido");
                             pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                             pps.setInt(1, idpadre);
                             pps.setInt(2, nob);
                             pps.setInt(3, 2);
                             pps.setInt(4, idap2);
                             pps.executeUpdate();               
                         }else{
                            pps=cone.prepareStatement("INSERT INTO apellido(apellido) VALUES(?)");
                            pps.setString(1, txtsegundoapad1.getText());
                            pps.executeUpdate();
                            
                            pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                            pps.setString(1, txtsegundoapad1.getText());
                            ra2= pps.executeQuery();
                            
                            while(ra2.next()){
                              idap2=ra2.getInt("id_apellido");
                              pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                              pps.setInt(1, idpadre);
                              pps.setInt(2, nob);
                              pps.setInt(3, 2);
                              pps.setInt(4, idap2);
                              pps.executeUpdate();  
                            }
                         }
                    }
                    
                    //MADRINA
                                    pps=cone.prepareStatement("INSERT INTO padresp_bautizado(padre_padrino,genero) VALUES(?,?)");
                pps.setInt(1, 1);
                pps.setInt(2, 1);
                pps.executeUpdate();
                        
               //verifica el id de la madrina   
                pps=cone.prepareStatement("SELECT count(id_pad) FROM padresp_bautizado");
                ResultSet rm=pps.executeQuery();
                int idmadrina=0;
                
                while(rm.next()){
                    idmadrina=rm.getInt("count(id_pad)");
                    System.out.print("\n id madrina: " + idmadrina);
                }
                //primer nombre
                if(txtsegundopad2.getText().isEmpty() && txttercerpad2.getText().isEmpty()){
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimerpad2.getText());
                   ResultSet r1= pps.executeQuery();
                   int n1;
                   
                    if(r1.next()){
                      n1=r1.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 1);
                      pps.setInt(4, n1);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtprimerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtprimerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n1=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, n1);
                          pps.executeUpdate();
                      }
                   }
               //GAUDAR DOS NOMBRES DE LA MADRINA
               }else if(txttercerpad2.getText().isEmpty()){
                   //primer nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimerpad2.getText());
                   ResultSet r1= pps.executeQuery();
                   int n1;
                   
                    if(r1.next()){
                      n1=r1.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 1);
                      pps.setInt(4, n1);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtprimerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtprimerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n1=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, n1);
                          pps.executeUpdate();
                      }
                   }
                   
                   //segundo nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtsegundopad2.getText());
                   ResultSet r2= pps.executeQuery();
                   int n2;
                   
                    if(r2.next()){
                      n2=r2.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 2);
                      pps.setInt(4, n2);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtsegundopad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtsegundopad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n2=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, n2);
                          pps.executeUpdate();
                      }
                   }
               //GUARDA TRES NOMBRES DE LA MADRINA
               }else{
                  //primer nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtprimerpad2.getText());
                   ResultSet r1= pps.executeQuery();
                   int n1;
                   
                    if(r1.next()){
                      n1=r1.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 1);
                      pps.setInt(4, n1);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtprimerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtprimerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n1=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 1);
                          pps.setInt(4, n1);
                          pps.executeUpdate();
                      }
                   }
                   
                   //segundo nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txtsegundopad2.getText());
                   ResultSet r2= pps.executeQuery();
                   int n2;
                   
                    if(r2.next()){
                      n2=r2.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 2);
                      pps.setInt(4, n2);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txtsegundopad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txtsegundopad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n2=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 2);
                          pps.setInt(4, n2);
                          pps.executeUpdate();
                      }
                   }
                   
                   //tercer nombre
                   pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                   pps.setString(1, txttercerpad2.getText());
                   ResultSet r3= pps.executeQuery();
                   int n3;
                   
                    if(r3.next()){
                      n3=r3.getInt("id_nombre");
                      pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                      pps.setInt(1, idmadrina);
                      pps.setInt(2, nob);
                      pps.setInt(3, 3);
                      pps.setInt(4, n3);
                      pps.executeUpdate();
                    }else{
                      pps=cone.prepareStatement("INSERT INTO nombre(nombre) VALUES(?)");
                      pps.setString(1, txttercerpad2.getText());
                      pps.executeUpdate();  
                      
                      pps=cone.prepareStatement("SELECT id_nombre FROM nombre WHERE nombre=?");
                      pps.setString(1, txttercerpad2.getText());
                      ResultSet rs= pps.executeQuery();
                      
                      while(rs.next()){
                          n3=rs.getInt("id_nombre");
                          pps=cone.prepareStatement("INSERT INTO nombres_padrepa VALUES(?,?,?,?)");
                          pps.setInt(1, idmadrina);
                          pps.setInt(2, nob);
                          pps.setInt(3, 3);
                          pps.setInt(4, n3);
                          pps.executeUpdate();
                      }
                   }                                     
               }
                
              //GUARDA LOS APELLIDOS DE LA MADRINA
              
              if(txtsegundoapad2.getText().isEmpty()){
                 //primer apellido
                 pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                 pps.setString(1, txtprimerapad2.getText());
                 ResultSet rt1= pps.executeQuery(); 
                 int idap1;
                 
                 if(rt1.next()){
                     idap1=rt1.getInt("id_apellido");
                     pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                     pps.setInt(1, idmadrina);
                     pps.setInt(2, nob);
                     pps.setInt(3, 1);
                     pps.setInt(4, idap1);
                     pps.executeUpdate();
                 }else{
                     pps=cone.prepareStatement("INSER INTO apellido(apellido) WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     pps.executeUpdate();
                     
                     pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     ResultSet rs= pps.executeQuery();
                     
                     while(rs.next()){
                        idap1=rs.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                        pps.setInt(1, idmadrina);
                        pps.setInt(2, nob);
                        pps.setInt(3, 1);
                        pps.setInt(4, idap1);
                        pps.executeUpdate();
                     }
                 }
              //GUARDA DOS APELLIDOS DE LA MADRINA
              }else{
                 //primer apellido
                 pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                 pps.setString(1, txtprimerapad2.getText());
                 ResultSet rt1= pps.executeQuery(); 
                 int idap1;
                 
                 if(rt1.next()){
                     idap1=rt1.getInt("id_apellido");
                     pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                     pps.setInt(1, idmadrina);
                     pps.setInt(2, nob);
                     pps.setInt(3, 1);
                     pps.setInt(4, idap1);
                     pps.executeUpdate();
                 }else{
                     pps=cone.prepareStatement("INSER INTO apellido(apellido) WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     pps.executeUpdate();
                     
                     pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                     pps.setString(1, txtprimerapad2.getText());
                     ResultSet rs= pps.executeQuery();
                     
                     while(rs.next()){
                        idap1=rs.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                        pps.setInt(1, idmadrina);
                        pps.setInt(2, nob);
                        pps.setInt(3, 1);
                        pps.setInt(4, idap1);
                        pps.executeUpdate();
                     }
                 }
                 
                 //segundo apellido
                 pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                 pps.setString(1, txtsegundoapad2.getText());
                 ResultSet rt2= pps.executeQuery(); 
                 int idap2;
                 
                 if(rt2.next()){
                     idap2=rt2.getInt("id_apellido");
                     pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                     pps.setInt(1, idmadrina);
                     pps.setInt(2, nob);
                     pps.setInt(3, 2);
                     pps.setInt(4, idap2);
                     pps.executeUpdate();
                 }else{
                     pps=cone.prepareStatement("INSER INTO apellido(apellido) WHERE apellido=?");
                     pps.setString(1, txtsegundoapad2.getText());
                     pps.executeUpdate();
                     
                     pps=cone.prepareStatement("SELECT id_apellido FROM apellido WHERE apellido=?");
                     pps.setString(1, txtsegundoapad2.getText());
                     ResultSet rs= pps.executeQuery();
                     
                     while(rs.next()){
                        idap2=rs.getInt("id_apellido");
                        pps=cone.prepareStatement("INSERT INTO apellidos_padrepa VALUES(?,?,?,?)");
                        pps.setInt(1, idmadrina);
                        pps.setInt(2, nob);
                        pps.setInt(3, 1);
                        pps.setInt(4, idap2);
                        pps.executeUpdate();
                     }
                 }
              }
            }
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Datos Guardados");
            alert.setContentText("Se guardaron los datos del bautismo exitósamente");
            alert.showAndWait();     
     
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

    @FXML
    private void irBuscarBautismo(ActionEvent event) {
       try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/vistaBuscarBautismos.fxml"));
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
