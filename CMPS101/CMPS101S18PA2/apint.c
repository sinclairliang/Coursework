/* ****************************************************** 
CREATED: Sinclair Liang on April 30th, 2018;
CHANGED: Sinclair Liang on May 1st, 2018: Almost finished, having some 
bugs with multiplications;
CHANGED: Sinclair Liang on May 4th, 2018: Modified program to recognise 
adding subtracting negative;
CHANGED: Sinclair Liang on May 4th, 2018: Adding comments;
	
******************************************************
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include"apint.h"

typedef struct apint {
	/*
	defult constructor to allocate space;
	*/
	char sign;
	int* digits;
	size_t length;
} apint;


apint* subtract(apint* one, apint* two);
	/*
	prototype for subtract to be called before it is defined;
	*/


apint* apint_new() {
	/*
	default constructor, to allocate memory;
	*/
	apint *a = malloc(sizeof(apint)); // alocate memory space
	return a;
}


apint* normalisation(apint *original) {
	/*
	making sure it returns the normalised form of apint object by eliminating leading zeros;
	*/
	apint *result = apint_new(); // inisiate a new apint object';
	int leading_zero = 0; // initiasing leading zero to 0;

	while (original->digits[leading_zero] == 0) {
	// count leading zeros;
		leading_zero++;
	}

	result->sign = original->sign; // keep the sign
	result->length = original->length - leading_zero; // calculate the actual length;

	if (result->length == 0) {
		result->length++;
		result->digits = malloc(result->length * sizeof(int));
		result->digits[0] = 0;
	} else {
		result->digits = malloc(result->length * sizeof(int));
		for (int i = 0; i < result->length; i++) {
			result->digits[i] = original->digits[leading_zero + i];
		}
	}

	return result;
}


apint* ArrayConstruct(char *a) {
	/*
	Turning a string into an apint object;
	*/
	apint *result = apint_new();
	int leading_zero = 0;
	char s0 = a[0]; // takes the first char of the array;

	while (a[leading_zero] == '0') {
		// count leading zeros;
		leading_zero++;
	}

	if (!isdigit(s0)) {
		// if there is a sign with it;
		result->sign = s0;
		result->length = strlen(a) - leading_zero - 1;
	} else {
		// if there is no sign with it;
		result->length = strlen(a) - leading_zero;
	}

	if (result->length == 0) {
		// construct the what is inside apint object;
		// if apint == 0;
		result->length++;
		result->digits = malloc(result->length * sizeof(int));
		result->digits[0] = 0;
	} else {
		result->digits = malloc(result->length * sizeof(int));

		if (!isdigit(s0)) {
		// if there is a sign with it;
		// construct the what is inside apint object;
			for (int i = 0; i < result->length; i++) {
				result->digits[i] = a[leading_zero + i + 1] - '0';
			}
		} else {
		// if there is no sign with it;
			for (int i = 0; i < result->length; i++) {
				result->digits[i] = a[leading_zero + i] - '0';
			}
		}
	}
	return result;
}


apint* intConstruct(int a) {
	/*
	Turning integer into apint object;
	*/
	apint *result = apint_new();

	if (a == 0) {
		result->length = 1;
		result->digits = malloc(result->length * sizeof(int));
		result->digits[0] = 0;
	} else {
		int back_up = 0 + a;

		while (a > 0) {
			result->length++;
			a /= 10;
		}

		result->digits = malloc(result->length * sizeof(int));

		for (int i = 0; i < result->length; i++) {
			result->digits[result->length - i - 1] = back_up % 10;
			back_up /= 10;
		}
	}

	apint* result_value = normalisation(result);
	return result_value;
}


void print(apint *Z) {
	/*
	the method to properly print out apint object;
	*/
	if (Z->sign == '-') {
		printf("%c", Z->sign);
	}

	else {
		printf("%c", '+');
	}

	for (int i = 0; i < Z->length; i++) {
		printf("%d", Z->digits[i]);
	}

	printf("\n");
}


apint* half(apint* A) {
	/*
	Reture half of the value of input;
	Parameter: apint object;
	Return: apint object;
	*/
	apint *result = apint_new();
	result->length = A->length;
	result->digits = malloc(result->length * sizeof(int));
	int carry_over = 0;

	for (int i = 0; i < (A->length) ; i++) {
		result->digits[i] = (A->digits[i] + carry_over * 10) / 2;
		carry_over = A->digits[i] % 2;
	}

	return result;
}


apint* add(apint* one, apint* two) {
	/*
	Method foe addition, return the sum of two apint objects;
	Parameters: two apint objects;
	Return: the sum of two apint objects;
	*/
	apint *result = apint_new();
	result->length = (one->length >= two->length ? one->length : two->length) + 2;
	int need_add = (one->length <= two->length ? one->length : two->length) + 1;
	result->digits = malloc(result->length * sizeof(int));
	int carry_over = 0;
	int *longer_array, *shorter_array;
	// distinguish longer and shorter array so that we can properly map the length of 
	// the addition;

	if (one->length >= two->length) {
		// copying over;
		longer_array = one->digits;
		shorter_array = two->digits;
	} else {
		longer_array = two->digits;
		shorter_array = one->digits;
	}

	int *longer_array_use = malloc((result->length - 1) * sizeof(int));
	longer_array_use[0] = 0;

	for (int j = 1; j < result->length - 1; j++) {
		longer_array_use [j] = longer_array[j - 1];
	}

	int *shorter_array_use = malloc(need_add * sizeof(int));
	shorter_array_use[0] = 0;

	for (int k = 1; k < need_add; k++) {
		shorter_array_use [k] = shorter_array[k - 1];
	}

	 if (two->sign == '-')
	 {
	 	// if adding a negative number, call subtract() on them;
	 	apint *to_be_minus = apint_new();
	 	to_be_minus->sign = '+';
	 	to_be_minus->length = two->length;
	 	// copy the length over;
	 	to_be_minus->digits = malloc(to_be_minus->length * sizeof(int));
	 	for (int i = 0; i < to_be_minus->length; i++) {
	 		to_be_minus->digits[i] = two->digits[i];
	 	}
	 	to_be_minus = normalisation(to_be_minus);
	 	apint* result_value = normalisation(subtract(one, to_be_minus));
	 	return result_value;
	 }

	for (int i = 0; i < need_add; i++) {
		// doing the subtraction operation;
		int temp_digit = shorter_array_use[need_add - i - 1] + longer_array_use[result->length - 1 - i - 1] + carry_over;

		if (temp_digit < 10) {
			result->digits[result->length - i - 1] =  temp_digit;
			carry_over = 0;
		} else {
			result->digits[result->length - i - 1] =  temp_digit - 10;
			carry_over = 1;
		}
	}

	for (int k = need_add + 1; k < result->length; k++) {
		int temp_digit = longer_array_use[result->length - k - 1] + carry_over;
		if (temp_digit >= 10) {
			result->digits[result->length - k] = temp_digit - 10;
			carry_over = 1;
		}
		else {
			result->digits[result->length - k] = temp_digit;
			carry_over = 0;
		}
	}

	apint* result_value = normalisation(result);
	return result_value;
}


apint* doubling(apint* A) {
	/*
	Reture double of the value of input;
	Parameter: apint object;
	Return: apint object;
	*/
	apint* result = add(A, A);
	return result;
}


apint* subtract(apint* one, apint* two) {
	/*
	Method foe subtraction, return the difference of two apint objects;
	Parameters: two apint objects;
	Return: the difference of two apint objects;
	Assumptions: apint* A > apint* B;
	*/
	apint *result = apint_new();
	result->length = (one->length >= two->length ? one->length : two->length) + 1;
	int need_sub = (one->length <= two->length ? one->length : two->length);
	result->digits = malloc(result->length * sizeof(int));
	int carry_over = 0;
	int *longer_array, *shorter_array;

	longer_array = one->digits;
	shorter_array = two->digits;
	 if (two->sign == '-')
	 {
	 	apint *to_be_minus = apint_new();;
	 	to_be_minus->sign = '+';
	 	to_be_minus->length = two->length;
	 	to_be_minus->digits = malloc(to_be_minus->length * sizeof(int));
	 	for (int i = 0; i < to_be_minus->length; i++) {
	 		to_be_minus->digits[i] = two->digits[i];
	 	}
	 	to_be_minus = normalisation(to_be_minus);
	 	
	 	apint* result_value = normalisation(add(one, to_be_minus));
	 	return result_value;
	 }

	for (int i = 0; i < need_sub; i++) {
		int temp_digit = longer_array[one->length - i - 1] - shorter_array[two->length - i - 1] + carry_over;

		if (temp_digit < 0) {
			result->digits[result->length - i - 1] =  (10 - shorter_array[two->length - i - 1] + longer_array[one->length - i - 1] + carry_over);
			carry_over = -1;
		} else {
			result->digits[result->length - i - 1] =  temp_digit;
			carry_over = 0;
		}
	}
	for (int k = need_sub + 1; k < result->length; k++) {
		int temp_digit = longer_array[result->length - k - 1] + carry_over;
		if (temp_digit == -1) {
			result->digits[result->length - k] = 9;
			carry_over = -1;
		} else {
			result->digits[result->length - k] = temp_digit;
			carry_over = 0;
		}
	}

	apint* result_value = normalisation(result);
	return result_value;
}


int is_zero(apint* A) {
	/*
	to judge whether an apint is zero or not, return 1 if it is 0, 0 otherwise;
	*/
	for (int i = 0; i < A->length; i++) {
		if (A->digits[i] != 0) {
			return 0;
		}
	}

	return 1;
}


int is_one (apint* A) {
	/*
	To judge whether an apint is one or not, return 1 if it is 0, 0 otherwise;
	Parameter: apint object;
	Return: int;
	*/
	int i = 0;
	int leading_zero = 0;
	apint* to_compare = normalisation(A);

	if (to_compare->length == 1) {
		if (to_compare->digits[to_compare->length - 1] == 1) {
			return 1;
		}
	}

	return 0;
}


apint* mul(apint* one, apint* two) {
	/*
	Multiplication method for apint objects, return the product of two apint objects;
	Parameters: two apint objects;
	Return: the difference of two apint objects;
	Tribute: Russian-Peasant multiplication;
	*/
	int one_sign;
	int two_sign;
	apint *result = apint_new();
	apint *zero = apint_new();
	zero->length = 1;
	zero->digits = malloc(zero->length * sizeof(int));
	zero->digits[0] = 0;
	int this_is_zero = is_zero(one);
	result = zero;
	if (this_is_zero == 1) {
		return zero;
	}
	if (is_zero(two) == 1) {
		return zero;
	}

	if (one->sign != two->sign) {
		// if two apints have different signs, the result is a negative number;
		result->sign = '-';
	}
	else {
		result->sign = '+';
	}
	if (one->length == 1 && two->length == 1) {
		// if both inputs are single digits;
		int temp_result = one->digits[0] * two->digits[0];
		if (temp_result < 10) {
			result->length = 1;
			result->digits = malloc(result->length * sizeof(int));
			result->digits[0] = temp_result;
		} else {
			apint *ready_2_add = intConstruct(temp_result);
			result = add(result, ready_2_add);
		}
		return result;
	}
	result->digits = malloc(result->length * sizeof(int));
	if (one->digits[one->length - 1] % 2 != 0) {
		result = add(result, two);
	}
	while (is_one(one) == 0) {
		one = half(one);
		two = doubling(two);
		if (one->digits[one->length - 1] % 2 != 0) {
			result = add(result, two);
		}
	}
	apint* result_value = normalisation(result);
	return result_value;
}


apint* factorial(apint* a) {
	/*
	A function to calculate factorial of an apint object;
	Parameters: one apint object;
	Return: the factorial of that apint;
	*/
	apint* one = ArrayConstruct("1");
	apint* zero = ArrayConstruct("0");

	if (is_zero(a) == 1) {
		return one;
	}
	if (is_one(a) == 1) {
		return one;
	}
	else {
		return (mul(a, factorial(subtract(a, one))));
	}

}
