import java.util.*;

public class Subsets {

    public static List<List<Integer>> createSubsets(int[] arr)
    {
        //chcek validity
        if(arr == null)
        {
            return null;
        }

        //Initialization
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsets.add(temp); //int [] into subsets
        int startIndex = 0, endIndex = subsets.size() - 1;

        //Build subsets
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = startIndex; j <= endIndex; j++)
            {
                temp = new ArrayList<>(subsets.get(j));
                temp.add(arr[i]);
                subsets.add(temp);
            }

            //update startIndex and endIndex
            startIndex = 0;
            endIndex = subsets.size() - 1;
        }

        //return
        return subsets;
    }//
    

    public static List<List<Integer>> createSubsetsWithoutDuplicateSubsets(int[] arr)
    {
        //Check Validity
        if(arr == null)
        {
            return null;
        }

        //Sort arr out
        Arrays.sort(arr);
    
        //Initialization
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        results.add(temp); //Insert [] into results
        int startIndex = 0, endIndex = results.size() - 1;
        
        //Find the distinct subsets
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = startIndex; j <= endIndex; j++)
            {
                temp = new ArrayList<>(results.get(j));
                temp.add(arr[i]);
                results.add(temp);
            }//for
            
            if(i + 1 < arr.length && arr[i + 1] == arr[i]) //Duplicate element
            {
                startIndex = endIndex + 1; 
                endIndex = results.size() - 1;
            }
            else if(i + 1 < arr.length && arr[i + 1] != arr[i]) //NOT Duplicate element
            {
                startIndex = 0;
                endIndex = results.size() - 1;
            }//else

        }//for

        //return 
        return results;

    }//


    public static List<List<Integer>> findPermutation(int[] arr)
    {
        //Check validity
        if(arr == null)
        {
            return null;
        }

        //Create permutation
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        results.add(temp);
        int startIndex = 0, endIndex = results.size() - 1;

        for(int i = 0; i < arr.length; i++)
        {
            for(int j = startIndex; j <= endIndex; j++)
            { 
                temp = new ArrayList<>(results.get(j));
                int len = temp.size() + 1;
                for(int k = 0; k < len; k++)
                {
                    temp.add(k, arr[i]);
                    results.add(new ArrayList<>(temp));
                    temp.remove(k);        
                }//for

            }//for

            //Delete previous items
            for(int n = 1; n <= endIndex - startIndex + 1; n++)
            {
                results.remove(0);
            }//for

            //update startIndex and endIndex
            startIndex = 0;
            endIndex = results.size() - 1;

        }//for

        //return
        return results;

    }//permutation
    

    public static List<String> findStringPermutation(String s)
    {
        //Check valadity
        if(s.isEmpty())
        {
            return new ArrayList<>();
        }

        //Initialization
        List<String> result = new ArrayList<>();
        result.add(s);
    
        //Find permuataion
        int startIndex = 0, endIndex = result.size() - 1;
        for(int i = 0; i < s.length(); i++)
        {
            char chartemp = s.charAt(i);
            for(int j = startIndex; j <= endIndex; j++)
            {
                StringBuilder temp = new StringBuilder(result.get(j));
                if(chartemp >= 'a' && chartemp <= 'z')
                {
                    temp.setCharAt(i, Character.toUpperCase(chartemp));
                    result.add(temp.toString());
                }
                else if(chartemp >= 'A' && chartemp <= 'Z')
                {
                    temp.setCharAt(i, Character.toLowerCase(chartemp));
                    result.add(temp.toString());
                }
                
            }//for

            startIndex = 0;
            endIndex = result.size() - 1;

        }//for

        //return
        return result;

    }//


    public static List<String> findParenthesisBalanced(int N)
    {
        //Check Validity
        if(N < 0)
        {
            return null;
        }//

        //Initialization
        Queue<String> queue = new LinkedList<>();
        queue.add("(");
        List<String> result = new ArrayList<>();

        //Build balanced parenthesis
        while(!queue.isEmpty())
        {
            int length = queue.size();
            for(int k = 0; k < length; k++)
            {
                String temp  = queue.poll();
                int openCount = 0, closeCount = 0;
                for(int i = 0; i < temp.length(); i++)
                {
                    if(temp.charAt(i) == '(')
                    {
                        openCount++;
                    }//

                    if(temp.charAt(i) == ')')
                    {
                        closeCount++;
                    }
                }// for

                //whether ( can be inserted into temp
                if(openCount < N)
                {
                    StringBuilder templeft = new StringBuilder(temp);
                    templeft.append('(');
                    queue.add(templeft.toString());
                }// if

                //whether ) can be inserted into temp
                if(openCount > closeCount)
                {
                    StringBuilder tempright = new StringBuilder(temp);
                    tempright.append(')');
                    queue.add(tempright.toString());
                }// if

                //Update the set of result
                if(openCount == closeCount && openCount == N)
                {
                    result.add(temp.toString());
                } 

            }// for

        }// while

        //return
        return result;
    }// 


    public static List<Integer> differentExpression(String input)
    {
        //Case1
        if(input == null)
        {
            return null;
        }

        //Case2
        if(!input.contains("+") && !input.contains("-") && !input.contains("*"))
        {
            List<Integer> result = new ArrayList<>();
            result.add(Integer.parseInt(input));
            return result;
        }//

        //Case3
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < input.length(); i++)
        {
            if(!Character.isDigit(input.charAt(i)))
            {
                List<Integer> left, right;
                left = differentExpression(input.substring(0, i));
                right = differentExpression(input.substring(i + 1, input.length()));
                for(Integer l : left)
                {
                    for(Integer r : right)
                    {
                        if(input.charAt(i) == '+')
                        {
                            result.add(l + r);
                        }//
                        else if(input.charAt(i) == '-')
                        {
                            result.add(l - r);
                        }//
                        else if(input.charAt(i) == '*')
                        {
                            result.add(l * r);
                        }// else

                    }// for

                }// for

            }// for
        }//
        return result;
    }//


    public static List<TreeNode> buildBST(int start, int end)
    {
        //Case1
        if(start > end)
        {
            List<TreeNode> result = new ArrayList<>();
            result.add(null);
            return result;
        }

        //Case2
        List<TreeNode> result = new ArrayList<>();
        for(int i = start; i <= end; i++)
        {
            //
            List<TreeNode> leftTree, rightTree;
            leftTree = buildBST(start, i - 1);
            rightTree = buildBST(i + 1, end);
            
            //Concanate the left tree and right tree
            for(TreeNode itemLeft : leftTree)
            {
                for(TreeNode itemRight : rightTree)
                {
                    TreeNode root = new TreeNode(i);
                    root.left = itemLeft;
                    root.right = itemRight;
                    result.add(root);
                }//

            }//
            
        }//
        return result;
    }
    
    public static void main(String[] args)
    {
        //Create subsets
        System.out.println("Create Subsets:");
        System.out.println("Here is the list of subsets: " + Subsets.createSubsets(new int[] { 1, 3 }));
        System.out.println("Here is the list of subsets: " + Subsets.createSubsets(new int[] { 1, 5, 3 }) + "\n");
        System.out.println();
        
        //Find distinct subsets
        System.out.println("Create Subsets without duplicate subsets:");
        System.out.println("Here is the list of subsets: " + Subsets.createSubsetsWithoutDuplicateSubsets(new int[] { 1, 3, 3 }));
        System.out.println("Here is the list of subsets: " + Subsets.createSubsetsWithoutDuplicateSubsets(new int[] { 1, 5, 3, 3 }) + "\n");
        System.out.println();

        //Find the permutation
        System.out.print("Here are all the permutations: " + Subsets.findPermutation(new int[] { 1, 3, 5 })+ "\n");
        System.out.println();

        //Find string permutation
        System.out.println(" String permutations are: " + Subsets.findStringPermutation("ad52"));
        System.out.println(" String permutations are: " + Subsets.findStringPermutation("ab7c"));    
        System.out.println();

        //Balance parenthesis
        System.out.println(" Balance parenthesis");
        List<String> result_string = Subsets.findParenthesisBalanced(2);
        System.out.println("All combinations of balanced parentheses are: " + result_string);
    
        result_string = Subsets.findParenthesisBalanced(3);
        System.out.println("All combinations of balanced parentheses are: " + result_string);

        //Different expression
        System.out.println(" Different expression ");
        List<Integer> result = Subsets.differentExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);
    
        result = Subsets.differentExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);

        //Build unique BST tree
        System.out.println(" Different BST tree ");
        List<TreeNode> result_tree = Subsets.buildBST(1, 10);
        System.out.println(result_tree.size());

    }// main
    
}//Class



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