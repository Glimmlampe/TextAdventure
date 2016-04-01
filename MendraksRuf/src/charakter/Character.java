package charakter;

import java.util.Random;

public class Character
{
	private int bravery;
	private HitPoints hitPoints;
	private int attack;
	private int parry;
	private int armor;
	private int damageDice;
	private int damageBonus;
	
	/**
	 * 
	 * @param bravery
	 * @param hitPoints
	 * @param attack if attack is 1-3, then use 4!
	 * @param parry
	 * @param armor
	 * @param damageDice XW6
	 * @param damageBonus W6+X
	 */
	public Character(int bravery, HitPoints hitPoints, int attack, int parry, int armor, int damageDice, int damageBonus)
	{
		this.bravery = bravery;
		this.hitPoints = hitPoints;
		this.attack = attack;
		this.parry = parry;
		this.armor = armor;
		this.damageDice = damageDice;
		this.damageBonus = damageBonus;
	}
	
	/**
	 * 
	 * @return attack has hit?
	 */
	public boolean attack()
	{
		boolean isHit=false;
		
		if(rollD6(1, 0) < attack)
			isHit=true;
		
		return isHit;
	}
	
	/**
	 * 
	 * @return has parried
	 */
	public boolean parry()
	{
		boolean hasParried=false;
		
		if(rollD6(1, 0) < parry)
			hasParried=true;
		
		return hasParried;
	}
	
	/**
	 * 
	 * @return damage done
	 */
	public int rollDamage()
	{
		return rollD6(damageDice, damageBonus);
	}
	
	/**
	 * Rolls a D6
	 * 
	 * @param number how many dice (XD6)
	 * @param modifier how much to add (+X)
	 * @return result
	 */
	public static int rollD6(int number, int modifier)
	{
		int result=0;
		Random rand=new Random();
		for(int i=0;i<number;i++)
			result += rand.nextInt(6)+1;
		
		return result+modifier;
	}
	
	public void changeHealthStatus(int mod)
	{
		this.setHitPoints(this.getHitPoints() + mod);
	}

	public int getBravery()
	{
		return bravery;
	}
	
	public void setBravery(int bravery)
	{
		this.bravery = bravery;
	}

	public int getHitPoints()
	{
		return hitPoints.getHitPoints();
	}

	public void setHitPoints(int hitPoints)
	{
		this.hitPoints.setHitPoints(hitPoints);
	}

	public int getArmor()
	{
		return armor;
	}

	public String getDamageDiceResult()
	{
		return ""+damageDice+"D6+"+damageBonus;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getDamageDice()
	{
		return damageDice;
	}
	
	public int getDamageBonus()
	{
		return damageBonus;
	}
	
	@Override
	public String toString()
	{
		return "Mut: " + bravery + " HP: " + hitPoints + " Attack:" + attack + " Parry:" + parry + " Armor:" + armor
				+ " Damage: " + getDamageDiceResult();
	}
}
