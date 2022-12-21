import java.util.*;

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
        Iterator<Interval> itr = input.iterator();
        Interval current = itr.next();
        int start = current.start, end = current.end;
        while(itr.hasNext())
        {
            current = itr.next();
            if(current.start <= end)
            {
                end = Math.max(current.end, end);
            }
            else 
            {
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }//

        }//
        
        result.add(new Interval(start, end)); // add the last interval to the set of result

        //return
        return result;
    
    }//
    
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

    }  
    
    




}//Class


