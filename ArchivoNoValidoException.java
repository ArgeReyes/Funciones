package Datosp;

/**
 *
 * @author moonrain
 */
public class ArchivoNoValidoException extends Exception {
     private String mensaje = "El archivo no tiene la estructura de una Agenda";
    
    /**
     *
     */
    public ArchivoNoValidoException() {
        super();
    }   
    
    /**
     *
     * @param mensaje
     */
    public ArchivoNoValidoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
    
    /**
     *
     * @param causa
     */
    public ArchivoNoValidoException(Throwable causa) {
        super(causa);
    }
    
    /**
     *
     * @param mensaje
     * @param causa
     */
    public ArchivoNoValidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.mensaje = mensaje;
    }

    /**
     * Se crea para cuando se necesite escribir directamente la clase
     * @return mensaje
     */
    @Override
    public String toString() {
        return mensaje;
    }

    /**
     * Método para obtner el mensaje, sobre-escrito para que no despliegue
     * el mejsaje de error de la clase heredada.
     * @return mensaje
     */
    @Override
    public String getMessage() {
        return mensaje;
    }
}
