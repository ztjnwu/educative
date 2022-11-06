import java.util.*;

public class DFS 
{
    public static void findPathSum(TreeNode root, int preNodeSum, int sum, List<Boolean> result)
    {
        if(root == null)
        {
            return;
        }
        
        //related operations on current nodes
        if(root.left == null && root.right == null)
        {
            
            if(root.value + preNodeSum == sum)
            {
                result.add(true);
            }
            
        }  
        
        //traverse left subtree and right subtree
        preNodeSum += root.value;
        findPathSum(root.left, preNodeSum, sum, result);
        findPathSum(root.right, preNodeSum, sum, result);

    }//

    public static void findAllPaths(TreeNode root, int preNodeSum, int sum, List<Integer> currentPath, List<List<Integer>> allPaths)
    {
        //Check validity
        if(root == null)
        {
            return;
        }

        //related operation on current nodes
        List<Integer> tempPath = new ArrayList<>(currentPath);
        if(root.left == null && root.right == null)
        {
            if(root.value + preNodeSum == sum)
            {        
                tempPath.add(root.value);
                allPaths.add(tempPath);
                tempPath.remove(tempPath.size() - 1);
            }
        }
        
        //traverse left subtree and right subtree
        tempPath.add(root.value);
        preNodeSum += root.value;
        findAllPaths(root.left, preNodeSum , sum, tempPath, allPaths);
        findAllPaths(root.right, preNodeSum, sum, tempPath, allPaths);
        
    }
    

    public static void main(String[] args)
    {
        //Find a path of sum S
        System.out.println("Find a path of sum S");
        BST bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        int sum = 13;
        List<Boolean> result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        boolean flag = false;
        if(result.size() != 0)
        {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 15;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if(result.size() != 0)
        {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 21;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if(result.size() != 0)
        {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 23;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if(result.size() != 0)
        {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 14;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if(result.size() != 0)
        {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        //Find all paths
        System.out.println("Find all paths of sum S");
        bst = new BST(new int[] {6, 4, 2, 1, 3, 5, 8, 7, 9});
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        sum = 15;
        findAllPaths(bst.getRoot(), 0, sum, currentPath, allPaths);
        System.out.println("sum:" + sum + " result:" + allPaths);

        //

    }//

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
        
        //Select a branch to insert a given node into the tree
        if(x < root.value)
        {
            root.left = insert(root.left, x);
        }
        else 
        {
            root.right = insert(root.right, x);
        }//else

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