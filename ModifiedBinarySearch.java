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
        //start points to the smallest number greater than the key, while end points to the biggest number smaller than the key.
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
                result = mid;
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
        return result;

    }//

    
    public static int findNumberInInfiniteArray(ArrayReader reader, int key)
    {
        //Check Validity
        if(reader == null)
        {
            return -1;
        }//

        //Find the proper bounds of the array
        int start = 0, end = 0; //assume that there is the only one element in the infinite array
        while(reader.get(end) < key)
        {
            int length = (end - start + 1) * 2;
            start = end + 1;
            end = start + length - 1;
            //System.out.println(start + " " + end );
        }//

        //
        int result = -1;
        while(start <= end)
        {
            int mid = start + (end - start) / 2;
            if(reader.get(mid) < key)
            {
                start = mid + 1;
            }
            else if(reader.get(mid) > key)
            {
                end = mid - 1;
            }
            else // 
            {
                result = mid;
                break;
            }
        }//

        //return
        if(start > end)
        {
            result = -1;
        }

        return result;
    }

    public static int findMinDiffElement(int[] arr, int key)
    {
        //Check validity
        if(arr == null)
        {
            return -1;
        }

        //Initialization
        int start = 0, end = arr.length - 1;
        int mid = Integer.MAX_VALUE;
        while(start <= end)
        {
            mid = start + (end - start) / 2;
            if(arr[mid] < key)
            {
                start = mid + 1;
            }
            else if(arr[mid] > key)
            {
                end = mid - 1;
            }
            else 
            {
                break;
            }//
        }//

        //return
        int result = Integer.MIN_VALUE;
        if(start <= end)
        {
            result = arr[mid];
        }//
        else 
        {
            if(start == arr.length)
            {
                result = arr[arr.length - 1];
            }

            if(end == -1)
            {
                result = arr[0];
            }
            
            if(start <= arr.length -1 && end >= 0)
            {
                result = Math.abs(arr[start] - key) > Math.abs(arr[end] - key) ? arr[end] : arr[start];  
            }
            
        }//

        return result;
    }

    public static int findMaximumInBotinicArray(int[] arr)
    {
        //Check Validity
        if(arr == null)
        {
            return -1;    
        }

        //Initialization
        int start = 0, end = arr.length - 1;
        while(start < end)
        {
            int mid = start + (end - start) / 2;
            if(arr[mid] < arr[mid + 1])
            {
                start = mid + 1;
            }
            else
            {
                end = mid;
            }//

        }//

        //return 
        return arr[start];

    }//

    public static int findTargetedItemBotinicArray(int[] arr, int key)
    {
        //Base Check
        if(arr == null)
        {
            return -1;
        }//

        //Init
        int result = -1;

        //Find the max in the botanic array
        int start = 0, end = arr.length - 1;
        int max = Integer.MIN_VALUE;
        while(start < end)
        {
            int mid = start + (end - start) / 2;
            if(arr[mid] > arr[mid + 1])
            {
                end = mid;
            }//
            else 
            {
                start = mid + 1;
            }//

        }//
        max = arr[start];
        
        //Binary Search
        int index = -1;
        int start_org = start;
        if(key < max)
        {
            end = start - 1;
            start = 0;
            while(start <= end)
            {
                int mid = start + (end - start) / 2;
                if(arr[mid] == key)
                {
                    break;
                }//
                else if(arr[mid] < key)
                {
                    start = mid + 1;
                }
                else 
                {
                    end = mid - 1;
                }//
    
            }//
    
            if(start <= end)
            {
                index = start + (end - start) / 2;
            }
            else 
            {
                index = -1;
                start = start_org;
                end = arr.length - 1;
                while(start <= end)
                {
                    int mid = start + (end - start) / 2;
                    if(arr[mid] == key)
                    {
                        break;
                    }//
                    else if(arr[mid] < key)
                    {
                        start = mid + 1;
                    }
                    else 
                    {
                        end = mid - 1;
                    }//
        
                }//

                if(start <= end)
                {
                    index = start + (end - start) / 2;
                }//
                else
                {
                    index = -1;
                }//

            }//

        }
        else if(key > max)
        {
            index = -1;
        }//
        else  //key == max
        {
            index = start;
        }//
        
       
        //return
        result = index;
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
        System.out.println();

        //Find the targeted number in a infinitie array
        System.out.println("Find the range of targeted number!");
        ArrayReader reader = new ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
        System.out.println(ModifiedBinarySearch.findNumberInInfiniteArray(reader, 16));
        System.out.println(ModifiedBinarySearch.findNumberInInfiniteArray(reader, 11));
        reader = new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
        System.out.println(ModifiedBinarySearch.findNumberInInfiniteArray(reader, 15));
        System.out.println(ModifiedBinarySearch.findNumberInInfiniteArray(reader, 200));
        System.out.println();

        //Find the minimum difference 
        System.out.println("Find the minimum difference !");
        System.out.println(ModifiedBinarySearch.findMinDiffElement(new int[] { 4, 6, 10 }, 7));
        System.out.println(ModifiedBinarySearch.findMinDiffElement(new int[] { 4, 6, 10 }, 4));
        System.out.println(ModifiedBinarySearch.findMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(ModifiedBinarySearch.findMinDiffElement(new int[] { 4, 6, 10 }, 17));
        System.out.println();

        //Find the maximum in a botinic array
        System.out.println("Find the maximum in a botinic array!");
        System.out.println(ModifiedBinarySearch.findMaximumInBotinicArray(new int[] { 1, 3, 8, 8, 12, 4, 2 }));
        System.out.println(ModifiedBinarySearch.findMaximumInBotinicArray(new int[] { 3, 8, 3, 1 }));
        System.out.println(ModifiedBinarySearch.findMaximumInBotinicArray(new int[] { 1, 3, 8, 12 }));
        System.out.println(ModifiedBinarySearch.findMaximumInBotinicArray(new int[] { 10, 9, 8 }));
        System.out.println();
        
        //Find the targeted element
        System.out.println("Find the targeted item in a botinic array!");
        System.out.println(ModifiedBinarySearch.findTargetedItemBotinicArray(new int[] { 1, 3, 8, 4, 3 }, 4));
        System.out.println(ModifiedBinarySearch.findTargetedItemBotinicArray(new int[] { 3, 8, 3, 1 }, 8));
        System.out.println(ModifiedBinarySearch.findTargetedItemBotinicArray(new int[] { 1, 3, 8, 12 }, 12));
        System.out.println(ModifiedBinarySearch.findTargetedItemBotinicArray(new int[] { 10, 9, 8 }, 10));

    }//main

    
}// class


class ArrayReader 
{
    int[] arr;
  
    ArrayReader(int[] arr) 
    {
        this.arr = arr;
    }//
  
    public int get(int index) 
    {
        if (index >= arr.length)
        {
            return Integer.MAX_VALUE;
        }

        return arr[index];
    }//

}//
  
