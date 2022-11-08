import java.util.*;

public class KLargestNumbers {
    pubic static List<Integer> findKLargestNumbers(int[] arr, int K)
    {
        if(arr == null || arr.length <= 0)
        {
            return null;
        }

        //
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    }

    //Find K largest numbers
    List<Integer> result = KLargestNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
    System.out.println("Here are the top K numbers: " + result);
    result = KLargestNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
    System.out.println("Here are the top K numbers: " + result);

}
