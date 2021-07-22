package T5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Generator
{
    // Attributes

    /* Number of cases to generate. */
    public static final int C = 10;

    /* Minimum range for the number of values. */
    public static   final int min = 1000;

    /* Maximum range for the number of values. */
    public static final int max = 5000;

    /* Minimum range for the values. */
    public static final int minRange = -1;

    /* Maximum range for the values. */
    public static final int maxRange = 100;

    public static final int maxSum = 100;

    // Main

    public static void main(String[] args) throws IOException
    {
        // Writing variables.
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/T5/InputRaw.in"));
        StringBuilder sb;

        // Pre-allocation of generation variables.
        int n, T, i, j, x;

        // Each iteration is a new case.
        for(i = 0; i < C; ++i)
        {
            // Number of cases and sum.
            n = ThreadLocalRandom.current().nextInt(min, max+1);
            T = ThreadLocalRandom.current().nextInt(-maxSum, maxSum+1);
            // Initializations.
            sb = new StringBuilder();

            sb.append(n).append(" ").append(T).append("\n");
            for(j = 0; j < n; ++j)
            {
                x = ThreadLocalRandom.current().nextInt(minRange, maxRange+1);
                sb.append(x).append(j != n-1 ? " " : "\n");
            }
            bw.write(sb.toString());
        }
        // Final value to indicate the finalization of the test cases:
        bw.write("0 0\n");
        bw.flush();

        // Closing the writers.
        bw.close();
    }
}
