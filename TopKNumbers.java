import java.util.*;

public class TopKNumbers {
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

    public static int findKthSmallestNUmbers(int[] arr, int K)
    {
        if(arr == null)
        {
            return -1;
        }

        //find the Kth smallest elements in the arr
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(Comparator.naturalOrder());
        for(int i = 0; i < K; i++)
        {
            minHeap.add(arr[i]);
        }

        //
        for(int i = K; i < arr.length; i++)
        {
            if(minHeap.peek() > arr[i])
            {
                minHeap.poll();
                minHeap.add(arr[i]);
            }//
        }//

        //


        //return
        int result = ;
        return result;


    }


    //Find K largest numbers
    public static void main(String[] argv)
    {
        //Find the largest numbers
        List<Integer> result = TopKNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);
        result = TopKNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }

}//
