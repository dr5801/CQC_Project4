package runner;

public class Runner
{
	public static void main(String[] args)
	{
		int n = 50;
		System.out.println("First " + n + " terms of Fibonacci sequence are: ");

		int next = 0;
		int first = 0;
		int second = 1;
		int i;
		for(i = 1; i <= n; i++)
		{
			if(i >= 1)
			{
				next = first + second;
				first = second;
				second = next;
			}
			else
			{
				next = i;
			}

			System.out.println(next);
		}
	}
}
