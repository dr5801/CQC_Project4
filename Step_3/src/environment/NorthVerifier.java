package environment;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class NorthVerifier implements DirectionVerifier 
{
	/**
	 * returns true if the direciton is north
	 */
	@Override
	public boolean meetsCriteria(String direction) 
	{
		return direction.equalsIgnoreCase("North");
	}
}
