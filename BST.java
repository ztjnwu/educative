import java.util.*;
import TreeNode;

public class BST {
    TreeNode root;

    //Construction function
    BST()
    {
        this.root = null;
    }

    //Create a BST tree
    public static TreeNode create(int[] arr)
    {
        if(arr == null)
        {
            return null;
        }
        
        TreeNode root = null;
        for(int i = 0; i < arr.length; i++)
        {
            insert(root, arr[i]);
        }

        return root;
    }

    //Insert nodes into a BST 
    public static TreeNode insert(TreeNode root, int x)
    {
        //the proper postion to be inserted 
        if(root == null)
        {
            return new TreeNode(x);
        }
        
        //Select a branch to insert a given node into the tree
        if(x < root.value)
        {
            root.left = insert(root.left, x);
        }
        else 
        {
            root.right = insert(root.right, x);
        }

        //return
        return root;

    }//


    //Traverse a BST in preorder
    public static void preOrder(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        //traverse
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }



    public static void preOrder(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        //traverse
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    
}
