package uva.poo.bicis;

import uva.poo.practica2.Talla;

/**
 * Tipo de dato que representa una bici de niño, se garantiza que tiene las mismas carácteristicas que bici
 * ya que es descendiente de ella, pero que a mayores tiene una talla representativa de una bici de niño.
 * Esta talla debe estar comprendida entre el 12 y el 26 siendo además un número par.  
 * @author izajime
 * @author asigarc
 */
public class ChildBike extends Bike {
	
	private Talla talla;
	
	/**
	 * Construye una bici de niño a partir del constructor de bici y la talla de la bici de niño.
	 * @param marca marca de la bici, se admite cualquier marca.
	 * @param modelo modelo de la bici, se admite cualquier modelo.
	 * @param peso peso de la bici, debe ser extrictamente mayor que 0.
	 * @param platos número de platos de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param piniones número de piñones de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param talla la talla de la bici, solo puede adoptar valores pares entre 12 y 26
	 * @throws IllegalArgumentException cuando {@code talla $ 2 != 0 || @code talla < 12 || @code talla > 26}
	 */
	public ChildBike (String marca, String modelo, double peso, int platos, int piniones, int talla) {
		super(marca, modelo, peso, platos, piniones);
		switch (talla) {
		case 12:
			this.talla = Talla.T12;
			break;
		case 14:
			this.talla = Talla.T14;
			break;
		case 16:
			this.talla = Talla.T16;
			break;
		case 18:
			this.talla = Talla.T18;
			break;
		case 20:
			this.talla = Talla.T20;
			break;
		case 22:
			this.talla = Talla.T22;
			break;
		case 24:
			this.talla = Talla.T24;
			break;
		case 26:
			this.talla = Talla.T26;
			break;
		default:
			throw new IllegalArgumentException("Talla inválida");
		}
	}
	
	/**
	 * Consulta el valor del factor de correción en una bici de niño.
	 * @return un dato tipo double que siempre será 0.85, ya que se aplicará un descuento del 15%.
	 */
	public double getFactorDeCorreccion() {
		return 0.85;
	}
	
	/**
	 * Consulta la talla de la bici de niño.
	 * @return un dato tipo String que puede ser los correspondientes a los números pares entre el 12 y el 26
	 */
	public String getTalla() {
		return talla.getTalla();
	}
	
	/**
	 * Consulta la codificación de la bici de niño, la cual corresponde a su valor númerico de talla 
	 * @return un dato tipo entero, se garantiza que este sea par y esté entre el 12 y el 26.
	 */
	public int getCodificacion() {
		return talla.getCodificacion();
	}
	
	/**
	 * Devolverá la información detallada correspondiente a la bici de niño.
	 * @return un dato de tipo String que represente a la información de la bici.
	 */
	public String toString() {
		return "Bici: " + getIdentificador() + ", marca: " + getMarca() + ", modelo: " + getModelo() 
		+ ", peso: " + getPeso() + ", platos: " + getPlatos() + ", piñones: " + getPiniones() + ", talla: " 
		+ getTalla();
	}
}
