package environment;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class SouthVerifier implements DirectionVerifier 
{
	/**
	 * returns true if the direction is south
	 */
	@Override
	public boolean meetsCriteria(String direction) 
	{
		return direction.equalsIgnoreCase("South");
	}
}
