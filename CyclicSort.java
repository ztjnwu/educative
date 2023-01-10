import java.util.*;

class CyclicSort
{
    public static boolean cyclicSort(int[] arr)
    {   
        //valid judgement
        if(arr == null)
        {
            return false;
        }

        //Initialization
        boolean result = false; //default is success

        //cyclicSort
        for(int i = 0; i < arr.length; i++)
        {
            while((arr[i] < arr.length) && (arr[i] - 1) != i && (arr[i] != arr[arr[i] - 1]))
            {
                int j = arr[i] - 1;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }//while

        }//for

        //return
        return true;
    }


    public static int findMissingNumber(int[] arr)
    {
        //Check validity
        if(arr == null)
        {
            return -1;
        }
        
        //Sort the arr passed into the function
        for(int i = 0; i < arr.length; i++)
        {
            while((arr[i] < arr.length) && (arr[i] != i) && (arr[i] != arr[arr[i]]))
            {
                int j = arr[i];
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }//while
        }//for
        
        //update returnValue
        int returnValue = -1;
        for(int k = 0; k < arr.length; k++)
        {
            if(arr[k] != k)
            {
                returnValue = k;
                break;
            }// 
        }// 

        //return
        return returnValue;
    }//


    public static List<Integer> findAllMissingNumbers(int[] arr)
    {
        //check validality
        if(arr == null)
        {
            return null;
        }//

        //Find all missing numbers
        for(int i = 0; i < arr.length; i++)
        {   
            while((arr[i] < arr.length) && (arr[i] != i) && (arr[i] != arr[arr[i]]))
            {
                int j = arr[i];
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }// while
        }// for
        
        //update result sets
        List<Integer> result = new ArrayList<>();
        for(int k = 0; k < arr.length; k++)
        {
            if(arr[k] != k)
            {
                result.add(k);
            }//
        }//

        //Display the sorted array
        System.out.print("Sorted Array: ");
        for (int num : arr)
        {
            System.out.print(num + " ");
        }//
        System.out.print("All missing numbers: ");

        //return results
        return result;
    }
    

    public static int findDuplicatesNumber(int[] arr)
    {
        //Check validity
        if(arr == null)
        {
            return -1;
        }//

        //Initialization
        int result = -1;
        int count = 0;
        //swap operation
        for(int i = 0; i < arr.length; i++)
        {   
            while((arr[i] - 1 < arr.length) && (arr[i] - 1 != i) && (arr[i] != arr[arr[i] - 1]))
            {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }//while

            if( (arr[i] - 1 < arr.length) && (arr[i] - 1 != i) && (arr[i] == arr[arr[i] - 1]))
            {
                result = arr[i];
                if(count == 0)
                {
                    count = 2;
                }//
                else
                {
                    count++;
                }//

            }// if

        }// for

        //return
        System.out.print("Result: " + result + " Comparative count: " + count);
        return result;
    }


    public static List<Integer> findAllDuplicateNumber(int[] arr)
    {   
        //Check Validity
        if(arr == null)
        {
            return null;
        }//
        
        //swap
        for(int i = 0; i < arr.length; i++)
        {
            while((arr[i] - 1 < arr.length) && (arr[i] - 1 != i) && (arr[i] != arr[arr[i] - 1]))
            {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }//

        }//
        
        //Form Result
        List<Integer> result = new ArrayList<>();
        for(int k = 0; k < arr.length; k++)
        {        
            if(arr[k] - 1 != k )
            {
                result.add(arr[k]);
            }//
        }//
       
        //return
        return result;

    }//


    public static List<Integer> findNumbers(int[] arr)
    {
        //Base Check
        if(arr == null)
        {
            List<Integer> result = new ArrayList<>();
            result.add(null);
            return result;
        }//

        //Init
        List<Integer> result = new ArrayList<>();

        //Find numbers
        for(int i = 0; i < arr.length; i++)
        {
            //Place right position
            while((arr[i] - 1) <= arr.length - 1 && (arr[i] - 1) != i && (arr[i] != arr[arr[i] - 1]))
            {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }//

        }//

        //Count the duplicate number
        int index = -1;
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(" " + arr[i]);
            if(arr[i] - 1 != i)
            {
                index = i;
                result.clear();
                result.add(arr[i]);
                result.add(index);
            } 
        }//

        //Return
        return result;

    }//

    public static List<Integer> findSmallestMissingPositiveNumber(int[] arr)
    {
        //Base Check
        if(arr == null)
        {
            List<Integer> result = new ArrayList<>();
            result.add(null);
            return result;
        }//

        //Init
        List<Integer> result = new ArrayList<>();
        
        //Find the mallest positive number
        for(int i = 0; i < arr.length; i++)
        {
            while(arr[i] - 1 >= 0 && arr[i] - 1<= arr.length - 1 && arr[i] - 1 != i && arr[i] != arr[arr[i] - 1])
            {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }//

        }//

        //return
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] - 1 != i)
            {
                result.add(i + 1);
            }
        }//
        return result;

    }


    public static void main(String[] args)
    {
        
        //Cyclic Sort
        System.out.println("Cyclic sort!");
        int[] arr = new int[] {3, 1, 2, 3, 6, 4};
        //arr = new int[] { 3, 1, 5, 4, 2 , 3};
        CyclicSort.cyclicSort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.cyclicSort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        CyclicSort.cyclicSort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
        System.out.println();
        
        
        //Find the missing number
        System.out.println("Find the missing number!");
        arr = new int[] { 9, 3, 5, 2, 4, 6, 0, 1};
        System.out.println(CyclicSort.findMissingNumber(arr));
        arr = new int[] { 100, 0, 3, 1 };
        System.out.println(CyclicSort.findMissingNumber(arr));
        System.out.println();


        //Find all missing numbers
        System.out.println("Find ALL missing number!");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
        System.out.println(CyclicSort.findAllMissingNumbers(new int[] { 2, 3, 2, 1}));
        System.out.println(CyclicSort.findAllMissingNumbers(new int[] { 2, 3, 1, 8, 3, 2, 5, 1 }));
        System.out.println(CyclicSort.findAllMissingNumbers(new int[] { 2, 4, 1, 2 }));
        System.out.println();


        //Find duplicate number
        System.out.println("Find duplicate numbers");
        arr = new int[] { 1, 4, 4, 3, 4, 4, 4, 4};                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        System.out.println(CyclicSort.findDuplicatesNumber(arr));
        arr = new int[]{ 2, 1, 3, 3, 5, 3, 3};                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        System.out.println(CyclicSort.findDuplicatesNumber(arr));
        arr = new int[] { 2, 4, 1, 1, 1, 1, 5};
        System.out.println(CyclicSort.findDuplicatesNumber(arr));
        System.out.println();

        //Find all duplicate numbers
        System.out.println("Find ALL duplicate numbers");
        arr = new int[] {3, 4, 4, 5, 5 };                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        System.out.println(CyclicSort.findAllDuplicateNumber(arr));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        arr = new int[]{ 5, 4, 7, 2, 3, 5, 3};                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        System.out.println(CyclicSort.findAllDuplicateNumber(arr));
        System.out.println();

        //Find corrupt number
        System.out.println("Find corrupt number");
        arr = new int[] {3, 1, 2, 5, 2};
        System.out.println(" " + CyclicSort.findNumbers(arr));
        arr = new int[] {3, 1, 2, 3, 6, 4};
        System.out.println(" " + CyclicSort.findNumbers(arr));

        //Find the smallest misssing positive
        System.out.println("Find the smallest missing postive number");
        System.out.println(CyclicSort.findSmallestMissingPositiveNumber(new int[] { -3, 1, 5, 4, 2 }));
        System.out.println(CyclicSort.findSmallestMissingPositiveNumber(new int[] { 3, -2, 0, 1, 2 }));
        System.out.println(CyclicSort.findSmallestMissingPositiveNumber(new int[] { 3, 2, 5, 1 }));


    }//

}//ClassString