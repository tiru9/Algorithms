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
    static boolean canSumRecursive(int num[], int sum, int n) {
        //System.out.println("Remaining target :: "+ sum);
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (num[n - 1] > sum)
            return canSumRecursive(num, sum, n - 1);

        /* else, check if sum can be achieved by any of the following
            (a) including the last element
            (b) excluding the last element */
        return canSumRecursive(num, sum - num[n - 1], n - 1) || canSumRecursive(num, sum, n - 1);

        /*
         *  Time complexity O(n^m)
         *   where m: sum, n is size of array
         */
    }

    public static boolean canSumMemoization(int[] num, boolean[] mem, int sum, int n) {
        if (sum == 0)
            return true;

        if (n < 0)
            return false;

        if (mem[n])
            return mem[n];

        // If last element is greater than
        // sum, then ignore it
        if (sum < 0) {//when current element not fit into to sum
            mem[n] = false;
            return mem[n];
        }

        if (n > 0) {
            mem[n] = canSumMemoization(num, mem, sum, n - 1)
                    || canSumMemoization(num, mem, sum - num[n - 1], n - 1);
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
                    +canSumSubsetCount(num, count, sum - num[n - 1], n - 1); //exclude current + include current
        }
        return count;
    }

    private static boolean canSumTabulation(int[] num, boolean[][] table, int sum) {
        // step1: fill first columns with true as sum zero is possible with out taking any number from array
        for (int i = 0; i <= num.length; i++)
            table[0][i] = true;

        //step2: fill first rows with false as no sum possible with considering zero
        //So java following step not required as default primitive boolean values are false
        // for(int j = 0; j <= sum; j++)
        //     table[j][0] = true;

        // step3: Fill table for possible sums until target
        for (int i = 1; i <= num.length; i++)
            for (int j = 1; j <= sum; j++) {
                //assign the current row values with above row, this can be true or false based on previous possibilities
                table[i][j] = table[i][j - 1];

               /*check if current sum is greater than current value from input array
                 a) if greater compare current table[i][j] with remainder sum column from previous row and make xor
                    i.e sum is possible with current and previous values
                 b) else continue filling
                */
                if(i >= num[i-1])
                    table[i][j] =  table[i][j] ||  table[i-num[i-1]][j-1];
            }
        //Final sum possibility is calculated at right-bottom corner
        return table[num.length][sum];
    }

    public static void main(String args[]) {
        int num[] = new int[]{4, 5, 2, 5, 3, 6, 1};
        int sum = 9;//true
        //int num[] = new int[]{4, 5, 2};
        //int sum = 10;//false

        int n = num.length;
        System.out.println("SumRecursive, Found a subset :: " + canSumRecursive(num, sum, n));
        boolean[] mem = new boolean[n];
        System.out.println("canSumMemoization, Found a subset :: " + canSumMemoization(num, mem, sum, n - 1));

        boolean table[][] = new boolean[n + 1][sum + 1];
        System.out.println("canSumTabulation, Found a subset :: " + canSumTabulation(num, table, sum));

        int subSetCount = 0;
        System.out.println("canSumSubsetCountMemoization, Found a subset :: " + canSumSubsetCount(num, subSetCount, sum, n - 1));
    }
}
