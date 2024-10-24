package uva.poo.bicis;

import uva.poo.practica2.Talla;

/**
 * Tipo de dato que representa una bici eléctrica, se garantiza que tiene las mismas carácteristicas que bici
 * ya que es descendiente de ella, pero que a mayores tiene unas características propias de una bici eléctrica:
 * el par motor medido en Nm, el vontaje medido en V y la carga medida en Ah. Además de una talla igual para toda
 * bici eléctrica, una talla única.
 * @author izajime
 * @author asigarc
 */
public class ElectricBike extends Bike {
	private double parMotorNm;
	private int voltajeV;
	private double cargaAh;
	private Talla talla;
	
	/**
	 * Construye una bici eléctrica a partir del constructor de bici y las características de la bici eléctrica:
	 * par motor, voltaje y carga. Además se inicializará la talla a una talla estándar equivalente a la de todas
	 * las bicis eléctricas, debido a que esta es única.
	  @param marca marca de la bici, se admite cualquier marca.
	 * @param modelo modelo de la bici, se admite cualquier modelo.
	 * @param peso peso de la bici, debe ser extrictamente mayor que 0.
	 * @param platos número de platos de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param piniones número de piñones de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param parMotor par motor correspondiente al motor de la bici eléctrica, debe estar en Nm
	 * @param voltaje voltaje correspondiente al motor de la bici eléctrica, debe estar en V
	 * @param carga de la bici eléctrica, debe estar en Ah
	 * @throws IllegalArgumentException cuando {@code voltaje != 24, 36, 48}
	 */
	public ElectricBike (String marca, String modelo, double peso, int platos, int piniones, double parMotor, int voltaje, double carga) {
		super(marca, modelo, peso, platos, piniones);
		if (voltaje != 24 && voltaje !=36 && voltaje != 48) throw new IllegalArgumentException("Llamada incorrecta: voltaje != 24, 36, 48");
		this.parMotorNm = parMotor;
		this.voltajeV = voltaje;
		this.cargaAh = carga;
		this.talla = Talla.TALLA_UNICA;
	}
	
	/**
	 * Consulta el par motor de la bici eléctrica en Nm como unidades.
	 * @return un dato double que representa el par motor de la bici.
	 */
	public double getParMotorNm() {
		return parMotorNm;
	}
	
	/**
	 * Consulta el voltaje de la bici eléctrica en V como unidades.
	 * @return un dato entero que representa el voltaje de la bici.
	 */
	public int getVoltajeV() {
		return voltajeV;
	}
	
	/**
	 * Consulta la carga de la bici eléctrica en Ah como unidades.
	 * @return un dato double que representa la carga de la bici.
	 */
	public double getCargaAh() {
		return cargaAh;
	}
	
	/**
	 * Consulta la talla de la bici eléctrica.
	 * @return un dato tipo String que solo puede ser el que representa a la talla única
	 */
	public String getTalla() {
		return talla.getTalla();
	}
	
	/**
	 * Consulta la codificación de la bici eléctrica, la cual corresponde al 0, 
	 * ya que es el valor que se ha elegido para codificar la talla eléctrica.
	 * @return un dato tipo entero igual a 0.
	 */
	public int getCodificacion() {
		return talla.getCodificacion();
	}
	
	/**
	 * Consulta la energía almacenada en KWh.
	 * @return un dato tipo double que representa el valor de la energía almacenada en KWh.
	 */
	public double getEnergiaAlmacenada() {
		return (voltajeV*cargaAh)/100;
	}
	
	/**
	 * Consulta el valor del factor de correción en una bici eléctrica.
	 * Devolverá un valor dependiendo del voltaje de la bici. 
	 * @return un dato tipo double ya sea 1.24, 1.36 o 1.48 dependiendo de si el voltaje de la bici es 24, 36 o 48 respectivamente
	 */
	public double getFactorDeCorreccion() {
		return 1.0 + (voltajeV/100.0);
	}
	
	/**
	 * Devolverá la información detallada correspondiente a la bici eléctrica.
	 * @return un dato de tipo String que represente a la información de la bici.
	 */
	public String toString() {
		return "Bici: " + getIdentificador() + ", marca: " + getMarca() + ", modelo: " + getModelo() 
		+ ", peso: " + getPeso() + ", platos: " + getPlatos() + ", piñones: " + getPiniones() + ", talla: " 
		+ getTalla() + ", par motor en Nm: " + getParMotorNm() + ", voltaje en voltios: " + getVoltajeV() + 
		", carga en Ah: " + getCargaAh();
		
	}
}
