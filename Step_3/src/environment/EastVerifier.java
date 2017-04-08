package environment;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class EastVerifier implements DirectionVerifier 
{
	/**
	 * returns true if the direction is east
	 */
	@Override
	public boolean meetsCriteria(String direction) 
	{
		return direction.equalsIgnoreCase("East");
	}

}
