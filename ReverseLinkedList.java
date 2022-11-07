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

    public static void main(String[] args) {

        // Reverse a linkedlist
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        System.out.print("Nodes of the reversed LinkedList are: ");
        head = reverseList(head);
        ListNode result = head;
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
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
