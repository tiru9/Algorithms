package DP.targetsum;

/*
*
* Given a set of positive numbers,
* find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.
*
* ex:   Input: {1, 2, 3, 4}
        Output: True
        Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
* */


public class EqualSubSetSum {
    public static void main(String args[]) {
        int arr[] = new int[]{1, 2, 3, 4, 6};//true
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }



        if (totalSum % 2 == 0){
            int s = totalSum/2;
            System.out.println("hasEqualSubSetSumRecursion " + hasEqualSubSetSumRecursion(arr, s, 0));
            Boolean[][] memo = new Boolean[arr.length][totalSum / 2];
            System.out.println("hasEqualSubsetSumMemoization " + hasEqualSubsetSumMemoization(arr, memo, s, 0));

            boolean[][] table = new boolean[arr.length][s + 1];
            System.out.println("hasEqualSubsetSumTabulation " + hasEqualSubsetSumTabulation(arr, table,s, 0));

        }else {
            System.out.println("Equal subset sum not possible");
        }
    }

    private static Boolean hasEqualSubSetSumRecursion(int[] arr,int sum, int index) {

        if (sum == 0)
            return true;
        if(index >= arr.length)
            return false;

        if (arr[index] <= sum) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            if (hasEqualSubSetSumRecursion(arr, sum - arr[index], index+1))
                return true;
        }

        // recursive call after excluding the number at the currentIndex
        return hasEqualSubSetSumRecursion(arr, sum, index+1);

        /*
         * Time Complexity: O(2^n) , n is size of array
         * Space Complexity: o(n), memory which is used to store the recursion stack
         */
    }

    private static Boolean hasEqualSubsetSumMemoization(int[] arr, Boolean[][] memo, int sum, int index) {

        if (sum == 0)
            return true;
        if(index >= arr.length)
            return false;

        if (memo[index] == null) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            if (hasEqualSubSetSumRecursion(arr, sum - arr[index], index+1)) {
                memo[index][sum] = true;
                return true;
            }
        }

        // recursive call after excluding the number at the currentIndex
        return hasEqualSubSetSumRecursion(arr, sum, index+1);

        /*
         * Time Complexity: O(n * s) , n is size of array
         * Space Complexity: o(n * s), sum is half total of all numbers
         */
    }

    private static Boolean hasEqualSubsetSumTabulation(int[] arr, boolean[][] table, int sum, int index) {

        // populate the sum=0 column, as we can always have '0' sum without including any element
        for(int i = 0; i < arr.length; i++){
            table[i][0] = true; //if sum is zero partition is possible without considering any elements for sum
        }

        for(int s = 1; s <= sum; s++){
            table[0][s] = arr[0] == s ? true : false;
        }

        for(int i = 1; i < arr.length; i++){
            for(int s = 1; s <= sum; s++){
                // if we can get the sum 's' without the number at index 'i'
                if(table[i-1][s]) {
                    table[i][s] = table[i-1][s]; // copy value from above row in case of possible
                } else if (s >= arr[i]) { // else if we can find a subset to get the remaining sum
                    table[i][s] = table[i-1][s - arr[i]];
                }
            }
        }
        return table[arr.length - 1][sum];

        /*
         * Time Complexity: O(n * s) , n is size of array
         * Space Complexity: o(n * s), sum is half total of all numbers
         */
    }
}
