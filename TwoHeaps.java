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


    public static int findMaximumProfits(int[] capitals, int[] profits, int K, int initCapital)
    {
        //Check validity
        if(capitals == null || profits == null || K < 0 || initCapital < 0)
        {
            return -1;
        }//


        //Init
        Map<Integer, Integer> capitalProfit = new HashMap<Integer, Integer>();
        for(int i = 0; i < capitals.length; i++)
        {
            capitalProfit.put(capitals[i], profits[i]);
        }//

        //Init
        PriorityQueue<Map.Entry<Integer, Integer>> minHeapCap    = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        for(Map.Entry<Integer, Integer> entry : capitalProfit.entrySet())
        {
            minHeapCap.offer(entry);
        }//

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeapProfit = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        //Find the projects whose capitals are less than currentCapital, 
        //and then among which to find the projects whose profits are the biggest.
        int currCapital = initCapital;
        for(int j = 0; j < K; j++)
        {
            while(minHeapCap.size() != 0 && minHeapCap.peek().getKey() <= currCapital)
            {
                maxHeapProfit.offer(minHeapCap.poll());
            }// while

            currCapital += maxHeapProfit.peek().getValue();
        }// for

        //return
        return currCapital;
    }


    public static Map<Interval, Interval> findNextInterval(Interval[] input)
    {
        //Base Check
        if(input == null)
        {
            return null;
        }//

        //Init
        Map<Interval, Interval> result = new HashMap<>();
        PriorityQueue<Interval> maxEndHeap = new PriorityQueue<Interval>((a, b) -> b.end - a.end);
        PriorityQueue<Interval> maxStartHeap = new PriorityQueue<Interval>((a, b) -> b.start - a.start);

        for(int i = 0; i < input.length; i++)
        {
            result.put(input[i], new Interval(-1, -1));
            maxEndHeap.offer(input[i]);
            maxStartHeap.offer(input[i]);
        }//

        //Find next interval
        while(!maxEndHeap.isEmpty())
        {
            //Take out the top item
            Interval item = maxEndHeap.poll();

            //TO DO
            Interval startItem = null;
            while(!maxStartHeap.isEmpty() && maxStartHeap.peek().start >= item.end)
            {
                startItem = maxStartHeap.poll();
            }//

            if(!maxStartHeap.isEmpty())
            {
                if(startItem != null)
                {
                    result.put(item, startItem);
                }//
            }//
            else 
            {
                startItem = null;
            }//

            //Update
            if(startItem != null)
            {
                maxStartHeap.offer(startItem);
            }//

        }//

        //return
        return result;

    }//

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
        System.out.println();

        //Find the medians of all subarraies of size K
        System.out.println("Find the median of all subarraies of size K");
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
        System.out.println();

        //Find the maximum capitals
        System.out.println("Find the maximum capitals");
        int result = TwoHeaps.findMaximumProfits(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = TwoHeaps.findMaximumProfits(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        System.out.println("Maximum capital: " + result);
        System.out.println();

        //Find the next intervals
        System.out.println("Find the next intervals");
        Interval[] intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
        Map<Interval, Interval> result_intervals = TwoHeaps.findNextInterval(intervals);
        for(Map.Entry<Interval, Interval> item : result_intervals.entrySet())
        {
            System.out.print("[" + item.getKey().start + ", " + item.getKey().end + "]");
            System.out.println(", [" + item.getValue().start + ", " + item.getValue().end + "]");
        }
        System.out.println();
    
        intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
        result_intervals = TwoHeaps.findNextInterval(intervals);
        for(Map.Entry<Interval, Interval> item : result_intervals.entrySet())
        {
            System.out.print("[" + item.getKey().start + ", " + item.getKey().end + "]");
            System.out.println(", [" + item.getValue().start + ", " + item.getValue().end + "]");
        }
        System.out.println();
      

    }// main
    
}//
