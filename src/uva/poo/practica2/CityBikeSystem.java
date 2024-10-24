package uva.poo.practica2;

import java.util.ArrayList;

/**
 * Tipo de dato que implementa la posibilidad de hacer un sistema de parkings
 * Presta funcionalidades desde agregar o eliminar un parking de bicis y modificar la fianza de los mismos hasta
 * obtener todos los parkings los cuales dispongan de un anclaje ocupado o vacío dependiendo de la necesidad
 * pudiendo añadir el radio en el que se buscan esos parkings.
 * Los parámetros que implican dar coordenadas se pueden dar en tipo GD o en tipo GMS
 * @author izajime
 * @author asigarc
 */
public class CityBikeSystem {
	private ArrayList<CityBikeParkingPoint> listaParkings;
	private double fianza;
		
	/**
	 * Construye un nuevo sistema a partir de un valor inicial para la fianza
	 * En este caso se supone que la lista de parkings que lo componen estará vacía inicialmente
	 * @param fianza un valor double que deberá ser extrictamente mayor que 0
	 * @throws IllegalArgumentException cuando {@code fianza <= 0}
	 */
	public CityBikeSystem (double fianza) {
		if (fianza <= 0) throw new IllegalArgumentException("Llamada incorrecta: fianza <= 0");
		listaParkings = new ArrayList<>();
		this.fianza = fianza;
	}	
	
	/**
	 * Permite añadir un punto de aparcamiento al sistema
	 * @param nuevoParking no puede ser null
	 * @throws NullPointerException cuando {@code nuevoParking == null}
	 * @throws IllegalArgumentException {@code nuevoParking == a un parking ya agregado al sistema}
	 */
	public void agregarParking(CityBikeParkingPoint nuevoParking) {
		if (nuevoParking == null) throw new NullPointerException("Llamada incorrecta: nuevoParking == null");
		for (CityBikeParkingPoint parking : listaParkings) {
			if (parking.equals(nuevoParking)) throw new IllegalArgumentException("Llamada incorrecta: parking.coordenadas == nuevoParking.coordenadas");
		}
		listaParkings.add(nuevoParking);
		nuevoParking.setFianza(fianza);
	}
	
	/**
	 * Permite eliminar un punto de aparcamiento a partir de uno otro cualquiera dado 
	 * Se buscará el parking en la lista y si no existe en ella se considerará un caso no válido
	 * @param nuevoID dato tipo String que representa el identificador del parking que se quiere eliminar
	 * @throws NullPointerException cuando {@code eliminarParking == null}
	 * @throws IllegalArgumentException cuando {@code idParking no es encontrado en listaParkings}
	 */
	public void eliminarParking(String nuevoID) {
		if (nuevoID == null) throw new NullPointerException ("Llamada incorrecta: nuevoID == null");
		boolean eliminacionRealizada = false;
		for (int i = 0; i<listaParkings.size(); i++) {
			if(listaParkings.get(i).esId(nuevoID)) {
				listaParkings.remove(i);
				eliminacionRealizada = true;
			}
		}
		if (!eliminacionRealizada)throw new IllegalArgumentException("Llamada incorrecta: idParking no existe en listaParkings");
	}
	
	/**
	 * Parmite modificar la cantidad establecida para la fianza
	 * @param nuevaFianza un valor double cualquiera, no puede ser menor que 0
	 * @throws IllegalArgumentException cuando {@code nuevaFianza < 0} 
	 */
	public void setFianza(double nuevaFianza) {
		if (nuevaFianza < 0) throw new IllegalArgumentException("Llamada incorrecta: nuevaFianza < 0");
		fianza = nuevaFianza;
		for (CityBikeParkingPoint parking : listaParkings) {
			parking.setFianza(nuevaFianza);
		}
	}
	
	/**
	 * Consulta la cantidad establecida para la fianza en el sistema en ese momento
	 * @return un valor double que se garantiza que es 0 o mayor
	 */
	public double getFianza() {
		return fianza;
	}

	/**
	 * Consulta todos los parkings que componen al sistema
	 * @return se garantiza una lista con los mismos estados de los parkings, pero sin estar conectada con la fuente
	 */
	public ArrayList<CityBikeParkingPoint> getListaParkings() {
		ArrayList<CityBikeParkingPoint> listaResultado = new ArrayList<>();
		for (int i = 0; i<listaParkings.size(); i++) {
			listaResultado.add(new CityBikeParkingPoint(listaParkings.get(i)));
		}
		return listaResultado;
	}

	/**
	 * Consulta la lista de parkings con algún anclaje ocupado, es decir, bici disponible
	 * @return una lista que garantiza parkings con algún anlcaje ocupado y con el mismo estado que tenían en la lista original
	 */
	public ArrayList<CityBikeParkingPoint> parkingsConOcupados() {
		ArrayList<CityBikeParkingPoint> listaConOcupados = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaParkings) {
			if(parking.anclajesOcupados() > 0) listaConOcupados.add(parking);
		}
		return listaConOcupados;
	}

	/**
	 * Consulta la lista de parkings con algún anclaje vacío, es decir, bici no disponible
	 * @return una lista que garantiza parkings con algún anclaje vacío y con el mismo estado que tenían en la lista original
	 */
	public ArrayList<CityBikeParkingPoint> parkingsConVacios() {
		 ArrayList<CityBikeParkingPoint> listaConVacios = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaParkings) {
			if(parking.anclajesVacios() > 0) listaConVacios.add(parking);
		}
		return listaConVacios;
	}
	
	/**
	 * Consulta una lista con todos los parkings cercanos dadas unas coordenadas y un radio
	 * @param coordenadas dato de la clase Coordenadas, representa las coordenadas de las que se desea saber los parkings cercanos
	 * @param radio dato double que representa la máxima distancia para que un parking sea cercano
	 * @return una lista con los parkings cercanos
	 */
	private ArrayList<CityBikeParkingPoint> parkingsCercanos(Coordenadas coordenadas, double radio){
		double distancia;
		ArrayList<CityBikeParkingPoint> listaConCercanos = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaParkings) {
			distancia = coordenadas.calcularDistancia(parking.getLatitudGD(), parking.getLongitudGD());
			if (distancia <= radio) listaConCercanos.add(parking);
		}
		return listaConCercanos;
	} 
	
	/**
	 * Permite obtener puntos de aparcamiento de bicis cercanos a una coordenada GPS en GMS
	 * Dentro de un radio dado en metros donde se puede dejar una bici, es decir, su anclaje tiene el color rojo
	 * @param latitud una cadena de carácteres que representa una latitud en GMS, debe cumplir con el formato coordenadas GMS
	 * @param longitud una cadena de carácteres que representa una longitud en GMS, debe cumplir con el formato coordenadas GMS
	 * @param radio cualquier valor double, debe ser mayor o igual que 0
	 * @return una lista que garantiza parkings dentro del radio con algún anclaje en rojo
	 * @throws IllegalArgumentException cuando {@code radio < 0}
	 */
	public ArrayList<CityBikeParkingPoint> parkingsCercanosConVacios(String latitud, String longitud, double radio) {
		Coordenadas otrasCoordenadas = new Coordenadas (latitud, longitud);
		if (radio < 0) throw new IllegalArgumentException("Llamada incorrecta: radio < 0");
		ArrayList<CityBikeParkingPoint> listaConCercanos = parkingsCercanos(otrasCoordenadas, radio);
		ArrayList<CityBikeParkingPoint> listaConCercanosConVacios = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaConCercanos) {
			if (parking.anclajesVacios() > 0) listaConCercanosConVacios.add(parking);
		}
		return listaConCercanosConVacios;
	}
	
	/**
	 * Permite obtener puntos de aparcamiento de bicis cercanos a una coordenada GPS en GD
	 * Dentro de un radio dado en metros donde se puede dejar una bici, es decir, su anclaje tiene el color rojo
	 * @param latitud un valor double que representa una latitud en GD, debe estar en [-90, 90]
	 * @param longitud un valor double que representa una longitud en GD, debe estar en [-180, 180]
	 * @param radio cualquier valor double, debe ser mayor o igual que 0
	 * @return una lista que garantiza parkings dentro del radio con algún anclaje en rojo
	 * @throws IllegalArgumentException cuando {@code radio < 0}
	 */
	public ArrayList<CityBikeParkingPoint> parkingsCercanosConVacios(double latitud, double longitud, double radio) {
		Coordenadas otrasCoordenadas = new Coordenadas (latitud, longitud);
		if (radio < 0) throw new IllegalArgumentException("Llamada incorrecta: radio < 0");
		ArrayList<CityBikeParkingPoint> listaConCercanos = parkingsCercanos(otrasCoordenadas, radio);
		ArrayList<CityBikeParkingPoint> listaConCercanosConVacios = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaConCercanos) {
			if (parking.anclajesVacios() > 0)listaConCercanosConVacios.add(parking);
		}
		return listaConCercanosConVacios;
	}
	
	/**
	 * Permite obtener puntos de aparcamiento de bicis cercanos a una coordenada GPS en GMS
	 * Dentro de un radio dado en metros donde se puede coger una bici, es decir, su anclaje tiene el color verde
	 * @param latitud una cadena de carácteres que representa una latitud en GMS, debe cumplir con el formato coordenadas GMS
	 * @param longituduna cadena de carácteres que representa una longitud en GMS, debe cumplir con el formato coordenadas GMS
	 * @param radio cualquier valor double, debe ser mayor o igual que 0
	 * @return una lista que garantiza parkings dentro del radio con algún anclaje en verde
	 * @throws NullPointerException cuando {@code latitud == null}
	 * @throws NullPointerException cuando {@code longitud == null}
	 * @throws IllegalArgumentException cuando {@code radio < 0}
	 */
	public ArrayList<CityBikeParkingPoint> parkingsCercanosConOcupados(String latitud, String longitud, double radio) {
		Coordenadas otrasCoordenadas = new Coordenadas (latitud, longitud);
		if (latitud == null) throw new NullPointerException("Llamada incorrecta: latitud == null");
		if (longitud == null) throw new NullPointerException("Llamada incorrecta: longitud == null");
		if (radio < 0) throw new IllegalArgumentException("Llamada incorrecta: radio < 0");
		ArrayList<CityBikeParkingPoint> listaConCercanos = parkingsCercanos(otrasCoordenadas, radio);
		ArrayList<CityBikeParkingPoint> listaConCercanosConOcupados = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaConCercanos) {
			if (parking.anclajesOcupados() > 0)listaConCercanosConOcupados.add(parking);
		}
		return listaConCercanosConOcupados;
	}
	
	/**
	 * Permite obtener puntos de aparcamiento de bicis cercanos a una coordenada GPS en GD
	 * Dentro de un radio dado en metros donde se puede coger una bici, es decir, su anclaje tiene el color verde
	 * @param latitud un valor double que representa una latitud en GD, debe estar en [-90, 90]
	 * @param longitud un valor double que representa una longitud en GD, debe estar en [-180, 180]
	 * @param radio cualquier valor double, debe ser mayor o igual que 0
	 * @return una lista que garantiza parkings dentro del radio con algún anclaje en verde
	 * @throws IllegalArgumentException cuando {@code radio < 0}
	 */
	public ArrayList<CityBikeParkingPoint> parkingsCercanosConOcupados(double latitud, double longitud, double radio) {
		Coordenadas otrasCoordenadas = new Coordenadas (latitud, longitud);
		if (radio < 0) throw new IllegalArgumentException("Llamada incorrecta: radio < 0");
		ArrayList<CityBikeParkingPoint> listaConCercanos = parkingsCercanos(otrasCoordenadas, radio);
		ArrayList<CityBikeParkingPoint> listaConCercanosConOcupados = new ArrayList<>();
		for (CityBikeParkingPoint parking : listaConCercanos) {
			if (parking.anclajesOcupados() > 0)listaConCercanosConOcupados.add(parking);
		}
		return listaConCercanosConOcupados;
	}
	
}