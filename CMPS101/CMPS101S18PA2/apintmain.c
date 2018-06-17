#include <stdio.h>
#include"apint.h"


void test_print()
{
	printf("Start testing print method \n");
	char *one = "629749276";
	char *two = "+4293472965783265";
	char *three = "-297927289675";
	apint* demo0 = ArrayConstruct(one);
	apint* demo1 = ArrayConstruct(two);	
	apint* demo2 = ArrayConstruct(three);
	print(demo0);
	print(demo1);
	print(demo2);
	int four = 2396;
	int five = 23876;
	int six = 32768;
	apint* demo3 = intConstruct(four);
	apint* demo4 = intConstruct(five);	
	apint* demo5 = intConstruct(six);
	print(demo3);
	print(demo4);
	print(demo5);
	printf("End of printing Unit test\n");
}


void test_add()
{
	printf("Start testing addtion method \n");
	char *one = "56483648";
	char *two = "+27589292759";
	char *three = "3842899472082";
	apint* demo0 = ArrayConstruct(one);
	apint* demo1 = ArrayConstruct(two);	
	apint* demo2 = ArrayConstruct(three);

	apint* result1 = add(demo0, demo1);
	apint* result2 = add(demo0, demo2);
	apint* result3 = add(demo1, demo2);

	printf("56483648 + 27589292759 = ");
	print(result1);

	printf("56483648 + 3842899472082 = ");
	print(result2);

	printf("27589292759 + 3842899472082 = ");
	print(result3);

	printf("End of addtion unit test\n");

}

void test_sub()
{
	printf("Start testing subtraction method \n");
	char *one = "-67";
	char *two = "+27589292759";
	char *three = "877";
	char *four = "3842899472082";

	apint* demo0 = ArrayConstruct(one);
	apint* demo1 = ArrayConstruct(two);	
	apint* demo2 = ArrayConstruct(three);
	apint* demo3 = ArrayConstruct(four);

	apint* result1 = subtract(demo2, demo0);
	apint* result2 = subtract(demo3, demo1);
	apint* result3 = subtract(demo1, demo2);

	printf("877 - (-67) = ");
	print(result1);

	printf("3842899472082 - 27589292759 = ");
	print(result2);

	printf("27589292759 - 877 = ");
	print(result3);

	printf("End of subtraction unit test\n");

}


void test_mul()
{
	printf("Start testing multiplication method \n");
	char *one = "56483648";
	char *two = "+27589292759";
	char *three = "3842899472082";

	apint* demo0 = ArrayConstruct(one);
	apint* demo1 = ArrayConstruct(two);	
	apint* demo2 = ArrayConstruct(three);

	apint* result1 = mul(demo2, demo0);
	apint* result2 = mul(demo2, demo1);
	apint* result3 = mul(demo1, demo0);

	printf("3842899472082 * 56483648 = ");
	print(result1);

	printf("3842899472082 * 27589292759 = ");
	print(result2);

	printf("27589292759 * 56483648 = ");
	print(result3);

	printf("End of multiplication unit test\n");

}


void test_extra_credits()
{
	printf("Start testing Extra Credits Sections \n");
	printf("1000! = ");
	int thousand = 1000;
	apint* demo0 = intConstruct(thousand);
	apint* result0 = factorial(demo0);
	print(result0);
}

void negative_test(){
	printf("Start testing negative numbers \n");
	char *one = "-76";
	char *two = "+658457";
	char *three = "+25289675";
	char *four = "-5464";
	apint* demo0 = ArrayConstruct(one);
	apint* demo1 = ArrayConstruct(two);	
	apint* demo2 = ArrayConstruct(three);
	apint* demo6 = ArrayConstruct(four);

	apint* demo3 = normalisation(demo0);
	apint* demo4 = normalisation(demo1);
	apint* demo5 = normalisation(demo2);

	apint* result1 = add(demo2, demo0);
	apint* result2 = subtract(demo2, demo6);
	

	print(demo3);
	print(demo4);
	print(demo5);
	printf("25289675 + (-76) = ");
	print(result1);
	printf("25289675 - (-5464) = ");
	print(result2);
}



int main(void)
{
	test_print();
	printf("\n");
	negative_test();
	printf("\n");
	test_add();
	printf("\n");
	test_sub();
	printf("\n");
	test_mul();
	printf("\n");
	test_extra_credits();


	return 0;	
}