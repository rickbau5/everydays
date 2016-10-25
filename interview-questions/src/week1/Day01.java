package week1;

import java.util.Arrays;

/**
 * Created by Rick on 10/24/16.
 */
public class Day01 {

    public static void main(String[] args) {
        System.out.println("---- Permutation ----");
        checkPerm("abc", "bac");
        checkPerm("abb", "bac");
        checkPerm("flibbity flab!", "flib flabbity!");
    }

    /* 1.2 Given two strings, write method to decide if one is permutation of the other. */

    /**
     * Naive solution: just sort the arrays and compare char for char.
     *
     * O(n + n log n + n log n) = O(n log n), where n is length of strings
     *
     * @param a first string
     * @param b second string
     * @return T/F if a is a permutation of b
     */
    static boolean isPerm_Sort(String a, String b) {
        if (a.length() != b.length())
            return false;
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        Arrays.sort(aChars);    // O(n log n)
        Arrays.sort(bChars);    // O(n log n)

        for (int i = 0; i < aChars.length; i++) // O(n)
            if (aChars[i] != bChars[i])
                return false;

        return true;
    }

    /**
     * Use an array to keep track of numbers of characters encountered in first
     * string. Then traverse second string and decrement, if negative it's mismatched!
     *
     * O(n + n) = O(n), where n is length of the strings.
     * Note that if the strings aren't a permutation, it will most likely
     * not traverse the entire string a second time. So O(n) only, not O(2n).
     *
     * @param a first string
     * @param b second string
     * @return T/F whether they are permutations of the each other
     */
    static boolean isPerm_Array(String a, String b) {
        if (a.length() != b.length())
            return false;

        int chars[] = new int[256];

        int i;
        for (i = 0; i < a.length(); i++) {  // O(n)
            chars[a.charAt(i)] += 1;
        }

        for (i = 0; i < b.length(); i++) {  // O(n)
            chars[b.charAt(i)] -= 1;
            if (chars[b.charAt(i)] < 0) {
                return false;
            }
        }

        return true;
    }

    static void checkPerm(String arg, String arg2) {
        System.out.printf("\"%s\" & \"%s\" ? %s\n", arg, arg2, isPerm_Sort(arg, arg2) ? "true" : "false");
        System.out.printf("\"%s\" & \"%s\" ? %s\n", arg, arg2, isPerm_Array(arg, arg2) ? "true" : "false");
    }
}
