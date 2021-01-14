package control;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	public static String formatMontant(float nombre) {
		StringBuilder str = new StringBuilder();
		if (nombre < 0) {
			str.append("- ");
		}
		else {
			if (nombre > 0) str.append("+ ");
		}
		
		DecimalFormat df = new DecimalFormat("###,###,##0.00 €");
		str.append(df.format(nombre));
		
		return str.toString();
	}
	
	public static String premierJourMoisSuivant() {
		LocalDate dateJour = LocalDate.now();
		int mois = dateJour.getMonthValue();
		int annee = dateJour.getYear();
		
		if (mois == 12) {
			mois = 1;
			annee+=1;
		}
		else mois+=1;
		
		LocalDate datePemierMoisSuivant = LocalDate.of(annee,mois,1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return formatter.format(datePemierMoisSuivant);
	}

}
