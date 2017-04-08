package environment;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class WestVerifier implements DirectionVerifier 
{
	@Override
	public boolean meetsCriteria(String direction) 
	{
		return direction.equalsIgnoreCase("West");
	}
}
