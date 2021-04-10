/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.Date;
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
public class Bautismo {
   /* int noLibro;
    int folio;
    Date fecha;
    int parroco;
    int presbitero;
    int sexo;
    String primernb;
    String segundonb;
    String tercernb;
    String primerab;
    String segundoab;
    String primernp;
    String segundonp;
    String tercernp;
    String primerap;
    String segundoap;
    String primernm;
    String segundonm;
    String tercernm;
    String primeram;
    String segundoam;
    String primerpad1;
    String segundopad1;
    String tercerpad1;
    String primerapad1;
    String segundoapad1;
    String primerpad2;
    String segundopad2;
    String tercerpad2;
    String primerapad2;
    String segundoapad2;*/
    
    int noBautismo;
    String fechaB;
    String nombreB;
    String fechaNac;
    String madre;
    String padre;
    String Padrino;
    String Madrina;

    public Bautismo(int noBautismo, String fechaB, String nombreB, String fechaNac, String madre, String padre, String Padrino, String Madrina) {
        this.noBautismo = noBautismo;
        this.fechaB = fechaB;
        this.nombreB = nombreB;
        this.fechaNac = fechaNac;
        this.madre = madre;
        this.padre = padre;
        this.Padrino = Padrino;
        this.Madrina = Madrina;
    }

    public int getNoBautismo() {
        return noBautismo;
    }

    public String getFechaB() {
        return fechaB;
    }

    public String getNombreB() {
        return nombreB;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getMadre() {
        return madre;
    }

    public String getPadre() {
        return padre;
    }

    public String getPadrino() {
        return Padrino;
    }

    public String getMadrina() {
        return Madrina;
    }
    
    public static void llenarTabla(Connection cone, ObservableList<Bautismo>lista){
        try {
            for(int i=1;i<=2;i++){
            String sql="SELECT sacramento.no_bautismo, DATE_FORMAT(sacramento.fecha_bautismo, '%d-%m-%Y') AS fechaBautismo, CONCAT(nombre.nombre, ' ', apellido.apellido) as Bautizado, DATE_FORMAT(sacramento.fecha_nac, '%d-%m-%Y') as fechaNacimiento, " +
                        "(SELECT CONCAT(nombre.nombre, ' ', apellido.apellido) FROM nombre,nombres_padrepa,apellido,apellidos_padrepa, padresp_bautizado, sacramento WHERE nombres_padrepa.no_bautismo=sacramento.no_bautismo AND nombres_padrepa.id_nombre=nombre.id_nombre AND padresp_bautizado.id_pad=nombres_padrepa.id_pad AND apellidos_padrepa.no_bautismo=sacramento.no_bautismo AND padresp_bautizado.id_pad=apellidos_padrepa.id_pad AND apellidos_padrepa.id_apellido=apellido.id_apellido AND padresp_bautizado.padre_padrino=0 AND padresp_bautizado.genero=1 GROUP BY padresp_bautizado.id_pad) as Madre, " +
                            "(SELECT CONCAT(nombre.nombre, ' ', apellido.apellido) FROM nombre,nombres_padrepa,apellido,apellidos_padrepa, padresp_bautizado, sacramento " +
                            "WHERE nombres_padrepa.no_bautismo=sacramento.no_bautismo AND nombres_padrepa.id_nombre=nombre.id_nombre AND padresp_bautizado.id_pad=nombres_padrepa.id_pad AND apellidos_padrepa.no_bautismo=sacramento.no_bautismo AND padresp_bautizado.id_pad=apellidos_padrepa.id_pad AND apellidos_padrepa.id_apellido=apellido.id_apellido AND padresp_bautizado.padre_padrino=0 AND padresp_bautizado.genero=0 GROUP BY padresp_bautizado.id_pad) as Padre, " +
                                "(SELECT CONCAT(nombre.nombre, ' ', apellido.apellido) FROM nombre,nombres_padrepa,apellido,apellidos_padrepa, padresp_bautizado, sacramento WHERE nombres_padrepa.no_bautismo=sacramento.no_bautismo AND nombres_padrepa.id_nombre=nombre.id_nombre AND padresp_bautizado.id_pad=nombres_padrepa.id_pad AND apellidos_padrepa.no_bautismo=sacramento.no_bautismo AND padresp_bautizado.id_pad=apellidos_padrepa.id_pad AND apellidos_padrepa.id_apellido=apellido.id_apellido AND padresp_bautizado.padre_padrino=1 AND padresp_bautizado.genero=0 GROUP BY padresp_bautizado.id_pad) as Padrino, " +
                                    "(SELECT CONCAT(nombre.nombre, ' ', apellido.apellido) FROM nombre,nombres_padrepa,apellido,apellidos_padrepa, padresp_bautizado, sacramento WHERE nombres_padrepa.no_bautismo=sacramento.no_bautismo AND nombres_padrepa.id_nombre=nombre.id_nombre AND padresp_bautizado.id_pad=nombres_padrepa.id_pad AND apellidos_padrepa.no_bautismo=sacramento.no_bautismo AND padresp_bautizado.id_pad=apellidos_padrepa.id_pad AND apellidos_padrepa.id_apellido=apellido.id_apellido AND padresp_bautizado.padre_padrino=1 AND padresp_bautizado.genero=1 GROUP BY padresp_bautizado.id_pad) as Madrina " +
                        "FROM sacramento, nombre, nombres_bautizado, apellido, apellidos_bautizado, padresp_bautizado, nombres_padrepa, apellidos_padrepa\n" +
                        "WHERE sacramento.no_bautismo=nombres_bautizado.no_bautismo AND sacramento.no_bautismo=apellidos_bautizado.no_bautismo AND nombres_bautizado.id_nombre=nombre.id_nombre AND apellidos_bautizado.id_apellido=apellido.id_apellido AND nombres_padrepa.no_bautismo=sacramento.no_bautismo AND apellidos_padrepa.no_bautismo AND nombres_padrepa.id_pad=padresp_bautizado.id_pad AND apellidos_padrepa.id_pad=padresp_bautizado.id_pad " +
                        "GROUP BY sacramento.no_bautismo";
            Statement st =cone.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                lista.add(
                     new Bautismo(
                              rs.getInt("sacramento.no_bautismo"),
                              rs.getString("fechaBautismo"),
                              rs.getString("Bautizado"),
                              rs.getString("fechaNacimiento"),
                              rs.getString("Madre"),
                              rs.getString("Padre"),
                              rs.getString("Padrino"),
                              rs.getString("Madrina")
                             
                       )
                );
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bautismo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
}
