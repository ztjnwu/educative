import java.nio.file.FileStore;
import java.util.*;

class ListNode
{
    int value = 0;
    ListNode next;
    
    ListNode(int value)
    {
        this.value = value;
        this.next = null;
    }
}


class LinkedListCycle
{
    public static boolean hasCycle(ListNode head)
    {   
        //Base Check
        if(head == null)
        {
            return false;
        }
        
        //Initialization
        boolean result = false;
        
        //Find a cycle
        ListNode s = head, f = head.next == null ? null: head.next.next; 
        while(f != null && f != s)
        {
            s = s.next; // move forward one step
            if(f.next != null)
            {
                f = f.next.next; // move forward two steps
            }
            else 
            {
                f = null;
            }
        }
        
        if(f == null)
        {
            result = false;
        }//
        else
        {
            result = true;
        }

        //return
        return result;  
    } 


    public static int calculateLength(ListNode head)
    {   
        //Base Chcek
        if(head == null)
        {
            return 0;
        }
        
        //Initialization
        int result = 0;


        //Computer the length of the targeted list
        ListNode s = head;
        ListNode f = head.next == null ? null : head.next.next; 
        int length = 1;
        if(head.next != null)
        {
            if(head.next.next != null)
            {
                length = 3;
            }
            else 
            {
                length = 2;
            }
        }//
        
        while(f != null && f != s)
        {   
            s = s.next;
            if(f.next != null)
            {
                f = f.next.next;
                if(f == null)
                {
                    length++;
                }
                else
                {
                    length += 2;
                }  
            }
            else 
            {
                f = null;
            }
                
        }
        
        if(f == null)
        {
            result = length;
        }
        else
        {
            //Initialization
            ListNode temp = f.next; //we consider that sp.next is the first element of the linkedlist.
            length = 0;

            //Calculate the length of the cycle
            while(temp != f)
            {
                length++;
                temp = temp.next;
            }
            length++;

            //update result
            result = length;
        }

        //return
        return result;  
    } 
    

    public static ListNode findCycleStart(ListNode head)
    {
        //Base Check
        if(head == null)
        {
            return null;
        }
        
        //Initialization
        ListNode result = null;
        
        //Check a cycle
        ListNode s = head;
        ListNode f = head.next == null ? null : head.next.next;
        while(f != null && (f != s))
        {
            s = s.next;
            if(f.next != null)
            {
                f = f.next.next;
            }
            else 
            {
                f = null;
            }
        }//

        //judgement
        if(f == null)
        {
            result = null;
        }
        else // f == s
        {
            //Calculate the length of the cycle
            ListNode temp = f.next;
            int length = 0;
            while(temp != f)
            {
                length++;
                temp = temp.next;
            }
            length++;

            //p2 is length - 1 nodes ahead of p1 
            ListNode start = head, end = head;
            int k = 0;
            while(k != length - 1)
            {
                end = end.next;
                k++;
            }

            //both p1 and p2 move forward by 1
            while(end.next != start)
            {
                end = end.next;
                start = start.next;
            }
            
            //set result to p1
            result = start;
    
        }
    
        //return result
        return result;
    }//


    public static boolean findHappyNumber(int number)
    {
        //Base Chcek
        if(number <= 0)
        {
            return false;
        }

        //Initialization
        boolean result = false;

        //Find happy number
        int s, f;
        s = number;
        f = squareNumber(squareNumber(number));
        while(f != 1 && f != s)
        {
            s = squareNumber(s);
            f = squareNumber(squareNumber(f));

        }//while
        
        //Update
        if(f == 1)
        {
            result = true;
        }
        else //s == f 
        {
            result = false;
        }
        
        //return
        return result;
    }


    private static int squareNumber(int num)
    {
        //Base Check
        if(num <= 0)
        {
            return 0; 
        }

        //Initialization
        int result = -1;

        //Square number
        int sum = 0;
        while(num != 0)
        {
            sum += (num % 10) * (num % 10);
            num /= 10;
        }
        
        //Return
        result = sum;
        return result;
    }


    public static ListNode findMiddleItem(ListNode head)
    {
        if(head == null)
        {
            return null;
        }

        //find the position
        ListNode sp = head, fp = null;
        if(head.next != null)
            fp = sp.next.next;
        ListNode result = null;
        while(fp != null)
        {
            sp = sp.next;
            if(fp.next == null)
            {
                fp = null;
            }
            else 
            {
                fp = fp.next.next;
            }
        }

        result = sp;

        //return 
        return result;
    }
    

    public static void main(String[] args)
    {   
        //initialize List
        ListNode head = null;
        for(int i = 7; i >= 1; i--)
        {
            ListNode temp = new ListNode(i);
            if(head == null)
            {
                head = temp;
            }
            else 
            {
                temp.next = head;
                head = temp;
            }
        }
        
        //Show all of the elements 
        ListNode p = head; 
        System.out.print("All of elements:");
        while(p != null)
        {
            System.out.print(" " + p.value);
            p = p.next;
        }//
        System.out.println("\n");
        
        //check cycle
        System.out.println("Detect cycle");
        System.out.println(LinkedListCycle.hasCycle(head));
        head.next.next.next.next.next.next = head.next.next;
        System.out.println(LinkedListCycle.hasCycle(head));
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(LinkedListCycle.hasCycle(head));
        System.out.println();

        //calculate length
        System.out.println("Calculate length");
        head.next.next.next.next.next.next = null; //head.next.next;
        System.out.println(LinkedListCycle.calculateLength(head));
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(LinkedListCycle.calculateLength(head));
        System.out.println();

        //find the start of the cycle
        System.out.println("Find the start of the cycle");
        head.next.next.next.next.next.next = head.next.next;
        System.out.println(LinkedListCycle.findCycleStart(head).value);
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(LinkedListCycle.findCycleStart(head).value);
        head.next.next.next.next.next.next = head;
        System.out.println(LinkedListCycle.findCycleStart(head).value);
        System.out.println();

        //Find happy number
        System.out.println("Find the start of the cycle");
        System.out.println(LinkedListCycle.findHappyNumber(23));
        System.out.println(LinkedListCycle.findHappyNumber(12));
        System.out.println(LinkedListCycle.findHappyNumber(1));
        System.out.println(LinkedListCycle.findHappyNumber(2));
        System.out.println();


        //Find the middle item
        ListNode head1 = null;
        for(int i = 8; i >= 1; i--)
        {
            ListNode temp = new ListNode(i);
            if(head1 == null)
            {
                head1 = temp;
            }
            else 
            {
                temp.next = head1;
                head1 = temp;
            }
        }

        System.out.println("\nMiddle Node: " + LinkedListCycle.findMiddleItem(head1).value);

    } 
    
}//Class