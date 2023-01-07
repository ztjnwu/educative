import java.util.*;

import javax.crypto.NullCipher;

public class DFS 
{
    public static void findPathSum(TreeNode root, int preNodeSum, int sum, List<Boolean> result) 
    {
        if (root == null) 
        {
            return;
        }

        // related operations on current nodes
        if (root.left == null && root.right == null) 
        {

            if (root.value + preNodeSum == sum) 
            {
                result.add(true);
            }

        }

        // traverse left subtree and right subtree
        preNodeSum += root.value;
        findPathSum(root.left, preNodeSum, sum, result);
        findPathSum(root.right, preNodeSum, sum, result);

    }// findPathSum


    public static void findAllPaths(TreeNode root, int preNodeSum, int sum, List<Integer> currentPath, List<List<Integer>> allPaths) 
    {

        // Check validity
        if (root == null) 
        {
            return;
        }

        // related operation on current nodes
        List<Integer> updatePath = new ArrayList<>(currentPath);

        //
        if (root.left == null && root.right == null) 
        {
            if (root.value + preNodeSum == sum) 
            {
                updatePath.add(root.value);
                allPaths.add(updatePath);
                updatePath.remove(updatePath.size() - 1);
            }
        }

        //Traverse left subtree and right subtree
        updatePath.add(root.value);
        preNodeSum += root.value;
        findAllPaths(root.left, preNodeSum, sum, updatePath, allPaths);
        findAllPaths(root.right, preNodeSum, sum, updatePath, allPaths);

    }//findAllPaths


    public static void findAllRootToLeafPaths(TreeNode root, List<Integer> currentPath, List<List<Integer>> allPaths)
    {
        if(root == null)
        {
            return;
        }
         
        //Initialization
        List<Integer> updatePath = new ArrayList<>(currentPath);

        //related operation on current nodes
        if(root.left == null && root.right == null)
        {
            updatePath.add(root.value);
            allPaths.add(updatePath);
            updatePath.remove(updatePath.size() - 1);
        }

        //Traverse the remaining subtrees
        updatePath.add(root.value);
        findAllRootToLeafPaths(root.left, updatePath, allPaths);
        findAllRootToLeafPaths(root.right, updatePath, allPaths);
        
    }//


    public static boolean findSpecificPath(BST bst, int[] path)
    {
        if(bst == null)
        {
            return false;
        }

        //initialization
        Boolean[] result = new Boolean[] {Boolean.FALSE};
        int level = 0;

        //traverse
        dFS(bst.getRoot(), path, level, result);

        //return
        return result[0].booleanValue();

    }//findSpecificPath


    private static void dFS(TreeNode root, int[] path, int level, Boolean[] result)
    {
        //The condition of termination
        if(root == null || root.value != path[level])
        {
            return;
        }

        //visit current node
        if(root.left == null && root.right == null)
        {
            result[0] = Boolean.TRUE;
        }
       
        //traverse left subtree and right subtree
        dFS(root.left, path, level + 1, result);
        dFS(root.right, path, level + 1, result);
        
    }


    public static List<Double> findPathSums(BST bst)
    {
        if(bst == null)
        {
            return null;
        }

        //Initialization
        List<Double> result = new ArrayList<>();
        TreeNode root = bst.getRoot();
        int level = 0; 
        double currentSum = 0;

        //Invoke function
        dFSfindPathSums(root, level, currentSum, result);

        //return
        return result;
    }


    public static void dFSfindPathSums(TreeNode root, int level, double currentSum, List<Double> result)
    {
        if(root == null || level < 0)
        {
            return;
        }

        //operations on current node
        double weight = Math.pow(10, level);
        double sum = weight * root.value + currentSum;

        if(root.left == null && root.right == null)
        {
            result.add(sum);
        }

        //Traverse initialization
        level++;     
        currentSum = sum;
        dFSfindPathSums(root.left, level, currentSum, result);
        dFSfindPathSums(root.right, level, currentSum, result);
        
    }


    public static List<Boolean> findPathWithGivenSequence(BST bst, int[] path)
    {
        //Check Validity
        if(bst == null || path == null)
        {
            return null;
        }

        //Initilialization
        List<Boolean> result = new ArrayList<>();
        TreeNode root = bst.getRoot();
        int level = 0;

        //
        dFSFindGivenSequence(root, path, level, result);
 
        //return
        return result;

    }//


    private static void dFSFindGivenSequence(TreeNode root, int[] path, int level, List<Boolean> result)
    {
        //Chcek Validity
        if(root == null || level < 0 || root.value != path[level])
        {
            return;
        }

        //
        if(root.left == null && root.right == null)
        {
            result.add(true);
        }
        

        //traverse
        level++;
        dFSFindGivenSequence(root.left, path, level, result);
        dFSFindGivenSequence(root.right, path, level, result);

    }//


    public static int findAllOfSubPathsSumEqualtoSum(BST bst, int sum)
    {
        if(bst == null)
        {
            return -1;
        }

        //
        List<Integer> result = new ArrayList<>();
        result.add(0, 0);
        List<Integer> currentPath = new ArrayList<>();
        dFSFindAllOfSubPathsSumEqualtoSum(bst.getRoot(), currentPath, sum, result);
        
        //return
        return result.get(0).intValue();
    }//


    private static void dFSFindAllOfSubPathsSumEqualtoSum(TreeNode root, List<Integer> currentPath, int sum, List<Integer> result)
    {
        //Check validity
        if(root == null || sum < 0)
        {
            return;
        }

        //Initialization
        List<Integer> currentPath_copy = new ArrayList(currentPath);
        currentPath_copy.add(root.value);
        
        //Only Compute all of subsets ending at root
        int size = currentPath_copy.size();
        int subsum = 0;
        int number = 0;
        for(int i = size - 1; i >= 0; i--)
        {
            subsum += currentPath_copy.get(i);
            if(subsum == sum)
            {
                number++;
            }// if
        }// for

        number += result.get(0);
        result.add(0, number);

        //Traverse left subtree and right subtree
        dFSFindAllOfSubPathsSumEqualtoSum(root.left,  currentPath_copy, sum, result);
        dFSFindAllOfSubPathsSumEqualtoSum(root.right, currentPath_copy, sum, result);

    }//


    public static int findAllOfSubPathsSumEqualtoSumUsingPrefixArray(BST bst, int sum)
    {
        //Check Validity
        if(bst == null)
        {
            return -1;
        }//

        //
        Map<Integer, Integer> prefixMap = new HashMap<>();
        List<Integer> result = new LinkedList<>();
        result.add(0, 0);
        int currentSum = 0;

        //invoke the function dFSFindAllOfSubPathsSumEqualtoSumUsingPrefixArray to get result;
        dFSFindAllOfSubPathsSumEqualtoSumUsingPrefixArray(bst.getRoot(), prefixMap, currentSum, sum, result);

        //return
        return result.get(0);
    }


    private static void dFSFindAllOfSubPathsSumEqualtoSumUsingPrefixArray(TreeNode root, Map<Integer, Integer> currentPathPrefix, int currentSum, int target, List<Integer> result)
    {
        //Check validity
        if(root == null)
        {
            return;
        }

        //
        currentSum += root.value;
        Map<Integer, Integer> copy = new HashMap<>(currentPathPrefix);
        copy.put(currentSum, 1);

        //Compute the nubmer of the paths with the specific target
        if(currentSum == target)
        {
            int number = result.get(0);
            number++;
            result.add(0, number);
        }//

        //Compute the number of the paths with the specific target
        if(copy.get(currentSum - target) != null)
        {
            int number = result.get(0);
            number++;
            result.add(0, number);
        }//

        //Traverse BST 
        dFSFindAllOfSubPathsSumEqualtoSumUsingPrefixArray(root.left,  copy, currentSum, target, result);
        dFSFindAllOfSubPathsSumEqualtoSumUsingPrefixArray(root.right, copy, currentSum, target, result);

    }//

    public static int findDiameter(BST bst)
    {
        //Base Check
        if(bst == null)
        {
            return -1;
        }//

        //Init
        List<Integer> maxDiameter = new ArrayList<>();

        //Find diameter
        
        maxDiameter.add(Integer.MIN_VALUE);
        dfsHeight(bst.getRoot(), maxDiameter);
        
        //Return
        return maxDiameter.get(0);
    }
    
    public static int dfsHeight(TreeNode root, List<Integer> maxDiameter)
    {
        //Base Check
        if(root == null)
        {
            return 0;
        }//

        //Dfs left substree and right subtree
        int heightLeft = dfsHeight(root.left, maxDiameter);
        int heightRight = dfsHeight(root.right, maxDiameter);

        //Root Node
        if(root.left != null && root.right != null)
        {
            int max = Math.max(maxDiameter.get(0), heightLeft + heightRight + 1);
            maxDiameter.clear();
            maxDiameter.add(max);
        }// 

        return 1 + Math.max(heightLeft, heightRight); 
    }//

    public static int findPathWithMaxSum(BST bst)
    {
        //Base Check
        if(bst == null)
        {
            return -1;
        }//

        //Init
        List<Integer> result = new ArrayList<>();
        result.add(Integer.MIN_VALUE);

        //Find the max path
        dfsMaximumPath(bst.getRoot(), result);

        //Return
        return result.get(0);
    }//

    public static int dfsMaximumPath(TreeNode root, List<Integer> result)
    {
        //Base Check
        if(root == null)
        {
            return 0;
        }//

        //Left subtree and right subtree
        int maxLeft, maxRight;
        maxLeft = dfsMaximumPath(root.left, result);
        maxRight = dfsMaximumPath(root.right, result);

        //Current Node
        int max;
        max = Math.max(result.get(0), maxLeft + maxRight + root.value);
        result.clear();
        result.add(max);
        
        //return
        return root.value + Math.max(maxLeft, maxRight);
    }


    public static void main(String[] argv)
    {
        System.out.println("Find a path of sum S");
        BST bst = new BST(new int[] { 6, 4, 2, 1, 3, 5, 8, 7, 9 });
        int sum = 13;
        List<Boolean> result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        boolean flag = false;
        if (result.size() != 0) {
            flag = true;
        } 
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 15;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if (result.size() != 0) {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 21;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if (result.size() != 0) {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        sum = 23;
        result = new ArrayList<>();
        findPathSum(bst.getRoot(), 0, sum, result);
        flag = false;
        if (result.size() != 0) {
            flag = true;
        }
        System.out.println("sum:" + sum + " result:" + flag);

        //Find all paths of sum S
        System.out.println("\nFind all paths of sum S");
        bst = new BST(new int[] { 6, 4, 2, 1, 3, 5, 8, 7, 9 });
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        sum = 15;
        findAllPaths(bst.getRoot(), 0, sum, currentPath, allPaths);
        System.out.println("sum:" + sum + " result:" + allPaths);

        //Find all root-to-leaf path
        System.out.println("\nFind all root-to-leaf paths");
        bst = new BST(new int[] { 6, 4, 2, 1, 3, 5, 8, 7, 9 });
        allPaths = new ArrayList<>();
        currentPath = new ArrayList<>();
        findAllRootToLeafPaths(bst.getRoot(), currentPath, allPaths);
        System.out.println("result:" + allPaths);

        //Find a specific path
        System.out.println("\nMatch the specific path!");
        bst = new BST(new int[] { 1, 0 ,1, 1, 6, 5});
        System.out.println("Tree has path sequence: " + DFS.findSpecificPath(bst, new int[] { 1, 0}));
        System.out.println("Tree has path sequence: " + DFS.findSpecificPath(bst, new int[] { 1, 6, 5 }));
        
        //Find the sums of all paths
        System.out.println("\nMatch the specific path!");
        bst = new BST(new int[] { 6, 4, 2, 1, 3, 5, 8, 7, 9});
        System.out.println("Path sums " + DFS.findPathSums(bst));

        //Find a given path
        System.out.println("\nFind the specific path!");
        bst = new BST(new int[] { 6, 4, 2, 1, 3, 5, 8, 7, 9});
        System.out.println("Path sums " + DFS.findPathWithGivenSequence(bst, new int[] { 6, 4, 2, 1}));
        System.out.println("Path sums " + DFS.findPathWithGivenSequence(bst, new int[] { 6, 4, 2, 3}));
        System.out.println("Path sums " + DFS.findPathWithGivenSequence(bst, new int[] { 6, 4, 5}));
        System.out.println("Path sums " + DFS.findPathWithGivenSequence(bst, new int[] { 6, 8, 7}));
        System.out.println("Path sums " + DFS.findPathWithGivenSequence(bst, new int[] { 6, 8, 9}));
        System.out.println("Path sums " + DFS.findPathWithGivenSequence(bst, new int[] { 6, 8, 0}));

        //Find the path with a given sum
        System.out.println("\n Find the path with a given sum!");
        bst = new BST(new int[] {12, 7, 1, 4, 10, 5});
        System.out.println("result: " + DFS.findAllOfSubPathsSumEqualtoSum(bst, 12));

        //Find the path with a given sum USING prefix array
        System.out.println("\n Find the path with a given sum USING pre=1fix array!");
        bst = new BST(new int[] {12, 7, 1, 4, 10, 5});
        System.out.println("result: " + DFS.findAllOfSubPathsSumEqualtoSumUsingPrefixArray(bst, 12));

        //Find the diameter of a binary tree
        System.out.println("\n Find the diameter of a binary tree");
        bst = new BST(new int[] {12, 7, 1, 4, 10, 5});
        System.out.println("result: " + DFS.findDiameter(bst));

        //Find the path with the maximum sum
        System.out.println("\n Find the path with the maximum sum");
        bst = new BST(new int[] {12, 7, 1, 4, 10, 5});
        System.out.println("result: " + DFS.findPathWithMaxSum(bst));

    
    }//
       
}// DFS

// TreeNode
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }// construction function
}

// BST
class BST {
    private TreeNode root;

    // Construction function
    BST(int[] arr) {
        this.root = create(arr);
    }

    public TreeNode getRoot() 
    {
        return this.root;
    }

    // Create a BST tree
    private TreeNode create(int[] arr)
    {
        if (arr == null) 
        {
            return null;
        }

        TreeNode root = null;
        for (int i = 0; i < arr.length; i++)
        {
            root = insert(root, arr[i]);
        }
        // return

        return root;
    }

    // Insert nodes into a BST
    private TreeNode insert(TreeNode root, int x) 
    {
        // the proper postion to be insertednull
        if (root == null) 
        {
            return new TreeNode(x);
        } // if

        // Select a branch to insert a given node into the tree
        if (x < root.value) 
        {
            root.left = insert(root.left, x);
        }
        else if(x > root.value)
        {
            root.right = insert(root.right, x);
        }

        // return
        return root;

    }//

    // Traverse a BST in preorder
    public void preOrder(TreeNode root) 
    {
        if (root == null) 
        {
            return;
        }

        // traverse
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);

    }//

    // Traverse a BST in inorder
    public void inOrder(TreeNode root) 
    {
        if (root == null) 
        {
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