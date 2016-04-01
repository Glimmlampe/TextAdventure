package charakter;

public class Enemy extends Character
{
	private String name;
	
	public Enemy(String name, int bravery, HitPoints hitPoints, int attack, int damageDice, int damageBonus, int parry,
			int armor)
	{
		super(bravery, hitPoints, attack, parry, armor, damageDice, damageBonus);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getBravery()
	{
		return super.getBravery();
	}

	public int getHitPoints()
	{
		return super.getHitPoints();
	}
	public void setHitPoints(int hitPoints)
	{
		super.setHitPoints(hitPoints);
	}
	public int getArmor()
	{
		return super.getArmor();
	}
	
	@Override
	public String toString()
	{
		return getName() + " " + super.toString();
	}
}
