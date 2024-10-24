package uva.poo.packs;

import java.util.ArrayList;

import uva.poo.bicis.Bike;

/**
 * Tipo de dato que implementa un pack familiar a partir de la clase abstracta pack
 * Proporciona funcionalidades como eliminar las bicis necesarias cumpliendo un mínimo de bicis de niño de 4 y un
 * mínimo de bicis de 4 
 * Además se podrá obtener el factor de correción correspondiente que es de un 50 %
 * De las bicis que contine el pack.
 * @author izajime
 * @author asigarc
 *
 */
public class PackFamiliar extends Pack {

	/**
	 * Construye un nuevo pack familiar a partir de una lista de bicis
	 * Se garantiza que el número de bicis de niño es al menos 2 y el número de bicis totales es al menos 4
	 * @param listaBicis una lista de bicis que representa la lista de bicis del pack
	 * @throws IllegalArgumentException cuando {@code contadorChild < 2}
	 * @throws IllegalArgumentException cuando {@code numBicis < 4}
	 */
	public PackFamiliar (Bike[] listaBicis) {
		super(listaBicis);
		int contadorChild = 0;
		for (Bike bici : listaBicis) {
			if (bici.isChild()) contadorChild++;
		}
		if (listaBicis.length < 4) throw new IllegalArgumentException("Un pack familiar debe tener 4 bicis como mínimo");
		if (contadorChild < 2) throw new IllegalArgumentException("Un pack familiar debe tener 2 bicis infantiles como mínimo");
	}
	
	/**
	 * Permite eliminar una bici a partir de una otra cualquiera dada comprobando que 
	 * no se incumplen las características del pack familiar.
	 * @param identificador un dato tipo String que representa el identificador de la bici que se quiere eliminar
	 * @throws IllegalArgumentException ninguna bici corresponde con el identificador introducido {@code !eliminado}
	 * @throws IllegalArgumentException hay menos de 4 bicis {@code numBicis < 4}
	 * @throws IllegalArgumentException hay menos de 2 bicis infantiles {@code contadorChild < 2}
	 */
	public void eliminarBiciPack(String identificador) {
		ArrayList<Bike> nuevaLista = getListaBicis();
		boolean eliminado = false;
		
		for (int i = 0; i < numBicis(); i++) {
			if (nuevaLista.get(i).getIdentificador().equals(identificador)) {
				nuevaLista.remove(i);
				eliminado = true;
			}
		}
		
		if (!eliminado) throw new IllegalArgumentException("Llamada incorrecta: Ninguna bici corresponde con el identificador introducido");
		setListaBicis(nuevaLista);
		if (numBicis() < 4) throw new IllegalArgumentException("Llamada incorrecta: El pack de grupo debe tener un mínimo de 4 bicis");
		
		int contadorChild = 0;
		for (Bike bici : getListaBicis()) {
			if (bici.isChild()) contadorChild++;
		}
		if (contadorChild < 2) throw new IllegalArgumentException("Un pack familiar debe tener 2 bicis infantiles como mínimo");
	}
	
	/**
	 * El descuento que hay que aplicar a la suma de las fianzas individuales
	 * @return un valor double que representa el descuento del 50% en el pack familiar
	 */

	public double getFactorDeCorreccion() {
		return 0.5;
	}
}
