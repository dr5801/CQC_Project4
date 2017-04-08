package environment;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public interface DirectionVerifier 
{
	/**
	 * this method must check which direction the lifeform needs to move
	 * @param direction
	 * @return true if the direction is matched; false otherwise
	 */
	boolean meetsCriteria(String direction);
}
