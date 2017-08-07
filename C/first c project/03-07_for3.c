#include <stdio.h>

int main()
{
	char ch,t;

	printf("Type a letter 'a' to 'z': ");
	scanf("%c",&ch);

	for(t='a';t<='z';t++)
	{
		putchar(t);
		if (t == ch)
            break;
	}
	putchar('\n');

	return(0);
}

