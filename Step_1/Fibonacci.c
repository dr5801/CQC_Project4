/*
 * Fibonacci.c
 *
 *  Created on: Mar 28, 2017
 *      Author: dr5801
 */


int main(void)
{
	int n = 50;
	printf("First %d terms of Fibonacci sequence are: \n", n);

	int next;
	int first = 0;
	int second = 1;
	int i;
	for(i = 0; i < n; i++)
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

		printf("%d\n", next);
	}
}
