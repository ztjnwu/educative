import java.util.*;

public class KLargestNumbers {
    public static List<Integer> findKLargestNumbers(int[] arr, int K)
    {
        if(arr == null || arr.length <= 0)
        {
            return null;
        }

        //Initialization
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(Comparator.naturalOrder());
        for(int i = 0; i < K; i++)
        {
            minHeap.add(arr[i]);
        }

        //Find the K largest numbers
        for(int j = K; j < arr.length; j++)
        {
            if(arr[j] > minHeap.peek())
            {
                minHeap.poll();
                minHeap.add(arr[j]);
            }
        }

        //return
        List<Integer> result = new ArrayList<>(minHeap); 
        return result;
    }

    //Find K largest numbers
    public static void main(String[] argv)
    {
        //Find the largest numbers
        List<Integer> result = KLargestNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);
        result = KLargestNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }

}//
