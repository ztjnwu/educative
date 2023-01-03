import java.util.*;

public class SlideWindow 
{
    public static int findMax(int K, int[] arr) 
    {
        // initialization
        double[] result = new double[arr.length - K + 1];
        double max = Double.MIN_VALUE;
        int maxIndex = 0;
        double partSum = Double.MIN_VALUE;
        for (int j = 0; j <= K - 2; j++) {
            partSum += (double) arr[j];
        }

        // calculation
        for (int i = 0; i <= arr.length - K; i++) {
            result[i] = partSum + (double) arr[i + K - 1];
            partSum = result[i] - (double) arr[i];
            if (max < result[i]) {
                max = result[i];
                maxIndex = i;
            }
        }

        // return
        return (int) max;

    }

    public static int findMinArray(int S, int[] arr) {
        // initialization
        double[][] result = new double[arr.length + 1][arr.length];
        double min = S;
        int flag = 0;

        // loop
        for (int k = 1; k <= arr.length; k++) {
            // initializaiont
            double partSum = 0;
            for (int j = 0; j <= k - 2; j++) {
                partSum += (double) arr[j];
            }

            // loop
            for (int i = 0; i <= arr.length - k; i++) {
                result[k][i] = partSum + (double) arr[i + k - 1];
                partSum = result[k][i] - arr[i];
            }

        }

        // find the minimum
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= arr.length - 1; j++) {
                if (result[i][j] >= S && result[i][j] < min) {
                    min = result[i][j];
                }
            }
        }

        // print the subarray with a minimum sum
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= arr.length - 1; j++) {
                if (result[i][j] == min) {
                    for (int k = j; k <= j + i - 1; k++) {
                        System.out.print(" " + arr[k]);
                    }
                    System.out.println();
                    flag = 1;
                }
            }

            if (flag == 1) {
                break;
            }

        }

        // print
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= arr.length - 1; j++) {
                System.out.print(" " + result[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        // return
        return -1;
    }

    public static int basic(int K, int[] arr) {
        // initialization
        int winS = 0, winE = 0;

        // loop
        while (winE < arr.length) {
            while (winS <= winE && winE - winS + 1 > K) {// mean a current window of length K, [winS, winE]
                // necessary operation
                winS++;
            }

            if (winS <= winE && (winE - winS + 1 == K)) {
                // to do
                for (int i = winS; i <= winE; i++) {
                    System.out.print(" " + arr[i]);
                }
                System.out.println();
            }

            // necessary operation
            winE++;
        }

        // return
        return 1;

    }

    public static int basicOPT(int K, int[] arr) 
    {
        // initialization
        int winS = 0, winE = 0;

        // loop
        while (winE < arr.length) 
        {
            if (winE - winS + 1 == K) 
            {
                // to do
                for (int i = winS; i <= winE; i++) 
                {
                    System.out.print(" " + arr[i]);
                }
                System.out.println();
                winE++; // slide the window forward
                winS++;
            } 
            else if (winE - winS + 1 < K)  // adjust slide window
            {
                winE++;
            }
            // PLUS: winE - winS + 1 > does not exist!
      
        } // while

        // return
        return 0;
    }

    public static int basicSumMax(int K, int[] arr) {
        // initialization
        int winS = 0, winE = 0;
        double winSum = arr[0];
        double max = Double.MIN_VALUE;

        // loop
        while (winE < arr.length) {
            // have an operation on targeted element.
            while (winS <= winE && winE - winS + 1 > K) {
                winS++;
                winSum -= arr[winS - 1];
            }

            if (winS <= winE && winE - winS + 1 == K) {
                max = Math.max(max, winSum);
            }

            // necessary operation
            winE++;
            if (winE < arr.length) {
                winSum += arr[winE];
            }
        }

        // return
        return (int) max;

    }

    public static int basicSumMaxOPT(int K, int[] arr) {
        // initialization
        int winS = 0, winE = 0;
        double winSum = arr[0];
        double max = Double.MIN_VALUE;

        //loop
        while (winE < arr.length) 
        {
            if (winE - winS + 1 == K) 
            {
                winSum = Math.max(winSum, max);
                winS++;
                winE++;
            }//
            else if (winE - winS + 1 < K) 
            {
                winE++;
                winSum += arr[winE];
            }//

        } // while

        // return
        return (int) max;
    }

    public static int basicSumBeyondS(int S, int[] arr) {
        // initialization
        int winS = 0, winE = 0;
        double winSum = arr[0];
        int minLen = Integer.MAX_VALUE;

        // loop
        while (winE < arr.length) {
            int curMin = Integer.MAX_VALUE;
            while (winS <= winE && winSum >= S) {
                curMin = Math.min(minLen, winE - winS + 1);
                winS++;
                winSum -= arr[winS - 1];
            }
            minLen = Math.min(minLen, curMin);

            // update
            winE++;
            if (winE < arr.length) {
                winSum += arr[winE];
            }

        }

        // return
        return minLen;
    }

    public static int basicSumBeyondSOPT(int S, int[] arr) {
        // initialization
        int winS = 0, winE = 0;
        double winSum = arr[0];
        int minLen = Integer.MAX_VALUE;

        // loop
        while (winS <= winE && winE < arr.length) {
            if (winSum >= S) {
                minLen = Math.min(minLen, winE - winS + 1);
                winS++;
                winSum -= arr[winS - 1];
            } else {
                winE++;
                if (winE < arr.length) {
                    winSum += arr[winE];
                }
            }
        }

        // return
        return minLen;
    }

    public static int basicSumBeyondSTest(int S, int[] arr) 
    {
        //initialization
        int winS = 0, winE = 0;
        double winSum = arr[0];
        int minLen = Integer.MAX_VALUE;

        //loop
        while (winE < arr.length) {
            boolean flag = false;
            while (winS <= winE && winSum >= S) {
                // update
                winS++;
                winSum -= arr[winS - 1];
                flag = true;
            }

            if (flag == true) {
                winS--;
                winSum += arr[winS];
                minLen = Math.min(minLen, winE - winS + 1);
            }

            // update
            winE++;
            if (winE < arr.length) {
                winSum += arr[winE];
            }

        }

        // return
        return minLen;
    }

    public static int basicKCharLongestOPT(int K, String str) 
    {
        //Base Check
        if(str == null)
        {
            return -1;
        }

        //Initialization
        int result = -1;
        int winS = 0, winE = 0;
        int maxLen = Integer.MIN_VALUE;
        Map<Character, Integer> freqMap = new HashMap<>();
        freqMap.put(str.charAt(0), 1);

        //Compute the number of 
        while(winE < str.length()) 
        {
            if(freqMap.size() == K) 
            {
                maxLen = Math.max(maxLen, winE - winS + 1);
                winE++;
                if (winE < str.length()) 
                {
                    Character tempLetter = str.charAt(winE);
                    freqMap.put(tempLetter, freqMap.getOrDefault(tempLetter, 0) + 1);
                }

            } 
            else if(freqMap.size() < K) 
            {
                winE++;
                if (winE < str.length())
                {
                    Character tempLetter = str.charAt(winE);
                    freqMap.put(tempLetter, freqMap.getOrDefault(tempLetter, 0) + 1);
                }

            } 
            else 
            {// freqMap.size() > K
                winS++;
                Character tempLetter = str.charAt(winS - 1);
                freqMap.put(tempLetter, freqMap.get(tempLetter) - 1);
                if (freqMap.get(tempLetter) == 0) 
                {
                    freqMap.remove(tempLetter);
                }

            } // else

        }

        // return value
        return maxLen;
    }


    public static int basicFruitTreeBuckets(int FT, String str) 
    {
        //Base Check
        if(str == null)
        {
            return -1;
        }

        //Initialization
        int result = -1;


        int winS = 0, winE = 0;
        int maxLen = Integer.MIN_VALUE;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int charSum = 0;
        Character curLetter = str.charAt(winS);
        freqMap.put(curLetter, freqMap.getOrDefault(curLetter, 0) + 1);
        charSum = freqMap.size();

        //Find the number of fruits
        while(winE < str.length()) 
        {
            while(winS <= winE && charSum > FT) 
            {
                winS++;
                curLetter = str.charAt(winS - 1);
                freqMap.put(curLetter, freqMap.get(curLetter) - 1);
                if (freqMap.get(curLetter) == 0) 
                {
                    freqMap.remove(curLetter);
                }
                charSum = freqMap.size();
            }

            if (winS <= winE && charSum == FT) 
            {
                maxLen = Math.max(maxLen, winE - winS + 1);
            }

            //Shrink current window
            winE++;
            if (winE < str.length()) 
            {
                curLetter = str.charAt(winE);
                freqMap.put(curLetter, freqMap.getOrDefault(curLetter, 0) + 1);
                charSum = freqMap.size();
            }//

        }//

        //return
        return maxLen;
    }


    public static int basicFruitTreeBucketsOPT(int FT, String str) 
    {
        //Base Check
        if(str == null)
        {
            return -1;
        }

        //Initialization
        int result = -1;
        int winS = 0, winE = 0;
        int maxLen = Integer.MIN_VALUE;
        Map<Character, Integer> freqMap = new HashMap<>();
        Character curLetter = str.charAt(0);
        freqMap.put(curLetter, 1);

        //Find the number of fruits
        while (winE < str.length()) 
        {
            if (freqMap.size() == FT) 
            {
                maxLen = Math.max(maxLen, winE - winS + 1);
                winE++;
                if (winE < str.length())
                {
                    Character temLetter = str.charAt(winE);
                    freqMap.put(temLetter, freqMap.getOrDefault(temLetter, 0) + 1);
                }

            } else if (freqMap.size() < FT) 
            {
                winE++;
                if (winE < str.length()) 
                {
                    Character temLetter = str.charAt(winE);
                    freqMap.put(temLetter, freqMap.getOrDefault(temLetter, 0) + 1);
                }

            } 
            else 
            {
                winS++;
                Character temLetter = str.charAt(winS - 1);
                freqMap.put(temLetter, freqMap.get(temLetter) - 1);
                if(freqMap.get(temLetter) == 0)
                {
                    freqMap.remove(temLetter);
                } 

            } // else

        } // while

        //return
        result = maxLen;
        return result;
    }


    public static int basicLongestDiffCharSubstring(String str) 
    {
        //Base Check
        if(str == null)
        {
            return -1;
        }//

        //Initialization
        int result = -1;
        int maxLen = Integer.MIN_VALUE;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        freqMap.put(str.charAt(0), freqMap.getOrDefault(str.charAt(0), 0) + 1);

        //Find the longest substring with all different letters
        int winS = 0, winE = 0;
        while (winE < str.length()) 
        {
            if(winE - winS + 1 == freqMap.size()) 
            {
                maxLen = Math.max(maxLen, winE - winS + 1);

                winE++;
                if(winE < str.length())
                {
                    freqMap.put(str.charAt(winE), freqMap.getOrDefault(str.charAt(winE), 0) + 1);
                }// if

            }//
            else if(winE - winS + 1 > freqMap.size())
            {
                
                freqMap.put(str.charAt(winS), freqMap.get(str.charAt(winS)) - 1);
                if(freqMap.get(str.charAt(winS)) == 0)
                {
                    freqMap.remove(str.charAt(winS));
                }// if

                winS++;
            }//

        }//

        //return
        result = maxLen;
        return result;
    }


    public static int basicLongestRepSubstringRight(int K, String str) 
    {
        //Base Check
        if(str == null || K == -1)
        {
            return -1;
        }

        //Initialization
        int result = -1;

        int maxLen = Integer.MIN_VALUE; //avoid the string with all same letters
        HashMap<Character, Integer> freqMap = new HashMap<>();
        freqMap.put(str.charAt(0), freqMap.getOrDefault(str.charAt(0), 0) + 1);

        //The longes substring with the replaced letters
        int winS = 0, winE = 0;
        while(winE < str.length()) 
        {
            if((winE - winS + 1) - Collections.max(freqMap.values()) == K)
            {
                maxLen = Math.max(maxLen, winE - winS + 1);
                winE++;
                if(winE < str.length())
                {
                    freqMap.put(str.charAt(winE), freqMap.getOrDefault(str.charAt(winE), 0) + 1);
                }//

            }
            else if((winE - winS + 1) - Collections.max(freqMap.values()) < K)
            {
                winE++;
                if(winE < str.length())
                {
                    freqMap.put(str.charAt(winE), freqMap.getOrDefault(str.charAt(winE), 0) + 1);
                }//

            }
            else 
            {
                freqMap.put(str.charAt(winS), freqMap.get(str.charAt(winS)) - 1);
                if(freqMap.get(str.charAt(winS)) == 0)
                {
                    freqMap.remove(str.charAt(winS));
                }//

                winS++;
            }//

        }

        // return
        result = maxLen;
        return result;
    }


    public static int basicLongest1sSubstring(int K, String str) 
    {
        //Base Check
        if(str == null || K == -1)
        {
            return -1;
        }//

        //Initialization
        int result = -1;
        int maxLen = Integer.MIN_VALUE;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        freqMap.put(str.charAt(0), freqMap.getOrDefault(str.charAt(0), 0) + 1);

        //Find the numbe of subarray with the same letters
        int winS = 0, winE = 0;
        while (winE < str.length()) 
        {
            if((winE - winS + 1) - freqMap.getOrDefault('1', 0) == K) 
            {
                maxLen = Math.max(maxLen, winE - winS + 1);
                winE++;
                if(winE < str.length())
                {
                    freqMap.put(str.charAt(winE), freqMap.getOrDefault(str.charAt(winE), 0) + 1);
                }//
            }//
            else if((winE - winS + 1) - freqMap.getOrDefault('1', 0) < K)
            {
                winE++;
                if(winE < str.length())
                {
                    freqMap.put(str.charAt(winE), freqMap.getOrDefault(str.charAt(winE), 0) + 1);
                }//
            }//
            else 
            {
                freqMap.put(str.charAt(winS), freqMap.get(str.charAt(winS)) - 1);
                if(freqMap.get(str.charAt(winS)) == 0)
                {
                    freqMap.remove(str.charAt(winS));     
                }//

                winS++;
            }//

        }

        // return
        result = maxLen;
        return result;
    }


    public static Boolean basicPermutionCheck(String str, String pattern) {
        int winS = 0, winE = 0;
        Map<Character, Integer> freqMapPattern = new HashMap<>();
        for (char letter : pattern.toCharArray()) {
            freqMapPattern.put(letter, freqMapPattern.getOrDefault(letter, 0) + 1);
        }
        Character curLetter = str.charAt(winS);
        int matchedSum = 0;
        if (freqMapPattern.containsKey(curLetter)) {
            freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) - 1);
            if (freqMapPattern.get(curLetter) == 0) {
                matchedSum = 1;
            }
        }
        Boolean result = false;

        while (winE < str.length()) {
            // Filter out
            while (winS <= winE && (matchedSum == freqMapPattern.size())) {
                if (winE - winS + 1 == pattern.length()) {
                    result = true;
                }
                winS++;
                curLetter = str.charAt(winS - 1);
                if (freqMapPattern.containsKey(curLetter)) {
                    freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) + 1);
                    if (freqMapPattern.get(curLetter) == 1) {
                        matchedSum--;
                    }
                }
            }

            // Update
            winE++;
            if (winE < str.length()) {
                curLetter = str.charAt(winE);
                if (freqMapPattern.containsKey(curLetter)) {
                    freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) - 1);
                    if (freqMapPattern.get(curLetter) == 0) {
                        matchedSum++;
                    }
                }
            }

        }

        // return
        return result;
    }


    public static List<Integer> basicPermution(String str, String pattern) {
        int winS = 0, winE = 0;
        Map<Character, Integer> freqMapPattern = new HashMap<>();
        for (char letter : pattern.toCharArray()) {
            freqMapPattern.put(letter, freqMapPattern.getOrDefault(letter, 0) + 1);
        }
        Character curLetter = str.charAt(winS);
        int matchedSum = 0;
        if (freqMapPattern.containsKey(curLetter)) {
            freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) - 1);
            if (freqMapPattern.get(curLetter) == 0) {
                matchedSum = 1;
            }
        }
        List<Integer> result = new ArrayList<Integer>();

        while (winE < str.length()) {
            while (winS <= winE && (matchedSum == freqMapPattern.size())) {
                if (winE - winS + 1 == pattern.length()) {
                    result.add(winS);
                }

                winS++;
                curLetter = str.charAt(winS - 1);
                if (freqMapPattern.containsKey(curLetter)) {
                    freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) + 1);
                    if (freqMapPattern.get(curLetter) == 1) {
                        matchedSum--;
                    }
                }
            }

            winE++;
            if (winE < str.length()) {
                curLetter = str.charAt(winE);
                if (freqMapPattern.containsKey(curLetter)) {
                    freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) - 1);
                    if (freqMapPattern.get(curLetter) == 0) {
                        matchedSum++;
                    }
                }
            }

        }

        // return
        return result;

    }


    public static List<String> basicSmallestSubstring(String str, String pattern) {
        // base check
        int winS = 0, winE = 0;
        List<String> result = new ArrayList<>();
        Map<Character, Integer> freqMapPattern = new HashMap<>();
        for (char letter : pattern.toCharArray()) {
            freqMapPattern.put(letter, freqMapPattern.getOrDefault(letter, 0) + 1);
        }
        Character curLetter = str.charAt(winS);
        int matchedSum = 0;
        if (freqMapPattern.containsKey(curLetter)) {
            freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) - 1);
            if (freqMapPattern.get(curLetter) == 0) {
                matchedSum = 1;
            } else {
                matchedSum = 0;
            }
        }
        int minLen = Integer.MAX_VALUE;

        while (winE < str.length()) {
            // Filter out
            while (winS <= winE && (matchedSum == freqMapPattern.size())) {
                if (minLen > winE - winS + 1) {
                    minLen = Math.min(minLen, winE - winS + 1);
                    result.add(str.substring(winS, winE + 1));
                }
                winS++;
                curLetter = str.charAt(winS - 1);
                if (freqMapPattern.containsKey(curLetter)) {
                    freqMapPattern.put(curLetter, freqMapPattern.get(curLetter) + 1);
                    if (freqMapPattern.get(curLetter) == 1) {
                        matchedSum--;
                    }
                }
            }

            // Update
            winE++;
            if (winE < str.length()) {
                curLetter = str.charAt(winE);
                if (freqMapPattern.containsKey(curLetter)) {
                    freqMapPattern.put(curLetter, freqMapPattern.getOrDefault(curLetter, 0) - 1);
                    if (freqMapPattern.get(curLetter) == 0) {
                        matchedSum++;
                    }
                }

            }
        }

        // return
        return result;
    }


    public static void main(String[] args) {
        System.out.println("find max");
        int result;
        // basic testing
        System.out.println("All subarrays(k == 3): ");
        result = SlideWindow.basic(3, new int[] { 2, 1, 5, 1, 3, 2 });
        System.out.println();

        // basic testing
        System.out.println("OPT");
        System.out.println("All subarrays(k == 3): ");
        result = SlideWindow.basicOPT(3, new int[] { 2, 1, 5, 1, 3, 2 });

        // max testing
        System.out.println("\nBasic Sum");
        result = SlideWindow.basicSumMax(3, new int[] { 2, 1, 5, 1, 3, 2 });
        System.out.println(" " + result + "\n");

        // max testing
        System.out.println("\n Sum_OPT ");
        result = SlideWindow.basicSumMax(3, new int[] { 2, 1, 5, 1, 3, 2 });
        System.out.println(" " + result + "\n");

        // beyond testing
        System.out.println("\nBeyond Sum ORG");
        result = SlideWindow.basicSumBeyondS(7, new int[] { 2, 1, 5, 2, 9, 7 });
        System.out.println("Smallest subarray length: " + result);
        result = SlideWindow.basicSumBeyondS(8, new int[] { 3, 4, 1, 5, 6 });
        System.out.println("Smallest subarray length: " + result);
        result = SlideWindow.basicSumBeyondS(8, new int[] { 2, 1, 5, 2, 8, 2 });
        System.out.println("Smallest subarray length: " + result + "\n");

        // beyond testing
        System.out.println("\nBeyond Sum_OPT");
        result = SlideWindow.basicSumBeyondSOPT(7, new int[] { 2, 1, 5, 2, 9, 7 });
        System.out.println("Smallest subarray length: " + result);
        result = SlideWindow.basicSumBeyondSOPT(8, new int[] { 3, 4, 1, 5, 6 });
        System.out.println("Smallest subarray length: " + result);
        result = SlideWindow.basicSumBeyondSOPT(8, new int[] { 2, 1, 5, 2, 8, 2 });
        System.out.println("Smallest subarray length: " + result + "\n");

        System.out.println("\nBeyond Sum TEST");
        result = SlideWindow.basicSumBeyondSTest(7, new int[] { 2, 1, 5, 2, 9, 7 });
        System.out.println("Smallest subarray length: " + result);
        result = SlideWindow.basicSumBeyondSTest(8, new int[] { 3, 4, 1, 5, 6 });
        System.out.println("Smallest subarray length: " + result);
        result = SlideWindow.basicSumBeyondSTest(8, new int[] { 2, 1, 5, 2, 8, 2 });
        System.out.println("Smallest subarray length: " + result + "\n");

        //Longest testing
        System.out.println("\nLongest_OPT");
        System.out.println("Length of the longest substring: " + SlideWindow.basicKCharLongestOPT(2, "araaci"));
        System.out.println("Length of the longest substring: " + SlideWindow.basicKCharLongestOPT(1, "araaci"));
        System.out.println("Length of the longest substring: " + SlideWindow.basicKCharLongestOPT(3, "cbbebi"));
        System.out.println();

        //Fruit bucket
        System.out.println("\nFruit Bucket ");
        result = SlideWindow.basicFruitTreeBuckets(2, "ABCAC");
        System.out.println("Maximum number of fruits: " + result);
        result = SlideWindow.basicFruitTreeBuckets(2, "ABCBBC");
        System.out.println("Maximum number of fruits: " + result);
        System.out.println();

        //Fruit bucket
        System.out.println("\nFruit Bucket OPT");
        result = SlideWindow.basicFruitTreeBucketsOPT(2, "ABCAC");
        System.out.println("Maximum number of fruits: " + result);
        result = SlideWindow.basicFruitTreeBucketsOPT(2, "ABCBBC");
        System.out.println("Maximum number of fruits: " + result);
        System.out.println();

        //Longest All Characters
        System.out.println("Longest All Characters");
        result = SlideWindow.basicLongestDiffCharSubstring("aabccbb");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestDiffCharSubstring("abbbb");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestDiffCharSubstring("abccde");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestDiffCharSubstring("abcde");
        System.out.println("Length of the longest substring: " + result);
        System.out.println();

        //Longest All Characters RIGHT
        System.out.println("Longest Replacement substring RIGHT Algorithm!");
        result = SlideWindow.basicLongestRepSubstringRight(2, "aabccbb");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestRepSubstringRight(1, "abbcb");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestRepSubstringRight(1, "abccde");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestRepSubstringRight(1, "abcde");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongestRepSubstringRight(1, "aaaaaaa");
        System.out.println("Length of the longest substring: " + result);
        System.out.println();

        //Longest 1S
        System.out.println("Longest 1S substring!");
        result = SlideWindow.basicLongest1sSubstring(2, "01100011011");
        System.out.println("Length of the longest substring: " + result);
        result = SlideWindow.basicLongest1sSubstring(3, "0100110110011");
        System.out.println("Length of the longest substring: " + result);
        System.out.println();

        //Check permution
        System.out.println("Check permution!");
        Boolean result_check = false;
        result_check = SlideWindow.basicPermutionCheck("oidbcaf", "abc");
        System.out.println("Permutation exist: " + result_check);
        result_check = SlideWindow.basicPermutionCheck("odicf", "dc");
        System.out.println("Permutation exist: " + result_check);
        result_check = SlideWindow.basicPermutionCheck("bcdxabcdy", "bcdyabcdx");
        System.out.println("Permutation exist: " + result_check);
        result_check = SlideWindow.basicPermutionCheck("aaacb", "abc");
        System.out.println("Permutation exist: " + result_check);
        System.out.println();

        // Check permution
        System.out.println("All of permutions");
        List<Integer> result_permution = null;
        result_permution = SlideWindow.basicPermution("ppqp", "pq");
        System.out.println("Permution: " + result_permution.toString());
        result_permution = SlideWindow.basicPermution("abbcabc", "abc");
        System.out.println("Permution: " + result_permution.toString());
        System.out.println();

        // Check permution
        System.out.println("Smallest substring");
        List<String> result_sbstring = null;
        result_sbstring = SlideWindow.basicSmallestSubstring("aabdec", "abc");
        if (result_sbstring.size() != 0)
            System.out.println("substring: " + result_sbstring.get(result_sbstring.size() - 1).toString());
        result_sbstring = SlideWindow.basicSmallestSubstring("aabdec", "abac");
        if (result_sbstring.size() != 0)
            System.out.println("substring: " + result_sbstring.get(result_sbstring.size() - 1).toString());
        result_sbstring = SlideWindow.basicSmallestSubstring("abdbca", "abc");
        if (result_sbstring.size() != 0)
            System.out.println("substring: " + result_sbstring.get(result_sbstring.size() - 1).toString());
        result_sbstring = SlideWindow.basicSmallestSubstring("adcad", "abc");
        if (result_sbstring.size() != 0)
            System.out.println("substring: " + result_sbstring.get(result_sbstring.size() - 1).toString());
        else {
            System.out.println("substring: " + result_sbstring.toString());
        }
        System.out.println();
    }

}// class slidewindow
