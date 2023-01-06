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


public class ReverseLinkedList 
{
    public static ListNode reverseList(ListNode head) 
    {
        //Base check
        if (head == null) 
        {
            return null;
        }

        //Initialization
        ListNode result = null;
        
        //Reverse the list
        ListNode p = head.next;
        head.next = null;
        while (p != null) 
        {
            ListNode temp = p.next;
            p.next = head;
            head = p;
            p = temp;
        } //

        // return
        result = head;
        return result;
    }


    public static ListNode reverseSubList(ListNode head, int p, int q)
    {
        //Base Check
        if(head == null)
        {
            return null;
        }

        //Initiaization
        ListNode current = head, start = null, rear = null, pre = null;
        while(current != null && current.value != p)
        {
            pre = current;
            current = current.next;
        }
        
        if(current == null)
        {
            start = null;
        }
        else 
        {
            start = current;
            while(current != null && current.value != q)
            {
                current = current.next;
            }//

            if(current == null)
            {
                rear = null;
            }//
            else 
            {
                rear = current;
            }//

        }//

    
        //Reverse the nodes of values ranging from p to q
        ListNode result = null;
        if(start == null && rear == null)
        {
            result = null;
        }
        else 
        {
            //combine the first part with the last part
            pre.next = rear.next;
            rear.next = null;

            //reverse the sublist
            ListNode pp = start;
            while(pp != null)
            {
                ListNode temp = pp.next;
                pp.next = pre.next;
                pre.next = pp;
                pp = temp;
            }//

            //update result
            result = head;
        }
      

        //return
        return result;
    }//


    public static ListNode reverseEveryKElement(ListNode head, int K)
    {
        //Base Check
        if(head == null || K <= 0)
        {
            return null;
        }

        //Initializaion
        ListNode result = null;

        //Reverse every K elements
        ListNode reversedStart = new ListNode(Integer.MAX_VALUE);
        ListNode reversedRear = reversedStart;
        ListNode rear = null;
        ListNode p = head;
        int index = 0;
        while(p != null)
        {
            //Store next node
            ListNode temp = p.next;
            p.next = null;

            //Add current node into the tail of resulting list
            p.next = reversedRear.next;
            reversedRear.next = p;

            //Update resulting set
            if(index == 0)
            {
                rear = p;
            }
            else if(index == K - 1)
            {
                reversedRear = rear;
            }//

            //Update index and p
            p = temp;
            index = (index + 1) % K;

        }// while

        //return
        result = reversedStart.next;
        return result;
    }


    public static ListNode reverseEveryAlternativeKElements(ListNode head, int K)
    {
        //Base Check
        if(head == null || K == -1)
        {
            return null;
        }

        //Init
        ListNode result = null;

        //Reverse
        ListNode reversedStart = new ListNode(Integer.MAX_VALUE);
        ListNode reversedRear = reversedStart;
        ListNode p = head;
        ListNode rear = null;
        int index = 0;
        while(p != null)
        {
            //Store
            ListNode temp = p.next;
            p.next = null;

            //Insert
            if(index >= 0 && index <= K - 1)
            {
                p.next = reversedRear.next;
                reversedRear.next = p;
            }
            else 
            {
                reversedRear.next = p;
                reversedRear = p;
            }

            //Updare reversedRear
            if(index == 0)
            {
                rear = p;
            }
            else if(index == K - 1)
            {
                reversedRear = rear;
            }//

            //Update p and index
            p = temp;
            index = (index + 1) % (2 * K);
        }

        //return
        result = reversedStart.next;
        return result;

    }

    public static void main(String[] args) 
    {

        // Reverse a linkedlist
        System.out.println("Nodes of the reversed LinkedList are: ");
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        System.out.print("Original list : ");
        ListNode p = head;
        while (p != null) 
        {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();

        System.out.print("Reversed list : ");
        head = reverseList(head);
        p = head;
        while (p != null) 
        {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println("\n");


        //Reverse sub-list
        System.out.println("Nodes of the reversed subList are: ");
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head = ReverseLinkedList.reverseSubList(head, 2, 4);
        p = head;
        while(p != null)
        {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println("\n");


        //Reverse every k-element sublist
        System.out.println("Reverse every k-element sublist");
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head = ReverseLinkedList.reverseEveryKElement(head, 3);
        p = head;
        while (p != null) 
        {
            System.out.print(p.value + " ");
            p = p.next;
        }//

        System.out.println("\n");

        //Reverse Alternative k element sublist
        System.out.println("Reverse Alternative k element sublist");
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
    
        ListNode result = ReverseLinkedList.reverseEveryAlternativeKElements(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) 
        {
          System.out.print(result.value + " ");
          result = result.next;
        }//

        System.out.println("\n");

    }

}