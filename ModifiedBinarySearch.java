import java.util.*;


public class ModifiedBinarySearch {
    static int search(int[] arr, int key)
    {
        if(arr == null)
        {
            return -1;
        }

        //
        int start = 0, end = arr.length - 1;
        boolean flag = false; //true stands for ascending, while false stands for descending
        if(arr[end] > arr[start])
        {
            flag = true;
        }
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
    

    public static void main(String[] args)
    {
        System.out.println("find the index of a given number");
        System.out.println(ModifiedBinarySearch.search(new int[] { 4, 6, 10 }, 10));
        System.out.println(ModifiedBinarySearch.search(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(ModifiedBinarySearch.search(new int[] { 10, 6, 4 }, 10));
        System.out.println(ModifiedBinarySearch.search(new int[] { 10, 6, 4 }, 4));
        System.out.println(ModifiedBinarySearch.search(new int[] { 10, 6, 4 , 1}, 1));
    }

    
}
