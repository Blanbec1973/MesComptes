package model;

import java.util.HashMap;
import java.util.Map;

public class Modele {
	private SGBDManager sgbd;
	private Map <String,String> lstCodeNature = new HashMap<>();
	
	public Modele(String nomFichierBase) {
		sgbd = new SGBDManager(nomFichierBase);
		sgbd.connect();
		
		sgbd.chargeTabCodeLibelle(lstCodeNature, "Code nature");
	}

	public void close() {
		sgbd.disconnect();
	}
}
