package com.url.edu.gt.lectormetadatos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moonrain
 */
public class Main {

    public static void main(String[] args) {
        Archivo documento = new Archivo();
        documento.extraerMetadatos("datos.pdf");
        
        System.out.println("Tamaño del archivo: " + documento.getTamanoArchivo() / 1000000 + "MB");
        System.out.println("Tamaño de la página: " + documento.getX() + " x " + documento.getY());
        System.out.println("Número de páginas: " + documento.getNumPaginas());
        System.out.println("Título: " + documento.getTitulo());
        System.out.println("Asunto: " + documento.getAsunto());
        System.out.println("Palabras clave: " + documento.getPalabrasClave());
        System.out.println("Versión de PDF: " + documento.getPdfVersion());
        System.out.println("Aplicación con la que fue creado: " + documento.getProductor());
        System.out.println("Cantidad de imágenes: " + documento.getContImg());
        System.out.println("Lista de fuentes: " + documento.getFuentes().toString());
        
        String fuentesS = String.join(",", documento.getFuentes());
        
        try {
            Almacenamiento a = new Almacenamiento();
            a.AgregarDatos(documento.getTitulo(), documento.getAsunto(), fuentesS, documento.getContImg(), (short)documento.getNumPaginas(), documento.getProductor(), 
                    documento.getPdfVersion(), documento.getTamanoArchivo(), documento.getX() + "x" + documento.getY(), documento.getPalabrasClave());
            //a.imprimirIndice();
            //a.buscar("Mendez");
        } catch (IOException | ArchivoNoValidoException ex) {
            Logger.getLogger(Almacenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}