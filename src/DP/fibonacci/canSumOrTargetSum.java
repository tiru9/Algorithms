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
    static boolean canSumRecursive(int num[], int sum , int n)
    {
        //System.out.println("Remaining target :: "+ sum);
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (num[n - 1] > sum)
            return canSumRecursive(num, sum,n - 1);

        /* else, check if sum can be achieved by any of the following
            (a) including the last element
            (b) excluding the last element */
        return canSumRecursive(num, sum, n - 1)
                || canSumRecursive(num, sum - num[n - 1], n - 1);

        /*
        *  Time complexity O(n^m)
        *   where m: sum, n is size of array
        */
    }

    public static boolean canSumMemoization(int[] num, boolean[] mem, int sum, int n){
        if(sum == 0)
            return true;

        if (n < 0)
            return false;

        if(mem[n])
            return mem[n];

        // If last element is greater than
        // sum, then ignore it
        if (sum < 0){//when current element not fit into to sum
            mem[n] = false;
            return mem[n];
        }

        if(n > 0){
            mem[n] = canSumMemoization(num, mem, sum,n - 1)
                    || canSumMemoization(num, mem,sum - num[n-1], n - 1 );
        }

        return mem[n];

        /*
        * Space complexity O(m)
        * Time complexity O(m * n)
        * where m: target sum, n is size of array
        * */
    }

    public static int canSumSubsetCount(int[] num, int count, int sum, int n) {
        //base case 1
        if (sum == 0)
            return ++count;
        //base case 2
        if (n < 0)
            return 0;

        // If current element not fit into the sum
        if (sum < 0) {
            return 0;
        }

        //count when included current i.e n-1 th element
        //count when  excluded n-1 th element and sum up
        if (n > 0) {
            count = count + canSumSubsetCount(num, count, sum, n - 1) +
                    + canSumSubsetCount(num, count, sum - num[n - 1], n - 1); //exclude current + include current
        }
        return count;
    }

        public static void main(String args[])
    {
       int num[] = new int[]{4, 5, 2, 5, 3, 6, 1};
       int sum = 9;//true
       //int num[] = new int[]{4, 5, 2};
       //int sum = 10;//false

        int n = num.length;
        System.out.println("SumRecursive, Found a subset :: "+ canSumRecursive(num, sum, n));
        boolean[] mem = new boolean[n];
        System.out.println("canSumMemoization, Found a subset :: "+ canSumMemoization(num, mem, sum, n-1));

        int subSetCount =  0;
        System.out.println("canSumSubsetCountMemoization, Found a subset :: "+ canSumSubsetCount(num,  subSetCount, sum, n-1));
    }

}
