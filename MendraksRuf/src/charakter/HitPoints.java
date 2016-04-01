package charakter;

public class HitPoints
{
	private int hitPoints;
	
	public HitPoints(int hitpoints)
	{
		this.setHitPoints(hitpoints);
	}
	
	public String toString()
	{
		return "HitPoints: " + hitPoints;
	}

	public int getHitPoints()
	{
		return hitPoints;
	}

	public void setHitPoints(int hitPoints)
	{
		this.hitPoints = hitPoints;
	}
}
