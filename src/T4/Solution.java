package T4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Solution for homework 4. Handles test cases by means of external files.
 */
public class Solution
{
    /* Template code starts here. */
    public static class Pair<T1, T2>
    {
        public T1 left; public T2 right;
        public Pair(T1 pLeft, T2 pRight)
        { this.left = pLeft; this.right = pRight; }
    }
    /* Template code ends here. */

    // Main

    public static void main(String[] args) throws IOException
    {
        // Standard input and output reading variables.
        OutputStream os;
        InputStream ts;
        File initialFile, outFile;

        initialFile = new File("src/T4/input.in");
        outFile = new File("src/T4/output.out");
        ts = new FileInputStream(initialFile);
        os = new FileOutputStream(outFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(ts, StandardCharsets.UTF_8));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

        // Pre-allocation of problem variables.
        int n, i, x, y;
        String[] dataA, dataB;
        HashMap<Integer, Integer> mapA, mapB;
        Pair<Boolean, Integer> pair;

        n = Integer.parseInt(br.readLine());
        while(n != 0)
        {
            // String arrays containing the data of each array.
            dataA = br.readLine().split(" ");
            dataB = br.readLine().split(" ");
            // Maps to track frequency of the array values.
            mapA = new HashMap<>(2*n, (float) 0.75);
            mapB = new HashMap<>(2*n, (float) 0.75);
            // The cycle will process both arrays and update the frequencies of values.
            for(i = 0; i < n; ++i)
            {
                x = Integer.parseInt(dataA[i]);
                y = Integer.parseInt(dataB[i]);
                if(mapA.containsKey(x))
                    mapA.put(x, mapA.get(x) + 1);
                else
                    mapA.put(x, 1);
                if(mapB.containsKey(y))
                    mapB.put(y, mapB.get(y) + 1);
                else
                    mapB.put(y, 1);
            }
            // Pair that contain whether there's an answer, and if it there is, which value has the highest frequency.
            pair = mapA.size() > mapB.size() ? findMostFrequent(mapA, mapB) : findMostFrequent(mapB, mapA);
            bw.write((pair.left ? pair.right : "nohay") + "\n");
            n = Integer.parseInt(br.readLine());
        }
        bw.flush();
        br.close();
        bw.close();
    }

    /**
     * Finds the value with the highest frequency on two given frequency maps.
     * @param bigMap Frequency map with a higher number of unique values.
     * @param smallMap Frequency map with a lower number of unique values.
     * @return A pair containing whether there is an answer, and if so, which value it is.
     */
    private static Pair<Boolean, Integer> findMostFrequent(HashMap<Integer, Integer> bigMap, HashMap<Integer, Integer> smallMap)
    {
        int temp;
        int count = 0;
        Pair<Boolean, Integer> pair = new Pair<>(false, 0);
        // It's more efficient to iterate through the map with fewer unique values (fewer keys).
        for(Integer key : smallMap.keySet())
        {
            if(bigMap.containsKey(key))
            {
                temp = bigMap.get(key) + smallMap.get(key);
                if(temp > count)
                {
                    pair.left = true;
                    pair.right = key;
                    count = temp;
                }
            }
        }
        return pair;
    }
}
