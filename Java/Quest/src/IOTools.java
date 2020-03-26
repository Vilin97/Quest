import java.util.Arrays;
import java.util.Scanner;

public class IOTools {
    // class to get input from user

    public static String getValidatedInput(String[] possibleInputs) {
        // keep asking the user for input until it is recognized, and return it
        // spaces are stripped away
        String res = "";
        Scanner in = new Scanner(System.in);
        boolean notDone = true;
        while (notDone) {
            String ans = in.nextLine();
            ans = ans.replace(" ", "");
            if (Arrays.asList(possibleInputs).contains(ans)) {
                res = ans;
                notDone = false;
            } else {
                System.out.println("Your input was not recognized. Try again:");
            }
        }
        return res;
    }

    public static String[] getNInputs(String[][] possibleInputs, String delimiter) {
        // get N inputs, with possibleInputs[i] being the possible options for input i
        String res = "";
        Scanner in = new Scanner(System.in);
        String[] ar = new String[0];
        boolean notDone = true;
        while (notDone) {
            boolean success = true;
            String ans = in.nextLine();
            ar = ans.replace(" ", "").split(delimiter);
            if (ar.length == possibleInputs.length) {
                for (int i = 0; i < ar.length; i++) {
                    if (!Arrays.asList(possibleInputs[i]).contains(ar[i])) { success = false; }
                }
            }
            notDone = !success;
            if (notDone) { System.out.println("Your input was not recognized. Try again:"); }
        }
        return ar;
    }

    public static String[] getNInputs(String[][] possibleInputs) {
        return getNInputs(possibleInputs, ",");
    }

    public static String[] getTwoInputs(String[] in1, String[] in2) {
        return getNInputs(new String[][] {in1,in2}, ",");
    }

    public static String[] getStringAndNumberInput(String[] possibleInputs, int l, int u) {
        // get input of a string and a number in range [l,u-1]
        String[][] inputs = new String[][]{possibleInputs, General.intsToString(General.intsInRange(l, u))};
        return getNInputs(inputs);
    }

    public static int getIntInput(int l, int u) {
        // get input of an int within the bounds
        return Integer.parseInt(getNInputs(new String[][]{General.intsToString(General.intsInRange(l, u))})[0]);
    }

    public static int getIntInput(int[] possibleInts) {
        // get input of an int in the possible ints
        return Integer.parseInt(getValidatedInput(General.intsToString(possibleInts)));
    }
}
