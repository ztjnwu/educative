import java.util.*;

public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        // check validity
        if (head == null) {
            return null;
        }

        //
        ListNode p = head.next;
        head.next = null;
        ListNode temp = null;
        while (p != null) {
            temp = p.next;
            p.next = head;
            head = p;
            p = temp;
        } //

        // return
        return head;
    }

    public static ListNode reverseSubList(ListNode head, int p, int q)
    {
        //check validity
        if(head == null)
        {
            return null;
        }

        //Find the position of the node of value p
        ListNode pre = null, current = head, rear = null;
        while(current.value != p)
        {
            pre = current;
            current = current.next;
        }
    

        //Reverse the nodes of values ranging from p to q
        ListNode temp = null;
        while(current != null && current.value != q)
        {
            temp = current.next;
            current.next = pre.next;
            pre.next = current;
            if(rear == null)
            {
                rear = current;
                rear.next = null;
            }
            current = temp;
            temp = null; 
        }

        //connect with the remaining part of the original list
        ListNode result = null;
        if(current == null)
        {
            result = null;
        }
        else 
        {
            temp = current.next;
            current.next = pre.next;
            pre.next = current;
            rear.next = temp;
            rear = null;
            result = head;
        }
        
        //return
        return head;

    }

    public static void main(String[] args) {

        // Reverse a linkedlist
        System.out.print("Nodes of the reversed LinkedList are: ");
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head = reverseList(head);
        ListNode p = head;
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();


        //reverse sub-list
        System.out.print("Nodes of the reversed subList are: ");
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
        System.out.println();
        
    }

}

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}
