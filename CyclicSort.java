import java.util.*;

class CyclicSort
{
    public static boolean sort(int[] arr)
    {   
        //valid judgement
        if(arr == null)
        {
            return false;
        }

        //sort
        boolean result = true; // default is success
        for(int i = 0; i < arr.length; i++)
        {
            while(arr[i] - 1 != i  && arr[i] < arr.length)
            {
                int j = arr[i] - 1;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }//while

            if(arr[i] < arr.length)
            {
                result = false;
            }

        }//for

        //return
        return result;
    }

    public static int findMissingNumber(int[] arr)
    {
        if(arr == null)
        {
            return -1;
        }
        
        //sort the arr passed into the function
        for(int i = 0; i < arr.length; i++)
        {

            while(arr[i] != i  && arr[i] < arr.length)
            {
                int j = arr[i];
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j = arr[i];
            }//while
        }//for
        
        //update returnValue
        int returnValue = -1;
        for(int k = 0; k < arr.length; k++)
        {
            if(arr[k] - 1 != k)
            {
                returnValue = arr[k];
                break;
            }
        }

        //return
        return returnValue;

    }//

    public static List<Integer> findAllMissingNumbers(int[] arr)
    {
        //check validality
        if(arr == null)
        {
            return null;
        } 

        for(int i = 0; i < arr.length; i++)
        {   
            while((arr[i] != arr[arr[i] - 1]) && (arr[i] - 1 < arr.length))
            {
                int j = arr[i] - 1;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        
        //update result sets
        List<Integer> result = new ArrayList<>();
        for(int j = 0; j < arr.length; j++)
        {
            if(arr[j] - 1 != j)
            {
                result.add(j + 1);
            }
        }

        //return results
        return result;
    }
    
    public static int findDuplicatesNumber(int[] arr)
    {
        if(arr == null)
        {
            return -1;
        }

        //swap operation
        for(int i = 0; i < arr.length; i++)
        {   
            int j = arr[i] - 1;
            int temp = arr[j];
            while(arr[i] != arr[j] && arr[i] - 1 < arr.length)
            {
                arr[j] = arr[i];
                arr[i] = temp;
                j = arr[i] - 1;
                temp = arr[j];
            }//while

        }//for

        //find the duplicate number
        int returnValue = -1;
        for(int k = 0; k < arr.length; k++)
        { 
            if(arr[k] - 1 != k)
            {
                returnValue = arr[k];
                break;
            } 
        }

        //return
        return returnValue;
    }

    public static List<Integer> findAllDuplicateNumber(int[] arr)
    {   
        if(arr == null)
        {
            return null;
        }
        
    
        //swap
        for(int i = 0; i < arr.length; i++)
        {
            int j = arr[i] - 1;
            int temp = arr[j];
            while(arr[i] != arr[j] && arr[i] -1 < arr.length)
            {
                arr[j] = arr[i];
                arr[i] = temp;
                j = arr[i] - 1;
                temp = arr[j];
            }

        }
        
        //form result
        List<Integer> result = new ArrayList<>();
        for(int k = 0; k < arr.length; k++)
        {        
            if(arr[k] - 1 != k )
            {
                result.add(arr[k]);
            }
        }
       
        //return
        return result;
    }

    public static void main(String[] args)
    {
        int[] arr = null;

        //Find missing number
        System.out.println("\nFind missing number");        
        arr = new int[] {8, 3, 5, 2, 4, 6, 0, 1};
        System.out.println(CyclicSort.findMissingNumber(arr));

        //Find all missing numbers
        arr = new int[]{2, 3, 2, 1 };                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        System.out.println(CyclicSort.findAllMissingNumbers(arr));

        //Find duplicate number
        System.out.println("\nFind duplicate numbers");
        arr = new int[] { 1, 4, 4, 3, 2 };                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        System.out.println(CyclicSort.findDuplicatesNumber(arr));
        arr = new int[]{ 2, 1, 3, 3, 5, 4 };                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        System.out.println(CyclicSort.findDuplicatesNumber(arr) );

        //Find all duplicate numbers
        arr = new int[] {3, 4, 4, 5, 5 };                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        System.out.println(CyclicSort.findAllDuplicateNumber(arr));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        arr = new int[]{ 5, 4, 7, 2, 3, 5, 3};                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        System.out.println(CyclicSort.findAllDuplicateNumber(arr));

    }//

}//ClassString