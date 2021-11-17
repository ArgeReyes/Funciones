/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.File;

/**
 *
 * @author ARGENTINA REYES
 */
public class Proyecto {
public static boolean Hayruta=false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     
        
   
         String archivo = "/ruta/rutapdf.txt";
         File file = new File(archivo);
         if (file.exists()) {
                Hayruta=true;
                
            }
         else{
         enrutador VentanaRuta = new enrutador();
         VentanaRuta.setVisible(true);
         
        
         
         
    }
    }
}
