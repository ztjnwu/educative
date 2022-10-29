import java.util.*;

public class DP {
    public static int fabonaci(int n){
        if(n < 2){
            return n;
        }
        else {
            return fabonaci(n - 1) + fabonaci(n - 2);
        }
    }
    
    public static int fabonacciMemorization(int n){
        return -1;

    }

    public static int fabonacciTabulation(int n){
        if(n <=1 ){
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        //return
        return dp[n];
    }
    
    public static int knapsack(int[] profits, int[] weights, int number, int capacity){
        //base checkes
        if(number == 0 || capacity <= 0){
            return 0;
        }
        
        //recursive calls
        int profit1 = knapsack(profits, weights, number - 1, capacity);
        int profit2 = 0;
        if(weights[number - 1] <= capacity){
            profit2 = profits[number - 1] + knapsack(profits, weights, number - 1, capacity - weights[number - 1]);
        }
        else {
            profit2 = profit1;
        }
        
        //return
        return Math.max(profit1, profit2);
    }

    public static int knapsnackTabulation(int[] profits, int[] weights, int number, int capacity){
        //base check
        if(number == 0 || capacity <= 0){
            return 0;
        }
        
        //initialization
        int[][] dp = new int[number + 1][capacity + 1];
        for(int i = 0; i<= number; i++){
            for(int j = 1; j <= capacity;  j++){
                dp[i][j] = 0;
            }
        }
       
        //DP
        for(int i = 1; i <= number; i++){
            for(int j = 1; j <= capacity; j++){
                int profit1 = dp[i - 1][j];
                int profit2 = 0;
                if(weights[i - 1] <= j){
                    profit2 = profits[i - 1] + dp[i - 1][j - weights[i - 1]];
                }
                else {
                    profit2 = profit1;
                }
                dp[i][j] = Math.max(profit1, profit2);
            }    
        }
        
        //return 
        return dp[number][capacity];
    }

    public static int knapsnackTabulationDisplay(int[] profits, int[] weights, int number, int capacity){
        //base check
        if(number == 0 || capacity <= 0){
            return 0;
        }
        
        //initialization
        int[][] dp = new int[number + 1][capacity + 1];
        for(int i = 0; i<= number; i++){
            for(int j = 0; j <= capacity;  j++){
                dp[i][j] = 0;
            }
        }
       
        //DP
        for(int i = 1; i <= number; i++){
            for(int j = 1; j <= capacity; j++){
                int profit1 = dp[i - 1][j];
                int profit2 = 0;
                if(weights[i - 1] <= j){
                    profit2 = profits[i - 1] + dp[i - 1][j - weights[i - 1]];
                }
                else {
                    profit2 = profit1;
                }
                dp[i][j] = Math.max(profit1, profit2);
            }   
        }
        
        List<Integer> sequence_index = new ArrayList<>();
        List<Integer> sequence_weight = new ArrayList<>();
        int i = number, j = capacity;
        while(i != 0 && j != 0){
            if(dp[i][j] == dp[i - 1][j]){
                i--;
            }
            else {
                sequence_index.add(i - 1);
                sequence_weight.add(weights[i - 1]);
                j -= weights[i-1];
                i--;
            }
        }

        //return 
        System.out.println("Sequence Index: " + sequence_index.toString());
        System.out.println("Sequence Weight: " + sequence_weight.toString());
        return dp[number][capacity];
    }

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

    public static boolean sumPartipationDP(int[] arr, int number, int sum){

        //base check
        if(number < 0 || sum < 0){
            return false;
        }

        if(sum == 0){
            return true;
        }
        else if(number == 0){
            return false;
        }

        //loop
        boolean[][] dp = new boolean[number + 1][sum + 1];
        for(int i = 0; i < number + 1; i++){
            dp[i][0] = true;
        }
        for(int j = 1; j < sum + 1; j++){
            dp[0][j] = false;
        }
        
        for(int i = 1; i < number + 1; i++){
            for(int j = 1; j < sum + 1; j++){
                boolean flag1 = dp[i - 1][j];
                boolean flag2 = false;
                if(arr[i - 1] <= j){
                    flag2 = dp[i - 1][j - arr[i - 1]];
                }
                else {
                    flag2 = flag1;
                }
                dp[i][j] = flag1 || flag2;
            }
        }

        //return
        return dp[number][sum];
    }

    public static boolean sumEqualRE(int[] arr, int number, int sum){
        //base chcek
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
        boolean flag1 = sumEqualRE(arr, number - 1, sum);
        boolean flag2 = false;
        if(arr[number - 1] <= sum){
            flag2 = sumEqualRE(arr, number - 1, sum - arr[number - 1]);
        }
        else {
            flag2 = flag1;
        }
        
        //return
        boolean result = flag1 || flag2;
        return result;
    }

    public static boolean sumEqualDP(int[] arr, int number, int sum){
        //base check
        if(number < 0 || sum < 0){
            return false;
        }
        if(sum == 0){
            return true;
        }
        else if(number == 0){
            return false;
        }

        //loop
        boolean[][] dp = new boolean[number + 1][sum + 1];
        for(int i = 0; i < number + 1; i++){
            dp[i][0] = true;
        }
        for(int j = 1 ;j < sum + 1; j++){
            dp[0][j] = false;
        }
        for(int i = 1; i < number + 1; i++){
            for(int j = 1; j < sum + 1; j++){
                boolean flag1 = dp[i - 1][j];
                boolean flag2 = false;
                if(arr[i - 1] <= j){
                    flag2 = dp[i - 1][j - arr[i - 1]];
                }
                else {
                    flag2 = dp[i - 1][j];
                }
                dp[i][j] = flag1 || flag2;
            }
        }

        //return
        return dp[number][sum];
    }


    public static void main(String[] args){
        System.out.println("Fibonacci array");
        int result = DP.fabonaci(9);
        System.out.println("result_recursive: "+ result);
        result = DP.fabonacciTabulation(9);
        System.out.println("result-tabulation: "+ result);
        System.out.println("");

        System.out.println("0-1 problem: Recursive");
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        result = DP.knapsack(profits, weights, 4, 7);
        System.out.println("result: " + result);
        result = DP.knapsack(profits, weights, 4, 6);
        System.out.println("result: " + result);
        System.out.println("");

        System.out.println("0-1 problem: Tabulation");
        int[] profits_dp = {1, 6, 10, 16};
        int[] weights_dp = {1, 2, 3, 5};
        result = DP.knapsnackTabulation(profits_dp, weights_dp, 4, 7);
        System.out.println("result: " + result);
        result = DP.knapsnackTabulation(profits_dp, weights_dp, 4, 6);
        System.out.println("result: " + result);
        System.out.println("");

        System.out.println("0-1 problem: Tabulation Display");
        int[] profits_dp_display = {1, 6, 10, 16};
        int[] weights_dp_display = {1, 2, 3, 5};
        result = DP.knapsnackTabulationDisplay(profits_dp_display, weights_dp_display, 4, 7);
        System.out.println("result: " + result);
        result = DP.knapsnackTabulationDisplay(profits_dp_display, weights_dp_display, 4, 6);
        System.out.println("result: " + result);
        System.out.println("");
        
        //**********************
        System.out.println("0-1 problem Optimization: 3 * (capacity + 1)");
        int[] profits_dp_optimization = {1, 6, 10, 16};
        int[] weights_dp_optimization = {1, 2, 3, 5};
        result = DP.knapsnackTabulationOptimization(profits_dp_optimization, weights_dp_optimization, 4, 7);
        System.out.println("optimization: " + result);
        result = DP.knapsnackTabulationOptimization(profits_dp_optimization, weights_dp_optimization, 4, 6);
        System.out.println("optimization: " + result);
        System.out.println("");
        
        //**********************
        System.out.println("0-1 problem Optimization: Best optimization");
        int[] profits_single = {1, 6, 10, 16};
        int[] weights_single = {1, 2, 3, 5};
        result = DP.knapsnackTabulationOptimization(profits_single, weights_single, 4, 7);
        System.out.println("optimization: " + result);
        result = DP.knapsnackTabulationOptimization(profits_single, weights_single, 4, 6);
        System.out.println("optimization: " + result);
        System.out.println("");
        
        //**********************
        System.out.println("RE: Sum Participation");
        int[] arr = {1, 2, 3, 4};
        int sum = 0;
        boolean result_sum = false;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        if(sum % 2 != 0){
            result_sum = false;
        }
        else {
            result_sum = DP.sumPartipationRE(arr, 4, sum / 2);
            
        }
        System.out.println("result: " + result_sum);

        arr = new int[]{1, 1, 3, 4, 7};
        sum = 0;
        result_sum = false;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        if(sum % 2 != 0){
            result_sum = false;
        }
        else {
            result_sum = DP.sumPartipationRE(arr, 5, sum / 2);
            
        }
        System.out.println("result: " + result_sum);

        arr = new int[]{2, 3, 4, 6};
        sum = 0;
        result_sum = false;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        if(sum % 2 != 0){
            result_sum = false;
        }
        else {
            result_sum = DP.sumPartipationRE(arr, 4, sum / 2);
        }
        System.out.println("result: " + result_sum);
        System.out.println();

        System.out.println("DP: Sum Participation");
        arr = new int[]{1, 2, 3, 4};
        int sum_dp = 0;
        boolean result_dp = false;
        for(int i = 0; i < arr.length; i++){
            sum_dp += arr[i];
        }
        if(sum_dp % 2 != 0){
            result_dp = false;
        }
        else {
            result_dp = DP.sumPartipationDP(arr, 4, sum_dp / 2);
            
        }
        System.out.println("result: " + result_dp);

        arr = new int[]{1, 1, 3, 4, 7};
        sum_dp = 0;
        result_sum = false;
        for(int i = 0; i < arr.length; i++){
            sum_dp += arr[i];
        }
        if(sum_dp % 2 != 0){
            result_dp = false;
        }
        else {
            result_dp = DP.sumPartipationDP(arr, 5, sum_dp / 2);    
        }
        System.out.println("result: " + result_dp);

        arr = new int[]{2, 3, 4, 6};
        sum_dp = 0;
        result_dp = false;
        for(int i = 0; i < arr.length; i++){
            sum_dp += arr[i];
        }
        if(sum_dp % 2 != 0){
            result_dp = false;
        }
        else {
            result_dp = DP.sumPartipationDP(arr, 4, sum_dp / 2);
            
        }
        System.out.println("result: " + result_dp);
        System.out.println();

        //**********************
        System.out.println("RE: Sum Equation");

        arr = new int[]{1, 2, 3, 7};
        result_dp = DP.sumEqualRE(arr, 4, 6);
        System.out.println("result: " + result_dp);

        arr = new int[]{1, 2, 7, 1, 5};
        result_dp = DP.sumEqualRE(arr, 5, 10);    
        System.out.println("result: " + result_dp);

        arr = new int[]{1, 3, 4, 8};
        result_dp = DP.sumEqualRE(arr, 4, 6);
        System.out.println("result: " + result_dp);
        System.out.println();


         //**********************
         System.out.println("DP: Sum Equation");
         arr = new int[]{1, 2, 3, 7};
         result_dp = DP.sumEqualDP(arr, 4, 6);
         System.out.println("result: " + result_dp);
 
         arr = new int[]{1, 2, 7, 1, 5};
         result_dp = DP.sumEqualDP(arr, 5, 10);    
         System.out.println("result: " + result_dp);
 
         arr = new int[]{1, 3, 4, 8};
         result_dp = DP.sumEqualDP(arr, 4, 6);
         System.out.println("result: " + result_dp);
         System.out.println();

    }
}