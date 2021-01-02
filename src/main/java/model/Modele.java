package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Modele {
	private SGBDManager sgbd;
	private Map <String,String> lstCodeNature = new HashMap<>();
	private static final Logger LOGGER = Logger.getLogger("MesComptes");
	
	public Modele(String nomFichierBase) {
		sgbd = new SGBDManager(nomFichierBase);
		sgbd.connect();
		
		this.chargeTabCodeLibelle(lstCodeNature, "Code nature");	
		
	}

	public static void main(String[] args) {
		Modele m = new Modele("//C:\\\\Users/heynerr\\\\Documents\\\\0-Personnel\\\\Comptes\\\\Comptes.accdb");
		System.out.println("Solde 0 : "+m.calculSolde("0"));
		m.close();
	}
	
	public void close() {
		sgbd.disconnect();
	}
	
	public void chargeTabCodeLibelle(Map<String, String> lstCodeNature, String nomTable) {
		String sql = "Select * from ["+nomTable+"] order by 1;";
		ResultSet rst = sgbd.selectSQL(sql);
		try {
			while (rst.next()) {
				lstCodeNature.put(rst.getString(1), rst.getString(2));
			}
			rst.close();
		} catch (SQLException e) {LOGGER.severe(e.getMessage());}
	}
	
	public float calculSolde(String numSolde) {
		String sql="Select * from [Solde pris en compte Banque BPVF] where pec ='"+numSolde+"'";
		float solde=0;
		ResultSet rst = sgbd.selectSQL(sql);
		try {
			rst.next();
			solde = rst.getFloat(1);
			rst.close();
		} catch (SQLException e) {LOGGER.severe(e.getMessage()); return 0;}
		return solde;
	}
}
