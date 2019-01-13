import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class Knapsack {

	// Recursive version
	// Returns the maximum value that can be put in a knapsack of capacity W

	static int knapSackRecursive(int W, int wt[], int val[], int n) {
		// Base Case
		if (n == 0 || W == 0)
			return 0;

		// If weight of the nth item is more than Knapsack capacity W, then
		// this item cannot be included in the optimal solution
		if (wt[n - 1] > W)
			// Your code needed here to replace the return
			return knapSackRecursive(W, wt, val, n - 1);
		// Return the maximum of two cases:
		// (1) nth item included
		// (2) not included
		else
			// Your code needed here to replace the return
			return max(val[n - 1] + knapSackRecursive(W - wt[n - 1], wt, val, n - 1),
					knapSackRecursive(W, wt, val, n - 1));

	}

	// Bottom up version
	// Returns the maximum value that can be put in a knapsack of capacity W
	static int knapSack(int bagCapCity, int wt[], int val[], int n) {

		int K[][] = new int[n + 1][bagCapCity + 1];
		boolean A[][] = new boolean[n + 1][bagCapCity + 1];
		// {(1,2) -> [1,2,3,4]}

		for (int index = 0; index <= n; index++) {
			for (int weight = 0; weight <= bagCapCity; weight++) {
				if (index == 0 || weight == 0) {
					K[index][weight] = 0;
					A[index][weight] = false;
				}

				else if (wt[index - 1] <= weight) {
					// store the answer for this item at this capacity
					// value of this item + | wt[i-1] is the weight of this item
					K[index][weight] = max(val[index - 1] + K[index - 1][weight - wt[index - 1]], K[index - 1][weight]);

					if (val[index - 1] + K[index - 1][weight - wt[index - 1]] >= K[index - 1][weight]) {

						A[index][weight] = true;

					}

					else {

						A[index][weight] = false;
					}
				}
			}
		}
		printKnapSack(A, bagCapCity, wt, val, n);
		return K[n][bagCapCity];
	}

	public static void printKnapSack(boolean[][] A, int W, int wt[], int val[], int n) {
		if (n == 0 || W == 0)
			return;
		if (A[n][W]) {
			System.out.println("The knapsack is filled with the following value: " + val[n - 1]);
			printKnapSack(A, W - wt[n - 1], wt, val, n - 1);
		} else {
			printKnapSack(A, W, wt, val, n - 1);
		}

	}

	private static int max(int x, int y) {

		if (x > y) {
			return x;
		}
		return y;
	}

	// Driver program to test above function
	public static void main(String args[]) {

		int val[] = new int[] { 60, 100, 120 };
		int wt[] = new int[] { 10, 20, 30 };
		int W = 50;
		int n = val.length;

		System.out.println("The capcity of the knapsack is : " + knapSack(W, wt, val, n));

	}

}
