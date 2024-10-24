package uva.poo.practica2;

import uva.poo.bicis.Bike;

/**
 * Tipo de dato que representa una base de anclaje de un parking de bicis
 * Proporciona funcionalidades básicas como poder cambiar el color de la base, meter y sacar una bici,
 * o retornar valores lógicos útiles para el uso de los parkings
 * Se tiene en cuenta que los anclajes se inicializarán siempre con bases de color rojo y sin bici
 * Indicando así que el anclaje está vacío y listo para recibir una bici.
 * Se verifica que cuando la luz de la base esté en verde siempre tendrá una bici, y cuando
 * esté en rojo nunca tendrá una bici.
 * @author izajime
 * @author asigarc
 */
public class Anclaje {
    private String luz;
    private Bike bici;
   
    /**
     * Construye un anclaje el cual, se inicializará de color rojo y sin bici
     */
    public Anclaje() {
        luz = "rojo";
        bici = null;
    }
    
    /**
     * Construye un anclaje igual que el anclaje fuente dado
     * La copia superficial equivale a la copia en profundidad
     * @param fuente no puede ser null, debe estar conectado
     * @throws NullPointerException si {@code fuente == null}
     */
    public Anclaje(Anclaje fuente) {
    	if (fuente == null) throw new NullPointerException("Llamada incorrecta: fuente == null");
    	luz = fuente.getLuz();
    	bici = fuente.getBici();
    }

    /**
     * Consulta el color de la base de anclaje 
     *  @return un dato String indicando el color de la base
     */
    public String getLuz() {
        return luz;
    }

    /**
     * Cambia el color de la luz de la base del anclaje
     * @param nuevoColor nuevo color para la luz. Solo es admitido "verde", "rojo", "azul", "apagado", letras minúsculas
     * @throws IllegalArgumentException cuando {@code nuevoColor =! "verde", "rojo", "azul", "apagado"}
     * @throws NullPointerException cuando {@code nuevoColor == null}
     */
    public void setLuz(String nuevoColor) {
        if (nuevoColor == null) throw new NullPointerException("Llamada incorrecta: nuevoColor == null");
    	if (nuevoColor.equals("verde") || nuevoColor.equals("rojo") || nuevoColor.equals("azul") || nuevoColor.equals("apagado")) {
        	luz = nuevoColor;
        } else throw new IllegalArgumentException("Color de luz para el anlcaje incorrecto");
    }    
    
    /**
     * Nos indica si hay una bici en el anclaje, se comprobará si el color de la luz es verde
     * @return un valor lógico indicando si el anclaje tiene bici (true) o no (false)
     */
    public boolean estaOcupado(){
    	return getLuz().equals("verde");
    }
    
    /**
     * Consulta la bici que hay en el anclaje
     * @return se garantiza un objeto tipo Bike, el cual, puede ser null en el caso de que no haya bici 
     */
    public Bike getBici() {
    	return bici;
    }
    
    /**
     * Establece una nueva bici dada en el anclaje, y en consecuencia la luz del anclaje pasará a ser verde
     * @param nuevaBici la bici que se quiere establecer en el anclaje, no puede ser null
     * * @throws NullPointerException si {@code nuevaBici == null}
     */
    public void meterBici(Bike nuevaBici) {
    	if (nuevaBici == null) throw new NullPointerException("Llamada incorrecta: nuevaBici == null");
    	bici = nuevaBici;
    	luz = "verde";
    }
    
    /**
     * Se establece un valor nulo para la bici del anclaje, null representará que no hay bici en el anclaje 
     * Y en consecuencia la luz del anclaje pasará a ser roja
     */
    public void sacarBici() {
    	bici = null;
    	luz = "rojo";
    }
    
    
    
}