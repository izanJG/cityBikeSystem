package uva.poo.practica2;

/**
 * Tipo de dato que representa una posición GPS dada una latitud y una longitud, admitiendo el tipo GD y el GMS.
 * Proporciona funcionalidades básicas como dar la latitud y longitud en cualquier tipo (GD y GMS) independientemente
 * del tipo con el que se haya creado.
 * Además también puede calcular distancia entre ella y otra posición GPS dada.
 * @author izajime
 * @author asigarc
 *
 */
public class Coordenadas {
	private double latitud;
	private double longitud;
	
	/**
	 * Construye unas coordedenadas a partir de su latitud y su longitud en GD
	 * @param latitud un dato tipo double entre [-90, 90]
	 * @param longitud un dato tipo double entre [-180 y 180]
	 * @throws IllegalArgumentException cuando {@code latitud < -90}
	 * @throws IllegalArgumentException cuando {@code latitud > 90}
	 * @throws IllegalArgumentException cuando {@code longitud < -180}
	 * @throws IllegalArgumentException cuando {@code longitud > 180}
	 */

	public Coordenadas(double latitud, double longitud) {
		if (latitud < -90) throw new IllegalArgumentException("Llamada incorrecta: latitud < -90");
		else if (latitud > 90) throw new IllegalArgumentException("Llamada incorrecta: latitud > 90");
		
		if (longitud < -180) throw new IllegalArgumentException("Llamada incorrecta: longitud < -180");
		else if (longitud > 180) throw new IllegalArgumentException("Llamada incorrecta: longitud > 180");
		
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	
	/**
	 * Construye unas coordenadas a partir de su latitud y su longitud en GMS
	 * @param latitud un dato tipo String que representa la latitud en GMS
	 * @param longitud un dato tipo String que representa la longitud en GMS
	 * @throws NullPointerException cuando {@code latitud == null}
	 * @throws NullPointerException cuando {@code longitud == null}
	 * @throws IllegalArgumentException cuando {@code !coordGMSValida(latitud, "latitud")}
	 * @throws IllegalArgumentException cuando {@code !coordGMSValida(longitud, "longitud")}
	 */

	public Coordenadas(String latitud, String longitud) {
		if (latitud == null) throw new NullPointerException("Llamada incorrecta: latitud == null");
		if (longitud == null) throw new NullPointerException("Llamada incorrecta: longitud == null");
		
		if (!coordGMSValida(latitud, "latitud")) {
			throw new IllegalArgumentException("Llamada incorrecta: latitudGMS no válida");
		}
		
		if (!coordGMSValida(longitud, "longitud")) {
			throw new IllegalArgumentException("Llamada incorrecta: longitudGMS no válida");
		}
		
		this.latitud = coordsGD(latitud);
		this.longitud = coordsGD(longitud);
	}
	
	
	/**
	 * Consulta el valor de la latitud en GD en la que se encuentra el parking
	 * @return un valor double dentro del rango [-90, 90]
	 */
	public double getLatitudGD() {
		return latitud;
	}

	/**
	 * Consulta el valor de la longitud en GD en la que se encuentra el parking
	 * @return un valor double dentro del rango [-180, 180]
	 */
	public double getLongitudGD() {
		return longitud;
	}
	
	/**
	 * Consulta el valor de la latitud en GMS en la que se enceuntra el parking
	 * @return un String con un valor válido de coordenadas GMS
	 */
	public String getLatitudGMS() {
		double coords = getLatitudGD();
		String transformacion = coordsGMS(coords, "latitud");
		return transformacion;
	}
	
	/**
	 * Consulta el valor de la longitud en GMS en la que se enceuntra el parking
	 * @return un String con un valor válido de coordenadas GMS
	 */
	public String getLongitudGMS() {
		double coords = getLongitudGD();
		String transformacion = coordsGMS(coords, "longitud");
		return transformacion;
	}
	
	/**
	 * Dado un string, nos devuelve si representa o no unas coordenadas en GMS reales 
	 * Dependiendo del tipo de coordenada (latitud, longitud) tendrá unas condiciones u otras
	 * @param coords un String cualquiera
	 * @param tipo un String que puede tomar como valores solo "latitud" y "longitud"
	 * @return un valor lógico para indicar si es válido (true) o no (false)
	 */
	public boolean coordGMSValida(String coords, String tipo) {
		String[] grados = coords.split("°");
		if (grados.length != 2 || grados[0].equals("")) return false;
		String[] minutos = grados[1].split(Character.toString(39));
		if (minutos.length != 2 || minutos[0].equals("")) return false;
		String[] segundos = minutos[1].split(Character.toString(34));
		if (segundos.length != 2 || segundos[0].equals("")) return false;
		
		for (int i = 0;i<grados[0].length(); i++) {
			if (grados[0].charAt(i) < 48 || grados[0].charAt(i)> 57) return false;
		}
		
		for (int i = 0;i<minutos[0].length(); i++) {
			if (minutos[0].charAt(i) < 48 || minutos[0].charAt(i)> 57) return false;
		}
		
		for (int i = 0;i<segundos[0].length(); i++) {
			if (segundos[0].charAt(i) < 48 || segundos[0].charAt(i)> 57) return false;
		}
		
		if (segundos[1].equals("N") || segundos[1].equals("S") || segundos[1].equals("O") || segundos[1].equals("E")) { 
		
			if (tipo.equals("latitud")) {
				if (Integer.parseInt(grados[0])>90) return false;
				if (segundos[1].equals("E") || segundos[1].equals("O")) return false;
			}
		
			if (tipo.equals("longitud")) {
				if (Integer.parseInt(grados[0])>180) return false;
				if(segundos[1].equals("N") || segundos[1].equals("S")) return false;
			}
		
			if (Integer.parseInt(minutos[0])>59) return false;
			if (Integer.parseInt(segundos[0])>59) return false;
		
			return true;
		} else return false;
	}
	
	/**
	 * Convierte coordenadas GMS en coordenadas GD
	 * @param coords un String indicando unas coordenadas en GMS, y se puede garantizar que son válidas
	 * @return un valor double, que se puede garantizar que será válido
	 */
	public double coordsGD(String coords) {
		String[] grados = coords.split("°");
		String[] minutos = grados[1].split(Character.toString(39));
		String[] segundos = minutos[1].split(Character.toString(34));
		double nGrados, nMinutos, nSegundos;
		double transformacion;
		
		nGrados = Double.parseDouble(grados[0]);
		nMinutos = Double.parseDouble(minutos[0]);
		nSegundos = Double.parseDouble(segundos[0]);
		
		transformacion = nGrados + (nMinutos/60) + (nSegundos/3600);
		
		if (segundos[1].equals("S") || segundos[1].equals("O")) {
			transformacion = transformacion * -1;
		}
		return transformacion;
	}
		
	/**
	 * Convierte coordenadas GD en coordenadas GMS
	 * @param coords valor double de unas coordenadas en GD
	 * @param tipo un String que puede tomar como valores solo "latitud" y "longitud"
	 * @return String cuyo valor representa las coordenadas GD introducidas en GMS, se puede garantizar que el valor será válido
	 */
	public String coordsGMS(double coords, String tipo) {
		boolean esNegativo = false;
		double parteDecimal, calculos;
		String grados, minutos, segundos, direccion, resultado;
		
		if (coords<0) {
			esNegativo = true;
			coords = coords * -1;
		}
		
		grados = String.valueOf((int)coords);
		parteDecimal = coords - (int)coords;
		calculos = parteDecimal*60;
		minutos = String.valueOf((int)calculos);
		parteDecimal = calculos - (int)calculos;
		calculos = parteDecimal*60;
		segundos = String.valueOf((int)calculos);
		
		if (esNegativo == true) {
			if (tipo == "latitud") direccion = "S";
			else direccion = "O";
		} else if (tipo == "latitud") direccion = "N";
			else direccion = "E";
		
		resultado = grados + "°" + minutos + Character.toString(39) + segundos + Character.toString(34) + direccion;
		return resultado;
	}
	
	/**
	 * Calcula la distancia en metros entre el parking y otro punto GPS dado en coordenadas en GD
	 * @param otraLatitud valor double que debe estar en [-90, 90], se debe garantizar que es un dato completamente válido
	 * @param otraLongitud valor double que debe estar en [-180, 180], se debe garantizar que es un dato completamente válido
	 * @return cualquier valor double mayor que 0
	 */
	public double calcularDistancia (double otraLatitud, double otraLongitud) {
		double otraLatitudR, otraLongitudR, latitudR, longitudR;
		double dlon, dlat, a, c, distancia;
		int RadioTierra = 6371*1000;// metros

		otraLatitudR = (otraLatitud * Math.PI) / 180;
		otraLongitudR = (otraLongitud * Math.PI) / 180;
		latitudR = (getLatitudGD() * Math.PI) / 180;
		longitudR = (getLongitudGD() * Math.PI) / 180;

		dlon = otraLongitudR - longitudR;
		dlat = otraLatitudR - latitudR;

		a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(latitudR) * Math.cos(otraLatitudR) * Math.pow(Math.sin(dlon / 2), 2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		distancia = RadioTierra * c;
		return distancia;
	}
	
	
	
	
}
