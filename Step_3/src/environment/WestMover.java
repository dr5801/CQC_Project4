package environment;
import lifeform.LifeForm;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class WestMover implements Mover 
{
	/**
	 * Moves a lifeform to the west
	 */
	@Override
	public boolean move(LifeForm lifeForm, Cell[][] cells, int row, int column, int spaces)
	{
		boolean moved = false;
		
		if((column-spaces) < 0)
		{
			spaces = column;
		}
		
		for(int i = 0; i < spaces; i++)
		{
			if(cells[row][column-(spaces-i)].getLifeForm() == null)
			{
				cells[row][column].removeLifeForm();
				cells[row][column-(spaces-i)].addLifeForm(lifeForm);
				Environment.getWorldInstance().setSelectedLFRow(row);
				Environment.getWorldInstance().setSelectedLFCol(column-(spaces-i));
				moved = true;
			}
		}
		
		return moved;
	}
}
