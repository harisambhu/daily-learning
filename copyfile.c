#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *src, *dest;
    char ch;

    // Open input.txt for reading
   src = fopen("input.txt", "r");
    if (src == NULL) {
        printf("Cannot open input.txt\n");
        return 1;
    }

    // Open output.txt for writing
    dest = fopen("output.txt", "w");
    if (dest == NULL) {
        printf("Cannot open output.txt\n");
        fclose(src);
        return 1;
    }

    // Copy contents
    while ((ch = fgetc(src)) != EOF) {
        fputc(ch, dest);
    }

    printf("File copied successfully.\n");

    fclose(src);
    fclose(dest);

    return 0;
}

