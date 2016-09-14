package projectEuler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class ProjectEuler2 {
	
	public static long pathCounter = 0;//for question 15 only

	/**
	 * What is the greatest product of four adjacent numbers in the same
	 * direction (up, down, left, right, or diagonally) in the 20 by 20 grid?
	 * 
	 * @param arr
	 *            array
	 * @return greatest product
	 */
	public static int question11(int[][] arr) {
		int horizontalMax = getHorizontalMax(arr);
		int verticalMax = getVerticalMax(arr);
		int leftDiag = leftDiag(arr);
		int rightDiag = rightDiag(arr);
		return getMax(horizontalMax, verticalMax, leftDiag, rightDiag);

	}

	/**
	 * gets horizontal max
	 * 
	 * @param arr
	 * @return greatest horizontal max product
	 */
	private static int getHorizontalMax(int[][] arr) {
		int max = 0;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length - 3; c++) {
				int product = arr[r][c] * arr[r][c + 1] * arr[r][c + 2]
						* arr[r][c + 3];
				if (product > max) {
					max = product;
				}
			}
		}
		return max;
	}

	/**
	 * helper method that gets vertical max
	 * 
	 * @param arr
	 *            array to search from
	 * @return product of vertical max
	 */
	private static int getVerticalMax(int[][] arr) {
		int max = 0;
		for (int r = 0; r < arr.length - 3; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				int product = arr[r][c] * arr[r + 1][c] * arr[r + 2][c]
						* arr[r + 3][c];
				if (product > max) {
					max = product;
				}
			}
		}
		return max;
	}

	/**
	 * helper method that gets left diag max
	 * 
	 * @param arr
	 *            array to search from
	 * @return product of max left diag
	 */
	private static int leftDiag(int[][] arr) {
		int max = 0;
		for (int r = 0; r < arr.length - 3; r++) {
			for (int c = 3; c < arr[r].length; c++) {
				int product = arr[r][c] * arr[r + 1][c - 1] * arr[r + 2][c - 2]
						* arr[r + 3][c - 3];
				if (product > max) {
					max = product;
				}
			}
		}
		return max;
	}

	/**
	 * helper method that returns the maximum right Diagonal
	 * 
	 * @param arr
	 *            the array to search from
	 * @return the maximum right Diagonal
	 */
	private static int rightDiag(int[][] arr) {
		int max = 0;
		for (int r = 0; r < arr.length - 3; r++) {
			for (int c = 0; c < arr.length - 3; c++) {
				int product = arr[r][c] * arr[r + 1][c + 1] * arr[r + 2][c + 2]
						* arr[r + 3][c + 3];
				if (product > max) {
					max = product;
				}
			}
		}
		return max;
	}

	/**
	 * returns the max of 4 numbers
	 * 
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @return the max
	 */
	private static int getMax(int one, int two, int three, int four) {
		if (one > two && one > three && one > four) {
			return one;
		} else if (two > one && two > three && two > four) {
			return two;
		} else if (three > one && three > two && three > four) {
			return three;
		} else {
			return four;
		}
	}

	/**
	 * What is the value of the first triangle number to have over five hundred
	 * divisors?
	 * 
	 * @param numOfDiv
	 *            five hundred(number of divisors)
	 * @return the triangle number
	 */
	public static int question12(int numOfDiv) {
		int counter = 1;
		int tCounter = numOfDiv;
		int tNumber = triangleNumber(tCounter);
		while (counter < (numOfDiv + 1)) {
			for (int i = 2; i <= tNumber; i++) {
				if (tNumber % i == 0) {
					counter++;
				}
			}
			if (counter < (numOfDiv + 1)) {
				tNumber = triangleNumber(++tCounter);
				counter = 1;
			}
		}
		return tNumber;
	}

	/**
	 * helper method that calculates the triangle number of the given number
	 * 
	 * @param numbers
	 *            number to add up until.
	 * @return the Triangle Number
	 */
	private static int triangleNumber(int numbers) {
		int triangleNumber = 0;
		for (int i = 1; i <= numbers; i++) {
			triangleNumber += i;
		}
		return triangleNumber;
	}

	/**
	 * Work out the first ten digits of the sum of the following input.
	 * 
	 * @param input
	 *            an array of very long numbers
	 * @return first ten digits of the sum of the input
	 */
	public static long question13(BigInteger[] input) {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 0; i < input.length; i++) {
			sum = sum.add(input[i]);
		}
		String bigNumber = sum.toString();
		long answer = 0;
		String temp = bigNumber.substring(0, 10);
		answer = Long.parseLong(temp);
		return answer;
	}

	/**
	 * Which starting number, under one million, produces the longest Collatz
	 * chain?
	 * 
	 * @param number
	 *            the number 999999
	 * @return the number with the longest collatz chain
	 */
	public static long question14(long number) {
		int max = 0;
		for (long i = number; i > 0; i--) {
			int sequenceLength = collatzNumbers(i);
			if (sequenceLength >= max) {
				max = sequenceLength;
				number = i;
			}
		}
		return number;
	}

	/**
	 * helper method that finds number of terms in collatz chain
	 * 
	 * @param number
	 *            number to find collatz chain for
	 * @return number of terms in collatz chain
	 */
	private static int collatzNumbers(long number) {
		//int counter = 1;
		//long originalNumber = number;
		ArrayList<Long> collatzSeq = new ArrayList<Long>();
		while (number != 1) {
			collatzSeq.add(number);
			if (number % 2 == 0) {
				number = number / 2;
			} else {
				number = 3 * number + 1;
			}
		}
		//System.out.println(String.format("For %d size of Collatz Sequence is %d", originalNumber, collatzSeq.size()));
		return collatzSeq.size();
	}
	
	/**
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param dimension
	 *            the size of the matrix
	 * @return the number of paths
	 */
	public static long question15(int x, int y, int dimension) {//add one to your actual dimension
		if(x > dimension - 1 || y > dimension - 1)
			return pathCounter;
		if(x == y && x == dimension - 1) {
			pathCounter++;
			return pathCounter;
		}
		question15(x, y + 1, dimension);
		question15(x + 1, y, dimension);
		return pathCounter;
	}
	
	/**
	 * 
	 * @param power
	 *            the power to which the number will be raised
	 * @return the sum of the digits of the number
	 */
	public static BigInteger question16(int power) {
		BigInteger number = BigInteger.ONE.add(BigInteger.ONE);// 2
		number = number.pow(power);
		String num = number.toString();
		System.out.println(num);
		BigInteger sum = BigInteger.ZERO;
		while (number.compareTo(BigInteger.ZERO) != 0) {
			sum = sum.add(number.mod(BigInteger.TEN));
			number = number.divide(BigInteger.TEN);
		}
		return sum;
	}
	
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static int question17(int number) {
		int charCount = 0;
		for(int i = 1; i <= number; i++) {
			if(i == 1 || i == 2 || i == 6 || i == 10)
				charCount += 3;
			else if(i == 3 || i == 7 || i == 8)
				charCount += 5;
			else if(i == 4 || i == 5 || i == 9)
				charCount += 4;
			else if(i == 11 || i == 12)
				charCount += 6;
		}
		return charCount;
	}
	
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static int question18(int[][] arr, int index1, int index2) {
		int sum = arr[index1][index2];
		System.out.print(arr[index1][index2] + " ");
		while(index1 < arr.length - 1) {
			sum += Math.max(arr[index1 + 1][index2], arr[index1 + 1][index2 + 1]);
			System.out.print(Math.max(arr[index1 + 1][index2], arr[index1 + 1][index2 + 1]) + " ");
			int firstVal = arr[index1 + 1][index2];
			int secondVal = arr[index1 + 1][index2 + 1];
			index1++;
			if(firstVal < secondVal) {
				index2++;
			}
		}
		System.out.printf("\n");
		return sum;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Date start = Calendar.getInstance().getTime();
		/**FileReader triangleNumbers = new FileReader("/Users/anshuman/Desktop/triangle.txt");**/
		int[][] arr = {{75},
				       {95, 64},
				       {17, 47, 82},
				       {18, 35, 87, 10},
				       {20, 4, 82, 47, 65},
				       {19, 1, 23, 75, 3, 34},
				       {88, 02, 77, 73, 07, 63, 67},
				       {99, 65, 04, 28, 06, 16, 70, 92}, 
				       {41, 41, 26, 56, 83, 40, 80, 70, 33},
				       {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
				       {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
				       {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
				       {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
				       {63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31}, 
				       {04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23}};
		int answer = question18(arr, 0, 0);
		Date end = Calendar.getInstance().getTime();
		String answer1 = "" + answer;
		System.out.println("time: " + (end.getTime() - start.getTime())
				+ "\nanswer: " + answer1);
	}

}
