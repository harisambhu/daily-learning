#include <stdio.h>

struct Employee {
    int id;
    char name[50];
    float basic_salary;
    float hra;
    float da;
    float total_salary;
};

int main() {
    struct Employee emp;

    printf("Enter Employee ID: ");
    scanf("%d", &emp.id);

    printf("Enter Employee Name: ");
    scanf("%s", emp.name);

    printf("Enter Basic Salary: ");
    scanf("%f", &emp.basic_salary);

    // Calculating allowances
    emp.hra = emp.basic_salary * 0.20;  // 20% HRA
    emp.da  = emp.basic_salary * 0.10;  // 10% DA
    emp.total_salary = emp.basic_salary + emp.hra + emp.da;

    // Output the result
    printf("\n--- Employee Salary Details ---\n");
    printf("ID            : %d\n", emp.id);
    printf("Name          : %s\n", emp.name);
    printf("Basic Salary  : %.2f\n", emp.basic_salary);
    printf("HRA (20%%)     : %.2f\n", emp.hra);
    printf("DA (10%%)      : %.2f\n", emp.da);
    printf("Total Salary  : %.2f\n", emp.total_salary);

    return 0
}

