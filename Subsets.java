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
        List<String> result_string = Subsets.findParenthesisBalanced(2);
        System.out.println("All combinations of balanced parentheses are: " + result_string);
    
        result_string = Subsets.findParenthesisBalanced(3);
        System.out.println("All combinations of balanced parentheses are: " + result_string);
    }// main
    
}//Class