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
        
        //check if there is a cycle in the linkedlist
        ListNode sp = head, fp = head.next.next;
        while(fp != null && (sp != fp))
        {
            sp = sp.next;
            if(fp.next != null)
            {
                fp = fp.next.next;
            }
            else 
            {
                fp = null;
            }
        }

        //judgement
        ListNode result = null;
        if(sp == fp)
        {
            //calculate the length of the cycle
            ListNode p1 = sp, p2 = fp.next;
            int length = 0;
            while(p1 != p2)
            {
                length++;
                p2 = p2.next;
            }
            length++;

            //locate the start of the cycle
            p1 = head;
            p2 = head;
            while(length != 0)
            {
                p2 = p2.next;
                length--;
            }

            //both p1 and p2 move forward by 1
            while(p1 != p2)
            {
                p1 = p1.next;
                p2 = p2.next;
            }
            
            //set result to p1
            result = p1;

        }
        else // sp == null
        {
            result = null;
        }

        //return result
        return result;
    }//


    public static boolean findHappyNumber(int number)
    {
        if(number <= 0)
        {
            return false;
        }

        //Find happy number
        int s, f;
        s = correspondingNumber(number);
        f = correspondingNumber(correspondingNumber(number));
        boolean result = false;
        while(s != 1 && s != f)
        {
            s = correspondingNumber(s);
            f = correspondingNumber(correspondingNumber(f));

        }//while
        
        //judgement
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


    private static int correspondingNumber(int num)
    {
        int sum = 0;
        while(num != 0)
        {
            sum += (num % 10) * (num % 10);
            num = num / 10;
        }
        
        //return
        return sum;
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
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));
        System.out.println("\n");

        //calculate length
        System.out.println("Calculate length");
        head.next.next.next.next.next.next = null; //head.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycle.calculateLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycle.calculateLength(head));

        //find the start of the cycle
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("\n LinkedList cycle start: " + LinkedListCycle.findCycleStart(head).value);
    
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycle.findCycleStart(head).value);
    
        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + LinkedListCycle.findCycleStart(head).value);

        //Find happy number
        System.out.println("\n result: " + LinkedListCycle.findHappyNumber(23));
        System.out.println("result: " + LinkedListCycle.findHappyNumber(12));

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