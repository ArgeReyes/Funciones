package com.url.edu.gt.lectormetadatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Esta clase se utiliza para cargar verificar y manipular información de los
 * archivos que contengan información en estructura de una agenda.
 *
 * @author moonrain
 */
public class Estructura {

    private final String nombreArchivo;
    private final String marcaDeInicio = "dta";
    private RandomAccessFile estructura;
    private final ArrayList<Registro> registros;
    private final ArrayList<Referencia> indice;

    /**
     * Constructor de la estructura del archivo
     *
     * @param ruta
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ArchivoNoValidoException
     */
    public Estructura(String ruta) throws IOException, FileNotFoundException, ArchivoNoValidoException {
        registros = new ArrayList();
        indice = new ArrayList();
        if (ruta.isEmpty()) {
            throw new IOException("No se puede crear un archivo sin un nombre de archivo");
        }
        nombreArchivo = ruta;
        validarArchivo();
    }

    public final boolean validarArchivo() throws FileNotFoundException, IOException, ArchivoNoValidoException {
        File archivo;
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            estructura = new RandomAccessFile(nombreArchivo, "rw");
            estructura.writeBytes(marcaDeInicio);
            estructura.write(Conversion.deLongA3Bytes(estructura.getFilePointer() + 3));
            byte[] cantidadDatos = {0, 0, 0};
            estructura.write(cantidadDatos);

            estructura.getFD().sync();
        } else {
            estructura = new RandomAccessFile(nombreArchivo, "rw");
            byte[] marca = new byte[marcaDeInicio.length()];
            estructura.read(marca);
            if (!marcaDeInicio.equals(new String(marca))) {
                throw new ArchivoNoValidoException();
            }
        }
        return true;
    }

    private long moverAIndice() throws IOException {
        estructura.seek(3);

        byte[] b = new byte[3];
        estructura.read(b);
        int posIndice = Conversion.de3BytesAInt(b);

        estructura.seek(posIndice);
        return posIndice;
    }

    public int contarRegistros() throws IOException {
        moverAIndice();
        byte[] b = new byte[3];
        estructura.read(b);
        return Conversion.de3BytesAInt(b);
    }

    public void leerIndice() throws IOException {
        int contador = contarRegistros();
        indice.clear();
        if (contador > 0) {
            for (int i = 0; i < contador; i++) {
                indice.add(ManejadorArchivo.leerReferenciaIndice(estructura));
            }
        }
    }
/*
    public Registro buscarPersona(String titulo) throws IOException, ArchivoNoValidoException {
        Registro r = null;
        leerIndice();
        int indiceReferencia = indice.indexOf(new Referencia(titulo));
        if (indiceReferencia >= 0) {
            Referencia ref = indice.get(indiceReferencia);
            estructura.seek(ref.getPosicion());
            r = ManejadorArchivo.leerRegistro(estructura);
        } else {
            System.out.println("no encontrado");
        }

        return r;
    }
*/
    public void agregarRegistro(Registro r) {
        registros.add(r);
    }

    public void escribirDatos() throws IOException {
        estructura.seek(6);

        // nos aseguramos de que el índice se encuentre vacío
        indice.clear();

        for (Registro r : registros) {
            indice.add(new Referencia(r.getTitulo(), estructura.getFilePointer()));
            ManejadorArchivo.escribirRegistro(r, estructura);
        }

        indice.sort(null);

        long posicionIndice = estructura.getFilePointer();

        estructura.write(Conversion.deLongA3Bytes(registros.size()));

        for (Referencia f : indice) {
            ManejadorArchivo.escribirReferenciaIndice(f, estructura);
        }

        estructura.setLength(estructura.getFilePointer());

        estructura.seek(3);
        estructura.write(Conversion.deLongA3Bytes(posicionIndice));

    }

    public void cerrar() throws IOException {
        estructura.close();
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public ArrayList<Referencia> getIndice() {
        return indice;
    }
}
