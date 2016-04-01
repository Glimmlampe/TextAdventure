package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JToolBar;
import javax.swing.JLabel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import charakter.PlayerCharacter;
import main.MainDialog;
import xmlParser.Entry;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class P_PanelFrame extends JPanel
{
	private MainDialog md;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public P_PanelFrame(Entry entry)
	{
		md = MainDialog.getInstance();
		setLayout(new BorderLayout(0, 0));

		JScrollPane entryScrollPane = new JScrollPane();
		add(entryScrollPane, BorderLayout.CENTER);

		JTextPane entryText = new JTextPane();
		entryText.setEditable(false);
		entryScrollPane.setViewportView(entryText);

		entryText.setText(entry.getText());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(4, 2, 0, 0));

		Vector<JButton> buttons = new Vector<JButton>();
		buttons.add(new JButton("Option 1"));
		buttons.add(new JButton("Option 2"));
		buttons.add(new JButton("Option 3"));
		buttons.add(new JButton("Option 4"));
		buttons.add(new JButton("Option 5"));
		buttons.add(new JButton("Option 6"));
		buttons.add(new JButton("Option 7"));
		buttons.add(new JButton("Option 8"));

		boolean fightWon = false;
		boolean magier = false;

		if (entry.getNumberEnemys() > 0)
		{
			if (entry.getEnemys().get(0).getName().equals("Magier"))
				magier = true;
			fightWon = md.getCharacter().fight(entry.getEnemys(), magier);
		}

		Iterator<JButton> it = buttons.iterator();
		for (java.util.Map.Entry<Integer, String> en : entry.getChoices().entrySet())
		{
			JButton jb = (JButton) it.next();
			jb.setText(en.getValue());
			buttonPanel.add(jb);

			jb.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					md.setState(en.getKey());// 1.set new State
					P_PanelFrame.this.setVisible(false);// 2. dispose panel
					md.nextStep();
				}
			});

			if (jb.getText().equals("Gewonnen") && !fightWon)
				jb.setEnabled(false);
			else if (jb.getText().equals("Tod") && fightWon)
				jb.setEnabled(false);

		}

		HashMap<String, Integer> mods = entry.getModifications();
		if (!mods.isEmpty())
		{
			PlayerCharacter c = md.getCharacter();
			for (java.util.Map.Entry<String, Integer> m : mods.entrySet())
			{
				switch (m.getKey())
				{
				case "bravery":
					c.setBravery(c.getBravery() + m.getValue());
					break;
				case "health":
					if (m.getValue() == 99)
						c.healthStatusChange(-1 * charakter.Character.rollD6(1, 0));
					else
						c.healthStatusChange(m.getValue());
					break;
				default:
					break;
				}
			}
		}

		HashMap<String, Integer> inventory = entry.getInventory();
		if (!inventory.isEmpty())
		{
			for (java.util.Map.Entry<String, Integer> inv : inventory.entrySet())
			{
				md.getCharacter().addItem(inv.getKey(), inv.getValue());
			}
		}

		System.out.println(md.getCharacter().getInventory());
	}
}
