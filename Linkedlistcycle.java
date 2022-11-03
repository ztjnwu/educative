class Listnode
{
    int value = 0;
    Listnode next;
    
    Listnode(int value)
    {
        this.value = value;
        this.next = null;
    }
}


class Linkedlistcycle
{
    public static boolean hasCycle(Listnode head)
    {   
        //initialization
        if(head == null)
        {
            return false;
        }
        
        //judgement
        Listnode sp = head, fp = head.next.next; 
        boolean flag = false;
        boolean result = false;
        while(sp != null && fp !=null)
        {
            if(sp == fp)
            {
                flag = true;
                break;
            }
            else 
            {
                sp = sp.next;
                fp = fp.next.next;
            }
        }
        if(flag == true)
        {
            result = true;
        }

        //return
        return result;  
    } 
    
    public static int calculateLength(Listnode head)
    {   
        //initialization
        if(head == null)
        {
            return 0;
        }
        
        //judgement
        Listnode sp = head, fp = head.next.next; 
        boolean flag = false;
        while(sp != null && fp !=null)
        {   
            if(sp == fp)
            {
                flag = true;
                break;
            }
            else 
            {   
                sp = sp.next;
                fp = fp.next.next;
            }
        }
        
        int length = 0;
        if(flag == true)
        {
            Listnode temp = sp.next; //we consider that sp.next is the first element of the linkedlist.
            while(temp != sp)
            {
                length++;
                temp = temp.next;
            }
            length++;
        }

        //return
        return length;  
    } 

    public static void main(String[] args)
    {   
        //initialize List
        Listnode head = null;
        for(int i = 0; i < 6; i++)
        {
            Listnode temp = new Listnode(i);
            if(head == null)
            {
                head = temp;
                head.next = null;
            }
            else 
            {
                temp.next = head;
                head = temp;
            }
        }
        
        //show all of the elements 
        Listnode p = head; 
        
        while(p != null)
        {
            System.out.print(" " + p.value);
            p = p.next;
        }
        
        //To do list
        //check cycle
        System.out.println("\n LinkedList has cycle: " + Linkedlistcycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + Linkedlistcycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + Linkedlistcycle.hasCycle(head));

        //calculate
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: " + Linkedlistcycle.calculateLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + Linkedlistcycle.calculateLength(head));
    } 
}//Class

