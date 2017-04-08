import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Drew Rife and Jordan Long
 *
 * Reads a file and puts it into an arraylist, sorts the list and then prints them out
 *
 */
public class FileReader
{
	private static ArrayList<Integer> listOfRandomNumbers;

	/**
	 * establishes the file path and calls readFile
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Path filePath = Paths.get("random/random_numbers.txt");
		readFile(filePath);
	}

	/**
	 * reads the file and inserts into the list
	 * @param filePath
	 * @throws IOException
	 */
	private static void readFile(Path filePath) throws IOException
	{
		listOfRandomNumbers = new ArrayList<Integer>();
		Scanner scanner = new Scanner(filePath);

		int entriesInFile = 5000;
		for(int i = 1; i < entriesInFile ; i++)
		{
			if(scanner.hasNext())
			{
				String number = scanner.next();
				int parsedNumber = Integer.parseInt(number);
				listOfRandomNumbers.add(parsedNumber);
			}
		}
	}

}
