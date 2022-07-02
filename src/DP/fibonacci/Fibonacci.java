package DP.fibonacci;

public class Fibonacci {

    public static void main(String []args){
          //System.out.format("recursive: fib of %d is ", Integer.parseInt(args[0]));
         // System.out.print( fibonacciRecursive(Integer.parseInt(args[0])));

          int mem[] = new int[(Integer.parseInt(args[0])+1)];
        for(int num : mem)
          //System.out.println("mem "+ num);
          System.out.format("Memoization: fib of %d is ", Integer.parseInt(args[0]));
          System.out.print( fibonacci_Memoization(Integer.parseInt(args[0]), mem));
    }

    static int fibonacciRecursive(int num){
        if(num <=0 ) return 0;
        if(num < 2 ) return 1;
        else
           return fibonacciRecursive(num-1)+fibonacciRecursive(num-2);
        //Time complexity - O(2^n)
        //Space Complexity O(n)
        /*                No of calls
                5           ---> 1
               /  \
              4    3       ----> 2 * 1
             /\    / \
            3  2   2   1   ----> 2* 2
           /\  /\  /\  /\
          2 1 1 0  1 0 0 0 ----> 2 * 2 * 2  (2^3)
          /\
         1  0              ----> 2 * 4  (2^4)

         Time complexity ---------> o(2^n)
         Space complexity --------> saving n number of solutions in stack -> O(n)

        */
    }

    static int fibonacci_Memoization(int num, int[] mem){
        //idea here is to store previously calculated fib numbers and reuse it in further recursive calls
        if(num == 0 || num == 1 ) {
            mem[num] = num;
        }else {
            if (mem[num] <= 0) {
                mem[num] = fibonacci_Memoization(num - 1, mem) + fibonacci_Memoization(num - 2, mem);
            }
        }
        return mem[num];
        //Time complexity - O(2^n)
        //Space Complexity O(n)
        /*
                    No of calls
                5    ----> 1
               /\
              4  3   ----> 1
             /\
            3  2     ----> 1
           /\
          2  1       ----> 1
          /\
         1  0        -----> 1

         Time complexity ---------> O(n)
         Space complexity --------> saving n number of solutions in stack -> O(n)
        */
    }

    static int fibonacci_Tabulation(){
        return 0;
    }
}
