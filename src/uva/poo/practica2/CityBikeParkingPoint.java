package uva.poo.practica2;

import fabricante.externo.tarjetas.TarjetaMonedero;
import uva.poo.bicis.Bike;
import uva.poo.packs.Pack;

/**
 * Tipo de dato que implementa un Parking a partir de anclajes de bicis
 * Los anclajes estarán numerados dependiendo de su número de orden
 * Con tolerancia a coordenadas dadas en GD y GMS, a parte de disponer de un identificador único
 * Aporta funcinalidades desde obtener su número de anclajes y sus coordendas hasta calcular
 * la distancia que hay desde él hasta otro punto GPS dado o prestar y devolver bicis
 * sin olvidarse también de que puede calcular el número de anclajes ocupados o saber 
 * que disponibilidad tiene un base de anclaje
 * @author izajime
 * @author asigarc
 */
public class CityBikeParkingPoint {
	private int nAnclajes;
	private Anclaje[] listaAnclajes;
	private Coordenadas coordenadas;
	private String id;
	private double fianza;

	/**
	 * Construye un nuevo parking a partir de su número de anclajes y sus coordenadas
	 * Se creará además un identificador único y la lista de anclajes todos ellos inicialmente
	 * sin bici y con la luz de la base en rojo. Además la fianza se inicializará a 0, lo que significa que 
	 * no está establecida y no se podrán usar varios métodos de la clase hasta que no se establezca
	 * @param nAnclajes un número de anclajes fijo para el parking, debe ser mayor que 0, sin este incluido
	 * @param latitud debe representarse en GD y estar dentro del rango de reprensetable [-90, 90]
	 * @param longitud debe representarse en GD y estar dentro del rango de representable [-180, 180]
	 * @throws IllegalArgumentException cuando {@code nAnclajes <= 0}
	 */
	public CityBikeParkingPoint(int nAnclajes, double latitud, double longitud) {
		if (nAnclajes<=0) throw new IllegalArgumentException("Llamada incorrecta: nAnclajes <= 0");
		this.nAnclajes = nAnclajes;
		coordenadas = new Coordenadas (latitud, longitud);
		id = java.util.UUID.randomUUID().toString();
		fianza = 0;
		listaAnclajes = new Anclaje[nAnclajes];
		for (int i = 0; i<nAnclajes;i++) {
			listaAnclajes[i] = new Anclaje();
		}
	}
	
	/**
	 * Construye un nuevo parking a partir de su número de anclajes y sus coordenadas
	 * Se creará además un identificador único y la lista de anclajes todos ellos inicialmente
	 * sin bici y con la luz de la base en rojo
	 * Las coordenadas se introducirán en GMS con el siguiente formato: x°y'z"D (sin espacios entre medias)
	 * Siendo x los grados válidos en [0,90] de enteros para latitud y en [0,180] de enteros para longitud
	 * z e y serán minutos y segundos válidos ambos para el intervalo [0,60) sin estar este último incluido
	 * Los grados se representarán con ° (ASCII = 248), los minutos con ' (ASCII = 39), y segundos con " (ASCII = 34)
	 * D representa la dirección, puede ser norte ("N"), sur ("S"), este ("E") y oeste ("O"), letra en mayúsucula
	 * Siempre deberá aparecer un símbolo que represente los grados los minutos y los segundos, cuando estos sean cero
	 * habrá que representarlo igualmente, y cuando el número sea un solo dígito, solo habrá que representar
	 * ese dígito, no junto a un cero.
	 * @param nAnclajes un número de anclajes fijo para el parking, debe ser mayor que 0, sin este incluido
	 * @param latitud un tipo de dato String que debe cumplir con el formato, representará la latitud del parking
	 * @param longitud un tipo de dato String que debe cumplir con el formato, representará la longitud del parking
	 */
	public CityBikeParkingPoint(int nAnclajes, String latitud, String longitud) {				
		this.nAnclajes = nAnclajes;
		coordenadas = new Coordenadas (latitud, longitud);
		id = java.util.UUID.randomUUID().toString();
		fianza = 0;
		listaAnclajes = new Anclaje[nAnclajes];
		for (int i = 0; i<nAnclajes;i++) {
			listaAnclajes[i] = new Anclaje();
		}
	}
	
	/**
	 * Constructor de copia que realiza una copia en profundidad
	 * Construye un nuevo parking igual (copia) al fuente
	 * Se garantiza que los anclajes de la fuente no están conectados con los de la copia
	 * @param fuente no puede ser null
	 * @throws NullPointerException cuando {@code fuente == null}
	 */

	public CityBikeParkingPoint(CityBikeParkingPoint fuente) {
    	if (fuente == null) throw new NullPointerException("Llamada incorrecta: fuente == null");
    	nAnclajes = fuente.getNAnclajes();  	
    	fuente.coordenadas = new Coordenadas (coordenadas.getLatitudGD(), coordenadas.getLongitudGD());
    	id = fuente.getId();
    	listaAnclajes = fuente.getListaAnclajes();
    	fianza = fuente.getFianza();
    }
	
	/**
	 * Consulta el número fijo de anclajes de nuestro parking
	 * @return cualquier valor entero mayor que 0, sin este incluido
	 */
	public int getNAnclajes() {
		return nAnclajes;
	}
	
	/**
	 * Consulta la fianza establecida en el parking, puede devolver 0 en caso de no haberse establecido una fianza
	 * @return un valor double que representa la fianza del parking
	 */
	public double getFianza() {
		return fianza;
	}
	
	/**
	 * Establece un nuevo valor a la fianza, esta fianza deberá ser extrictamente mayor que 0
	 * @param nuevaFianza un valor double que representa la fianza del parking
	 * @throws IllegalArgumentException cuando {@code fianza <= 0}
	 */
	public void setFianza(double nuevaFianza) {
		if (fianza <= 0) throw new IllegalArgumentException("Llamada incorrecta: nuevaFianza <= 0");
		fianza = nuevaFianza;
	}
	
	/**
	 * Consulta el valor de la latitud en GD donde se encuentra el parking
	 * @return un valor double que representa la latitud del parking
	 */
	public double getLatitudGD() {
		return coordenadas.getLatitudGD();
	}
	
	/**
	 * Consulta el valor de la longitud en GD donde se encuentra el parking
	 * @return un valor double que representa la longitud del parking
	 */
	public double getLongitudGD() {
		return coordenadas.getLatitudGD();
	}
	
	/**
	 * Consulta el valor de la latitud en GMS donde se encuentra el parking
	 * @return un valor double que representa la latitud del parking
	 */
	public String getLatitudGMS() {
		return coordenadas.getLatitudGMS();
	}
	
	/**
	 * Consulta el valor de la longitud en GMS donde se encuentra el parking
	 * @return un valor double que representa la longitud del parking
	 */
	public String getLongitudGMS() {
		return coordenadas.getLongitudGMS();
	}
	
	/**
	 * Compara el objeto de tipo parking con otro cualquiera para saber si son el mismo objeto,
	 * A parte de ver si son instancias de la misma clase comprobaremos también la latitud y la longitud
	 * ya que dos parkings no pueden tener las mismas coordenadas
	 * @return un valor lógico indicando si es el mismo parking: true (lo es), false (no lo es).
	 */
	@Override
	public boolean equals(Object objeto) {
		if (objeto == this) return true;
		if (!(objeto instanceof CityBikeParkingPoint)) return false;
		if (getClass() != objeto.getClass()) return false;
		CityBikeParkingPoint otroParking = (CityBikeParkingPoint)objeto;
		if (otroParking.coordenadas.getLatitudGD() != coordenadas.getLatitudGD()) return false;
		return otroParking.coordenadas.getLongitudGD() == coordenadas.getLongitudGD();
	}

	/**
	 * Consulta todos los anclajes que componen al parking
	 * @return se garantiza una lista de anclajes con los mismos estados de los anclajes, pero sin estar conectada con la fuente
	 */
	public Anclaje[] getListaAnclajes() {
		Anclaje [] listaResultado = new Anclaje[listaAnclajes.length];
		for (int i = 0; i<listaAnclajes.length; i++) {
			listaResultado[i] = new Anclaje (listaAnclajes[i]);
		}
		return listaResultado;
	}

	/**
	 * Consulta el identificador de nuestro parking
	 * @return un String, que se garantiza que es único
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Comprueba si una id dada es igual a la de nuestro parking
	 * @param otraId cualquier valor String
	 * @return un valor lógico dependiendo de si las ids son iguales (true) o no (false)
	 */
	public boolean esId(String otraId) {
		return otraId.equals(getId());
	}

	/**
	 * Consulta el número de anclajes ocupados por una bici, es decir, su base de anlcaje está de color verde
	 * @return un valor entero que no puede ser negativo
	 */
	public int anclajesOcupados() {
		int nAnclajesOcupados = 0;
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.estaOcupado()) {
				nAnclajesOcupados++;
			}
		}
		return nAnclajesOcupados;
	}
	
	/**
	 * Consulta el número de anclajes vacíos, es decir, su base de anclaje está de color rojo y sin bici
	 * @return un valor entero que no puede ser negativo
	 */
	public int anclajesVacios() {
		int nAnclajesVacios = 0;
		for (Anclaje anclaje : listaAnclajes) {
			if (!anclaje.estaOcupado()) {
				nAnclajesVacios++;
			}
		}
		return nAnclajesVacios;
	}

	/**
	 * Dado un anclaje, saber si este está vacío (luz roja), reservado (luz azul), ocupado (luz verde) o no operativa (luz apagada)
	 * @param idAnclaje será el propio número de orden del anclaje dentro de la lista de anclajes, debe estar comprendido entre 0 y el número de anclajes sin este último incluido (estamos respetando la posicion 0 del array) 
	 * @return un String con el estado del anclaje, puede tomar los valores ("Vacío", "Ocupado", "Reservado", "No Operativo"), 
	 * @throws IllegalArgumentException cuando {@code idAnclaje < 0}
	 * @throws IllegalArgumentException cuando {@code idAnclaje >= nAnclajes}
	 */
	public String estadoAnclaje(int idAnclaje) {
		if (idAnclaje<0) throw new IllegalArgumentException("Llamada incorrecta: idAnclaje < 0");
		if (idAnclaje >= nAnclajes) throw new IllegalArgumentException("Llamada incorrecta: idAnclaje >= nAnclajes");
		String colorLuz;
		colorLuz = listaAnclajes[idAnclaje].getLuz();
		switch (colorLuz) {
		case ("rojo"):
			return ("Vacío");
		case ("verde"):
			return ("Ocupado");
		case ("azul"):
			return ("Reservado");
		default:
			return ("No Operativo");
		}
	}
	
	/**
	 *  Determina si se puede prestar una bici dado un numero de anclaje
	 *  Para poder prestar la bici la luz de la base debe ser verde, y el saldo de la tarjeta mayor o igual 
	 *  que la fianza a pagar con el factor de correción ya aplicado
	 *  Si se determina que sí se puede prestar cambiará la luz a rojo, el valor de bici ahora será null
	 *  y descontará de tarjeta la fianza con el factor de correción aplicado.
	 *  @param numeroAnclaje un dato entero que representa el número de orden del anclaje del que se quiere la bici
	 *  @param tarjeta objeto de la clase tarjetaMonedero, trae con sigo un saldo el cúal se usará para pagar el prestamo de la bici, no puede apuntar a null
	 *	@throws NullPointerException cuando {@code tarjeta == null}
	 *	@throws IllegalStateException cuando {@code fianza == 0}
	 *	@throws IllegalArgumentException cuando {@code numeroAnclaje >= nAnclajes}
	 *	@throws IllegalArgumentException cuando {@code numeroAnclaje < 0}
	 *	@throws IllegalStateException cuando {@code tarjeta.getSaldoActual() < fianza con descuento aplicado}
	 *	@throws IllegalStateException cuando {bici no está disponible}
	 */
	public void prestarBici(int numeroAnclaje, TarjetaMonedero tarjeta) {
		if (tarjeta == null) throw new NullPointerException("Llamada incorrecta: tarjeta == null");
		if (fianza == 0) throw new IllegalStateException("Llamada incorrecta: fianza == 0");
		if (numeroAnclaje >= nAnclajes) throw new IllegalArgumentException("Llamada incorrecta: numeroAnclaje >= nAnclajes");
		if (numeroAnclaje < 0) throw new IllegalArgumentException("Llamada incorrecta: numeroAnclaje < 0");
		if (listaAnclajes[numeroAnclaje].estaOcupado()) {
			listaAnclajes[numeroAnclaje].sacarBici();
			if(tarjeta.getSaldoActual() < listaAnclajes[numeroAnclaje].getBici().getDepositToPay(fianza)) {
				throw new IllegalStateException("Estado incorrecta: saldoActual < fianza con descuento aplicado");
			}
			tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", listaAnclajes[numeroAnclaje].getBici().getDepositToPay(fianza));
			
		} else throw new IllegalStateException ("Llamada incorrecta: No se puede prestar la bici");
	}
	
	/**
	 *  Determina si se puede prestar una bici eléctrica
	 *  Para poder prestar la bici la luz de la base debe ser verde, y el saldo de la tarjeta mayor o igual 
	 *  que la fianza a pagar con el factor de correción ya aplicado
	 *  Si se determina que sí se puede prestar cambiará la luz a rojo, el valor de bici ahora será null
	 *  y descontará de tarjeta la fianza con el factor de correción aplicado.
	 *  @param tarjeta objeto de la clase tarjetaMonedero, trae con sigo un saldo el cúal se usará para pagar el prestamo de la bici, no puede apuntar a null
	 *	@throws NullPointerException cuando {@code tarjeta == null}
	 *	@throws IllegalStateException cuando {@code fianza == 0}
	 *	@throws IllegalStateException cuando {@code tarjeta.getSaldoActual() < fianza con descuento aplicado}
	 *	@throws IllegalStateException cuando {@code getNumeroElectricas() == 0}
	 */
	public void prestarBiciElectrica(TarjetaMonedero tarjeta) {
		if (tarjeta == null) throw new NullPointerException("Llamada incorrecta: tarjeta == null");
		if (fianza == 0) throw new IllegalStateException("Llamada incorrecta: fianza == 0");
		boolean prestada = false;
		if (getNumeroElectricas() == 0) throw new IllegalStateException("Llamada incorrecta: no hay bicis eléctricas en el parking");
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getLuz().equals("verde") && !prestada) {
				if (anclaje.getBici().isElectric()) {
					if(tarjeta.getSaldoActual() >= anclaje.getBici().getDepositToPay(fianza)) {
						anclaje.sacarBici();
						tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", anclaje.getBici().getDepositToPay(fianza));
					}
					else throw new IllegalStateException("Estado incorrecta: saldoActual < fianza con descuento aplicado");
					prestada = true;
				}
			}
		}
	}
	
	/**
	 *  Determina si se puede prestar una bici de adulto
	 *  Para poder prestar la bici la luz de la base debe ser verde, y el saldo de la tarjeta mayor o igual 
	 *  que la fianza a pagar con el factor de correción ya aplicado
	 *  Si se determina que sí se puede prestar cambiará la luz a rojo, el valor de bici ahora será null
	 *  y descontará de tarjeta la fianza con el factor de correción aplicado.
	 *  @param tarjeta objeto de la clase tarjetaMonedero, trae con sigo un saldo el cúal se usará para pagar el prestamo de la bici, no puede apuntar a null
	 *	@throws NullPointerException cuando {@code tarjeta == null}
	 *	@throws IllegalStateException cuando {@code fianza == 0}
	 *	@throws IllegalStateException cuando {@code tarjeta.getSaldoActual() < fianza con descuento aplicado}
	 *	@throws IllegalStateException cuando {@code getNumeroElectricas() == 0}
	 */
	public void prestarBiciAdulto(TarjetaMonedero tarjeta) {
		if (tarjeta == null) throw new NullPointerException("Llamada incorrecta: tarjeta == null");
		if (fianza == 0) throw new IllegalStateException("Llamada incorrecta: fianza == 0");
		boolean prestada = false;
		if (getNumeroAdultos() == 0) throw new IllegalStateException("Llamada incorrecta: no hay bicis de adulto en el parking");
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getLuz().equals("verde") && !prestada) {
				if (anclaje.getBici().isAdult()) {
					if(tarjeta.getSaldoActual() >= anclaje.getBici().getDepositToPay(fianza)) {
						anclaje.sacarBici();
						tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", anclaje.getBici().getDepositToPay(fianza));
					}
					else throw new IllegalStateException("Estado incorrecta: saldoActual < fianza con descuento aplicado");
					prestada = true;
				}
			}
		}
	}
	
	/**
	 *  Determina si se puede prestar una bici de niño
	 *  Para poder prestar la bici la luz de la base debe ser verde, y el saldo de la tarjeta mayor o igual 
	 *  que la fianza a pagar con el factor de correción ya aplicado
	 *  Si se determina que sí se puede prestar cambiará la luz a rojo, el valor de bici ahora será null
	 *  y descontará de tarjeta la fianza con el factor de correción aplicado.
	 *  @param tarjeta objeto de la clase tarjetaMonedero, trae con sigo un saldo el cúal se usará para pagar el prestamo de la bici, no puede apuntar a null
	 *	@throws NullPointerException cuando {@code tarjeta == null}
	 *	@throws IllegalStateException cuando {@code fianza == 0}
	 *	@throws IllegalStateException cuando {@code tarjeta.getSaldoActual() < fianza con descuento aplicado}
	 *	@throws IllegalStateException cuando {@code getNumeroElectricas() == 0}
	 */
	public void prestarBiciChild(TarjetaMonedero tarjeta) {
		if (tarjeta == null) throw new NullPointerException("Llamada incorrecta: tarjeta == null");
		if (fianza == 0) throw new IllegalStateException("Llamada incorrecta: fianza == 0");
		boolean prestada = false;
		if (getNumeroChild() == 0) throw new IllegalStateException("Llamada incorrecta: no hay bicis de niño en el parking");
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getLuz().equals("verde") && !prestada) {
				if (anclaje.getBici().isChild()) {
					if(tarjeta.getSaldoActual() >= anclaje.getBici().getDepositToPay(fianza)) {
						anclaje.sacarBici();
						tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", anclaje.getBici().getDepositToPay(fianza));
					}
					else throw new IllegalStateException("Estado incorrecta: saldoActual < fianza con descuento aplicado");
					prestada = true;
				}
			}
		}
	}
	
	/**
	 * Consulta el numero de bicis de niño que tiene el parking
	 * @return un valor tipo entero que no puede ser negativo
	 */
	public int getNumeroChild() {
		int contadorChild = 0;
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getBici().isChild()) contadorChild++;
		}
		return contadorChild;
	}
	
	/**
	 * Consulta el numero de bicis de adulto que tiene el parking
	 * @return un valor tipo entero que no puede ser negativo
	 */
	public int getNumeroAdultos() {
		int contadorAdultos = 0;
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getBici().isAdult()) contadorAdultos++;
		}
		return contadorAdultos;
	}
	
	/**
	 * Consulta el numero de bicis de adulto que tiene el parking
	 * @return un valor tipo entero que no puede ser negativo
	 */
	public int getNumeroElectricas() {
		int contadorElectricas = 0;
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getBici().isElectric()) contadorElectricas++;
		}
		return contadorElectricas;
	}
	
	/**
	 * Determina si se puede prestar un pack ya definido
	 * @param tarjeta objeto de la clase tarjetaMonedero, trae con sigo un saldo el cúal se usará para pagar el prestamo de la bici, no puede apuntar a null
	 * @param pack un objeto tipo Pack que se compondrá de bicis de cualquier tipo, siempre respetando los mínimos definidos de los packs
	 * @throws NullPointerException cuando {@code tarjeta == null}
	 * @throws NullPointerException cuando {@code pack == null}
	 * @throws IllegalStateException cuando {@code fianza == 0}
	 * @throws IllegalArgumentException cuando {Numero bicis de niño en el parking < numero de bicis de niño en el pack} 
	 * @throws IllegalArgumentException cuando {Numero bicis de adulto en el parking < numero de bicis de adulto en el pack} 
	 * @throws IllegalArgumentException cuando {Numero bicis eléctricas en el parking < numero de bicis eléctricas en el pack} 
	 * @throws IllegalStateException cuando {@code tarjeta.getSaldoActual() < fianza con descuento aplicado}
	 */
	public void prestarPack(TarjetaMonedero tarjeta, Pack pack) {
		if (tarjeta == null) throw new NullPointerException("Llamada incorrecta: tarjeta == null");
		if (pack == null) throw new NullPointerException("Llamada incorrecta: pack == null");
		if (fianza == 0) throw new IllegalStateException("Llamada incorrecta: fianza == 0");
		if (getNumeroChild() < pack.getNumeroChild()) throw new IllegalArgumentException("Llamada incorrecta: Bicis de niño insuficientes en el parking");
		if (getNumeroAdultos() < pack.getNumeroAdultos()) throw new IllegalArgumentException("Llamada incorrecta: Bicis de adulto insuficientes en el parking");
		if (getNumeroElectricas() < pack.getNumeroElectricas()) throw new IllegalArgumentException("Llamada incorrecta: Bicis eléctricas insuficientes en el parking");
		
		for (Bike biciPack : pack.getListaBicis()) {
			for (Anclaje anclaje : listaAnclajes) {
				if (biciPack.mismoTipoBici(anclaje.getBici())) {
					anclaje.sacarBici();
					if(tarjeta.getSaldoActual() >= pack.getDepositToPay(fianza)) {
						tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", pack.getDepositToPay(fianza));
					}else throw new IllegalStateException("Estado incorrecta: saldoActual < fianza con descuento aplicado");
					
				}
			}
		}
	}

	/**
	 * Determina si se puede devolver una bici
	 * Para poder devolver la bici la luz de la base debe ser roja
	 * Si se determina que sí se puede cambiará la luz a verde y volverá a añadir la fianza a la tarjeta
	 * además de añadir la bici en la primera posición que encuentre vacía
	 * @param bici  objeto de la calse Bike, representará la bici que se quiere devolver, no puede ser null
	 * @param tarjeta objeto de la clase tarjetaMonedero, recibirá la fianza pagada al devolver la bici, no puede apuntar a null
	 * @throws NullPointerException cuando {@code tarjeta == null}
	 * @throws NullPointerException cuando {@code bici == null}
	 * @throws IllegalStateException cuando {@code fianza == 0}
	 * @throws IllegalStateException cuando {No hay sitio en el parking}
	 */
	public void devolverBici(Bike bici, TarjetaMonedero tarjeta) {
		if (tarjeta == null) throw new NullPointerException("Llamada incorrecta: tarjeta == null");
		if (bici == null) throw new NullPointerException("Llamada incorrecta: bici == null");
		if (fianza == 0) throw new IllegalStateException ("Estado incorrecto: fianza == 0");
		boolean devuelta = false;
		for (Anclaje anclaje : listaAnclajes) {
			if (anclaje.getBici() == null && !devuelta) {
				anclaje.meterBici(bici);
				devuelta = true;
			}
		}
		if (!devuelta) throw new IllegalStateException ("Llamada incorrecta: Parking lleno, no se pudo devolver la bici");
	}
	
	/**
	 * Permite conocer la distancia en metros desde el parking hasta una coordenada GPS cualquiera dada en GMS
	 * Se garantiza que las coordenadas de nuestro parking son válidas
	 * @param otraLatitud una cadena de carácteres que representa una latitud en GMS, debe cumplir con el formato coordenadas GMS
	 * @param otraLongitud una cadena de carácteres que representa una longitud en GMS, debe cumplir con el formato coordenadas GMS
	 * @return cualquier valor double mayor que 0
	 */
	public double distancia(String otraLatitud, String otraLongitud) {
		Coordenadas otrasCoordenadas = new Coordenadas(otraLatitud, otraLongitud);
		return coordenadas.calcularDistancia(otrasCoordenadas.getLatitudGD(), otrasCoordenadas.getLongitudGD());
	}
	
	/**
	 * Permite conocer la distancia en metros desde el parking hasta una coordenada GPS cualquiera dada en GD
	 * Se garantiza que las coordenadas de nuestro parking son válidas
	 * @param otraLatitud un valor doule que representa una latitud en GD, debe estar dentro de [-90, 90]
	 * @param otraLongitud un valor doule que representa una longitud en GD, debe estar dentro de [-180, 180]
	 * @return cualquier valor double mayor que 0
	 */
	public double distancia(double otraLatitud, double otraLongitud) {
		Coordenadas otrasCoordenadas = new Coordenadas(otraLatitud, otraLongitud);
		return coordenadas.calcularDistancia(otrasCoordenadas.getLatitudGD(), otrasCoordenadas.getLongitudGD());
	}
	
	/**
	 * Permite conocer la distancia en metros desde el parking hasta otro Parking
	 * Se garantiza que las coordenadas de nuestro parking y del otro parking son válidas, ya que no se pueden construir parkings con coordenadas no válidas
	 * @param otroParking parking con el que se va a calcular la distancia, no puede ser null
	 * @return cualquier valor double mayor que 0
	 * @throws NullPointerException cuando {@code otroParking == null}
	 */
	public double distanciaConParking (CityBikeParkingPoint otroParking) {
		if (otroParking == null) throw new NullPointerException("Llamada incorrecta: otroParking == null");
		double otraLatitud = otroParking.coordenadas.getLatitudGD();
		double otraLongitud = otroParking.coordenadas.getLongitudGD();
		return coordenadas.calcularDistancia(otraLatitud, otraLongitud);
	}	
}