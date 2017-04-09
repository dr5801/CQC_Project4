package environment;
import lifeform.LifeForm;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class SouthMover implements Mover 
{
	/**
	 * moves a lifeform south
	 */
	@Override
	public boolean move(LifeForm lifeForm, Cell[][] cells, int row, int column, int spaces)
	{
		boolean moved = false;
		
		int numberOfRows = Environment.getWorldInstance().getWorldRows();
		if((row+spaces) >= numberOfRows)
		{
			spaces = (numberOfRows - row) - 1;
		}
		
		int i = 0;
		while(!moved && (i < spaces))
		{
			if(cells[row+(spaces-i)][column].getLifeForm() == null)
			{
				cells[row][column].removeLifeForm();
				cells[row+(spaces-i)][column].addLifeForm(lifeForm);
				Environment.getWorldInstance().setSelectedLFRow(row+(spaces-i));
				Environment.getWorldInstance().setSelectedLFCol(column);
				moved = true;
			}
			i++;
		}
		
		return moved;
	}
}
