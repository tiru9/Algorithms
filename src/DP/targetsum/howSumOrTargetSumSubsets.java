package DP.targetsum;

import java.util.ArrayList;
import java.util.List;

public class howSumOrTargetSumSubsets {
    /*
     * Problem statement:
     * From a given array find possibility yes or no, to construct a given sum.
     * Ex1 : Find sum of 10 from array [4, 5, 2, 5, 3, 6, 1]
     * so possibilities are : [4, 5,1] or [5, 5] or  [2, 5, 3] or [3, 6, 1]
     * O/P: true
     * -----------
     * Ex2 : Find sum of 10 from array [4, 5, 2]
     * so possibilities are : []
     * O/P : false
     * */

    // Returns true if there is a subset
    // of set[] with sum equal to given sum
    static boolean howSumRecursive(int num[], int sum , List<Integer> indexes, int n) {

        if(sum == 0){
            System.out.println();
            for(int i : indexes){
                System.out.print(" "+i);
            }
            //indexes = new ArrayList<Integer>();
            return true;
        }

        if(n < num.length -1 ) {
            if (sum < num[n]) {
                howSumRecursive(num, sum, indexes, n+1);
            }

            indexes.add(n);
            //include current index sum
            howSumRecursive(num, sum - num[n], indexes, n+1);
            indexes = new ArrayList<Integer>();

            //exclude current index
            howSumRecursive(num, sum, indexes, n+1);
        }
        return true;
    }


        public static void main(String args[])
    {
       int num[] = new int[]{4, 5, 1};
       int sum = 5;//true
       //int num[] = new int[]{4, 5, 2};
       //int sum = 10;//false

        int n = num.length;
        List<Integer> indexes = new ArrayList<Integer>();
        howSumRecursive(num, sum, indexes, 0);

        // boolean[] mem = new boolean[n];
       // System.out.println("canSumMemoization, Found a subset :: "+ canSumMemoization(num, mem, sum, n-1));

       // int subSetCount =  0;
       // System.out.println("canSumSubsetCountMemoization, Found a subset :: "+ canSumSubsetCountMemoization(num,  subSetCount, sum, n-1));

    }

}
