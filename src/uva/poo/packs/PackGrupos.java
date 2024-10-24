package uva.poo.packs;

import java.util.ArrayList;

import uva.poo.bicis.Bike;

/**
 * Tipo de dato que implementa un pack para grupos a partir de la clase abstracta pack
 * Proporcina funcionalidades como eliminar las bicis necesarias cumpliendo un mínimo de bicis de 10
 * Además permite obtener el factor de corrección correspondiente, 80%
 * @author izajime
 * @author asigarc
 *
 */

public class PackGrupos extends Pack{

	/**
	 * Construye un nuevo pack de grupo a partir de una lista de bicis
	 * Se garantiza que el número de bicis totales es al menos 10
	 * @param listaBicis una lista de bicis que representa la lista de bicis del pack
	 * @throws IllegalArgumentException cuando {@code numBicis < 10}
	 */
	public PackGrupos (Bike[] listaBicis) {
		super(listaBicis);
		if (listaBicis.length < 10) throw new IllegalArgumentException("Llamada incorrecta: numBicis < 10");
	}
	
	/**
	 * Permite eliminar una bici a partir de una otra cualquiera dada comprobando que 
	 * no se incumplen las características del pack de grupo.
	 * @param identificador un dato tipo String que representa el identificador de la bici que se quiere eliminar
	 * @throws NullPointerException cuando {@code identificador == null}
	 * @throws IllegalArgumentException ninguna bici corresponde con el identificador introducido {@code !eliminado}
	 * @throws IllegalArgumentException hay menos de 4 bicis {@code numBicis < 10}
	 */
	public void eliminarBiciPack(String identificador) {
		if (identificador == null) throw new NullPointerException ("Llamada incorrecta: identificador == null");
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
		if (numBicis() < 10) throw new IllegalArgumentException("Llamada incorrecta: El pack de grupo debe tener un mínimo de 10 bicis");
	}
	
	/**
	 * El descuento que hay que aplicar a la suma de las fianzas individuales
	 * @return un valor double que representa el descuento del 80% en el pack de grupo
	 */
	public double getFactorDeCorreccion() {
		return 0.8;
	}
	
}
