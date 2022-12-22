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
        Collections.sort(input, (a, b) -> a.start - b.start);

        //Merge the intervals
        Interval merged = new Interval(input.get(0).start, input.get(0).end);
        for(int i = 1; i < input.size(); i++)
        {
            Interval current = input.get(i);     
            if(current.start > merged.end)
            {
                result.add(new Interval(merged.start, merged.end));
                merged = new Interval(current.start, current.end);
            }
            else
            {
                merged.start = Math.min(merged.start, current.start);
                merged.end = Math.max(merged.end, current.end);
            }// else

        }// for

        result.add(merged); // add the last interval to the set of result

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

        //Sort input
        Collections.sort(input, (a,b) -> a.start - b.start);

        //Find correct position
        int i = 0;
        while(i < input.size() && newInterval.start > input.get(i).start)
        {
            i++;
        }// while

        if(i == input.size())
        {
            input.add(newInterval);
        }
        else
        {
            input.add(i, newInterval);
        }
        
        //Merge the remaining intervals
        Interval merged = new Interval(input.get(0).start, input.get(0).end);
        for(int k = 0; k < input.size(); k++)
        {
            Interval current = input.get(k);
            if(current.start > merged.end)
            {
                result.add(new Interval(merged.start, merged.end));
                merged = new Interval(current.start, current.end);
            }
            else 
            {
                merged.start = Math.min(current.start, merged.start);
                merged.end = Math.max(current.end, merged.end);
            }//

        }//

        result.add(merged);

        //return
        return result;
    }


    public static List<Interval> intersection(List<Interval> input1, List<Interval> input2)
    {
        //Base check
        if(input1 == null || input2 == null)
        {
            return null;
        }

        //Initialization
        List<Interval> result = new ArrayList<>();

        //Sort the two lists
        Collections.sort(input1, (a, b) -> a.start - b.start);
        Collections.sort(input2, (a, b) -> a.start - b.start);

        //Find out the intersection between the two input sets
        int i = 0, j = 0;
        while(i < input1.size() && j < input2.size())
        {
            
            Interval ti = input1.get(i);
            Interval tj = input2.get(j);
            int start, end;
            start = Math.max(ti.start, tj.start);
            end = Math.min(ti.end, tj.end);
            if(start <= end)
            {
                result.add(new Interval(start, end));
            }
            
            //updatre indices
            if(ti.end <= tj.end)
            {
                i++;
            }//
            else
            {
                j++;
            }//
            
        }

        //return
        return result;

    }


    public static List<List<Interval>> conflicts(List<Interval> input)
    {
        //Base Chcek
        if(input == null)
        {
            return null;
        }

        //Initialization
        List<Interval> result_merged = new ArrayList<>();
        List<List<Interval>> conflicts = new ArrayList<>();
        List<Interval> merged_intervals = new ArrayList<>();

        //Sort input
        Collections.sort(input, (a, b) -> a.start - b.start);

        //Merge
        Interval merged = new Interval(input.get(0).start, input.get(0).end);
        merged_intervals.clear(); // not real merged interval, therefore merged_intervals must be set to []
        for(int i = 1; i < input.size(); i++)
        {
            Interval current = input.get(i);
            if(current.start > merged.end)
            {
                result_merged.add(merged);
                merged = new Interval(current.start, current.end);

                //Update conflicts
                conflicts.add(new ArrayList<>(merged_intervals));
                merged_intervals.clear(); //
            }
            else 
            {
                //Build the one of conflicts 
                if(merged_intervals.size() == 0) //first merge
                {
                    merged_intervals.add(new Interval(merged.start, merged.end));
                    merged_intervals.add(new Interval(current.start, current.end));
                }//
                else // not first merge
                {
                    merged_intervals.add(new Interval(current.start, current.end));
                }//

                //update merged
                merged.start = Math.min(current.start, merged.start);
                merged.end = Math.max(current.end, merged.end); 

            }// else

        }// for

        //Update merged and conflicts
        result_merged.add(merged);
        conflicts.add(new ArrayList<>(merged_intervals));

        //return
        return conflicts;

    }//

    public static void main(String[] args)
    {
        //Merge intervals
        System.out.println("Merging");
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 2));
        input.add(new Interval(8, 9));
        input.add(new Interval(2, 3));
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
        System.out.println("\n");

        //Intersection
        System.out.println("Intersection");
        List<Interval> input1 = new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)));
        List<Interval> input2 = new ArrayList<> (Arrays.asList(new Interval(2, 3), new Interval(5, 7)));
        List<Interval> result = MergeIntervals.intersection(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new ArrayList<> (Arrays.asList(new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)));
        input2 = new ArrayList<> (Arrays.asList(new Interval(5, 10)));
        result = MergeIntervals.intersection(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println("\n");

        //Conflicts
        System.out.println("Conflicts");
        List<Interval> intervals = new ArrayList<>(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)));
        List<List<Interval>> conflicts = MergeIntervals.conflicts(intervals);
        if(conflicts.get(0).isEmpty())
        {
            System.out.print("[]");
        }
        else 
        {
            for(List<Interval> list : conflicts)
            {
                for (Interval item : list)
                System.out.print("[" + item.start + "," + item.end + "] ");
    
            }// for
        }//
     
        System.out.println();
    
        intervals = new ArrayList<>(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)));
        conflicts = MergeIntervals.conflicts(intervals);
        if(conflicts.get(0).isEmpty())
        {
            System.out.print("[]");
        }
        else 
        {
            for(List<Interval> list : conflicts)
            {
                for (Interval item : list)
                System.out.print("[" + item.start + "," + item.end + "] ");
    
            }// for
        }//
        System.out.println();
    
        intervals = new ArrayList<>(Arrays.asList(new Interval(4, 5), new Interval(2, 3), new Interval(3, 6)));
        conflicts = MergeIntervals.conflicts(intervals);
        if(conflicts.get(0).isEmpty())
        {
            System.out.print("[]");
        }
        else 
        {
            for(List<Interval> list : conflicts)
            {
                for (Interval item : list)
                System.out.print("[" + item.start + "," + item.end + "] ");
    
            }// for
        }//
        System.out.println();

        intervals = new ArrayList<>(Arrays.asList(new Interval(4, 5), new Interval(2, 3), new Interval(3, 6), new Interval(5, 7), new Interval(7, 8)));
        conflicts = MergeIntervals.conflicts(intervals);
        if(conflicts.get(0).isEmpty())
        {
            System.out.print("[]");
        }
        else 
        {
            for(List<Interval> list : conflicts)
            {
                for (Interval item : list)
                System.out.print("[" + item.start + "," + item.end + "] ");
    
            }// for
        }//
        System.out.println();

    }  
    
}//Class
