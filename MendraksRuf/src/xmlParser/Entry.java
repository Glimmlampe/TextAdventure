package xmlParser;

import java.util.HashMap;
import java.util.Vector;

import charakter.Enemy;

public class Entry
{
	private long stateID;
	private HashMap<Integer, String> choices;
	private String text;
	private Vector<Enemy> enemys = null;
	private HashMap<String, Integer> modifications;
	private HashMap<String, Integer> inventory;
	
	public Entry(long iD, HashMap<Integer, String> choices, String text, Vector<Enemy> enemys, HashMap<String, Integer> modifications, HashMap<String, Integer> inventory)
	{
		super();
		setiD(iD);
		setChoices(choices);
		setText(text);
		this.enemys = enemys;
		this.modifications = modifications;
		this.inventory = inventory;
	}
	
	public HashMap<String, Integer> getInventory()
	{
		return inventory;
	}
	
	public int getNumberEnemys()
	{
		return enemys.size();
	}
	
	public Vector<Enemy> getEnemys()
	{
		return enemys;
	}

	public long getiD()
	{
		return stateID;
	}

	public void setiD(long iD)
	{
		this.stateID = iD;
	}

	public HashMap<Integer, String> getChoices()
	{
		return choices;
	}

	public void setChoices(HashMap<Integer, String> choices)
	{
		this.choices = choices;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public HashMap<String, Integer> getModifications()
	{
		return modifications;
	}
	
	@Override
	public String toString()
	{
		return "Entry [iD=" + stateID + ", choices=" + choices + ", text=" + text
				+ "]";
	}
}
