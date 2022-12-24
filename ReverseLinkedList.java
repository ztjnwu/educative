import java.util.*;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
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

        //Intitializaiont
        ListNode result = null;

        //Reverse every K elements
        ListNode start = null, rear = null;
        ListNode reversedStart = null, reversedRear = null;
        ListNode p = head;
        int index = 0;
        head = null;
        while(p != null)
        {
            //
            ListNode temp = p.next;

            //update {start, rear}
            if(start == null && rear == null)
            {
                start = p;
                rear = p;
                rear.next = null;
            }
            else 
            {
                p.next = start;
                start = p;
            }// 

            //update {reversedStart, reversedRear}
            if(index == K - 1)
            {
                if(reversedRear != null)
                {
                    reversedRear.next = start;
                    reversedRear = rear;

                }// if
                else 
                {
                    reversedRear = rear;
                    if(head == null)
                    {
                        reversedStart = start;
                    }//
                }

                start = null;
                rear = null; 

            }//

            //Update
            p = temp;
            index = (index + 1) % K;

        }// while

        reversedRear.next = start;
        head = reversedStart;

        //return
        result = head;
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
    }

}