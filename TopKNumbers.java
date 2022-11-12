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

    public static int findKthSmallestNumber(int[] arr, int K)
    {
        //Check validity
        if(arr == null)
        {
            return -1;
        }


        //find the Kth smallest elements in the arr
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for(int i = 0; i < K; i++)
        {
            maxHeap.add(arr[i]);
        }
        
        //
        for(int i = K; i < arr.length; i++)
        {
            if(maxHeap.peek() >= arr[i])
            {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }// if


        }// for

        //return
        int result = maxHeap.peek();
        return result;
    }


    public static List<Point> findKClosestPoints(Point[] arr, int K)
    {
        //checke validity
        if(arr == null)
        {
            return null;
        }

        //
        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>((a, b) -> b.distance - a.distance);

        //
        for(int i = 0; i < K; i++)
        {
            maxHeap.add(arr[i]);
        }

        //
        for(int i = K; i < arr.length; i++)
        {
            if(arr[i].distance < maxHeap.peek().distance)
            {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        //return
        List<Point> result = new ArrayList<>(maxHeap);
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

        //Find the Kth smallest element
        int result_int = TopKNumbers.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result_int);
    
        result_int = TopKNumbers.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result_int);
    
        result_int = TopKNumbers.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result_int);

        //Find the Kth smallest points
        Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
        List<Point> result_point = TopKNumbers.findKClosestPoints(points, 2);
        for(int i = 0; i < result_point.size(); i++)
        {
            System.out.println("[" + result_point.get(i).x + ", " + result_point.get(i).y + "]");
        }//

    }//

}//


class Point
{
    int x;
    int y;
    int distance;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.distance = distanceFromOrigin(this.x, this.y);
        
    }

    private int distanceFromOrigin(int x, int y)
    {
        int result;
        result = (int)Math.sqrt(x * x + y * y);
        return result;
    }

}
