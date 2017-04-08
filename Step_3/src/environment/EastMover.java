package environment;
import lifeform.LifeForm;

/**
 * 
 * @author Drew Rife and Jordan Long
 *
 */
public class EastMover implements Mover 
{
	/**
	 * Moves a lifeform east 
	 */
	@Override
	public boolean move(LifeForm lifeForm, Cell[][] cells, int row, int column, int spaces)
	{
		boolean moved = false;
		int numberOfColumns = Environment.getWorldInstance().getWorldCols();
		
		if((column+spaces) >= numberOfColumns)
		{
			spaces = (numberOfColumns - column) - 1;
		}
		
		for(int i = 0; i < spaces; i++)
		{
			if(cells[row][column+(spaces-i)].getLifeForm() == null)
			{
				cells[row][column].removeLifeForm();
				cells[row][column+(spaces-i)].addLifeForm(lifeForm);
				Environment.getWorldInstance().setSelectedLFRow(row);
				Environment.getWorldInstance().setSelectedLFCol(column+(spaces-i));
				moved = true;
			}
		}
		
		return moved;
	}

}
