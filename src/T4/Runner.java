package T4;

import org.apache.commons.lang3.time.StopWatch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Class that does a benchmarking of the time the algorithm takes to solve the problem.
 * It is done over a number of runs, and outputs the average execution time per test case.
 */
public class Runner
{
    // Attributes

    /* Number of runs to execute the code. */
    public static final int RUNS = 100;

    // Main

    public static void main(String[] args) throws IOException
    {
        // BufferedReader br = new BufferedReader(new InputStreamReader(ts, StandardCharsets.UTF_8));
        // Scanner sc = new Scanner(ts);

        // Time variables.
        OutputStream os2 = new FileOutputStream("src/T4/times.out");
        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(os2, StandardCharsets.UTF_8));

        //***************************************************//
        ArrayList<Long> times = new ArrayList<>(RUNS);       //
        StopWatch watch = new StopWatch();                   //
        File initialFile;                                    //
        InputStream ts;                                      //
        initialFile = new File("src/T4/input.in"); //
        //***************************************************//

        // Main loop for testing the average number of cases.
        for (int run = 0; run < RUNS; ++run) {
            //************************************//
            watch.start();                        //
            ts = new FileInputStream(initialFile);//
            //***********************************/*/

            // SOLUTION STARTS HERE.

            BufferedReader br = new BufferedReader(new InputStreamReader(ts, StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // Pre-allocation of problem variables.
            int n, i, x, y;
            String[] dataA, dataB;
            HashMap<Integer, Integer> mapA, mapB;
            Solution.Pair<Boolean, Integer> pair;

            n = Integer.parseInt(br.readLine());
            while (n != 0) {
                // String arrays containing the data of each array.
                dataA = br.readLine().split(" ");
                dataB = br.readLine().split(" ");
                // Maps to track frequency of the array values.
                mapA = new HashMap<>(2 * n, (float) 0.75);
                mapB = new HashMap<>(2 * n, (float) 0.75);
                // The cycle will process both arrays and update the frequencies of values.
                for (i = 0; i < n; ++i) {
                    x = Integer.parseInt(dataA[i]);
                    y = Integer.parseInt(dataB[i]);
                    if (mapA.containsKey(x))
                        mapA.put(x, mapA.get(x) + 1);
                    else
                        mapA.put(x, 1);
                    if (mapB.containsKey(y))
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

            // SOLUTION ENDS HERE.

            //***********************************************//
            watch.stop();                                    //
            long time = watch.getTime(TimeUnit.MILLISECONDS);//
            times.add(time);                                 //
            watch.reset();                                   //
            //***********************************************//
            bw.close();
            br.close();
        }

        long average = 0, min = Long.MAX_VALUE, max = 0;
        for (Long time : times)
        {
            min = Math.min(min, time);
            max = Math.max(max, time);
            average += time / RUNS;
        }
        bw2.write("Runs: " + RUNS + "\n");
        bw2.write("Average: " + average + " ms\n");
        bw2.write("Best time: " + min + " ms\n");
        bw2.write("Worst time: " + max + " ms");
        bw2.flush();
        bw2.close();
    }

    private static Solution.Pair<Boolean, Integer> findMostFrequent(HashMap<Integer, Integer> bigMap, HashMap<Integer, Integer> smallMap)
    {
        int temp;
        int count = 0;
        Solution.Pair<Boolean, Integer> pair = new Solution.Pair<>(false, 0);
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
