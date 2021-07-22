package T5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution for homework 5. Handles test cases by means of external files.
 */
public class Solution
{
    /* Template code starts here. */
    public static Integer[] strToIntArr(String s)
    {
        List<Integer> list = new ArrayList<>();
        Arrays.asList(s.split(" ")).forEach(s1 -> list.add(Integer.parseInt(s1)));
        return list.toArray(new Integer[0]);
    }
    /* Template code ends here. */

    public static void main(String[] args) throws IOException
    {
        // Standard input and output reading variables.
        OutputStream os;
        InputStream ts;
        File initialFile, outFile;

        initialFile = new File("src/T5/input.in");
        outFile = new File("src/T5/output.out");
        ts = new FileInputStream(initialFile);
        os = new FileOutputStream(outFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(ts, StandardCharsets.UTF_8));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

        // Pre-allocation of problem variables.
        Integer[] input = strToIntArr(br.readLine());
        int n, T, i, j, min, max, L, act, minSum, maxSum;
        boolean found, finished;
        boolean[] dp1, dp2;
        String[] data;
        int[] array;

        n = input[0];
        T = input[1];
        while(n != 0)
        {
            array = new int[n];
            // Reading and processing the array.
            data = br.readLine().split(" ");
            // If the sum is 0 it can always be made (empty array).
            if(T == 0)
                bw.write("1\n");
            else
            {
                found = false;
                minSum = 0; maxSum = 0;
                // Parsing and processing each array element.
                for (i = 0; i < n && !found; ++i)
                {
                    array[i] = Integer.parseInt(data[i]);
                    if(array[i] == T)
                        found = true;
                    if(array[i] >= 0)
                        maxSum += array[i];
                    else
                        minSum += array[i];
                }
                // If there's a value in the array equal to T.
                if(found)
                {
                    bw.write("1\n");
                    bw.flush();
                    input = strToIntArr(br.readLine());
                    n = input[0];
                    T = input[1];
                    continue;
                }
                // If there was only one element in the array.
                else if(n == 1)
                {
                    bw.write("0\n");
                    bw.flush();
                    n = input[0];
                    T = input[1];
                    continue;
                }
                L = Math.max(Math.abs(minSum), Math.max(Math.abs(T), Math.abs(maxSum)));
                dp1 = new boolean[2*L+1];
                dp2 = new boolean[2*L+1];
                // Base case: can always sum 0 (position L).
                dp1[L] = true;
                // Only first value
                dp1[L+array[0]] = true;
                // Processing the array
                finished = false;
                for(i = 1; i < n && !finished; ++i)
                {
                    for(j = 0; j <= 2*L && !finished; ++j)
                    {
                        act = (j-L) - array[i];
                        if(-L <= act && act <= L)
                            dp2[j] = dp1[j] || dp1[L+act];
                        else
                            dp2[j] = dp1[j];
                        // Checks if it finished
                            if (dp2[L+T])
                                finished = true;
                    }
                    dp1 = dp2.clone();
                }
                // Printing the final result
                if(dp1[L+T])
                    bw.write("1\n");
                else
                    bw.write("0\n");
            }
            bw.flush();
            input = strToIntArr(br.readLine());
            n = input[0];
            T = input[1];
        }
		bw.close();
        br.close();
    }
}
