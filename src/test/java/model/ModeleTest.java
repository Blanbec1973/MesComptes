package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.Controle;

class ModeleTest {
	private static Modele modele;
	private static Controle controle=Mockito.mock(Controle.class);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		modele=new Modele(controle, "//C:\\\\Users/heynerr\\\\Documents\\\\0-Personnel\\\\Comptes\\\\Comptes.accdb");
	}
	
	@AfterAll
	static void cleanUp() throws SQLException {
		modele.close();
	}

	@Test
	void testChargeTabCodeLibelle() {
		Map <String, String> table = new HashMap <String, String>();
		modele.chargeTabCodeLibelle(table, "Code nature");
		assertEquals(9,table.size());
		assertEquals("Carte bleue", table.get("CB"));
	}

	@Test
	void testCalculSolde() {
		DecimalFormat df = new DecimalFormat("###.##");
	//	assertEquals("4468,26", df.format(modele.calculSoldePEC()));
	}

}
