package projectEuler;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * static methods for Project Euler questions
 * 
 * @author anshuman
 * 
 */
public class ProjectEuler {

	/**
	 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
	 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
	 * 
	 * Find the sum of all the multiples of 3 or 5 below 1000.
	 * 
	 * @param upperBound
	 *            the upperBound for which the sum should be below
	 * @return the sum
	 */
	public static int question1(int upperBound) {
		int startingNumber = 0;
		int sum = 0;
		while (startingNumber < upperBound) {
			if (startingNumber % 3 == 0 || startingNumber % 5 == 0) 
				sum += startingNumber;
			startingNumber++;
		}
		return sum;
	}

	/**
	 * What is the largest prime factor of the number 600851475143?
	 * 
	 * @param number
	 *            The number which we need to find the largest prime factor for.
	 * @return the largest prime Factor.
	 */
	public static int question3(long number) {
		ArrayList<Integer> primeFactors = primeFactors(number);
		return primeFactors.get(primeFactors.size() - 1);
	}

	/**
	 * helper method that returns a list of the prime factors of a number.
	 * 
	 * @param number
	 *            number which we need to find the prime factorization for.
	 * @return list of prime factors.
	 */
	private static ArrayList<Integer> primeFactors(long number) {
		ArrayList<Integer> primeFactor = new ArrayList<Integer>();
		for (int i = 2; i <= number; i++) {
			if (number % i == 0) {
				primeFactor.add(i);
				number /= i;
				i--;
			}
		}
		return primeFactor;
	}

	/**
	 * 
	 * @return largest palindrome number
	 */
	public static int question4() {
		int palindrome = 0;
		int max = 0;
		for (int i = 100; i <= 999; i++) {
			for (int j = 100; j <= 999; j++) {
				int product = i * j;
				if (isPalindrome(product) && product > max) {
					palindrome = product;
					max = product;
				}
			}
		}
		return palindrome;
	}

	/**
	 * helper method that returns true if the parameter is a palindrome, else
	 * false.
	 * 
	 * @param number
	 *            the number that must be tested
	 * @return true if palindrome, else false.
	 */
	private static boolean isPalindrome(int number) {
		Integer input = new Integer(number);
		String parameter = input.toString();
		int lastIndex = parameter.length() - 1;
		int startingIndex = 0;
		if (parameter.length() % 2 == 1) {
			while (startingIndex != lastIndex) {
				if (parameter.charAt(startingIndex) == parameter
						.charAt(lastIndex)) {
					startingIndex++;
					lastIndex--;
				} else 
					break;
			}
		} else if (parameter.length() % 2 == 0) {
			while (lastIndex - startingIndex > 0) {
				if (parameter.charAt(startingIndex) == parameter
						.charAt(lastIndex)) {
					startingIndex++;
					lastIndex--;
				} else 
					break;
			}
		}
		if (startingIndex == lastIndex || lastIndex - startingIndex < 0) 
			return true;
		else 
			return false;

	}

	/**
	 * What is the smallest positive number that is evenly divisible by all of
	 * the numbers from 1 to a given upperBound?
	 * 
	 * @return the smallest number divisible from 1 to upperBound
	 */
	public static int question5(int upperBound) {
		int i = 2;
		int number = 2520;
		while (i < upperBound) {
			if (number % i == 0) 
				i++;
			else {
				number++;
				i = 2;
			}
		}
		return number;
	}

	/**
	 * Find the difference between the sum of the squares of the first one
	 * hundred natural numbers and the square of the sum.
	 * 
	 * @param upperBound
	 *            the number of natural numbers, in this case, 100
	 * @return the difference.
	 */
	public static int question6(int upperBound) {
		int sumOfNumbers = 0;
		int counter = 1;
		while (counter <= upperBound) {
			sumOfNumbers += counter;
			counter++;
		}
		int squareOfSum = (int) Math.pow(sumOfNumbers, 2.0);
		int sumOfSquares = 0;
		for (int i = 1; i <= upperBound; i++) 
			sumOfSquares += (int) Math.pow(i, 2.0);
        
		int difference = squareOfSum - sumOfSquares;
		return difference;
	}

	/**
	 * What is the nth prime number?
	 * 
	 * @param nthPrime
	 *            the nthPrime we must find.
	 * @return value of the nthPrime.
	 */
	public static int question7(int nthPrime) {
		int primeCounter = 1;
		int testNumber = 3;
		while (primeCounter < nthPrime) {
			if (isPrime(testNumber)) {
				if (primeCounter != nthPrime - 1) 
					testNumber += 2;
				primeCounter++;
			} else 
				testNumber += 2;
		}
		return testNumber;
	}

	/**
	 * a helper method which tells whether a number is prime or not.
	 * 
	 * @param number
	 *            the number to test
	 * @return true if the number is prime, false if it is not.
	 */
	private static boolean isPrime(int number) {
		int sqrt = (int) Math.sqrt(number) + 1;
		for (int i = 2; i < sqrt; i++) {
			if (number % i == 0) 
				return false;
		}
		return true;
	}

	/**
	 * Find the n adjacent digits in the input that have the greatest product.
	 * What is the value of this product?
	 * 
	 * @param adjNumbers
	 *            the number of adjacent digits
	 * @param input
	 *            the long number
	 * @return the product
	 */
	public static long question8(int adjNumbers, BigInteger input) {
		int[] contenderArr = new int[adjNumbers];
		int[] solutionArr = new int[adjNumbers];
		String inputStr = input.toString();
		for (int i = 0; i < adjNumbers; i++) {
			contenderArr[i] = charToInt(inputStr.charAt(i));
			solutionArr[i] = charToInt(inputStr.charAt(i));
		}
		for (int i = 13; i < inputStr.length(); i++) {
			int indexToChange = i % adjNumbers;
			contenderArr[indexToChange] = charToInt(inputStr.charAt(i));
			if (productOfArr(contenderArr) > productOfArr(solutionArr)) {
				for (int j = 0; j < solutionArr.length; j++) {
					solutionArr[j] = contenderArr[j];
				}
			}
		}
		return productOfArr(solutionArr);
	}

	/**
	 * a helper method that turns a given character into an integer
	 * 
	 * @param ch
	 *            the character to change
	 * @return the integer form of the character
	 */
	private static int charToInt(char ch) {
		int digit = new Integer("" + ch);
		return digit;
	}

	/**
	 * a helper method that returns the product of all the terms in a given
	 * array
	 * 
	 * @param arr
	 *            the array whose terms we must multiply
	 * @return the product
	 */
	private static long productOfArr(int[] arr) {
		long product = 1;
		for (int i = 0; i < arr.length; i++) {
			product *= arr[i];
		}
		return product;
	}

	/**
	 * There exists exactly one Pythagorean triplet for which a + b + c = 1000. 
	 * Find the product abc.
	 * @param sum = 1000
	 * @return abc
	 */
	public static int question9(int sum) {
		int a = 0;
		int b = 0;
		int c = 0;
		for(a = 1; a < sum; a++) {
			for(b = a + 1; b < sum; b++) {
				for(c =  b + 1; c < sum; c++) {
					if(isPythagorean(a,b,c)) {
						if((a+b+c) == sum) {
							break;
						}
					}
				}
				if((a+b+c) == sum) {
					break;
				}
			}
			if((a+b+c) == sum) {
				break;
			}
		}
		return (a*b*c);
	}
	/**
	 * helper method that tells whether the given parameters form a pythagorean triple
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private static boolean isPythagorean(int a, int b, int c) {
		return (a*a) + (b*b) == (c*c);
	}
	
	/**
	 * Find the sum of all the primes below the upperBound.
	 * 
	 * @param upperBound
	 *            the max number to test until
	 * @return sum of all prime numbers below the upperBound.
	 */
	public static long question10(int upperBound) {
		long primeSum = 2;
		for (int i = 3; i <= upperBound; i += 2) {
			if (isPrime(i)) {
				primeSum += i;
			}
		}
		return primeSum;
	}

}
