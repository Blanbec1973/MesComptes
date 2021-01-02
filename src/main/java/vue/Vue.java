package vue;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import control.Controle;


public class Vue {
	private JFrame fen;
	private Controle controle;
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		new Vue(null);
	}
	
	public Vue(Controle controle)
		{
		this.controle=controle;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dimEcran = tk.getScreenSize();
		int largEcran=dimEcran.width;
		int hautEcran=dimEcran.height;
		int largFenetre = largEcran * 4 / 5;
		int hautFenetre = hautEcran * 4 / 5 ;
		fen = new JFrame("Mes comptes");
    	fen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fen.setSize(largFenetre, hautFenetre);
		fen.setLocationRelativeTo(null);
		fen.setResizable(false);
		JPanel pnlMain = (JPanel) fen.getContentPane();
		pnlMain.setLayout(new BorderLayout());
		//setBounds(50,100,largFenetre,hautFenetre); // coordonnées coin supérieur gauche + largeur + hauteur
		
		pnlMain.add(buildPnlSolde(), BorderLayout.NORTH);
		
		pnlMain.add(buildPnlSaisie(), BorderLayout.SOUTH);
		
//		JMenuBar barreMenus = new JMenuBar();
//		fen.setJMenuBar(barreMenus);
//		JMenu menu1 = new JMenu("menu1");
//		barreMenus.add(menu1);
//		JMenuItem menu1option1 = new JMenuItem("menu1option1");
//		menu1.add(menu1option1);
//		JMenuItem menu1option2 = new JMenuItem("menu1option2");
//		menu1.add(menu1option2);
//		JMenuItem menu1option3 = new JMenuItem("menu1option3");
//		menu1.add(menu1option3);
//		JMenu menu2 = new JMenu("menu2");
//		barreMenus.add(menu2);
//		JMenuItem menu2option1 = new JMenuItem("menu2option1");
//		menu2.add(menu2option1);
//		JMenuItem menu2option2 = new JMenuItem("menu2option2");
//		menu2.add(menu2option2);
//		JMenuItem menu2option3 = new JMenuItem("menu2option3");
//		menu2.add(menu2option3);
		
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	if (controle != null) controle.demandeClosing();
            }
        };
        fen.addWindowListener(exitListener);
		fen.setVisible(true);
	}
	private JPanel 	buildPnlSolde() {
		JPanel pnlSoldes = new JPanel();
		pnlSoldes.setLayout(new GridLayout(1,4));
		
		Date maDate = new Date();
		String tempString = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(maDate);
		JLabel lblMaDate = new JLabel("Date : "+tempString);
		pnlSoldes.add(lblMaDate);
		
		pnlSoldes.add(new JLabel("Solde PEC Banque : "+controle.demandeSolde("1")));
		pnlSoldes.add(new JLabel("Solde PEC Banque à date : "+controle.demandeSolde("0")));
		pnlSoldes.add(new JLabel("Solde PEC Banque à  fin de mois"));
		
		return pnlSoldes;
	}
	
	private JPanel buildPnlSaisie() {
		JPanel pnlSaisie = new JPanel();
		pnlSaisie.setLayout(new GridLayout(2,6));
		
		JLabel lblDate = new JLabel("Date");
		pnlSaisie.add(lblDate);
		lblDate.setHorizontalAlignment(JLabel.CENTER);
		JLabel lblLibelle = new JLabel("Libellé");
		pnlSaisie.add(lblLibelle);
		lblLibelle.setHorizontalAlignment(JLabel.CENTER);
		JLabel lblMode = new JLabel("Mode");
		pnlSaisie.add(lblMode);
		lblMode.setHorizontalAlignment(JLabel.CENTER);
		JLabel lblMontant = new JLabel("Montant");
		pnlSaisie.add(lblMontant);
		lblMontant.setHorizontalAlignment(JLabel.CENTER);
		JLabel lblFlagPec = new JLabel("Flag PEC");
		pnlSaisie.add(lblFlagPec);
		lblFlagPec.setHorizontalAlignment(JLabel.CENTER);
		pnlSaisie.add(new JLabel(""));
		
		JTextField saisieDate = new JTextField();
		pnlSaisie.add(saisieDate);
		JTextField saisieLibelle = new JTextField();
		pnlSaisie.add(saisieLibelle);
		JTextField saisieMode = new JTextField();
		pnlSaisie.add(saisieMode);
		JTextField saisieMontant = new JTextField();
		pnlSaisie.add(saisieMontant);
		JCheckBox saisieFlagPec = new JCheckBox();
		pnlSaisie.add(saisieFlagPec);
		
		JButton enregistrerBouton = new JButton("Enregistrer");
		pnlSaisie.add(enregistrerBouton);
        enregistrerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (controle != null) controle.demandeInsertionLigneDeCompte(saisieDate.getText(),
                		                               saisieLibelle.getText(),
                		                               saisieMode.getText(),
                		                               saisieMontant.getText(),
                		                               saisieFlagPec.isSelected());
            }
        });
		
		return pnlSaisie;
	}

	
}



