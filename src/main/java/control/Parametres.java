	package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Parametres {
	private String nomFichierBase;
	 private static final Logger logger = Logger.getLogger(Parametres.class.getPackage().getName());
	
	public Parametres(String nomFichier) {
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(nomFichier);

		try {
			prop.load(input);
		} catch (IOException e) {
			logger.severe(e.getMessage());
		} 	
		nomFichierBase = prop.getProperty("nomFichierBase");
	}
	
	public String getNomFichierBase() {return nomFichierBase;}
}
