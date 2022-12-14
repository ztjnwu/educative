import java.util.*;

public class TopKNumbers {
    public static List<Integer> findKLargestNumbers(int[] arr, int K)
    {
        //
        if(arr == null || arr.length <= 0)
        {
            return null;
        }

        //Initialization
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a - b);
        for(int j = 0; j < arr.length; j++)
        {
            minHeap.add(arr[j]);
            if(minHeap.size() > K)
            {
                minHeap.poll(); 
            }//
        }//

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
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
        for(int i = 0; i < arr.length; i++)
        {
            maxHeap.add(arr[i]);
            if(maxHeap.size() > K)
            {
                maxHeap.poll();
            }

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

        //Initialization
        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>((a, b) -> b.distance - a.distance);

        //Find K closer points
        for(int i = 0; i < arr.length; i++)
        {
            maxHeap.add(arr[i]);
            if(maxHeap.size() > K)
            {
                maxHeap.poll();
            }
        }

        //return
        List<Point> result = new ArrayList<>(maxHeap);
        return result;

    }


    public static int findConnectRopes(int[] ropes)
    {
        //Chcek validity
        if(ropes == null)
        {
            return -1;
        }//

        //Initialization
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a - b);
        for(int i = 0; i < ropes.length; i++)
        {
            minHeap.add(ropes[i]);
        }//

        //Connect ropes
        int cost = 0;
        while(minHeap.size() != 1)
        {
            int r1 = minHeap.poll();
            int r2 = minHeap.poll();
            cost += (r1 + r2);
            minHeap.add(r1 + r2);
        }//

        //update
        return cost;
    }//

    
    public static List<Integer> findTopKFrequencyItems(int[] arr, int K)
    {
        //Check Validity
        if(arr == null)
        {
            return null;
        }

        //Initialization freMap
        Map<Integer, Integer> freMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++)
        {
            freMap.put(arr[i], freMap.getOrDefault(arr[i], 0) + 1);
        }//

        //Initialization minHeap
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for(Map.Entry<Integer, Integer> entry : freMap.entrySet())
        {

            minHeap.add(entry);
            if(minHeap.size() > K)
            {
                minHeap.poll();
            }//
        }//


        //return
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < K; i++)
        {
            result.add(minHeap.poll().getKey());
        }

        return result; 
    
    }

    
    public static String frequencySort(String str)
    {
        //Check Validity
        if(str == null)
        {
            return null;
        }//

        //Defination 
        char[] str_arr = str.toCharArray();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        Map<Character, Integer> freMap = new HashMap<>();

        //Initialization
        for(int i = 0; i < str_arr.length; i++)
        {
            freMap.put(str_arr[i], freMap.getOrDefault(str_arr[i], 0) + 1);
        }//
        
        for(Map.Entry<Character, Integer> entry : freMap.entrySet())
        {
            maxHeap.offer(entry);
        }

        //Frequency Sort
        StringBuilder result = new StringBuilder();
        while(maxHeap.size() != 0)
        {
            Map.Entry<Character, Integer> entry = null;
    
            entry = maxHeap.poll();
            for(int i = 0; i < entry.getValue(); i++)
            {
                result.append(entry.getKey());
            }//
        }//

        //return
        return result.toString();

    }


    public static int findKthEmelent(int[] arr, int K)
    {
        if(arr == null)
        {
            return -1;
        }//

        //
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a -b);
        for(int i = 0; i < arr.length; i++)
        {
            minHeap.offer(arr[i]);
            if(minHeap.size() > K)
            {
                minHeap.poll();
            }
        }

        //return
        return minHeap.peek();

    }


    public static List<Integer> findKClosestNumbers(int[] arr, int K, int Y)
    {
        //Check validity
        if(arr == null)
        {
            return null;
        }

        //Initialization
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        Map<Integer, Integer> disMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++)
        {
            disMap.put(arr[i], Math.abs(arr[i] - Y));
        }

        //find X 
        for(Map.Entry<Integer, Integer> entry : disMap.entrySet())
        {
            maxHeap.offer(entry);
            if(maxHeap.size() > K)
            {
                maxHeap.poll();
            }//
        }//


        //return
        List<Integer> result = new ArrayList<>();
        while(!maxHeap.isEmpty())
        {
            result.add(maxHeap.poll().getKey());
        }//

        return result;
    }

    public static void main(String[] argv)
    {
        //Find the largest numbers
        System.out.println("Find the largest numbers");
        List<Integer> result = TopKNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);
        result = TopKNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
        System.out.println();

        //Find K smallest element
        System.out.println("Find K smallest element");
        int result_int = TopKNumbers.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result_int);
        System.out.println();
    
        result_int = TopKNumbers.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result_int);
        System.out.println();

        result_int = TopKNumbers.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result_int);
        System.out.println();

        //Find K smallest points
        System.out.println("FFind K smallest points");
        Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
        List<Point> result_point = TopKNumbers.findKClosestPoints(points, 2);
        for(int i = 0; i < result_point.size(); i++)
        {
            System.out.println("[" + result_point.get(i).x + ", " + result_point.get(i).y + "]");
        }//
        System.out.println();

        //Connect several ropes with minimum cost
        System.out.println("Connect several ropes with minimum cost");
        result_int = TopKNumbers.findConnectRopes(new int[] { 1, 3, 11, 5 });
        System.out.println("Minimum cost to connect ropes: " + result_int);
        result_int = TopKNumbers.findConnectRopes(new int[] { 3, 4, 5, 6 });
        System.out.println("Minimum cost to connect ropes: " + result_int);
        result_int = TopKNumbers.findConnectRopes(new int[] { 1, 3, 11, 5, 2 });
        System.out.println("Minimum cost to connect ropes: " + result_int);
        System.out.println();

        //Find Top K frequent item
        System.out.println("Find Top K frequent item");
        List<Integer> result_list = TopKNumbers.findTopKFrequencyItems(new int[] { 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4}, 2);
        System.out.println("op K frequent item: " + result_list);
        System.out.println();

        //Frequqncy Sort;
        System.out.println("Frequqncy Sort");
        System.out.println("" + TopKNumbers.frequencySort("Programming"));
        System.out.println("" + TopKNumbers.frequencySort("abcbab"));
        System.out.println();

        //Find Kth element
        System.out.println("Frequqncy Sort");
        System.out.println("4th largest number is: " + TopKNumbers.findKthEmelent(new int[] { 3, 1, 5, 12, 2, 11, 6}, 4));
        System.out.println("4th largest number is: " + TopKNumbers.findKthEmelent(new int[] { 3, 1, 5, 12, 2, 11, 6, 13}, 4));
        System.out.println("4th largest number is: " + TopKNumbers.findKthEmelent(new int[] { 3, 1, 5, 12, 2, 11, 6, 13, 4}, 4));
        System.out.println();

        //Find the closest numbers
        System.out.println("Find the closest numbers");
        System.out.println("Find the closest numbers: " + TopKNumbers.findKClosestNumbers(new int[] { 5, 6, 7, 8, 9}, 3, 7));
        System.out.println("Find the closest numbers: " + TopKNumbers.findKClosestNumbers(new int[] { 4, 5, 6, 9 }, 3, 6));
        System.out.println("Find the closest numbers: " + TopKNumbers.findKClosestNumbers(new int[] { 4, 5, 6, 9 }, 3, 10));

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
