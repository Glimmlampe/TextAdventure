package main;

import java.awt.EventQueue;
import java.util.HashMap;

import charakter.PlayerCharacter;
import gui.MainWindow;
import gui.P_PanelFrame;
import xmlParser.CreateEntrys;
import xmlParser.Entry;

public class MainDialog
{
	private static MainDialog instance=null;
	public static MainDialog getInstance()
	{
		if(instance==null)
		{
			instance = new MainDialog();
		}
		return instance;
	}
	
	private HashMap<Long, Entry> storyElements;
	private long state;
	private MainWindow window;
	
	private PlayerCharacter character;
	
	private MainDialog()
	{
		setState(51);
		storyElements = new HashMap<Long, Entry>();
		
		character = new PlayerCharacter();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainDialog md = MainDialog.getInstance();
					new CreateEntrys();
					
					md.setWindow(new MainWindow());
					md.getWindow().getFrame().setVisible(true);
					
//**************************					
//					String introduction = "Willkommn zu 'Mendraks Ruf, dem Textadventure.\n\nDas Textadventure ist aus dem Buch 'Knaurs Buch der Rollenspiele' ISBN 3-426-03795-5\nVerantwortlich für PC Umsetzung ist Glimmlampe!\n\n\nUm das Spiel zu starten, Klicken Sie auf 'Weiter', es wird automatisch ein Charakter für Sie erstellt.";
//					HashMap<Integer, String> choice = new HashMap<Integer, String>();
//					choice.put(1, "Weiter");
//					Entry intro = new Entry(0, choice, introduction);
//
//					String s001 = "...irgend etwas hat dich geweckt! Eben noch hast du fest geschlafen, aber plötzlich war da ein vager Traum, ein fremder Gedanke, der dich aufgeschreckt hat. Noch etwas benommen setz du dich im Bett auf und versuchst dir über die Ursache klarzuwerden. Das Zimmer liegt ruhig und friedlich da, die Uhr zeigt drei Uhr morgens. Du spürst, wie sich dir jemand geistig mitteilen will, ein unerklärlicher, logisch nicht verständlicher Vorgang, aber du weißt sicher, daß dich jemand ruft. Du konzentrierst dich und merkst, daß diese geistigen Impulse von unten kommen, offenbar aus dem Keller.\nWie wirst du auf diese außergewöhnliche Kontaktaufnahme reagieren:";
//					choice = new HashMap<Integer, String>();
//					choice.put(9, "Ignorieren und weiterschlafen");
//					choice.put(17, "Den Impulsen folgen und nachforschen");
//					Entry en001 = new Entry(1, choice, s001);
//					
//					md.addStoryElement(intro);
//					md.addStoryElement(en001);
//					
//**************************

					md.nextStep();

				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void nextStep()
	{
		if(character.getHitPoints()<=0)
			state=999;
		Entry actEntry = storyElements.get(state);
		window.getFrame().add(new P_PanelFrame(actEntry));
		window.refreshStats();
	}

	public void addStoryElement(Entry entry)
	{
		storyElements.put(entry.getiD(), entry);
	}
	
	public HashMap<Long, Entry> getStoryElements()
	{
		return storyElements;
	}

	public void setWindow(MainWindow window)
	{
		this.window = window;
	}
	
	public MainWindow getWindow()
	{
		return window;
	}

	public long getState()
	{
		return state;
	}

	public void setState(long state)
	{
		this.state = state;
		System.out.println("State: " + state);
	}


	public PlayerCharacter getCharacter()
	{
		return character;
	}
}
