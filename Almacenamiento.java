package Datosp;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author moonrain
 */
public class Almacenamiento {

    private final Estructura archivo;
    private static final String nombreArchivo = "test.txt";

    public Almacenamiento() throws IOException, FileNotFoundException, ArchivoNoValidoException {
        archivo = new Estructura(nombreArchivo);
    }

    public void AgregarDatos(String titulo, String asunto, String fuentes, int numImagenes, short numPaginas, String productor, String pdfVersion, double tamArchivo, String tamPaginas, String palabrasClave) throws IOException {
        archivo.agregarRegistro(new Registro(titulo, asunto,fuentes,numImagenes,numPaginas,productor,pdfVersion,tamArchivo,tamPaginas,palabrasClave));
        archivo.escribirDatos();
    }
}
