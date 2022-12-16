import java.util.*;

public class TwoHeaps 
{
    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

    public static void insertNum(int num) 
    {
        //
        if(maxHeap.size() == 0 || maxHeap.peek() > num)
        {
            maxHeap.offer(num);
        }//
        else // num > maxHeap.peek()
        {
            minHeap.offer(num);
        }

        //Adjust the two Heaps
        if(maxHeap.size() - minHeap.size() == 2)
        {
            minHeap.offer(maxHeap.poll());
        }//
        else if(minHeap.size() - maxHeap.size() == 1)
        {
            maxHeap.offer(minHeap.poll());
        }

        //return
        return;
    }


    public static double findMedian() 
    {
        double result;
        if(maxHeap.size() == minHeap.size())
        {
            result = (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else
        {
            result = maxHeap.peek();
        }
        
        return result;
    }


    public static List<Double> findMediansOfAllSubarries(int[] arr, int K)
    {
        //Check Validity
        if(arr == null)
        {
            return null;
        }//

        //Initialization
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        //Find medians
        int winS, winE;
        winS = 0;
        winE = 0;
        insertHeaps(arr[0], maxHeap, minHeap);
        List<Double> result = new ArrayList<>();
        while(winE < arr.length)
        {
            if(winE - winS + 1 == K)
            {
                System.out.println(maxHeap);
                System.out.println(minHeap);
                if(maxHeap.size() == minHeap.size())
                {
                    result.add((maxHeap.peek() + minHeap.peek()) / 2.0);
                }// if
                else 
                {
                    result.add(maxHeap.peek() * 1.0 );
                }// else

                winE++;
                if(winE < arr.length)
                {
                    insertHeaps(arr[winE], maxHeap, minHeap);
                }//

                maxHeap.remove(arr[winS]);
                winS++;
                
                //adjust
                if(maxHeap.size() - minHeap.size() == 2)
                {
                    minHeap.offer(maxHeap.poll());
                }//
                else if(minHeap.size() - maxHeap.size() == 1)
                {
                    maxHeap.offer(minHeap.poll());
                }

            }//
            else if(winE - winS + 1 < K)
            {
                winE++;
                if(winE < arr.length)
                {
                    insertHeaps(arr[winE], maxHeap, minHeap);
                }
                
            }// 
        }// while

        //return
        return result;

    }//

    public static void insertHeaps(Integer num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap)
    {
        //Insert num into targeted heaps
        if(maxHeap.size() == 0 || maxHeap.peek() >= num)
        {
            maxHeap.offer(num);
        }//
        else 
        {
            minHeap.offer(num);
        }//

        //adjust
        if(maxHeap.size() - minHeap.size() == 2)
        {
            minHeap.offer(maxHeap.poll());
        }//
        else if(minHeap.size() - maxHeap.size() == 1)
        {
            maxHeap.offer(minHeap.poll());
        }

        //return
        return;
    }



    public static void main(String[] argv)
    {
        //Find the median of a number string
        System.out.println("Find the median of a number string");
        TwoHeaps.insertNum(3);
        System.out.println("The median is: " + TwoHeaps.findMedian());
        TwoHeaps.insertNum(1);
        System.out.println("The median is: " + TwoHeaps.findMedian());
        TwoHeaps.insertNum(5);
        System.out.println("The median is: " + TwoHeaps.findMedian());
        TwoHeaps.insertNum(4);
        System.out.println("The median is: " + TwoHeaps.findMedian());
        TwoHeaps.insertNum(2);
        System.out.println("The median is: " + TwoHeaps.findMedian());
        System.out.println();

        //Find the medians of all subarraies of size K
        System.out.println("Find the median of a number string");
        List<Double> result_double = TwoHeaps.findMediansOfAllSubarries(new int[] { 1, 2, -1, 3, 5 }, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result_double)
            System.out.print(num + " ");
        System.out.println();

        result_double = TwoHeaps.findMediansOfAllSubarries(new int[] { 1, 2, -1, 3, 5 }, 3);
        System.out.print("Sliding window medians are: ");
        for (double num : result_double)
            System.out.print(num + " ");
        System.out.println();
        
    }
    
}
