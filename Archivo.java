package com.url.edu.gt.lectormetadatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moonrain
 */
public class Archivo {

    private int x;
    private int y;
    private int contImg;
    private float tamanoArchivo;
    private int numPaginas;
    private final byte[] titulo = new byte[50];
    private final byte[] asunto = new byte[50];
    private final byte[] productor = new byte[50];
    private final byte[] pdfVersion = new byte[50];
    private final byte[] palabrasClave = new byte[100];
    private final ArrayList<String> fuentes = new ArrayList<>();

    public void extraerMetadatos(String archivo) {

        try {
            RandomAccessFile data = new RandomAccessFile(archivo, "r");

            //Recorrer todo el documento
            /**
             *
             */
            int letra = 0;
            int contador = 0;
            int contarCajasDeTamanio = 0;

            byte caracter = ' ', caracterTitulo = ' ', caracterProductor = ' ', caracterAsunto = ' ', caracterKeyWords = ' ', caracterFont = ' ', caracterTamPagina = ' ';
            byte[] fuente = new byte[50];
            byte[] tamPagina = new byte[25];

            String tamX = "";
            String tamY = "";

            boolean terminaParentesis = false;

            tamanoArchivo = (int) data.length();

            data.readByte();
            while (caracter != '%') {
                caracter = data.readByte();
                if (caracter != '%') {
                    pdfVersion[contador] = caracter;
                }
                contador++;
            }
            contador = 0;

            while (letra != -1) {
                letra = data.read();
                if (letra == 41 && data.read() == 47 || terminaParentesis == true && letra == 47) {

                    letra = data.read();
                    terminaParentesis = false;

                    //Título del archivo
                    if (letra == 84 && data.read() == 105 && data.read() == 116 && data.read() == 108 && data.read() == 101) {

                        while (caracterTitulo != 41) {
                            caracterTitulo = data.readByte();
                            titulo[contador] = caracterTitulo;
                            contador++;
                        }
                        contador = 0;

                    }

                    //Productor del archivo
                    if (letra == 80 && data.read() == 114 && data.read() == 111 && data.read() == 100 && data.read() == 117 && data.read() == 99 && data.read() == 101 && data.read() == 114) {

                        while (caracterProductor != 41) {
                            caracterProductor = data.readByte();
                            productor[contador] = caracterProductor;
                            contador++;
                        }
                        contador = 0;
                        terminaParentesis = true;

                    }

                    //Palabras clave
                    if (letra == 75 && data.read() == 101 && data.read() == 121 && data.read() == 119 && data.read() == 111 && data.read() == 114 && data.read() == 100 && data.read() == 115) {

                        while (caracterKeyWords != 41) {
                            caracterKeyWords = data.readByte();
                            palabrasClave[contador] = caracterKeyWords;
                            contador++;
                        }
                        contador = 0;

                    }

                    //Asunto
                    if (letra == 83 && data.read() == 117 && data.read() == 98 && data.read() == 106 && data.read() == 101 && data.read() == 99 && data.read() == 116) {

                        while (caracterAsunto != 41) {
                            caracterAsunto = data.readByte();
                            asunto[contador] = caracterAsunto;
                            contador++;
                        }
                        contador = 0;

                    }

                }

                //Fuentes del documento
                if (letra == 'B' && data.read() == 'a' && data.read() == 's' && data.read() == 'e' && data.read() == 'F' && data.read() == 'o' && data.read() == 'n' && data.read() == 't' && data.read() == '/') {

                    while (caracterFont != '/') {
                        caracterFont = data.readByte();

                        if (caracterFont != '/') {
                            fuente[contador] = caracterFont;
                        }

                        contador++;
                    }

                    boolean repetida = false;
                    for (String font : getFuentes()) {
                        if (font.equals(new String(fuente))) {
                            repetida = true;
                        }
                    }

                    if (repetida != true) {
                        getFuentes().add(new String(fuente));
                    }

                    caracterFont = ' ';
                    contador = 0;

                }

                if (letra == 'P') {
                    letra = data.read();
                    //Listado de imagenes
                    if (letra == 'r' && data.read() == 'o' && data.read() == 'c'
                            && data.read() == 'S' && data.read() == 'e' && data.read() == 't') {

                        contImg++;

                    }

                }

                //Número de páginas
                if (letra == 'T' && data.read() == 'y' && data.read() == 'p' && data.read() == 'e' && data.read() == '/' && data.read() == 'P' && data.read() == 'a' && data.read() == 'g' && data.read() == 'e') {
                    if (data.read() != 's') {
                        numPaginas++;
                    }
                }

                //Tamaño de página
                if (letra == 'M' && data.read() == 'e' && data.read() == 'd'
                        && data.read() == 'i' && data.read() == 'a' && data.read() == 'B' && data.read() == 'o' && data.read() == 'x') {

                    for (int i = 0; i < 5; i++) {
                        data.read();
                    }

                    while (caracterTamPagina != ']' && contarCajasDeTamanio < 4) {
                        caracterTamPagina = data.readByte();

                        if (caracterTamPagina != ']') {
                            tamPagina[contador] = caracterTamPagina;
                        } else {
                            tamY = new String(tamPagina);
                            tamPagina = new byte[25];
                        }

                        if (caracterTamPagina == ' ') {
                            tamX = new String(tamPagina);
                            tamPagina = new byte[25];
                        }

                        contador++;
                    }
                    contarCajasDeTamanio++;

                    caracterTamPagina = ' ';
                    tamPagina = new byte[50];
                    contador = 0;
                }

            }
            if (x != 0) {
                x = (int) Math.round(Double.parseDouble(tamX) / 2.835);
            }
            
            if (y != 0) {
                x = (int) Math.round(Double.parseDouble(tamX) / 2.835);
            }

            data.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the tamanoArchivo
     */
    public float getTamanoArchivo() {
        return tamanoArchivo;
    }

    /**
     * @return the numPaginas
     */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return new String(titulo);
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return new String(asunto);
    }

    /**
     * @return the productor
     */
    public String getProductor() {
        return new String(productor);
    }

    /**
     * @return the pdfVersion
     */
    public String getPdfVersion() {
        return new String(pdfVersion);
    }

    /**
     * @return the palabrasClave
     */
    public String getPalabrasClave() {
        return new String(palabrasClave);
    }

    /**
     * @return the fuentes
     */
    public ArrayList<String> getFuentes() {
        return fuentes;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the contImg
     */
    public int getContImg() {
        return contImg;
    }
}
