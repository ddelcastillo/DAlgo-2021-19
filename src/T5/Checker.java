package T5;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Checks the outputted solutions with the solution file.
 */
public class Checker
{
    // Main

    public static void main(String[] args) throws IOException
    {
        // Solution and output files.
        File solutionFile = new File("src/T5/solution.out");
        File outputFile = new File("src/T5/output.out");
        InputStream ss = new FileInputStream(solutionFile);
        InputStream os = new FileInputStream(outputFile);

        // Buffered readers for the output and solution files.
        BufferedReader sbr = new BufferedReader(new InputStreamReader(ss, StandardCharsets.UTF_8));
        BufferedReader obr = new BufferedReader(new InputStreamReader(os, StandardCharsets.UTF_8));

        String line1, line2;
        int counter = 0;
        // Compares each case.
        for(int i = 0; i < 100; i++)
        {
            if((line1 = obr.readLine()) != null && (line2 = sbr.readLine()) != null)
            {
                if(line1.equals(line2))
                    ++counter;
            }
        }

        // Outputs how many solutions are correct.
        System.out.println("Total: " + counter + "/100.");

        sbr.close();
        obr.close();
    }
}