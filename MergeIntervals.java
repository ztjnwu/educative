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


class Job
{
    int start;
    int end;
    int load;
    
    public Job(int start, int end, int load)
    {
        this.start = start;
        this.end = end;
        this.load = load;
    }//
}


class Meeting
{
    int start;
    int end;

    public Meeting(int start, int end)
    {
        this.start = start;
        this.end = end;
    }
}

class Employee
{
    Interval inter;
    int employeeID;
    int intervalID;

    public static Employee(Interval inter, int employeeID, int intervalID)
    {
        this.inter = inter;
        this.employeeID = employeeID;
        this.intervalID = intervalID;
    }//
}//


public class MergeIntervals 
{

    public static List<Interval> merge(List<Interval> input)
    {
        //Base check
        if(input == null)
        {
            return null;
        }//

        //Init
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


    public static int findMaxRooms(List<Interval> input)
    {
        //Base Check
        if(input == null || input.size() == 0)
        {
            return 0;
        }//

        //Init
        int result = 0;

        //Sort
        Collections.sort(input, (a, b) -> a.start - b.start);

        //Compute the number of rooms
        PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>((a, b) -> a.end - b.end);
        minHeap.offer(input.get(0));
        int currentRooms = 1;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < input.size(); i++)
        {
            
            //Filter out non-active meetings
            Interval currentInterval = input.get(i);
            while(!minHeap.isEmpty() && minHeap.peek().end <= currentInterval.start)
            {
                minHeap.poll();
            }//
            
            //Add a new meeting
            minHeap.offer(currentInterval);

            //Update currentRooms
            currentRooms = minHeap.size();
            max = Math.max(max, currentRooms);
        }//

        //return
        result = max;
        return result;
    }


    public static int findMaxCpuLoad(List<Job> input)
    {
        //Base Check
        if(input == null || input.size() == 0)
        {
            return 0;
        }

        //Init
        int result = 0;

        //Sort
        Collections.sort(input, (a, b) -> a.start - b.start);

        //Compute the maximum workload
        PriorityQueue<Job> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);
        minHeap.offer(input.get(0));
        int currentLoad = input.get(0).load;
        int maxLoad = Integer.MIN_VALUE;
        for(int i = 1; i < input.size(); i++)
        {
            //Filter out unqualified
            while(!minHeap.isEmpty() && input.get(i).start >= minHeap.peek().end)
            {
                currentLoad -= minHeap.poll().load;
            }// while

            //Add
            minHeap.offer(input.get(i));

            //Update
            currentLoad += input.get(i).load;
            maxLoad = Math.max(maxLoad, currentLoad);

        }// For


        //return
        result = maxLoad;
        return result;

    }//


    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> input)
    {
        //Base Check
        if(input == null || input.size() == 0)
        {
            return null;
        }//

        //Init
        List<Interval> result = new ArrayList<>();

        //Flatten input
        List<Interval> input_flattern = new ArrayList<>();
        for(int i = 0 ; i < input.size(); i++)
        {
            for(int j = 0; j < input.get(i).size(); j++)
            {
                input_flattern.add(input.get(i).get(j));
            }//
        }//
        for(int i = 0 ; i < input_flattern.size(); i++)
        {
            System.out.print("[" + input_flattern.get(i).start + ", " + input_flattern.get(i).end + "] ");
        }//

        //Sort
        Collections.sort(input_flattern, (a, b) -> a.start - b.start);

        //Free intervals
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);
        minHeap.offer(input_flattern.get(0));
        for(int i = 1; i < input_flattern.size(); i++)
        {
            Interval temp = null;
            while(!minHeap.isEmpty() && input_flattern.get(i).start > minHeap.peek().end)
            {
                temp = minHeap.poll();
            }//

            if(minHeap.isEmpty())
            {
                result.add(new Interval(temp.end, input_flattern.get(i).start));
            }//

            //ADD
            minHeap.offer(input_flattern.get(i));

        }//
        
        //return 
        return result;

    }
    


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
        System.out.println();



        //Find Max rooms
        System.out.println("Compute the minimum rooms required for meetings");
        input = new ArrayList<Interval>() 
        {
        {
            add(new Interval(1, 4));
            add(new Interval(2, 5));
            add(new Interval(7, 9));
        }
        };
        System.out.println(MergeIntervals.findMaxRooms(input));

        input = new ArrayList<Interval>() 
        {
        {
            add(new Interval(6, 7));
            add(new Interval(2, 4));
            add(new Interval(8, 12));
        }
        };
        System.out.println(MergeIntervals.findMaxRooms(input));

        input = new ArrayList<Interval>()
        {
        {
            add(new Interval(1, 4));
            add(new Interval(2, 3));
            add(new Interval(3, 6));
        }
        };
        
        System.out.println(MergeIntervals.findMaxRooms(input));;

        input = new ArrayList<Interval>() {
        {
            add(new Interval(4, 5));
            add(new Interval(2, 3));
            add(new Interval(2, 4));
            add(new Interval(3, 5));
        }
        };
        System.out.println(MergeIntervals.findMaxRooms(input));
        System.out.println();

        //Find the maximum workload
        System.out.println("Find the maximum workload");
        List<Job> input_job = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println(MergeIntervals.findMaxCpuLoad(input_job));
        input_job = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println(MergeIntervals.findMaxCpuLoad(input_job));
        input_job = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println(MergeIntervals.findMaxCpuLoad(input_job));
        System.out.println();

        //Find the free intervals
        System.out.println("Find the free intervals");
        List<List<Interval>> input_interval = new ArrayList<>();
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result_employee = MergeIntervals.findEmployeeFreeTime(input_interval);
        System.out.print("Free intervals: ");
        for (Interval interval : result_employee)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input_interval = new ArrayList<>();
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result_employee = MergeIntervals.findEmployeeFreeTime(input_interval);
        System.out.print("Free intervals: ");
        for (Interval interval : result_employee)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input_interval = new ArrayList<>();
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input_interval.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result_employee = MergeIntervals.findEmployeeFreeTime(input_interval);
        System.out.print("Free intervals: ");
        for (Interval interval : result_employee)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        
        System.out.println();

    }// main
    
}//Class
