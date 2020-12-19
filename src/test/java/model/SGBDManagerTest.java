package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SGBDManagerTest {
	private static SGBDManager base;
	private static boolean isConnected;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		base = new SGBDManager("//C:\\\\Users/heynerr\\\\Documents\\\\0-Personnel\\\\Comptes\\\\Comptes.accdb");
		isConnected=base.connect();
	}
	
	@AfterAll
	static void cleanUp() {
		base.disconnect();
	}

	@Test
	void testSGBDManager() {
		assertNotNull(base.getConnection());
	}

	@Test
	void testConnect() {
		assertTrue(isConnected);
	}

	@Test
	void testSQLSelect() {
		//System.out.println("testSQLSelect");
		ResultSet rst = base.slectSQL("Select * from [Code nature]");
		int nbRow=0;
		String lastLibelle ="";
		
		try {
			//System.out.println("Type resulset : "+rst.getType());
			while (rst.next()) {
				nbRow+=1;
				lastLibelle=rst.getString(1);
			}
			assertEquals(8,nbRow);
			assertEquals("VS", lastLibelle);
		} catch (SQLException e) {
			e.printStackTrace();
			assertTrue(false);
		}	
	}

//	@Test
//	void testSQLUpdate() {
//		fail("Not yet implemented");
//	}

	@Test
	void testChargeTabCodeLibelle() {
		Map <String, String> table = new HashMap <String, String>();
		base.chargeTabCodeLibelle(table, "Code nature");
		assertEquals(8,table.size());
		assertEquals("Carte bleue", table.get("CB"));
	}

}
