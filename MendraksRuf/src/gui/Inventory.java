package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainDialog;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class Inventory extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Inventory()
	{
		setTitle("Inventar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			Vector<String> invent = new Vector<String>(); 
			for (java.util.Map.Entry<String, Integer> inv : MainDialog.getInstance().getCharacter().getInventory().entrySet())
			{
				String item = inv.getKey();
				if(inv.getKey().equals("Wertgegenstände"))
					item +=" für:";
				item += " " + inv.getValue();
				if(inv.getKey().equals("Wertgegenstände"))
					item +=" Dukaten";
				invent.add(item);
			}
			JList<String> listInventory = new JList<String>(invent);
			contentPanel.add(listInventory);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.setActionCommand("Close");
				buttonPane.add(closeButton);
				closeButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						Inventory.super.dispose();
					}
				});
			}
		}
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
