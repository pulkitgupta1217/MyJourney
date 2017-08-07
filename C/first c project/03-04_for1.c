#include <stdio.h>

int main()
{
	int x;

	for(x=0;x<20;x++)
		printf("%d\n",x+1);
    for (x = 1; x < 20; x+=2)
    {
        printf("%d\n",x+1);
    }
	for (x = 10; x > 0; x--)
	{
	    printf("%d\n", x);
	}

	return(0);
}

