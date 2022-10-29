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
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != i)
            {
                returnValue = i;
                break;
            }
        }

        //return 
        return returnValue;
    }

    public static List<Integer> findAllMissingNumbers(int[] arr)
    {
        //check validality
        if(arr == null)
        {
            return null;
        }

        //swap
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
    

    public static void main(String[] args)
    {
        //sort
        System.out.println("Cyclic Sort");

        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        CyclicSort.sort(arr);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.sort(arr);
        for (int num : arr)
        System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
        System.out.print(num + " ");
        System.out.println();
        

        //Find missing number
        System.out.println("\nFind missing number");

        arr = new int[] {4, 0, 3, 1};
        System.out.println(CyclicSort.findMissingNumber(arr));
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
        
        arr = new int[] {8, 3, 5, 2, 4, 6, 0, 1};
        System.out.println(CyclicSort.findMissingNumber(arr));
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        //Find all missing numbers
        System.out.println("\nFind all missing numbers");

        List<Integer> result = null; 
        arr = new int[] {2, 3, 1, 8, 2, 3, 5, 1};
        result = CyclicSort.findAllMissingNumbers(arr);
        System.out.println(result.toString());
        
        arr = new int[] {2, 4, 1, 2 };
        result = CyclicSort.findAllMissingNumbers(arr);
        System.out.println(result.toString());

        arr = new int[]{2, 3, 2, 1 };
        result = CyclicSort.findAllMissingNumbers(arr);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        System.out.println(result.toString());
    }//main

}//Class