import java.util.*;

import javax.lang.model.util.ElementScanner6;

class Interval
{
    int start;
    int end;

    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
    } 

}//interval

public class MergeIntervals 
{

    public static List<Interval> merge(List<Interval> input)
    {
        //Base check
        if(input == null)
        {
            return null;
        }//

        //Initialization
        List<Interval> result = new ArrayList<>();

        //Sort the intervals on the start time in a ascending order 
        //Collections.sort(input, (a, b) -> a.start - b.start);

        //Merge the intervals
        int start_merged = input.get(0).start, end_merged = input.get(0).end;
        for(int i = 1; i < input.size(); i++)
        {
            Interval current = input.get(i);
            if(current.end < start_merged)
            {
                
                result.add(current);
                start_merged = current.start;
                end_merged = current.end;
            }
            else if(current.start > end_merged)
            {
                result.add(new Interval(start_merged, end_merged));
                start_merged = current.start;
                end_merged = current.end;
            }
            else 
            {
                start_merged = Math.min(current.start, start_merged);
                end_merged = Math.max(current.end, end_merged);
            }
        
        }//
        
        result.add(new Interval(start_merged, end_merged)); // add the last interval to the set of result

        //return
        return result;
    
    }//
    

    public static List<Interval> insert(List<Interval> input, Interval newInterval)
    {
        //Base check
        if(input == null)
        {
            return null;
        }

        //Initialization
        List<Interval> result = new ArrayList<>();

        //Find the correct position
        int i = 0;
        while(i < input.size() && newInterval.start > input.get(i).end)
        {
            result.add(input.get(i));
            i++;
        }//
        input.add(i, newInterval);

        //Merge the remaining intervals
        int start_merged = input.get(i).start, end_merged = input.get(i).end;
        for(int k = i + 1; k < input.size(); k++)
        {
            Interval current = input.get(k);
            if(current.start <= end_merged)
            {
                start_merged = Math.min(current.start, start_merged);
                end_merged = Math.max(current.end, end_merged);
            }
            else
            {
                result.add(new Interval(start_merged, end_merged));
                start_merged = current.start;
                end_merged = current.end;
            }//

        }//

        result.add(new Interval(start_merged, end_merged));

        //return
        return result;
    }


    public static void main(String[] args)
    {
        //Merge intervals
        System.out.println("Merging");
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        //Merge intervals
        System.out.println("\nInsert");
        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : MergeIntervals.insert(input, new Interval(4, 6)))
        System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : MergeIntervals.insert(input, new Interval(4, 10)))
        System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : MergeIntervals.insert(input, new Interval(1, 4)))
        System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

    }  
    
    




}//Class


