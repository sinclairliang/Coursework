
//-----------------------------------------------------------------------------
// aprat.java
// Sinclair Liang
// wliang13@ucsc.edu
// pa1: Building arbitrary precision numeric types by using ADT
//-----------------------------------------------------------------------------
import java.lang.*;
import java.util.Arrays;
import java.lang.Math;

class aprat extends apint {
	private String sign = "+";
	private apint numerator;
	private apint denominator;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public aprat() {
		sign = "";
		numerator = new apint(0);
		denominator = new apint(1);
	}

	public aprat(apint n, apint d) {
		// returns aprat such as n/d;
		if (n.sign != d.sign) {
			// different signs;
			this.sign = "-";
		}
		this.sign = "";
		numerator = n;
		denominator = d;
	}

	public aprat(int n, int d) {
		// returns aprat such as n/d;
		if (n * d < 0) {
			this.sign = "-";
		}
		numerator = new apint(n);
		denominator = new apint(d);
		System.out.println();
	}

	public aprat(double a) {
		if (a < 0) {
			this.sign = "-";
		}
		apint one = new apint(1);
		apint b = new apint(a);
		numerator = b;
		denominator = one;

	}

	// ---------------------------------------------------------------------------
	// Public methods
	// ---------------------------------------------------------------------------

	public static void print(aprat z) {
		if (z.numerator.sign == z.denominator.sign) {
			System.out.print("");
		}
		if (is_one(z.denominator)) {
			printArray(z.numerator.digits);
		} else {
			System.out.print(z.sign);
			printArray(z.numerator.digits);
			System.out.print("/");
			printArray(z.denominator.digits);
			// System.out.println();
		}

	}

	aprat add(aprat z) {
		// return the simplfied result of the sum of two aprat numbers;
		apint this_up = this.numerator;
		apint this_down = this.denominator;
		apint z_up = z.numerator;
		apint z_down = z.denominator;
		apint up_result = (this_up.multiplication(z_down)).add(z_up.multiplication(this_down));
		apint down_result = this_down.multiplication(z_down);
		aprat pre_result = new aprat(up_result, down_result);
		return pre_result.normalisation();
	}

	aprat sub(aprat z) {
		// return the simplfied result of the difference of two aprat numbers;
		apint this_up = this.numerator;
		apint this_down = this.denominator;
		apint z_up = z.numerator;
		apint z_down = z.denominator;
		apint up_result = (this_up.multiplication(z_down)).sub(z_up.multiplication(this_down));
		apint down_result = this_down.multiplication(z_down);
		aprat pre_result = new aprat(up_result, down_result);
		return pre_result.normalisation();
	}

	aprat multiplication(aprat z) {
		// return the simplfied result of the product of two aprat numbers;
		apint numerator_up = this.numerator;
		apint denominator_up = this.denominator;
		apint numerator_down = z.numerator;
		apint denominator_down = z.denominator;
		apint up_result = numerator_up.multiplication(numerator_down);
		apint down_result = denominator_up.multiplication(denominator_down);
		aprat pre_result = new aprat(up_result, down_result);
		return pre_result.normalisation();
	}

	aprat division(aprat z) {
		apint this_up = this.numerator;
		apint this_down = this.denominator;
		apint z_up = z.numerator;
		apint z_down = z.denominator;
		apint up_result = this_up.multiplication(z_down);
		apint down_result = this_down.multiplication(z_up);
		aprat pre_result = new aprat(up_result, down_result);
		return pre_result.normalisation();
	}

	public static apint gcd(apint a, apint b) {
		// return the greatest common divisor of two apint numbers;
		if (is_zero(a)) {
			return b;
		} else {
			// get the remainder b/a
			apint quotient = b.division(a);
			apint remainder = b.sub(quotient.multiplication(a));
			return gcd(remainder, a);
		}
	}

	aprat normalisation() {
		// return the simplified form of any aprat number;
		apint numerator = this.numerator;
		apint denominator = this.denominator;
		apint divisor = gcd(numerator, denominator);
		return new aprat(numerator.division(divisor), denominator.division(divisor));
	}
}
