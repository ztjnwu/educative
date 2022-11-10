import java.util.*;

public class KWayMerge {
    public static ListNode mergeKWayList(ListNode[] lists)
    {
        if(lists == null)
        {
            return null;
        }

        //merge
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(Comparator.naturalOrder());

        //Initialization
        for(int i = 0; i < lists.length; i++)
        {
            minHeap.add(lists[i]);
            lists[i] = lists[i].next;
        }
        
        //Merge
        ListNode head = null, tail = null;
        while(!minHeap.isEmpty())
        {
            ListNode temp = minHeap.poll();
            minHeap.add(temp.next);
            temp.next = null;

            if(head == null)
            {
                head = temp;
            }//   
            
            tail.next = temp;
            temp.next = null;

        }//while

        //return
        return head;

    }//public


    public static void main(String[] argv)
    {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = KWayMerge.mergeKWayList(new ListNode[] {l1, l2, l3});
        ListNode temp = result;
        while(temp != null)
        {
            System.out.println(temp.value);
            temp = temp.next;
        }//while

    }
    
}


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
