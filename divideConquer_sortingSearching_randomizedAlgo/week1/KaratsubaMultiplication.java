package week1;

public class KaratsubaMultiplication {
	/*
	 * Multiplication method using Karatsuba Divide and Conquer algorithm
	 * Karatsuba formula = ((a*c)*10^n) + (((a+b)*(c+d) - (a*c) - (b*d)) * 10^(n/2)) + (b*d)
	 * Karatsuba Pseudocode - https://en.wikipedia.org/wiki/Karatsuba_algorithm#Pseudocode 
	 * Assumptions -
	 * 1. Both numbers are non-negative. This can easily be fixed by counting number of
	 * negative numbers in multiplication whether it is odd or even.
	 * 
	 * @param iNum1 - First number in multiplication
	 * @param iNum2 - Second number in multiplication
	 * 
	 * @return result of multiplication
	 */
	public static String multiply(String iNum1, String iNum2) {
		if (iNum1 == null || iNum2 == null) {
			return "";
		}
		String num1 = iNum1.trim();
		String num2 = iNum2.trim();
		if (num1.length() == 1 && num2.length() == 1) {
			return String.valueOf((int) (num1.charAt(0) - '0') * (num2.charAt(0) - '0'));
		}
		// Make lengths of both strings same to the power of 2
		int maxPower = KaratsubaMultiplication
				.getMinimum2sPowerGreaterThanNumber(Math.max(num1.length(), num2.length()));
		num1 = KaratsubaMultiplication.prependZeros(num1, maxPower);
		num2 = KaratsubaMultiplication.prependZeros(num2, maxPower);
		int length = num1.length();
		int halfLength = length / 2;
//		System.out.println("num1 - " + num1 + ", num2 - " + num2 + ", length - " + length + ", halfLength - " + halfLength);
		// Generate a, b, c, d terms
		String a = num1.substring(0, halfLength);
		String b = num1.substring(halfLength, length);
		String c = num2.substring(0, halfLength);
		String d = num2.substring(halfLength, length);
//		System.out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d);
		// 3 recursive calls
		String firstTerm = KaratsubaMultiplication.multiply(a, c);
		String aPlusb = KaratsubaMultiplication.add(a, b);
		String cPlusd = KaratsubaMultiplication.add(c, d);
		String secondTerm = KaratsubaMultiplication.multiply(aPlusb, cPlusd);
		String thirdTerm = KaratsubaMultiplication.multiply(b, d);
		// Middle term - (a+b)*(c+d) - (a*c) - (b*d)
		String firstPlusThirdTerm = KaratsubaMultiplication.add(firstTerm, thirdTerm);
		String middleTerm = KaratsubaMultiplication.subtract(secondTerm, firstPlusThirdTerm);
//		System.out.println("firstTerm = " + firstTerm + ", middleTerm = " + middleTerm + ", thirdTerm = " + thirdTerm);
		// Karatsuba formula = ((a*c)*10^n) + (((a+b)*(c+d) - (a*c) - (b*d)) * 10^(n/2))
		// + (b*d)
		String firstTermWithCoe = KaratsubaMultiplication.multiplyBy10thPower(firstTerm, length);
		String middleTermWithCoe = KaratsubaMultiplication.multiplyBy10thPower(middleTerm, halfLength);
//		System.out.println("firstTermWithCoe = " + firstTermWithCoe + ", middleTermWithCoe = " + middleTermWithCoe);
		String firstTwoTermAddition = KaratsubaMultiplication.add(firstTermWithCoe, middleTermWithCoe);
		String result = KaratsubaMultiplication.add(firstTwoTermAddition, thirdTerm);
		result = KaratsubaMultiplication.removePrecedingZeros(result);
		return result;
	}

	/*
	 * Helper method to add any two non-negative integers
	 * @param iNum1 - first number of addition
	 * @param iNum2 - second number of addition
	 * 
	 * @return String - addition result
	 */
	public static String add(String iNum1, String iNum2) {
		if (iNum1 == null || iNum2 == null) {
			return "";
		}
		String num1 = iNum1.trim();
		String num2 = iNum2.trim();
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
			int digitNum1 = i > -1 ? num1.charAt(i) - '0' : 0;
			int digitNum2 = j > -1 ? num2.charAt(j) - '0' : 0;
			int sum = digitNum1 + digitNum2 + carry;
			sb.insert(0, sum % 10);
			carry = sum / 10;
		}
		if (carry > 0) {
			sb.insert(0, carry);
		}
		return sb.toString();
	}
	
	/*
	 * Helper method to subtract second from first non-negative integers
	 * Assumptions -
	 * 1 num1 is bigger than num2. This can easily be fixed by returning
	 * negative sign
	 * 
	 * @param iNum1 - first number
	 * @param iNum2 - second number
	 * 
	 * @return String - subtraction result
	 */
	public static String subtract(String iNum1, String iNum2) {
		if (iNum1 == null || iNum2 == null) {
			return "";
		}
		String num1 = iNum1.trim();
		String num2 = iNum2.trim();
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
			int digitNum1 = i > -1 ? num1.charAt(i) - '0' : 0;
			int digitNum2 = j > -1 ? num2.charAt(j) - '0' : 0;
			int subtraction = 0;
			if (digitNum1 - carry < digitNum2) {
				digitNum1 = 10 + digitNum1 - carry;
				carry = 1;
			} else {
				digitNum1 = digitNum1 - carry;
				carry = 0;
			}
			subtraction = digitNum1 - digitNum2;
			sb.insert(0, subtraction);
		}
		return sb.toString();
	}
	
	/*
	 * Helper method which multiplies any number with given power of 10
	 * 
	 * @param num - input number
	 * @param power - power of 10
	 * 
	 * @return result of number multiplied by 10^power
	 */
	public static String multiplyBy10thPower(String num, int power) {
		StringBuilder sb = new StringBuilder();
		sb.append(num);
		int i = power;
		while (i > 0) {
			sb.append(0);
			i--;
		}
		return sb.toString();
	}
	
	/*
	 * Helper method to get minimal number greater than n which is power of 2
	 * 
	 * @param n - input number
	 * 
	 * @return integer result
	 */
	public static int getMinimum2sPowerGreaterThanNumber(int n) {
		int result = 1;
		while (result < n) {
			result *= 2;
		}
		return result;
	}
	
	/*
	 * Helper method to prepend zeros to number to make it of length that is
	 * power of 2
	 * 
	 * @param num - input number
	 * @param l - final length size after prepending zeros
	 * 
	 * @return number after prepending zeros
	 */
	public static String prependZeros(String num, int l) {
		StringBuilder sb = new StringBuilder(num);
		int diff = l - num.length();
		while (diff > 0) {
			sb.insert(0, 0);
			diff--;
		}
		return sb.toString();
	}
	
	/*
	 * Helper method to remove preceding zeros
	 * 
	 * @param num - input number
	 * 
	 * @return number after removing preceding zeros
	 */
	public static String removePrecedingZeros(String num) {
		int index = 0;
		int length = num.length();
		while (index < length && num.charAt(index) == '0') {
			index++;
		}
		return num.substring(index);
	}
	/*
	 * Main method to test the Karatsuba Algorithm
	 */
	public static void main(String[] args) {
		System.out.println(
				KaratsubaMultiplication.multiply("3141592653589793238462643383279502884197169399375105820974944592",
				"2718281828459045235360287471352662497757247093699959574966967627")
				);
	}
}
