package uva.poo.practica2test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uva.poo.bicis.AdultBike;
import uva.poo.bicis.Bike;
import uva.poo.packs.PackGrupos;

/**
 * Batería de pruebas de la clase PackGrupos
 * @author izajime
 * @author asigarc
 *
 */
public class PackGruposTest {
	
	Bike[] listaBicis;
	PackGrupos pack;
	
	@Before
	public void before() {
		listaBicis = new Bike[11];
		for(int i = 0; i < 11; i++) {
			listaBicis[i]=new AdultBike("Renault","Clio",78,8,8,"XL");
		}
		pack = new PackGrupos(listaBicis);
	}

	@Test
	public void testPackGrupos() {
		for (Bike bici : listaBicis) {
			assertTrue(pack.getListaBicis().contains(bici));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPackGruposConBicisInsuficientes() {
		Bike[] otralistaBicis = new Bike[9];
		for(int i = 0; i < 9; i++) {
			otralistaBicis[i]=new AdultBike("Renault","Clio",78,8,8,"XL");
		}
		@SuppressWarnings("unused")
		PackGrupos otroPack = new PackGrupos (otralistaBicis);
	}
	
	@Test
	public void testEliminarBiciPack() {
		String buscarBici = listaBicis[2].getIdentificador();
		pack.eliminarBiciPack(buscarBici);
		assertTrue(!pack.getListaBicis().contains(listaBicis[2]));
	}
	
	@Test(expected = NullPointerException.class)
	public void testEliminarBiciPackConIdentificadorNulo() {
		pack.eliminarBiciPack(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarBiciPackSinBiciEnPack() {
		pack.eliminarBiciPack("12345");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarBiciPackMenorQueDiez() {
		String buscarBici1 = listaBicis[2].getIdentificador();
		String buscarBici2 = listaBicis[5].getIdentificador();
		pack.eliminarBiciPack(buscarBici1);
		pack.eliminarBiciPack(buscarBici2);
	}
	
	@Test
	public void testGetFactorDeCorreccion() {
		assertTrue(pack.getFactorDeCorreccion() == 0.8);
	}

}
