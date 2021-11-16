package com.url.edu.gt.lectormetadatos;

/**
 * Clase que permite el almacenamiento de un solo Registro de datos en la
 * Agenda.
 *
 * La estructura de cada registro se puede entender mejor consultando la
 * documentación en el archivo doc/Estructura.md del proyecto.
 *
 * @author moonrain
 */
public class Registro {

    private String titulo;
    private String asunto;
    private String fuentes;
    private int numImagenes;
    private short numPaginas;
    private String productor;
    private String pdfVersion;
    private double tamArchivo;
    private String tamPaginas;
    private String palabrasClave;

    public Registro() {
        this.titulo = new String();
        this.tamArchivo = 0;
        this.numPaginas = 0;
        this.numImagenes = 0;
        this.asunto = new String();
        this.fuentes = new String();
        this.productor = new String();
        this.palabrasClave = new String();
        this.pdfVersion = new String();
        this.tamPaginas = new String();
    }

    /**
     *
     * Permite la creación de un registro con todos los datos necesarios,
     *
     * El número de teléfono siempre se convertirá a 8 caracteres, si tiene mas
     * se eliminan, si tiene menos números se rellena con 0's al momento de
     * almacenarse.
     *
     * @param titulo
     * @param asunto
     * @param fuentes
     * @param numImagenes
     * @param numPaginas
     * @param productor
     * @param pdfVersion
     * @param tamArchivo
     * @param tamPaginas
     * @param palabrasClave 
     */
    public Registro(String titulo,
            String asunto,
            String fuentes,
            int numImagenes,
            short numPaginas,
            String productor,
            String pdfVersion,
            double tamArchivo,
            String tamPaginas,
            String palabrasClave
    ) {
        this.titulo = titulo;
            this.asunto = asunto;
            this.fuentes = fuentes;
            this.numImagenes = numImagenes;
            this.numPaginas = numPaginas;
            this.productor = productor;
            this.pdfVersion = pdfVersion;
            this.tamArchivo = tamArchivo;
            this.tamPaginas = tamPaginas;
            this.palabrasClave = palabrasClave;
    }

    Registro(String asunto, String fuentes, int numImagenes, short numPaginas, String productor, String pdfVersion, double tamArchivo, String tamPaginas, String palabrasClave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the fuentes
     */
    public String getFuentes() {
        return fuentes;
    }

    /**
     * @param fuentes the fuentes to set
     */
    public void setFuentes(String fuentes) {
        this.fuentes = fuentes;
    }

    /**
     * @return the palabrasClave
     */
    public String getPalabrasClave() {
        return palabrasClave;
    }

    /**
     * @param palabrasClave the palabrasClave to set
     */
    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    /**
     * @return the numImagenes
     */
    public int getNumImagenes() {
        return numImagenes;
    }

    /**
     * @param numImagenes the numImagenes to set
     */
    public void setNumImagenes(int numImagenes) {
        this.numImagenes = numImagenes;
    }

    /**
     * @return the numPaginas
     */
    public short getNumPaginas() {
        return numPaginas;
    }

    /**
     * @param numPaginas the numPaginas to set
     */
    public void setNumPaginas(short numPaginas) {
        this.numPaginas = numPaginas;
    }

    /**
     * @return the productor
     */
    public String getProductor() {
        return productor;
    }

    /**
     * @param productor the productor to set
     */
    public void setProductor(String productor) {
        this.productor = productor;
    }

    /**
     * @return the pdfVersion
     */
    public String getPdfVersion() {
        return pdfVersion;
    }

    /**
     * @param pdfVersion the pdfVersion to set
     */
    public void setPdfVersion(String pdfVersion) {
        this.pdfVersion = pdfVersion;
    }

    /**
     * @return the tamArchivo
     */
    public double getTamArchivo() {
        return tamArchivo;
    }

    /**
     * @param tamArchivo the tamArchivo to set
     */
    public void setTamArchivo(double tamArchivo) {
        this.tamArchivo = tamArchivo;
    }

    /**
     * @return the tamPaginas
     */
    public String getTamPaginas() {
        return tamPaginas;
    }

    /**
     * @param tamPaginas the tamPaginas to set
     */
    public void setTamPaginas(String tamPaginas) {
        this.tamPaginas = tamPaginas;
    }

    /**
     * Metodo implementado para cuando se utilice una escritura rápida del
     * contenido del registro.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Tamaño del archivo: " + tamArchivo
                + "\nNúmero de páginas: " + numPaginas
                + "\nTítulo: " + titulo
                + "\nAsunto: " + asunto
                + "\nProductor : " + titulo
                + "\nVersión del PDF : " + pdfVersion
                + "\nPalabras clave: " + palabrasClave
                + "\nFuentes: " + fuentes
                + "\nTamaño del archivo: " + tamArchivo
                + "\nConteo de Imágenes: " + numImagenes;
    }
}
