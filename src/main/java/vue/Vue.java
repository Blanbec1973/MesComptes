package vue;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import control.Controle;


public class Vue {
	private JFrame fen;
	private Controle controle;
	
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
		Container contenu = fen.getContentPane();
		//setBounds(50,100,largFenetre,hautFenetre); // coordonn�es coin sup�rieur gauche + largeur + hauteur
    	
		Date maDate = new Date();
		String tempString = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(maDate);
		JLabel lblMaDate = new JLabel("Date : "+tempString);
		contenu.add(lblMaDate);
		lblMaDate.setLocation(100,100);
		
		JMenuBar barreMenus = new JMenuBar();
		fen.setJMenuBar(barreMenus);
		JMenu menu1 = new JMenu("menu1");
		barreMenus.add(menu1);
		JMenuItem menu1option1 = new JMenuItem("menu1option1");
		menu1.add(menu1option1);
		JMenuItem menu1option2 = new JMenuItem("menu1option2");
		menu1.add(menu1option2);
		JMenuItem menu1option3 = new JMenuItem("menu1option3");
		menu1.add(menu1option3);
		JMenu menu2 = new JMenu("menu2");
		barreMenus.add(menu2);
		JMenuItem menu2option1 = new JMenuItem("menu2option1");
		menu2.add(menu2option1);
		JMenuItem menu2option2 = new JMenuItem("menu2option2");
		menu2.add(menu2option2);
		JMenuItem menu2option3 = new JMenuItem("menu2option3");
		menu2.add(menu2option3);
		
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	controle.demandeClosing();
            }
        };
        fen.addWindowListener(exitListener);
		fen.setVisible(true);
	}
	
	public void windowClosing() {
		controle.demandeClosing();
	}
	
	
}