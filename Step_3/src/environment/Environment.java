package environment;
import exceptions.EnvironmentException;
import weapon.Weapon;
import lifeform.LifeForm;

/**
 * An environment composed of a two-dimensional grid of cells.
 * @author David Reichard
 * @author Josh Varone - added Lab 5 Singleton functionality
 * @author Jeff Titanich - added getters for World dimensions in Lab 6
 */
public class Environment 
{
	
	private Cell[][] cells;								//array of cells in environment
	private int numberOfRows, numberOfColumns;			//total number of rows and columns
	private volatile static Environment environment;	//unique instance of Environment
	private int selectedLFRow = 0;						//Beginning LifeForm starts at Row[0]
	private int selectedLFCol = 0;						//Beginning LifeForm starts at Col[0]
	
	private final Edge[] movingMachine = 
		{
				new Edge(new NorthVerifier(), new NorthMover()),
				new Edge(new SouthVerifier(), new SouthMover()),
				new Edge(new WestVerifier(), new WestMover()),
				new Edge(new EastVerifier(), new EastMover())
		};
	
	/**
	 * Create an instance of an environment.
	 * @param rows Number of rows in environment.
	 * @param columns Number of columns in environment.
	 */
	private Environment(int rows, int columns)
	{
		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		cells = new Cell[rows][columns];
		
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				cells[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * Return the life form in cell at specified position.
	 * @param row Row position of cell.
	 * @param column Column position of cell.
	 * @return Life form contained in cell at position row,column.
	 */
	public LifeForm getLifeForm(int row, int column)
	{
		boolean validCoordinate = checkCoordinates(row, column);
		
		/* if the coordinate is valid return the lifeform at the coordinate position */
		if(validCoordinate)
		{
			return cells[row][column].getLifeForm();
		}
		else
		{
			/* invalid coordinate, return null */
			return null;
		}
	}

	/**
	 * Add a life form to the environment at the specified location.
	 * @param entity Entity being added to the environment.
	 * @param row Row position of cell in environment to add entity.
	 * @param column Column position of cell in environment to add entity.
	 * @return True if successful, false if otherwise.
	 */
	public boolean addLifeForm(LifeForm entity, int row, int column) 
	{
		boolean validCoordinate = checkCoordinates(row, column);
		
		/* if the coordinate is valid return true if the lifeform was added; false otherwise */
		if(validCoordinate)
		{
			return cells[row][column].addLifeForm(entity);
		}
		else
		{
			/* invalid coordinate, lifeform cannot be added */
			return false;
		}
	}
	
	/**
	 * Checks to see if the coordinates are valid (in bounds)
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean checkCoordinates(int row, int column) 
	{
		boolean validCoordinate = false;
		
		/* check lower bound */
		if(row >= 0 && column >= 0)
		{
			/* check upper bound */
			if(this.numberOfRows > numberOfRows && this.numberOfColumns > column)
			{
				validCoordinate = true;
			}
		}
		
		return validCoordinate;
	}

	/**
	 * Remove a life form from the environment at the specified location.
	 * @param row Row position of cell in environment to remove entity.
	 * @param column Column position of cell in environment to remove entity.
	 * @return True if successful, false if otherwise.
	 */
	public boolean removeLifeForm(int row, int column) 
	{
		boolean validCoordinate = checkCoordinates(row, column);
		
		/* if valid coordinate, remove life form */
		if(validCoordinate)
		{
			return cells[row][column].removeLifeForm();
		}
		else
		{
			/* not a vlid coordinate; return false */
			return false;
		}
	}
	
	/**
	 * If an instance of Environment does not already exist, create one.
	 * @return the instance of Environment.
	 * @author Josh Varone
	 */
	public static Environment getWorldInstance(int width, int height)
	{
		/* check at first if the environment doesn't exist */
		if(!environmentExists())
		{
			/* allow only one thread to enter at once */
			synchronized(Environment.class)
			{
				/* if the environment hasn't been created from a previous thread */
				if(!environmentExists())
				{
					environment = new Environment(width, height);
				}
			}
		}
		
		return environment;
	}
	
	/**
	 * The base getWorldInstance when rows and columns aren't specified.
	 * Gets defaulted to a 10 by 10 grid
	 * @return
	 */
	public static synchronized Environment getWorldInstance() 
	{
		return getWorldInstance(10, 10);
	}
	
	/**
	 * Checks to see that the environment exists
	 * @return true if environment exists; false otherwise
	 */
	public static boolean environmentExists()
	{
		boolean environmentExists = false;
		
		if(environment != null)
		{
			environmentExists = true;
		}
		
		return environmentExists;
	}

	
	
	/**
	 * Adds the specified weapon to the cell at (row, col).
	 * @return true if successfully added, false otherwise.
	 * @author Josh Varone
	 */
	public boolean addWeapon(Weapon weapon, int row, int col)
	{
		boolean validCoordinate = checkCoordinates(row, col);
		
		/* if valid position, add weapon to the cell */
		if(validCoordinate)
		{
			return cells[row][col].addWeapon(weapon);
		}
		else
		{
			/* not valid coordinate; return false */
			return false;
		}
	}
	
	/**
	 * Removes the weapon at the given position from the cell at (row, col).
	 * @return the removed weapon, or null if one was not removed.
	 * @author Josh Varone
	 */
	public Weapon removeWeapon(int position, int row, int col)
	{
		boolean validCoordinate = checkCoordinates(row, col);
		
		/* if valid position, remove weapon */
		if(validCoordinate)
		{
			return cells[row][col].removeWeapon(position);
		}
		else
		{
			/* not valid position, return null */
			return null;
		}
	}
	
	/**
	 * Gets the weapon at the specified position in the cell at (row, col).
	 * @return the weapon at the given position.
	 * @author Josh Varone
	 */
	public Weapon getWeapon(int position, int row, int col)
	{
		boolean validCoordinate = checkCoordinates(row, col);
		
		/* if valid coordinate, get the weapon at the position */
		if(validCoordinate)
		{
			return cells[row][col].getWeapon(position);
		}
		else
		{
			/* not valid coordinate, return null */
			return null;
		}
	}
	
	/**
	 * Calculates the distance between two given Cells within the environment.
	 * @return the distance between the two Cells
	 * @throws EnvironmentException if coordinates are out of bounds
	 * @author Josh Varone
	 */
	public int getDistance(LifeForm lifeForm1, LifeForm lifeForm2) throws EnvironmentException
	{
		/* get the positional coordinates of lifeform1 and lifeform2 */
		int[] lifeForm1Position = getLifeFormPosition(lifeForm1);
		int[] lifeForm2Position = getLifeFormPosition(lifeForm2);
		
		int lifeForm1Row = lifeForm1Position[0];
		int lifeForm1Col = lifeForm1Position[1];
		int lifeForm2Row = lifeForm2Position[0];
		int lifeForm2Col = lifeForm2Position[1];
		
		/* check lifeForm1 and lifeForm2 are in valid positions */
		if(checkCoordinates(lifeForm1Row, lifeForm1Col) && checkCoordinates(lifeForm2Row, lifeForm2Col))
		{
			Distance distance = new Distance(lifeForm1Row, lifeForm1Col, lifeForm2Row, lifeForm2Col);
			return distance.getCalculatedDistance();
		}
		else
		{
			/* one of the lifeForms are not in a valid position */
			throw new EnvironmentException();
		}
	}
	
	/**
	 * returns the positional coordinate of the lifeform within the grid
	 * @param lifeForm
	 * @return
	 */
	private int[] getLifeFormPosition(LifeForm lifeForm)
	{
		/* initialize array to invalid positional coordinates */
		int[] lifeFormPosition = {-1, -1};
		boolean lifeFormFound = false;
		
		int row = 0;
		while(!lifeFormFound && (row < this.numberOfRows))
		{
			int column = 0;
			while(!lifeFormFound && (column < this.numberOfColumns))
			{
				if(this.cells[row][column].getLifeForm() == lifeForm)
				{
					lifeFormPosition[0] = row;
					lifeFormPosition[1] = column;
					lifeFormFound = true;
				}
				
				column++;
			}
			
			row++;
		}
		
		return lifeFormPosition;
	}
	
	/**
	 * Makes the Environment null so that it can be made new for tests.
	 * @author Josh Varone
	 */
	public void clearEnvironment()
	{
		Environment.environment = null;
	}
	
	/**
	 * Moves the specified LifeForm maxSpeed spaces in its currentDirection,
	 * assuming no obstacles sit in the destination.
	 * @returns true if successfully moved, false otherwise
	 * @author Josh Varone
	 */
	public boolean move(LifeForm lifeForm, int spaces)
	{
		int[] lifeFormPosition = getLifeFormPosition(lifeForm);
		int row = lifeFormPosition[0];
		int column = lifeFormPosition[1];
		boolean didMove = false;
		
		if(checkCoordinates(row, column))
		{
			if(checkLifeExists(lifeForm))
			{
				if(validSpaces(spaces))
				{
					didMove = move(row, column, spaces);
				}
			}
		}

		return didMove;
	}
	
	/**
	 * @param lifeForm
	 * @return true if the entity exists; false otherwise
	 */
	private boolean checkLifeExists(LifeForm lifeForm) 
	{
		if(lifeForm != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @param spaces
	 * @return true if spaces is greater than 0; false otherwise
	 */
	private boolean validSpaces(int spaces) 
	{
		if(spaces > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Moves the LifeForm in the specified location the input number of spaces in
	 * the LifeForm's currentDirection, assuming no obstacles at the destination.
	 * @returns true if successfully moved, false otherwise
	 * @author Josh Varone
	 */
	public boolean move(int row, int col, int spaces)
	{
		LifeForm lifeForm = this.cells[row][col].getLifeForm();
		
		if(spaces > lifeForm.getMaxSpeed())
		{
			spaces = lifeForm.getMaxSpeed();
		}
		
		for(Edge edge : movingMachine)
		{
			if(edge.verifier.meetsCriteria(lifeForm.getDirection()))
			{
				return edge.moveDirection.move(lifeForm, this.cells, row, col, spaces);
			}
		}
		
		return false;
	}
	
	/**
	 * @return the number of rows in the Environment
	 */
	public int getWorldRows()
	{
		return this.numberOfRows;
	}
	
	/**
	 * @return the number of columns in the Environment
	 */
	public int getWorldCols()
	{
		return this.numberOfColumns;
	}
	
	
	/**
	 * Returns the currently selected LifeForm's row position.
	 * @author Jordan Long
	 * @return selectedLFRow Current Selected LifeForm's row position.
	 */
	public int getselectedLFRow()
	{
		return this.selectedLFRow;
	}
	
	/**
	 * Sets the selectedLFRow()
	 */
	public void setSelectedLFRow(int row)
	{
		this.selectedLFRow = row;
	}
	
	/**
	 * Returns the currently selected LifeForm's column position.
	 * @author Jordan Long
	 * @return selectedLFCol Current Selected LifeForm's column position.
	 */
	public int getselectedLFCol()
	{
		return this.selectedLFCol;
	}
	
	/**
	 * sets the selectedLFCol
	 */
	public void setSelectedLFCol(int column)
	{
		this.selectedLFCol = column;
	}
	
	
}
