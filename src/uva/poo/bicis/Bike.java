package uva.poo.bicis;

import uva.poo.practica2.IResource;

/**
 * Tipo de dato abstracto que representa una bici, ya pueda ser de adulto, niño o eléctrica
 * Se garantiza que cada bici tiene un identificador único, así como unas caracterísicas generales, 
 * marca, modelo, peso, número de platos y número de piñones. Además cada tipo de bici implementará sus propias carácteristicas
 * dependiendo del tipo que esta sea.
 * @author izajime
 * @author asigarc
 *
 */
public abstract class Bike implements IResource {
	private String identificador;
	private String marca;
	private String modelo;
	private double peso;
	private int platos;
	private int piniones;

	/**
	 * Construye una nueva bici a partir de unas características dadas (marca, modelo, peso, platos, piñones).
	 * El número mínimo de platos y piñones lógicamente será 1, además de que el peso no podrá ser 0 ni menor que 0.
	 * Se creará un identificador único, una vez creado no se podrá cambiar.  
	 * @param marca marca de la bici, se admite cualquier marca.
	 * @param modelo modelo de la bici, se admite cualquier modelo.
	 * @param peso peso de la bici, debe ser extrictamente mayor que 0.
	 * @param platos número de platos de la bici, debe ser mayor que 0 (mínimo 1).
	 * @param piniones número de piñones de la bici, debe ser mayor que 0 (mínimo 1).
	 * @throws NullPointerException cuando {@code marca == null}
	 * @throws NullPointerException cuando {@code modelo == null}
	 * @throws IllegalArgumentException cuando {@code platos < 1}
	 * @throws IllegalArgumentException cuando {@code piniones < 1}
	 * @throws IllegalArgumentException cuando {@code peso <= 0}
	 */
	protected Bike(String marca, String modelo, double peso, int platos, int piniones) {
		if (marca == null) throw new NullPointerException("Llamada incorrecta: marca == null");
		if (modelo == null) throw new NullPointerException("Llamada incorrecta: modelo == null");
		if (platos < 1) throw new IllegalArgumentException("Llamada incorrecta: platos < 1");
		if (piniones < 1) throw new IllegalArgumentException("Llamada incorrecta: piniones < 1");
		if (peso <= 0) throw new IllegalArgumentException("Llamada incorrecta: peso <= 0");
		this.identificador = java.util.UUID.randomUUID().toString();
		this.marca = marca;
		this.modelo = modelo;
		this.peso = peso;
		this.platos = platos;
		this.piniones = piniones;
	}
	
	/**
	 * Consulta el identificador de la bici.
	 * @return un dato tipo String que representa el identificador de la bici.
	 */
	public String getIdentificador() {
		return identificador;
	}
	
	/**
	 * Consulta la marca de la bici.
	 * @return un dato tipo String que representa la marca de la bici.
	 */
	public String getMarca() {
		return marca;
	}
	
	/**
	 * Consulta el modelo de la bici.
	 * @return un dato tipo String que representa el modelo de la bici.
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * Consulta el peso de la bici.
	 * @return un valor double que representa el peso de la bici.
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Consulta el número de platos de la bici.
	 * @return un valor entero que representa el número de platos de la bici.
	 */
	public int getPlatos() {
		return platos;
	}
	
	/**
	 * Consulta el número de piñones de la bici.
	 * @return un valor entero que representa el número de piñones de la bici.
	 */
	public int getPiniones() {
		return piniones;
	}
	/**
	 * Consulta el precio de la bici dependiendo del tipo de esta, ya que caa una tendrá un factor de correción diferente:
	 * ChildBike tendrá un 15% de descuento, AdultBike no se verá afectada por el factor de correción y por último
	 * ElectricBike habrá que pagar un porcentaje a mayores equivalente al voltaje, es decir, si la bici es de 24V
	 * costará un 24% más si es de 36V un 36% más y si es de 48V un 48% más. Todo factor de correción se aplicará
	 * a la fianza establecida en el parking en el que está situada, y se asegura que si esta fianza cambia, los precios
	 * cambiarán proporcionalmente.
	 */
	public double getDepositToPay(double deposit) {
		return deposit*getFactorDeCorreccion();
	}
	
	/**
	 * Nos indica si una bici es de niño o no, se diferencia por la talla, ya que las tallas de niño son un valor
	 * numerico par entre el 12 y el 26.
	 * @return un valor lógico que indica si la bici es de niño: true (es de niño), false (no lo es).
	 */
	public boolean isChild() {
		return getCodificacion() >= 12;
	}
	
	/**
	 * Nos indica si una bici es de adulto o no, se diferencia por la talla, ya que las tallas de adulto son un valor
	 * String: "S", "M", "L", "XL".
	 * @return un valor lógico que indica si la bici es de adulto: true (es de adulto), false (no lo es).
	 */
	public boolean isAdult() {
		return getCodificacion() >= 1 && getCodificacion() <= 4;
	}
	
	/**
	 * Nos indica si una bici es eléctrica o no, se diferencia por la talla, ya que las tallas de bicis eléctricas
	 * son únicas.
	 * @return un valor lógico que indica si la bici es eléctrica: true (es eléctrica), false (no lo es).
	 */
	public boolean isElectric() {
		return getCodificacion() == 0;
	}

	/**
	 * Consulta el factor de correción de la bicicleta, devolverá valores diferentes para bicis diferentes
	 * para la bici de niño devuelve 0.85 (15% de descuento), para la bici de adulto devuelve 1 (neutro), y
	 * para la bici eléctrica devuelve dependiendo de su voltaje 1.24, 1.36, 1.48 (24%, 36%, 48% más respectivamente).
	 * @return un valor double correspondiente al factor de correción de la bici.
	 */
	public abstract double getFactorDeCorreccion();
	
	/**
	 * Consulta la talla de la bici
	 * @return un dato tipo String indicando que representa la talla de la bici.
	 */
	public abstract String getTalla();
	
	/**
	 * Consulta la codificación de la talla de la bici, al ser cada talla un tipo de dato necesitamos una
	 * codificación numerica para poder comparar con más facilidad
	 * @return un valor entero indicando la codificación de la talla de la bici.
	 */
	public abstract int getCodificacion();

	/**
	 * Compara el objeto de tipo bici con otro cualquiera para saber si son el mismo objeto,
	 * A parte de ver si son instancias de la misma clase comprobaremos también el identificador,
	 * el cual debe ser el mismo para que las bicis sean las mismas.
	 * @return un valor lógico indicando si es la misma bici: true (lo es), false (no lo es).
	 */
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Bike)) return false;
		if (getClass() != objeto.getClass()) return false;
		Bike otraBici = (Bike)objeto;
		if (this.identificador != otraBici.getIdentificador()) return false;
		return true;
	}
	
	/**
	 * Compara el tipo de bici con otra bici para saber si son el mismo tipo de bici (aduta, niño o eléctrica)
	 * @param otraBici un objeto de la clase Bike, el cual, puede ser cualquier tipo de bici
	 * @return un valor lógico que indica si son el mismo tipo de bici, true (si lo son) y false (si no lo son).
	 */
	public boolean mismoTipoBici(Bike otraBici) {
		if (isChild() && otraBici.isChild()) return true;
		if (isAdult() && otraBici.isAdult()) return true;
		if (isElectric() && otraBici.isElectric()) return true;
		return false;
	}
}
