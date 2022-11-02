import java.util.*;
import TreeNode;

public class BST {
    TreeNode root;

    //Construction function
    BST(int[] arr)
    {
        this.root = create(arr);
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
            insert(root, arr[i]);
        }

        return root;
    }

    //Insert nodes into a BST 
    private TreeNode insert(TreeNode root, int x)
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
    private void preOrder()
    {
        if(this.root == null)
        {
            return;
        }

        //traverse
        System.out.print(this.root.value + " ");
        preOrder(this.root.left);
        preOrder(this.root.right);
    }

    //Traverse a BST in inorder
    private void inOrder()
    {
        if(this.root == null)
        {
            return;
        }

        //traverse
        inOrder(this.root.left);
        System.out.print(this.root.value + " ");
        inOrder(this.root.right);
        
    }

    //Traverse a BST in postOrder
    private void postOrder()
    {
        if(this.root == null)
        {
            return;
        }

        //traversr
        postOrder(this.root.left);
        postOrder(this.root.right);
        System.out.print(this.root.value + "");
    }

    
}