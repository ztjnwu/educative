import java.util.*;

public class BFS 
{

    //Traverse a bst in layer
    public static List<List<Integer>> traverse(BST bst)
    {
        //Check validity
        if(bst == null)
        {
            return null;
        }

        //Initialization
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(bst.getRoot());

        //build the set result
        while(queue.size() != 0)
        {
            int queueLength = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for(int i = 0; i < queueLength; i++)
            {
                TreeNode tempNode = queue.poll(); //dequeue
                 //enqueue left child
                if(tempNode.left != null)
                {
                    queue.add(tempNode.left);
                }
                
                //enqueue right child
                if(tempNode.right != null)
                {
                    queue.add(tempNode.right);
                }

                //enlist
                tempList.add(tempNode.value);
            }//
            
            //Update the set result.
            result.add(tempList);          
        }

        //return
        return result;

    }//


    //reverse the result of traversing a bst in layer
    public static List<List<Integer>> reverseTraverse(BST bst)
    {
        //Chcek validty
        if(bst == null)
        {
            return null;
        }

        //Initialization
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(bst.getRoot());
        
        while(queue.size() != 0)
        {
            int queueLength = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for(int i = 0; i < queueLength; i++)
            {
                TreeNode tempNode = queue.poll();
                if(tempNode.left != null)
                {
                    queue.add(tempNode.left);
                }
                
                if(tempNode.right != null)
                {
                    queue.add(tempNode.right);
                }
                
                tempList.add(tempNode.value);
            }

            //update result
            result.add(0, tempList);
        }

        //return
        return result;

    }


    //Zigzag
    public static List<List<Integer>> zigzagTraverse(BST bst)
    {
        //check validity
        if(bst == null)
        {
            return null;
        }

        //Initialization
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        int level = 1;

        //add nodes in a zigzag way
        while(queue.size() != 0)
        {
            List<Integer> tempList = new ArrayList<>();
            int length = queue.size();
            for(int i = 0; i < length; i++)
            {
                TreeNode tempNode = queue.poll();
                if(tempNode.left != null)
                {
                    queue.add(tempNode.left);
                }
                
                if(tempNode.right != null)
                {
                    queue.add(tempNode.right);
                }

                if(level % 2 == 0)
                {
                    tempList.add(0, tempNode.value);
                }
                else 
                {
                    tempList.add(tempNode.value);
                }//else

            }//for

            //update the set result
            result.add(tempList);
            level++;

        }//while

        //return
        return result;

    }//


    //Average
    public static List<Double> findLevelAverage(BST bst)
    {
        //check validity
        if(bst == null)
        {
            return null;
        }
        
        //Initialization
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        List<Double> averages = new ArrayList<>();

        //
        while(queue.size() != 0)
        {
            int length = queue.size();
            double sum = 0;
            TreeNode current = null;

            for(int i = 0; i < length; i++)
            {
                current = queue.poll();

                if(current.left != null)
                {
                    queue.offer(current.left);
                }
                if(current.right != null)
                {
                    queue.offer(current.right);
                }

                sum += current.value;
                System.out.print(" " + current.value);

            }// for
            System.out.println();
            
            averages.add(sum / length); 

        }// while


        //return
        return averages;

    }//
    

    //Minimum levels
    public static int findMinimumLevel(BST bst)
    {
        if(bst == null)
        {
            return -1;
        }

        //Initialization
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        int currentLevel = 0;
        int miniLevel = Integer.MAX_VALUE;

        //Compute minimum layers
        while(queue.size() != 0)
        {
            int length = queue.size();
            for(int i = 0; i < length; i++)
            {
                TreeNode tempNode = queue.poll();
                if(tempNode.left != null)
                {   
                    queue.offer(tempNode.left);
                }// if

                if(tempNode.right != null)
                {
                    queue.offer(tempNode.right);
                }// if

                //make a judgement
                if(tempNode.left == null && tempNode.right == null)
                {
                    if(miniLevel > currentLevel)
                    {
                        miniLevel = currentLevel;
                    }//
                }// if
                
            }// for

            //update level
            currentLevel++;
        }//

        //return
        return miniLevel;
    }//


    //level order successor
    public static TreeNode findLevelOrderSuccessor(BST bst, int target)
    {
        if(bst == null)
        {
            return null;
        }//

        //Initialization
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        TreeNode result = null;

        //Find successor
        while(!queue.isEmpty())
        {
            int length = queue.size();
            for(int i = 0; i < length; i++)
            {
                TreeNode temp = queue.poll();
                if(temp.left != null)
                {
                    queue.offer(temp.left);
                }

                if(temp.right != null)
                {
                    queue.offer(temp.right);
                }
                
                if(temp.value == target)
                {
                    if(queue.peek() != null)
                    {
                        result = queue.peek();
                    }//
                }//

            }//

        }// while

        //update
        return result;
    }//


    //connect level order successor
    public static boolean connectLevelOrderSuccsor(BST bst)
    {
        //Base Check
        if(bst == null)
        {
            return false;
        }

        //Init
        boolean result = true;

        //Connect level order successors
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        
        while(!queue.isEmpty())
        {
            int length;
            length = queue.size();

            for(int i = 0; i < length; i++)
            {
                TreeNode temp = queue.poll();
                if(temp.left != null)
                {
                    queue.offer(temp.left);
                }
                
                if(temp.right != null)
                {
                    queue.offer(temp.right);
                }
                if(i == length - 1)
                {
                    temp.next = null;
                }
                else 
                {
                    temp.next = queue.peek();
                }//

            }// for
        }// while
        
        //return
        return result;
    }


    //connect level order successor
    public static boolean connectAllLevelOrderSuccsor(BST bst)
    {
        //Base Check
        if(bst == null)
        {
            return false;
        }

        //Init
        boolean result = true;

        //Connect level order successors
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        
        while(!queue.isEmpty())
        {
            int length;
            length = queue.size();

            for(int i = 0; i < length; i++)
            {
                TreeNode temp = queue.poll();
                if(temp.left != null)
                {
                    queue.offer(temp.left);
                }
                
                if(temp.right != null)
                {
                    queue.offer(temp.right);
                }

                temp.next = queue.peek();
            }// for
        }// while
        
        //return
        return result;
    }


    public static List<TreeNode> rightViewOfBinaryTree(BST bst)
    {
        //Base Tree
        if(bst == null)
        {
            return null;
        }//

        //Init
        List<TreeNode> result = new ArrayList<>();
        
        //Find the right view of a binary tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(bst.getRoot());
        while(!queue.isEmpty())
        {
            int length = queue.size();
            for(int i = 0; i < length; i++)
            {
                TreeNode current = queue.poll();
                if(current.left != null)
                {
                    queue.offer(current.left);
                }
                
                if(current.right != null)
                {
                    queue.offer(current.right);
                }

                //
                if(i == length - 1)
                {
                    result.add(current);
                }//
            }//
        }//

        //return
        return result;

    }

    public static void main(String[] args)
    {   
        
        BST bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        bst.inOrder(bst.getRoot());
        System.out.println();

        //Breath First Search 
        System.out.println("\nTraverse a BST in BST.");
        bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        System.out.println("Level order traversal: " + traverse(bst));

        //Reverse BFS
        System.out.println("\nTraverse a BST in BST.");
        bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        System.out.println("Level order traversal: " + reverseTraverse(bst));

        //Zigzag a BST
        System.out.println("\nZigzag a BST in BST.");
        bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        System.out.println("Level order traversal: " + zigzagTraverse(bst));

        //Compute the average of each level
        System.out.println("\n average a BST in BST.");
        bst = new BST(new int[]{12, 7, 1, 9, 2, 10, 5});
        List<Double> result = BFS.findLevelAverage(bst);
        System.out.println(result);

        //Compute minimum level
        System.out.println("\n Minimum layer");
        bst = new BST(new int[]{12, 7, 1, 9, 2, 10, 5});
        System.out.println(BFS.findMinimumLevel(bst));
        bst = new BST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println(BFS.findMinimumLevel(bst));

        //Find the level order successor
        System.out.println("\n layer order successor");
        TreeNode result_node = null;
        bst = new BST(new int[]{1, 2, 3, 4 ,5, 6});
        result_node = BFS.findLevelOrderSuccessor(bst, 6);
        if(result_node != null)
        {
            System.out.println(result_node.value);
        }
        else 
        {
            System.out.println("Last node, no successor");
        }

        bst = new BST(new int[]{12, 7, 1, 9, 10, 5});
        result_node = BFS.findLevelOrderSuccessor(bst, 5);
        if(result_node != null)
        {
            System.out.println(result_node.value);
        }
        else 
        {
            System.out.println("Last node, no successor");
        }
        System.out.println();

        //Connect the level order successor
        System.out.println("CONNECT layer order successor");
        bst = new BST(new int[]{12, 7, 1, 9, 10, 5});
        TreeNode temp = bst.getRoot();
        BFS.connectLevelOrderSuccsor(bst);
        while(temp != null)
        {
            System.out.print(" " + temp.value);
            temp = temp.next;
        }
        System.out.println("\n");

        //Connect the level order successor
        System.out.println("CONNECT All layer order successors");
        bst = new BST(new int[]{12, 7, 1, 9, 10, 5});
        temp = bst.getRoot();
        BFS.connectAllLevelOrderSuccsor(bst);
        while(temp != null)
        {
            System.out.print(" " + temp.value);
            temp = temp.next;
        }
        System.out.println("\n");

        //The right view of a binary tree
        System.out.println("The right view of a binary tree");
        bst = new BST(new int[]{12, 7, 1, 9, 10, 5, 3});
        temp = bst.getRoot();
        List<TreeNode> result_nodes = BFS.rightViewOfBinaryTree(bst);
        for(int i = 0; i < result_nodes.size(); i++)
        {
            System.out.println(result_nodes.get(i).value);
        }//

        System.out.println();

    }//
    
}//

//TreeNode
class TreeNode 
{
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int value) 
    {
        this.value = value;
        this.left  = null;
        this.right = null;
        this.next  = null;
    }// construction function

}//

// BST
class BST 
{
    private TreeNode root;

    // Construction function
    BST(int[] arr) 
    {
        this.root = create(arr);
    }

    public TreeNode getRoot() 
    {
        return this.root;
    }

    // Create a BST tree
    private TreeNode create(int[] arr) 
    {
        if (arr == null) {
            return null;
        }

        TreeNode root = null;
        for (int i = 0; i < arr.length; i++) {
            root = insert(root, arr[i]);
        }
        // return

        return root;
    }

    // Insert nodes into a BST
    private TreeNode insert(TreeNode root, int x) 
    {
        // the proper postion to be insertednull
        if (root == null) {
            return new TreeNode(x);
        } // if

        // Select a branch to insert a given node into the tree
        if (x < root.value) {
            root.left = insert(root.left, x);
        } else if(x > root.value)
        {
            root.right = insert(root.right, x);
        }

        // return
        return root;

    }//

    // Traverse a BST in preorder
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        // traverse
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);

    }//

    // Traverse a BST in inorder
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        // traverse
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);

    }//

    // Traverse a BST in postOrder
    public void postOrder(TreeNode root) 
    {
        if (root == null) 
        {
            return;
        }

        // traversr
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }//

}//