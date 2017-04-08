package environment;

import lifeform.LifeForm;

/**
 * Distance class
 * @author Drew Rife and Jordan Long
 *
 */
public class Distance 
{
	private int row1;
	private int row2;
	private int column1;
	private int column2;

	public Distance(int row1, int column1, int row2, int column2) 
	{
		this.row1 = row1;
		this.row2 = row2;
		this.column1 = column1;
		this.column2 = column2;
	}

	/**
	 * If the rows are the same return difference in columns * 5
	 * If the columns are the same return difference in rows * 5
	 * If none are equal use the pythagorean theorem to calculate the distance
	 * @return a calculated distance
	 */
	public int getCalculatedDistance()
	{
		int distance;
		
		if(row1 == row2)
		{
			distance = Math.abs(this.column1 - this.column2) * 5;
		}
		else if(column1 == column2)
		{
			distance = Math.abs(this.row1 - this.row2) * 5;
		}
		else
		{
			/* pythagorean theorem */
			double a = Math.pow((row1-row2)*5, 2);
			double b = Math.pow((column1 - column2)*5, 2);
			
			distance = (int)Math.sqrt(a+b);
		}
		
		return distance;
	}

}
