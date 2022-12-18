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


    public static char findNextNumber(char[] arr, char key)
    {
        //CHeck Validity
        if(arr == null)
        {
            return '\0';
        }


        //Find the next number
        int start = 0, end = arr.length - 1;
        while(start <= end)
        {
            int mid = start + (end - start) / 2;
            if(key < arr[mid])
            {
                end = mid - 1;
            }//
            else if(key > arr[mid])
            {
                start = mid + 1; 
            }//
            else //key == arr[mid]
            {
                start = mid + 1;
            }//
        }//

        //return
        return arr[start % arr.length];
    }

    private static List<Integer> findRangeOfSomeNumber(int[] arr, int key)
    {
        //Check validity
        if(arr == null)
        {
            return null;
        }

        //Initialization
        List<Integer> result = new ArrayList<>();

        //search for the range
        int left  = findRangeOfSomeNumber(arr, key, 1);
        int right = findRangeOfSomeNumber(arr, key, 2);
        result.add(left);
        result.add(right);

        //return
        return result;

    }//

    private static int findRangeOfSomeNumber(int[] arr, int key, int searchCount)
    {
        //Check Validity
        if(arr == null)
        {
            return -1;
        }//

        //Initialization
        int result = -1;
        
        //Find the range
        int start = 0, end = arr.length - 1;
        while(start <= end)
        {
            int mid = start + (end - start) / 2;
            if(key < arr[mid])
            {
                end = mid - 1;
            }
            else if(key > arr[mid])
            {
                start = mid + 1;
            }
            else 
            {
                if(searchCount == 1)
                {
                    end = mid - 1;
                }
                else if(searchCount == 2)
                {
                    start = mid + 1;
                }//
            }//

        }//

        //Update result
        //if found, start is the first position, and end is the last position
        if(searchCount == 1)
        {
            if(arr[start] == key)
            {
                result = start;
            }
            else 
            {
                result = -1;
            }//
        }//
        
        if(searchCount == 2)
        {
            if(arr[end] == key)
            {
                result = end;
            }
            else 
            {
                result = -1;
            }
        }//

        return result;
    }//

    
    public static void main(String[] args)
    {
        //Search for a number
        System.out.println("find the index of a given number");
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 4, 6, 10 },  6));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 10, 6, 4 }, 10));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 10, 6, 4 }, 4));
        System.out.println(ModifiedBinarySearch.searchNumber(new int[] { 10, 6, 4 , 1}, 1));
        System.out.println();


        //Find Ceiling
        System.out.println("find the ceiling of a given number");
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 4, 6, 10 }, 6));
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 1, 3, 8, 10, 15  }, 15));
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 4, 6, 10 }, 17));
        System.out.println(ModifiedBinarySearch.searchCeiling(new int[] { 4, 6, 10  }, -1));
        System.out.println();


        //Find the next number
        System.out.println("Find the next number!");
        System.out.println(ModifiedBinarySearch.findNextNumber(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        System.out.println(ModifiedBinarySearch.findNextNumber(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(ModifiedBinarySearch.findNextNumber(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(ModifiedBinarySearch.findNextNumber(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
        System.out.println();


        //Find the range of targeted number
        System.out.println("Find the range of targeted number!");
        List<Integer> result_list = ModifiedBinarySearch.findRangeOfSomeNumber(new int[] { 4, 6, 6, 6, 9 }, 6);
        System.out.println("result_list " + result_list);
        result_list = ModifiedBinarySearch.findRangeOfSomeNumber(new int[] { 1, 3, 8, 10, 15 }, 10);
        System.out.println("result_list " + result_list);
        result_list = ModifiedBinarySearch.findRangeOfSomeNumber(new int[] { 1, 3, 8, 10, 15 }, 12);
        System.out.println("result_list " + result_list);
        result_list = ModifiedBinarySearch.findRangeOfSomeNumber(new int[] { 25, 25, 25, 25, 25 }, 25);
        System.out.println("result_list " + result_list);



        
    }//main

    
}
