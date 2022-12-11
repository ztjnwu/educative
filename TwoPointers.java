import java.util.*;

public class TwoPointers {
    
    //itereate through all the subsets of 2 elements that are subject to given factors.
    public static int[] basicDoubleSubsetTarget(int targetSum, int[] arr){
        int pL = 0, pR = arr.length - 1;
        int[] result = new int[2];
        while(pL < pR){
            int curSum = arr[pL] + arr[pR];

            if(curSum < targetSum){
                pL++;
            }
            else if(curSum > targetSum){
                pR--;
            }
            else {// curSum == targetSum
                result[0] = arr[pL];
                result[1] = arr[pR];
                pL++;
                pR--;
                System.out.print("["+ result[0] + ", " + result[1] + "]");
            }
        }

        //return 
        return result;
    }
    
    public static int[] basicDoubleSubsetTargetOPT(int targetSum, int[] arr){
        int pl = 0, pr = arr.length - 1;
        int[] result = new int[2];
        while(pl < pr){
            if(arr[pr] + arr[pl] == targetSum){
                System.out.println("[" + arr[pl] + "," + arr[pr] + "]");
                result[0] = arr[pl];
                result[1] = arr[pr];
                pl++;
                pr--;
            }
            else if(arr[pl] + arr[pr] < targetSum){
                pl++;
            }
            else {
                pr--;
            }
            
        }//while

        //return
        return result;
    }

    public static int basicRemoveDuplicate(int[] arr){
        int pL = 0, pR = 1;
        while(pR < arr.length){
            if(arr[pR] == arr[pL]){
                pR++;
            }
            else {
                pL++;
                arr[pL] = arr[pR];
                pR++;
            }
        }
    
        //return
        for(int i = 0; i <= pL; i++){
            System.out.print(" "+ arr[i]);
        }
        
        return pL;
    }
     
    public static int basicRemoveDuplicateCharacter(int K, int[] arr){
        //base check
        if(arr.length < 0 || arr == null){
            return -1;
        }

        //initialization
        int pL = -1, pR = 0;
        while(pR < arr.length){
            if(arr[pR] == K){
                pR++;
            }
            else {
                pL++;
                arr[pL] = arr[pR];
                pR++;
            }
        }

        //return
        for(int i = 0; i <= pL; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
        return pL;
    }

    public static int basicSquareArray(int[] arr){
        int pL = 0, pR = 1;
        arr[0] = arr[0] * arr[0];

        while(pR < arr.length){
            int curSquare = arr[pR] * arr[pR];
            if(curSquare >= arr[pL]){
                arr[pR] = curSquare;
            }
            else {
                int i = pR - 1;
                while(i >= 0 &&  arr[i] > curSquare){
                    arr[i + 1] = arr[i];
                    i--;
                }
                arr[i + 1] = curSquare;
            }
            pL++;
            pR++;
        } 

        //return
        for(int i = 0; i < arr.length;i++){
            System.out.print(" " + arr[i] + " ");
        }
        return 1;
    }

    public static int[] basicSquareOptimization(int[] arr){
        //base check
        if(arr.length < 0){
            return null;
        }

        //initialization
        int[] result = new int[arr.length];
        int index = arr.length;
        int pL = 0, pR = arr.length - 1;
        while(pL < pR){
            index--;
            if(arr[pR] * arr[pR] > arr[pL] * arr[pL]){
                result[index] = arr[pR] * arr[pR];
                pR--;
            }
            else {
                result[index] = arr[pL] * arr[pL];
                pL++;
            }
        }
        index--; 
        result[index] = arr[pL];

        //return
        for(int i = 0; i < arr.length;i++){
            System.out.print(" " + result[i] + " ");
        }
        return result;
    }
    
    public static List<List<Integer>> basicTripleSubsetZero(int[] arr){
        //base check
        if(arr.length == 0 || arr == null){
            return null;
        }

        //sort arr
        Arrays.sort(arr);
        
        //detect the triple subset
        List<List<Integer>> result = new ArrayList<>();
        searchSum(arr, arr[0], 1, result);
        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i - 1]){
                searchSum(arr, arr[i], i + 1, result);
            }
        }
        
        //return
        return result;
    }

    public static boolean searchSum(int[] arr, int sum, int start, List<List<Integer>> result){
        //base check
        if(arr.length <= 0){
            return false;
        }

        //loop
        int pL, pR;
        pL = start;
        pR = arr.length - 1;
        while(pL < pR){
            if(sum + arr[pL] + arr[pR] == 0){
                result.add(Arrays.asList(arr[pL], arr[pR], sum));
                while(pL < pR && arr[pL] == arr[pL + 1]){
                    pL++;
                }
                if(pL < pR){
                    pL++;
                }
                while(pL < pR && arr[pR] == arr[pR - 1]){
                    pR--;
                }
                if(pL < pR){
                    pR--;
                }
            }
            else if(sum + arr[pL] + arr[pR] < 0){
                pL++;
            }
            else {//sum + arr[pL] + arr[pR] > 0
                pR--;
            }
        }
        
        //return
        return true;
    }

    public static boolean basicTripleSubsetTarget(int arr[], int target){
        //base check
        if(arr == null){
            return false;
        }
         
        //Sort
        Arrays.sort(arr);

        //loop
        int pL = 1, pR = arr.length - 1;
        int minDistance = Integer.MAX_VALUE;
        int minTripleSum = Integer.MAX_VALUE;
        for(int i = 0; i <= arr.length - 3; i++){
            pL = i + 1;
            pR = arr.length - 1;
            while(pL < pR){
                int curDiff = arr[i] + arr[pL] + arr[pR] - target;
                if(minDistance > Math.abs(curDiff)){
                    minDistance = Math.abs(curDiff);
                    minTripleSum = arr[i] + arr[pL] + arr[pR];
                }

                //update
                if(curDiff > 0){
                    pR--;
                }
                else if(curDiff < 0){
                    pL++;
                }
                else {//curDiff == 0
                    pR--;
                    pL++;
                }
            }
            
        }

        //return
        System.out.println("minDiff: " + minDistance + " element: "+ minTripleSum);
        return true;
    }

    public static boolean basicTripleSubsetLessThanTarget(int arr[], int target){
        //base check
        if(arr == null || arr.length < 0){
            return false;
        }

        //Sort
        Arrays.sort(arr);

        //Loop
        int pL = 1, pR = arr.length - 1;
        int num = 0;
        for(int i = 0; i <= arr.length - 3; i++){
            pL = i + 1;
            pR = arr.length - 1;
            while(pL < pR){
                if(arr[i] + arr[pL] + arr[pR] < target){
                    num += pR - pL;
                    pL++;
                }
                else if(arr[i] + arr[pL] + arr[pR] >= target)
                {
                    pR--;
                }
            }
        }

        //return
        System.out.println("num: " + num);
        return true;
    }

    public static boolean basicSubarrayProductLessThanTarget(int arr[], int target){
        //base check
        if(arr == null || arr.length == 0){
            return false;
        }

        //loop
        int winS = 0, winE = 0;
        int winProduct = arr[winS];
        List<Integer> tempList = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        while(winE < arr.length){
            //filter out 
            while(winS <= winE && (winProduct >= target)){
                winS++;
                winProduct /= arr[winS - 1];
            }

            //Form result array
            for(int i = winS; i <= winE; i++){
                for(int j = i; j <= winE; j++){
                    System.out.print(" " + arr[j]);
                    tempList.add(arr[j]);
                }
                result.add(new ArrayList<>(tempList));
                tempList.clear();
            }

            //update
            winE++;
            if(winE < arr.length){
                winProduct *= arr[winE];
            } 
        }

        //return
        System.out.println(" "+ result.toString());
        return true;
    }

    public static boolean basicSortThreeElements(int arr[]){
        //base check
        if(arr == null || arr.length <= 0){
            return false;
        }

        //Loop
        int pL = -1, pR = arr.length;
        int i = 0;

        while(i < pR){// make sure the index of i is less than the right bound of the array
            System.out.println(" " + arr[i]);
            if(arr[i] == 0){
                arr[i] = 1;
                arr[pL + 1] = 0;
                pL++;
                i++;
            }
            else if(arr[i] == 2){
                arr[i] = arr[pR - 1];
                arr[pR - 1] = 2;
                pR--;
            }
            else {// arr[i] == 1
                i++;
            }
        }

        //return
        System.out.println("result: " + Arrays.toString(arr));
        return true;
    }


    public static void main(String[] args){
        System.out.println("Get Sum");
        int[] result = TwoPointers.basicDoubleSubsetTarget(6, new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6});
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = TwoPointers.basicDoubleSubsetTarget(11, new int[] { 2, 5, 9, 11 });
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        System.out.println();


        System.out.println("Get Sum OPT");
        int[] result1 = TwoPointers.basicDoubleSubsetTargetOPT(6, new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6});
        System.out.println("Pair with target sum: [" + result1[0] + ", " + result1[1] + "]");
        result = TwoPointers.basicDoubleSubsetTargetOPT(11, new int[] { 2, 5, 9, 11 });
        System.out.println("Pair with target sum: [" + result1[0] + ", " + result1[1] + "]");
        System.out.println();

        System.out.println("Remove duplicate elements");
        System.out.println(" Last index:" + TwoPointers.basicRemoveDuplicate(new int[] {2, 3, 3, 3, 6, 9, 9}));
        System.out.println(" Last index:" + TwoPointers.basicRemoveDuplicate(new int[] {2, 2, 2, 11}));
        System.out.println();
        
        System.out.println("Remove duplicate number K ");
        System.out.println(" Last index:" + TwoPointers.basicRemoveDuplicateCharacter(3, new int[] { 3, 2, 3, 6, 3, 10, 9, 3 }));
        System.out.println(" Last index:" + TwoPointers.basicRemoveDuplicateCharacter(2, new int[] { 2, 11, 2, 2, 1 }));
        System.out.println();

        System.out.println("Square elements");
        System.out.println(" Last index:" + TwoPointers.basicSquareArray(new int[] {-2, -1, 1, 2, 3}));
        System.out.println(" Last index:" + TwoPointers.basicSquareArray(new int[] {-3, -1, 1, 1, 2}));
        System.out.println();

        System.out.println("Square elements OPTIMIZATION");
        System.out.println(" Last index:" + TwoPointers.basicSquareOptimization(new int[] {-2, -1, 1, 2, 3}));
        System.out.println(" Last index:" + TwoPointers.basicSquareOptimization(new int[] {-3, -1, 1, 1, 2}));
        System.out.println();
        
        System.out.println("Triple Subsets");
        System.out.println(TwoPointers.basicTripleSubsetZero(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        System.out.println(TwoPointers.basicTripleSubsetZero(new int[] { -5, 2, -1, -2, 3 }));
        System.out.println();

        System.out.println("Closet Distance ");
        System.out.println(TwoPointers.basicTripleSubsetTarget(new int[] { -2, 0, 1, 2 }, 2));
        System.out.println(TwoPointers.basicTripleSubsetTarget(new int[] { -3, -1, 1, 2 }, 1));
        System.out.println(TwoPointers.basicTripleSubsetTarget(new int[] { 1, 0, 1, 1 }, 100));
        System.out.println();

        System.out.println("Sum < Target ");
        System.out.println(TwoPointers.basicTripleSubsetLessThanTarget(new int[] { -1, 0, 2, 3 }, 3));
        System.out.println(TwoPointers.basicTripleSubsetLessThanTarget(new int[] { -1, 4, 2, 1, 3 }, 5));
        System.out.println();
        
        System.out.println("Product < Target ");
        System.out.println(TwoPointers.basicSubarrayProductLessThanTarget(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(TwoPointers.basicSubarrayProductLessThanTarget(new int[] { 8, 2, 6, 5 }, 50));
        System.out.println();

        System.out.println("Sort 3 types of elements ");
        System.out.println(TwoPointers.basicSortThreeElements(new int[] { 1, 0, 2, 1, 0 }));
        System.out.println(TwoPointers.basicSortThreeElements(new int[] { 2, 2, 0, 1, 2, 0 }));
    }
}//Class