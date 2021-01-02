package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ModeleTest {
	private static Modele modele;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		modele=new Modele("//C:\\\\Users/heynerr\\\\Documents\\\\0-Personnel\\\\Comptes\\\\Comptes.accdb");
	}
	
	@AfterAll
	static void cleanUp() throws SQLException {
		modele.close();
	}

	@Test
	void testChargeTabCodeLibelle() {
		Map <String, String> table = new HashMap <String, String>();
		modele.chargeTabCodeLibelle(table, "Code nature");
		assertEquals(8,table.size());
		assertEquals("Carte bleue", table.get("CB"));
	}

	@Test
	void testCalculSolde() {
		DecimalFormat df = new DecimalFormat("###.##");
		assertEquals("4468,26", df.format(modele.calculSolde("1")));
	}

}
