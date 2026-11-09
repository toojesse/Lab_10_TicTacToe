import java.util.Scanner;

public class SafeInput {

    // Part A: getNonZeroLenString
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    // Part B: getInt
    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine(); // Clear newline
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer, not: " + trash);
            }
        } while (!done);
        return retInt;
    }

    // Part C: getDouble
    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0.0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine(); // Clear newline
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double, not: " + trash);
            }
        } while (!done);
        return retDouble;
    }

    // Part D: getRangedInt
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retInt = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine();
                if (retInt >= low && retInt <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range [" + low + " - " + high + "]: " + retInt);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer, not: " + trash);
            }
        } while (!done);
        return retInt;
    }

    // Part E: getRangedDouble
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retDouble = 0.0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                if (retDouble >= low && retDouble <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range [" + low + " - " + high + "]: " + retDouble);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double, not: " + trash);
            }
        } while (!done);
        return retDouble;
    }

    // Part F: getYNConfirm
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response = "";
        boolean retVal = false;
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                retVal = true;
                done = true;
            } else if (response.equalsIgnoreCase("N")) {
                retVal = false;
                done = true;
            } else {
                System.out.println("You must enter [Y/N], not: " + response);
            }
        } while (!done);
        return retVal;
    }

    // Part G: getRegExString
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Invalid input format: " + response);
            }
        } while (!done);
        return response;
    }

    // Part H: prettyHeader
    public static void prettyHeader(String msg) {
        int totalWidth = 60;

        // Print top row of asterisks using a loop
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print middle row with centered message
        System.out.print("***");
        int totalPadding = totalWidth - 6 - msg.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        for (int i = 0; i < leftPadding; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < rightPadding; i++) {
            System.out.print(" ");
        }
        System.out.println("***");

        // Print bottom row of asterisks using a loop
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}