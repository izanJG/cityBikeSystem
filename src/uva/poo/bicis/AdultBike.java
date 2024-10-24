package uva.poo.bicis;

import uva.poo.practica2.Talla;

/**
 * Tipo de dato que representa una bici de adulto, se garantiza que tiene las mismas carácteristicas que bici
 * ya que es descendiente de ella, pero que a mayores tiene una talla representativa de una bici de adulto.
 * Esta talla puede ser pequeña (S), mediana (M), grande (L), o muy grande (XL).
 * @author izajime
 * @author asigarc
 */
public class AdultBike extends Bike {
	
	private Talla talla;
	
	/**
	 * Construye una bici de adulto a partir del constructor de bici y la talla de la bici de adulto.
	 * @param marca marca de la bici, se admite cualquier marca.
	 * @param modelo modelo de la bici, se admite cualquier modelo.
	 * @param peso peso de la bici, debe ser extrictamente mayor que 0.
	 * @param platos número de platos de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param piniones número de piñones de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param talla la talla de la bici, solo puede adoptar los valores "S", "M", "L", "XL"
	 * @throws IllegalArgumentException cuando {@code talla != "S", "M", "L", "XL"}
	 */
	public AdultBike(String marca, String modelo, double peso, int platos, int piniones, String talla) {
		super(marca, modelo, peso, platos, piniones);
		
		switch (talla) {
		case "XL":
			this.talla = Talla.XL;
			break;
		case "L":
			this.talla = Talla.L;
			break;
		case "M":
			this.talla = Talla.M;
			break;
		case "S":
			this.talla = Talla.S;
			break;
		default:
			throw new IllegalArgumentException("Talla inválida");
		}
	}
	
	/**
	 * Consulta la talla de la bici de adulto.
	 * @return un dato tipo String que puede ser "S", "M", "L", "XL".
	 */
	public String getTalla() {
		return talla.getTalla();
	}
	
	/**
	 * Consulta la codificación de la bici de adulto, la cual corresponde a 1 (S), 2 (M) 3 (L) 4 (XL) 
	 * los cuales son los valores elegidos para codificar las tallas de adulto.
	 * @return un dato tipo entero que puede ser 1, 2, 3 o 4.
	 */
	public int getCodificacion() {
		return talla.getCodificacion();
	}
	
	/**
	 * Consulta el valor del factor de correción en una bici de adulto.
	 * @return un dato tipo double que siempre será 1.0, ya que no varía el precio en esta bici.
	 */
	public double getFactorDeCorreccion() {
		return 1.0;
	}
	
	/**
	 * Devolverá la información detallada correspondiente a la bici de adulto.
	 * @return un dato de tipo String que represente a la información de la bici.
	 */
	public String toString() {
		return "Bici: " + getIdentificador() + ", marca: " + getMarca() + ", modelo: " + getModelo() 
		+ ", peso: " + getPeso() + ", platos: " + getPlatos() + ", piñones: " + getPiniones() + ", talla: " 
		+ getTalla();
	}
}
