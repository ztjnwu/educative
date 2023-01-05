import java.util.*;

public class TwoPointers {

    public static List<List<Integer>> findPairs(int[] arr, int targetSum)
    {
        //Base Check
        if(arr == null)
        {
            return null;
        }//

        //Initialization
        //r in [l + 1, arr.length - 1]
        int l = 0, r = arr.length - 1;
        List<List<Integer>> result = new ArrayList<>();

        //Find a pair with a smaller sum
        while(l < r)
        {
            //Form a new pair
            if(arr[l] + arr[r] == targetSum)
            {
                //Add the pair [arr[l], arr[r]] to the set of result
                result.add(Arrays.asList(arr[l], arr[r]));
            }

            
            //Update the index
            if(arr[l] + arr[r] == targetSum) // arr[l] + arr[r - 1] < targetsum, arr[l] + arr[r - 2] < targetsum, .....arr[l + 1] + arr[r - 2] < targetsum 
            {
                //Find the next pair
                l++;
                //Skip duplicate elements
                while(l < r && arr[l] == arr[l - 1])
                {
                    l++;
                }//
                
                //Find the duplicate elements
                r--;
                //Skip duplicate elements
                while(r > l && arr[r] == arr[r + 1])
                {
                    r--;
                }//

            }//
            else if(arr[l] + arr[r] > targetSum)
            {
                r--; //l still keeps the same position
                //Skip duplicate elements
                while(r > l && arr[r] == arr[r + 1])
                {
                    r--;
                }//

            }
            else if(arr[l] + arr[r] < targetSum)
            {
                l++; //r still keeps the same position
                //Skip duplicate elements
                while(l < r && arr[l] == arr[l - 1])
                {
                    l++;
                }//

            }//
            
        }//while

        //return
        return result;
    }


    public static List<List<Integer>> findTriplets(int[] arr, int targetSum)
    {
        //Base Check
        if(arr == null)
        {
            return null;
        }//

        //Init
        List<List<Integer>> result = new ArrayList<>();

        //Sort the array
        Arrays.sort(arr);

        //Find a triplet
        for(int i = 0; i <= arr.length - 3; i++)
        {
            if(i == 0 || (i > 0 && arr[i] != arr[i - 1]))
            {
                int l = i + 1, r = arr.length - 1;
                while(r > l)
                {
                    //Form a new triplet with the requirement
                    if(arr[i] + arr[l] + arr[r] == targetSum)
                    {
                        result.add(Arrays.asList(arr[i], arr[l], arr[r]));
                    }
                    
                    //update index
                    if(arr[i] + arr[l] + arr[r] == targetSum)
                    {
                        
                        l++;
                        while(l < r  && arr[l] == arr[l - 1])
                        {
                            l++;
                        }//

                        r--;
                        while(r > l && arr[r] == arr[r + 1])
                        {
                            r--;
                        }//
                    }
                    else if(arr[i] + arr[l] + arr[r] > targetSum)
                    { 
                        r--;
                        while(r > l && arr[r] == arr[r + 1])
                        {
                            r--;
                        }//
                        
                    }
                    else 
                    {
                        l++;
                        //Skip duplicate elements
                        while(l < r && arr[l] == arr[l - 1])
                        {
                            l++;
                        }//

                    }//

                }// while

            }//

        }// for

        //return
        return result;
    }//


    public static List<Integer> removeDuplicate(int[] arr)
    {
        //Chcek Validity
        if(arr == null)
        {
            return null;
        }// if

        //Initialization
        //arr must be sorted to prepare for removing duplicate elements
        Arrays.sort(arr);

        //l points to the last elements of the subarray of unique elements
        //r points to the current element
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
    
        //Return
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i <= l; i++)
        {
            result.add(arr[i]);
        }

        return result;
    }//
     

    public static List<Integer> removeCharacter(int[] arr, int key)
    {
        //Check Validity
        if(arr == null)
        {
            return null;
        }

        //Initialization
        //l points to the last element of the subarray without the keys
        //r points to current element
        int l = -1, r = 0;
        
        //Remove
        while(r < arr.length)
        {
            if(arr[r] != key)
            {
                l++;
                arr[l] = arr[r];
            }//
            
            //update r
            r++;
        }//

        //Return
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i <= l; i++)
        {
            result.add(arr[i]);
        }//

        return result;
    }//


    public static List<Integer> squareArray(int[] arr)
    {
        //Base check
        if(arr == null)
        {
            return null;
        }//

        //Init
        List<Integer> result = new ArrayList<>();
        int l = 0, r = arr.length - 1;

        //Square
        while(l <= r)
        {
            if(arr[l] * arr[l] > arr[r] * arr[r])
            {
                result.add(0, arr[l] * arr[l]);
                l++;
            }// if
            else 
            {
                result.add(0, arr[r] * arr[r]);
                r--;
            }// else

        }//

        //return
        return result;
    }


    public static List<Integer> findTripletMinimumDifference(int[] arr, int targetSum)
    {
        //Base check
        if(arr == null)
        {
            return null;
        }//

        //Initailization
        List<Integer> result = new ArrayList<>();

        //Sort the array
        Arrays.sort(arr);

        //Find the minimum differnce
        int minimum = Integer.MAX_VALUE;
        for(int i = 0; i <= arr.length - 3; i++)
        {
            if(i == 0 || i > 0 && arr[i] != arr[i - 1])
            {
                int l = i + 1, r = arr.length -1;
                while(r > l)
                {
                    //Form a new triplet
                    if(Math.abs(arr[i] + arr[l] + arr[r] - targetSum) < minimum)
                    {
                        minimum = Math.abs(arr[i] + arr[l] + arr[r] - targetSum);
                        result.clear();
                        result.add(arr[i]);
                        result.add(arr[l]);
                        result.add(arr[r]);
                    }// 

                    //Update index
                    if(arr[i] + arr[l] + arr[r] - targetSum == 0)
                    {
                        l++;
                        while(r > l && arr[l] != arr[l - 1])
                        {
                            l++;
                        }

                        r--;
                        while(r > l && arr[r] != arr[r + 1])
                        {
                            r--;
                        }
                    }//
                    else if(arr[i] + arr[l] + arr[r] - targetSum > 0)
                    {
                        r--;
                        while(r > l && arr[r] != arr[r + 1])
                        {
                            r--;
                        }
                    }
                    else 
                    {
                        l++;
                        while(r > l && arr[l] != arr[l - 1])
                        {
                            l++;
                        }
                    }//
                }//while
            }// if
        }//for

        //return
        return result;
        
    }


    public static List<List<Integer>> findTripletSumLessThanTarget(int[] arr, int targetSum)
    {
        //Base check
        if(arr == null)
        {
            return null;
        }

        //Init
        List<List<Integer>> result = new ArrayList<>();

        //Sort
        Arrays.sort(arr);

        //Find a triplet
        for(int i = 0; i <= arr.length - 3; i++)
        {
            if(i == 0 || arr[i] != arr[i - 1]) //Avoid duplicate elements from left to right
            {
                int l = i + 1, r = arr.length - 1;
                while(r > l)
                {
                    //For new triplets
                    if(arr[i] + arr[l] + arr[r] < targetSum)
                    {
                        for(int k = r; k > l; k--)
                        {
                            if((k == r) || (arr[k] != arr[k + 1])) // avoid duplicate elements from right to left
                            {
                                result.add(Arrays.asList(arr[i], arr[l], arr[k]));
                            }//
                        }//

                    }//

                    //Update indices
                    if(arr[i] + arr[l] + arr[r] < targetSum)
                    {
                        l++;
                        while(l < r && arr[l] == arr[l - 1])
                        {
                            l++;
                        }// 
                    }//
                    else // arr[i] + arr[l] + arr[r] == targetSum
                    {
                        r--;
                        while(r > l && arr[r] == arr[r + 1])
                        {
                            r--;
                        }//
                    }
                   
                }// while

            }// if

        }//for

        //return
        return result;

    }


    public static List<List<Integer>> findTripletProductLessThanTarget(int[] arr, int targetProduct)
    {
        //Base check
        if(arr == null)
        {
            return null;
        }//
        
        //Initialization
        List<List<Integer>> result = new ArrayList<>();

        //Find all of the subarrays whose products are less than target
        int winS = 0, winE = 0;
        int product = arr[0];
        while(winE < arr.length)
        {
            //For a new subarray
            if(product < targetProduct)
            {
                for(int i = winS; i <= winE; i++)
                {
                    List<Integer> temp = new ArrayList<>();
                    for(int j = i; j <= winE; j++)
                    {
                        temp.add(arr[j]);           
                    }
                    result.add(temp);
                }// for
                
            }//

            //Update indices
            if(product < targetProduct)
            {
                winE++;
                if(winE <= arr.length - 1)
                {
                    product *= arr[winE];
                }//
            
            }//
            else  //product >= targetProduct
            {
                winS++;
                if(winS <= winE)
                {
                    product /= arr[winS - 1];
                }//
            }//

        }// while

        //return
        return result; 
    }// 


    public static boolean basicSubarrayProductLessThanTarget(int arr[], int target)
    {
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


    public static List<Integer> basicSortThreeElements(int arr[])
    {
        //Base Check
        if(arr == null || arr.length <= 0)
        {
            return null;
        }

        //Init
        List<Integer> result = new ArrayList<>();

        //Sort
        int l = 0, r = arr.length - 1;
        int i = 0;
        while(i <= r)
        {   
            if(arr[i] == 0)
            {
                int temp = arr[i];
                arr[i] = arr[l];
                arr[l] = temp; 
                
                //update l and i
                l++;
                i++; //arr[l] have to be 0 or 1, not 2 
            }//
            else if(arr[i] == 2)
            {
                int temp = arr[i];
                arr[i] = arr[r];
                arr[r] = temp;
                
                //update r
                r--;
            }//
            else //arr[i] == 1
            {
                //update i
                i++;
            }//
        }

        //return
        for(i = 0; i < arr.length; i++)
        {
            result.add(arr[i]);
        }

        return result;
    }


    public static List<List<Integer>> findQuadruple(int arr[], int targetSum)
    {
        //Base Check
        if(arr == null)
        {
            return null;
        }

        //Init
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);

        //Search for a quadraple
        for(int i = 0; i <= arr.length - 4; i++)
        {
            if(i == 0 || arr[i] != arr[i - 1])
            {
                for(int j = i + 1; j < arr.length - 3; j++)
                {
                    int l = j + 1, r = arr.length - 1;
                    while(l < r)
                    {
                        //Form a quadraple 
                        if(arr[i] + arr[j] + arr[l] + arr[r] == targetSum)
                        {
                            result.add(Arrays.asList(arr[i], arr[j], arr[l], arr[r]));
                        }//

                        //Update index
                        if(arr[i] + arr[j] + arr[l] + arr[r] == targetSum)
                        {
                            l++;
                            while(l < r && arr[l] == arr[l + 1])
                            {
                                l++;
                            }//
                            
                            r--;
                            while(r > l && arr[r] == arr[r - 1])
                            {
                                r--;
                            }//

                        }// if
                        else if(arr[i] + arr[j] + arr[l] + arr[r] < targetSum)
                        {
                            l++;
                            while(l < r && arr[l] == arr[l - 1])
                            {
                                l++;
                            }//

                        }
                        else 
                        {
                            r--;
                            while(r > l && arr[r] == arr[r - 1])
                            {
                                r--;
                            }//

                        }//
                        
                    }// while

                }// for
            }// if
        }// for


        //return
        return result;
    }//


    public static boolean checkBackspace(String str1, String str2)
    {
        //Base Check
        if(str1 == null || str2 == null)
        {
            return false;
        }// 

        //Init
        boolean result = false;

        //Check backspace
        int p1 = str1.length() -1, p2 = str2.length() - 1;
        int count1 = 0, count2 = 0;
        while(p1 != -1)
        {
            //judegment
            if(str1.charAt(p1) != '#' && count1 == 0) 
            {
                break;
            }// if

            //update index and count1
            if(str1.charAt(p1) == '#')
            {
                count1++;
            }
            else 
            {
                count1--;
            }

            p1--;

        }// while

        while(p2 != -1)
        {
            //Judegment
            if(str2.charAt(p2) != '#' && count2 == 0)
            {
                break;
            }
            
            //Update index and count2
            if(str2.charAt(p2) == '#')
            {
                count2++;
            }
            else 
            {
                count2--;
            }

            p2--;

        }// while

        while(p1 != -1 && p2 != -1)
        {
            //Judgement
            if(str1.charAt(p1) != str2.charAt(p2))
            {
                break;
            }// if

            //Update p1 
            p1--;
            while(p1 != -1)
            {
                //judegment
                if(str1.charAt(p1) != '#' && count1 == 0) 
                {
                    break;
                }

                //Update index and count1
                if(str1.charAt(p1) == '#')
                {
                    count1++;
                }
                else 
                {
                    count1--;
                }

                p1--;

            }// while


            //Update p2
            p2--;
            while(p2 != -1)
            {
                //Judgement
                if(str2.charAt(p2) != '#' && count2 == 0)
                {
                    break;
                }
                
                //Update index and count2
                if(str2.charAt(p2) == '#')
                {
                    count2++;
                }
                else 
                {
                    count2--;
                }

                p2--;

            }// while

        }//

        //Update result
        if(p1 == -1)
        {
            if(p2 == -1)
            {
                result = true;
            }
            else 
            {
                result = false;
            }
        }
        else
        {
            result = false;
        }

        //return
        return result;
    }

    public static void main(String[] args){
    
        System.out.println("Find a pair with a targeted sum");
        System.out.println(TwoPointers.findPairs(new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6}, 6));
        System.out.println(TwoPointers.findPairs(new int[] { 2, 5, 9, 11 }, 11));
        System.out.println();


        System.out.println("Remove all of duplicate elements");
        System.out.println(TwoPointers.removeDuplicate(new int[] {2, 6, 3, 3, 6, 9, 9}));
        System.out.println(TwoPointers.removeDuplicate(new int[] {2, 2, 2, 11}));
        System.out.println(TwoPointers.removeDuplicate(new int[] {1, 6, 5, 6, 6, 9, 9}));
        System.out.println(TwoPointers.removeDuplicate(new int[] {15, 2, 2, 11, 11, 3}));
        System.out.println();
        

        System.out.println("Remove targeted number key ");
        System.out.println(TwoPointers.removeCharacter(new int[] { 3, 3, 3, 6, 3, 10, 9, 3 }, 3));
        System.out.println(TwoPointers.removeCharacter(new int[] { 2, 11, 2, 2, 1 }, 2));
        System.out.println(TwoPointers.removeCharacter(new int[] {1, 5, 5, 6, 6, 9, 9}, 6));
        System.out.println(TwoPointers.removeCharacter(new int[] {2, 2, 2, 11, 11, 3}, 11));
        System.out.println();

        System.out.println("Square elements");
        System.out.println(TwoPointers.squareArray(new int[] {-2, -1, 1, 2, 3}));
        System.out.println(TwoPointers.squareArray(new int[] {-5, -4, -3, -2, -1, 0, 1, 1, 2}));
        System.out.println();
        

        System.out.println("Find a triplet");
        System.out.println(TwoPointers.findTriplets(new int[] { -3, -2, -1, 0, 1, 1, 2}, 0));
        System.out.println(TwoPointers.findTriplets(new int[] { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6}, 0));
        System.out.println();

        System.out.println("the smallest differnce ");
        System.out.println(TwoPointers.findTripletMinimumDifference(new int[] { -2, 0, 1, 2 }, 2));
        System.out.println(TwoPointers.findTripletMinimumDifference(new int[] { -3, -1, 1, 2 }, 1));
        System.out.println(TwoPointers.findTripletMinimumDifference(new int[] { 1, 0, 1, 1 }, 100));
        System.out.println();
        System.out.println();

        System.out.println("Sum < Target ");
        System.out.println(TwoPointers.findTripletSumLessThanTarget(new int[] { -1, 0, 2, 3, 3}, 3));
        System.out.println(TwoPointers.findTripletSumLessThanTarget(new int[] { -1, 4, 2, 1, 3, 2}, 5));
        System.out.println();

        System.out.println("Product < Target ");
        System.out.println(TwoPointers.findTripletProductLessThanTarget(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(TwoPointers.findTripletProductLessThanTarget(new int[] { 8, 2, 6, 5 }, 50));
        System.out.println();

        System.out.println("Product < Target ");
        System.out.println(TwoPointers.basicSubarrayProductLessThanTarget(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(TwoPointers.basicSubarrayProductLessThanTarget(new int[] { 8, 2, 6, 5 }, 50));
        System.out.println();


        System.out.println("Dutch color ");
        System.out.println(TwoPointers.basicSortThreeElements(new int[] { 1, 0, 2, 1, 0 }));
        System.out.println(TwoPointers.basicSortThreeElements(new int[] { 2, 2, 0, 1, 2, 0 }));
        System.out.println();

        System.out.println("QuadralTrip ");
        System.out.println(TwoPointers.findQuadruple(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
        System.out.println(TwoPointers.findQuadruple(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
        System.out.println();

        System.out.println("Backspace ");
        System.out.println(TwoPointers.checkBackspace("xy#z", "xzz#"));
        System.out.println(TwoPointers.checkBackspace("xy#z", "xyz#"));
        System.out.println(TwoPointers.checkBackspace("xp#", "xyz##"));    
        System.out.println(TwoPointers.checkBackspace("dxywrrmp", "dd#xywrrmu#p"));
        System.out.println();






    }
}//Class
