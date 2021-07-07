package T4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Generator
{
    // Attributes

    /* Number of cases to generate. */
    public static final int C = 10;

    /* Minimum range for the number of values. */
    public static   final int min = 9999;

    /* Maximum range for the number of values. */
    public static final int max = 9999;

    /* Minimum range for the values. */
    public static final int minRange = -100000000;

    /* Maximum range for the values. */
    public static final int maxRange = 100000000;

    // Main

    public static void main(String[] args) throws IOException
    {
        // Writing variables.
        BufferedWriter bw1 = new BufferedWriter(new FileWriter("src/T4/InputRaw.in"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/T4/SolutionRaw.out"));
        StringBuilder sb;

        // Pre-allocation of generation variables.
        int i, j, N, fMax, f, g, value1 = 0, value2 = 0, firstValue = 0;
        HashMap<Integer, Integer> usedValues;
        ArrayList<Integer> array1, array2;
        boolean gen1, gen2, first, hasSolution;

        // Each iteration is a new case.
        for(i = 0; i < C; ++i)
        {
            // Number of cases and starting maximum frequency.
            N = ThreadLocalRandom.current().nextInt(min, max+1);
            hasSolution = Math.abs(ThreadLocalRandom.current().nextInt(minRange, maxRange+1)) % 2 == 0;
            if(hasSolution)
                fMax = ThreadLocalRandom.current().nextInt(2, N+1);
            else
                fMax = ThreadLocalRandom.current().nextInt(2, (int) Math.ceil((double) N/2)+1);

            // Initializations.
            usedValues = new HashMap<>(2*N);   // HashMap where the used values will be stored.
            array1 = new ArrayList<>();                     // First array where the values will be stored.
            array2 = new ArrayList<>();                     // Second array where the values will be stored.
            first = true;                                   // Boolean to use for storing the most frequent value.
            f = fMax;                                       // The frequency of each generation.
            g = hasSolution ? 2*N : N;                      // Let g be the number of available spaces in the array.

            // Each iteration is a new generation of values.
            while(g > 0)
            {
                // Value creation.
                gen1 = true;
                gen2 = true;
                while(gen1)
                {
                    // A random value is generated with a range [minRange, maxRange].
                    value1 = ThreadLocalRandom.current().nextInt(minRange, maxRange+1);
                    // If the value has already been used, it generated another, until a new one is found and stored.
                    if(!usedValues.containsKey(value1))
                    {
                        usedValues.put(value1, f);
                        gen1 = false;
                        // Conditional to store the first value.
                        if(first)
                        {
                            firstValue = value1;
                            first = false;
                        }
                    }
                    if(!hasSolution)
                    {
                        while(gen2)
                        {
                            // If there's no solution, value2 should not be in the used values (since it's for array 1)
                            // and it should be different to value1 so it doesn't add the same.
                            value2 = ThreadLocalRandom.current().nextInt(minRange, maxRange+1);
                            if(value1 != value2 && !usedValues.containsKey(value2))
                            {
                                usedValues.put(value2, f);
                                gen2 = false;
                            }
                        }
                    }
                }
                // If there's a solution, both arrays are filled with the same value, distributed.
                if(hasSolution)
                {
                    for(j = 0; j < f; ++j)
                    {
                        if(array1.size() <= array2.size() && array1.size() < N)
                            array1.add(value1);
                        else if(array2.size() < N)
                            array2.add(value1);
                    }
                }
                // If there's no solution, values are added with the same frequency on both arrays.
                else
                {
                    for(j = 0; j < f; ++j)
                    {
                        array1.add(value1);
                        array2.add(value2);
                    }
                }

                // Update of g, the maximum frequency, and the new frequency, which has a range [1, fMax-1]:
                g -= f;                                                                 // The number of available spaces is reduced by f.
                fMax -= Math.floor((double) f/2);                                       // Helps to lower the upper bound of the frequency generation.
                f = Math.min(ThreadLocalRandom.current().nextInt(1, fMax+1), g);
            }
            // The arrays are shuffled.
            Collections.shuffle(array1);
            Collections.shuffle(array2);

            // Output of the solution (most frequent value): all numbers have a new line at the end except for the last case.
            if(i != C - 1)
                bw2.write((hasSolution ? firstValue : "nohay") + "\n");
            else
                bw2.write("" + (hasSolution ? firstValue : "nohay"));
            bw2.flush();

            // Output of the input case (size of the array + array).
            sb = new StringBuilder(N + "\n");
            for(j = 0; j < array1.size(); ++j)
            {
                // All number have a space at the end except for the last value, which starts a next line.
                if(j != array1.size()-1)
                    sb.append(array1.get(j)).append(" ");
                else
                    sb.append(array1.get(j)).append("\n");
            }
            for(j = 0; j < array2.size(); ++j)
            {
                if(j != array2.size()-1)
                    sb.append(array2.get(j)).append(" ");
                else
                    sb.append(array2.get(j)).append("\n");
            }

            // The built string is printed out to the output file.
            bw1.write(sb.toString());
            bw1.flush();
        }
        // Final value to indicate the finalization of the test cases:
        bw1.write("0\n");
        bw1.flush();

        // Closing the writers.
        bw1.close();
        bw2.close();
    }
}
