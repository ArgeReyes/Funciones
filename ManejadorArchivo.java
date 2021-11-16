package com.url.edu.gt.lectormetadatos;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author moonrain
 */
public class ManejadorArchivo {

    public static void escribirRegistro(Registro r, RandomAccessFile f) throws IOException {

        byte[] longitudTitulo = {(byte) r.getTitulo().length()};
        byte[] longitudAsunto = {(byte) r.getAsunto().length()};
        byte[] longitudProductor = {(byte) r.getProductor().length()};
        byte[] longitudPdfVersion = {(byte) r.getPdfVersion().length()};
        byte[] longitudPalabrasClave = {(byte) r.getPalabrasClave().length()};
        byte[] longitudFuentes = {(byte) r.getFuentes().length()};
        byte[] longitudTamPaginas = {(byte) r.getTamPaginas().length()};

        f.write(longitudTitulo);
        f.writeBytes(r.getTitulo());
        f.write(longitudAsunto);
        f.writeBytes(r.getAsunto());
        f.write(longitudProductor);
        f.writeBytes(r.getProductor());
        f.write(longitudPdfVersion);
        f.writeBytes(r.getPdfVersion());
        f.write(longitudPalabrasClave);
        f.writeBytes(r.getPalabrasClave());
        f.write(longitudFuentes);
        f.writeBytes(r.getFuentes());
        f.write(longitudTamPaginas);
        f.writeBytes(r.getTamPaginas());
        f.writeBytes(r.getNumImagenes() + "");
        f.writeBytes(r.getTamArchivo() + "");
        f.writeBytes(r.getNumPaginas() + "");
    }
/*
    public static leerRegistro(RandomAccessFile f) throws IOException, ArchivoNoValidoException {
        Registro r;

        byte[] titulo = null;
        int longitudTitulo;
        longitudTitulo = Conversion.unsignedByteAInt(f.readByte());
        if (longitudTitulo > 0) {
            titulo = new byte[longitudTitulo];
            f.read(titulo);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] asunto = null;
        int longitudAsunto;
        longitudAsunto = Conversion.unsignedByteAInt(f.readByte());
        if (longitudAsunto > 0) {
            asunto = new byte[longitudAsunto];
            f.read(asunto);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] productor = null;
        int longitudProductor;
        longitudProductor = Conversion.unsignedByteAInt(f.readByte());
        if (longitudProductor > 0) {
            productor = new byte[longitudProductor];
            f.read(productor);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] pdfVersion = null;
        int longitudpdfVersion;
        longitudpdfVersion = Conversion.unsignedByteAInt(f.readByte());
        if (longitudpdfVersion > 0) {
            pdfVersion = new byte[longitudpdfVersion];
            f.read(pdfVersion);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] palabrasClave = null;
        int longitudPalabrasClave;
        longitudPalabrasClave = Conversion.unsignedByteAInt(f.readByte());
        if (longitudPalabrasClave > 0) {
            palabrasClave = new byte[longitudPalabrasClave];
            f.read(palabrasClave);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] fuentes = null;
        int longitudFuentes;
        longitudFuentes = Conversion.unsignedByteAInt(f.readByte());
        if (longitudFuentes > 0) {
            fuentes = new byte[longitudFuentes];
            f.read(fuentes);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] tamPaginas = null;
        int longitudTamPaginas;
        longitudTamPaginas = Conversion.unsignedByteAInt(f.readByte());
        if (longitudTamPaginas > 0) {
            tamPaginas = new byte[longitudTamPaginas];
            f.read(tamPaginas);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] tamImagenes = null;
        int longitudTamImagenes;
        longitudTamImagenes = Conversion.unsignedByteAInt(f.readByte());
        if (longitudTamImagenes > 0) {
            tamImagenes = new byte[longitudTamImagenes];
            f.read(tamImagenes);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] numImagenes = null;
        int longitudNumImagenes;
        longitudNumImagenes = Conversion.unsignedByteAInt(f.readByte());
        if (longitudNumImagenes > 0) {
            numImagenes = new byte[2];
            f.read(numImagenes);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] tamArchivo = null;
        int longitudTamArchivo;
        longitudTamArchivo = Conversion.unsignedByteAInt(f.readByte());
        if (longitudTamArchivo > 0) {
            tamArchivo = new byte[longitudTamArchivo];
            f.read(tamArchivo);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        byte[] numPaginas = null;
        int longitudNumPaginas;
        longitudNumPaginas = Conversion.unsignedByteAInt(f.readByte());
        if (longitudNumPaginas > 0) {
            numPaginas = new byte[4];
            f.read(numPaginas);

        } else {
            throw new ArchivoNoValidoException("Estructura de registro no valida");
        }

        r = new Registro(
                new String(asunto),
                new String(fuentes),
                numImagenes,
                numPaginas,
                new String(productor),
                new String(pdfVersion),
                tamArchivo,
                tamPaginas,
                new String(palabrasClave),
                new String(titulo));
        return r;
    }
    */
    
    public static void escribirReferenciaIndice(Referencia r, RandomAccessFile f) throws IOException{
        byte []longitudTitulo = {(byte) r.getTitulo().length()};
        
        f.write(Conversion.deLongA3Bytes(r.getPosicion()));
        f.write(longitudTitulo);
        f.writeBytes(r.getTitulo());
    }
    
    public static Referencia leerReferenciaIndice(RandomAccessFile f) throws IOException, ArrayIndexOutOfBoundsException {
        Referencia r;
        byte []titulo;        
        byte []posicion = new byte[3];
        
        f.read(posicion);
        int longitudTitulo;
        
        longitudTitulo = Conversion.unsignedByteAInt(f.readByte());
        if (longitudTitulo > 0) {
            titulo = new byte[longitudTitulo];
            f.read(titulo);
        }
        else
            throw new IOException("La estructura del archivo esta dañada o no es un archivo de agenda");
        r = new Referencia(new String(titulo), Conversion.de3BytesAInt(posicion));
        return r;
    }
}
