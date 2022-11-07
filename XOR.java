public class XOR {
    public static int findMissingNumber(int[] arr)
    {
        //Check validity
        if(arr == null)
        {
            return -1;
        }

        //Take XOR of an array of number ranging from 1 to n
        int s1 = 1;
        for(int i = 2; i <= arr.length + 1; i++)
        {
            s1 ^= i;
        }

        int s2 = arr[0];
        for(int j = 1; j < arr.length; j++)
        {
            s2 ^= arr[j];
        }

        //return
        int result;
        result = s1 ^ s2;
        return result;
    }

    public static void main(String[] args)
    {
        //find missing number
        System.out.println("Finding Missing number");
        int[] arr = new int[] { 1, 5, 2, 6, 4 };
        System.out.println("Missing number is: " + XOR.findMissingNumber(arr));


        //
    }

}
