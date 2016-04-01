package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import charakter.Enemy;
import charakter.HitPoints;
import main.MainDialog;

public class CreateEntrys
{
	private MainDialog md;

	public CreateEntrys()
	{
		md = MainDialog.getInstance();
		File inputFile = new File(".\\Data\\Story.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try
		{
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Document doc = null;
		try
		{
			doc = builder.parse(inputFile);
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getDocumentElement();

		NodeList nList = doc.getElementsByTagName("state");

		System.out.println("Reading story: " + root.getNodeName());
		System.out.println("\tContains " + nList.getLength() + " states.");

		long stateID;
		HashMap<Integer, String> choices;
		String text;

		for (int tmp = 0; tmp < nList.getLength(); tmp++)
		{
			Node nNode = nList.item(tmp);
			// System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element eElement = (Element) nNode;

				stateID = Long.parseLong(eElement.getAttribute("stateID"));
				System.out.println("stateID: " + stateID);

				text = eElement.getElementsByTagName("text").item(0).getTextContent();
				// System.out.println("Text: " + text);

				choices = new HashMap<Integer, String>();

				Vector<Enemy> enemys = new Vector<Enemy>();
				NodeList enemyList = eElement.getElementsByTagName("enemy");
				for (int tmpOpt = 0; tmpOpt < enemyList.getLength(); tmpOpt++)
				{
					Element subElement = (Element) (enemyList.item(tmpOpt));
					String name = subElement.getAttribute("name");

					NodeList skills = subElement.getElementsByTagName("skill");
					int[] skillValues = new int[7];
					int i = 0;
					Element skillElement = (Element) (skills.item(i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("bravery"));
					System.out.print(skillValues[i]);
					skillElement = (Element) (skills.item(++i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("hitpoints"));
					System.out.print(" " + skillValues[i]);
					skillElement = (Element) (skills.item(++i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("attack"));
					System.out.print(" " + skillValues[i]);
					skillElement = (Element) (skills.item(++i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("damageDice"));
					System.out.print(" " + skillValues[i]);
					skillElement = (Element) (skills.item(++i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("damageBonus"));
					System.out.print(" " + skillValues[i]);
					skillElement = (Element) (skills.item(++i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("parry"));
					System.out.print(" " + skillValues[i]);
					skillElement = (Element) (skills.item(++i));
					skillValues[i] = Integer.parseInt(skillElement.getAttribute("armor"));
					System.out.println(" " + skillValues[i]);
					enemys.add(new Enemy(name, skillValues[0], new HitPoints(skillValues[1]), skillValues[2],
							skillValues[3], skillValues[4], skillValues[5], skillValues[6]));
				}
				
				HashMap<String, Integer> inventory = null;
				NodeList inventoryList = eElement.getElementsByTagName("inventory");
				for (int tmpItem = 0; tmpItem < inventoryList.getLength(); tmpItem++)
				{
					inventory = new HashMap<String, Integer>();
					Element subElement = (Element) (inventoryList.item(tmpItem));
//					String name = subElement.getAttribute("name");
					NodeList items = subElement.getElementsByTagName("item");
					for (int i = 0; i < items.getLength(); i++)
					{
						Element itemElement = (Element) items.item(i);
						System.out.print(itemElement.getAttribute("name"));
//						if(itemElement.getTextContent()!=null)
//							System.out.println(" " + itemElement.getTextContent());
//						else
//							System.out.println();
						if(!itemElement.getTextContent().isEmpty())
							inventory.put(itemElement.getAttribute("name"), Integer.parseInt(itemElement.getTextContent()));
						else
							inventory.put(itemElement.getAttribute("name"), 1);
					}
				}
				
				HashMap<String, Integer> modifications = new HashMap<String, Integer>();
				NodeList modifyList = eElement.getElementsByTagName("modify");
				for (int tmpMod = 0; tmpMod < modifyList.getLength(); tmpMod++)
				{
					Element subElement = (Element) (modifyList.item(tmpMod));
					String type = subElement.getAttribute("name");
					int value = Integer.parseInt(subElement.getElementsByTagName("value").item(0).getTextContent());
					modifications.put(type, value);
				}
				
				NodeList optionList = eElement.getElementsByTagName("target");
				for (int tmpOpt = 0; tmpOpt < optionList.getLength(); tmpOpt++)
				{
					Element subElement = (Element) (optionList.item(tmpOpt));
					int targetID = Integer.parseInt(subElement.getAttribute("targetID"));
					String optionText = subElement.getElementsByTagName("value").item(0).getTextContent();
					choices.put(targetID, optionText);
				}

				md.addStoryElement(new Entry(stateID, choices, text, enemys, modifications, inventory));
			}
		}
	}
}
