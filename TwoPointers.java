import java.util.*;

public class TwoPointers {

    public static List<Integer> basicDoubleSubsetTargetOPT(int[] arr, int targetSum)
    {
        //Check Validity
        if(arr == null)
        {
            return null;
        }//

        //Initialization
        int l = 0, r = arr.length - 1;
        List<Integer> result = new ArrayList<>();

        //Find a pair with a smaller sum
        while(l < r)
        {
            if(arr[l] + arr[r] == targetSum)
            {
                result.add(arr[l]);
                result.add(arr[r]);

                l++;
                while(l < r && arr[l] == arr[l - 1])
                {
                    l++;
                }//
               
                r--;
                while(r > l && arr[r] == arr[r - 1])
                {
                    r--;
                }//

            }//
            else if(arr[l] + arr[r] < targetSum)
            {
                l++;
            }
            else 
            {
                r--;
            }
            
        }//while

        //return
        return result;
    }


    public static List<Integer> basicRemoveDuplicate(int[] arr)
    {
        //Chcek Validity
        if(arr == null)
        {
            return null;
        }// if

        //Initialization
        int l = 0, r = 1;

        //Remove duplicate numbers
        while(r < arr.length)
        {
            if(arr[r] == arr[l])
            {
                r++;
            }//
            else 
            {
                //add one element to the taile of the left part with distinct elements
                l++;
                arr[l] = arr[r];

                //update r
                r++;
            }//

        }// while
    
        //return
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i <= l; i++)
        {
            result.add(arr[i]);
        }

        return result;

    }//
     

    public static List<Integer> basicRemoveDuplicateCharacter(int[] arr, int key)
    {
        //Check Validity
        if(arr == null)
        {
            return null;
        }

        //Initialization
        int l = -1, r = 0;
        
        //Remove
        while(r < arr.length)
        {
            if(arr[r] != key)
            {
                l++;
                arr[l] = arr[r];
            }//
            
            r++;
        }//

        //return
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i <= l; i++)
        {
            result.add(arr[i]);
        }//

        return result;

    }//


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
            return false;3,

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
    
        System.out.println("Get Sum OPT");
        List<Integer> result = TwoPointers.basicDoubleSubsetTargetOPT(new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6}, 6);
        System.out.println("Pair with target sum: " + result);
        result = TwoPointers.basicDoubleSubsetTargetOPT(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum:" + result);
        System.out.println();


        System.out.println("Remove duplicate elements");
        System.out.println(TwoPointers.basicRemoveDuplicate(new int[] {2, 3, 3, 3, 6, 9, 9}));
        System.out.println(TwoPointers.basicRemoveDuplicate(new int[] {2, 2, 2, 11}));
        System.out.println(TwoPointers.basicRemoveDuplicate(new int[] {1, 5, 5, 6, 6, 9, 9}));
        System.out.println(TwoPointers.basicRemoveDuplicate(new int[] {2, 2, 2, 11, 11, 3}));
        System.out.println();
        

        System.out.println("Remove duplicate number key ");
        System.out.println(TwoPointers.basicRemoveDuplicateCharacter(new int[] { 3, 3, 3, 6, 3, 10, 9, 3 }, 3));
        System.out.println(TwoPointers.basicRemoveDuplicateCharacter(new int[] { 2, 11, 2, 2, 1 }, 2));
        System.out.println(TwoPointers.basicRemoveDuplicateCharacter(new int[] {1, 5, 5, 6, 6, 9, 9}, 6));
        System.out.println(TwoPointers.basicRemoveDuplicateCharacter(new int[] {2, 2, 2, 11, 11, 3}, 11));
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