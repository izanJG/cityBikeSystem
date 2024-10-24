package uva.poo.packs;

import java.util.ArrayList;

import uva.poo.bicis.Bike;
import uva.poo.practica2.IResource;

/**
 * Tipo de dato abstracto que representa un pack, ya pueda ser de grupo o familiar
 * Se garantiza que un pack nunca estará compuesto por dos bicis iguales
 * Proporciona funcionalidades para añadir y eliminar bicis respetando siempre los límites correspondientes
 * @author izajime
 * @author asigarc
 *
 */
public abstract class Pack implements IResource{
	private ArrayList<Bike> listaBicis;
	
	/**
	 * Construye un pack de bicis a partir de un array de bicis, se garantiza que no hay bicis repetidas en el pack
	 * @param listaBicis array de datos de la clase Bike que representará la lista de bicis que se desea para el pack
	 * @throws NullPointerException cuando {@code listaBicis == null}
	 * @throws NullPointerException cuando {algún elemento de la lista = null}
	 * @throws IllegalArgumentException cuando {hay bicis repetidas}
	 */
	protected Pack (Bike[] listaBicis) {
		if (listaBicis == null) throw new NullPointerException("Llamada incorrecta: listaBicis = null");
		this.listaBicis = new ArrayList<>();
		for (Bike bici : listaBicis) {
			if (bici == null) throw new NullPointerException("Llamada incorrecta: La lista tiene que estar llena al completo de bicis");
			for (Bike bicisIguales : this.listaBicis) {
				if(bici.equals(bicisIguales)) throw new IllegalArgumentException ("Llamada incorrecta: No puede haber bicis repetidas");
			}
			this.listaBicis.add(bici);
		}
	}
	
	/**
	 * Consulta el numero de bicis que componen el pack
	 * @return un dato entero que representa el numero de bicis del pack
	 */
	public int numBicis() {
		return listaBicis.size();
	}

	/**
	 * Consulta si una bici cualquiera dada está en el pack
	 * @param comprobarBici objeto de la clase bici que representa la bici que se quiere comprobar si está en el pack
	 * @return un valor lógico que indica si la bici está en el pack: true (si está), false (si no está)
	 */
	public boolean estaBici(Bike comprobarBici) {
		for (Bike bici : listaBicis) {
			if (bici.equals(comprobarBici))return true;
		}
		return false;
	}
	
	/**
	 * Consulta si un objeto cualquiera es igual a este objeto
	 * @return un valor lógico que toma valor true (si son el mismo) o false (si no son el mismo)
	 */
	@Override
	public boolean equals (Object objeto) {
		if (!(objeto instanceof Pack)) return false;
		Pack otroPack = (Pack)objeto;
		if(listaBicis == otroPack.getListaBicis()) return false;
		return true;
	}

	/**
	 * Consulta el factor de correción del pack, devolverá valores diferentes para packs diferentes
	 * Para el pack familiar devolverá 0.5 por su descuento del 50% y para el pack de grupo devolverá 0.8
	 * por su descuento del 20%.
	 * @return un valor double correspondiente al factor de correción de la bici.
	 */
	public abstract double getFactorDeCorreccion();
	
	/**
	 * Permite eliminar una bici de un pack respetando las limitaciones de cada pack
	 * @param identificador un dato tipo String que representa el identificador de la bici que se quiere eliminar
	 */
	public abstract void eliminarBiciPack(String identificador);
	
	/**
	 * Devuelve la información del pack en forma de String. Proporciona el número de bicis que componen el pack.
	 */
	@Override
	public  String toString() {
		return "Este pack tiene " + numBicis() + " bicis";
	}
	
	/**
	 * Permite agregar una nueva bici al pack, se garantiza que esta bici no puede ser igual a otr que ya haya en el pack
	 * @param nuevaBici un objeto de la calse Bike que representa la bici que se quiere agregar al pack
	 */
	public void agregarBiciPack(Bike nuevaBici) {
		for (Bike bici : listaBicis) {
			if (bici.equals(nuevaBici)) {
				throw new IllegalArgumentException("Llamada incorrecta: la bici ya está contenida en el pack");
			}
		}
		listaBicis.add(nuevaBici);
	}
	
	/**
	 * Consulta el precio del pack dependiendo del descuento aplicado a la suma del precio de todas las bicis
	 * @return un valor double que representa el precio a pagar para el pack
	 */
	public double getDepositToPay(double deposit) {
		double precio = 0;
		for (Bike bici : listaBicis) {
			precio = precio + bici.getDepositToPay(deposit);
		}
		return precio * getFactorDeCorreccion();
	}
	
	/**
	 * Consulta todas las bicis que componen el pack
	 * @return una lista con todas las bicis que componen al pack
	 */
	public ArrayList<Bike> getListaBicis() {
		return listaBicis;
	}
	
	/**
	 * Permite actualizar la lista de bicis que componen al pack
	 * @param nuevaLista la lista que se desea establecer como nueva lista de bicis del pack
	 */
	public void setListaBicis(ArrayList<Bike> nuevaLista) {
		listaBicis = nuevaLista;
	}
	
	/**
	 * Consulta el numero de bicis de niño que tiene el pack
	 * @return un valor tipo entero que no puede ser negativo
	 */
	public int getNumeroChild() {
		int contadorChild = 0;
		for (Bike bici : listaBicis) {
			if (bici.isChild()) contadorChild++;
		}
		return contadorChild;
	}
	
	/**
	 * Consulta el numero de bicis de adulto que tiene el pack
	 * @return un valor tipo entero que no puede ser negativo
	 */
	public int getNumeroAdultos() {
		int contadorAdulto = 0;
		for (Bike bici : listaBicis) {
			if (bici.isAdult()) contadorAdulto++;
		}
		return contadorAdulto;
	}
	
	/**
	 * Consulta el numero de bicis eléctricas que tiene el pack
	 * @return un valor tipo entero que no puede ser negativo
	 */
	public int getNumeroElectricas() {
		int contadorElectrica = 0;
		for (Bike bici : listaBicis) {
			if (bici.isElectric()) contadorElectrica++;
		}
		return contadorElectrica;
	}
}
