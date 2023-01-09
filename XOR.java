import java.util.*;

public class XOR 
{
    public static int findMissingNumber(int[] arr)
    {
        //Base Check
        if(arr == null)
        {
            return -1;
        }

        //Initialization
        int result = -1;

        //Take XOR of the numbers ranging from 1 to n
        int s1 = 0;
        for(int i = 1; i <= arr.length + 1; i++)
        {
            s1 ^= i;
        }

        //Take XOR of the targeted array
        int s2 = 0;
        for(int j = 0; j < arr.length; j++)
        {
            s2 ^= arr[j];
        }

        //Return
        result = s1 ^ s2;

        return result;
    }


    public static int singleNumber(int[] arr)
    {
        //Base Check
        if(arr == null)
        {
            return -1;
        }//

        //Initialization
        int result = -1;


        //Take XOR of the array arr
        int sum = 0;
        for(int i = 0; i < arr.length; i++)
        {
            sum ^= arr[i];
        }

        //Return
        result = sum;
        return result;
    }


    public static List<Integer> findTwoNumber(int[] arr)
    {
        //Base Check
        if(arr == null)
        {
            return null;
        }

        //Initialization
        List<Integer> result = new ArrayList<>();

        //Build two types of groups
        int xorSum = 0;
        for(int i = 0; i < arr.length; i++)
        {
            xorSum ^= arr[i];
        }
        
        int bit = 0x1;
        while((bit & xorSum) == 0)
        {
            bit = bit << 1;
        }//


        List<Integer> type1 = new ArrayList<>();
        List<Integer> type2 = new ArrayList<>();
        for(int i = 0; i < arr.length; i++)
        {
            if((bit & arr[i]) == 0)
            {
                type1.add(arr[i]);
            }
            else 
            {
                type2.add(arr[i]);
            }
        }//

        int xorT1 = 0;
        for(int i = 0; i < type1.size(); i++)
        {
            xorT1 ^= type1.get(i);
        }

        int xorT2 = 0;
        for(int i = 0; i < type2.size(); i++)
        {
            xorT2 ^= type2.get(i);
        }

        //Return
        result.add(xorT1);
        result.add(xorT2);
        return result;
    }


    public static int findComplement(int key)
    {
        //Base Check
        if(key < 0)
        {
            return -1;
        }

        //Initialization
        int result = -1;

        //Find complement
        int totalBits = 0;
        int copy_key = key;
        while(copy_key != 0)
        {
            copy_key = copy_key >> 1;
            totalBits++;
        }
        
        int allOnes = (int) Math.pow(2, totalBits) - 1;

        //return
        result = key ^ allOnes;
        return result;
    }


    public static int[][] flipInvertMatrix(int[][] matrix)
    {
        //Base Check
        if(matrix == null)
        {
            return matrix;
        }

        //Flip and Invert
        for(int i = 0; i < matrix.length; i++)
        {
            int length = matrix[0].length;
            for(int j = 0; j < length / 2; j++)
            {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - 1 - j];
                matrix[i][length - 1 -j] = temp;

                matrix[i][j] = matrix[i][j] ^ 1;
                matrix[i][length - 1 - j] = matrix[i][length - 1 - j] ^ 1;
            }//

            if(matrix[0].length %2 != 0)
            {
                matrix[i][matrix[0].length %2] = matrix[i][matrix[0].length %2] ^ 1;
            }
            

        }//

        //return
        return matrix;

    }

    public static void main(String[] args)
    {
        //Find missing number
        System.out.println("Finding Missing number");
        int[] arr = new int[] { 1, 5, 2, 6, 4 };
        System.out.println("Missing number is: " + XOR.findMissingNumber(arr)); 
        System.out.println();

        //Find single number
        System.out.println("Finding single number");
        arr = new int[] {1, 4, 2, 1, 3, 2, 3};
        System.out.println("Missing number is: " + XOR.singleNumber(arr)); 
        System.out.println();

        //Find two different numbers
        System.out.println("Find two different numbers");
        arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
        System.out.println(XOR.findTwoNumber(arr));
        arr = new int[] { 2, 1, 3, 2 };
        System.out.println(XOR.findTwoNumber(arr));
        System.out.println();

        //Find the complement
        System.out.println("Find the complement");
        System.out.println(XOR.findComplement(8));
        System.out.println(XOR.findComplement(10));
        System.out.println();


        //Flip and Invert
        System.out.println("Flip and Invert");
        int[][] arr1 = {{1, 0, 1}, {1, 1, 1}, {0, 1, 1}};
        XOR.flipInvertMatrix(arr1);
        for(int i = 0; i < arr1.length; i++)
        {
            for(int j = 0; j < arr1[0].length; j++)
            {
                System.out.print(" " + arr1[i][j]);
            }
            System.out.println();
        }
        System.out.println();
       
    
        int[][] arr2 = {{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        XOR.flipInvertMatrix(arr2);
        for(int i = 0; i < arr2.length; i++)
        {
            for(int j = 0; j < arr2[0].length; j++)
            {
                System.out.print(" " + arr2[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
    }

}