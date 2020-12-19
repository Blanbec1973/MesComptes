package control;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ParametresTest {
	private static Parametres parametres;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		parametres = new Parametres("config.properties");
	}

	@Test
	void testGetNomFichierBase() {
		String nomFichier = parametres.getNomFichierBase();
		assertNotNull(nomFichier);
	}
	
	@Test
	void testGetNomFichierBase2() {
		assertThrows(Exception.class, ()->new Parametres("config2properties"));
	}
}
