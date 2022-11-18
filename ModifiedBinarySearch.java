import java.util.*;


public class ModifiedBinarySearch {
    public static int searchNumber(int[] arr, int key)
    {
        if(arr == null)
        {
            return -1;
        }

        //Initialization
        int start = 0, end = arr.length - 1;
        boolean flag = false; //true stands for ascending, while false stands for descending
        if(arr[end] > arr[start])
        {
            flag = true;
        }

        //Binary Search 
        int result = -1;
        while(start <= end)
        {
            int middle = start + (end - start) / 2; // must compute the value of middle in this way, otherwise middle = (start + end ) / 2 might lead to overflow.
            if(arr[middle] < key)
            {
                if(flag == true)
                {
                    start = middle + 1;
                }
                else 
                {
                    end = middle - 1;
                }
            }// if
            else if(arr[middle] > key)
            {
                if(flag == true)
                {
                    end = middle - 1;
                }
                else 
                {
                    start = middle + 1;
                }
            } 
            else 
            {
                result = middle;
                break;
            }// else

        }// while


        //return
        return result;
    }
    
    public static int searchCeiling(int[] arr, int key)
    {
        if(arr == null)
        {
            return -1;
        }

        //Initialization
        int start = 0, end = arr.length - 1;
        int middle = start + (end - start) / 2;
        
        //Search the given key
        int result = -1;
        while(start <= end)
        {
            if(key == arr[middle])
            {
                result = middle;
                break;
            }//
            else if(key < arr[middle])
            {
                end = middle - 1;
                middle = start + (end - start) / 2;
            }//
            else 
            {
                start = middle + 1;
                middle = start + (end - start) / 2;
            }//
            
        }//
        
        //update result
        //Special condition 1
        if(start == arr.length)
        {
            result = -1;
        }

        //Special condition 2
        if(end == -1)
        {
            result = 0;
        }

        //return
        return result;

    }//

    public static void main(String[] args)
    {
        //
        System.out.println("find the index of a given number");
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 4, 6, 10 }, 10));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 10, 6, 4 }, 10));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 10, 6, 4 }, 4));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 10, 6, 4 , 1}, 1));

        //
        System.out.println("find the ceiling of a given number");
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 4, 6, 10 }, 6));
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 1, 3, 8, 10, 15  }, 15));
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 4, 6, 10 }, 17));
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 4, 6, 10  }, -1));

    }

    
}
