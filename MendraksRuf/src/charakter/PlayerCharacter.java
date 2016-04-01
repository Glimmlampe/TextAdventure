package charakter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class PlayerCharacter extends Character
{
	private int maxTP = 30;
	private int dexterity;
	private HashMap<String, Integer> inventory;

	public PlayerCharacter()
	{
		super(rollD6(1, 7), new HitPoints(30), 4, 3, 3, 1, 4);
		dexterity = rollD6(1, 7);
		inventory = new HashMap<String, Integer>();
	}

	public boolean dexTest()
	{
		int result = 0;
		for (int i = 0; i < 3; i++)
			result += Character.rollD6(1, 0);

		if (result < dexterity)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param enemys
	 *            the list of enemys
	 * @return true if player wins the fight
	 */
	public boolean fight(Vector<Enemy> enemys, boolean magier)
	{
		int round = 1;
		boolean fightWon = false;
		boolean pcFirst = false;

		/* Check if Player is first */
		if (getBravery() > enemys.get(0).getBravery())
		{
			pcFirst = true;
		} else if (getBravery() == enemys.get(0).getBravery())
		{
			int result1, result2;
			do
			{
				result1 = rollD6(1, 0);
				result2 = rollD6(1, 0);
			} while (result1 == result2);
			if (result1 > result2)
				pcFirst = true;
		}
		System.out.println("PC first: " + pcFirst);
		/* END */

		/* do fighting */
		/* Player first */
		do
		{
			System.out.println("\n\n\nRunde: " + round++);
			// Debug printout enemys
			System.out.println("Player: " + this.toString());
			int i = 0;
			for (Iterator<Enemy> iterator = enemys.iterator(); iterator.hasNext();)
			{
				Enemy enemy = (Enemy) iterator.next();
				System.out.println("" + ++i + ". " + enemy.toString());
			}

			if (pcFirst)
			{
				// Player attacks
				if (this.attack() && !enemys.get(0).parry())
				{
					int damage = this.rollDamage() - enemys.get(0).getArmor();
					if (damage < 0)
						damage = 0;

					enemys.get(0).changeHealthStatus(damage);
					System.out.println("You hit for " + damage + " damage!");
				} else
					System.out.println("Your sword cuts thin air...");
				while (!enemys.isEmpty() && enemys.get(0).getHitPoints() <= 0)
				{
					enemys.remove(0);
					System.out.println("You crushed your enemy!");
				}
				// all Enemys attack
				i = 0;
				if (!enemys.isEmpty())
				{
					if (magier && 0 == (round - 1) % 3)
					{
						int damage = rollD6(1, 0) - this.getArmor();
						if (damage < 0)
							damage = 0;
						this.changeHealthStatus(damage);
						System.out.println("UFF... Feuerball für " + damage + " Schaden!");
					}
					for (Iterator<Enemy> iterator = enemys.iterator(); iterator.hasNext();)
					{
						Enemy enemy = (Enemy) iterator.next();
						if (enemy.attack() && !this.parry())
						{
							int damage = enemy.rollDamage() - this.getArmor();
							if (damage < 0)
								damage = 0;

							this.changeHealthStatus(damage);
							System.out.println(enemy.getName() + i++ + ". hits you for " + damage + " damage!");
						} else
							System.out.println("You dodge the " + enemy.getName() + "'s attack!");
					}
				}
			} else
			{
				// all Enemys attack
				i = 0;
				if (!enemys.isEmpty())
				{
					if (magier && 0 == (round - 1) % 3)
					{
						int damage = rollD6(1, 0) - this.getArmor();
						if (damage < 0)
							damage = 0;
						this.changeHealthStatus(damage);
						System.out.println("UFF... Feuerball für " + damage + " Schaden!");
					}
					for (Iterator<Enemy> iterator = enemys.iterator(); iterator.hasNext();)
					{
						Enemy enemy = (Enemy) iterator.next();
						if (enemy.attack() && !this.parry())
						{
							int damage = enemy.rollDamage() - this.getArmor();
							if (damage < 0)
								damage = 0;

							this.changeHealthStatus(damage);
							System.out.println(enemy.getName() + i++ + ". hits you for " + damage + " damage!");
						} else
							System.out.println("You dodge the " + enemy.getName() + "'s attack!");
					}
				}

				// Player attacks
				if (this.attack() && !enemys.get(0).parry())
				{
					int damage = this.rollDamage() - enemys.get(0).getArmor();
					if (damage < 0)
						damage = 0;

					enemys.get(0).changeHealthStatus(damage);
					System.out.println("You hit for " + damage + " damage!");
				} else
					System.out.println("Your sword cuts thin air...");
				while (!enemys.isEmpty() && enemys.get(0).getHitPoints() <= 0)
				{
					enemys.remove(0);
					System.out.println("You smashed your enemy!");
				}
			}
			// try
			// {
			// Thread.sleep(1000);
			// } catch (InterruptedException e)
			// {
			// e.printStackTrace();
			// }
		} while (!enemys.isEmpty() && this.getHitPoints() > 0);
		/* END Player first END */

		System.out.println("\n\nResult:\nPlayer: " + this.toString());
		int i = 0;
		for (Iterator<Enemy> iterator = enemys.iterator(); iterator.hasNext();)
		{
			Enemy enemy = (Enemy) iterator.next();
			System.out.println("" + ++i + ". " + enemy.toString());
		}

		if (this.getHitPoints() > 0)
			fightWon = true;

		return fightWon;
	}

	public int getDexterity()
	{
		return dexterity;
	}

	public int getMaxTP()
	{
		return maxTP;
	}

	public HashMap<String, Integer> getInventory()
	{
		return inventory;
	}

	public void addItem(String key, Integer value)
	{
		if(containsItem(key))
		{
			int actValue = inventory.get(key);
			actValue+=value;
			inventory.replace(key, actValue);
		}else
			inventory.put(key, value);
	}

	public boolean containsItem(String key)
	{
		return inventory.containsKey(key);
	}
	
	public void removeItem(String item)
	{
		inventory.remove(item);
	}

	public void removeItem(int item)
	{
		inventory.remove(item);
	}

	public void healthStatusChange(int hitpoints)
	{
		if (getHitPoints() + hitpoints < maxTP)
			super.setHitPoints(getHitPoints() + hitpoints);
		else
			super.setHitPoints(maxTP);
	}

	@Override
	public String toString()
	{
		String toString = super.toString();
		toString += " Geschicklichkeit: " + dexterity;
//		for (Iterator<String> iterator = inventory.iterator(); iterator.hasNext();)
//			toString += (String) iterator.next();

		return toString;
	}

	public static void main(String[] args)
	{
		System.out.println("Test fight!");

		PlayerCharacter pc = new PlayerCharacter();
		pc.setHitPoints(99);

		boolean magier = false;
		Vector<Enemy> enemys = new Vector<Enemy>();

		/* Test Insektenkrieger */
		// int bravery = 11, attack = 4, damageDice = 1, damageBonus = 2, parry = 3, armor = 2;
		// enemys.add(new Enemy(bravery, new HitPoints(9), attack, damageDice, damageBonus, parry, armor));
		// enemys.add(new Enemy(bravery, new HitPoints(9), attack, damageDice, damageBonus, parry, armor));
		// enemys.add(new Enemy(bravery, new HitPoints(10), attack, damageDice, damageBonus, parry, armor));
		// enemys.add(new Enemy(bravery, new HitPoints(11), attack, damageDice, damageBonus, parry, armor));

		/* Test Gottheit */
		// HitPoints hp = new HitPoints(20);
		// enemys.add(new Enemy(9, hp, 5, 1, 2, 3, 4));
		// enemys.add(new Enemy(9, hp, 5, 1, 4, 0, 99));

		/* Test Mendrak */
		// mendrak = true;
		// HitPoints hp = new HitPoints(30);
		// enemys.add(new Enemy(13, hp, 5, 1, 2, 4, 3));
		// enemys.add(new Enemy(13, hp, 4, 1, 2, 0, 99));

		if (pc.fight(enemys, magier))
			System.out.println("Player won!");
		else
			System.out.println("Player dies!");

		System.out.println("Player: " + pc.toString());
		int i = 0;
		for (Iterator<Enemy> iterator = enemys.iterator(); iterator.hasNext();)
		{
			Enemy enemy = (Enemy) iterator.next();
			System.out.println("Enemy" + i++ + enemy.toString());
		}
	}
}
