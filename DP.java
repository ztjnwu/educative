import java.util.*;

public class DP {
    
    public static int knapsack(int[] profits, int[] weights, int number, int capacity){
        //base checkes
        if(number <= 0 || capacity <= 0){
            return 0;
        }
        
        //recursive calls
        //Do not select (number - 1)th item;
        int profit1 = knapsack(profits, weights, number - 1, capacity);
        
        //Do select (number - 1)th item;
        int profit2;
        if(weights[number - 1] <= capacity){
            profit2 = profits[number - 1] + knapsack(profits, weights, number - 1, capacity - weights[number - 1]);
        }
        else
        {
            profit2 = 0;
        }
    
        //return
        int result = Math.max(profit1, profit2);
        return result;
    }

    public static int knapsackDP(int[] profits, int[] weights, int length, int capacity)
    {
        if(profits == null || weights == null || length < 0 || capacity < 0)
        {
            return -1;
        }//

        //initialization
        int[][] dp = new int[length + 1][capacity + 1];
        for(int i = 0; i <= length; i++)
        {
            for(int j = 0; j <= capacity; j++)
            {
                dp[i][j] = 0;
            }
        }//initialization

        //DP
        for(int i = 1; i <= length; i++)
        {
            for(int j = 1; j <= capacity; j++)
            {
                int profit1 = dp[i - 1][j];
                int profit2;    
                if(weights[i - 1] <= j)
                {
                    profit2 = profits[i - 1] + dp[i - 1][j - weights[i - 1]]; 
                }//
                else 
                {
                    profit2 = dp[i - 1][j];
                }//
                
                dp[i][j] = Math.max(profit1, profit2);
            }//for

        }//for

        //return
        int result;
        result = dp[length][capacity];
        return result;

    }//knapsackDP

    public static List<Integer> knapsackDPRouteShow(int[] profits, int[] weights, int length, int capacity)
    {
        if(profits == null || weights == null || length < 0 || capacity < 0)
        {
            return null;
        }//

        //initialization
        int[][] dp = new int[length + 1][capacity + 1];
        for(int i = 0; i <= length; i++)
        {
            for(int j = 0; j <= capacity; j++)
            {
                dp[i][j] = 0;
            }
        }

        //DP
        for(int i = 1; i <= length; i++)
        {
            for(int j = 1; j <= capacity; j++)
            {
                int profit1 = dp[i - 1][j];
                int profit2;    
                if(weights[i - 1] <= j)
                {
                    profit2 = profits[i - 1] + dp[i - 1][j - weights[i - 1]]; 
                }//
                else 
                {
                    profit2 = dp[i - 1][j];
                }//
                
                dp[i][j] = Math.max(profit1, profit2);
            }//for

        }//for


        //Show the route
        int i = length, j = capacity;
        List<Integer> path = new ArrayList<>();
        while(i != 0)
        {
            if(dp[i][j] != dp[i - 1][j])
            {
                path.add(i);
            }
            
            
            //Update i and j
            if(dp[i][j] != dp[i - 1][j])
            {
                j -= weights[i - 1];
            }
            i--;
        }//

        //return
        List<Integer> result = new ArrayList<>(path);
        return result;
    }//

    public static int knapsnackTabulationOptimization(int[] profits, int[] weights, int number, int capacity){
        //base check
        if(number == 0 || capacity <= 0){
            return 0;
        }
        
        //initialization
        int[][] dp = new int[3][capacity + 1];
        for(int i = 0; i <= 2 ; i++){
            for(int j = 0; j <= capacity; j++){
                dp[i][j] = 0;
            }
        }

        //Loop
        for(int i = 1; i <= number; i++){
            for(int j = 1; j <= capacity; j++){
                int profit1 = dp[(i - 1) % 2 + 1][j];
                int profit2 = 0;
                if(weights[i - 1] <= j){
                    profit2 = profits[i - 1] + dp[(i - 1) % 2 + 1][j - weights[i - 1]]; 
                }
                else {
                    profit2 = profit1;
                }
                dp[i % 2 + 1][j] = Math.max(profit1, profit2);
            }
        }

        //return
        return dp[(number % 2) + 1][capacity];
    }

    public static boolean sumPartipationRE(int[] arr, int number, int sum){
        //base check
        if(number < 0 || sum < 0){
            return false;
        }
        //empty subset
        if(sum == 0){
            return true;
        }
        else if(number == 0){
            return false;
        }

        //loop
        boolean flag1 = sumPartipationRE(arr, number - 1, sum);
        boolean flag2 = false;
        if(arr[number - 1] <= sum){
            flag2  = sumPartipationRE(arr, number - 1, (sum - arr[number - 1]));
        }
        else {
            flag2 = flag1;
        }

        //return 
        boolean result = flag1 || flag2;
        return result;
    }

    public static boolean sumPartitionDP(int[] arr, int sum)
    {
        if(arr == null || sum % 2 != 0)
        {
            return false;
        }

        //Initialization
        boolean[][] dp = new boolean[arr.length + 1][(sum/2) + 1];
        for(int i = 0; i <= arr.length; i++)
        {
            for(int j = 0; j <= (sum / 2); j++)
            {
                if(j == 0)
                {
                    dp[i][j] = true;
                }//
                else 
                {
                    dp[i][j] = false;
                }//
                
            }//
            
        }//for

        int[] arr_copy = new int[arr.length + 1];
        for(int j = 1; j <= arr.length; j++)
        {
            arr_copy[j] = arr[j - 1];
        }


        //Compute dp[][]
        for(int i = 1; i <= arr.length; i++)
        {
            for(int j = 1; j <= (sum / 2); j++)
            {
                boolean result1 = dp[i - 1][j];
                boolean result2 = false;;
                if(arr_copy[i] > j)
                {
                    result2 = dp[i - 1][j];
                }//
                else //arr_copy[i] < j
                {
                    if(dp[i - 1][j - arr_copy[i]] == true)
                    {
                        result2 = true;
                    }
                    else // 
                    {
                        result2 = false;
                    }//else

                }//else

                dp[i][j] = result1 || result2;
                
            }//

        }//for

        //returnx
        return dp[arr.length][sum / 2];
    }

    public static boolean sumEqualSDP(int[] arr, int S)
    {
        //Check validity
        if(arr == null || S <= 0)
        {
            return false;
        }

        //Initialization
        boolean[][] dp = new boolean[arr.length + 1][S + 1];
        for(int i = 0; i <= arr.length; i++)
        {
            for(int j = 0; j <= S; j++)
            {
                if(j == 0)
                {
                    dp[i][j] = true;
                }
                else 
                {
                    dp[i][j] = false;
                }//
            }
        }//

        int[] arr_copy = new int[arr.length + 1];
        for(int k = 1; k <= arr.length; k++)
        {
            arr_copy[k] = arr[k - 1];
        }//


        //find the specific subset
        for(int i = 1; i <= arr.length; i++)
        {
            for(int j = 1; j <= S; j++)
            {
                boolean result1 = dp[i - 1][j];
                boolean result2 = false;
                if(arr_copy[i] > j)
                {
                    result2 = dp[i - 1][j];
                }//
                else 
                {
                    result2 = true;

                }// else
                
                dp[i][j] = result1 || result2;

            }// for


        }// for

        //return
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j <= S; j++)
            {
                System.out.print(" " + dp[i][j]);
            }

            System.out.println();
            
        }
        return dp[arr.length][S];

    }//

    public static int minimumDiffrence(int[] arr)
    {
        //Check validity
        if(arr == null)
        {
            return -1;
        }

        //Initialization
        int sum = 0;
        for(int i = 0;i < arr.length; i++)
        {
            sum += arr[i];
        }

        boolean[][] dp = new boolean[arr.length + 1][(sum/2) + 1];
        for(int i = 0;i <= arr.length; i++)
        {
            for(int j = 0; j <= (sum/2);j++)
            {
                if(j == 0)
                {
                    dp[i][j] = true;
                }//
                {
                    dp[i][j] = false;
                }//
            }//
        }//

        int[] arr_copy = new int[arr.length + 1];
        for(int k = 1; k <= arr.length; k++)
        {
            arr_copy[k] = arr[k - 1];
        }


        //Find a minimum difference.
        for(int i = 1; i <= arr.length; i++)
        {
            for(int j = 1; j <= (sum/2); j++)
            {
                boolean result1 = dp[i - 1][j];
                boolean result2 = false;
                if(arr_copy[i] > j)
                {
                    result2 = dp[i - 1][j];
                }
                else
                {
                    result2 = dp[i - 1][j - arr_copy[i]];
                }//


                dp[i][j] = result1 || result2;
            }//

        }//
        
        //update
        int result = Integer.MIN_VALUE;
        if(dp[arr.length][sum / 2] == false)
        {
            result = -1;
        }
        else 
        {
            int i = arr.length, j = (sum / 2);
            int subsetSum = 0;
            while(i != 0)
            {
                if(dp[i][j] != dp[i - 1][j])
                {
                    subsetSum += arr_copy[i];
                }//

                if(dp[i][j] == dp[i - 1][j])
                {
                    i--;
                }
                else 
                {
                    i--;
                    j -= arr_copy[i];
                }//
            
            }//
            

            result = Math.abs((sum - subsetSum) - subsetSum);
        }//
        

        //return
        return result;
        
    }//

    public static void main(String[] args)
    {

        //
        System.out.println("Recursive");
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int result = DP.knapsack(profits, weights, 4, 7);
        System.out.println("result: " + result);
        result = DP.knapsack(profits, weights, 4, 6);
        System.out.println("result: " + result);
        System.out.println("");

        //
        System.out.println("\n DP");
        profits = new int[]{1, 6, 10, 16};
        weights = new int[]{1, 2, 3, 5};
        result = DP.knapsackDP(profits, weights, 4, 7);
        System.out.println("result: " + result);
        result = DP.knapsackDP(profits, weights, 4, 6);
        System.out.println("result: " + result);
        System.out.println(""); 

        //
        System.out.println("\n Route Dispaly");
        profits = new int[]{1, 6, 10, 16};
        weights = new int[]{1, 2, 3, 5};
        List<Integer> resultt = DP.knapsackDPRouteShow(profits, weights, 4, 7);
        System.out.println("result: " + resultt);
        resultt = DP.knapsackDPRouteShow(profits, weights, 4, 6);
        System.out.println("result(index start from 1 to length): " + resultt);
        System.out.println("");

        //
        System.out.println("\n Partition sum");
        System.out.println(DP.sumPartitionDP(new int[] {1, 2, 3, 4}, 10));
        System.out.println(DP.sumPartitionDP(new int[] {1, 1, 3, 4, 7}, 16));
        System.out.println(DP.sumPartitionDP(new int[] {2, 3, 4, 6}, 15));

        //
        System.out.println("\n Same sum");
        System.out.println(DP.sumEqualSDP(new int[] {1, 2, 3, 7 }, 5));
        System.out.println(DP.sumEqualSDP(new int[] {1, 2, 7, 1, 5 }, 15));
        System.out.println(DP.sumEqualSDP(new int[] {1, 3, 4, 8}, 13));

        //
        System.out.println("\n MInimum difference");
        System.out.println(DP.minimumDiffrence(new int[] {1, 2, 3, 9}));
        System.out.println(DP.minimumDiffrence(new int[] {1, 2, 7, 1, 5}));
        System.out.println(DP.minimumDiffrence(new int[]{1, 3, 100, 4}));

    }// main


    
}//Class