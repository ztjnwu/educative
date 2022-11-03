import java.util.*;

public class BFS {

    public static boolean traverse(TreeNode root)
    {
        return true;
    }//

    
    public static void main(String[] args)
    {
        //Traverse a BST
        BST bst = new BST(new int[] {1, 2, 3, 4, 5, 6, 7, 8});
        bst.preOrder(bst.getRoot());
        System.out.println();
        bst.inOrder(bst.getRoot());
        System.out.println();
        bst.postOrder(bst.getRoot());
        System.out.println();

        //Breath First Search 


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