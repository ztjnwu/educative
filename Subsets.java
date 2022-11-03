import java.util.*;

public class Subsets {

    public static List<List<Integer>> createSubsets(int[] arr)
    {
        //chcek validity
        if(arr == null)
        {
            return null;
        }

        //build subsets
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsets.add(temp);
        int startIndex = 0, endIndex = subsets.size() - 1;

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
        //check validity
        if(arr == null)
        {
            return null;
        }

        //Sort arr out
        Arrays.sort(arr);
    
        //Find the distinct subsets
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        results.add(temp);
        int startIndex = 0, endIndex = results.size() - 1;

        for(int i = 0; i < arr.length; i++)
        {
            for(int j = startIndex; j <= endIndex; j++)
            {
                temp = new ArrayList<>(results.get(j));
                temp.add(arr[i]);
                results.add(temp);
            }//for
            
            if(i + 1 < arr.length && arr[i + 1] == arr[i]) //not duplicate element
            {
                startIndex = endIndex + 1; 
                endIndex = results.size() - 1;
            }
            else if(i + 1 < arr.length && arr[i + 1] != arr[i]) //
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

        //Find permuataion
        List<String> result = new ArrayList<>();
        String temp = s;
        result.add(temp);
        int startIndex = 0, endIndex = result.size() - 1;
        System.out.println("length: " + s.length());
        for(int i = 0; i < s.length(); i++)
        {

            char chartemp = s.charAt(i);
            for(int j = startIndex; j <= endIndex; j++)
            {
                temp = result.get(j);
                if(chartemp >= '0' && chartemp <= '9')
                {
                    result.add(temp + chartemp);
                }
                else if(chartemp >= 'a' && chartemp <= 'z')
                {
                    result.add(temp + chartemp);
                    result.add(temp.substring(temp.length()) + Character.toUpperCase(chartemp)); 
                }
            }//for

            //update startIndex and endIndex
            for(int k = startIndex; k < endIndex; k++)
            {
                result.remove(0);
            }

            startIndex = 0;
            endIndex = result.size() - 1;

        }//for

        //return
        return result;

    }//

    public static void main(String[] args)
    {
        //Create subsets
        System.out.println("Create Subsets:");
        System.out.println("Here is the list of subsets: " + Subsets.createSubsets(new int[] { 1, 3 }));
        System.out.println("Here is the list of subsets: " + Subsets.createSubsets(new int[] { 1, 5, 3 }) + "\n");
        
        //Find distinct subsets
        System.out.println("Create Subsets without duplicate subsets:");
        System.out.println("Here is the list of subsets: " + Subsets.createSubsetsWithoutDuplicateSubsets(new int[] { 1, 3, 3 }));
        System.out.println("Here is the list of subsets: " + Subsets.createSubsetsWithoutDuplicateSubsets(new int[] { 1, 5, 3, 3 }) + "\n");

        //Find the permutation
        System.out.print("Here are all the permutations: " + Subsets.findPermutation(new int[] { 1, 3, 5 })+ "\n");

        //Find string permutation
        System.out.println(" String permutations are: " + Subsets.findStringPermutation("ad52"));
        System.out.println(" String permutations are: " + Subsets.findStringPermutation("ab7c"));    

    }//main

    
}//Class
