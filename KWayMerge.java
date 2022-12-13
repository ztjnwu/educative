import java.util.*;

public class KWayMerge {
    public static ListNode mergeKWayList(List<List<ListNode>> lists)
    {
        //Check validity
        if(lists == null)
        {
            return null;
        }//

        //merge
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((a, b) -> a.value - b.value);

        //Initialization
        for(int i = 0; i < lists.size(); i++)
        {
            minHeap.add(lists.get(i).get(0));
        }//
        
        //Merge
        ListNode head = null, tail = null;
        while(!minHeap.isEmpty())
        {
            ListNode temp = minHeap.poll();

            if(temp.next != null)
            {
                minHeap.add(temp.next);
            }
            temp.next = null;

            //NULL list
            if(head == null)
            {
                head = temp;
                tail = head;
            }// if
            
            //non-null lilst
            tail.next = temp;
            tail = temp;
        
        }//while

        //return
        return head;

    }//public


    public static int findKthsmallestInManyList(List<List<Integer>> lists, int K)
    {
        //Check validity
        if(lists == null)
        {
            return -1;
        }

        //
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((a, b) -> a.value - b.value);
        for(int i = 0; i < lists.size(); i++)
        {
            minHeap.add(new Node(i, 0, lists.get(i).get(0)));
        }
        
        //
        int num = 0;
        Node temp = null;
        while(!minHeap.isEmpty() && num != K )
        {
            temp = minHeap.poll();
            num++;
            if(temp.colum + 1 < lists.get(temp.row).size())
            {
                int row = temp.row;
                int colum = temp.colum + 1;
                int value = lists.get(temp.row).get(temp.colum);
                minHeap.add(new Node(row, colum, value));
            }
            
        }//

        //return
        int result = temp.value;
        return result;

    }

    public static int findKthSmallestInMatrix(int[][] matrix, int K)
    {
        //Check validity
        if(matrix == null)
        {
            return -1;
        }

        //Initialization
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((a, b) -> a.value - b.value);
        for(int i = 0; i < matrix.length; i++)
        {
            minHeap.add(new Node(i, 0, matrix[i][0]));
        }// for
        
        //Find kth item
        int sum = 0;
        Node temp = null;
        while(!minHeap.isEmpty() && sum != K)
        {
            temp = minHeap.poll();
            sum++;
            if(temp.colum + 1 <= matrix[temp.row].length - 1)
            {
                minHeap.add(new Node(temp.row, temp.colum + 1, matrix[temp.row][temp.colum + 1]));
            }//
    
        }//

        //
        if(sum == K)
        {
            return temp.value;
        }//
        else //minHeap.isEmpty() == null
        {
            return -1;
        }//

        
    }//



    public static void main(String[] argv)
    {
        //Merger many lists
        System.out.println("Merge many lists");

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);
        List<ListNode> a = Arrays.asList(l1, l1.next, l1.next.next);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);
        List<ListNode> b = Arrays.asList(l2, l2.next, l2.next.next);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);
        List<ListNode> c = Arrays.asList(l3, l3.next, l3.next.next);

        List<List<ListNode>> lists = Arrays.asList(a, b, c);

        ListNode result = KWayMerge.mergeKWayList(lists);
        ListNode temp = result;
        while(temp != null)
        {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }//while

        System.out.println("\n");

        //Find the kth elements in sorted lists
        System.out.println("Find Kth smallest item in lists" );
        List<Integer> l4 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> l5 = Arrays.asList(6, 7, 8, 9, 10);
        List<Integer> l6 = Arrays.asList(11, 12, 13, 14, 15);
        List<List<Integer>> lists_integer = Arrays.asList(l4, l5, l6);
        System.out.println(lists_integer);

        int result_int = KWayMerge.findKthsmallestInManyList(lists_integer, 15);
        System.out.print("Kth smallest number is: " + result_int);
        System.out.println();

        //Find the kth elements in matrix
        System.out.println("\nFind Kth smallest item in a matrix");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        result_int = KWayMerge.findKthSmallestInMatrix(matrix, 5);
        System.out.print("Kth smallest number is: " + result_int);
        System.out.println();
        
    }//public
    
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

class Node
{
    int row;
    int colum;
    int value;
    
    Node(int row, int colum, int value)
    {
        this.row = row;
        this.colum = colum;
        this.value = value;
    }//

}
