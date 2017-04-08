package environment;

import lifeform.LifeForm;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public interface Mover 
{
	public boolean move(LifeForm lifeForm, Cell[][] cells, int row, int column, int spaces);
}
