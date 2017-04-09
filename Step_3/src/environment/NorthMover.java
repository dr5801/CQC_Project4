package environment;
import lifeform.LifeForm;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class NorthMover implements Mover 
{
	/**
	 * moves the lifeform north
	 */
	@Override
	public boolean move(LifeForm lifeForm, Cell[][] cells, int row, int column, int spaces) 
	{
		boolean moved = false;
		
		if((row-spaces) < 0)
		{
			spaces = row;
		}
		
		int i = 0;
		while(!moved && (i < spaces))
		{
			if(cells[row-(spaces-i)][column].getLifeForm() == null)
			{
				cells[row][column].removeLifeForm();
				cells[row-(spaces-i)][column].addLifeForm(lifeForm);
				Environment.getWorldInstance().setSelectedLFRow(row-(spaces-i));
				Environment.getWorldInstance().setSelectedLFCol(column);
				moved = true;
			}
			i++;
		}
		
		return moved;
	}
}
