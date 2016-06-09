/**
 * Given a set of ints and a desired sum this will
 * return the number of and set of unique combinations of 
 * those ints whose sum equals the target amount.
 * 
 * Example: int[] = {1,2,3,4}, targetSum=6
 * 			numberOfCombinations=2 (4+2, 3+2+1)
 * 
 * @author Alan Fluder
 * @date June 8th 2016
 * 
 * 
 * How would you extend it to deal with a varying length input array?
 * This program already deals with this. By looping through combinations from 1 digit until the array's length it makes no difference of the length of the integer set
 * 
 * How would you extend it to return the set of combinations instead of the number of combinations?
 * This program already deals with this. Since the program saves the index values for each combinations all I need to do was save look to correct combinations and after one was found I would translate those index values to the numbers in the array and print them out. 
 * 
 */



import java.util.Arrays;
import java.util.Scanner;

public class Combination {

	static int numberOfCombinations=0;
	static int curSubsetLength=1;
	static boolean correctCombination=false;
	static int targetSum=0;

	/**
	 * Starting with 1 loops through possible lengths of input array to get possible combinations
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] initialArray = createInitialArray();

		while (curSubsetLength<=initialArray.length) 
		{
			int[] indexValuesToSum = new int[curSubsetLength]; 
			getPossibleCombinations(initialArray, indexValuesToSum, targetSum, 0, 0);
			curSubsetLength++;
		}

		System.out.println("Number of Combinations: " + numberOfCombinations);
	}
	/**
	 * Recursively loops through array testing all number combinations 
	 * 
	 * @param initialArray Set of numbers imported to test
	 * @param indexValuesToSum Second array containing index values
	 * @param targetSum Sum set of of number is testing against
	 * @param arrayCounter Holds index values of corresponding to initialArray
	 * @param indexValuesCounter Counter for second array
	 */
	public static void getPossibleCombinations(int[] initialArray, int[] indexValuesToSum, int targetSum, int arrayCounter, int indexValuesCounter){
		if(indexValuesCounter == indexValuesToSum.length){
			int arraySum = sumArray(initialArray, indexValuesToSum);

			if (arraySum == targetSum) {
				numberOfCombinations++;
				correctCombination = true;
			}
		}
		else{
			for(int i = arrayCounter; i < initialArray.length; i++){
				indexValuesToSum[indexValuesCounter] = i;
				getPossibleCombinations(initialArray, indexValuesToSum, targetSum, i+1, indexValuesCounter+1);
				if (correctCombination)
				{
					printCorrectCombinations(initialArray, indexValuesToSum);
					correctCombination=false;
				}	
			}
		}
	}
	/**
	 * Sums numbers in initialArray corresponding to second array's index values
	 * 
	 * @param initialArray Set of numbers imported to test
	 * @param indexValuesToSum Second array containing index values
	 * @return Sum of combination of numbers
	 */
	public static int sumArray(int[] initialArray, int[]indexValuesToSum) 
	{
		int arraySum = 0;
		for(int i = 0; i < indexValuesToSum.length; i++) { 
			arraySum += initialArray[indexValuesToSum[i]];
		}
		return arraySum;
	}
	/**
	 * Loops array with index values from second array
	 * 
	 * @param initialArray Set of numbers imported to test
	 * @param indexValuesToSum Second array containing index values
	 */
	public static void printCorrectCombinations(int[] initialArray, int[] indexValuesToSum) 
	{
		System.out.println("Combination #"+numberOfCombinations+": ");
		for (int j : indexValuesToSum) {
			System.out.print(initialArray[j]+ " ");
		}
		System.out.println();
	}
	/**
	 * User prompts for each test case
	 * @return initialArray for testing
	 */
	public static int[] createInitialArray() 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Number of integers you wish to input for your set?");
		int[] initialArray = new int[in.nextInt()];
		System.out.println("Please enter your "+initialArray.length+" numbers, seperated by spaces:");
		for (int i = 0; i < initialArray.length; i++)
			initialArray[i] = in.nextInt();
		System.out.println("What is your target sum?");
		targetSum = in.nextInt();

		return initialArray;
	}
}
