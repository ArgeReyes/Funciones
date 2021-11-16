package com.url.edu.gt.lectormetadatos;

/**
 *
 * @author DavidG2
 */

import java.util.Objects;
public class Referencia {
      private String titulo;
      private long posicion;
    
    /**
     * Constructor sobrecargado, utilizado generalmente para la realización de
     * búsqedas.
     * 
     * @param titulo
     */
    public Referencia(String titulo) {
        this(titulo,0l);
    }
    
    /**
     * Constructor principal, debe de recibir siempre un apellido y la posicoón
     * donde se encuentra el registro.
     * 
     * Las referencias deben de ir creandose en cuanto el programador sepa
     * en que parte ha quedado un registro.
     * 
     * @param titulo de la persona
     * @param posicion desplazamiento del registro.
     * @throws NullPointerException
     */
    public Referencia(String titulo, long posicion) throws NullPointerException{
       this.titulo = obtenerPrimeraPalabra(titulo);
       this.posicion = posicion;
    }

    /**
     * Devuelve el apellido
     * @return apellido
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Metodo para filtrar solamente un apellido de una cadena
     * 
     * En ocasiones se guardarán mas de un apellido, por lo que éste método
     * se asegura de obtener solo uno, separando los apellidos por espacio y
     * obteniendo solamente el primero.
     * 
     * @param s cadena con los apellidos
     * @return un solo apellido
     * @throws NullPointerException
     */
    public static String obtenerPrimeraPalabra(String s) throws NullPointerException{
        if (!s.isEmpty()) {
            String unaPalabra= s.split(" ", 2)[0];
            if (!unaPalabra.isEmpty())
               return unaPalabra;
            else
               throw new NullPointerException("No existe ningún Titulo");
        }
        else
            throw new NullPointerException("No existe ningún Titulo");
    }

    /**
     * Almacena un apellido, en caso de querer cambiar el contenido del mismo.
     * 
     * @param titulo
     * @throws NullPointerException
     */
    public void setApellido(String titulo) throws NullPointerException{
        this.titulo=obtenerPrimeraPalabra(titulo);
    }

    /**
     * Obtiene la posición donde se almacena el registro con los datos.
     * 
     * El desplazamiento siempre es medido desde el inicio del archivo.
     * @return desplazamiento
     */
    public long getPosicion() {
        return posicion;
    }

    /**
     * Almacena la posición del registro con los datos
     * 
     * El desplazamiento debe ser siempre desde el inicio del archivo.
     * 
     * @param posicion
     */
    public void setPosicion(long posicion) {
        this.posicion = posicion;
    }

    /**
     * Método sobre-escrito para permitir realizar comparaciones entre los datos
     * y de ésta manera poder utilizar la búsqueda implementada en un ArrayList
     * 
     * @see java.util.ArrayList#indexOf(java.lang.Object) 
     * 
     * @param o clase Referencia a utilizar para comparar.
     * @return 
     */
    //@Override
    public int compareTo(Referencia o) {
        return this.titulo.compareTo(o.getTitulo());
    }

    /**
     * Metodo sobre-escrito para permitir verificar que elemento es mas pequeño o 
     * grande que otro, generalmente utilizado en los métodos de ordenamiento
     * de ArrayLists.
     * 
     * @see java.util.ArrayList#sort(java.util.Comparator) 
     * @param o Objeto a comparar
     * @return -1 si es menor; 0 si son iguales; 1 si es mayor.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Referencia) {
            return this.titulo.equals(((Referencia)o).getTitulo());
        }            
        else if (o instanceof String) {
            return this.titulo.equals((String)o);
        }
        else
            return false;
    }
    
    /**
     * Método utilizado en algunos casos para crear campos hash e identificar
     * a cada elemento, utilizado en algunos casos para ordenar y para búsquedas.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.titulo);
        return hash;
    }
}

    

