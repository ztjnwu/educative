import java.util.*;

public class BFS {

    //traverse a bst in layer
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
                if(tempNode.left == null && tempNode.right == null)
                {
                    if(miniLevel > currentLevel)
                    {
                        miniLevel = currentLevel;
                    }//
                }//
                
                if(tempNode.left != null)
                {   
                    queue.offer(tempNode.left);
                }//

                if(tempNode.right != null)
                {;
                    queue.offer(tempNode.right);
                }//

                
            }//

            //update level
            currentLevel++;
        }//

        //return
        return miniLevel;
    }//


    public static void main(String[] args)
    {
        //Traverse a BST
        System.out.println("Traverse a BST in preorder, inorder and postorder.");
        BST bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        bst.preOrder(bst.getRoot());
        System.out.println();
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

    }
    
}//


//TreeNode
class TreeNode 
{
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }//construction function

}


//BST
class BST {
    private TreeNode root;

    //Construction function
    BST(int[] arr)
    {
        this.root = create(arr);
    }

    public TreeNode getRoot()
    {
        return this.root;
    }

    //Create a BST tree
    private TreeNode create(int[] arr)
    {
        if(arr == null)
        {
            return null;
        }
        
        TreeNode root = null;
        for(int i = 0; i < arr.length; i++)
        {
            root = insert(root, arr[i]);
        }
        //return
        
        return root;
    }

    //Insert nodes into a BST 
    private TreeNode insert(TreeNode root, int x)
    {
        //the proper postion to be inserted 
        if(root == null)
        {
            return new TreeNode(x);
        }//if
        
        // Select a branch to insert a given node into the tree
        if (x < root.value) {
            root.left = insert(root.left, x);
        } else if(x > root.value)
        {
            root.right = insert(root.right, x);
        }

        //return
        return root;

    }//

    //Traverse a BST in preorder
    public void preOrder(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        //traverse
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
        
    }//

    //Traverse a BST in inorder
    public void inOrder(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        //traverse
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);  

    }//

    //Traverse a BST in postOrder
    public void postOrder(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        //traversr
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }//

}//