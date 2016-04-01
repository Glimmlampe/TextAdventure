package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import main.MainDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

	private JFrame frmMendraksRuf;
	private MainDialog md;
	
	private JLabel actHP;
	private JLabel actBravery;
	private JLabel actDex;
	private JLabel actArmor;
	private JLabel actAttack;
	private JLabel actDamage;
	
	/**
	 * Create the application.
	 */
	public MainWindow() {
		md = MainDialog.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 800, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMendraksRuf.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmNeuesSpiel = new JMenuItem("Neues Spiel");
		mnDatei.add(mntmNeuesSpiel);
		
		JMenuItem mntmSpeichern = new JMenuItem("Speichern");
		mnDatei.add(mntmSpeichern);
		
		JMenuItem mntmLaden = new JMenuItem("Laden");
		mnDatei.add(mntmLaden);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		mntmBeenden.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		JPanel statusPanel = new JPanel();
		frmMendraksRuf.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_statusPanel = new GridBagLayout();
		gbl_statusPanel.columnWidths = new int[]{85, 17, 18, 16, 22, 18, 45, 18, 75, 0, 0, 0, 0, 0, 0, 0};
		gbl_statusPanel.rowHeights = new int[]{23, 0};
		gbl_statusPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_statusPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		statusPanel.setLayout(gbl_statusPanel);
		
		JLabel lblHp = new JLabel("HP:");
		GridBagConstraints gbc_lblHp = new GridBagConstraints();
		gbc_lblHp.anchor = GridBagConstraints.WEST;
		gbc_lblHp.insets = new Insets(0, 0, 0, 5);
		gbc_lblHp.gridx = 1;
		gbc_lblHp.gridy = 0;
		statusPanel.add(lblHp, gbc_lblHp);
		
		actHP = new JLabel(""+md.getCharacter().getHitPoints());
		GridBagConstraints gbc_actHP = new GridBagConstraints();
		gbc_actHP.anchor = GridBagConstraints.WEST;
		gbc_actHP.insets = new Insets(0, 0, 0, 5);
		gbc_actHP.gridx = 2;
		gbc_actHP.gridy = 0;
		statusPanel.add(actHP, gbc_actHP);
		
		JLabel lblMaxTP = new JLabel("/"+md.getCharacter().getMaxTP());
		GridBagConstraints gbc_lblMaxTP = new GridBagConstraints();
		gbc_lblMaxTP.anchor = GridBagConstraints.WEST;
		gbc_lblMaxTP.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaxTP.gridx = 3;
		gbc_lblMaxTP.gridy = 0;
		statusPanel.add(lblMaxTP, gbc_lblMaxTP);
		
		JLabel lblMut = new JLabel("Mut:");
		GridBagConstraints gbc_lblMut = new GridBagConstraints();
		gbc_lblMut.anchor = GridBagConstraints.WEST;
		gbc_lblMut.insets = new Insets(0, 0, 0, 5);
		gbc_lblMut.gridx = 4;
		gbc_lblMut.gridy = 0;
		statusPanel.add(lblMut, gbc_lblMut);
		
		actBravery = new JLabel(""+md.getCharacter().getBravery());
		GridBagConstraints gbc_actBravery = new GridBagConstraints();
		gbc_actBravery.anchor = GridBagConstraints.WEST;
		gbc_actBravery.insets = new Insets(0, 0, 0, 5);
		gbc_actBravery.gridx = 5;
		gbc_actBravery.gridy = 0;
		statusPanel.add(actBravery, gbc_actBravery);
		
		JLabel lblGeschick = new JLabel("Geschick:");
		GridBagConstraints gbc_lblGeschick = new GridBagConstraints();
		gbc_lblGeschick.anchor = GridBagConstraints.WEST;
		gbc_lblGeschick.insets = new Insets(0, 0, 0, 5);
		gbc_lblGeschick.gridx = 6;
		gbc_lblGeschick.gridy = 0;
		statusPanel.add(lblGeschick, gbc_lblGeschick);
		
		actDex = new JLabel(""+md.getCharacter().getDexterity());
		GridBagConstraints gbc_actDex = new GridBagConstraints();
		gbc_actDex.anchor = GridBagConstraints.WEST;
		gbc_actDex.insets = new Insets(0, 0, 0, 5);
		gbc_actDex.gridx = 7;
		gbc_actDex.gridy = 0;
		statusPanel.add(actDex, gbc_actDex);
		
		JLabel lblRstung = new JLabel("R\u00FCstung:");
		GridBagConstraints gbc_lblRstung = new GridBagConstraints();
		gbc_lblRstung.insets = new Insets(0, 0, 0, 5);
		gbc_lblRstung.gridx = 8;
		gbc_lblRstung.gridy = 0;
		statusPanel.add(lblRstung, gbc_lblRstung);
		
		actArmor = new JLabel(""+md.getCharacter().getArmor());
		GridBagConstraints gbc_actArmor = new GridBagConstraints();
		gbc_actArmor.insets = new Insets(0, 0, 0, 5);
		gbc_actArmor.gridx = 9;
		gbc_actArmor.gridy = 0;
		statusPanel.add(actArmor, gbc_actArmor);
		
		JLabel lblAngriffswert = new JLabel("Angriffswert:");
		GridBagConstraints gbc_lblAngriffswert = new GridBagConstraints();
		gbc_lblAngriffswert.insets = new Insets(0, 0, 0, 5);
		gbc_lblAngriffswert.gridx = 10;
		gbc_lblAngriffswert.gridy = 0;
		statusPanel.add(lblAngriffswert, gbc_lblAngriffswert);
		
		actAttack = new JLabel("1-"+(md.getCharacter().getAttack()-1));
		GridBagConstraints gbc_actAttack = new GridBagConstraints();
		gbc_actAttack.insets = new Insets(0, 0, 0, 5);
		gbc_actAttack.gridx = 11;
		gbc_actAttack.gridy = 0;
		statusPanel.add(actAttack, gbc_actAttack);
		
		JLabel lblDamage = new JLabel("Schaden:");
		GridBagConstraints gbc_lblDamage = new GridBagConstraints();
		gbc_lblDamage.insets = new Insets(0, 0, 0, 5);
		gbc_lblDamage.gridx = 12;
		gbc_lblDamage.gridy = 0;
		statusPanel.add(lblDamage, gbc_lblDamage);
		
		actDamage = new JLabel(""+md.getCharacter().getDamageDice()+"W6+"+md.getCharacter().getDamageBonus());
		GridBagConstraints gbc_actDamage = new GridBagConstraints();
		gbc_actDamage.insets = new Insets(0, 0, 0, 5);
		gbc_actDamage.gridx = 13;
		gbc_actDamage.gridy = 0;
		statusPanel.add(actDamage, gbc_actDamage);
		
		JButton btnInventar = new JButton("Inventar");
		GridBagConstraints gbc_btnInventar = new GridBagConstraints();
		gbc_btnInventar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnInventar.gridx = 14;
		gbc_btnInventar.gridy = 0;
		statusPanel.add(btnInventar, gbc_btnInventar);
		btnInventar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new Inventory();				
			}
		});
	}

	public JFrame getFrame() {
		return frmMendraksRuf;
	}

	private void setFrame(JFrame frame) {
		this.frmMendraksRuf = frame;
		frmMendraksRuf.setTitle("Mendraks Ruf");
	}

	public void refreshStats()
	{
		actHP.setText(""+md.getCharacter().getHitPoints());
		actBravery.setText(""+md.getCharacter().getBravery());
		actDex.setText(""+md.getCharacter().getDexterity());
		actArmor.setText(""+md.getCharacter().getArmor());
		actAttack.setText("1-"+(md.getCharacter().getAttack()-1));
		actDamage.setText(""+md.getCharacter().getDamageDice()+"W6+"+md.getCharacter().getDamageBonus());
	}
}
