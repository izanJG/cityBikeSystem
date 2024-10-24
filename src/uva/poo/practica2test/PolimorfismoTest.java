package uva.poo.practica2test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uva.poo.bicis.AdultBike;
import uva.poo.bicis.Bike;
import uva.poo.bicis.ChildBike;
import uva.poo.bicis.ElectricBike;
import uva.poo.packs.Pack;
import uva.poo.packs.PackGrupos;
import uva.poo.packs.PackFamiliar;
import uva.poo.practica2.IResource;

/**
 * Batería de pruebas para probar el uso de los objetos polimórficamente desde el punto de vista más abstracto
 * @author izajime
 * @author asigarc
 *
 */
public class PolimorfismoTest {

	Bike[] listaBicis;
	IResource biciResourceDeAdulto;
	IResource biciResourceChild;
	IResource biciResourceElectrica;
	IResource packResourceGrupo;
	IResource packResourceFamiliar;
	Bike biciDeAdulto;
	Bike biciChild;
	Bike biciElectrica;
	Pack packGrupo;
	Pack packFamiliar;
	
	@Before
	public void before() {
		listaBicis = new Bike[11];
		for(int i = 0; i < 11; i++) {
			listaBicis[i]=new AdultBike("Renault","Clio",78,8,8,"XL");
		}
		listaBicis[2] = new ChildBike("Renault", "Clio", 78, 8, 8, 22);
		listaBicis [5] = new ChildBike("Renault", "Clio", 78, 8, 8, 22);
		listaBicis [7] = new ElectricBike("Renault", "Clio", 78, 8, 8, 25.8, 24, 56.7);
		
		biciResourceDeAdulto = new AdultBike("Renault", "Clio", 78, 8, 8, "XL");
		biciResourceChild = new ChildBike("Renault", "Clio", 78, 8, 8, 22);
		biciResourceElectrica = new ElectricBike("Renault", "Clio", 78, 8, 8, 25.8, 24, 56.7);
		packResourceGrupo = new PackGrupos(listaBicis);
		packResourceFamiliar = new PackFamiliar(listaBicis);
		biciDeAdulto = new AdultBike("Renault", "Clio", 78, 8, 8, "XL");
		biciChild = new ChildBike("Renault", "Clio", 78, 8, 8, 22);
		biciElectrica = new ElectricBike("Renault", "Clio", 78, 8, 8, 25.8, 24, 56.7);
		packGrupo = new PackGrupos(listaBicis);
		packFamiliar = new PackFamiliar(listaBicis);
		
		assertNotNull(biciResourceDeAdulto);
		assertNotNull(biciResourceChild);
		assertNotNull(biciResourceElectrica);
		assertNotNull(packResourceGrupo);
		assertNotNull(packResourceFamiliar);
		assertNotNull(biciDeAdulto);
		assertNotNull(biciChild);
		assertNotNull(biciElectrica);
		assertNotNull(packGrupo);
		assertNotNull(packFamiliar);
	}
	
	@Test
	public void testResourceChildBike() {
		assertEquals(biciResourceChild.getDepositToPay(10.0), 8.5, 0.00001);
	}
	
	@Test
	public void testResourceElectricBike() {
		assertEquals(biciResourceElectrica.getDepositToPay(10.0), 12.4, 0.00001);
	}
	
	@Test
	public void testResourceAdultBike() {
		assertEquals(biciResourceDeAdulto.getDepositToPay(10.0), 10.0, 0.00001);
	}
	
	@Test
	public void testResourcePackGrupo() {
		assertEquals(packResourceGrupo.getDepositToPay(10.0), 87.52, 0.00001);
	}
	
	@Test
	public void testResourcePackFamiliar() {
		assertEquals(packResourceFamiliar.getDepositToPay(10.0), 54.7, 0.00001);
	}
	
	@Test(expected = NullPointerException.class)
	public void testBikeConMarcaNull() {
		@SuppressWarnings("unused")
		Bike otraBici = new AdultBike(null ,"Clio", 78, 8, 8, "XL");	
	}
	
	@Test(expected = NullPointerException.class)
	public void testBikeConModeloNull() {
		@SuppressWarnings("unused")
		Bike otraBici = new AdultBike("Renault" ,null, 78, 8, 8, "XL");	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBikeConPlatosNoValidos() {
		@SuppressWarnings("unused")
		Bike otraBici = new AdultBike("Renault" ,"Clio", 78, 0, 8, "XL");	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBikeConPinionesNoValidos() {
		@SuppressWarnings("unused")
		Bike otraBici = new AdultBike("Renault" ,"Clio", 78, 8, 0, "XL");	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBikeConPesoNoValido() {
		@SuppressWarnings("unused")
		Bike otraBici = new AdultBike("Renault" ,"Clio", 0, 8, 8, "XL");	
	}
	
	@Test
	public void testGetMarca() {
		assertEquals(biciDeAdulto.getMarca(), "Renault");
	}
	
	@Test
	public void testGetModelo() {
		assertEquals(biciDeAdulto.getModelo(), "Clio");
	}
	
	@Test
	public void testGetPlatos() {
		assertEquals(biciDeAdulto.getPlatos(), 8);
	}
	
	@Test
	public void testGetPeso() {
		assertEquals(biciDeAdulto.getPeso(), 78.0, 0.00001);
	}
	
	@Test
	public void testGetPiniones() {
		assertEquals(biciDeAdulto.getPiniones(), 8);
	}
	
	@Test
	public void testGetDepositToPay() {
		assertEquals(biciResourceDeAdulto.getDepositToPay(10.0), 10.0, 0.00001);
	}
	
	@Test
	public void testIsChildConAdulto() {
		assertFalse(biciDeAdulto.isChild());
	}
	
	@Test
	public void testIsChildConChild(){
		assertTrue(biciChild.isChild());
	}
	
	@Test
	public void testIsAdultConElectric() {
		assertFalse(biciElectrica.isAdult());
	}
	
	@Test
	public void testIsAdultConAdulto() {
		assertTrue(biciDeAdulto.isAdult());
	}
	
	@Test
	public void testIsAdultConChild() {
		assertFalse(biciChild.isAdult());
	}
	
	@Test
	public void testIsElectricConAdulto() {
		assertFalse(biciDeAdulto.isElectric());
	}
	
	@Test
	public void testIsElectricConElectric() {
		assertTrue(biciElectrica.isElectric());
	}
	
	@Test
	public void testEqualsConElMismoObjeto() {
		assertTrue(biciDeAdulto.equals(biciDeAdulto));
	}
	
	@Test
	public void testEqualsConDiferenteClase() {
		assertFalse(biciDeAdulto.equals(packGrupo));
	}
	
	@Test
	public void testEqualsConDiferenteIdentificador() {
		AdultBike otraBiciDeAdulto = new AdultBike("Renault","Clio",78,8,8,"XL");  
		assertFalse(biciDeAdulto.equals(otraBiciDeAdulto));
	}
	
	@Test
	public void testMismoTipoBiciSinSerDelMismoTipoAdulto() {
		assertFalse(biciDeAdulto.mismoTipoBici(biciChild));
	}
	
	@Test
	public void testMismoTipoBiciSinSerDelMismoTipoChild() {
		assertFalse(biciChild.mismoTipoBici(biciDeAdulto));
	}
	
	@Test
	public void testMismoTipoBiciSinSerDelMismoTipoElectrica() {
		assertFalse(biciElectrica.mismoTipoBici(biciDeAdulto));
	}
	
	@Test
	public void testMismoTipoBiciConAdulto() {
		assertTrue(biciDeAdulto.mismoTipoBici(biciDeAdulto));
	}
	
	@Test
	public void testMismoTipoBiciConChild() {
		assertTrue(biciChild.mismoTipoBici(biciChild));
	}
	
	@Test
	public void testMismoTipoBiciConElectrica() {
		assertTrue(biciElectrica.mismoTipoBici(biciElectrica));
	}
	
	@Test(expected = NullPointerException.class)
	public void testPackConNull() {
		@SuppressWarnings("unused")
		PackFamiliar otroPack = new PackFamiliar(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPackConBicisRepetidas() {
		Bike[] otraLista = new Bike[2];
		otraLista[0] = biciDeAdulto;
		otraLista[1] = biciDeAdulto;
		@SuppressWarnings("unused")
		PackFamiliar otroPack = new PackFamiliar(otraLista);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPackConListaConNull() {
		Bike[] otraLista = new Bike[2];
		otraLista[0] = biciDeAdulto;
		otraLista[1] = null;
		@SuppressWarnings("unused")
		PackFamiliar otroPack = new PackFamiliar(otraLista);
	}
	
	@Test
	public void testEstaBici() {
		Bike otraBici = listaBicis[2];
		assertTrue(packFamiliar.estaBici(otraBici));
	}
	
	@Test
	public void testNoEstaBici() {
		Bike otraBici = new AdultBike("Renault", "Clio", 78, 8, 8, "XL");
		assertFalse(packFamiliar.estaBici(otraBici));
	}
	
	@Test
	public void testEqualsPackConMismoObjeto() {
		assertFalse(packFamiliar.equals(packFamiliar));
	}
	
	@Test
	public void testEqualsPackConDistintaLista() {
		Bike[] otraLista = new Bike[13]; 
		for(int i = 0; i < 13; i++) {
			otraLista[i]=new AdultBike("Renault","Clio",78,8,8,"XL");
		}
		PackGrupos otroPack = new PackGrupos(otraLista);
		assertTrue(packGrupo.equals(otroPack));
	}
	
	@Test
	public void testEqualsPackConDistintaClase() {
		assertFalse(packFamiliar.equals(biciChild));
	}
	
	@Test
	public void testToStringPack() {
		assertEquals(packFamiliar.toString(), "Este pack tiene 11 bicis");
	}
	
	@Test
	public void testAgregarBici() {
		packFamiliar.agregarBiciPack(biciChild);
		assertTrue(packFamiliar.getListaBicis().contains(biciChild));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarBiciConRepetida() {
		Bike otraBici = listaBicis[1];
		packFamiliar.agregarBiciPack(otraBici);
	}
	
	@Test
	public void testGetDepositToPayPack() {
		assertEquals(packFamiliar.getDepositToPay(10.0), 54.7, 0.00001);
	}
	
	
	@Test
	public void testNumBicis() {
		assertEquals(packFamiliar.numBicis(), 11);
	}
	
	@Test
	public void testGetNumeroElectricas() {
		assertEquals(packFamiliar.getNumeroElectricas(), 1);
	}

	@Test
	public void testGetNumeroAdultas() {
		assertEquals(packFamiliar.getNumeroAdultos(), 8);
	}
	
	@Test
	public void testGetNumeroChild() {
		assertEquals(packFamiliar.getNumeroChild(), 2);
	}
	
	@Test
	public void testGetSetListaBicis() {
		 ArrayList<Bike> otraListaBicis = new ArrayList<Bike>();
		for(int i = 0; i < 11; i++) {
			otraListaBicis.add(new AdultBike("Renault","Clio",78,8,8,"XL"));
		}
		
		packFamiliar.setListaBicis(otraListaBicis);
		assertEquals(otraListaBicis, packFamiliar.getListaBicis());
		
	}
	
}
