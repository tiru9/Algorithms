package DP.fibonacci;

public class canSumOrTargetSum {
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
    static boolean canSumRecursive(int num[], int n, int sum)
    {
        System.out.println("Remaining target :: "+ sum);
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (num[n - 1] > sum)
            return canSumRecursive(num, n - 1, sum);

        /* else, check if sum can be obtained
        by any of the following
            (a) including the last element
            (b) excluding the last element */
        return canSumRecursive(num, n - 1, sum)
                || canSumRecursive(num, n - 1, sum - num[n - 1]);
    }

    public static void main(String args[])
    {
       int num[] = new int[]{4, 5, 2, 5, 3, 6, 1};
       int sum = 9;
       // int set[] = new int[]{4, 5, 2};
       // int sum = 10;

        int n = num.length;
        System.out.println("Found a subset :: "+ canSumRecursive(num, n, sum));
    }

}
