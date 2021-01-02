	package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parametres {
	private String nomFichierBase;
	 private static final Logger logger = LogManager.getLogger(Parametres.class);
	
	public Parametres(String nomFichier) {
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(nomFichier);

		try {
			prop.load(input);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} 	
		nomFichierBase = prop.getProperty("nomFichierBase");
	}
	
	public String getNomFichierBase() {return nomFichierBase;}
}
