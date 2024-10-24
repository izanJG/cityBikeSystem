package uva.poo.practica2test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uva.poo.bicis.AdultBike;

/**
 * Batería de pruebas de la clase AdultBike
 * @author izajime
 * @author asigarc
 *
 */
public class AdultBikeTest {

AdultBike bici;
	
	@Before
	public void before() {
		bici = new AdultBike("Renault","Clio",78,8,8,"XL");
		assertNotNull(bici);
	}
	
	@Test
	public void testAdultBikeConTallaXL() {
		AdultBike otraBici  = new AdultBike("Renault","Clio",78,8,8,"XL");
		assertNotNull(otraBici);
		assertEquals(otraBici.getMarca(), "Renault");
		assertEquals(otraBici.getModelo(), "Clio");
		assertEquals(otraBici.getPeso(), 78, 0.000001);
		assertEquals(otraBici.getPlatos(), 8);
		assertEquals(otraBici.getPiniones(), 8);
		assertEquals(otraBici.getTalla(), "XL");
	}
	
	@Test
	public void testAdultBikeConTallaL() {
		AdultBike otraBici  = new AdultBike("Renault","Clio",78,8,8,"L");
		assertNotNull(otraBici);
		assertEquals(otraBici.getMarca(), "Renault");
		assertEquals(otraBici.getModelo(), "Clio");
		assertEquals(otraBici.getPeso(), 78, 0.000001);
		assertEquals(otraBici.getPlatos(), 8);
		assertEquals(otraBici.getPiniones(), 8);
		assertEquals(otraBici.getTalla(), "L");
	}
	
	@Test
	public void testAdultBikeConTallaM() {
		AdultBike otraBici  = new AdultBike("Renault","Clio",78,8,8,"M");
		assertNotNull(otraBici);
		assertEquals(otraBici.getMarca(), "Renault");
		assertEquals(otraBici.getModelo(), "Clio");
		assertEquals(otraBici.getPeso(), 78, 0.000001);
		assertEquals(otraBici.getPlatos(), 8);
		assertEquals(otraBici.getPiniones(), 8);
		assertEquals(otraBici.getTalla(), "M");
	}
	
	@Test
	public void testAdultBikeConTallaS() {
		AdultBike otraBici  = new AdultBike("Renault","Clio",78,8,8,"S");
		assertNotNull(otraBici);
		assertEquals(otraBici.getMarca(), "Renault");
		assertEquals(otraBici.getModelo(), "Clio");
		assertEquals(otraBici.getPeso(), 78, 0.000001);
		assertEquals(otraBici.getPlatos(), 8);
		assertEquals(otraBici.getPiniones(), 8);
		assertEquals(otraBici.getTalla(), "S");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAdultBikeConTallaInvalida() {
		@SuppressWarnings("unused")
		AdultBike otraBici = new AdultBike("Renault","Clio",78,8,8,"RE");	
	}
	
	@Test
	public void testGetTalla() {
		assertEquals(bici.getTalla(), "XL");
	}
	
	@Test
	public void testGetCodificacion() {
		assertEquals(bici.getCodificacion(), 4);
	}
	
	@Test
	public void testGetFactorDeCorrecion() {
		assertTrue(bici.getFactorDeCorreccion() == 1.0);
	}	
	
	@Test
	public void testToString() {
		assertEquals(bici.toString(), "Bici: " + bici.getIdentificador() + ", marca: " + "Renault" + ", modelo: " + "Clio" 
		+ ", peso: " + "78.0" + ", platos: " + "8" + ", piñones: " + "8" + ", talla: " + "XL");
	}
}
