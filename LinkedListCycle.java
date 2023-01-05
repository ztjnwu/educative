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
        
        //Init
        boolean result = false;
        
        //Find a cycle
        ListNode s = head;
        ListNode f = head.next == null ? null: head.next.next; 
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
        }// while
        
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
    }// 


    public static int calculateLength(ListNode head)
    {   
        //Base Chcek
        if(head == null)
        {
            return 0;
        }
        
        //Initialization
        int result = 0;

        //Compute the length of the targeted list
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
        
        //Init
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

        //Judgement
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
        //Base Check
        if(head == null)
        {
            return null;
        }//

        //Init
        ListNode result = null;

        //Find the position
        ListNode s = head;
        ListNode f = head.next == null ? null: head.next.next;
        while(f != null && f != s)
        {
            s = s.next;
            if(f.next != null)
            {
                f = f.next.next;
            }
            else 
            {
                f = null; 
            }//

        }//
        
        if(f == null)
        {
            result = s;
        }
        else 
        {
            result = null;
        }

        //Return 
        return result;

    }//
    

    public static boolean isPalindrome(ListNode head)
    {
        //Base Check
        if(head == null)
        {
            return false;
        }

        //Init
        boolean result = false;

        //Check
        ListNode s = head;
        ListNode f = head.next == null ? null : head.next.next;
        while(f != null && f != s)
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

        if(f == s)// There is a cycle
        {
            return false;
        }
        

        //Reverse the second part
        ListNode head2 = null;
        ListNode p = s.next;
        s.next = null;
        while(p != null)
        {
            ListNode temp = p.next;
            p.next = null;

            if(head2 == null)
            {
                head2 = p;
            }// 
            else 
            {
                p.next = head2;
                head2 = p;
            }//
            
            p = temp;
        }//

    
        //Compare the first part and the second part
        ListNode p1 = head, p2 = head2;
        while(p1 != null && p2 != null)
        {
            //To Do
            if(p1.value != p2.value)
            {
                break;
            }

            //Update p1 and p2
            p1 = p1.next;
            p2 = p2.next;

        }//

        if(p1 == null && p2 == null || p1 != null && p2 == null) // the length of the list is even, and the length of the list is odd
        {
            result = true;
        }//
        else 
        {
            result = false;
        }//


        //Restore the second part
        p = head2;
        head2 = null;
        while(p != null)
        {
            ListNode temp = p.next;
            p.next = null;

            if(head2 == null)
            {
                head2 = p;
            }// 
            else 
            {
                p.next = head2;
                head2 = p;
            }//

            p = temp;
        }// 

        //Concacinate the first part and second part
        s.next = head2;

        System.out.print("Restore to the original form: ");
        p = head;
        while(p != null)
        {
            System.out.print(" " + p.value);
            p = p.next;
        }
        System.out.println();

        //return
        return result;
    }//


    public static ListNode reorder(ListNode head)
    {
        //Base Check
        if(head == null)
        {
            return null;
        }

        //Init
        ListNode result = null;

        //Find the middle node
        ListNode s = head;
        ListNode f = head.next == null ? null : head.next.next;
        while(f != null && f != s)
        {
            s = s.next;
            if(f.next != null)
            {
                f = f.next.next;
            }
            else
            {
                f = null;
            }//
        }//
        
        if(f == s)
        {
            return null;
        }//


        //Reverse the seconde part
        ListNode head2 = null;
        ListNode p = s.next;
        s.next = null;
        while(p != null)
        {
            ListNode temp = p.next;
            p.next = null;

            if(head2 == null)
            {
                head2 = p;
            }
            else 
            {
                p.next = head2;
                head2 = p;
            }

            p = temp;
        }//

        //Reorder the first part
        ListNode p1 = head;
        ListNode p2 = head2;
        while(p1 != null && p2 != null)
        {
            //init
            ListNode temp = p2.next;
            p2.next = null;
            
            //To Do
            p2.next = p1.next;
            p1.next = p2;

            //update 
            p1 = p1.next.next;
            p2 = temp;
        }//

        //Display the new list
        System.out.print("Restored list: ");
        p = head;
        while(p != null)
        {
            System.out.print(" " + p.value);
            p = p.next;
        }
        System.out.println();
    
        //return
        result = head;
        return result;
    }//


    public static boolean loopExists(int[] arr)
    {
        //Base Check
        if(arr == null)
        {
            return false;
        }

        //Init
        boolean result = false;

        //Check
        for(int i = 0; i < arr.length; i++)
        {
            int direction = arr[0] > 0 ? 1 : 0;
            int s = 0, f = arr[0] + arr[arr[0]];
            if(direction == 1)
            {
                f = arr[0] + arr[arr[0]];
            }//
            else 
            {
                f = arr[0] + arr[arr]
            }//


            while(s != f)
            {

            }
        }
        


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
        
        //Check cycle
        System.out.println("Detect cycle");
        System.out.println(LinkedListCycle.hasCycle(head));
        head.next.next.next.next.next.next = head.next.next;
        System.out.println(LinkedListCycle.hasCycle(head));
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(LinkedListCycle.hasCycle(head));
        System.out.println();

        //Calculate length
        System.out.println("Calculate length");
        head.next.next.next.next.next.next = null; //head.next.next;
        System.out.println(LinkedListCycle.calculateLength(head));
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(LinkedListCycle.calculateLength(head));
        System.out.println();

        //Find the start of the cycle
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
        System.out.println("Find the middle item");
        ListNode head1 = null;
        for(int i = 9; i >= 1; i--)
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

        System.out.println("Middle Node: " + LinkedListCycle.findMiddleItem(head1).value);
        System.out.println();


        //Test palapriemde list
        System.out.println("Find if it is Palindrome");
        head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println(LinkedListCycle.isPalindrome(head));
        head.next.next.next.next.next = new ListNode(2);
        System.out.println(LinkedListCycle.isPalindrome(head));
        System.out.println();

        //Rearrange a single linked list
        System.out.println("Rearrange a single linked list");
        head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        LinkedListCycle.reorder(head);
        System.out.println();

        //Cycle an array
        System.out.println("Cycle in a array");
        System.out.println(LinkedListCycle.loopExists(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println(LinkedListCycle.loopExists(new int[] { 2, 2, -1, 2 }));
        System.out.println(LinkedListCycle.loopExists(new int[] { 2, 1, -1, -2 }));



        
    } 
    
}//Class