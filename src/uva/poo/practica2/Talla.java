package uva.poo.practica2;

/**
 * Clase tipo enum para almacenar las tallas de las bicis
 * A cada tipo de talla se le implementará una codificación especifica para poder diferenciar entre ellas.
 * Proporciona la posibilidad de obtener la codificación y la posibilidad de obtener la talla una vez
 * inicializado
 * @author izajime
 * @author asigarc
 */
public enum Talla {
	
	TALLA_UNICA(0, "Unica"), S (1, "S"), M(2, "M"), L(3, "L"), XL(4, "XL"),
	T12(12, "12"), T14(14, "14"), T16(16, "16"), T18(18, "18"), T20(20, "20"), T22(22, "22"), T24(24, "24"), T26(26, "26");
	
	private int codificacion;
	private String talla;
	
	private Talla (int codificacion, String talla) {
		this.codificacion = codificacion;
		this.talla = talla;
	}
	
	public int getCodificacion() {
		return codificacion;
	}
	
	public String getTalla() {
		return talla;
	}
}
