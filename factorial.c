#include <stdio.h>

int factorial(int n) {
    if (n == 0 || n == 1)
        return 1;    // Base case
    else
        return n * factorial(n - 1);   // Recursive call
}

int main() {
    int num ;

    printf("Enter a number: ");
    scanf("%d", &num);

    if (num < 0)
        printf("Factorial of a negative number doesn't exist.\n");
    else
        printf("Factorial of %d = %d\n", num, factorial(num));

    return 0;
}

